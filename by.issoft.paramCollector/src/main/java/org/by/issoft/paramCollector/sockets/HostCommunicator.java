package org.by.issoft.paramCollector.sockets;

import org.by.issoft.paramCollector.params.ParamValueAbstract;

public interface HostCommunicator {
	public ParamValueAbstract<?> getValueFromHost(String paramName, String hostName);
}
