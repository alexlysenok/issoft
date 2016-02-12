package org.by.issoft.paramCollector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.paramObtainers.FreePhysicalMemoryObtainer;
import org.by.issoft.paramCollector.paramObtainers.InstalledAppsObtainer;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;


public class Main {
	
	public static List<ParamObtainer> obtainers=new ArrayList<ParamObtainer>();
	
	//create ObtainersRegistry class that loads and provides access to list of all available Obtainers
	public static void obtainersLoading(){
		String packageName="org.by.issoft.paramCollector.paramObtainers";
		List<Class<?>> classes=ClassFinder.find(packageName);
		for(Class<?> c:classes){
    		try {
				ParamObtainer obtainer=(ParamObtainer) c.newInstance();
				obtainers.add(obtainer);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
	}

	public static void main(String[] args) {
					
		obtainersLoading();
		System.out.println(obtainers);
		for(ParamObtainer obtainer:obtainers){
			obtainer.getCurrentParamValue();
			
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(ParamObtainer obtainer:obtainers){
			obtainer.print();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		}
		
	    
		
		/*
		CurrentTimeObtainer obtainer = new CurrentTimeObtainer();
		DisksInfoObtainer obtainer2 = new DisksInfoObtainer();
		PhysicalMemoryUsageObtainer obtainer3 = new PhysicalMemoryUsageObtainer();
		FreePhysicalMemoryObtainer obtainer4 = new FreePhysicalMemoryObtainer();
		InstalledAppsObtainer obtainer5 = new InstalledAppsObtainer();

		System.out.println(obtainer.getParamName() + ":");
		Date date = obtainer.getCurrentParamValue().getValue();
		// System.out.println(date);
		System.out.println(date.getHours() + " : " + date.getMinutes());
		System.out.println("-----------------------------------------------");

		System.out.println(obtainer2.getParamName() + ":");
		List<DisksInfoValue.Disk> array = obtainer2.getCurrentParamValue().getValue();
		for (DisksInfoValue.Disk disk : array) {
			System.out.println(disk);
		}
		System.out.println("-----------------------------------------------");

		System.out.println(obtainer3.getParamName() + ":");
		Long info = obtainer3.getCurrentParamValue().getValue();
		System.out.println(info + " Bytes");
		System.out.println("-----------------------------------------------");

		System.out.println(obtainer4.getParamName() + ":");
		Long info2 = obtainer4.getCurrentParamValue().getValue();
		System.out.println(info2 + " Bytes");
		System.out.println("-----------------------------------------------");

		System.out.println(obtainer5.getParamName() + ":");
		List<String> apps = obtainer5.getCurrentParamValue().getValue();
		System.out.println(apps);
		*/
	}

}
