package org.module1.paramObtainer;

import java.io.BufferedReader; 
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;



import org.module1.parameter.Param;
import org.module1.parameter.ScalarParam;
import org.module1.parameter.TabularParam;
import org.module1.parameter.VectorParam;
import org.module1.parameter.scalar.CurrentTime;
import org.module1.parameter.tabular.DiskInfo;

public class LocalObtainer extends Obtainer{

	File batInfo = null;
	public ArrayList<Param> params = new ArrayList<Param>();
	

	public LocalObtainer() {}
	
	
	@Override
	public <T> T getLastParamValue(Param<T> param) {

		if(param.getValue()==null){
			getCurrentParamValue(param);
		}
		return param.getValue();
	}


	
	
	
	@Override
	public <T> T getCurrentParamValue(Param<T> param){
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
				((TabularParam) param).readMyBatch(batInfo);
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
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");		
	}
	
	private void readVector(Param param) throws IOException{
		ArrayList<String> paramValues=new ArrayList<String>();
		
		

		BufferedReader br = new BufferedReader(new InputStreamReader(
			    new FileInputStream(batInfo.getAbsolutePath()), "Unicode"));


		//FileReader fr=new FileReader(batInfo.getAbsolutePath());
		//System.out.println(fr.getEncoding());
		//BufferedReader br=new BufferedReader(fr);
		//String line = new String("".getBytes(), "Unicode");
		String line="";
		br.readLine();
		while ((line=br.readLine())!=null && line.equals("")==false) {
			StringBuilder  paramValue=new StringBuilder();
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			while(tokenizer.hasMoreTokens()){
				String word=tokenizer.nextToken();				
				if (word.equals(param.getName())) {
					continue;
				}
				paramValue.append(word+" ");
			}
			//byte[] byteLine=line.getBytes();
			//String thisLine=new String(byteLine, Charset.forName("Unicode"));
			String value=new String(paramValue);
			paramValues.add(value);
		}
		br.close();
		
		ListIterator<String> iterator=paramValues.listIterator(0);
		
		param.setValue(paramValues);
		//System.out.println(paramValues.toString());
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}
	
	private void readTabular(Param param) throws IOException{
			
		BufferedReader br=new BufferedReader(new FileReader(batInfo.getAbsolutePath()));
		String line="";
		br.readLine();
		ArrayList<Object> objects=new ArrayList<Object>();
		while ((line=br.readLine())!=null) {
			ArrayList<String> fields=new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			while(tokenizer.hasMoreTokens()){
				String word=tokenizer.nextToken();
				fields.add(word);
				
			}
			
		} 
		br.close();
		
		
		
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

}
