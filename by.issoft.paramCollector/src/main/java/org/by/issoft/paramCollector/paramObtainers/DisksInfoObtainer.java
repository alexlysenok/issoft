package org.by.issoft.paramCollector.paramObtainers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue.Disk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.by.issoft.paramCollector.ParamObtainer;

@Component
public class DisksInfoObtainer extends ParamObtainer<DisksInfoValue> {

	// fix formatting
	private DisksInfoValue newValue;
	@Value("${urls.diskTXT}")
	private String TXT_URL;
	@Value("${urls.diskBAT}")
	private String BAT_URL;
	@Value("${diskBat.CMD}")
	private String BAT_CMD;
	@Value("${diskBat.EXEC}")
	private String BAT_EXEC;

	private File diskTXT = null;

	public DisksInfoObtainer() {
		setParamInfo(new Param("DISKS_INFO", ParamType.TABULAR, super.getEntityClass()));
	}

	private void createBatFile() {

		try {

			File bat = new File(BAT_URL);
			diskTXT = new File(TXT_URL);
			// if (diskTXT == null) {
			// diskTXT = new File(TXT_URL);
			// } else {
			// try (PrintWriter pw = new PrintWriter(diskTXT);) {
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
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(diskTXT.getAbsolutePath()), "Unicode"))) {
			String line = "";
			br.readLine();
			List<DisksInfoValue.Disk> array = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				String idField = " ";
				long longField = 0;
				String nameField = " ";
				if (words.length == 3) {
					idField = words[0];
					longField = Long.valueOf(words[1]);
					nameField = words[2];

				} else if (words.length == 2) {
					nameField = "noName";

				} else if (words.length == 1) {

					longField = 0L;
					nameField = "noInfo";

				} else {
					idField = "noInfo";
					longField = 0L;
					nameField = "noInfo";
				}

				Disk disk = new Disk(idField, longField, nameField);
				array.add(disk);
			}

			newValue = new DisksInfoValue(array);

			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public DisksInfoValue obtainValue() {
		createBatFile();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		parseBatFile();
		return newValue;
	}

}
