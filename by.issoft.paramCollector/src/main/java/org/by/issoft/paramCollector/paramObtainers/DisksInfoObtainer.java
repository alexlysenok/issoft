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
import java.util.List;
import java.util.Properties;
import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamInfo;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue.Disk;

public class DisksInfoObtainer extends ParamObtainer {

	// fix formatting
	private DisksInfoValue currentValue = new DisksInfoValue();
	private DisksInfoValue lastValue = new DisksInfoValue();

	private File diskTXT = null;
	private Properties properties = new Properties();

	public DisksInfoObtainer() {
		paramInfo = new ParamInfo("DISKS_INFO", ParamType.TABULAR);
	}

	@Override
	public DisksInfoValue getCurrentParamValue() {

		createBatFile();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		parseBatFile();
		return currentValue;
	}

	@Override
	public DisksInfoValue getLastParamValue() {

		lastValue = new DisksInfoValue(lastValue.getValue());
		return lastValue;
	}

	private void createBatFile() {

		try (FileReader in = new FileReader("urls.properties");) {

			properties.load(in);
			File bat = new File(properties.getProperty("urls.diskBat"));
			if (diskTXT == null) {
				diskTXT = new File(properties.getProperty("urls.diskTxt"));
			} else {
				try (PrintWriter pw = new PrintWriter(diskTXT);) {
					pw.flush();
				}
			}
			try (FileOutputStream fos = new FileOutputStream(bat); DataOutputStream dos = new DataOutputStream(fos);) {
				dos.writeBytes(properties.getProperty("diskBat.CMD"));
			}
			Runtime.getRuntime().exec(properties.getProperty("diskBat.EXEC"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void parseBatFile() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(diskTXT.getAbsolutePath()), "Unicode"))) {
			String line = "";
			br.readLine();
			List<DisksInfoValue.Disk> array = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				Long longField = Long.valueOf(words[1]);
				Disk disk = new Disk(words[0], longField, words[2]);
				array.add(disk);
			}
			lastValue = currentValue;
			currentValue = new DisksInfoValue(array);
			// disksInfo.setValue(array);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
