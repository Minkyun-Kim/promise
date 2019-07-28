package com.sec.promise;

import java.util.ArrayList;

public class Promise {
	
	private String promiseId;
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
		this.promiseId = Util.getObjectHash(this);
	}

	public Promise(String date, String location, float fund, ArrayList<String> participants) {
		this.date = date;
		this.location = location;
		this.fund = fund;
		this.participants = participants;
		this.wallet = new Wallet();
		this.promiseId = Util.getObjectHash(this);
	}
	
	public String getPromiseId() {
		return promiseId;
	}

	public void setPromiseId(String promiseId) {
		this.promiseId = promiseId;
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

	public void useService(String serviceName, String serviceWalletAddress, float price) {
		this.wallet.transfer("Service Name: " + serviceName + "\n", serviceWalletAddress, price);
	}

	public boolean hasMember(String memberName) {
		for(String participant: participants) {
			if(participant.equals(memberName))
				return true;
		}
		return false;
	}
	
}
