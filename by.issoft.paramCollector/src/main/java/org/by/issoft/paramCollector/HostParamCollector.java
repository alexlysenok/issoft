package org.by.issoft.paramCollector;

import java.util.Date;

import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import org.by.issoft.paramCollector.sockets.TCPHostCommunicator;
import org.by.issoft.paramCollector.xml.ParamToCollect;

public class HostParamCollector extends ParamCollector {

	TCPHostCommunicator communicator = new TCPHostCommunicator();

	// public volatile static boolean collectingReady = false;

	public HostParamCollector(DataStorage storage, ParamToCollect collectingParam) {
		super(storage, collectingParam);
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public ParamValueAbstract<?> collect() {

		ParamValueAbstract<?> value = communicator.getValueFromHost(getCollectingParam().getParamName(), getCollectingParam().getHost());
		Param param = ObtainerRegistry.findObtainer(getCollectingParam().getParamName()).getParamInfo();
		getStorage().addToStorage(param, value, new Date(), getCollectingParam().getHost());
		setLastValue(value);
		System.out.println("put from " + getCollectingParam().getHost() + "  value: " + value + " into storage");
		return value;
	}

}
