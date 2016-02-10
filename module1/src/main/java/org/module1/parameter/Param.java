package org.module1.parameter;

public abstract class Param<E> {
	protected String name;
	protected String[] commands;
	protected E value;
	
	
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

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}
	
	public void valueToString(){
		System.out.println(value.toString());
	}


		
	
}
