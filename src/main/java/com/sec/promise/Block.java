package com.sec.promise;

import java.util.ArrayList;

public class Block {

	private BlockHeader blockHeader = new BlockHeader();
	private String curBlockHash;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Block() {}
	
	public String getCurBlockHash() {
		return curBlockHash;
	}

	public void setCurBlockHash(String curBlockHash) {
		this.curBlockHash = curBlockHash;
	}
	
	
	public String toString() {
		String sentence =  blockHeader.toString();
		for(Transaction transaction: transactions) {
			sentence += "Transaction ID: " + Util.getObjectHash(transaction)+"\n";
			sentence += transaction.toString();
		}
		return sentence;
	}

	public BlockHeader getBlockHeader() {
		return this.blockHeader;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}


	public void makeBlock(String prevBlockHash) {
		blockHeader.setPrevBlockHash(prevBlockHash);
		blockHeader.setMerkleTreeRootHash(transactions);
	}

}
