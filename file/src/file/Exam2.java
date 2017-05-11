package file;


import java.io.*;

public class Exam2 {
	public void  writeFile(){
		try {
			RandomAccessFile raf1 = new RandomAccessFile("E:/test/1.txt", "rw");
			int length;
			byte buffer[]=new byte[1024];
			String str = "";
			while(((length=raf1.read(buffer, 0, buffer.length))!=-1)){
				str+= new String(buffer,0,length);
			    
			}
			long len = str.length();
			while(0!=len--){
				raf1.seek(len);
				char ch= (char)raf1.read();
				System.out.print(ch+"");
			}
			raf1.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在！");
		} catch (IOException e) {
			System.out.println("读取失败了！！");
		}
		
		
	}

	public static void main(String[] args) {
		new Exam2().writeFile();

	}

}
