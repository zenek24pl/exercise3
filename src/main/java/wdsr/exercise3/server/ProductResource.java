package wdsr.exercise3.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import wdsr.exercise3.model.Product;
import wdsr.exercise3.model.ProductType;

@Path("/products")
public class ProductResource {
	public static final String PRODUCT_TYPE_PARAM_NAME = "type";
	
	@Context
	private Application application;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@QueryParam(PRODUCT_TYPE_PARAM_NAME) Set<ProductType> types) {
		IServerApplication productApplication = (IServerApplication)application;
		List<Product> result = new ArrayList<>();
		
		if (types.isEmpty()) {
			result.addAll(productApplication.getAllProducts());
		} else {
			result.addAll(productApplication.getProducts(types));
		}
		
		GenericEntity<List<Product>> genericResult = new GenericEntity<List<Product>>(result) {};
		return Response.ok(genericResult).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam(value = "id") int id) {
		IServerApplication productApplication = (IServerApplication)application;
		Product product = productApplication.getProduct(id);
		return product;
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(Product product, @Context UriInfo uriInfo) {
		IServerApplication productApplication = (IServerApplication)application;
		try {
			productApplication.addProduct(product);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(product.getId()));
        
		return Response.created(builder.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product, @PathParam(value = "id") int id) {
		if (product.getId() != null && id != product.getId()) {
			return Response.status(Status.BAD_REQUEST).entity("Product ID is different in request path and message body").build();
		}
		IServerApplication productApplication = (IServerApplication)application;
		product.setId(id);
		productApplication.updateProduct(product);
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProduct(@PathParam(value = "id") int id) {
		IServerApplication productApplication = (IServerApplication)application;
		productApplication.deleteProduct(id);
	}	
}
