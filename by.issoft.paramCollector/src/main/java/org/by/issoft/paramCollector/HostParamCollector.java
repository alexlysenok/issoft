package org.by.issoft.paramCollector;

import java.util.Date;

import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import org.by.issoft.paramCollector.sockets.HostCommunicator;
import org.by.issoft.paramCollector.sockets.TCPHostCommunicator;
import org.by.issoft.paramCollector.xml.ParamToCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HostParamCollector extends ParamCollector {

	@Autowired
	HostCommunicator communicator = new TCPHostCommunicator();

	// public volatile static boolean collectingReady = false;

	public HostParamCollector(DataStorage storage, ParamToCollect collectingParam) {
		super(storage, collectingParam);
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public ParamValueAbstract<?> collect() {

		ParamValueAbstract<?> value = communicator.getValueFromHost(getCollectingParam().getParamName(), getCollectingParam().getHost());
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		ObtainerRegistry registry = (ObtainerRegistry) context.getBean("obtainerRegistry");
		Param param = registry.findObtainer(getCollectingParam().getParamName()).getParamInfo();
		getStorage().addToStorage(param, value, new Date(), getCollectingParam().getHost());
		setLastValue(value);
		System.out.println("put from " + getCollectingParam().getHost() + "  value: " + value + " into storage");
		return value;
	}

}
