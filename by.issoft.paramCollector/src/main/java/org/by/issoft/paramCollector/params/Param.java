package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents param info
 * 
 * @author AlexeyLysenok
 *
 */

public class Param {

	private String name;
	private ParamType type;

	public Param(String name, ParamType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public ParamType getType() {
		return type;
	}

	@Override
	public String toString() {
		String string = name + " - " + type;
		return string;
	}
}
