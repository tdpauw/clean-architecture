package io.thinkinglabs.client_files.client;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class ClientCreateTest {

    @Test
    public void createsValid() {
        Client client = Client.create("uuid", "firstname", "lastname");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(client.getClientId()).isEqualTo("uuid");
        softly.assertThat(client.getFirstname()).isEqualTo("firstname");
        softly.assertThat(client.getLastname()).isEqualTo("lastname");
        softly.assertAll();
    }
}
