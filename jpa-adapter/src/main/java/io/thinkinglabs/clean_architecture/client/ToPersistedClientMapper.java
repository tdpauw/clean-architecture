package io.thinkinglabs.clean_architecture.client;

public class ToPersistedClientMapper
{
    public PersistedClient from(Client client) {
        return new PersistedClient(client.getClientId(), client.getFirstname(), client.getLastname());
    }
}
