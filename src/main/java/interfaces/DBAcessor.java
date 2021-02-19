package interfaces;

import java.util.ArrayList;
import java.util.Map;

public interface DBAcessor {
	public void createProduct(String name, int price, ProductStatus status);
	
	public void createOrder(int ... product_ids);
	//accepts product_id - quantity pairs (key - product_id, value - quantity)
	public void updateOrderQuantities(int order_id, Map<Integer, Integer> pairs);
	
	public void removeProductById(int id, String password);
	
	public void removeAllProducts(String password);
	
	public ArrayList<String[]> getProductsView();
	
	public ArrayList<String[]> getOrderedProductsView();
	
	public ArrayList<String[]> getOrdersView();
}
