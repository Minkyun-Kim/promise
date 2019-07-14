package com.sec.promise;

import java.util.ArrayList;
import java.util.Queue;


public class BlockDB {
	
	public final Integer transactionPerBlock = 4;
	private static BlockDB instance = new BlockDB();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Transaction> transactionQueue = new ArrayList<Transaction>();
	
	private BlockDB() {	
		Block block = new Block();
		blocks.add(block);
	}

	
	public static BlockDB getInstance() {
		
		return instance;
	}

	public void queueTransaction(Transaction transaction) {
		transactionQueue.add(transaction);
		
		//transaction이 들어왔는데 4개면 즉각 블록 생성
		if(transactionQueue.size() == transactionPerBlock) {
			addBlockChain();
		}
		
	}

	public void addBlockChain() {
		Block block = new Block();
		for(int i = 0; i < transactionPerBlock; i++) {
			Debugger.log(transactionQueue.get(0));
			block.addTransaction(transactionQueue.get(0));
			transactionQueue.remove(0);
		}
		block.makeBlock(Util.getObjectHash(blocks.get(blocks.size()-1)));
		blocks.add(block);
		Debugger.log("blockchain size: " + blocks.size());
	}

	public void showBlocksInfo() {
		Debugger.log("\n");
		Debugger.log("////////////////BlockChain////////////////");
		for(Block block : blocks) {
			Debugger.log(block);
		}
		Debugger.log("////////////////Transaction Queue////////////////");
		for(Transaction transaction : transactionQueue) {
			Debugger.log(transaction);
		}
	}
}
