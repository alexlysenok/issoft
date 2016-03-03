package org.by.issoft.paramCollector.xml;

public class ParamToCollect {

	private long duration;
	private long frequency;
	private String paramName;

	public ParamToCollect(long duration, long frequency, String paramName) {
		this.duration = duration;
		this.frequency = frequency;
		this.paramName = paramName;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String toString() {
		return "[duration=" + duration + ", frequency=" + frequency + ", paramName=" + paramName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + (int) (frequency ^ (frequency >>> 32));
		result = prime * result + ((paramName == null) ? 0 : paramName.hashCode());
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
		ParamToCollect other = (ParamToCollect) obj;
		if (duration != other.duration)
			return false;
		if (frequency != other.frequency)
			return false;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		return true;
	}

}
