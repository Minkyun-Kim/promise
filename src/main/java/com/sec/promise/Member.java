package com.sec.promise;

import java.util.ArrayList;

public class Member {

	private String name;
	private String password;
	private Wallet wallet = new Wallet();
	private ArrayList<String> promises = new ArrayList<String>();
	
	
	public Member(String name, String password) {
		this.name = name;
		this.password = password;
		this.wallet.setCoin((float)10.0);

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
	
	public String toString() {
		String info = "";
		info += "name: " + name 
				+ "\n" + wallet.toString();
		for(String promise: promises) {
			info+= "\npromise: " + promise;
		}
		info += "\n";
		return info;
	}
	

}
