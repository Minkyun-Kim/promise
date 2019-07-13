package com.sec.promise;

import java.util.ArrayList;

public class BlockHeader {

	private String prevBlockHash;
	private String merkleTreeRootHash;
	private long timestamp;
	
	public BlockHeader() {
		prevBlockHash = "";
		merkleTreeRootHash = "";
		timestamp = System.currentTimeMillis();
	}
	
	public BlockHeader(String prevBlockHash, String merkleTreeRootHash) {
		this.prevBlockHash = prevBlockHash;
		this.merkleTreeRootHash = merkleTreeRootHash;
		timestamp = System.currentTimeMillis();
	}
	/*
	public String getPrevBlockHash() {
		return prevBlockHash;
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}

	public String getMerkleTreeRootHash() {
		return merkleTreeRootHash;
	}

	public void setMerkleTreeRootHash(String merkleTreeRootHash) {
		this.merkleTreeRootHash = merkleTreeRootHash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	*/
	public String toString() {
		return "previous block hash: " + prevBlockHash
				+ "\nmerkle tree root hash: " + merkleTreeRootHash
				+ "\ntimestamp: " + timestamp
				+ "\n";
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
		
	}

	public void setMerkleTreeRootHash(ArrayList<Transaction> transactions) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.merkleTreeRootHash = Util.getObjectHash(transactions);
		
	}

}
