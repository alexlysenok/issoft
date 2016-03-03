package org.by.issoft.paramCollector;

import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.dataStorage.MemoryStorage;
import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import org.by.issoft.paramCollector.xml.MyXMLParser;
import org.by.issoft.paramCollector.xml.ParamToCollect;
import org.by.issoft.paramCollector.xml.ParamsToCollect;

/**
 * 
 * Executes threads for obtaining all params from xml and saves them to the
 * setted storage
 * 
 * @author AlexeyLysenok
 *
 */

public class CollectionExecutor {

	DataStorage storage = new MemoryStorage();
	List<ParamCollector> threads = new ArrayList<>();

	public void setStorage(DataStorage storage) {
		this.storage = storage;
	}

	public void collect() {

		ParamsToCollect paramsToCollect = MyXMLParser.parseXML();

		for (ParamToCollect paramToCollect : paramsToCollect.getParams()) {
			if (ObtainerRegistry.hasObtainer(paramToCollect.getParamName())) {
				// ParamCollector paramCollector = new ParamCollector(storage,
				// ObtainerRegistry.findObtainer(paramToCollect.getParamName()),
				// paramToCollect.getDuration(), paramToCollect.getFrequency());
				// threads.add(paramCollector);

			}
		}

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
