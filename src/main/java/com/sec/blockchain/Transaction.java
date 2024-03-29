package com.sec.blockchain;

import com.google.gson.Gson;

public class Transaction {
	
	private String content;
	private String from;
	private String to;
	private float value;
	private long timestamp;
	
	public Transaction(String content, String from, String to, float value) {
		this.content = content;
		this.from = from;
		this.to = to;
		this.value = value;
		this.timestamp = System.currentTimeMillis();
	}



	public String toString() {
		return new Gson().toJson(this);
	}
}
