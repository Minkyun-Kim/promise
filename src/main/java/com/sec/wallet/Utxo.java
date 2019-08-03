package com.sec.wallet;

import java.util.ArrayList;
import java.util.Map;

import com.sec.util.Pair;

public class Utxo {

	private ArrayList<Pair<String, Float>> balance = new ArrayList<Pair<String,Float>>();

	public void addTransactionValue(String transactionId, Float value) {
		balance.add(new Pair<String, Float>(transactionId, value));
	}
	
	public Pair<String, Float> getOneUtxo(){
		Pair<String, Float> firstUtxo = balance.get(0);
		balance.remove(0);
		return firstUtxo;
	}

	public ArrayList<Pair<String, Float>> getUtxos() {
		return balance;
	}


}
