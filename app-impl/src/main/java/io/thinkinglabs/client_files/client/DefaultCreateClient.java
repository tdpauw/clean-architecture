package io.thinkinglabs.client_files.client;

public class DefaultCreateClient implements CreateClient {

    private final ClientBase clientBase;

    public DefaultCreateClient(ClientBase clientBase) {
        this.clientBase = clientBase;
    }

    @Override
    public Client createClient(CreateClientCommand createClientCommand) {
        Client client = Client.create(createClientCommand.getFirstname(), createClientCommand.getLastname());
        clientBase.addClient(client);
        return client;
    }
}
