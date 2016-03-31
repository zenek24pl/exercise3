package wdsr.exercise3.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public abstract class RestClientBase {
	protected final String serverHost;
	protected final int serverPort;
	protected Client client;
	protected WebTarget baseTarget;
	
	protected RestClientBase(final String serverHost, final int serverPort, final Client client) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.client = client;
		this.baseTarget = client.target("http://"+serverHost+":"+serverPort);
	}
}
