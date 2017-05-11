 package zuoye;
public class ThreadDemo2 {

	public static void main(String[] args) {
		
		Road1 road = new Road1();
		Thread stu1 = new Thread(road);
		Thread stu2 = new Thread(road);
		Thread tech = new Thread(road);
		
		stu1.setName("����");
		stu2.setName("����");
		tech.setName("��ʦ");
		
		
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
		if (name.equals("����")) {
			System.out.println("����" + name+",����˯��");
			try {
				Thread.currentThread().sleep(60 * 60 * 1000);
			} catch (InterruptedException e) {
				System.out.println(name + "�������ˣ���");
			}
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"Wake Up!!����");
			stu2.interrupt();
			
		} else if (name.equals("��ʦ")) {
			System.out.println("����" + name);
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
		else if(name.equals("����")){
			System.out.println("����"+name);
			try {
				Thread.currentThread().sleep(60*60*1000);
			} catch (InterruptedException e) {
				System.out.println(name+"�������ˣ�����");
			}
			
		}
	}
}