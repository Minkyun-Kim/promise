package com.sec.requestparam;

import com.google.gson.Gson;

public class JoinPromiseInfo {

	private String name;
	private String promiseId;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPromiseId() {
		return promiseId;
	}
	public void setPromiseId(String promiseId) {
		this.promiseId = promiseId;
	}
	

}
