package org.module1.paramObtainer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.module1.parameter.Param;
import org.module1.parameter.ScalarParam;
import org.module1.parameter.TabularParam;
import org.module1.parameter.VectorParam;
import org.module1.parameter.scalar.CurrentTime;
import org.module1.parameter.tabular.DiskInfo;
import org.module1.parameter.tabular.DiskInfo.Disk;
import org.module1.parameter.vector.InstalledApps;
import org.reflections.Reflections;

public class Main {

	private static HashSet allClasses = new HashSet();
	private static HashSet scalarClasses = new HashSet();
	private static HashSet vectorClasses = new HashSet();
	private static HashSet tabularClasses = new HashSet();
	private static ArrayList<Param> params = new ArrayList<Param>();
	private static ArrayList<VectorParam> vectorParams = new ArrayList<VectorParam>();
	private static ArrayList<ScalarParam> scalarParams = new ArrayList<ScalarParam>();
	private static ArrayList<TabularParam> tabularParams = new ArrayList<TabularParam>();


	private static void objectsLoading() {
		Reflections reflections1 = new Reflections("org.module1.parameter.scalar");
		Reflections reflections2 = new Reflections("org.module1.parameter.vector");
		// Reflections reflections3 = new Reflections("org.module1.parameter.tabular");
		Set<Class<? extends ScalarParam>> allScalar = reflections1.getSubTypesOf(ScalarParam.class);
		Set<Class<? extends VectorParam>> allVector = reflections2.getSubTypesOf(VectorParam.class);
		// Set<Class<? extends TabularParam>> allTabular = reflections3.getSubTypesOf(TabularParam.class);
		allClasses.addAll(allScalar);
		allClasses.addAll(allVector);
		// allClasses.addAll(allTabular);

		try {

			Iterator<Class<? extends Param>> iterator = allClasses.iterator();
			while (iterator.hasNext()) {
				String string = iterator.next().toString();
				String finalString = string.substring(6, string.length());
				Class c = Class.forName(finalString); 
				Param param = (Param) c.newInstance();
				params.add(param);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void obtainScalarParams() {
		Reflections reflections1 = new Reflections("org.module1.parameter.scalar");
		Set<Class<? extends ScalarParam>> allScalar = reflections1.getSubTypesOf(ScalarParam.class);
		try {

			Iterator<Class<? extends ScalarParam>> iterator = allScalar.iterator();
			while (iterator.hasNext()) {
				String string = iterator.next().toString();
				String finalString = string.substring(6, string.length());
				Class c = Class.forName(finalString);
				
				ScalarParam param = (ScalarParam) c.newInstance();
				scalarParams.add(param);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
	}

	public static void obtainVectorParams() {
		Reflections reflections2 = new Reflections("org.module1.parameter.vector");
		Set<Class<? extends VectorParam>> allVector = reflections2.getSubTypesOf(VectorParam.class);
		try {

			Iterator<Class<? extends VectorParam>> iterator = allVector.iterator();
			while (iterator.hasNext()) {
				String string = iterator.next().toString();
				String finalString = string.substring(6, string.length());
				Class c = Class.forName(finalString);
				VectorParam param = (VectorParam) c.newInstance();
				vectorParams.add(param);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void obtainTabularParams() {
		Reflections reflections3 = new Reflections("org.module1.parameter.tabular");
		Set<Class<? extends TabularParam>> allTabular = reflections3.getSubTypesOf(TabularParam.class);
		try {

			Iterator<Class<? extends TabularParam>> iterator = allTabular.iterator();
			while (iterator.hasNext()) {
				String string = iterator.next().toString();
				String finalString = string.substring(6, string.length());
				Class c = Class.forName(finalString);
				TabularParam param = (TabularParam) c.newInstance();
				tabularParams.add(param);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		objectsLoading();

		Obtainer localObtainer = new LocalObtainer();
		
		
		
		
		
		CurrentTime time=new CurrentTime();
		System.out.println(localObtainer.getCurrentParamValue(time).getClass());
		String string=localObtainer.getCurrentParamValue(time);
		System.out.println(localObtainer.getParamName(time)+ ": ");
		System.out.println(string);
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		
		
		InstalledApps apps=new InstalledApps();
		System.out.println(localObtainer.getCurrentParamValue(apps).getClass());
		List<String> strings=localObtainer.getCurrentParamValue(apps);
		System.out.println(localObtainer.getParamName(apps)+ ": ");
		System.out.println(strings);		
		
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		
		DiskInfo diskinfo=new DiskInfo();
		//System.out.println(localObtainer.getCurrentParamValue(diskinfo).getClass());
		//System.out.println(diskinfo.getValue().toString());
		System.out.println(localObtainer.getParamName(diskinfo)+ ": ");
		List<DiskInfo.Disk> disks=localObtainer.getCurrentParamValue(diskinfo);		
		for(DiskInfo.Disk d:disks){
			System.out.println(d);
		}
		
		
		
		/*
		for (Param param : params) {
			
			if(param instanceof ScalarParam){
				System.out.println(param.getClass());
				org.module1.parameter.scalar.CurrentTime obj=new org.module1.parameter.scalar.CurrentTime();
                
				String string1=localObtainer.getCurrentParamValue(obj);
			}
			
			if(param instanceof ScalarParam){
				
				//List<String> list=localObtainer.getCurrentParamValue(param);
			}
			
		
			
		}
		*/

	}
}
