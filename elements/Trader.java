package elements;

/**
 * the class that has objects which can do buy and sell operations
 * @author zeynp
 *
 */
public class Trader {
	private int id;
	private Wallet wallet;
	public static int numberOfUsers = 0;

	/**
	 * constructor of trader
	 * @param dollars initial amount of dollars that the trader has in wallet
	 * @param coins initial amount of coins that the trader has in wallet
	 */
	public Trader(double dollars, double coins) {
		this.wallet = new Wallet(dollars, coins);
		numberOfUsers ++;
		this.id = numberOfUsers;
	}
	
	/**
	 * makes selling operation in the transaction
	 * unblocks coins for the transaction
	 * add the dollars that trader gets from the selling to the wallet
	 * decreases the amount of coins that the trader has
	 * @param amount coins that the trader is selling
	 * @param price price per coin of the selling operation
	 * @param market in which selling occurs
	 * @return 0
	 */
	public int sell(double amount, double price, Market market) {
		if(this.id==0)
			return 0;
		this.wallet.unblockCoins(amount);
		this.wallet.addDollarsToWallet(amount*price*(1-market.getFee()/1000.00000));
		this.wallet.withdrawCoinsFromWallet(amount);
		return 0;
	}
	
	/**
	 * makes buying operation in the transaction
	 * unblocks dollars for the transaction
	 * increases the amount of coins that the trader has
	 * add the coins that trader gets from the buying to the wallet
	 * @param amount coins that the trader is selling
	 * @param price price per coin of the selling operation
	 * @param market in which selling occurs
	 * @return 0
	 */
	public int buy(double amount, double price, Market market) {
		if(this.id==0)
			return 0;
		this.wallet.unblockDollars(amount*price);
		this.wallet.addCoinsToWallet(amount);
		this.wallet.withdrawDollarsFromWallet(amount*price);
		return 0;
	}

	/**
	 * checks if the trader has enough amount of dollars for an operation
	 * @param amount of dollars
	 * @return true if trader has enough dollars, false if not
	 */
	public boolean hasEnoughDollars(double amount) {
		if(amount<=this.wallet.getDollars()||this.id==0)
			return true;
		return false;
	}
	
	/**
	 * checks if the trader has enough amount of coins for an operation
	 * @param amount of coins
	 * @return true if trader has enough coins, false if not
	 */
	public boolean hasEnoughCoins(double amount) {
		if(amount<=this.wallet.getCoins()||this.id==0)
			return true;
		return false;
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	public int getId() {
		return id;
	}
	
}