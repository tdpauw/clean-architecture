package io.thinkinglabs.client_files.client;

public class Client {

    private final String firstname;
    private final String lastname;

    private Client(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static Client create(String lastname, String firstname) {
        return new Client(firstname, lastname);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
