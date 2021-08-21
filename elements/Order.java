package elements;

/**
 * the parent class of BuyingOrder and SellingOrder
 * @author zeynp
 *
 */
public class Order{
	
	int traderID;
	double amount;
	double price;
	
	/**
	 * the constructor of the order
	 * @param traderID ID of the trader of the order
	 * @param amount the amount of coins of the order
	 * @param price the amount of price per coins of the order
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
}
