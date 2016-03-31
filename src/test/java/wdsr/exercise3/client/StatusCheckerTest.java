package wdsr.exercise3.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.junit.Test;

import wdsr.exercise3.server.StatusResource;

public class StatusCheckerTest extends ClientTestBase {
	@Test
	public void shouldReturnTrueIfStatusReturnsOK() throws InterruptedException {
		// given
		Application statusCheckApp = new StatusCheckApplication("OK", 200);
		server.deploy(statusCheckApp);
		StatusChecker statusChecker = new StatusChecker(ClientTestBase.SERVER_HOST, ClientTestBase.SERVER_PORT, client);

		// when
		boolean result = statusChecker.isServerOk();

		// then
		assertTrue(result);
	}
	
	@Test
	public void shouldReturnFalseIfStatusDoesNotReturnOK() throws InterruptedException {
		// given
		Application statusCheckApp = new StatusCheckApplication("FAILURE", 200);
		server.deploy(statusCheckApp);
		StatusChecker statusChecker = new StatusChecker(ClientTestBase.SERVER_HOST, ClientTestBase.SERVER_PORT, client);

		// when
		boolean result = statusChecker.isServerOk();

		// then
		assertFalse(result);
	}
	
	@Test
	public void shouldReturnFalseIfStatusRequestFails() throws InterruptedException {
		// given
		Application statusCheckApp = new StatusCheckApplication("FAILURE", 500);
		server.deploy(statusCheckApp);
		StatusChecker statusChecker = new StatusChecker(ClientTestBase.SERVER_HOST, ClientTestBase.SERVER_PORT, client);

		// when
		boolean result = statusChecker.isServerOk();

		// then
		assertFalse(result);
	}
	
	public class StatusCheckApplication extends BaseServerApplication {
		private final String status;
		private final int code;

		public StatusCheckApplication(final String status, final int code) {
			this.status = status;
			this.code = code;
		}

		@Override
		public Set<Class<?>> getClasses() {
			return Collections.singleton(StatusResource.class);
		}
		
		@Override
		public int getStatusCode() {
			return code;
		}

		@Override
		public String getStatus() {
			return status;
		}		
	}
}
