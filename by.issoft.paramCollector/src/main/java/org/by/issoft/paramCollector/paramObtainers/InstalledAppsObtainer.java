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

import org.by.issoft.paramCollector.MyPropertyManager;
import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;

public class InstalledAppsObtainer extends ParamObtainer {

	private InstalledAppsValue currentValue;
	private InstalledAppsValue lastValue;

	private File appsTXT = null;
	private final String TXT_URL = MyPropertyManager.getProperty("urls.appsTXT");
	private final String BAT_URL = MyPropertyManager.getProperty("urls.appsBAT");
	private final String BAT_CMD = MyPropertyManager.getProperty("appsBat.CMD");
	private final String BAT_EXEC = MyPropertyManager.getProperty("appsBat.EXEC");

	public InstalledAppsObtainer() {
		paramInfo = new Param("INSTALLED_APPS", ParamType.VECTOR);
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

		try {

			File bat = new File(BAT_URL);
			if (appsTXT == null) {
				appsTXT = new File(TXT_URL);
			} else {
				try (PrintWriter pw = new PrintWriter(appsTXT);) {
					pw.flush();
				}
			}
			try (FileOutputStream fos = new FileOutputStream(bat); DataOutputStream dos = new DataOutputStream(fos);) {
				dos.writeBytes(BAT_CMD);
			}
			Runtime.getRuntime().exec(BAT_EXEC);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
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
			throw new RuntimeException();
		}
	}

	@Override
	public ParamValue<?> getNewValue() {
		return null;
	}

}
