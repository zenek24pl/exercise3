package wdsr.exercise3.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import wdsr.exercise3.model.Product;
import wdsr.exercise3.model.ProductType;

public class ProductService extends RestClientBase {

	protected ProductService(final String serverHost, final int serverPort, final Client client) {
		super(serverHost, serverPort, client);
	}
	
	/**
	 * Looks up all products of given types known to the server.
	 * @param types Set of types to be looked up
	 * @return A list of found products - possibly empty, never null.
	 */
	public List<Product> retrieveProducts(Set<ProductType> types) {
		List<Product> products = new ArrayList<>();
		GenericType<List<Product>> productList=new GenericType<List<Product>>(){};
		List<Product> serverListResponse=new ArrayList<>();
		
		WebTarget webTarget = baseTarget.path("/products");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
		serverListResponse=response.readEntity(productList);
        if(response.getStatus() !=Response.Status.OK.getStatusCode()){
        	return products;
        }
		for (Product product : serverListResponse) {
			for (ProductType current : types) {
				if (current.equals(product.getType()))
					products.add(product);
			}
		}
		return products;
		
	}
	
	/**
	 * Looks up all products known to the server.
	 * @return A list of all products - possibly empty, never null.
	 */
	public List<Product> retrieveAllProducts() {
		WebTarget statusTarget = baseTarget.path("/products");
		GenericType<List<Product>> productList=new GenericType<List<Product>>(){};

		List<Product> result = statusTarget
		        .request(MediaType.APPLICATION_JSON)
		        .get(productList);
		
		return result;	
	}
	
	/**
	 * Looks up the product for given ID on the server.
	 * @param id Product ID assigned by the server
	 * @return Product if found
	 * @throws NotFoundException if no product found for the given ID.
	 */
	public Product retrieveProduct(int id) {
		List<Product> foundProduct=new ArrayList<>();
		GenericType<List<Product>> product=new GenericType<List<Product>>(){};

		WebTarget statusTarget = baseTarget.path("/products").queryParam("id", id);
		
		Response response=statusTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
		foundProduct=response.readEntity(product);
		
		if(foundProduct.size()==0 ){
			throw new NotFoundException("Product with given Id  not found");
		}
		
		return foundProduct.get(0);	
	}	
	
	/**
	 * Creates a new product on the server.
	 * @param product Product to be created. Must have null ID field.
	 * @return ID of the new product.
	 * @throws WebApplicationException if request to the server failed
	 */
	public int storeNewProduct(Product product) {
		
		WebTarget statusTarget = baseTarget.path("/products");
		Response response= statusTarget.request().post(Entity.entity(product, MediaType.APPLICATION_JSON));
		        
		if(response.getStatus()==Response.Status.BAD_REQUEST.getStatusCode()) {
			throw new WebApplicationException("Request to server failed");
		}
		return 0;
	
	}
	
	/**
	 * Updates the given product.
	 * @param product Product with updated values. Its ID must identify an existing resource.
	 * @throws NotFoundException if no product found for the given ID.
	 */
	public void updateProduct(Product product) {
		 	WebTarget statusTarget = baseTarget.path("/products/" + product.getId());
	        Response response = statusTarget.request().put(Entity.entity(product, MediaType.APPLICATION_JSON));

	        if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
	            throw new NotFoundException("Product with given Id not found");
	        }
	}

	
	/**
	 * Deletes the given product.
	 * @param product Product to be deleted. Its ID must identify an existing resource.
	 * @throws NotFoundException if no product found for the given ID.
	 */
	public void deleteProduct(Product product) {
		 	WebTarget statusTarget = baseTarget.path("/products/" + product.getId());
	        Response response = statusTarget.request().delete();
	        if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
	            throw new NotFoundException("Product with given Id not found");
	        }
	}
}
