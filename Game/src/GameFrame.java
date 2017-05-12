import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Timer;

import javax.sound.midi.*;
import javax.swing.*;





public class GameFrame extends JFrame implements ActionListener, ItemListener {

	GamePanel panel;
	JLabel lb, lb2;
	JButton btnrenew, btnlast, btnnext, btnchoose, btnfirst, btnover, btnmuc, btnback;
	Sound sound;
	JComboBox<String> jc = new JComboBox<String>();// 将按钮或可编辑字段与下拉列表组合的组件。下拉选项
	MenuItem renew, back, last, next, choose, exit, qin,ba, po, guang, nor, eye, about;// 菜单中的所有项必须属于类MenuItem或其子类之一。
   //下面是雪花类的实例化的对象
	private static final long serialVersionUID = 1L;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon snowFlower = new ImageIcon(this.getClass().getResource("images/snowflower.png"));
	private JLabel[] lbs = new JLabel[50];
	private ImageIcon leftWind = new ImageIcon(this.getClass().getResource("images/left.gif"));
	private ImageIcon rightWind = new ImageIcon(this.getClass().getResource("images/right.gif"));
	private JLabel wind = new JLabel();
	GameFrame() {
		super("推箱子小游戏");
		btnrenew = new JButton("重来");
		btnback = new JButton("悔一步");
		btnlast = new JButton("上一关");
		btnnext = new JButton("下一关");
		btnchoose = new JButton("选关");
		btnfirst = new JButton("第一关");
		btnover = new JButton("最终关");
		btnmuc = new JButton("关掉音乐");

		renew = new MenuItem("   重置");
		back = new MenuItem("    悔一步");
		last = new MenuItem("    上一关");
		next = new MenuItem("    下一关");
		choose = new MenuItem("    选关");
		exit = new MenuItem("    退出");
		qin = new MenuItem("    all_the_things_you_are");
		po = new MenuItem("    告白气球");
		guang = new MenuItem("    灌篮高手");
		nor = new MenuItem("    默认");
		ba = new MenuItem("    baba");
		
		eye = new MenuItem("    eyes on me");
		about = new MenuItem("    关于推箱子...");// 菜单中的所有项必须属于类MenuItem或其子类之一。
	}

