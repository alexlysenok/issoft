package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.dataStorage.DataBaseStorage;;

/**
 * 
 * @author AlexeyLysenok
 *
 **/

public class Main {

	public static void main(String[] args) {

		CollectionExecutor paramCollector = new CollectionExecutor();
		paramCollector.setStorage(new DataBaseStorage());
		paramCollector.collect();

	}

}
