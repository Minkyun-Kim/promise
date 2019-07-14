package com.sec.promise;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WalletTest {

	@Test
	public void testTransfer() {
		WebController webController = new WebController();
		
		webController.signUp("Kim", "Kim");
		webController.signUp("Lee", "Lee");
		webController.signUp("Park", "Park");
		webController.signUp("Son", "Son");
		
		//MemberDB.getInstance().showMembersInfo();
		BlockDB.getInstance().showBlocksInfo();

		Promise promise = new Promise("2019-08-09", "Digital City", (float)10000.0 );
		ArrayList<String> participants = new ArrayList<String>();
		participants.add("Kim");
		participants.add("Lee");
		participants.add("Park");
		participants.add("Son");
		promise.setParticipants(participants);
		webController.requestMakePromise(promise);

		
		//MemberDB.getInstance().transferMemberFund("Kim", promiseWalletAddress, promiseFund);
		
		
		
	}

}
