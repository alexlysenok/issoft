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
import org.by.issoft.paramCollector.MyPropertyManager;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue.Disk;

public class DisksInfoObtainer extends ParamObtainer {

	// fix formatting
	private DisksInfoValue currentValue;
	private DisksInfoValue lastValue;

	private final String TXT_URL = MyPropertyManager.getProperty("urls.diskTXT");
	private final String BAT_URL = MyPropertyManager.getProperty("urls.diskBAT");
	private final String BAT_CMD = MyPropertyManager.getProperty("diskBat.CMD");
	private final String BAT_EXEC = MyPropertyManager.getProperty("diskBat.EXEC");

	private File diskTXT = null;

	public DisksInfoObtainer() {
		paramInfo = new Param("DISKS_INFO", ParamType.TABULAR);
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

		try {

			File bat = new File(BAT_URL);
			if (diskTXT == null) {
				diskTXT = new File(TXT_URL);
			} else {
				try (PrintWriter pw = new PrintWriter(diskTXT);) {
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
