package org.by.issoft.paramCollector.paramObtainers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamInfo;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;

public class InstalledAppsObtainer extends ParamObtainer {

	private InstalledAppsValue currentValue = new InstalledAppsValue();
	private InstalledAppsValue lastValue = new InstalledAppsValue();

	private File appsTXT = null;
	private Properties properties = new Properties();

	public InstalledAppsObtainer() {
		paramInfo = new ParamInfo("INSTALLED_APPS", ParamType.VECTOR);
	}

	@Override
	public InstalledAppsValue getCurrentParamValue() {

		createBatFile();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parseBatFile();
		return currentValue;
	}

	@Override
	public InstalledAppsValue getLastParamValue() {
		lastValue = new InstalledAppsValue(lastValue.getValue());
		return lastValue;
	}

	private void createBatFile() {

		try (FileReader in = new FileReader("urls.properties");) {

			properties.load(in);
			File bat = new File(properties.getProperty("urls.appsBat"));
			if (appsTXT == null) {
				appsTXT = new File(properties.getProperty("urls.appsTxt"));
			} else {
				try (PrintWriter pw = new PrintWriter(appsTXT);) {
					pw.flush();
				}
			}
			try (FileOutputStream fos = new FileOutputStream(bat); DataOutputStream dos = new DataOutputStream(fos);) {
				dos.writeBytes(properties.getProperty("appsBat.CMD"));
			}
			Runtime.getRuntime().exec(properties.getProperty("appsBat.EXEC"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parseBatFile() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(appsTXT.getAbsolutePath()), "Unicode"))) {
			ArrayList<String> paramValues = new ArrayList<>();
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null && line.equals("") == false) {
				StringBuilder paramValue = new StringBuilder();
				String[] words = line.split("\\s+");
				for (String s : words) {
					paramValue.append(s + " ");
				}
				String value = new String(paramValue);
				paramValues.add(value);
			}
			lastValue = currentValue;
			currentValue = new InstalledAppsValue(paramValues);
			// apps.setValue(paramValues);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
