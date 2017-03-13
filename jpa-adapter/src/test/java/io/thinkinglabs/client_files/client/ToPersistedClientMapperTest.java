package io.thinkinglabs.client_files.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ToPersistedClientMapperTest
{
    private final ToPersistedClientMapper sut = new ToPersistedClientMapper();

    @Test
    public void fromDomainToPersistentObject() {
        assertThat(sut.from(ClientBuilder.scottYarrington().build())).isEqualToComparingFieldByField(PersistedClientBuilder.scottYarrington().build());
    }
}
