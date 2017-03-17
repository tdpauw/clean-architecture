package io.thinkinglabs.clean_architecture;

import io.thinkinglabs.clean_architecture.client.DefaultCreateClient;
import io.thinkinglabs.clean_architecture.client.PersistedClientBase;
import io.thinkinglabs.clean_architecture.client.ToPersistedClientMapper;
import io.vertx.core.Vertx;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

/**
 * @author @tdpauw
 */
public class Main
{
    public static final void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        final HashMap properties = new HashMap();
        properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:mem:clean-architecture;create=true");
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("clean-architecture", properties);

        vertx.deployVerticle(
                new ServerVerticle(
                        new DefaultCreateClient(
                                new PersistedClientBase(entityManagerFactory.createEntityManager(), new ToPersistedClientMapper()), new UUIDGenerator())));
    }
}
