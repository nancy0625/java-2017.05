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
			//��Ӣ��Сдת���ɴ�д
			String st = str.toUpperCase();
			byte bb[]= st.getBytes();
			fos.write(bb);
			fos.close();
			fis.close();
			System.out.println("�ļ�ת���ɹ�������");
		} catch (FileNotFoundException e) {
			System.out.println("�ļ������ڣ�����");
			
		} catch (IOException e) {
			System.out.println("��дʧ���ˣ�����");
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
