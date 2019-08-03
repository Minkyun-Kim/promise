package com.sec.blockchain;

import java.util.ArrayList;

import com.sec.util.Util;

public class Block {

	private BlockHeader blockHeader ;
	private String curBlockHash;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Block() {
		this.blockHeader = new BlockHeader.Builder().build();
	}
	
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

		while(transactions.size() != BlockDB.transactionPerBlock) {
			transactions.add(new Transaction("", "", "", (float)0.0));
		}
		int txSize = transactions.size();
		int treeSize = txSize * 2 - 1;
		String[] merkleTree = new String[treeSize];
		int srcIdx = 0;
		int destIdx = txSize;
		for(Transaction tx: transactions) {
			merkleTree[srcIdx] = Util.getObjectHash(tx);
		}
		for(; destIdx < treeSize; destIdx++) {
			merkleTree[destIdx] = Util.getObjectHash(merkleTree[srcIdx] + merkleTree[srcIdx+1]);
			srcIdx += 2;
		}

		
		this.blockHeader = new BlockHeader
				.Builder()
				.hashPrevBlock(prevBlockHash)
				.hashMerkleRoot(merkleTree[treeSize-1])
				.build();
	}

}
