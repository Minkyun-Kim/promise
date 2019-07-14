package com.sec.promise;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Wallet {

	private PrivateKey privateKey;
	private PublicKey publicKey;
	private String address;
	private Utxo utxos;
	
	/**
	 * make Public/Private Key
	 * make Wallet Address
	 */
	public Wallet() {
		
		KeyPair keyPair = Util.generateKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
		this.address = this.publicKey.toString();
		this.utxos = new Utxo();
		
	}
	
	/**
	 * 처음 계정 생성시 10coin 증정
	 * @param d
	 */
	public void setCoin(String name, float value) {
		Transaction transaction = new Transaction(name + " SignUp Coin", "", this.address, value);
		
		utxos.addTransactionValue(Util.getObjectHash(transaction), value);
		BlockDB.getInstance().queueTransaction(transaction);
	}

	/**
	 * TODO utxo가 찢어지는 경우가 없도록 구현되어있음. 후에 찢어질 경우도 생각해서 수정해야함
	 * @param receiverAddress
	 * @param value
	 */
	public void transfer(String content, String receiverAddress, float value) {
		ArrayList<Pair<String, Float>> utxoList = new ArrayList<Pair<String,Float>>();
		for(float toSendMoney = value; toSendMoney > 0;) {

			Debugger.log("toSendMoney: " + toSendMoney);

			Pair<String, Float> firstUtxo = utxos.getOneUtxo();
			toSendMoney -= firstUtxo.second;

			Debugger.log("firstUxto value:  " + firstUtxo.second);

			utxoList.add(firstUtxo);
		}
		Transaction transaction = new Transaction(content, this.address, receiverAddress, value);
		
		//블록 큐에 저장
		BlockDB.getInstance().queueTransaction(transaction);
		
		
		Debugger.log("ReceiverAddress: " + receiverAddress);

		//receiver 계좌 utxo에 저장
		if(PromiseDB.getInstance().findAddress(receiverAddress)){
			//공용계좌에 회비로 보내는 경우
			PromiseDB.getInstance().putPromiseUtxo(receiverAddress, Util.getObjectHash(transaction), value);
		}
		else if(MemberDB.getInstance().findAddress(receiverAddress)){
			//사람에게 보내는 경우
			MemberDB.getInstance().putMemberUtxo(receiverAddress, Util.getObjectHash(transaction), value);
			
		}
		else {
			ServiceDB.getInstance().putMemberUtxo(receiverAddress, Util.getObjectHash(transaction), value);
		}
		
	}

	public String getAddress() {
		return address;
	}

	public void addUtxo(String transactionHash, float value) {
		this.utxos.addTransactionValue(transactionHash, value);
	}

	public String toString() {
		String info = "PrivateKey: " + privateKey
				+ "\nPublicKey: " + publicKey
				+ "\nAddress: " + address;
		
		ArrayList<Pair<String, Float>> balance = utxos.getUtxos();
		float totalBalance = (float)0.0;
		for(Pair<String, Float> utxo: balance) {
			info += "\nTransactionID: " + utxo.first
					+ "   Value: " + utxo.second;

			Debugger.log("utxo fund: " + utxo.second);

			totalBalance += utxo.second;
		}
		info += "\nTotal Value: " + totalBalance;
		return info;
	}


}
