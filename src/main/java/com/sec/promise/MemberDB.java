package com.sec.promise;

import java.util.ArrayList;

/**
 * Singleton class
 * 
 * This class is Member database
 * 1st goal: Don't use database. Keep Member on the memory
 * 2nd goal: Save Member data into file with JASON format
 * 3rd goal: Save Member data in the database and get data from the database(SQLite)
 */
public class MemberDB {

	private static MemberDB instance = new MemberDB();
	private ArrayList<Member> members = new ArrayList<Member>();
	
	private MemberDB() {}
	
	public static MemberDB getInstance() {
		return instance;
	}
	
	public void signUpMember(String name, String password) {
		members.add(new Member(name, password));
	}

	/**
	 * After the new promise created, participants of promise need to
	 * enroll this promise to their promise list
	 * 
	 * @param promiseID: promise which participants need to enroll
	 * @param participants: participants of promise who need to enroll promise themselves
	 * @return void
	 */
	public void enrollPromiseToMember(String promiseID, ArrayList<String> participants) {
		for(String participantName : participants) {
			for(Member member: members) {
				String memberName = member.getName();
				if(participantName.equals(memberName)) {
					member.addPromise(promiseID);
					break;
				}
			}
		}
	}

	public ArrayList<String> getMemberPromises(String name) {
		ArrayList<String> info = new ArrayList<String>();
		for(Member member : members) {
			if(member.getName().equals(name)) {
				info = member.getPromises();
			}
		}
		return info;
	}

	public boolean signIn(String name, String password) {
		for(Member member: members) {
			if(member.validSignIn(name, password)) {
				return true;
			}
		}
		return false;
	}

	public boolean transferMemberFund(String memberName, String promiseWalletAddress, float promiseFund) {
		for(Member member: members) {
			if(member.getName().equals(memberName)) {
				member.transferFund(promiseWalletAddress, promiseFund);
				return true;
			}
		}
		return false;
	}

	public void putMemberUtxo(String receiverAddress, String transactionHash, float value) {
		for(Member member: members) {
			if(member.getWalletAddress().equals(receiverAddress)) {
				member.addWalletUtxo(transactionHash, value);
			}
		}
	}
	
	public void showMembersInfo() {
		Debugger.log();
		Debugger.log("Information of Members");
		for(Member member: members) {
			Debugger.log(member);
		}
		Debugger.log();
	}

}
