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
	JComboBox<String> jc = new JComboBox<String>();// ����ť��ɱ༭�ֶ��������б���ϵ����������ѡ��
	MenuItem renew, back, last, next, choose, exit, qin,ba, po, guang, nor, eye, about;// �˵��е����������������MenuItem��������֮һ��
   //������ѩ�����ʵ�����Ķ���
	private static final long serialVersionUID = 1L;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon snowFlower = new ImageIcon(this.getClass().getResource("images/snowflower.png"));
	private JLabel[] lbs = new JLabel[50];
	private ImageIcon leftWind = new ImageIcon(this.getClass().getResource("images/left.gif"));
	private ImageIcon rightWind = new ImageIcon(this.getClass().getResource("images/right.gif"));
	private JLabel wind = new JLabel();
	GameFrame() {
		super("������С��Ϸ");
		btnrenew = new JButton("����");
		btnback = new JButton("��һ��");
		btnlast = new JButton("��һ��");
		btnnext = new JButton("��һ��");
		btnchoose = new JButton("ѡ��");
		btnfirst = new JButton("��һ��");
		btnover = new JButton("���չ�");
		btnmuc = new JButton("�ص�����");

		renew = new MenuItem("   ����");
		back = new MenuItem("    ��һ��");
		last = new MenuItem("    ��һ��");
		next = new MenuItem("    ��һ��");
		choose = new MenuItem("    ѡ��");
		exit = new MenuItem("    �˳�");
		qin = new MenuItem("    all_the_things_you_are");
		po = new MenuItem("    �������");
		guang = new MenuItem("    ��������");
		nor = new MenuItem("    Ĭ��");
		ba = new MenuItem("    baba");
		
		eye = new MenuItem("    eyes on me");
		about = new MenuItem("    ����������...");// �˵��е����������������MenuItem��������֮һ��
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

		Menu choice = new Menu("    ѡ��");// �����ǴӲ˵������������ʽ�˵������
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

		Menu setmuc = new Menu("    ��������");

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

		Menu help = new Menu("    ����");
		help.add(about);
		about.addActionListener(this);

		MenuBar bar = new MenuBar();// ���װ�󶨵���ܵĲ˵�����ƽ̨���
		// Ϊ�˽��ò˵����� Frame ������������Ե��øÿ�ܵ� setMenuBar ������
		bar.add(choice);
		bar.add(setmuc);
		bar.add(help);
		setMenuBar(bar);

		nor.setEnabled(false);
		lb = new JLabel("JAVA������ѧ���棡����", SwingConstants.CENTER);
		lb2 = new JLabel("��������", SwingConstants.CENTER);

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
		
		jc.addItem("Ĭ��");
		jc.addItem("all_the_things");
		jc.addItem("�������");
		jc.addItem("��������");
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
		
		
		for(int i=0;i<50;i++){//����20��ѩ��
			lbs[i] = new JLabel();
			lbs[i].setSize(snowFlower.getIconWidth(), snowFlower.getIconHeight());
			lbs[i].setIcon(snowFlower);
			int x = new Random().nextInt(screenSize.width-snowFlower.getIconWidth());
			int y = new Random().nextInt((screenSize.height));
			lbs[i].setLocation(x,y);
			add(lbs[i]);
			new FlowDownThread(lbs[i]).start();//Ϊÿ��ѩ������һ��Ʈ����߳�
		}
		wind.setSize(leftWind.getIconWidth(), leftWind.getIconHeight());
		add(wind);
		setVisible(true);
		Timer timer = new Timer();
		timer.schedule(new Wind(), 2000, 5*1000);
	}
	
	//ģ��ѩ��Ʈ���߳�
	private class FlowDownThread extends Thread{
		private JLabel flower;
		private int speed = new Random().nextInt(20)+10;//ÿһ��ѩ���Ľ����ٶ�
		
		public FlowDownThread(JLabel flower){
			this.flower = flower;
		}
		@Override
		public void run() {
			while(true){
				Point p = flower.getLocation();
				p = new Point(p.x,p.y+2);
				flower.setLocation(p);
				if(p.y>screenSize.height){//��������߽�
					flower.setLocation(p.x,0);
					speed = new Random().nextInt(20)+10;//��������һ�������ٶ�
				}
				try {
					sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//�紵�������
	private class Wind extends TimerTask{
		@Override
		public void run() {
			//���λ��
			int x = new Random().nextInt(screenSize.width-snowFlower.getIconWidth());
			int y = new Random().nextInt(screenSize.height);
			if(x+wind.getWidth()>screenSize.width){
				x = screenSize.width-wind.getWidth();
			}
			if(y+wind.getHeight()>screenSize.getHeight()){
				y = screenSize.height-wind.getHeight();
			}
			int a = new Random().nextInt(2);//��������ķ磬1����0���Ҵ�
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
	//���紵��
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
		//Ƥ��ҳ�������
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					/*
					 * ��Ҫ�޸�Ƥ���Ļ���ֻ��Ҫ���ģ�������������Ĳ���������ĳ�ʲô�������Դ򿪣�Referenced Libraries -> ���substance.jar -> �ҵ�org.jvnet.substance.skin�����  -> �������SubstanceDustCoffeeLookAndFeel �滻�� �ոմ򿪵İ��µ�����һ����Substance....LookAndFeel������
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel());
					//���簴������Ĳ��裬���Կ���һ���У�"SubstanceOfficeBlue2007LookAndFeel.class"����Ҫ�滻�����Ƥ�����Ϳ�������������д
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// ����һ�£�Ƥ���Ϳ��Ի���
					// ��Ҫ��ϸ�˽��ͬѧ������ȥ�ٶ��������������substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new GameFrame().init();
				new GameFrame().setVisible(true);
			}
		});
		
		
	}

	// ����
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
				JOptionPane.showMessageDialog(this, "�����ǵ�һ��");
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
				JOptionPane.showMessageDialog(this, "�����Ѿ������һ��");
				panel.requestFocus();
			} else {
				panel.Tuixiangzi(panel.level);
				panel.requestFocus();// �����뽹����ڵ�����������Ŀؼ���
			}
			panel.remove();
		} else if (e.getSource() == exit) {
			System.exit(0);
		}

		else if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "java������С��Ϸ");
		} else if (e.getSource() == btnchoose || e.getSource() == choose) {
			String lel = JOptionPane.showInputDialog(this, "��������Ҫѡ��Ĺؿ��ţ�(1~50)");
			panel.level = Integer.parseInt(lel);
			panel.remove();
			if (panel.level > panel.maxlevel() || panel.level < 1) {
				JOptionPane.showMessageDialog(this, "û����һ���ؿ�����");
				panel.requestFocus();// �����뽹����ڵ�����������Ŀؼ���
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
			panel.remove();// ���ٴ���
		} else if (e.getSource() == btnmuc) {
			if (sound.isplay()) {
				sound.mystop();
				btnmuc.setLabel("��Ч��");

			} else {
				sound.loadSound();
				btnmuc.setLabel("��Ч��");
			}
			panel.requestFocus();
		} else if (e.getSource() == btnback || e.getSource() == back) {
			if (panel.isMystackEmpty()) {
				JOptionPane.showMessageDialog(this, "����δ�ƶ�������");
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
			btnmuc.setLabel("���ֹ�");
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
			btnmuc.setLabel("���ֹ�");
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
			btnmuc.setLabel("���ֹ�");
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
			btnmuc.setLabel("���ֹ�");
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
			btnmuc.setLabel("���ֹ�");
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
			btnmuc.setLabel("���ֹ�");
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
