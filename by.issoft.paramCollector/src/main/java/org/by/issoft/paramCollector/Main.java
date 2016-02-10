package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;

public class Main {

	public static void main(String[] args) {
		//System.out.println("HELLO");
		CurrentTimeObtainer obtainer=new CurrentTimeObtainer();
		System.out.println(obtainer.getParamName());
		System.out.println(obtainer.getCurrentParamValue().getValue());
		//System.out.println(obtainer.getCurrentParamValue().getValue().getHours()+" : "+obtainer.getCurrentParamValue().getValue().getMinutes());
		System.out.println("-----------------------------------------------");

	}

}
