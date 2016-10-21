package io.thinkinglabs.client_files.client;

import io.thinkinglabs.Builder;

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
        return aClient().withDefaults();
    }

    public PersistedClientBuilder withDefaults() {
        this.firstname = "John";
        this.lastname = "Doe";
        return this;
    }

    @Override
    public PersistedClient build() {
        return new PersistedClient(firstname, lastname);
    }
}
