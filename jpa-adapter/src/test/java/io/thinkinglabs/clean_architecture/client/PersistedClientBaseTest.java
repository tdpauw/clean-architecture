package io.thinkinglabs.clean_architecture.client;

import io.thinkinglabs.DatabaseMigrationRule;
import io.thinkinglabs.JPATransactor;
import io.thinkinglabs.Transactor;
import io.thinkinglabs.clean_architecture.TestConstants;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static io.thinkinglabs.clean_architecture.client.ClientBuilder.scottYarrington;

public class PersistedClientBaseTest {

    final EntityManager entityManager = Persistence.createEntityManagerFactory(TestConstants.PERSISTENCE_UNIT_NAME).createEntityManager();
    final Transactor transactor = new JPATransactor(entityManager);
    final PersistedClientBase sut = new PersistedClientBase(entityManager, new ToPersistedClientMapper());

    @Rule
    final public DatabaseMigrationRule databaseMigration = new DatabaseMigrationRule(entityManager, transactor);

    @Test
    public void canAddClient() throws Exception {
        Client client = scottYarrington().build();

        transactor.perform(() -> {
           sut.addClient(client);
        });
    }
}
