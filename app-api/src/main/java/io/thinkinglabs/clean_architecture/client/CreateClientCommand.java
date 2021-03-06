package io.thinkinglabs.clean_architecture.client;

public class CreateClientCommand {
    private final String firstname;
    private final String lastname;

    public CreateClientCommand(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
