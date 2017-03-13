package io.thinkinglabs.client_files.client;

/**
 * @author @tdpauw
 */
public class ClientBuilder {
    private String firstname;
    private String lastname;
    private String clientId;

    private ClientBuilder() {
        //do nothing
    }

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }


    public static ClientBuilder scottYarrington() {
        return aClient().withClientId(ObjectMother.SCOTT_YARRINGTON_ID).withFirstname("Scott").withLastname("Yarrington");
    }

    public ClientBuilder withClientId(final String clientId)
    {
        this.clientId = clientId;
        return this;
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
        return Client.create(clientId, firstname, lastname);
    }

}
