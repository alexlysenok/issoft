package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents param info
 * 
 * @author AlexeyLysenok
 *
 */

public class ParamInfo {

	private String name;
	private ParamType type;

	public ParamInfo() {
	}

	public ParamInfo(String name, ParamType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParamType getType() {
		return type;
	}

	public void setType(ParamType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String string = name + " - " + type;
		return string;
	}
}
