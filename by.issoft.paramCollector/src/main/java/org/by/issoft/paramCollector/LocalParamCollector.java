package org.by.issoft.paramCollector;

import java.util.Date;

import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.xml.ParamToCollect;

/**
 * 
 * represents thread that obtains param and puts it into the storage
 * 
 * @author AlexeyLysenok
 *
 */

public class LocalParamCollector extends ParamCollector {

	private ParamObtainer<?> obtainer;
	// private volatile long pauseTime = 0L;

	public LocalParamCollector(DataStorage storage, ParamObtainer<?> obtainer, ParamToCollect collectingParam) {

		super(storage, collectingParam);
		this.obtainer = obtainer;
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public ParamValueAbstract<?> collect() {

		ParamValueAbstract<?> value = (ParamValueAbstract<?>) obtainer.getCurrentParamValue();

		getStorage().addToStorage(obtainer.getParamInfo(), value, new Date(), getCollectingParam().getHost());
		setLastValue(value);
		System.out.println("from " + getCollectingParam().getHost() + "  value: " + value);
		return value;

	}

}
