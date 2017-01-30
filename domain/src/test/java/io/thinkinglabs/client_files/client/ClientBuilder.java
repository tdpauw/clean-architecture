package io.thinkinglabs.client_files.client;

public class ClientBuilder {
    private String firstname;
    private String lastname;

    private ClientBuilder() {
        //do nothing
    }

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }


    public static ClientBuilder johnDoe() {
        return aClient().withDefaults();
    }

    public ClientBuilder withDefaults() {
        this.firstname = "John";
        this.lastname = "Doe";
        return this;
    }

    public Client build() {
        return Client.create(lastname, firstname);
    }
}
