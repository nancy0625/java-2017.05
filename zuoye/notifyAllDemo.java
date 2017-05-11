package wangxiao;

public class notifyAllDemo {

	
	public static void main(String[] args) {
		TakeTiket tk = new TakeTiket();
		Thread zhang = new Thread(tk);
		Thread lisi = new Thread(tk);
		Thread wang = new Thread(tk);
		wang.setName("王五");
		zhang.setName("张三");
		lisi.setName("李思");
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
		if(name.equals("张三")){
			saleTiket(20);
		}else if(name.equals("李思")){
			saleTiket(5);
		}else if(name.equals("王五")){
			saleTiket(10);
		}
		
	}
	public synchronized void saleTiket(int tiketmoney){
		String name = Thread.currentThread().getName();
		if(tiketmoney==5){
			
			System.out.println(name+"钱正好，买到了");
			fiveNum++;
		}else if(tiketmoney==20){
			while(fiveNum<3||tenNum==0){
				System.out.println(name+"不够找，请等等");
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
			System.out.print(name+"钱够了，买到了");
			System.out.println("售票元有5块"+fiveNum+"；20块"+twentyNum);
			
			
		}
		else if(tiketmoney==10){
			while(fiveNum<1){
				System.out.println(name+"不够找，请等等");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fiveNum--;
			tenNum++;
			System.out.println(name+"够找了");
			
		}
		notifyAll();
	}
	
}