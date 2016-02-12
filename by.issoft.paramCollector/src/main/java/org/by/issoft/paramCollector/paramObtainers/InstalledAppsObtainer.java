package org.by.issoft.paramCollector.paramObtainers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;

public class InstalledAppsObtainer extends ParamObtainer {

	private InstalledAppsValue apps = new InstalledAppsValue();
	private File appsTXT = null;

	public InstalledAppsObtainer() {

	}

	@Override
	public InstalledAppsValue getCurrentParamValue() {
		try {
			createBatch();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			readMyBatch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apps;
	}

	@Override
	public InstalledAppsValue getLastParamValue() {
		if(apps.getValue()==null){
			apps=getCurrentParamValue();
		}
		return apps;
	}

	@Override
	public String getParamName() {
		// TODO Auto-generated method stub
		return apps.paramInfo.getName();
	}
	
	@Override
	public void print(){
		System.out.println("Installed apps:");
		System.out.println(apps.getValue().toString());
		
	}

	private void createBatch() throws IOException {

		File bat = new File("D:/Workspace/Training/project/by.issoft.paramCollector/src/main/resources/appsBat.bat");
		if (appsTXT == null) {
			appsTXT = new File("D:/Workspace/Training/project/by.issoft.paramCollector/src/main/resources/appsTXT.txt");
		} else {
			PrintWriter pw = new PrintWriter(appsTXT);
			pw.flush();
			pw.close();
		}

		FileOutputStream fos = new FileOutputStream(bat);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeBytes("wmic /OUTPUT:D:\\Workspace\\Training\\project\\by.issoft.paramCollector\\src\\main\\resources\\appsTXT.txt product get name\n");
		dos.close();
		fos.close();
		Runtime.getRuntime().exec("cmd /c start d:/Workspace/Training/project/by.issoft.paramCollector/src/main/resources/appsBat.bat");
	}

	private void readMyBatch() throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(appsTXT.getAbsolutePath()), "Unicode"));
		ArrayList<String> paramValues = new ArrayList<String>();
		String line = "";
		br.readLine();
		while ((line = br.readLine()) != null && line.equals("") == false) {
			StringBuilder paramValue = new StringBuilder();
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();

				paramValue.append(word + " ");
			}
			String value = new String(paramValue);
			paramValues.add(value);
		}
		apps.setValue(paramValues);

		br.close();
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

}
