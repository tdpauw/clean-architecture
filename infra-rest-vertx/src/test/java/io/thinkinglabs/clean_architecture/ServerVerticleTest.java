package io.thinkinglabs.clean_architecture;

import io.thinkinglabs.clean_architecture.client.DefaultCreateClient;
import io.thinkinglabs.clean_architecture.client.PersistedClientBase;
import io.thinkinglabs.clean_architecture.client.ToPersistedClientMapper;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import static io.thinkinglabs.clean_architecture.RegexMatcher.matches;
import static io.thinkinglabs.clean_architecture.VertxMatcherAssert.assertThat;

/**
 * @author @tdpauw
 */
@RunWith(VertxUnitRunner.class)
public class ServerVerticleTest
{
    //TODO use hsqldb with a file "jdbc:hsqldb:file:build/clean-architecture;create=true"
    //TODO pass Persistence Unit properties user, password, connection-url to the EntityManagerFactory using a Map
    private final String connectionUrl = "jdbc:hsqldb:mem:clean-architecture;create=true";

    private Vertx vertx;

    @Before
    public void setUp(TestContext context) throws SQLException
    {
        migrateDatabase(connectionUrl);

        final HashMap properties = new HashMap();
        properties.put("javax.persistence.jdbc.url", connectionUrl);
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("acceptance-tests", properties);

        vertx = Vertx.vertx();
        vertx.deployVerticle(
                new ServerVerticle(
                        new DefaultCreateClient(
                                new PersistedClientBase(entityManagerFactory.createEntityManager(), new ToPersistedClientMapper()), new UUIDGenerator())),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context)
    {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void home(TestContext context)
    {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8080, "localhost", "/",
                response ->
                {
                    response.handler(body ->
                    {
                        context.assertEquals("<h1>It' working</h1>", body.toString());
                        async.complete();
                    });
                });
    }

    @Test
    public void createClient(TestContext context)
    {
        final Async async = context.async();

        final HttpClientRequest request = vertx.createHttpClient().post(8080, "localhost", "/api/clients",
                response ->
                {
                    response.handler(body ->
                    {
                        context.assertEquals(201, response.statusCode());
                        // "^(https?)://([-a-zA-Z0-9.]+)(:[0-9]+)?/([-a-zA-Z0-9/]*)[-a-zA-Z0-9]+"
                        assertThat(context, response.getHeader("Location"), matches("^http://localhost:8080/api/clients/[-a-zA-Z0-9]{36}"));
                        context.assertEquals("application/json", response.getHeader("Content-Type"));

                        final JsonObject jsonObject = new JsonObject(body.toString());
                        context.assertEquals("John", jsonObject.getString("firstname"));
                        context.assertEquals("Doe", jsonObject.getString("lastname"));

                        async.complete();
                    });
                });

        final String jsonObject = new JsonObject().put("firstname", "John").put("lastname", "Doe").encode();
        Buffer buffer = Buffer.buffer(jsonObject.getBytes(Charset.forName("UTF-8")));
        request.putHeader(HttpHeaders.CONTENT_LENGTH, buffer.length() + "");
        request.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.write(buffer);
    }

    private void migrateDatabase(String connectionUrl) throws SQLException
    {
        try
        {
            final Connection connection = DriverManager.getConnection(connectionUrl);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase("META-INF/liquibase/db-changelog.xml", new ClassLoaderResourceAccessor(), database);

            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e)
        {
            throw new SQLException("Could not migrate database schema using Liquibase", e);
        } catch (SQLException e)
        {
            throw new SQLException("Unable to get a JDBC connection for " + connectionUrl);
        }
    }
}
