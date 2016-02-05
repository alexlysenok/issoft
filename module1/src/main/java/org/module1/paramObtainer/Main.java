package org.module1.paramObtainer;

import java.io.IOException;

import org.module1.parameter.Param;
import org.module1.parameter.scalar.CurrentTime;

public class Main {
	public static void main(String[] args) {
		Param param=new CurrentTime();
		LocalObtainer localObtainer=new LocalObtainer();
		localObtainer.getLastParamValue(param);
		System.out.println(param.getValue());
		try {
			Thread.sleep(70000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		localObtainer.getCurrentParamValue(param);
		System.out.println(param.getValue());
		System.out.println(LocalObtainer.allParams.toString());
	}
}
