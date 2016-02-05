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
import java.util.StringTokenizer;



import org.module1.parameter.Param;
import org.module1.parameter.scalar.CurrentTime;

public class LocalObtainer extends Obtainer{

	File batInfo = null;
	public ArrayList<Param> params = new ArrayList<Param>();
	public static HashMap<String, String> allParams = new HashMap<String, String>();

	public LocalObtainer() {
		
	}

	@Override
	public String getLastParamValue(Param param) {

		if(param.getValue()==null){
			getCurrentParamValue(param);
		}

		

		return param.getValue();
	}

	@Override
	public String getCurrentParamValue(Param param){
		try {
			createBatch(param);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			readBatch(param);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		dos.writeBytes("cd /d D:/Workspace/Training/project/module1/src/main/resources\n");		
		String string="ECHO "+param.getName()+">>myBatInfo.txt\n";		
		dos.writeBytes(string);
		dos.writeBytes(param.getCommand());
		dos.close();
		fos.close();
		Runtime.getRuntime().exec("cmd /c start d:/Workspace/Training/project/module1/src/main/resources/bat.bat");
	}

	private void readBatch(Param param) throws IOException{
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
	}

}
