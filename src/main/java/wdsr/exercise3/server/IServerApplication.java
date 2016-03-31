package wdsr.exercise3.server;

import java.util.Collection;
import java.util.Set;

import wdsr.exercise3.model.Product;
import wdsr.exercise3.model.ProductType;

public interface IServerApplication {
	int getStatusCode();
	String getStatus();
	Collection<Product> getAllProducts();
	Collection<Product> getProducts(Set<ProductType> types);
	int addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(int id);
	Product getProduct(int id);
}
