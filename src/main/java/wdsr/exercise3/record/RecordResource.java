package wdsr.exercise3.record;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * 
	 * Media type application/xml will be used for all requests/responses.
	 */
}
