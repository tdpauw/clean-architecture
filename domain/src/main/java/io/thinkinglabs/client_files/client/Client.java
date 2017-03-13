package io.thinkinglabs.client_files.client;

/**
 * @author @tdpauw
 */
public class Client {

    private final String clientId;
    private final String firstname;
    private final String lastname;

    private Client(final String clientId, String firstname, String lastname) {
        this.clientId = clientId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static Client create(final String clientId, String firstname, String lastname) {
        return new Client(clientId, firstname, lastname);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getClientId()
    {
        return "uuid";
    }
}
