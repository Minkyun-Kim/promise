package com.sec.promise;

import java.util.ArrayList;

public class ServiceDB {
	private static ServiceDB instance = new ServiceDB();
	private ArrayList<Service> services = new ArrayList<Service>();
	
	private ServiceDB() {}
	
	public static ServiceDB getInstance() {
		return instance;
	}
	
	public void makeService(String name, float price) {
		services.add(new Service(name, price));
	}

	public void showServiceInfo() {
		Debugger.log();
		Debugger.log("Information of Services");
		for(Service service: services) {
			Debugger.log(service);
		}
		Debugger.log();
	}

	public ArrayList<String> getServiceList() {
		ArrayList<String> serviceList = new ArrayList<String>();
		for(Service service : services) {
			serviceList.add(service.toString());
		}
		return serviceList;
	}

	public String getServiceWalletAddress(String serviceName) {
		for(Service service: services) {
			if(service.getName().equals(serviceName)) {
				return service.getWalletAddress();
			}
		}
		return null;
	}

	public float getServicePrice(String serviceName) {
		for(Service service: services) {
			if(service.getName().equals(serviceName)) {
				return service.getPrice();
			}
		}
		
		return (float) 0.0;
	}

	public void putMemberUtxo(String receiverAddress, String transactionHash, float value) {
		for(Service service: services) {
			if(service.getWalletAddress().equals(receiverAddress)) {
				service.addWalletUtxo(transactionHash, value);
			}
		}
	}
}
