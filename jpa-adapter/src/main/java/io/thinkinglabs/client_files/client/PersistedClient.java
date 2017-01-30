package io.thinkinglabs.client_files.client;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "client")
@SequenceGenerator(name = "client_seq", sequenceName = "client_seq")
public class PersistedClient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;

    private String firstname;
    private String lastname;

    public PersistedClient(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
