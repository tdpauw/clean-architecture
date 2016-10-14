package io.thinkinglabs.client_files.client;

public class ClientBuilder {
    private String firstname;
    private String lastname;

    private ClientBuilder() {
    }

    public static ClientBuilder aDefaultClient() {
        return new ClientBuilder().withDefaults();
    }

    private ClientBuilder withDefaults() {
        firstname = "firstname";
        lastname = "lastname";
        return this;
    }

    public Client build() {
        return Client.create(firstname, lastname);
    }
}
