package application;

//Class for the Customer

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private String address;
	private String plan;
	private ArrayList<String> interestedMediaList = new ArrayList<>();
	private ArrayList<String> rentedMediaList = new ArrayList<>();
	private String ID;
	private String MobileNumber;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public List<String> getInterestedMediaList() {
		return interestedMediaList;
	}

	public void setInterestedMediaList(ArrayList<String> interestedMediaList) {
		this.interestedMediaList = interestedMediaList;
	}

	public List<String> getRentedMediaList() {
		return rentedMediaList;
	}


	public void setRentedMediaList(ArrayList<String> rentedMediaList) {
		this.rentedMediaList = rentedMediaList;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", plan=" + plan + ", interestedMediaList="
				+ interestedMediaList + ", rentedMediaList=" + rentedMediaList + ", ID=" + ID + ", MobileNumber="
				+ MobileNumber + "]";
	}
	
	
	
}