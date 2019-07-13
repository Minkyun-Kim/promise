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

		
		System.err.println("Kim's promises");
		webController.requestMakePromise(promise1);
		ArrayList<String> promisesInfo = webController.showPromisesOfMember("Kim");
		for(String info: promisesInfo) {
			System.err.println(info);
		}
		System.err.println();
		
		
		System.err.println("Choi's promises");
		webController.requestMakePromise(promise2);
		promisesInfo = webController.showPromisesOfMember("Choi");
		for(String info: promisesInfo) {
			System.err.println(info);
		}
		System.err.println();
	}
	
	@Test
	public void testJoinPromise() {
		WebController webController = new WebController();

		webController.signUp("Kim",  "Kim");
		webController.signUp("Lee",  "Lee");

		Promise promise = new Promise("2019-08-09", "Digital City", (float)10000.0 ) ;

		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Kim");
		participants.add("Lee");
		promise.setParticipants(participants);

		webController.requestMakePromise(promise);
		ArrayList<String> promisesInfo = webController.showPromisesOfMember("Kim");

		MemberDB.getInstance().showMembersInfo();
		PromiseDB.getInstance().showPromisesInfo();
	}

}
