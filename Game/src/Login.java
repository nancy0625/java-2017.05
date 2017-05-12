
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.*;


public class Login extends JFrame implements ActionListener {
	JButton login, reset;
	JTextField tname;
	JPasswordField tpass;
	JLabel name, pass;
	JPanel jp, jp1, jp2, jp3, jp4,jp5,jp6;
	JLabel lblImage,lbImage1,lbImage2;
	
	
	Login() {
		super("��¼ҳ");
		tname = new JTextField("������",15);
		tpass = new JPasswordField("07160605",15);
		

		jp = new JPanel();
		jp1 = new JPanel();
		jp3 = new JPanel();
		jp2 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();

		name = new JLabel("�û�����");
		pass = new JLabel("  ���룺 ");

		login = new JButton("��¼");
		login.setIcon(new ImageIcon("src/images/exit.gif"));
		reset = new JButton("����");

		Icon icon = new ImageIcon("src/images/timg.jpg");
		lblImage = new JLabel(icon);
		Icon icon1 = new ImageIcon("src/images/mu.gif");
		lbImage1 = new JLabel(icon1);
		Icon icon2 = new ImageIcon("src/images/muu.gif");
		lbImage2 = new JLabel(icon2);

	}

	public void init() {
		
      
		setLayout(new GridLayout(2, 2));

		setSize(Const.LLWIDTH, Const.LLHEIGHT);
		setVisible(true);
		setResizable(false);
		setLocation(Const.LWIDTH, Const.LHEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cont = getContentPane();
		
		cont.setBackground(Color.pink);
		
		
		add(jp1);
		jp1.add(name);
		jp1.add(tname);

		add(jp3);
		jp3.add(pass);
		jp3.add(tpass);

		add(jp);
		jp.add(login);
		jp.add(reset);
		
		jp4.add(jp1);
		jp4.add(jp3);
		jp4.add(jp);

		add(jp4);
		add(jp2);
		jp2.add(lblImage);
		cont.add(jp4);
		
		jp5.add(lbImage1);
		add(jp5);
		jp6.add(lbImage2);
		add(jp6);
		
		
		login.addActionListener(this);
		reset.addActionListener(this);
		tname.addActionListener(this);
		tpass.addActionListener(this);
		
		
		


	}
	
	
	
	

	public static void main(String[] args) {
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
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceAutumnLookAndFeel());
					//���簴������Ĳ��裬���Կ���һ���У�"SubstanceOfficeBlue2007LookAndFeel.class"����Ҫ�滻�����Ƥ�����Ϳ�������������д
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// ����һ�£�Ƥ���Ϳ��Ի���
					// ��Ҫ��ϸ�˽��ͬѧ������ȥ�ٶ��������������substance.jar
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Login().init();
				new Login().setVisible(true);
			}
		});
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tname||e.getSource()==tpass||e.getSource()==login){
			//�жϵ�¼�������벻��Ϊ��
			if(isFormNull()){
				return;
			}
			//�ж������Ƿ���ȷ
			String username = tname.getText();
			String password = new String(tpass.getPassword());
			
			if(username.equals("������")&&password.equals("07160605")){
				JOptionPane.showMessageDialog(this, "����������Σ���������������Ϸ!!!");
				GameFrame gf = new GameFrame();
				gf.init();
				gf.setSize(new Dimension(Const.WIDTH, Const.HEIGHT));
				gf.setVisible(true);
				
			
				
			}else{
				JOptionPane.showMessageDialog(this, "�û��������벻���ڣ�����������!");
				tname.setText("");
				tpass.setText("");
				return;
			}
		}else{
			tname.setText("");
			tpass.setText("");
		}
		
	}
	private boolean isFormNull(){
		String name = tname.getText().trim();
		if(name.length()==0){
			JOptionPane.showMessageDialog(this, "��¼������Ϊ��");
			tname.requestFocus(true);
			return true;
		}
		String pass = new String(tpass.getPassword());
		if(pass.length()==0){
			JOptionPane.showMessageDialog(this, "���벻��Ϊ��");
			
			return true;
		}
		return false;
	}

}
