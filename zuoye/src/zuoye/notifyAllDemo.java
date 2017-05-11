package zuoye;

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
	int tenNum=0;

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
			fiveNum++;
			System.out.print(name+"钱正好，买到了");
			System.out.println("售票员有  5块"+fiveNum+"张 20块 "+twentyNum+" 10块 "+tenNum);
			
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
			fiveNum-=1;
			tenNum+=1;
			System.out.println(name+"找了5块  ！！"+"售票员10块有 "+tenNum+"张  5块有 "+fiveNum+"张   "+"20块有"+twentyNum+"张");
			
		}else if(tiketmoney==20){
			
			while(fiveNum<3){
				if(tenNum>0&&fiveNum>1){
					
					tenNum--;
					twentyNum++;
					fiveNum--;
					System.out.print(name+"可以买到票了  ");
					System.out.println("售票员有  5块"+fiveNum+"张 20块 "+twentyNum+" 10块 "+tenNum);
					
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
			
			System.out.print(name+"钱够了，买到了   ");
			System.out.println("售票员有  5块"+fiveNum+"张 20块 "+twentyNum+" 10块 "+tenNum);
			
		}
		notifyAll();
	}
	
}