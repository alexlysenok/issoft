package org.by.issoft.paramCollector.xml;

public class ParamToCollect {

	private long frequency;
	private String paramName;
	private String host;
	private String hostWithoutDots;

	public ParamToCollect(long frequency, String paramName, String host) {
		this.frequency = frequency;
		this.paramName = paramName;
		this.host = host;

	}

	public ParamToCollect() {
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
		return "[" + "frequency:" + frequency + ", paramName:" + paramName + ", host:" + host + "]";
	}

	public String getHost() {
		return host;
	}

	public String convertToRealHost(String s) {
		String string = s.replace('x', '.');
		return string;
	}

	public String convertToHostWithoutDots(String s) {
		String string = s.replace('.', 'x');
		return string;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (frequency ^ (frequency >>> 32));
		result = prime * result + ((host == null) ? 0 : host.hashCode());
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
		if (frequency != other.frequency)
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		return true;
	}

	public String getHostWithoutDots() {
		return hostWithoutDots;
	}

	public void setHostWithoutDots(String hostWithDots) {
		String hostWithoutDots = convertToHostWithoutDots(hostWithDots);
		this.hostWithoutDots = hostWithoutDots;
	}

}
