package core.java;

import java.util.concurrent.locks.*;

public class SynchBankTest {

	private static final int NACCOUNTS = 100;
	private static final double INITIAL_BALANCE = 100;
	
	public static void main(String[] args) {
		SynchBankTest synBank = new SynchBankTest();
		Bank b = synBank.new Bank(NACCOUNTS, INITIAL_BALANCE);
		for(int i=0; i< NACCOUNTS; i++){
			TransferRunnable tr = synBank.new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(tr);
			t.start();
		}
	}

	class Bank{
		private final double[] accounts;
		private ReentrantLock bankLock = new ReentrantLock();
		private Condition sufficientFunds;
		
		public Bank(int n, double initialBalance){
			accounts = new double[n];
			for(int i=0; i<n; i++)
				accounts[i] = initialBalance;
			sufficientFunds = bankLock.newCondition();
		}
		
		public void transfer(int fromAccount, int toAccount, double amount) throws InterruptedException{
			bankLock.lock();
			try{
				while(accounts[fromAccount] < amount) 
					sufficientFunds.await();
				System.out.println(Thread.currentThread());
				accounts[fromAccount] -= amount;
				System.out.printf("%10.2f from %d to %d", amount, fromAccount, toAccount);
				accounts[toAccount] += amount;
				System.out.printf("Total balance : %10.2f/n", getTotalBalance());
				sufficientFunds.signalAll();
			}
			finally{
				bankLock.unlock();
			}
		}
		
		public double getTotalBalance(){
			bankLock.lock();
			try{
				double sum = 0.0;
				for(double a : accounts)
					sum += a;
				return sum;
			}
			finally{
				bankLock.unlock();
			}
		}
		
		public int size(){
			return accounts.length;
		}
	}
	
	class TransferRunnable implements Runnable{
		private int fromAccount;
		private Bank bank;
		private double max;
		private int DELAY = 10;
		
		public TransferRunnable(Bank b, int fromAccount, double max){
			this.bank = b;
			this.fromAccount = fromAccount;
			this.max = max;
		}
		
		public void run(){
			try {
				while(true){
					int toAccount = (int)(bank.size() * Math.random());
					double amount = max * Math.random();
					bank.transfer(fromAccount, toAccount, amount);
					Thread.sleep((int)(DELAY*Math.random()));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
