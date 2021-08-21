package elements;

/**
 * the class that is used for keeping track of the amount of dollars and coins that a trader has and making operations on them
 * @author zeynp
 *
 */
public class Wallet {
	
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	
	/**
	 * constructor of wallet
	 * @param dollars initial dollars that the wallet has
	 * @param coins initial coins that the wallet has
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}
	
	/**
	 * increases the amount of dollars in the wallet
	 * @param amount of dollars that will be added
	 */
	public void addDollarsToWallet(double amount) {
		this.dollars += amount;
	}
	
	/**
	 * increases the amount of coins in the wallet
	 * @param amount of coins that will be added
	 */
	public void addCoinsToWallet(double amount) {
		this.coins += amount;
	}
	
	/**
	 * decreases the amount of dollars in the wallet
	 * @param amount of dollars that will be withdrawn
	 */
	public void withdrawDollarsFromWallet(double amount) {
		this.dollars -= amount;			
	}
	
	/**
	 * decreases the amount of coins in the wallet
	 * @param amount of coins that will be withdrawn
	 */
	public void withdrawCoinsFromWallet(double amount) {
		this.coins -= amount;
	}
	
	/**
	 * makes the amount of total dollars in the wallet unusable when a buying order is given 
	 * @param amount dollars
	 */
	public void blockDollars(double amount) {
		this.dollars -= amount;
		this.blockedDollars += amount;
	}
	
	/**
	 * makes the amount of coins in the wallet unusable when a selling order is given
	 * @param amount coins
	 */
	public void blockCoins(double amount) {
		this.coins -= amount;
		this.blockedCoins += amount;
	}
	
	/**
	 * makes the amount of dollars in the order usable when a transaction takes place
	 * @param amount dollars
	 */
	public void unblockDollars(double amount) {
		this.dollars += amount;
		this.blockedDollars -= amount;
	}
	
	/**
	 * makes the amount of coins in the order usable when a transaction takes place
	 * @param amount coins
	 */
	public void unblockCoins(double amount) {
		this.coins += amount;
		this.blockedCoins -= amount;
	}
	
	/**
	 * calculates the total amount of dollars in the wallet (both the blocked and non-blocked ones)
	 * @return total amount of dollars
	 */
	public double totalDollars() {
		return dollars + blockedDollars;
	}
	
	/**
	 * calculates the total amount of coins in the wallet (both the blocked and non-blocked ones)
	 * @return total amount of coins
	 */
	public double totalCoins() {
		return coins + blockedCoins;
	}

	public double getDollars() {
		return dollars;
	}

	public double getCoins() {
		return coins;
	}
}
