package io.thinkinglabs.client_files;

import io.thinkinglabs.client_files.client.*;
import org.junit.Test;

import static io.thinkinglabs.client_files.client.ClientBuilder.aDefaultClient;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateClientTest {

	public CreateClient sut = new DefaultCreateClient();

	@Test
	public void createClient() {
		Client client = sut.createClient(new CreateClientCommand("firstname", "lastname"));
		assertThat(client).isEqualToComparingFieldByField(aDefaultClient().build());
	}
}
