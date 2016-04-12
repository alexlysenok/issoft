package org.by.issoft.paramCollector.sockets;

import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Qualifier("TCPHostCommunicator")
@Scope("prototype")
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
