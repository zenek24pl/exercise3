package wdsr.exercise3.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class StatusResource {
	@Context
	private Application application;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getStatus() {
		IServerApplication statusCheckApp = (IServerApplication) application;
		if (statusCheckApp.getStatusCode() >= 500) {
			throw new WebApplicationException(statusCheckApp.getStatusCode());
		}
		return statusCheckApp.getStatus();
	}
}
