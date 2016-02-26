package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents param value abstract class
 * 
 * @author AlexeyLysenok
 *
 */
public abstract class ParamValueAbstract<T> implements ParamValue {

	private T value;

	public ParamValueAbstract(T value) {
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public String toString() {

		return value.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ParamValueAbstract<?> other = (ParamValueAbstract<?>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
