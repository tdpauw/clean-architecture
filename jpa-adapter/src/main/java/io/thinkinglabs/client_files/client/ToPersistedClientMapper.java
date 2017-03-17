package io.thinkinglabs.client_files.client;

public class ToPersistedClientMapper
{
    public PersistedClient from(Client client) {
        return new PersistedClient(client.getClientId(), client.getFirstname(), client.getLastname());
    }
}
