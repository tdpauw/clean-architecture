package io.thinkinglabs.client_files.client;

/**
 * @author @tdpauw
 */
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
        return aClient().withFirstname("John").withLastname("Doe");
    }

    public ClientBuilder withFirstname(String firstname)
    {
        this.firstname = firstname;
        return this;
    }

    public ClientBuilder withLastname(String lastname)
    {
        this.lastname = lastname;
        return this;
    }

    public Client build() {
        return Client.create(firstname, lastname);
    }
}
