package org.module1.parameter;

public abstract class Param<MyType> {
	protected String name;
	protected String[] commands;
	protected MyType value;
	
	
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

	public MyType getValue() {
		return value;
	}

	public void setValue(MyType value) {
		this.value = value;
	}
	
	public void valueToString(){
		System.out.println(value.toString());
	}


		
	
}
