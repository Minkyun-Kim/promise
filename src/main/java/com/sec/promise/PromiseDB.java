package com.sec.promise;

import java.util.ArrayList;

/**
 * Singleton class
 * This class is Promise database
 * 1st goal: Don't use database. Keep Member on the memory
 * 2nd goal: Save Promise data into file with JASON format
 * 3rd goal: Save Promise data in the database and get data from the database(SQLite)
 */
public class PromiseDB {
	
	private static PromiseDB instance = new PromiseDB();
	private ArrayList<Promise> promises  = new ArrayList<Promise>();

	
	private PromiseDB(){}
	
	public static PromiseDB getInstance() {
		return instance;
	}

	/**
	 * Make promise instance and return their id(hash)
	 * 
	 * @param date: date of promise member made
	 * @param location: location of promise member made
	 * @param fund: fund of promise member made
	 * @param participants: participants of promise member made
	 * @return promise id
	 */
	public String makePromise(String date, String location, float fund, ArrayList<String> participants) {
		
		Promise promise = new Promise(date, location, fund, participants);
		promises.add(promise);

		String promiseID = Util.getObjectHash(promise);

		return promiseID;
	}

	public String getPromiseInfo(String promiseID) {
		String info = null;
		for(Promise promise : promises) {
			if(Util.getObjectHash(promise).equals(promiseID)) {
				 info = promise.toString();
			}
		}
		return info;
	}

	public String getPromiseWalletAddress(String promiseId) {
		for(Promise promise: promises) {
			String objectHash = Util.getObjectHash(promise);
			if(objectHash.equals(promiseId));
			return promise.getWalletAddress();
		}
		return null;
	}

	public float getPromiseFund(String promiseId) {
		for(Promise promise: promises) {
			String objectHash = Util.getObjectHash(promise);
			if(objectHash.equals(promiseId));
			return promise.getFund();
		}
		return 0;
	}

	public boolean findAddress(String receiverAddress) {
		for(Promise promise: promises) {
			Debugger.log("promise Address: " + promise.getWalletAddress());
			if(promise.getWalletAddress().equals(receiverAddress))
				return true;
		}
		return false;
	}

	public void putPromiseUtxo(String receiverAddress, String transactionHash, float value) {
		for(Promise promise: promises) {
			if(promise.getWalletAddress().equals(receiverAddress)) {
				promise.addWalletUtxo(transactionHash, value);
				return;
			}
		}
		return;
	}

	public void showPromisesInfo() {
		Debugger.log();
		for(Promise promise: promises) {
			Debugger.log(promise);
		}
		Debugger.log();
	}

}
