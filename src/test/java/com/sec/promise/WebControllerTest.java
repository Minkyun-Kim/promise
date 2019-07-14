package com.sec.promise;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WebControllerTest {

	@Test
	public void testSignIn() {
		WebController webController = new WebController();
		webController.signUp("Kim", "Kim");
		assert(webController.signIn("Kim", "Kim"));
		
	}
	
	@Test
	public void testShowPromisesOfMember() {
		Debugger.setValid();
		WebController webController = new WebController();
		webController.signUp("Kim", "Kim");
		webController.signUp("Lee", "Lee");
		webController.signUp("Park","Park");
		webController.signUp("Choi", "Choi");
		webController.signUp("Jung", "Jung");
		webController.signUp("Nam", "Nam");
		
		Promise promise1 = new Promise("2019-08-09", "Digital City", (float)10000.0 ) ;
		Promise promise2 = new Promise("2019-08-10", "Global Tech Center", (float)20000.0);

		ArrayList<String> participants1 = new ArrayList<String>();
		participants1.add("Kim");
		participants1.add("Lee");
		participants1.add("Park");
		promise1.setParticipants(participants1);
		ArrayList<String> participants2 = new ArrayList<String>();
		participants2.add("Choi");
		participants2.add("Jung");
		participants2.add("Nam");
		promise2.setParticipants(participants2);

		
		Debugger.log("Kim's promises");
		webController.requestMakePromise(promise1);
		ArrayList<String> promisesInfo = webController.showPromisesOfMember("Kim");
		for(String info: promisesInfo) {
			System.err.println(info);
			Debugger.log(info);
		}
		Debugger.log("\n");
		
		Debugger.log("Choi's promises");
		webController.requestMakePromise(promise2);
		promisesInfo = webController.showPromisesOfMember("Choi");
		for(String info: promisesInfo) {
			Debugger.log(info);
		}
		Debugger.log("\n");
	}
	
	@Test
	public void testJoinPromise() {
		Debugger.setValid();
		WebController webController = new WebController();

		webController.signUp("Kim",  "Kim");
		webController.signUp("Lee",  "Lee");
		webController.signUp("Park", "Park");
		webController.signUp("Son", "Son");

		Promise promise = new Promise("2019-08-09", "Digital City", (float)10.0 ) ;

		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Kim");
		participants.add("Lee");
		participants.add("Park");
		participants.add("Son");
		promise.setParticipants(participants);

		webController.requestMakePromise(promise);

		//MemberDB.getInstance().showMembersInfo();
		//PromiseDB.getInstance().showPromisesInfo();
		
		
		ArrayList<String> promisesInfo = webController.showPromisesOfMember("Kim");
		String promiseID = promisesInfo.get(0);
		String promiseWalletAddress = PromiseDB.getInstance().getPromiseWalletAddress(promiseID);
		float promiseFund = PromiseDB.getInstance().getPromiseFund(promiseID);
		MemberDB.getInstance().transferMemberFund("Kim", promiseWalletAddress, promiseFund);
		MemberDB.getInstance().transferMemberFund("Lee", promiseWalletAddress, promiseFund);
		MemberDB.getInstance().transferMemberFund("Park", promiseWalletAddress, promiseFund);
		MemberDB.getInstance().transferMemberFund("Son", promiseWalletAddress, promiseFund);
		
		
		Debugger.log("\n");
		MemberDB.getInstance().showMembersInfo();
		Debugger.log("\n");
		PromiseDB.getInstance().showPromisesInfo();
		Debugger.log("\n");
		//BlockDB.getInstance().showBlocksInfo();
	}

}
