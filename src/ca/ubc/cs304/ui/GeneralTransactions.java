package ca.ubc.cs304.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class GeneralTransactions extends TerminalTransaction{

	public GeneralTransactions() {
	}

	/**
	 * Displays simple text interface
	 */ 
	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while (choice != 3) {
			System.out.println("Who are you?");
			System.out.println();
			System.out.println("1. Clerk");
			System.out.println("2. Customer");
			System.out.println("3. Quit");
			System.out.print("Please choose one of the above 2 options: ");

			choice = readInteger(false);

			System.out.println(" ");

			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:  
					handleClerk();
					break;
				case 2:  
					handleCustomer();
					break;
				case 3:
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
				}
			}
		}		
	}

	private void handleClerk() {
		System.out.println("Give the access key or type back:");
		String inputKey = readLine();
		if (!inputKey.equals("back")){
			ClerkTransactions clerk = new ClerkTransactions();
			clerk.login(inputKey, delegate);
		}
	}

	private void handleCustomer(){
		CustomerTransactions customer = new CustomerTransactions();
		customer.customerMenu(delegate);
	}

	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
}
