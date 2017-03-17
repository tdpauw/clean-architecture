package io.thinkinglabs.client_files.client;

import javax.persistence.*;

@Entity(name = "client")
@SequenceGenerator(name = "client_seq", sequenceName = "client_seq")
public class PersistedClient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;

    private final String clientId;
    private final String firstname;
    private final String lastname;

    public PersistedClient(final String clientId, String firstname, String lastname) {
        this.clientId = clientId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public String getClientId()
    {
        return clientId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
