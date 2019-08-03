package com.sec.requestparam;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromiseInfo {

	private String date;
	private String location;
	private float fund;
	private ArrayList<String> participants;

	public String toString() {
		String info = new Gson().toJson(this);
		return info;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getFund() {
		return fund;
	}
	public void setFund(float fund) {
		this.fund = fund;
	}
	public ArrayList<String> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<String> participants) {
		this.participants = participants;
	}
	

}
