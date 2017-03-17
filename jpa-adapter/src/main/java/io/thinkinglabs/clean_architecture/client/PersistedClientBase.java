package io.thinkinglabs.clean_architecture.client;

import javax.persistence.EntityManager;

public class PersistedClientBase implements ClientBase
{

    private final EntityManager entityManager;
    private ToPersistedClientMapper translateToDomain;

    public PersistedClientBase(EntityManager entityManager, final ToPersistedClientMapper translateToDomain) {

        this.entityManager = entityManager;
        this.translateToDomain = translateToDomain;
    }

    @Override
    public void addClient(Client client) {
        PersistedClient persistedClient = translateToDomain.from(client);
        entityManager.persist(persistedClient);
    }
}
