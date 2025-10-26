package com.project.obj;

public class Device {

	private String id;
	private String name;
	private String type;

	public Device() {}

	public Device(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}

}