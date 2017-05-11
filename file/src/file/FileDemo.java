package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDemo {

	//����һ��folder�ļ���
	public static void createFolder(String path){
		File folder=new File(path);
		if(folder.exists()){
			JOptionPane.showConfirmDialog(null, "�ļ����Ѿ�����");
		}else{
			folder.mkdirs();
		}
		
	}
	public static void createFile(String path,String filename){
		File file=new File(path,filename);
		if(file.exists()){
			JOptionPane.showConfirmDialog(null, "�ļ��Ѿ�����");
			System.out.println(file.length());
		}else{
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ�����ʧ�ܣ���");
			}
		}
		
	}
	//д�ļ�
	public static void writeFile(String path,String filename){
		try {
			String str = "abcdef\n1234567890";
			byte b[] = str.getBytes();
			FileOutputStream fos=new FileOutputStream(new File(path,filename));
			fos.write(b);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ������쳣");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("д���ļ�ʧ�ܣ���");
		}
	}
	//���ļ�
		public static void readFile(String path,String filename){
			try {
				int length =0;
				String str ="";
				byte buffer[] = new byte[10];
				FileInputStream fis=new FileInputStream(new File(path,filename));
				while((length=fis.read(buffer, 0, buffer.length))!=-1){
					str+=new String(buffer, 0, length);
				}
				System.out.println(str);
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("�ļ������쳣");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("д���ļ�ʧ�ܣ���");
			}
			
		}
		public static void copy(String src,String des){
			try {
				FileInputStream fis = new FileInputStream(src);
				FileOutputStream fos = new FileOutputStream(des);
				int c;
				while((c=fis.read())!=-1){
					fos.write(c);
				}
				fis.close();
				fos.close();
			} catch (FileNotFoundException e) {
				System.out.println("�ļ�������");
				
			} catch (IOException e) {
				System.out.println("��дʧ��");
			}
			
			
		}
		//�����ļ�
		public static void copy1(String src,String des){
			try {
				FileInputStream fis = new FileInputStream(src);
				FileOutputStream fos =  new FileOutputStream(des);
				int c;
				byte buff[] = new byte[1024];
				while((c=fis.read(buff,0,buff.length))!=-1){
					fos.write(buff,0,c);
				}
				fos.close();
				fis.close();
			} catch (FileNotFoundException e) {
				System.out.println("�ļ�������");
			} catch (IOException e) {
				System.out.println("��дʧ��");
			}
		}
		public static void FileReaderCopy(String src,String des){
			try {
				FileInputStream fis = new FileInputStream(src);
				FileOutputStream fos = new FileOutputStream(des);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public static void BufferReaderCopy(String src,String des){
			try {
				BufferedReader br = new BufferedReader(new FileReader(src));
				BufferedWriter bw = new BufferedWriter(new FileWriter(des));
				String str="";
				while ((str=br.readLine())!=null){
					bw.write(str);
					bw.newLine();
				}
				bw.close();
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public static void main(String[] args) {
		/*new FileDemo().createFolder("e:/Test");
		FileDemo.createFile("e:/Test", "1.txt");
		FileDemo.writeFile("e:/test", "1.txt");
		FileDemo.readFile("e:/Test", "Const.java");*/
       FileDemo.BufferReaderCopy("e:/test/1.txt", "E:/test/2.txt");
	}

}
