package org.by.issoft.paramCollector.paramObtainers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.by.issoft.paramCollector.ParamObtainer;

@Component
public class InstalledAppsObtainer extends ParamObtainer<InstalledAppsValue> {

	private InstalledAppsValue newValue;

	private File appsTXT = null;
	@Value("${urls.appsTXT}")
	private String TXT_URL;
	@Value("${urls.appsBAT}")
	private String BAT_URL;
	@Value("${appsBat.CMD}")
	private String BAT_CMD;
	@Value("${appsBat.EXEC}")
	private String BAT_EXEC;

	public InstalledAppsObtainer() {
		setParamInfo(new Param("INSTALLED_APPS", ParamType.VECTOR, super.getEntityClass()));

	}

	private void createBatFile() {

		try {

			File bat = new File(BAT_URL);
			appsTXT = new File(TXT_URL);
			// if (appsTXT == null) {
			// appsTXT = new File(TXT_URL);
			// } else {
			// try (PrintWriter pw = new PrintWriter(appsTXT);) {
			// pw.flush();
			// }
			// }
			try (FileOutputStream fos = new FileOutputStream(bat); DataOutputStream dos = new DataOutputStream(fos);) {
				dos.writeBytes(BAT_CMD);
			}
			Runtime.getRuntime().exec(BAT_EXEC);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
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

			newValue = new InstalledAppsValue(paramValues);
			// apps.setValue(paramValues);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public InstalledAppsValue obtainValue() {
		createBatFile();
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parseBatFile();
		return newValue;
	}

}
