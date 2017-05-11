package file;

import java.io.*;

public class Exam1 {
	public void writeFile(String src,String des){
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(des);
			int length;
			byte buffer[]=new byte[1024];
			String str = "";
			while(((length=fis.read(buffer, 0, buffer.length))!=-1)){
				str+= new String(buffer,0,length);
			    
			}
			//将英文小写转换成大写
			String st = str.toUpperCase();
			byte bb[]= st.getBytes();
			fos.write(bb);
			fos.close();
			fis.close();
			System.out.println("文件转换成功！！！");
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在！！！");
			
		} catch (IOException e) {
			System.out.println("读写失败了！！！");
		}
		
	}

	public static void main(String[] args) {
		try{
			new Exam1().writeFile("E:/test/1.txt","E:/test/2.txt");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
