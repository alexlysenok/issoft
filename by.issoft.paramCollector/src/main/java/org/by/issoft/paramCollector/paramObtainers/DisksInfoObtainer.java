package org.by.issoft.paramCollector.paramObtainers;

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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamInfo;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue.Disk;

import com.sun.webkit.network.URLs;

public class DisksInfoObtainer extends ParamObtainer {

	// fix formatting
	private DisksInfoValue disksInfo = new DisksInfoValue();
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
		return disksInfo;
	}

	@Override
	public DisksInfoValue getLastParamValue() {
		if (disksInfo.getValue() == null) {
			disksInfo = getCurrentParamValue();
		}
		return disksInfo;
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
			disksInfo = new DisksInfoValue(array);
			// disksInfo.setValue(array);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
