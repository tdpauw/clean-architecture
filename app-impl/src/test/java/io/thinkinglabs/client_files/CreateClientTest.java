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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateClientTest {

	private final ClientBase clientBase = mock(ClientBase.class);
	private final UUIDGenerator uuidGenerator = mock(UUIDGenerator.class);
	private final CreateClient sut = new DefaultCreateClient(clientBase, uuidGenerator);

	@Test
	public void createClient() {
		final String clientId = "4f3d7571-d6d3-44b7-8087-011ad5635a17";
		when(uuidGenerator.next()).thenReturn(clientId);
		Client client = sut.createClient(new CreateClientCommand("Scott", "Yarrington"));

		Client expected = scottYarrington().withClientId(clientId).build();
		verify(clientBase).addClient(refEq(expected));
		assertThat(client).isEqualToComparingFieldByField(expected);
	}
}
