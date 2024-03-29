package com.sec.user;

import java.util.ArrayList;

import com.sec.wallet.Wallet;

public class Member {

	private String name;
	private String password;
	private Wallet wallet = new Wallet();
	private ArrayList<String> promises = new ArrayList<String>();
	
	
	public Member(String name, String password) {
		this.name = name;
		this.password = password;
		this.wallet.setCoin(name, (float)10.0);

	}

	public String getName() {
		return this.name;
	}

	public void addPromise(String promiseID) {
		promises.add(promiseID);
		
	}

	public ArrayList<String> getPromises() {
		return promises;
	}

	public boolean validSignIn(String name, String password) {
		if(this.name.equals(name) && this.password.equals(password))
			return true;
		else
			return false;
	}

	public String getWalletAddress() {
		return wallet.getAddress();
	}

	public void addWalletUtxo(String transactionHash, float value) {
		this.wallet.addUtxo(transactionHash, value);
	}
	
	public void transferFund(String promiseWalletAddress, float promiseFund) {
		wallet.transfer(name + " transfered promise fund ", promiseWalletAddress, promiseFund);
	}

	public String toString() {
		String info = "";
		info += "name: " + name 
				+ "\n" + wallet.toString();
		for(String promise: promises) {
			info+= "\npromise: " + promise;
		}
		info += wallet.toString();
		info += "\n";
		return info;
	}

	

}
