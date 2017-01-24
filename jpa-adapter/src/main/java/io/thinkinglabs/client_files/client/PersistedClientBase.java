package io.thinkinglabs.client_files.client;

import javax.persistence.EntityManager;

public class PersistedClientBase implements ClientBase {

    private final EntityManager entityManager;
    private TranslateToPersistedClient translateToDomain;

    public PersistedClientBase(EntityManager entityManager, final TranslateToPersistedClient translateToDomain) {

        this.entityManager = entityManager;
        this.translateToDomain = translateToDomain;
    }

    @Override
    public void addClient(Client client) {
        PersistedClient persistedClient = translateToDomain.from(client);
        entityManager.persist(persistedClient);
    }
}
