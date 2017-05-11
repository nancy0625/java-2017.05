package file;

import java.io.*;

public class BufferDemo {
	public static void BufferReaderCopy(String src,String des){
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br=new BufferedReader(new FileReader(src));
			bw=new BufferedWriter(new FileWriter(des));
			
			String str="";
			int i=1;
			while((str=br.readLine())!= null){
				str=str+Integer.toString(i)+"";
				i++;
				System.out.println(str);
				bw.write(str);

				bw.newLine();
				
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	

	public static void main(String[] args) {
		BufferDemo.BufferReaderCopy("e:/test/1.txt","e:/test/3.txt");
		
	}	
}
