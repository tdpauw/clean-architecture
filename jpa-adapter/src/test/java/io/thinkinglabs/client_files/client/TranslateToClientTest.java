package io.thinkinglabs.client_files.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TranslateToClientTest {

    private final TranslateToPersistedClient sut = new TranslateToPersistedClient();

    @Test
    public void translateFromDomainToPersistentObject() {
        assertThat(sut.from(ClientBuilder.johnDoe().build())).isEqualToComparingFieldByField(PersistedClientBuilder.johnDoe().build());
    }
}
