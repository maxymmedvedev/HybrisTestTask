package interfaces;

import lombok.Getter;

public enum ProductStatus {
	OUT_OF_STOCK("out_of_stock", 1), IN_STOCK("in_stock", 2), RUNNING_LOW("running_low", 3);
	//string and index representation for MySQL
	@Getter private String string;
	@Getter private int index;
	
	private ProductStatus(String str, int ind) {
		this.index = ind;
		this.string = str;
	}
	
}
