package zuoye;

public class notifyAllDemo {

	
	public static void main(String[] args) {
		TakeTiket tk = new TakeTiket();
		Thread zhang = new Thread(tk);
		Thread lisi = new Thread(tk);
		Thread wang = new Thread(tk);
		wang.setName("����");
		zhang.setName("����");
		lisi.setName("��˼");
		wang.start();
		zhang.start();
		lisi.start();
		
	}

}


class TakeTiket implements Runnable{
	int fiveNum=3;
	int twentyNum=0;
	int tenNum=0;

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		if(name.equals("����")){
			saleTiket(20);
		}else if(name.equals("��˼")){
			saleTiket(5);
		}else if(name.equals("����")){
			saleTiket(10);
		}
		
	}
	public synchronized void saleTiket(int tiketmoney){
		String name = Thread.currentThread().getName();
		if(tiketmoney==5){
			fiveNum++;
			System.out.print(name+"Ǯ���ã�����");
			System.out.println("��ƱԱ��  5��"+fiveNum+"�� 20�� "+twentyNum+" 10�� "+tenNum);
			
		}
		else if(tiketmoney==10){
			while(fiveNum<1){
				System.out.println(name+"�����ң���ȵ�");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fiveNum-=1;
			tenNum+=1;
			System.out.println(name+"����5��  ����"+"��ƱԱ10���� "+tenNum+"��  5���� "+fiveNum+"��   "+"20����"+twentyNum+"��");
			
		}else if(tiketmoney==20){
			
			while(fiveNum<3){
				if(tenNum>0&&fiveNum>1){
					
					tenNum--;
					twentyNum++;
					fiveNum--;
					System.out.print(name+"������Ʊ��  ");
					System.out.println("��ƱԱ��  5��"+fiveNum+"�� 20�� "+twentyNum+" 10�� "+tenNum);
					
				}
				
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			fiveNum-=3;
			twentyNum++;
			
			System.out.print(name+"Ǯ���ˣ�����   ");
			System.out.println("��ƱԱ��  5��"+fiveNum+"�� 20�� "+twentyNum+" 10�� "+tenNum);
			
		}
		notifyAll();
	}
	
}