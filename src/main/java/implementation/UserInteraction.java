package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import interfaces.DBAcessor;
import interfaces.ProductStatus;


public class UserInteraction {
	DBAcessor dataAcessor;
	BufferedReader bReader;
	
	private UserInteraction() {
		this.bReader = new BufferedReader(new java.io.InputStreamReader(System.in));
	};
	
	public UserInteraction(DBAcessor dependency) {
		this();
		this.dataAcessor = dependency;
	};
	
	public void printMainMenu() {
		try {
			while(true) {
				System.out.println("Please select option:\r\n"
						+ "\n"
						+ "1 - See database views\r\n"
						+ "2 - Create product\r\n"
						+ "3 - Create order\r\n"
						+ "4 - Update entry quantities in order\r\n"
						+ "5 - Remove product by id\r\n"
						+ "6 - Remove all produsts from database\r\n"
						+ "7 - exit");
				System.out.print("Type your choice: ");
				
				int selector;
				try {
					selector = Integer.parseInt(bReader.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid input, try again");
					continue;
				}
				
				
				switch (selector) {
				case 1: {
					printViewsMenu();
					break;
				}
				case 2: {
					printCreateProductMenu();
					break;	
				}
				case 3: {
					printCreateOrderMenu();
					break;	
				}
				case 4: {
					printUpdateOrderQuantitiesMenu();
					break;
				}
				case 5: {
					printRemoveByIdMenu();
					break;	
				}
				case 6: {
					printRemoveAllProductsMenu();
					break;
				}
				case 7: {
					return;
				}
				
				default:
					System.out.println("Invalid input, please try again");
				}
				
			}
		} catch (IOException e) {
			System.out.println("Something went wrong while readung your input: "+ e.getMessage());
		}
	}
	
	private void printViewsMenu() throws NumberFormatException, IOException {
		while(true) {
			System.out.println("Please choose view:\r\n"
					+ "\n"
					+ "1 - Products list\r\n"
					+ "2 - Ordered products quantities\r\n"
					+ "3 - List all orders with detailed information\r\n"
					+ "4 - exit to main menu");
			System.out.print("Type your choice: ");
			
			int selector = Integer.parseInt(bReader.readLine());
			
			switch (selector) {
			case 1: { 
				printTable(this.dataAcessor.getProductsView());
				break;
			}
			case 2: { 
				printTable(this.dataAcessor.getOrderedProductsView());
				break;
			}
			case 3: { 
				printTable(this.dataAcessor.getOrdersView());
				break;
			}
			case 4: { return;
			}
			default:
				System.out.println("Wrong input, please try again");
			}
		}
		
	}
	
	private void printCreateProductMenu() throws NumberFormatException, IOException {
		String name;
		int price;
		ProductStatus status;
		while(true) {
			System.out.print("Enter product name: ");
			name = bReader.readLine();
			
			System.out.print("Enter product price: ");
			price = Integer.parseInt(bReader.readLine());
			
			System.out.print("Choose product status:\r\n "
					  	   + "1 - In stock\r\n"
					  	   + "2 - Running low\r\n"
					  	   + "3 - Out of stock\r\n");
			int selector = Integer.parseInt(bReader.readLine());
			switch (selector) {
			case 1: {
				status = ProductStatus.IN_STOCK;
				break;
			}
			case 2: { 
				status = ProductStatus.RUNNING_LOW;
				break;
			}
			case 3: { 
				status = ProductStatus.OUT_OF_STOCK;
				break;
			}
			default:
				System.out.println("Invalid input. Please try again");
				continue;
			}
			
			dataAcessor.createProduct(name, price, status);
			
			System.out.println("Create another product? (y/n)");
			if(bReader.readLine().toLowerCase().equals("y")) continue;
			else break;
		}
		
	}
	
	private void printCreateOrderMenu() throws IOException {
		
		LinkedList<Integer> product_ids = new LinkedList<>();
		
		printTable(dataAcessor.getProductsView());
		while(true) {
			System.out.println("Select product id from the list above or type \"exit\" to cancel");
			
			String input = bReader.readLine();
			if(input.toLowerCase().equals("exit")) return;
			try {
				product_ids.add(Integer.parseInt(input));
			}catch (NumberFormatException e) {
				System.out.println("Invalid input, try again");
				continue;
			}
			System.out.println("Do you want to add one more product to your order? (y/n)");
			String answer = bReader.readLine();
			if(answer.toLowerCase().equals("y")) continue;
			else break;
		}
		int[] args = new int[product_ids.size()];
		for(int i = 0; i < product_ids.size(); i++) {args[i] = product_ids.get(i);};
		dataAcessor.createOrder(args);
		
	}
	
	private void printUpdateOrderQuantitiesMenu() throws IOException {		
		while(true) {
			int order_id;
			Map<Integer, Integer> input_pairs = new LinkedHashMap<>();//key is product ID, value is quantity
			printTable(dataAcessor.getOrdersView());
			System.out.print("Type order id: ");
			
			try {
				order_id = Integer.parseInt(bReader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, try again");
				continue;
			}
			
			while(true) {
				int product_id, quantity;
				
				System.out.print("Type product id: ");
				try {
					product_id = Integer.parseInt(bReader.readLine());}
				catch (NumberFormatException e) {
					System.out.println("Invalid input, try again");
					continue;
				}
				
				System.out.print("Type quantity for selected product: ");
				try {
					quantity = Integer.parseInt(bReader.readLine());}
				catch (NumberFormatException e) {
					System.out.println("Invalid input, try again");
					continue;
				}
				
				input_pairs.put(product_id, quantity);
				
				System.out.print("Do you want to change quantities for other products? (y/n) ");
				if(!bReader.readLine().toLowerCase().equals("y")) break;
				
			}
			
			dataAcessor.updateOrderQuantities(order_id, input_pairs);
			
			System.out.print("Do you want to change quantities for other orders? (y/n) ");
			if(!bReader.readLine().toLowerCase().equals("y")) break;
			
		}
		
		
	}
	
	private void printRemoveByIdMenu() throws IOException {
		
//		if(dataAcessor.getOrdersView().size() == 1) {
//			System.out.println("No products in database is present yet\n"
//							 + "Please insert data first");
//			System.out.println("Back to main menu...");
//			return;
//		}

		printTable(dataAcessor.getProductsView());
		while(true) {
			System.out.println("Select product id from the list above or type \"exit\" to cancel");
			
			String select = bReader.readLine();
			if(select.toLowerCase().equals("exit'")) return;
			
			int remove_id; 
			try {
				remove_id = Integer.parseInt(select);
			}catch (NumberFormatException e) {
				System.out.println("Invalid input, try again");
				continue;
			}
			
			System.out.print("Please enter your password: ");
			String passw = bReader.readLine();
			
			dataAcessor.removeProductById(remove_id, passw);
			
			System.out.println("Do you want to remove more products? (y/n)");
			if(bReader.readLine().toLowerCase().equals("y")) continue;
			else break;
			
		}
		
	}
	
	private void printRemoveAllProductsMenu() throws IOException{
		printTable(dataAcessor.getProductsView());
		System.out.println("Enter your password to delete all products or type \"exit\" to cancel");
		String input = bReader.readLine();
		
		if(input.toLowerCase().equals("exit")) return;
		
		dataAcessor.removeAllProducts(input);
	}
	
	private void printTable(ArrayList<String[]> args) {
		if(args.size() == 1)//row with column names is always present 
		{
			System.out.println("No entries in database yet. Please insert data ");
			return;
		}
		
		int colwidth = 25;
		int width = args.get(0).length;
		
		for(int k = 0; k < (width * colwidth + width +1); k++) {System.out.printf("%c", '-');}
		System.out.println();
		
		Iterator<String[]> it = args.iterator();
		String[] row;
		int i = 0;
		while(it.hasNext()) 
		{	
			if(i == 1) {
				for(int k = 0; k < (width * colwidth + width +1); k++) {System.out.printf("%c", '-');}
				System.out.println();
			}
			
			System.out.printf("%c", 124);
			row = it.next();
			
			for(int j = 0; j < width; j++) 
			{		
				System.out.printf("%" + colwidth + "s",row[j]);
				System.out.printf("%c", 124);
			}
			
			System.out.printf("%n");
			
			
			i++;
		}
		for(int k = 0; k < (width * colwidth + width +1); k++) {System.out.printf("%c", '-');}
		System.out.println();
		
		
	}

}
