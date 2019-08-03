package com.sec.blockchain;

import java.util.ArrayList;
import java.util.Queue;

import com.google.gson.Gson;
import com.sec.util.Util;


public class BlockDB {
	
	public static final Integer transactionPerBlock = 4;
	private static BlockDB instance = new BlockDB();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Transaction> transactionQueue = new ArrayList<Transaction>();
	
	private BlockDB() {	
		Block block = new Block();
		block.setCurBlockHash(Util.getObjectHash(block.getBlockHeader()));
		blocks.add(block);
	}

	
	public static BlockDB getInstance() {
		
		return instance;
	}

	public void queueTransaction(Transaction transaction) {
		transactionQueue.add(transaction);
		
		//transaction이 들어왔는데 4개면 즉각 블록 생성
		if(transactionQueue.size() == BlockDB.transactionPerBlock) {
			addBlockChain();
		}
		
	}

	public void addBlockChain() {
		Block block = new Block();
		int txQueueSize = transactionQueue.size();
		for(int i = 0; i < txQueueSize; i++) {
			block.addTransaction(transactionQueue.get(0));
			transactionQueue.remove(0);
		}
		Block prevBlock = blocks.get(blocks.size()-1);
		String prevBlockHash = prevBlock.getCurBlockHash();
		block.makeBlock(prevBlockHash);
		block.setCurBlockHash(Util.getObjectHash(block.getBlockHeader()));
		blocks.add(block);
	}

	public String showBlocksInfo() {
		if(transactionQueue.size() != 0) {
			addBlockChain();
		}
		return new Gson().toJson(blocks);
	}
}