	public void init() {

		setSize(Const.WIDTH, Const.HEIGHT);
		setVisible(true);
		setResizable(false);
		setLocation(Const.LWIDTH, Const.LHEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cont = getContentPane();
		cont.setLayout(null);
		cont.setBackground(Color.gray);

		Menu choice = new Menu("    选项");// 对象是从菜单栏部署的下拉式菜单组件。
		choice.add(renew);
		choice.add(last);
		choice.add(next);
		choice.add(choose);
		choice.add(back);

		
		choice.addSeparator();
		choice.add(exit);

		renew.addActionListener(this);
		last.addActionListener(this);
		next.addActionListener(this);
		choose.addActionListener(this);
		exit.addActionListener(this);
		back.addActionListener(this);

		Menu setmuc = new Menu("    设置音乐");

		setmuc.add(nor);
		setmuc.add(qin);
		setmuc.add(po);
		setmuc.add(guang);
		setmuc.add(eye);
		setmuc.add(ba);

		nor.addActionListener(this);

		qin.addActionListener(this);
		po.addActionListener(this);
		guang.addActionListener(this);
		eye.addActionListener(this);
		ba.addActionListener(this);

		Menu help = new Menu("    帮助");
		help.add(about);
		about.addActionListener(this);

		MenuBar bar = new MenuBar();// 类封装绑定到框架的菜单栏的平台概念。
		// 为了将该菜单栏与 Frame 对象关联，可以调用该框架的 setMenuBar 方法。
		bar.add(choice);
		bar.add(setmuc);
		bar.add(help);
		setMenuBar(bar);

		nor.setEnabled(false);
		lb = new JLabel("JAVA推箱子学生版！！！", SwingConstants.CENTER);
		lb2 = new JLabel("更换音乐", SwingConstants.CENTER);

		add(lb);
		add(lb2);

		lb.setBounds(100, 20, 400, 20);
		lb.setForeground(Color.white);
		lb2.setBounds(625, 500, 55, 20);
		lb2.setForeground(Color.white);

		add(btnrenew);

		add(btnlast);
		add(btnnext);
		add(btnchoose);
		add(btnfirst);
		add(btnover);
		add(btnmuc);
		add(btnback);

		btnrenew.setBounds(625, 100, 80, 30);
		btnrenew.addActionListener(this);

		btnback.setBounds(625, 150, 80, 30);
		btnback.addActionListener(this);

		btnlast.setBounds(625, 200, 80, 30);
		btnlast.addActionListener(this);

		btnnext.setBounds(625, 250, 80, 30);
		btnnext.addActionListener(this);

		btnchoose.setBounds(625, 300, 80, 30);
		btnchoose.addActionListener(this);

		btnfirst.setBounds(625, 350, 80, 30);
		btnfirst.addActionListener(this);

		btnover.setBounds(625, 400, 80, 30);
		btnover.addActionListener(this);

		btnmuc.setBounds(625, 450, 80, 30);
		btnmuc.addActionListener(this);

		jc.setBounds(625, 530, 80, 20);
		
		jc.addItem("默认");
		jc.addItem("all_the_things");
		jc.addItem("告白气球");
		jc.addItem("灌篮高手");
		jc.addItem("eyes on me");
		jc.addItem("baba");
		jc.addItemListener(this);

		cont.add(jc);
		sound = new Sound();
		sound.loadSound();

		panel = new GamePanel();
		add(panel);

		panel.Tuixiangzi(panel.level);
		panel.requestFocus();
		validate();
		
		
		for(int i=0;i<50;i++){//产生20朵雪花
			lbs[i] = new JLabel();
			lbs[i].setSize(snowFlower.getIconWidth(), snowFlower.getIconHeight());
			lbs[i].setIcon(snowFlower);
			int x = new Random().nextInt(screenSize.width-snowFlower.getIconWidth());
			int y = new Random().nextInt((screenSize.height));
			lbs[i].setLocation(x,y);
			add(lbs[i]);
			new FlowDownThread(lbs[i]).start();//为每个雪花产生一个飘落的线程
		}
		wind.setSize(leftWind.getIconWidth(), leftWind.getIconHeight());
		add(wind);
		setVisible(true);
		Timer timer = new Timer();
		timer.schedule(new Wind(), 2000, 5*1000);
	}
	
	//模拟雪花飘落线程
	private class FlowDownThread extends Thread{
		private JLabel flower;
		private int speed = new Random().nextInt(20)+10;//每一个雪花的降落速度
		
		public FlowDownThread(JLabel flower){
			this.flower = flower;
		}
		@Override
		public void run() {
			while(true){
				Point p = flower.getLocation();
				p = new Point(p.x,p.y+2);
				flower.setLocation(p);
				if(p.y>screenSize.height){//如果超过边界
					flower.setLocation(p.x,0);
					speed = new Random().nextInt(20)+10;//重新生成一个降落速度
				}
				try {
					sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//风吹方向的类
	private class Wind extends TimerTask{
		@Override
		public void run() {
			//风的位置
			int x = new Random().nextInt(screenSize.width-snowFlower.getIconWidth());
			int y = new Random().nextInt(screenSize.height);
			if(x+wind.getWidth()>screenSize.width){
				x = screenSize.width-wind.getWidth();
			}
			if(y+wind.getHeight()>screenSize.getHeight()){
				y = screenSize.height-wind.getHeight();
			}
			int a = new Random().nextInt(2);//随机产生的风，1向左吹0向右吹
			wind.setVisible(true);
			for(int i=0;i<50;i++){
				if(a==0){
					wind.setIcon(rightWind);
					wind.setLocation(x, y);
					if(lbs[i].getX()>x-wind.getWidth()&&lbs[i].getY()>y){
						new FlowByWindThread(lbs[i],a).start();
					}
				}
				if(a==1){
					wind.setIcon(leftWind);
					wind.setLocation(x, y);
					if(lbs[i].getX()<x+wind.getWidth()&&lbs[i].getY()>y){
						new FlowByWindThread(lbs[i],a).start();
					}
				}
			}
		}
	}
	//被风吹过
	private class FlowByWindThread extends Thread{
		JLabel lb = null;
		int a;
		
		public FlowByWindThread(JLabel lb,int a){
			this.lb = lb;
			this.a = a;
		}
		@Override
		public void run() {
			int tmp = 100;
			while(tmp>0){
				if(a==0){
					lb.setLocation(lb.getX()+3, lb.getY());
				}
				if(a==1){
					lb.setLocation(lb.getX()-3, lb.getY());
				}
				try {
					sleep(10);
					tmp--;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(lb.getX()>screenSize.width){
					lb.setLocation(new Random().nextInt(screenSize.width-snowFlower.getIconWidth()),
							0);
				}
			}
			wind.setVisible(false);
		}
	}

	

	public static void main(String[] args) {
		//皮肤页面的美化
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced Libraries -> 点击substance.jar -> 找到org.jvnet.substance.skin这个包  -> 将下面的SubstanceDustCoffeeLookAndFeel 替换成 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel());
					//例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new GameFrame().init();
				new GameFrame().setVisible(true);
			}
		});
		
		
	}

	// 监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnrenew || e.getSource() == renew) {
			panel.Tuixiangzi(panel.level);
			panel.requestFocus();
			panel.remove();
		} else if (e.getSource() == btnlast || e.getSource() == last) {
			panel.level--;
			if (panel.level < 1) {
				panel.level++;
				JOptionPane.showMessageDialog(this, "本关是第一关");
				panel.requestFocus();
			} else {
				panel.Tuixiangzi(panel.level);
				panel.requestFocus();
			}
			panel.remove();
		} else if (e.getSource() == next || e.getSource() == btnnext) {
			panel.level++;
			if (panel.level > panel.maxlevel()) {
				panel.level--;
				JOptionPane.showMessageDialog(this, "本关已经是最后一关");
				panel.requestFocus();
			} else {
				panel.Tuixiangzi(panel.level);
				panel.requestFocus();// 把输入焦点放在调用这个方法的控件上
			}
			panel.remove();
		} else if (e.getSource() == exit) {
			System.exit(0);
		}

		else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "java推箱子小游戏");
		} else if (e.getSource() == btnchoose || e.getSource() == choose) {
			String lel = JOptionPane.showInputDialog(this, "请输入你要选择的关卡号：(1~50)");
			panel.level = Integer.parseInt(lel);
			panel.remove();
			if (panel.level > panel.maxlevel() || panel.level < 1) {
				JOptionPane.showMessageDialog(this, "没有这一个关卡！！");
				panel.requestFocus();// 把输入焦点放在调用这个方法的控件上
			} else {
				panel.Tuixiangzi(panel.level);
				panel.requestFocus();
			}
		} else if (e.getSource() == btnfirst) {
			panel.level = 1;
			panel.Tuixiangzi(panel.level);
			panel.requestFocus();
			panel.remove();
		} else if (e.getSource() == btnover) {
			panel.level = panel.maxlevel();
			panel.Tuixiangzi(panel.level);
			panel.requestFocus();
			panel.remove();// 销毁窗口
		} else if (e.getSource() == btnmuc) {
			if (sound.isplay()) {
				sound.mystop();
				btnmuc.setLabel("音效开");

			} else {
				sound.loadSound();
				btnmuc.setLabel("音效关");
			}
			panel.requestFocus();
		} else if (e.getSource() == btnback || e.getSource() == back) {
			if (panel.isMystackEmpty()) {
				JOptionPane.showMessageDialog(this, "您还未移动过！！");
			} else {
				switch (panel.back()) {
				case 10:
					panel.backup(10);
					break;
				case 11:
					panel.backup(11);
					break;
				case 20:
					panel.backdown(20);
					break;
				case 21:
					panel.backdown(21);
					break;
				case 30:
					panel.backleft(30);
					break;
				case 31:
					panel.backleft(31);
					break;
				case 40:
					panel.backright(40);
					break;
				case 41:
					panel.backright(41);
					break;
				}
			}
			panel.requestFocus();
		}

		else if (e.getSource() == nor) {
			jc.setSelectedIndex(0);
		} else if (e.getSource() == qin) {
			jc.setSelectedIndex(1);
		} else if (e.getSource() == po) {
			jc.setSelectedIndex(2);
		} else if (e.getSource() == guang) {

			jc.setSelectedIndex(3);

		} else if (e.getSource() == eye) {
			jc.setSelectedIndex(4);
		}
		 else if (e.getSource() == ba) {
				jc.setSelectedIndex(5);
			}

	}

	public void itemStateChanged(ItemEvent ie) {

		int no = jc.getSelectedIndex();
		switch (no) {
		case 0:
			sound.setMusic("nor.mid");
			if (sound.isplay())
				sound.mystop();
			sound.loadSound();
			btnmuc.setLabel("音乐关");
			nor.setEnabled(false);
			qin.setEnabled(true);
			guang.setEnabled(true);
			eye.setEnabled(true);
			po.setEnabled(true);
			ba.setEnabled(true);
			panel.requestFocus();
			break;

		case 1:
			sound.setMusic("all.mid");
			if (sound.isplay())
				sound.mystop();
			sound.loadSound();
			btnmuc.setLabel("音乐关");
			nor.setEnabled(true);
			qin.setEnabled(false);
			guang.setEnabled(true);
			eye.setEnabled(true);
			po.setEnabled(true);
			ba.setEnabled(true);
			panel.requestFocus();
			break;
		case 2:
			sound.setMusic("qiqiu.mid");
			if (sound.isplay())
				sound.mystop();
			sound.loadSound();
			btnmuc.setLabel("音乐关");
			nor.setEnabled(true);
			qin.setEnabled(true);
			guang.setEnabled(true);
			eye.setEnabled(true);
			po.setEnabled(false);
			ba.setEnabled(true);
			panel.requestFocus();
			break;
		case 3:
			sound.setMusic("guang.mid");
			if (sound.isplay())
				sound.mystop();
			sound.loadSound();
			btnmuc.setLabel("音乐关");
			nor.setEnabled(true);
			qin.setEnabled(true);
			guang.setEnabled(false);
			eye.setEnabled(true);
			po.setEnabled(true);
			ba.setEnabled(true);
			panel.requestFocus();
			break;
		case 4:
			sound.setMusic("eyes on me.mid");
			if (sound.isplay())
				sound.mystop();
			sound.loadSound();
			btnmuc.setLabel("音乐关");
			nor.setEnabled(true);
			qin.setEnabled(true);
			guang.setEnabled(true);
			eye.setEnabled(false);
			po.setEnabled(true);
			ba.setEnabled(true);
			panel.requestFocus();
			break;
		case 5:
			sound.setMusic("eyes on me.mid");
			if (sound.isplay())
				sound.mystop();
			sound.loadSound();
			btnmuc.setLabel("音乐关");
			nor.setEnabled(true);
			qin.setEnabled(true);
			guang.setEnabled(true);
			eye.setEnabled(true);
			po.setEnabled(true);
			ba.setEnabled(false);
			panel.requestFocus();
			break;
		}
	}
}
