package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDemo1 {

	//创建一个folder文件
	public static void createFolder(String path){
		File folder = new File(path);
		if(folder.exists()){
			System.out.println("文件已存在");
		}else{
			folder.mkdir();
		}
	}
	//创建一个file文件
	public static void creatFile(String path,String filename){
		File file = new File(path,filename);
		if(file.exists()){
			System.out.println("文件已存在");
		}else{
			try{
				file.createNewFile();
			}catch(Exception e){
				System.out.println("创建文件失败！");
			}
			
		}
	}
	//写入文件
	public static void write(String path,String filename){
		
		try {
			String str="abc";
			byte b[] = str.getBytes();
			FileOutputStream fos=new FileOutputStream(new File(path,filename));
			fos.write(b);
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("写入失败！！！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
public static void read(String path,String filename){
	
		try {
			int length=0;
			String str="";
			byte[]buffer=new byte[10];
			FileInputStream fis = new FileInputStream(new File(path,filename));
			while((length=fis.read(buffer,0,buffer.length))!=-1){
				str+=new String(buffer,0,length);
				
			}
			System.out.println(str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		FileDemo1.createFolder("e:/test00");
		FileDemo1.creatFile("e:/test00", "1.txt");
		FileDemo1.write("e:/test00", "1.txt");
		FileDemo1.read("e:/test00", "1.txt");
		

	}

}
