package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import elements.*;

/**
 * runnable class of the marketplace simulation
 * @author zeynp
 *
 */
public class Main{
	public static Random myRandom;
	
	/**
	 * reads the input file, makes the necessary operations and prints on the output file
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		int numOfInvalidQueries = 0;
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		int A = in.nextInt();
		myRandom = new Random(A);
		
		int B = in.nextInt();
		Market myMarket = new Market(B);
		
		int C = in.nextInt();
		int D = in.nextInt();

		for(int i=0; i<C; i++) {
			double dollars = in.nextDouble();
			double coins = in.nextDouble();
			Trader t = new Trader(dollars, coins);
			myMarket.getTraders().add(t);
		}
		
		for(int i=0; i<D; i++) {
			int query = in.nextInt();
			
			if(query==10) {
				int ID = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				if(myMarket.getTraders().get(ID).hasEnoughDollars(price*amount))
					myMarket.giveBuyOrder(new BuyingOrder(ID, amount, price));
				else
					numOfInvalidQueries++;
				
			}else if(query==11) {
				int ID11 = in.nextInt();
				double amount11 = in.nextDouble();
				if(myMarket.getSellingOrders().isEmpty())
					numOfInvalidQueries++;
				else {
					if(myMarket.getTraders().get(ID11).hasEnoughDollars(amount11*myMarket.CPBuying()))
						myMarket.giveBuyOrder(new BuyingOrder(ID11, amount11, myMarket.CPBuying()));
					else
						numOfInvalidQueries++;
				}
				
			}else if(query==20) {
				int ID2 = in.nextInt();
				double price2 = in.nextDouble();
				double amount2 = in.nextDouble();
				if(myMarket.getTraders().get(ID2).hasEnoughCoins(amount2))
					myMarket.giveSellOrder(new SellingOrder(ID2, amount2, price2));
				else
					numOfInvalidQueries++;
				
			}else if(query==21) {
				int ID21 = in.nextInt();
				double amount21 = in.nextDouble();
				if(myMarket.getBuyingOrders().isEmpty())
					numOfInvalidQueries++;
				else {
					if(myMarket.getTraders().get(ID21).hasEnoughCoins(amount21))
						myMarket.giveSellOrder(new SellingOrder(ID21, amount21, myMarket.CPSelling()));
					else
						numOfInvalidQueries++;
				}
				
			}else if(query==3) {
				int ID3 = in.nextInt();
				double amount3 = in.nextDouble();
				myMarket.getTraders().get(ID3).getWallet().addDollarsToWallet(amount3);
				
			}else if(query==4) {
				int ID4 = in.nextInt();
				double amount4 = in.nextDouble();
				if(myMarket.getTraders().get(ID4).hasEnoughDollars(amount4))
					myMarket.getTraders().get(ID4).getWallet().withdrawDollarsFromWallet(amount4);
				else
					numOfInvalidQueries ++;
				
			}else if(query==5) {
				int ID5 = in.nextInt();
				out.printf("Trader %d: %.5f$ %.5fPQ\n", ID5, myMarket.getTraders().get(ID5).getWallet().totalDollars(), myMarket.getTraders().get(ID5).getWallet().totalCoins());				
				
			}else if(query==777) {
				double adding;
				for(int j=0; j<Trader.numberOfUsers; j++) {
					adding = myRandom.nextDouble()*10;
					myMarket.getTraders().get(j).getWallet().addCoinsToWallet(adding);
				}
				
			}else if(query==666) {
				double price666 = in.nextDouble();
				myMarket.makeOpenMarketOperation(price666, myMarket.getTraders());
				
			}else if(query==500) {
				out.printf("Current market size: %.5f %.5f\n", myMarket.getDollarsInMarket(), myMarket.getCoinsInMarket());
				
			}else if(query==501) {
				out.printf("Number of successful transactions: %d\n", myMarket.getNumOfSuccessfulTransactions());
				
			}else if(query==502) {
				out.println("Number of invalid queries: " + numOfInvalidQueries);
				
			}else if(query==505) {
				out.printf("Current prices: %.5f %.5f %.5f\n", myMarket.CPSelling(), myMarket.CPBuying(), myMarket.CPAverage());
				
			}else if(query==555) {
				for(int j=0; j<Trader.numberOfUsers; j++) {
					out.printf("Trader %d: %.5f$ %.5fPQ\n", j, myMarket.getTraders().get(j).getWallet().totalDollars(), myMarket.getTraders().get(j).getWallet().totalCoins());
				}
			}
			myMarket.checkTransactions(myMarket.getTraders());
		}
		in.close();
		out.close();
	}
}