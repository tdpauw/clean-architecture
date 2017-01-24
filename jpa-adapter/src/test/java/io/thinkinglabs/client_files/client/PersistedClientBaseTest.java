package io.thinkinglabs.client_files.client;

import io.thinkinglabs.DatabaseMigrationRule;
import io.thinkinglabs.JPATransactor;
import io.thinkinglabs.Transactor;
import io.thinkinglabs.client_files.TestConstants;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static io.thinkinglabs.client_files.client.ClientBuilder.johnDoe;

public class PersistedClientBaseTest {

    final EntityManager entityManager = Persistence.createEntityManagerFactory(TestConstants.PERSISTENCE_UNIT_NAME).createEntityManager();
    final Transactor transactor = new JPATransactor(entityManager);
    final PersistedClientBase sut = new PersistedClientBase(entityManager, new ToPersistedClientMapper());

    @Rule
    final public DatabaseMigrationRule databaseMigration = new DatabaseMigrationRule(entityManager, transactor);

    @Test
    public void canAddClient() throws Exception {
        //TODO have a translator from domain Client to PersistedClient
        //PersistedClientBase takes a domain Client, translates it ot PersistedClient and saves
        Client client = johnDoe().build();

        transactor.perform(() -> {
           sut.addClient(client);
        });
    }
}
