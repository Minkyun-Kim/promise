package com.sec.blockchain;

import java.util.ArrayList;

import com.sec.util.Debugger;
import com.sec.util.Util;

public class BlockHeader {

	private String hashPrevBlock;
	private String hashMerkleRoot;
	private long timestamp;
	
	public static class Builder{
		
		private String hashPrevBlock = "";
		private String hashMerkleRoot = "";
		private long timestamp = System.currentTimeMillis();
		
		public Builder hashPrevBlock(String hashPrevBlock) {
			this.hashPrevBlock = hashPrevBlock;
			return this;
		}
		public Builder hashMerkleRoot(String hashMerkleRoot) {
			this.hashMerkleRoot = hashMerkleRoot;
			return this;
		}
		public BlockHeader build() {
			BlockHeader blockHeader = new BlockHeader(this.hashPrevBlock, this.hashMerkleRoot, this.timestamp);
			return blockHeader;
		}
	}
	
	public BlockHeader(String prevBlockHash, String merkleTreeRootHash, long timestamp) {
		this.hashPrevBlock = prevBlockHash;
		this.hashMerkleRoot = merkleTreeRootHash;
		this.timestamp = timestamp;
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

}
