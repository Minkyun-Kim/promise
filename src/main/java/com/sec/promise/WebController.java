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
	
	/**
	 * ��� ���� ���
	 * 
	 * When member has unjoined promise, join promise with transfer promise funds;
	 * 
	 * @param memberName
	 * @param promiseId
	 * @return
	 */
	public String joinPromise(String memberName, String promiseId) {
		String promiseWalletAddress = PromiseDB.getInstance().getPromiseWalletAddress(promiseId);
		float promiseFund = PromiseDB.getInstance().getPromiseFund(promiseId);
		
		boolean validTransfer = MemberDB.getInstance().transferMemberFund(memberName, promiseWalletAddress, promiseFund);
		if(validTransfer)
			return "transfered";
		else
			return "fail";
	}
	
	/**
	 * �̿� ������ ���� ���
	 * 
	 * @return serviceList
	 */
	public ArrayList<String> showServiceList() {
		ArrayList<String> serviceList = new ArrayList<String>();
		
		serviceList = ServiceDB.getInstance().getServiceList();
		
		return serviceList;
		
	}

	public void useService(String memberName, String promiseID, String serviceName) {
		String serviceWalletAddress = ServiceDB.getInstance().getServiceWalletAddress(serviceName);
		float price = ServiceDB.getInstance().getServicePrice(serviceName);

		if(PromiseDB.getInstance().isPromiseMember(promiseID, memberName)) {
			PromiseDB.getInstance().useServiceInPromise(promiseID, serviceName, serviceWalletAddress, price);
		}
	}
	
	
	public static void main(String[] args) {

		WebController webController = new WebController();
		
		//ȸ������ 4��
		webController.signUp("Kim",  "Kim");
		webController.signUp("Lee",  "Kim");
		webController.signUp("Park",  "Kim");
		webController.signUp("Choi",  "Kim");
		
		//���� 1�� ����
		ServiceDB.getInstance().makeService("cafe", (float)40.0);

		//�α��� 1��
		if(!webController.signIn("Kim", "Kim")) {
			Debugger.setValid();
			Debugger.log("Login fail");
			Debugger.setInvalid();
			
		}

		//��� ����. 4�� ��� �����ڷ� ���
		Promise promise = new Promise("2019-08-09", "Digital City", (float)10.0 ) ;

		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Kim");
		participants.add("Lee");
		participants.add("Park");
		participants.add("Choi");
		promise.setParticipants(participants);

		webController.requestMakePromise(promise);

		//�α����� ��� ��� ��ȸ
		Debugger.setValid();
		ArrayList<String> promiseIds = PromiseDB.getInstance().getPromiseIds("Kim");
		Debugger.setInvalid();

		//��� ����
		if(!webController.joinPromise("Kim", promiseIds.get(0)).equals("transfered")) {
			Debugger.setValid();
			Debugger.log("join promise fail!");
			Debugger.setInvalid();
		}
		if(!webController.joinPromise("Lee", promiseIds.get(0)).equals("transfered")) {
			Debugger.setValid();
			Debugger.log("join promise fail!");
			Debugger.setInvalid();
		}
		if(!webController.joinPromise("Park", promiseIds.get(0)).equals("transfered")) {
			Debugger.setValid();
			Debugger.log("join promise fail!");
			Debugger.setInvalid();
		}
		if(!webController.joinPromise("Choi", promiseIds.get(0)).equals("transfered")) {
			Debugger.setValid();
			Debugger.log("join promise fail!");
			Debugger.setInvalid();
		}

		//�Һ� ���� ��� ���
		ArrayList<String> serviceList = webController.showServiceList();
		for(String serviceInfo: serviceList) {
			System.out.println(serviceInfo);
		}

		//��ӿ��� �Һ�
		Debugger.setValid();
		promiseIds = PromiseDB.getInstance().getPromiseIds("Kim");
		webController.useService("Kim", promiseIds.get(0), "cafe");
		Debugger.setValid();

		//��ü ���
		Debugger.setValid();
		Debugger.log("//////////////////// Member Information ////////////////////");
		MemberDB.getInstance().showMembersInfo();
		Debugger.log();
		Debugger.log("//////////////////// Promise Information ////////////////////");
		PromiseDB.getInstance().showPromisesInfo();
		Debugger.log();
		Debugger.log("//////////////////// BlockChain Information ////////////////////");
		BlockDB.getInstance().showBlocksInfo();
		Debugger.log();
		Debugger.log("//////////////////// Service Information ////////////////////");
		ServiceDB.getInstance().showServiceInfo();
		Debugger.log();
		Debugger.setInvalid();
		
		

		
		
	}



}
