package wdsr.exercise3.record;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wdsr.exercise3.model.Record;

@Path("/records")
public class RecordResource {
	private static final Logger log = LoggerFactory.getLogger(RecordResource.class);
	
	@Inject
	private RecordInventory recordInventory;
	
	/**
	 * TODO Add methods to this class that will implement the REST API described in the slidedeck.
	 * Sample API invocations that must be supported:
	 * 
	 * * GET https://localhost:8090/records
	 * ** Returns all records 
	 * 
	 * * POST https://localhost:8090/records
	 * ** Creates a new record, returns ID of the new record
	 * 
	 * * GET https://localhost:8090/records/{id}
	 * ** Returns an existing record
	 * 
	 * * PUT https://localhost:8090/records/{id}
	 * ** Replaces an existing record
	 * 
	 * * DELETE https://localhost:8090/records/{id}
	 * ** Deletes an existing record
	 */
}
