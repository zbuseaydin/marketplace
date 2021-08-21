package elements;

/**
 * the class that is used for keeping track of the successful transactions
 * @author zeynp
 *
 */
public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	
	/**
	 * constructor of transaction
	 * @param sellingOrder the selling order of the successful transaction
	 * @param buyingOrder the buying order of the successful transaction
	 */
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.buyingOrder = buyingOrder;
		this.sellingOrder = sellingOrder;
	}
}