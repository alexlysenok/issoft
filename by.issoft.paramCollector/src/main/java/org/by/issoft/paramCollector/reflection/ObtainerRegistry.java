package org.by.issoft.paramCollector.reflection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.by.issoft.paramCollector.ParamObtainer;

@Component
public class ObtainerRegistry implements Registry {

	@Autowired
	private List<ParamObtainer<?>> obtainers;

	public ObtainerRegistry() {
	}

	public List<ParamObtainer<?>> getObtainers() {

		return obtainers;

	}

	public boolean hasObtainer(String paramName) {
		obtainers = getObtainers();
		boolean b = false;
		for (ParamObtainer<?> paramObtainer : obtainers) {
			if (paramObtainer.getParamInfo().getName().equals(paramName)) {
				b = true;
			}
		}
		return b;
	}

	public ParamObtainer<?> findObtainer(String paramName) {
		obtainers = getObtainers();
		ParamObtainer<?> obtainer = null;
		for (ParamObtainer<?> paramObtainer : obtainers) {
			if (paramObtainer.getParamInfo().getName().equals(paramName)) {
				obtainer = paramObtainer;
			}
		}
		return obtainer;
	}

}
