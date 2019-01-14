
public abstract class BankAccount {
	static int nextAccNum;
	private String name;
	private int acctNum;
	private double balance;
	
	
	abstract void endOfMonthUpdate();
	
	public BankAccount(String n) {
		name = n;
		balance = 0;
		acctNum = nextAccNum;
		nextAccNum ++;
	}
	
	public BankAccount (String n, double b) {
		name = n;
		balance = b;
		acctNum = nextAccNum;
		nextAccNum ++;
	}

	public void deposit(double amt) {
		if ( amt < 0) {
			throw new IllegalArgumentException();
		}
		else	{
			balance += amt;
		}
		
		}
		



	public void withdraw(double amt) {
		if (amt > 0){
		balance -= amt;
		}
	}
	
		
	/**
	 * 
	 * @param other is deposited into from this one
	 * @param amt is the amount withdrawn from this account
	 */
	public void transfer(BankAccount other, double amt) {
		if (getBalance() < amt || amt < 0) {
			
			throw new IllegalArgumentException();
		}
		else {
			other.deposit(amt);
			withdraw(amt);
			}
	}
	
	
	

	public int getAccountNum() {
		return acctNum;
	}
	
	public String getName() {
		return name;
		
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		return ""+acctNum+" "+ name+ " "+ balance+ "";
	}

}