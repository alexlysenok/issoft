package org.module1.parameter.tabular;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.module1.parameter.TabularParam;

public class DiskInfo extends TabularParam<DiskInfo.Disk> {

	List<Disk> value=new ArrayList<Disk>();

	@Override
	public List<Disk> getValue() {
		return value;
	}
	
	@Override
	public void setValue(List<Disk> value) {
		this.value = value;
	}

	public  DiskInfo() {
		this.name = "DISKs INFO";
		this.commands = new String[1];
		this.commands[0] = "wmic /OUTPUT:D:\\Workspace\\Training\\project\\module1\\src\\main\\resources\\myBatInfo.txt logicaldisk get deviceid, volumename, freespace\n";
	}
	@Override
	public void readMyBatch(File batInfo) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(  new FileInputStream(batInfo.getAbsolutePath()), "Unicode"));
		String line="";
		br.readLine();
		//ArrayList<Disk> disks=new ArrayList<Disk>();
		while ((line=br.readLine())!=null && line.equals("")==false) {
			
			ArrayList<String> fields=new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			while(tokenizer.hasMoreTokens()){
				String word=tokenizer.nextToken();
				fields.add(word);
				
			}
			//System.out.println(fields);
			Disk inner = this.new Disk(fields);
			value.add(inner);
		}
		
		br.close();
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}

	public class Disk {
		String id;
		String space;
		String name;
		
		
        /*
		public Disk(String id, String space, String name) {
			this.id = id;
			this.space = space;
			this.name = name;
		}
		*/
		public Disk(){}
		
		public Disk(ArrayList<String> list) {
			this.id = list.get(0);
			this.space = list.get(1);
			this.name = list.get(2);
		}
		
		@Override
		public String toString(){
			String string="Disk:"+id+" | FreeSpace:"+space+" bytes | VolumeName:"+name;
			return string;					
		}
	}
}
