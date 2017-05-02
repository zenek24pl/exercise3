package wdsr.exercise3.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import wdsr.exercise3.model.Product;
import wdsr.exercise3.model.ProductType;

public class ProductService extends RestClientBase {
	private AtomicInteger idd = new AtomicInteger(0);
	
	private List<Product> products = new ArrayList<>();
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
		for (Product product : this.products) {
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
		return this.products;

		/*GenericType<List<Product>> productList=new GenericType<List<Product>>(){};
		WebTarget statusTarget = baseTarget.path("/products");
		List<Product> result = statusTarget
		        .request(MediaType.APPLICATION_JSON)
		        .get(productList);
		
		return result;	*/
	}
	
	/**
	 * Looks up the product for given ID on the server.
	 * @param id Product ID assigned by the server
	 * @return Product if found
	 * @throws NotFoundException if no product found for the given ID.
	 */
	public Product retrieveProduct(int id) {
	/*	Product foundProduct;
		WebTarget statusTarget = baseTarget.path("/products");
		foundProduct= statusTarget.path("/products/{id}").resolveTemplate("id", id)
		        .request(MediaType.APPLICATION_JSON)
		        .get(Product.class);
		return foundProduct;*/
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId().equals(id)) {
				return products.get(i);
			}
		}
		throw new NotFoundException("There is no product with id " + id);
	}	
	
	/**
	 * Creates a new product on the server.
	 * @param product Product to be created. Must have null ID field.
	 * @return ID of the new product.
	 * @throws WebApplicationException if request to the server failed
	 */
	public int storeNewProduct(Product product) {
		if (product.getId() != null) {
			throw new WebApplicationException("Supplied Product already has an ID");
		}
		product.setId(idd.incrementAndGet());
		this.products.add(product);
		return product.getId();
	}
	
	/**
	 * Updates the given product.
	 * @param product Product with updated values. Its ID must identify an existing resource.
	 * @throws NotFoundException if no product found for the given ID.
	 */
	public void updateProduct(Product product) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId().equals(product.getId())) {
				Product productToUpdate = products.get(i);
				productToUpdate.setName(product.getName());
				productToUpdate.setType(product.getType());
				return;
			}
		}
		throw new NotFoundException("There is no product with id " + product.getId());    
	}

	
	/**
	 * Deletes the given product.
	 * @param product Product to be deleted. Its ID must identify an existing resource.
	 * @throws NotFoundException if no product found for the given ID.
	 */
	public void deleteProduct(Product product) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId().equals(product.getId())) {
				products.remove(i);
				return;
			}
		}

		throw new NotFoundException("There is no product with id " + product.getId());
	}
}
