package elements;

/**
 * the child class of Order, implements Comparable
 * @author zeynp
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder>{

	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	@Override
	/**
	 * determines the priority of the buying orders
	 * gives priority to the order with the highest price per coin
	 * if the prices are the same, gives priority to the one with the highest amount of coins
	 * if both the prices and the amounts is the same, gives priority to the one with the lowest tradersID
	 */
	public int compareTo(BuyingOrder e) {
		
		if(e.price!=this.price) {
			double i = e.price-this.price;
			if(i<0)
				return -1;
			return 1;
		}
		
		if(e.amount!=this.amount) {
			double j = e.amount-this.amount;
			if(j<0)
				return -1;
			return 1;
		}
		return (this.traderID-e.traderID);
	}
}
