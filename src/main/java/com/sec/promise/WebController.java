package com.sec.promise;

import java.util.ArrayList;

public class WebController {

	
	/**
	 * 회원가입 기능
	 * 
	 * @param name
	 * @param password
	 */
	public void signUp(String name, String password) {
		MemberDB.getInstance().signUpMember(name, password);
	}
	
	/**
	 * 로그인 기능
	 * 
	 * @param name
	 * @param password
	 */
	public boolean signIn(String name, String password) {
		return MemberDB.getInstance().signIn(name, password);
	}
	
	/**
	 * 약속 생성 기능
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
	 * 약속 조회 기능
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
	 * 약속 참여 기능
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
	 * 이용 가능한 서비스 출력
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
		
		//회원가입 4명
		webController.signUp("Kim",  "Kim");
		webController.signUp("Lee",  "Kim");
		webController.signUp("Park",  "Kim");
		webController.signUp("Choi",  "Kim");
		
		//서비스 1개 생성
		ServiceDB.getInstance().makeService("cafe", (float)40.0);

		//로그인 1명
		if(!webController.signIn("Kim", "Kim")) {
			Debugger.setValid();
			Debugger.log("Login fail");
			Debugger.setInvalid();
			
		}

		//약속 생성. 4명 모두 참가자로 등록
		Promise promise = new Promise("2019-08-09", "Digital City", (float)10.0 ) ;

		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Kim");
		participants.add("Lee");
		participants.add("Park");
		participants.add("Choi");
		promise.setParticipants(participants);

		webController.requestMakePromise(promise);

		//로그인한 사람 약속 조회
		Debugger.setValid();
		ArrayList<String> promiseIds = PromiseDB.getInstance().getPromiseIds("Kim");
		Debugger.setInvalid();

		//약속 참여
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

		//소비 가능 목록 출력
		ArrayList<String> serviceList = webController.showServiceList();
		for(String serviceInfo: serviceList) {
			System.out.println(serviceInfo);
		}

		//약속에서 소비
		Debugger.setValid();
		promiseIds = PromiseDB.getInstance().getPromiseIds("Kim");
		webController.useService("Kim", promiseIds.get(0), "cafe");
		Debugger.setValid();

		//전체 출력
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
