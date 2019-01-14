import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Main {


	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		
		
		//The program should loop until the user elects to terminate.
		//Prompt the user as to whether or not they would like to add an account, make a transaction, or terminate the program?  
		boolean run = true;
		boolean terminate = false;
		
		while (run) {
		String response;
		System.out.println("Would you like to add an account(1), make a transaction(2), or terminate(3)?");
		response = in.nextLine();
		
		while (!response.equals("1") &&! response.equals("2") && !response.equals("3")) {
			System.out.println("Would you like to add an account(1), make a transaction(2), or terminate(3)?");
			response = in.nextLine();
		}
		//Making accounts
		if (response.equals("1")) {
			String transponse;
			System.out.println("(S)avings or (C)hecking?");
			transponse = in.nextLine();
			
			while (!transponse.toLowerCase().equals("s") &&! transponse.toLowerCase().equals("c")) {
				System.out.println("(S)avings or (C)hecking?");
				transponse = in.nextLine();
			}
			
			//Savings Account
			if (transponse.toLowerCase().equals("s")) {
				System.out.print("Making savings account, ");
				String name;

				//Prompts for name
				System.out.print("Name:");
				name = in.nextLine();
				System.out.println("");
				
				
				
				//Makes the account
				accounts.add(new SavingsAccount (name, RATE, MIN_BAL, MIN_BAL_FEE));
				
				System.out.println("Savings Account made for "+ name );
				
				
			}
				//Checking Account
			else if (transponse.toLowerCase().equals("c")) {
				System.out.print("Making checking account, ");
				System.out.println("Please enter the");
				
				String name;
				System.out.print("Name:");
				name = in.nextLine();
				System.out.println("");
				
				//Makes account
				accounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				System.out.println("Checking Account made for "+ name );
			}
			
		}
			
		else if (response.equals("2")) {
				String transponse;	
		
		
		//If the user elects to make a transaction, prompt as to whether they would like to Withdraw, deposit, transfer, or get account numbers.  
		
			System.out.println("(W)ithdraw, (d)eposit, (t)ransfer, or (g)et account numbers?");
			transponse = in.nextLine();
		
			
			
			while (!transponse.toLowerCase().equals("w") &&! transponse.toLowerCase().equals("d") && !transponse.toLowerCase().equals("t") && !transponse.toLowerCase().equals("g")) {
				System.out.println("(W)ithdraw, (d)eposit, (t)ransfer, or (g)et account numbers?");
				transponse = in.nextLine();
			}
		
		//For deposit, withdraw and transfer, prompt the user for all necessary information and perform the transactions.   Use the Account Number to identify the account
		
			
		if (transponse.toLowerCase().equals("w")) {
			System.out.println("Withdrawing:");
			
			int accountnumber;
			String straccountnumber;
			
				System.out.println("Account Number:");
				straccountnumber = in.nextLine();
			
				
				while (!isNumeric(straccountnumber)) {
					String ans;
					System.out.println("Do you need help? (Y)es or (N)o");
					ans = in.nextLine();
					
					//help loop
					if (ans.toLowerCase().equals("y")) {
						boolean didSomething = false;
						String name;
						while (didSomething == false) {
							//keeps running until an account name is found
						System.out.println("What is the name? (C)ancel:");
						name = in.nextLine();
						
						for (BankAccount names:accounts) {
							
							if (names.getName().equals(name)) {
								System.out.println("The account number is"+names.getAccountNum());
								didSomething = true;
							}
							else if (name.toLowerCase().equals("c")) {
								didSomething = true;
								//Can cancel if name is forgotten
							}
						}
						}
					}
				System.out.println("Account Number:");
				straccountnumber = in.nextLine();
				
			}
			accountnumber = (int) Double.parseDouble(straccountnumber);
			
			System.out.println("Amount:");
			double amount;
			
			//For parsing
			String stramount;
			
			stramount = in.nextLine();
			while (!isNumeric(stramount)) {
				//Reprompts until a number is entered
				System.out.println("Amount:");
				stramount = in.nextLine();
				
				
			}
			amount = Double.parseDouble(stramount);
			
			
			
			for (BankAccount withdraws:accounts)
				//Finds the account matching
				if (withdraws.getAccountNum() == accountnumber) {
					
					
					
					//Use a try catch to catch any IllegalArgumentExceptions and display the message “transaction not authorized” whenever an exception appears.
					try {withdraws.withdraw(amount); System.out.print(withdraws.getBalance());}
					
					catch (IllegalArgumentException e) {
						System.out.println("This transaction is illegal");
					}
					
					
					
					
				}
			
		}
		
		//If when you prompt for an account number, an invalid account number is entered, give the user the option to reenter their account number or get their account numbers(enter their name and return each of their accounts and whether it is checking or savings).  
	
		
		else if (transponse.toLowerCase().equals("d")){
		System.out.println("Depositing:");
		
		double accountnumber;
		//For parsing
		String straccountnumber;
		
			System.out.print("Account Number:");
			straccountnumber = in.nextLine();
			
		while (!isNumeric(straccountnumber)) {
			
			//Every time the user enters a non-numeric value, they are prompted for help
			String ans;
			System.out.println("Do you need help? (Y)es or (N)o");
			ans = in.nextLine();
			if (ans.toLowerCase().equals("y")) {
				boolean didSomething = false;
				String name;
				while (didSomething == false) {
					//prompts for name until a real name is entered
				System.out.println("What is the name? (C)ancel:");
				name = in.nextLine();
				
				for (BankAccount names:accounts) {
					
					if (names.getName().equals(name)) {
						System.out.println("The account number is"+names.getAccountNum());
						didSomething = true;
					}
					else if (name.toLowerCase().equals("c")) {
						//Allows to cancel if the real name is forgotten
						didSomething = true;
					}
				}
				}
			}
			
			
			System.out.println("Account Number:");
			straccountnumber = in.nextLine();
		}
		accountnumber=Double.parseDouble(straccountnumber);
		

			System.out.print("Amount:");
			double amount;
			String stramount;
			boolean didSomething = false;
			stramount = in.nextLine();
			while (!isNumeric(stramount)) {
				System.out.print("Amount:");
				stramount = in.nextLine();
				}
			amount = Double.parseDouble(stramount);
			
			for (BankAccount deposit:accounts) {
				if (deposit.getAccountNum() == accountnumber) {
					//try catch this
					
					try {deposit.deposit(amount);
					System.out.print("The total amount is" +deposit.getBalance());
					didSomething = true;}
					catch (IllegalArgumentException e) {
						System.out.print("This transaction is illegal");
					}
				}
			}
		}
		
		
		
		else if (transponse.toLowerCase().equals("t")){
			int accNumW;
			int accNumD;
			String ans = "n";
			
			System.out.print("Transferring:");
			
			String straccountnumber;
			
				System.out.println("Account Number(Transferrer):");
				straccountnumber = in.nextLine();
			while (!isNumeric(straccountnumber)) {
				
				//Help loop
				System.out.println("Do you need help? (Y)es or (N)o");
				ans = in.nextLine();
				if (ans.toLowerCase().equals("y")) {
					boolean didSomething = false;
					String name;
					while (didSomething == false) {
					System.out.println("What is the name? (C)ancel:");
					name = in.nextLine();
					
					for (BankAccount names:accounts) {
						
						if (names.getName().equals(name)) {
							System.out.println("The account number is"+names.getAccountNum());
							didSomething = true;
						}
						else if (name.toLowerCase().equals("c")) {
							didSomething = true;
							//Can be  canceled if real name is forgotten
						}
					}
					}
				}
				else {
					System.out.println("Account Number(Transferrer):");
					//User re enters and if it's numeric  it's parsed
					straccountnumber = in.nextLine();
					}
				
			}
			accNumW = (int) Double.parseDouble(straccountnumber);
			in.nextLine();
			
			
			
			
			
			System.out.println("Account Number(Transferree):");
			straccountnumber = in.nextLine();
		
		while (!isNumeric(straccountnumber)) {
			
			//help loop
			System.out.println("Do you need help? (Y)es or (N)o");
			ans = in.nextLine();
			if (ans.toLowerCase().equals("y")) {
				boolean didSomething = false;
				String name;
				while (didSomething == false) {
				System.out.println("What is the name? (C)ancel:");
				name = in.nextLine();
				
				for (BankAccount names:accounts) {
					
					if (names.getName().equals(name)) {
						System.out.println("The account number is"+names.getAccountNum());
						didSomething = true;
					}
					else if (name.toLowerCase().equals("c")) {
						didSomething = true;
					}
				}
				}
			}
			
			
			
			System.out.println("Account Number(Transferree):");
			straccountnumber = in.nextLine();	
		}
		
		accNumD = (int) Double.parseDouble(straccountnumber);
		in.nextLine();
		//For parsing
		String stramount;
		
		double amount;
		System.out.println("Amount:");
		stramount = in.nextLine();
		
		while (!isNumeric(stramount)) {
			System.out.println("Amount:");
			stramount = in.nextLine();	
		}
		
		amount = (int) Double.parseDouble(stramount);
		in.nextLine();
		
		
		for (BankAccount depositor:accounts) {
			
			//Finds depositor
			if (depositor.getAccountNum() == accNumD) {
				//finds withdrawer
				for (BankAccount transfer:accounts) {
					if (transfer.getAccountNum() == accNumW) {
						try { transfer.transfer(depositor, amount); }
						
						catch (IllegalArgumentException e) {
							System.out.print("This transaction is illegal");
						}
					}
				}
			}
		
		}
			
			

			
		//End of transaction	
		}
		
		else if (transponse.toLowerCase().equals("g")){
			
			boolean didSomething = false;
			System.out.println("Getting account numbers:");
			
			//help loop but you don't need to mess up to get to it
			System.out.print("(C) to cancel, Name:");
			String name = in.nextLine();
			for (BankAccount list:accounts) {
				if (list.getName().equals(name)) {
				System.out.println("The account number is:"+ list.getAccountNum());
				didSomething = true;
				}
				else if (name.toLowerCase().equals("c")) {
					didSomething = true;
							
				
			}
			
		}
		
		}
		}
		
		
		else if (response.equals("3")) {
			//Terminate
			System.out.print("Terminating.");
			terminate = true;
			run = false;
		}
		
		
		
		
		
		
		//To keep things in run variable loop a terminate variable is used
		if (!terminate) {
		System.out.print("Want to make another action? (Y/N)");
		String runsponse = in.nextLine();
		while (!runsponse.toUpperCase().equals("Y") && !runsponse.toUpperCase().equals("N")) {
			System.out.print("Want to make another action? (Y/N)");
			 runsponse = in.nextLine();
		}
		if (runsponse.toUpperCase().equals("N")) {
			//To get out of run loop "N" must be entered
			run = false;
		}
		else {
			//if N isn't entered, it runs
			run = true;
		}
		
		
		}
		}
}


	


	private static boolean isNumeric(String str)
	{
	try
	{
	Double.parseDouble(str);
	return true;
	}
			catch(IllegalArgumentException e)
			{
				return false;
			}
	}


}

