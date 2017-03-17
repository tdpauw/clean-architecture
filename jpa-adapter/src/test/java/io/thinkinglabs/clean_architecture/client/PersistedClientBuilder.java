package io.thinkinglabs.clean_architecture.client;

import io.thinkinglabs.Builder;

import static io.thinkinglabs.clean_architecture.client.ObjectMother.SCOTT_YARRINGTON_ID;

/**
 * @author @tdpauw
 */
public class PersistedClientBuilder implements Builder<PersistedClient> {
    private String firstname;
    private String lastname;
    private String clientId;

    private PersistedClientBuilder() {
        //do nothing
    }

    public static PersistedClientBuilder aClient() {
        return new PersistedClientBuilder();
    }

    public static PersistedClientBuilder scottYarrington() {
        return aClient().withClientId(SCOTT_YARRINGTON_ID).withFirstname("Scott").withLastname("Yarrington");
    }

    private PersistedClientBuilder withClientId(final String clientId)
    {
        this.clientId = clientId;
        return this;
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
        return new PersistedClient(clientId, firstname, lastname);
    }
}
