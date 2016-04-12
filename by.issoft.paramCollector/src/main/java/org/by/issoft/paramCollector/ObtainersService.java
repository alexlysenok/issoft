package org.by.issoft.paramCollector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObtainersService {

	@Autowired
	private List<ParamObtainer<?>> obtainers;

	public ObtainersService() {
		// TODO Auto-generated constructor stub
	}

	public List<ParamObtainer<?>> getObtainers() {

		// return new ArrayList<>(obtainers);
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
