package com.sec.promise;

import java.util.ArrayList;

public class WebController {

	
	/**
	 * ȸ������ ���
	 * 
	 * @param name
	 * @param password
	 */
	public void signUp(String name, String password) {
		MemberDB.getInstance().signUpMember(name, password);
	}
	
	/**
	 * �α��� ���
	 * 
	 * @param name
	 * @param password
	 */
	public boolean signIn(String name, String password) {
		return MemberDB.getInstance().signIn(name, password);
	}
	
	/**
	 * ��� ���� ���
	 * 
	 * When the <Make Promise> requested,
	 * First, Make Request and save
	 * Second, Enroll requested promise to participants to let them know
	 * 
	 * @param promise: contain the information of the requested promise
	 */
	public void requestMakePromise(Promise promise) {
		
		PromiseDB promiseDB = PromiseDB.getInstance();
		String promiseID = promiseDB.makePromise(
				promise.getDate(), 
				promise.getLocation(), 
				promise.getFund(), 
				promise.getParticipants());
		
		MemberDB memberDB = MemberDB.getInstance();
		memberDB.enrollPromiseToMember(promiseID, promise.getParticipants());
		

	}
	
	/**
	 * ��� ��ȸ ���
	 * 
	 * when the <Show Member's Promises> requested,
	 * First, get Promise ids of Member from MemberDB
	 * Second, get Promises information from PromiseDB
	 * @param name
	 */
	public ArrayList<String> showPromisesOfMember(String name) {
		MemberDB memberdb = MemberDB.getInstance();
		ArrayList<String> promiseIDs = memberdb.getMemberPromises(name);
		ArrayList<String> promiseInfoList = new ArrayList<String>();
		PromiseDB promiseDb = PromiseDB.getInstance();
		for(String promiseID : promiseIDs){
			promiseInfoList.add(promiseDb.getPromiseInfo(promiseID));
		}
		return promiseInfoList;

	}
	
	public String joinPromise(String memberName, String promiseId) {
		String promiseWalletAddress = PromiseDB.getInstance().getPromiseWalletAddress(promiseId);
		float promiseFund = PromiseDB.getInstance().getPromiseFund(promiseId);
		
		boolean validTransfer = MemberDB.getInstance().transferMemberFund(memberName, promiseWalletAddress, promiseFund);
		if(validTransfer)
			return "transfered";
		else
			return "fail";
		
	}

}
