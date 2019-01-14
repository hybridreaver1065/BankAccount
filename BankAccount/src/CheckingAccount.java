
public class CheckingAccount extends BankAccount {
	private int numTransactions;
	
	final double OVER_DRAFT_FEE;
	
	final double TRANSACTION_FEE;
	
	final double FREE_TRANS;
	
	public CheckingAccount(String n,double odf, double tf, int freeTrans) {
		super(n);
		
		OVER_DRAFT_FEE = odf;
		
		TRANSACTION_FEE = tf;
		
		FREE_TRANS = freeTrans;
		
		numTransactions = 0;
	}
	
	public CheckingAccount (String n, double b, double odf, double tf, int freeTrans) {
		super(n, b);
		
		OVER_DRAFT_FEE = odf;
		
		TRANSACTION_FEE = tf;
		
		FREE_TRANS = freeTrans;
		
		numTransactions = 0;
	}
	
	public void deposit(double amt) {
		//Can't be negative
		if ( amt < 0) {
			throw new IllegalArgumentException();
		}	
		else {
				
			super.deposit(amt);
		}
		
		numTransactions++;
		if (FREE_TRANS < numTransactions) {
			super.withdraw(TRANSACTION_FEE);
		}
			
	}
	
	public void withdraw(double amt) {
		
		if (getBalance() < 0 || amt < 0) {
			throw new IllegalArgumentException();
		}
		else {
			super.withdraw(amt);
			
			numTransactions++;
		
			
			if (FREE_TRANS < numTransactions) {
				super.withdraw(TRANSACTION_FEE);
			}
			if (getBalance() < 0|| amt < 0) {
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
		
	}
	
	/**
	 * other is deposited into from this account
	 */
	public void transfer(BankAccount other, double amt) {
		if (other.getName().equals(this.getName())) {
		if (getBalance() < amt || amt < 0) {
			
			throw new IllegalArgumentException();
		}
		else {
			other.deposit(amt);
			//super so it doesn't run the withdraw method
			super.withdraw(amt);
			numTransactions++;
			
			
			if (FREE_TRANS < numTransactions) {
				super.withdraw(TRANSACTION_FEE);
			}
			if (getBalance() < 0) {
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
		}
		else  {
			throw new IllegalArgumentException();	
		}
		
	}
	public void endOfMonthUpdate() {
		numTransactions = 0;
	}
	
}