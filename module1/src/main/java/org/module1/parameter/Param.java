package org.module1.parameter;

public abstract class Param {
	protected String name;
	protected String command;
	protected String value;
	
	
	public Param(){
		
	}
	
	public  String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


		
	
}
