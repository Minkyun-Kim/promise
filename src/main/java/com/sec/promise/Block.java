package com.sec.promise;

import java.util.ArrayList;

public class Block {

	private BlockHeader blockHeader = new BlockHeader();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Block() {}
	
	public String toString() {
		String sentence =  blockHeader.toString();
		for(Transaction transaction: transactions) {
			sentence += transaction.toString();
		}
		return sentence;
	}


	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}


	public void makeBlock(String prevBlockHash) {
		blockHeader.setPrevBlockHash(prevBlockHash);
		blockHeader.setMerkleTreeRootHash(transactions);
	}

}
