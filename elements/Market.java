package elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * the only market of the system
 * in which we keep the selling orders, buying orders, transactions and the size of the market
 * @author zeynp
 *
 */
public class Market {
	
	private int fee;
	private PriorityQueue<SellingOrder> sellingOrders;
	private PriorityQueue<BuyingOrder> buyingOrders;
	private ArrayList<Transaction> transactions;
	private ArrayList<Trader> traders;
	
	/**
	 * constructor of market
	 * @param fee the commission that market receives from every transaction per thousand
	 */
	public Market(int fee) {
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.transactions = new ArrayList<Transaction>();
		this.traders = new ArrayList<Trader>();
		this.fee = fee;
	}
	
	/**
	 * creates a selling order and adds it to the priority queue of selling orders
	 * while blocking the order's amount of coins in the trader's wallet
	 * and increasing the amount of coins in the market
	 * @param order selling order
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
		traders.get(order.traderID).getWallet().blockCoins(order.amount);
	}
	
	/**
	 * creates a buying order and adds it to the priority queue of buying orders
	 * while blocking the necessary amount of dollars for the order in the trader's wallet
	 * and increasing the amount of dollars in the market
	 * @param order buying order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		if(traders.get(order.traderID).hasEnoughDollars(order.amount*order.price)) {
			buyingOrders.add(order);
			traders.get(order.traderID).getWallet().blockDollars(order.amount*order.price);
		}
	}
	
	/**
	 * creates market orders and makes transactions until the wanted price is reached
	 * @param price the price that market wants to reach
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		
		if(buyingOrders.peek().price>=price) {
			
			while(buyingOrders.peek().price>=price) {
				sellingOrders.add(new SellingOrder(0, buyingOrders.peek().amount, buyingOrders.peek().price));
				this.checkTransactions(traders);
			}
		}
		
		else if(sellingOrders.peek().price<=price) {
			
			while(sellingOrders.peek().price<=price) {
				buyingOrders.add(new BuyingOrder(0, sellingOrders.peek().amount, sellingOrders.peek().price));
				this.checkTransactions(traders);
			}
		}	
	}

	/**
	 * checks if there is any possible transaction and if yes, makes the transaction
	 * decreases the amount of coins and dollars in the market
	 * @param traders array list of all the traders in the market
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		BuyingOrder b;
		SellingOrder s;
		if(!this.buyingOrders.isEmpty()&&!this.sellingOrders.isEmpty()) {
			
			while(this.buyingOrders.peek().price>=this.sellingOrders.peek().price) {
				
				b = buyingOrders.poll();
				s = sellingOrders.poll();
				
				transactions.add(new Transaction(s, b));
				
				Trader buyingTrader = traders.get(b.traderID);
				Trader sellingTrader = traders.get(s.traderID);
				
				double transactionAmount;
				if(s.amount > b.amount) {
					transactionAmount = b.amount;
					s.amount -= b.amount;
					sellingOrders.add(s);
				} else if(s.amount < b.amount) {
					transactionAmount = s.amount;
					b.amount -= s.amount;
					buyingOrders.add(b);
				} else
					transactionAmount = s.amount;
				
				double transactionPrice = s.price;
				
				double extraDollars;
				extraDollars = (b.price-transactionPrice)*transactionAmount;
				
				buyingTrader.buy(transactionAmount, transactionPrice, this);
				buyingTrader.getWallet().addDollarsToWallet(extraDollars);
				
				sellingTrader.sell(transactionAmount, transactionPrice, this);

			}
		}
	}
	
	/**
	 * determines the current buying price in the market
	 * @return current buying price
	 */
	public double CPBuying() {
		if(this.getSellingOrders().isEmpty())
			return 0;
		return this.getSellingOrders().peek().price;
	}
	
	/**
	 * determines the current selling price in the market
	 * @return current selling price
	 */
	public double CPSelling() {
		if(this.getBuyingOrders().isEmpty())
			return 0;
		return this.getBuyingOrders().peek().price;
	}
	
	/**
	 * determines the current average price in the market
	 * @return current average price
	 */
	public double CPAverage() {
		if(this.CPBuying()==0)
			return this.CPSelling();
		else if(this.CPSelling()==0)
			return this.CPBuying();
		else
			return (this.CPBuying()+this.CPSelling())/2;
	}
	
	public int getNumOfSuccessfulTransactions() {
		return transactions.size();
	}
	
	/**
	 * determines the total dollars in the market
	 * @return dollars
	 */
	public double getDollarsInMarket() {
		Iterator <BuyingOrder> itr = buyingOrders.iterator();
		double dollars = 0;
		while(itr.hasNext()) {
			BuyingOrder b = itr.next();
			dollars += (b.amount*b.price);
		}
		return dollars;
	}

	/**
	 * determines the total amount of coins in the market
	 * @return amount of coins
	 */
	public double getCoinsInMarket() {
		Iterator <SellingOrder> itr = sellingOrders.iterator();
		double coins = 0;
		while(itr.hasNext()) {
			coins += itr.next().amount;
		}
		return coins;
	}
	
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	
	public ArrayList<Trader> getTraders() {
		return traders;
	}

	public int getFee() {
		return fee;
	}
	
}
