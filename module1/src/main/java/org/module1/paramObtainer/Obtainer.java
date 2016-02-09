package org.module1.paramObtainer;

import org.module1.parameter.Param;

public abstract class Obtainer{
	
	
	public abstract String getParamName(Param param);
	public abstract <T> T getLastParamValue(Param<T> param) ;
	public abstract <T> T getCurrentParamValue(Param<T> param);
}
