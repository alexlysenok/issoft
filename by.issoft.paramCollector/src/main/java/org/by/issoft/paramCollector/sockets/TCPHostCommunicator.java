package org.by.issoft.paramCollector.sockets;

import org.by.issoft.paramCollector.params.ParamValueAbstract;

public class TCPHostCommunicator implements HostCommunicator {

	@Override
	public ParamValueAbstract<?> getValueFromHost(String paramName, String hostName) {
		ParamValueAbstract<?> value = null;

		try {
			Client client = new Client(hostName);
			value = client.paramRequest(paramName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return value;
	}

}
