package org.module1.parameter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class TabularParam<E> extends Param<List<E>> {
	
	public TabularParam(){
		
	}
	
	public void readMyBatch(File batInfo) throws IOException {}
}
