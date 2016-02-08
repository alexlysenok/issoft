package org.module1.paramObtainer;

import org.module1.parameter.Param;

public abstract class Obtainer {
	public abstract Object getCurrentParamValue(Param param);
	public abstract Object getLastParamValue(Param param);
	public abstract String getParamName(Param param);
	
	
	
	
}
