package io.thinkinglabs.client_files.client;

public class DefaultCreateClient implements CreateClient {

    @Override
    public Client createClient(CreateClientCommand createClientCommand) {
        return Client.create(createClientCommand.getFirstname(), createClientCommand.getLastname());
    }
}
