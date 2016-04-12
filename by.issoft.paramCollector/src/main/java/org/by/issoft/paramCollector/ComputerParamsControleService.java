package org.by.issoft.paramCollector;

import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.reflection.Registry;
import org.by.issoft.paramCollector.xml.MyParser;
import org.by.issoft.paramCollector.xml.ParamToCollect;
import org.by.issoft.paramCollector.xml.ParamsToCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * Executes threads for obtaining all params from xml and saves them to the
 * setted storage
 * 
 * @author AlexeyLysenok
 *
 */
@Component
public class ComputerParamsControleService implements ParamsControleService {

	@Autowired
	@Qualifier("dataBaseStorage")
	private DataStorage storage;

	@Autowired
	private MyParser parser;

	@Autowired
	private Registry registry;

	private List<ParamCollector> threads = new ArrayList<>();

	@Value("${urls.xml}")
	private String XML_URL;

	public void changeStorage(DataStorage storage) {
		this.storage = storage;
	}

	public void changeParser(MyParser parser) {
		this.parser = parser;
	}

	@Override
	public void startCollecting() {

		ParamsToCollect paramsToCollect = parser.parse(XML_URL);

		if (paramsToCollect != null) {

			for (ParamToCollect paramToCollect : paramsToCollect.getParams()) {

				if (registry.hasObtainer(paramToCollect.getParamName())) {

					ParamCollector paramCollector = selectParamCollector(paramToCollect);

					threads.add(paramCollector);

				} else {
					System.out.println("Can't collect " + paramToCollect.getParamName() + ". Obtainer for this param doesn't exsist");
				}
			}
		} else {
			System.out.println("Problems with parsing.");
		}
	}

	private ParamCollector selectParamCollector(ParamToCollect paramToCollect) {
		ParamCollector paramCollector = null;
		if (paramToCollect.getHost().equalsIgnoreCase("localhost")) {

			paramCollector = new LocalParamCollector(storage, registry.findObtainer(paramToCollect.getParamName()), paramToCollect);

		} else {
			paramCollector = new HostParamCollector(storage, paramToCollect);
		}

		return paramCollector;

	}

	@Override
	public void tooglePause(ParamToCollect param) {
		for (int i = 0; i < threads.size(); i++) {
			if (threads.get(i).getCollectingParam().equals(param)) {
				if (threads.get(i).isPaused()) {
					threads.get(i).resumeCollecting();
				} else {
					threads.get(i).pauseCollecting();
				}
			}
		}
	}

	public List<ParamCollector> getThreads() {
		return threads;
	}

}
