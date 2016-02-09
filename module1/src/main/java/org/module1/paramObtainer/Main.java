package org.module1.paramObtainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.module1.parameter.Param;
import org.module1.parameter.ScalarParam;
import org.module1.parameter.TabularParam;
import org.module1.parameter.VectorParam;
import org.module1.parameter.scalar.CurrentTime;
import org.reflections.Reflections;

public class Main {
	
	private static HashSet allClasses=new HashSet();
	private static ArrayList<Param> params=new ArrayList<Param>();
	
	private static void objectsLoading(){
		Reflections reflections1 = new Reflections("org.module1.parameter.scalar");
		Reflections reflections2 = new Reflections("org.module1.parameter.vector");
		//Reflections reflections3 = new Reflections("org.module1.parameter.tabular");
		Set<Class<? extends ScalarParam>> allScalar = reflections1.getSubTypesOf(ScalarParam.class);
		Set<Class<? extends VectorParam>> allVector = reflections2.getSubTypesOf(VectorParam.class);
		//Set<Class<? extends TabularParam>> allTabular = reflections3.getSubTypesOf(TabularParam.class);
		allClasses.addAll(allScalar);
		allClasses.addAll(allVector);
		//allClasses.addAll(allTabular);
		
		
		System.out.println(allScalar.size());
		System.out.println(allVector.size());
		
		System.out.println(allClasses.size());
			try {
				Iterator<Class<? extends Param>> iterator =allClasses.iterator();
				String string=iterator.next().toString();
				String finalString=string.substring(6, string.length());
				Class c=Class.forName(finalString);
				Param param=(Param) c.newInstance();
				params.add(param);
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
		
		System.out.println(params);
		
		for(Param param:params){
			Obtainer localObtainer=new LocalObtainer();
			localObtainer.getLastParamValue(param);
			//System.out.println(param.getValue());
		
			
			for(Param par:params){
				param.valueToString();
			}
			
		}
		
	}
}
