package com.sec.promise;

import java.util.ArrayList;

public class BlockHeader {

	private String hashPrevBlock;
	private String hashMerkleRoot;
	private long timestamp;
	
	public BlockHeader() {
		hashPrevBlock = "";
		hashMerkleRoot = "";
		timestamp = System.currentTimeMillis();
	}
	
	public BlockHeader(String prevBlockHash, String merkleTreeRootHash) {
		this.hashPrevBlock = prevBlockHash;
		this.hashMerkleRoot = merkleTreeRootHash;
		timestamp = System.currentTimeMillis();
	}
	public String toString() {
		return "previous block hash: " + hashPrevBlock
				+ "\nmerkle tree root hash: " + hashMerkleRoot
				+ "\ntimestamp: " + timestamp
				+ "\n";
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.hashPrevBlock = prevBlockHash;
		
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
		this.hashMerkleRoot = Util.getObjectHash(transactions);
		
	}

}
