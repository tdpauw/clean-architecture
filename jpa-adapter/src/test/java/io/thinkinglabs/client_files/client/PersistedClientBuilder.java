package io.thinkinglabs.client_files.client;

import io.thinkinglabs.Builder;

/**
 * @author @tdpauw
 */
public class PersistedClientBuilder implements Builder<PersistedClient> {
    private String firstname;
    private String lastname;

    private PersistedClientBuilder() {
        //do nothing
    }

    public static PersistedClientBuilder aClient() {
        return new PersistedClientBuilder();
    }

    public static PersistedClientBuilder johnDoe() {
        return aClient().withFirstname("John").withLastname("Doe");
    }

    public PersistedClientBuilder withFirstname(String firstname)
    {
        this.firstname = firstname;
        return this;
    }

    public PersistedClientBuilder withLastname(String lastname)
    {
        this.lastname = lastname;
        return this;
    }

    @Override
    public PersistedClient build() {
        return new PersistedClient(firstname, lastname);
    }
}
