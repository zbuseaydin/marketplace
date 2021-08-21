package elements;

/**
 * the child class of Order, implements Comparable
 * @author zeynp
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{

	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	@Override
	/**
	 * determines the priority of the selling orders
	 * gives priority to the order with the lowest price per coin
	 * if the prices are the same, gives priority to the one with the highest amount of coins
	 * if both the prices and the amounts is the same, gives priority to the one with the lowest tradersID
	 */
	public int compareTo(SellingOrder e) {
		
		if(e.price!=this.price) {
			double i = this.price-e.price;
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
