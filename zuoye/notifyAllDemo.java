package wangxiao;

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
	int tenNum=1;

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
			
			System.out.println(name+"Ǯ���ã�����");
			fiveNum++;
		}else if(tiketmoney==20){
			while(fiveNum<3||tenNum==0){
				System.out.println(name+"�����ң���ȵ�");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			fiveNum-=3;
			twentyNum++;
			tenNum++;
			System.out.print(name+"Ǯ���ˣ�����");
			System.out.println("��ƱԪ��5��"+fiveNum+"��20��"+twentyNum);
			
			
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
			fiveNum--;
			tenNum++;
			System.out.println(name+"������");
			
		}
		notifyAll();
	}
	
}