
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
		super("登录页");
		tname = new JTextField("陈王晓",15);
		tpass = new JPasswordField("07160605",15);
		

		jp = new JPanel();
		jp1 = new JPanel();
		jp3 = new JPanel();
		jp2 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();

		name = new JLabel("用户名：");
		pass = new JLabel("  密码： ");

		login = new JButton("登录");
		login.setIcon(new ImageIcon("src/images/exit.gif"));
		reset = new JButton("重置");

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
					 * 想要修改皮肤的话，只需要更改，下面这个函数的参数，具体改成什么样，可以打开，Referenced Libraries -> 点击substance.jar -> 找到org.jvnet.substance.skin这个包  -> 将下面的SubstanceDustCoffeeLookAndFeel 替换成 刚刚打开的包下的任意一个“Substance....LookAndFeel”即可
					 */
					UIManager
							.setLookAndFeel(new org.jvnet.substance.skin.SubstanceAutumnLookAndFeel());
					//例如按照上面的步骤，可以看见一个叫，"SubstanceOfficeBlue2007LookAndFeel.class"，想要替换成这个皮肤，就可以向下面这样写
					//UIManager
					//.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
					// 运行一下，皮肤就可以换了
					// 想要详细了解的同学，可以去百度这个第三方包：substance.jar
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
			//判断登录名、密码不能为空
			if(isFormNull()){
				return;
			}
			//判断输入是否正确
			String username = tname.getText();
			String password = new String(tpass.getPassword());
			
			if(username.equals("陈王晓")&&password.equals("07160605")){
				JOptionPane.showMessageDialog(this, "您即将进入危险区域，请勿沉沦游戏!!!");
				GameFrame gf = new GameFrame();
				gf.init();
				gf.setSize(new Dimension(Const.WIDTH, Const.HEIGHT));
				gf.setVisible(true);
				
			
				
			}else{
				JOptionPane.showMessageDialog(this, "用户名或密码不存在，请重新输入!");
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
			JOptionPane.showMessageDialog(this, "登录名不能为空");
			tname.requestFocus(true);
			return true;
		}
		String pass = new String(tpass.getPassword());
		if(pass.length()==0){
			JOptionPane.showMessageDialog(this, "密码不能为空");
			
			return true;
		}
		return false;
	}

}
