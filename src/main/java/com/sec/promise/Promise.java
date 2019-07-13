package com.sec.promise;

import java.util.ArrayList;

public class Promise {
	
	private Wallet wallet;
	private String date;
	private String location;
	private float fund;
	private ArrayList<String> participants;
	private ArrayList<String> transactionIDs;


	public Promise(String date, String location, float fund) {
		this.date = date;
		this.location = location;
		this.fund = fund;
		this.wallet = new Wallet();
	}

	public Promise(String date, String location, float fund, ArrayList<String> participants) {
		this.date = date;
		this.location = location;
		this.fund = fund;
		this.participants = participants;
		this.wallet = new Wallet();
	}
	
	
	public String getLocation() {
		return location;
	}

	public float getFund() {
		return fund;
	}

	public ArrayList<String> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<String> participants) {
		this.participants = participants;
	}

	public String getDate() {
		return this.date;
	}
	
	public void addTransactionId(String transactionId) {
		transactionIDs.add(transactionId);
	}
	

	public String getWalletAddress() {
		return wallet.getAddress();
	}

	public void addWalletUtxo(String transactionHash, float value) {
		this.wallet.addUtxo(transactionHash, value);
	}

	public String toString() {
		String info = "date: " + date
				+ "\nlocation: " + location
				+ "\nfund:" + fund
				+ "\nparticipants: ";
		for(String name: participants) {
			info = info + name + " ";
		}
		info += wallet.toString();
		return info;
	}
	
}
