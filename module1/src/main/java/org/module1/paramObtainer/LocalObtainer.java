package org.module1.paramObtainer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.StringTokenizer;



import org.module1.parameter.Param;
import org.module1.parameter.ScalarParam;
import org.module1.parameter.TabularParam;
import org.module1.parameter.VectorParam;
import org.module1.parameter.scalar.CurrentTime;

public class LocalObtainer extends Obtainer{

	File batInfo = null;
	public ArrayList<Param> params = new ArrayList<Param>();
	public static HashMap<String, Object> allParams = new HashMap<String, Object>();

	public LocalObtainer() {
		
	}

	@Override
	public Object getLastParamValue(Param param) {

		if(param.getValue()==null){
			getCurrentParamValue(param);
		}
		return param.getValue();
	}

	@Override
	public Object getCurrentParamValue(Param param){
		try {
			createBatch(param);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        if(param instanceof ScalarParam){
        	try {
				readScalar(param);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else if (param instanceof VectorParam) {
			try {
				readVector(param);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (param instanceof TabularParam){
			try {
				readTabular(param);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		
		return param.getValue();
	}

	@Override
	public String getParamName(Param param){


		return param.getName();
	}

	private void createBatch(Param param) throws IOException {
		File bat = new File("D:/Workspace/Training/project/module1/src/main/resources/bat.bat");
		if(batInfo==null){
			batInfo=new File("D:/Workspace/Training/project/module1/src/main/resources/myBatInfo.txt");
		}
		
		PrintWriter pw = new PrintWriter(batInfo);
		pw.flush();
		pw.close();
		FileOutputStream fos = new FileOutputStream(bat);
		DataOutputStream dos = new DataOutputStream(fos);
		for(String s:param.getCommands()){
			dos.writeBytes(s);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		dos.close();
		fos.close();		
		Runtime.getRuntime().exec("cmd /c start d:/Workspace/Training/project/module1/src/main/resources/bat.bat");
	}

	private void readScalar(Param param) throws IOException{
		StringBuilder  paramValue=new StringBuilder();		
		BufferedReader br=new BufferedReader(new FileReader(batInfo.getAbsolutePath()));
		String line="";
		while ((line=br.readLine())!=null) {
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			while(tokenizer.hasMoreTokens()){
				String word=tokenizer.nextToken();
				
				if (word.equals(param.getName())) {
					continue;
				}
				paramValue.append(word+" ");
			}			
		}
		br.close();
		
		String value=new String(paramValue);
		
		param.setValue(value);
		if(!allParams.containsKey(param.getName())){
			allParams.put(param.getName(), param.getValue());
		}else{
			allParams.put(param.getName(), value);
		}
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		
	}
	
	private void readVector(Param param) throws IOException{
		ArrayList<String> paramValues=new ArrayList<String>();
		BufferedReader br=new BufferedReader(new FileReader(batInfo.getAbsolutePath()));
		String line="";
		while ((line=br.readLine())!=null) {
			if (line.equals("Name") || line.equals("")) {
				continue;
			}
			paramValues.add(line);
		}
		br.close();
		
		ListIterator<String> iterator=paramValues.listIterator(0);
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}		
		param.setValue(paramValues);
		//System.out.println(paramValues.toString());
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}
	
	private void readTabular(Param param) throws IOException{
		
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

}
