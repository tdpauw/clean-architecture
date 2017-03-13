package io.thinkinglabs.client_files.client;

import io.thinkinglabs.client_files.UUIDGenerator;

public class DefaultCreateClient implements CreateClient {

    private final ClientBase clientBase;
    private final UUIDGenerator uuidGenerator;

    public DefaultCreateClient(ClientBase clientBase, final UUIDGenerator uuidGenerator) {
        this.clientBase = clientBase;
        this.uuidGenerator = uuidGenerator;
    }

    @Override
    public Client createClient(CreateClientCommand createClientCommand) {
        Client client = Client.create(uuidGenerator.next(), createClientCommand.getFirstname(), createClientCommand.getLastname());
        clientBase.addClient(client);
        return client;
    }
}
