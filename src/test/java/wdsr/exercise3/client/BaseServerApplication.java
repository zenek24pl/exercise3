package wdsr.exercise3.client;

import java.util.Collection;
import java.util.Set;

import javax.ws.rs.core.Application;

import wdsr.exercise3.model.Product;
import wdsr.exercise3.model.ProductType;
import wdsr.exercise3.server.IServerApplication;

public class BaseServerApplication extends Application implements IServerApplication {
	@Override
	public int getStatusCode() {
		return 0;
	}

	@Override
	public String getStatus() {
		return null;
	}

	@Override
	public Collection<Product> getAllProducts() {
		return null;
	}

	@Override
	public Collection<Product> getProducts(Set<ProductType> types) {
		return null;
	}

	@Override
	public int addProduct(Product product) {
		return 0;
	}

	@Override
	public void updateProduct(Product product) {
		// empty
	}

	@Override
	public void deleteProduct(int id) {
		// empty
	}

	@Override
	public Product getProduct(int id) {
		return null;
	}
}
