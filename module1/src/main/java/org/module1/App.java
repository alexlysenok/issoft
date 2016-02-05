package org.module1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello World!");

		//File txt=new File("D:\\myBat.txt");
		File file=new File("D:/Workspace/Training/project/module1/src/main/resources/bat.bat");
		FileOutputStream fos=new FileOutputStream(file);
		DataOutputStream dos=new DataOutputStream(fos);
		dos.writeBytes("cd /d D:/Workspace/Training/project/module1/src/main/resources\n");
		//dos.writeBytes("ipconfig>>myBatInfo.txt\n");
		dos.writeBytes("time /t>>myBatInfo.txt\n"); 
		dos.close();
		Runtime.getRuntime().exec("cmd /c start d:/Workspace/Training/project/module1/src/main/resources/bat.bat");
		Object object=new String();

	}
}
