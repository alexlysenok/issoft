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
	private Class<?> paramClass;

	public Param(String name, ParamType type, Class<?> paramClass) {
		this.name = name;
		this.type = type;
		this.paramClass = paramClass;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Param other = (Param) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public Class<?> getParamClass() {
		return paramClass;
	}

	public void setParamClass(Class<?> paramClass) {
		this.paramClass = paramClass;
	}

	public void setName(String name) {
		this.name = name;
	}
}
