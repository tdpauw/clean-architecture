package io.thinkinglabs.client_files.client;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class ClientCreateTest {

    @Test
    public void createsValid() {
        Client client = Client.create("uuid-1234", "firstname", "lastname");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(client.getClientId()).isEqualTo("uuid-1234");
        softly.assertThat(client.getFirstname()).isEqualTo("firstname");
        softly.assertThat(client.getLastname()).isEqualTo("lastname");
        softly.assertAll();
    }
}
