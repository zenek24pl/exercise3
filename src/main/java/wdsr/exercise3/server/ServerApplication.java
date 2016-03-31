package wdsr.exercise3.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Application;

import wdsr.exercise3.model.Product;
import wdsr.exercise3.model.ProductType;
import wdsr.exercise3.server.ExceptionMappers.JsonMappingExceptionMapper;
import wdsr.exercise3.server.ExceptionMappers.NotFoundExceptionMapper;

/**
 * This class is not thread-safe!
 */
public class ServerApplication extends Application implements IServerApplication {
	private AtomicInteger counter = new AtomicInteger(0);
	private Map<Integer, Product> products = new HashMap<>();
	
	public ServerApplication() {
		// empty
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(StatusResource.class);
		classes.add(ProductResource.class);
		classes.add(JsonMappingExceptionMapper.class);
		classes.add(NotFoundExceptionMapper.class);
		return classes;
	}

	@Override
	public int getStatusCode() {
		return 200;
	}

	@Override
	public String getStatus() {
		return "OK";
	}

	@Override
	public Collection<Product> getAllProducts() {
		return products.values();
	}

	@Override
	public Collection<Product> getProducts(Set<ProductType> types) {
		return products.values().stream().filter(p -> types.contains(p.getType())).collect(Collectors.toList());
	}

	@Override
	public Product getProduct(int id) {
		if (! products.containsKey(id)) {
			throw new NotFoundException("Product with ID="+id+" does not exist");
		}
		return products.get(id);
	}	
	
	public int addProduct(Product product) {
		if (product.getId() != null) {
			throw new IllegalArgumentException("Supplied Product already has an ID");
		}
		product.setId(counter.incrementAndGet());
		products.put(product.getId(), product);
		return product.getId();
	}
	
	public void updateProduct(Product product) {
		if (! products.containsKey(product.getId())) {
			throw new NotFoundException("Product with ID="+product.getId()+" does not exist");
		}
		Product current = products.get(product.getId());
		current.setName(product.getName());
		current.setType(product.getType());
	}
	
	public void deleteProduct(int id) {
		if (! products.containsKey(id)) {
			throw new NotFoundException("Product with ID="+id+" does not exist");
		}
		products.remove(id);
	}
}
