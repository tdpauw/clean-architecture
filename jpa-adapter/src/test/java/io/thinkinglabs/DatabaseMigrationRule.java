package io.thinkinglabs;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseMigrationRule implements TestRule {

    private final EntityManager entityManager;
    private final Transactor transactor;

    public DatabaseMigrationRule(final EntityManager entityManager, final Transactor transactor) {
        this.entityManager = entityManager;
        this.transactor = transactor;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                transactor.perform(() -> {
                    Session session = entityManager.unwrap(Session.class);
                    session.doWork(new Work() {
                        @Override
                        public void execute(Connection connection) throws SQLException {
                            try {
                                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

                                Liquibase liquibase = new Liquibase("META-INF/liquibase/db-changelog.xml", new ClassLoaderResourceAccessor(), database);

                                liquibase.update(new Contexts(), new LabelExpression());
                            } catch (LiquibaseException e) {
                                throw new SQLException("Could not migrate database schema using Liquibase", e);
                            }
                        }
                    });
                });
                base.evaluate();
            }
        };
    }
}
