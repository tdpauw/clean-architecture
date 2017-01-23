package io.thinkinglabs.client_files.client;

import javax.persistence.EntityManager;

public class PersistedClientBase implements ClientBase {

    private final EntityManager entityManager;
    private TranslateToPersistedClient translateToDomain;

    public PersistedClientBase(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public void addClient(Client client) {
        PersistedClient persistedClient = translateToDomain.from(client);
        entityManager.persist(persistedClient);
    }
}
