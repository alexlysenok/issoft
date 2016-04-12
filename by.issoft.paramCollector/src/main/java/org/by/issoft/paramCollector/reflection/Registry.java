package org.by.issoft.paramCollector.reflection;

import java.util.List;

import org.by.issoft.paramCollector.ParamObtainer;

public interface Registry {

	boolean hasObtainer(String paramName);

	ParamObtainer<?> findObtainer(String paramName);

	List<ParamObtainer<?>> getObtainers();

}
