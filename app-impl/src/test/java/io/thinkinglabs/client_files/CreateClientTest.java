package io.thinkinglabs.client_files;

import io.thinkinglabs.client_files.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static io.thinkinglabs.client_files.client.ClientBuilder.scottYarrington;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateClientTest {

	private final ClientBase clientBase = mock(ClientBase.class);
	private final CreateClient sut = new DefaultCreateClient(clientBase);

	@Test
	public void createClient() {
		Client client = sut.createClient(new CreateClientCommand("Scott", "Yarrington"));

		Client expected = scottYarrington().build();
		verify(clientBase).addClient(refEq(expected));
		assertThat(client).isEqualToComparingFieldByField(expected);
	}
}
