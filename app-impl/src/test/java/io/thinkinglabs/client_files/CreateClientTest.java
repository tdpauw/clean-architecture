package io.thinkinglabs.client_files;

import io.thinkinglabs.client_files.client.CreateClient;
import io.thinkinglabs.client_files.client.CreateClientCommand;
import org.junit.Test;

import io.thinkinglabs.client_files.client.Client;
import io.thinkinglabs.client_files.client.DefaultCreateClient;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateClientTest {

	public CreateClient sut = new DefaultCreateClient();

	@Test
	public void createClient() {
		Client client = sut.createClient(new CreateClientCommand("firstname", "lastname"));
		assertThat(client).isEqualTo(Client.create("firstname", "lastname"));
	}
}
