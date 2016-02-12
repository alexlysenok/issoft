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
import java.util.List;
import java.util.StringTokenizer;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;

public class DisksInfoObtainer extends ParamObtainer {

	// fix formatting
	private DisksInfoValue disksInfo = new DisksInfoValue();
	private File diskTXT = null;

	public DisksInfoObtainer() {
	}

	@Override
	public DisksInfoValue getCurrentParamValue() {

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
			//throw Runtime exception
		}

		return disksInfo;
	}

	@Override
	public DisksInfoValue getLastParamValue() {
		if (disksInfo.getValue() == null) {
			disksInfo = getCurrentParamValue();
		}
		return disksInfo;
	}

	@Override
	public String getParamName() {
		return disksInfo.paramInfo.getName();
	}

	@Override
	public void print() {

		System.out.println("Disks:");
		for (int i = 0; i < disksInfo.getValue().size(); i++) {
			System.out.println(disksInfo.getValue().get(i));
		}

	}

	//rename
	private void createBatch() throws IOException {

		// remove absolute pathes usage
		File bat = new File("D:/Workspace/Training/project/by.issoft.paramCollector/src/main/resources/diskBat.bat");
		if (diskTXT == null) {
			diskTXT = new File("D:/Workspace/Training/project/by.issoft.paramCollector/src/main/resources/diskTXT.txt");
		} else {
			PrintWriter pw = new PrintWriter(diskTXT);
			pw.flush();
			pw.close();
		}
		
		FileOutputStream fos = new FileOutputStream(bat);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeBytes(
				"wmic /OUTPUT:D:\\Workspace\\Training\\project\\by.issoft.paramCollector\\src\\main\\resources\\diskTXT.txt logicaldisk get deviceid, volumename, freespace\n");
		
		//use try with resources
		dos.close();
		fos.close();
		Runtime.getRuntime().exec(
				"cmd /c start d:/Workspace/Training/project/by.issoft.paramCollector/src/main/resources/diskBat.bat");
	}

	//make private
	public void readMyBatch() throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(diskTXT.getAbsolutePath()), "Unicode"));
		// BufferedReader br=new BufferedReader(new
		// FileReader(batInfo.getAbsolutePath()));
		String line = "";
		br.readLine();

		// use <> at right part 
		List<DisksInfoValue.Disk> array = new ArrayList<DisksInfoValue.Disk>();
		while ((line = br.readLine()) != null) {

			ArrayList<String> fields = new ArrayList<String>();
			
			//use split
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();
				fields.add(word);

			}

			Long longField = Long.valueOf(fields.get(1));

			DisksInfoValue.Disk disk = disksInfo.new Disk(fields.get(0), longField, fields.get(2));
			// System.out.println(disk.getId() +" "+disk.getFreeSpace()+"
			// "+disk.getName());
			array.add(disk);
		}
		// System.out.println("array: "+array);
		disksInfo.setValue(array);
		// System.out.println("disks: "+disksInfo.getDisks());
		// System.out.println(disksInfo.getDisks().size());
		br.close();
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

}
