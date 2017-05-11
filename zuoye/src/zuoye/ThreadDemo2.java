 package zuoye;
public class ThreadDemo2 {

	public static void main(String[] args) {
		
		Road1 road = new Road1();
		Thread stu1 = new Thread(road);
		Thread stu2 = new Thread(road);
		Thread tech = new Thread(road);
		
		stu1.setName("张三");
		stu2.setName("李四");
		tech.setName("老师");
		
		
		road.setStu1(stu1);
		road.setStu2(stu2);
		
		stu1.start();
		stu2.start();
		tech.start();
	}

}
class Road1 implements Runnable{
	Thread stu1;
	Thread stu2;


	public void setStu1(Thread stu1) {
		this.stu1 = stu1;
	}

	public void setStu2(Thread stu2) {
		this.stu2 = stu2;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		if (name.equals("张三")) {
			System.out.println("我是" + name+",我想睡觉");
			try {
				Thread.currentThread().sleep(60 * 60 * 1000);
			} catch (InterruptedException e) {
				System.out.println(name + "被叫醒了！！");
			}
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"Wake Up!!李四");
			stu2.interrupt();
			
		} else if (name.equals("老师")) {
			System.out.println("我是" + name);
			for (int i = 0; i < 3; i++) {
				System.out.println("Wake Up!!!");

				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			stu1.interrupt();

		}
		else if(name.equals("李四")){
			System.out.println("我是"+name);
			try {
				Thread.currentThread().sleep(60*60*1000);
			} catch (InterruptedException e) {
				System.out.println(name+"被叫醒了！！！");
			}
			
		}
	}
}