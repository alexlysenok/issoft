package org.module1.parameter;

public abstract class Param {
	protected String name;
	protected String[] commands;
	protected Object value;
	
	
	public Param(){
		
	}
	
	public  String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCommands() {
		return commands;
	}
	public void setCommands(String[] commands) {
		this.commands = commands;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}


		
	
}
