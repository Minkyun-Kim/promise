package com.sec.user;

import com.sec.wallet.Wallet;

public class Service {
	Wallet wallet = new Wallet();
	String name;
	float price;
	
	public Service() {}

	public Service(String name, float price) {
		this.name = name;
		this.price = price;
	}
	

	public String getName() {
		return this.name;
	}

	public String getWalletAddress() {
		return wallet.getAddress();
	}

	public float getPrice() {
		return this.price;
	}

	public void addWalletUtxo(String transactionHash, float value) {
		this.wallet.addUtxo(transactionHash, value);
		
	}

	public String toString() {
		String info = "";
		info += "name: " + name
				+ "\nprice: " + price + "\n"
				+ wallet.toString();
		return info;
	}
}
