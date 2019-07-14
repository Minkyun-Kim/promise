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
	 * ó�� ���� ������ 10coin ����
	 * @param d
	 */
	public void setCoin(String name, float value) {
		Transaction transaction = new Transaction(name + " SignUp Coin", "", this.address, value);
		
		utxos.addTransactionValue(Util.getObjectHash(transaction), value);
		BlockDB.getInstance().queueTransaction(transaction);
	}

	/**
	 * TODO utxo�� �������� ��찡 ������ �����Ǿ�����. �Ŀ� ������ ��쵵 �����ؼ� �����ؾ���
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
		
		//��� ť�� ����
		BlockDB.getInstance().queueTransaction(transaction);
		
		
		Debugger.log("ReceiverAddress: " + receiverAddress);

		//receiver ���� utxo�� ����
		if(PromiseDB.getInstance().findAddress(receiverAddress)){
			//������¿� ȸ��� ������ ���
			PromiseDB.getInstance().putPromiseUtxo(receiverAddress, Util.getObjectHash(transaction), value);
		}
		else if(MemberDB.getInstance().findAddress(receiverAddress)){
			//������� ������ ���
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
