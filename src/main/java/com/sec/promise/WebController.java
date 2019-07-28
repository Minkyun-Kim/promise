package com.sec.promise;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class WebController {

	
	/**
	 * ȸ������ ���
	 * 
	 * @param name
	 * @param password
	 */
	public static void signUp(SignInfo signInfo) {
		Debugger.log("[signUp] " + signInfo.getName() + " signed up");
		MemberDB.getInstance().signUpMember(signInfo.getName(), signInfo.getPassword());
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
	@RequestMapping(value="/createPromise", method=RequestMethod.POST)
	public void requestMakePromise(@RequestBody PromiseInfo promiseInfo) {
		Debugger.log("[requestMakePromise] " + promiseInfo.toString());
		
		PromiseDB promiseDB = PromiseDB.getInstance();
		String promiseID = promiseDB.makePromise(
				promiseInfo.getDate(), 
				promiseInfo.getLocation(), 
				promiseInfo.getFund(), 
				promiseInfo.getParticipants());
		
		MemberDB memberDB = MemberDB.getInstance();
		memberDB.enrollPromiseToMember(promiseID, promiseInfo.getParticipants());
	}
	
	@RequestMapping(value="showUnjoinPromisesOfMember", method = RequestMethod.GET)
	public ArrayList<String> showUnjoinPromisesOfMember(@RequestParam String name) {
		Debugger.log("[showUnjoinPromises] " + name);
		MemberDB memberdb = MemberDB.getInstance();
		ArrayList<String> promiseIDs = memberdb.getMemberPromises(name);
		ArrayList<String> promiseInfoList = new ArrayList<String>();
		PromiseDB promiseDb = PromiseDB.getInstance();
		for(String promiseID : promiseIDs){
			promiseInfoList.add(promiseDb.getPromiseInfo(promiseID));
		}
		return promiseInfoList;
	}

	@RequestMapping(value="showJoinPromisesOfMember", method = RequestMethod.GET)
	public ArrayList<String> showJoinPromisesOfMember(@RequestParam String name) {
		Debugger.log("[showJoinPromises] " + name);
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
	@RequestMapping(value="joinPromise", method = RequestMethod.PUT)
	public String joinPromise(@RequestBody JoinPromiseInfo joinPromiseInfo) {
		Debugger.log("[joinPromise] " + joinPromiseInfo);

		String promiseWalletAddress = PromiseDB.getInstance().getPromiseWalletAddress(joinPromiseInfo.getPromiseId());
		float promiseFund = PromiseDB.getInstance().getPromiseFund(joinPromiseInfo.getPromiseId());
		
		boolean validTransfer = MemberDB.getInstance().transferMemberFund(joinPromiseInfo.getName(), promiseWalletAddress, promiseFund);
		if(validTransfer)
			return "transfered";
		else
			return "fail";
	}
	
	@RequestMapping(value="showBlocks", method=RequestMethod.GET)
	public String showBlocks() {
		Debugger.log("[showBlock] ");
		return BlockDB.getInstance().showBlocksInfo();
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
	
/*	
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
	*/



}
