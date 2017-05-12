import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener{
	
	int max =50;
	int[][] map,maptmp;
	int manX,manY,boxnum;
	Image[] myImage;
	Map Levelmap;
	Map Levelmaptmp;
	int len=30;
	public int level=1;
	Stack<Integer> mystack = new Stack<Integer>();//peek()看栈顶元素，search()查看元素位置，pop()移除栈顶元素，push()把项压入堆栈顶部
	GamePanel(){
		setBounds(15,50,600,600);
		
		addKeyListener(this);
		myImage = new Image[10];
		for(int i=0;i<10;i++){
			myImage[i] = Toolkit.getDefaultToolkit().getImage("src/images//"+i+".gif");
		}
         setVisible(true);
		}
	void Tuixiangzi(int i){
		Levelmap = new Map(i);
		Levelmaptmp = new Map(i);
		map=Levelmap.getmap();
		manX = Levelmap.getmanX();
		manY = Levelmap.getmanY();
		maptmp = Levelmaptmp.getmap();
		repaint();
	}
	int maxlevel(){
		return max;
	}
	

	public void paint(Graphics g) {
		for(int i=0;i<20;i++){
			for(int j=0;j<20;j++){
				g.drawImage(myImage[map[j][i]], i*len, j*len, this);
				
			}
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("楷体_2312",Font.BOLD,30));
			g.drawString("现在是", 150, 40);
			g.drawString(String.valueOf(level),310, 40);
			g.drawString("关",360, 40);
		}
	}

	

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			moveup();
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			movedown();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			moveleft();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			moveright();
		}
		if(iswin()){
			if(level==max){
				JOptionPane.showMessageDialog(this,"恭喜您通过最后一关");
			}else{
				String msg = "恭喜您通过"+level+"关！！！\n是否进入下一关";
				int type=JOptionPane.YES_NO_OPTION;
				String title ="过关";
				int choice =0;
				choice=JOptionPane.showConfirmDialog(null,msg,title,type);
				if(choice==1){
					System.exit(0);
				}else if(choice==0){
					level++;
					Tuixiangzi(level);
				}
			}
			mystack.removeAllElements();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	boolean isMystackEmpty(){
		return mystack.isEmpty();
	}
	int back(){
		return mystack.pop();
		
	}
	void remove(){
		mystack.removeAllElements();
	}
	
	
	//移動小人0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void moveup(){
		if(map[manY-1][manX]==2||map[manY-1][manX]==4){
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9){
				map[manY][manX]=4;
			}else{
				map[manY][manX]=2;
				
			}
			map[manY-1][manX]=8;
			repaint();
			manY--;
			mystack.push(10);
		}
		else if(map[manY-1][manX]==3){
			if(map[manY-2][manX]==4){
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9){
					map[manY][manX]=4;
					
				}else{
					map[manY][manX]=2;
				}
				map[manY-1][manX]=8;
				map[manY-2][manX]=9;
				repaint();
				manY--;
				mystack.push(11);
			}else if(map[manY-2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9){
					map[manY][manX]=4;
				}else{
					map[manY][manX]=2;
				}
				map[manY-1][manX]=8;
				map[manY-2][manX]=3;
				repaint();
				manY--;
				mystack.push(11);
			}
			else {map[manY][manX]=8;
			repaint();
		}
	}
		else if(map[manY-1][manX]==9)
		{
			if(map[manY-2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY-1][manX]=8;
				map[manY-2][manX]=9;
				repaint();
				manY--;
				mystack.push(11);
			}
			else if(map[manY-2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY-1][manX]=8;
				map[manY-2][manX]=3;
				repaint();
				manY--;
				mystack.push(11);
			}
			else {map[manY][manX]=8;
			repaint();
			}
		}
		if(map[manY-1][manX]==1)
		{
			map[manY][manX]=8;
			repaint();
		}	
	}
	//0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void backup(int t)
	{
		int n=t;
		if(n==10)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==11)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY-1][manX]==4||maptmp[manY-1][manX]==9)
			{
				map[manY-1][manX]=4;
			}
			else map[manY-1][manX]=2;
		}
		map[manY+1][manX]=8;
		repaint();
		manY++;
	}
//0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void movedown()
	{
		if(map[manY+1][manX]==2||map[manY+1][manX]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else {
				map[manY][manX]=2;
			}
			map[manY+1][manX]=5;
			repaint();
			manY++;
			mystack.push(20);
		}
		else if(map[manY+1][manX]==3)
		{
			if(map[manY+2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY+1][manX]=5;
				map[manY+2][manX]=9;
				repaint();
				manY++;
				mystack.push(21);
			}
			else if(map[manY+2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else{
					map[manY][manX]=2;
				}
				map[manY+1][manX]=5;
				map[manY+2][manX]=3;
				repaint();
				manY++;
				mystack.push(21);
			}
			else {map[manY][manX]=5;repaint();}
		}
		else if(map[manY+1][manX]==9)
		{
			if(map[manY+2][manX]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else{
					map[manY][manX]=2;
				}
				map[manY+1][manX]=5;
				map[manY+2][manX]=9;
				repaint();
				manY++;
				mystack.push(21);
			}
			else if(map[manY+2][manX]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY+1][manX]=5;
				map[manY+2][manX]=3;
				repaint();
				manY++;
				mystack.push(21);
			}
			else {map[manY][manX]=5;repaint();}
		}
		else if(map[manY+1][manX]==1)
		{
			map[manY][manX]=5;
			repaint();
		}
	}
//0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void backdown(int t)
	{
		int n=t;
		if(n==20)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==21)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY+1][manX]==4||maptmp[manY+1][manX]==9)
			{
				map[manY+1][manX]=4;
			}
			else map[manY+1][manX]=2;
		}
		map[manY-1][manX]=5;
		repaint();
		manY--;
	}
/////左移动 0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void moveleft()
	{
		if(map[manY][manX-1]==2||map[manY][manX-1]==4)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else {
				map[manY][manX]=2;
			}
			map[manY][manX-1]=6;			
			repaint();
			manX--;
			mystack.push(30);
		}
		else if(map[manY][manX-1]==3)
		{
			if(map[manY][manX-2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else{
					map[manY][manX]=2;
				}
				map[manY][manX-1]=6;
				map[manY][manX-2]=9;
				repaint();manX--;
				mystack.
				push(31);
			}
			else if(map[manY][manX-2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY][manX-1]=6;
				map[manY][manX-2]=3;
				repaint();
				manX--;
				mystack.push(31);
			}
			else {map[manY][manX]=6;repaint();}
		}
		else if(map[manY][manX-1]==9)
		{
			if(map[manY][manX-2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY][manX-1]=6;
				map[manY][manX-2]=9;
				repaint();
				manX--;
				mystack.push(31);
			}
			else if(map[manY][manX-2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY][manX-1]=6;
				map[manY][manX-2]=3;
				repaint();
				manX--;
				mystack.push(31);
			}
			else {map[manY][manX]=6;
			repaint();
			}
		}
		else if(map[manY][manX-1]==1)
		{
			map[manY][manX]=6;
			repaint();
		}
	}
///////回退左移动  0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void backleft(int t)
	{
		int n=t;
		if(n==30)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==31)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY][manX-1]==4||maptmp[manY][manX-1]==9)
			{
				map[manY][manX-1]=4;
			}
			else map[manY][manX-1]=2;
		}
		map[manY][manX+1]=6;
		repaint();
		manX++;
	}
/////右移动0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void moveright()
	{
		if(map[manY][manX+1]==2||map[manY][manX+1]==4)//
		{			
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else {
				map[manY][manX]=2;
			}
			map[manY][manX+1]=7;//右方向			
			repaint();
			manX++;
			mystack.push(40);
		}
		else if(map[manY][manX+1]==3)
		{
			if(map[manY][manX+2]==4)
			{
				if(maptmp[manY][manX]==4)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY][manX+1]=7;
				map[manY][manX+2]=9;
				repaint();
				manX++;
				mystack.push(41);
			}
			else if(map[manY][manX+2]==2)
			{
				if(maptmp[manY][manX]==4)
					map[manY][manX]=4;
				else{
					map[manY][manX]=2;
				}
				map[manY][manX+1]=7;
				map[manY][manX+2]=3;
				repaint();
				manX++;
				mystack.push(41);
			}
			else {map[manY][manX]=7;
			repaint();
			}
		}
		else if(map[manY][manX+1]==9)
		{
			if(map[manY][manX+2]==4)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else{
					map[manY][manX]=2;
				}
				map[manY][manX+1]=7;
				map[manY][manX+2]=9;
				repaint();
				manX++;
				mystack.push(41);
			}
			else if(map[manY][manX+2]==2)
			{
				if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
					map[manY][manX]=4;
				else {
					map[manY][manX]=2;
				}
				map[manY][manX+1]=7;
				map[manY][manX+2]=3;
				repaint();
				manX++;
				mystack.push(41);
			}
			else {map[manY][manX]=7;
			repaint();
			}
		}
		else if(map[manY][manX+1]==1)
		{
			map[manY][manX]=7;
			repaint();
		}
	}
/////回退右移动  0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	void backright(int t)
	{
		int n=t;
		if(n==40)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=4;
			}
			else map[manY][manX]=2;
		}
		else if(n==41)
		{
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
			{
				map[manY][manX]=9;
			}
			else map[manY][manX]=3;
			if(maptmp[manY][manX+1]==4||maptmp[manY][manX+1]==9)
			{
				map[manY][manX+1]=4;
			}
			else map[manY][manX+1]=2;
		}
		map[manY][manX-1]=7;
		repaint();
		manX--;
	}
//0墙 1 树木  2草地  3箱子 、4箱子需要到的位置 5小人向下 6 小人向左  7小人向右 8 小人向上 9已经推到箱子的图片
	boolean iswin()
	{
		boolean num=false;
		out:for(int i=0; i<20; i++)//这是标签，用于跳出循环的。break用于跳出包含它的最内层循环，break out可以直接跳出被out标记的循环
			for(int j=0; j<20; j++)
		{
			if(maptmp[i][j]==4||maptmp[i][j]==9)
				if(map[i][j]==9)
					num=true;
			    else {
			    	num=false;
			    break out;
			    }
		}
		return num;
	}
}
 /*
	Person person;
	Map map;

	GamePanel(){
		person = new Person(6,0);
		this.setFocusable(true);//�õ�ǰ������ȡ����
		this.addKeyListener(this);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		map.draw(g, this);
		person.draw(g,this);
	}
	
	@Override
	public void keyPressed(KeyEvent ee) {
		// TODO Auto-generated method stub
		int keycode = ee.getKeyCode();
		if(keycode==KeyEvent.VK_LEFT){
			if(map.moveable(person.getX()-1, person.getY())){
				person.move(Const.LEFT);
			}
			
		}else if(keycode == KeyEvent.VK_RIGHT){
			if(map.moveable(person.getX()+1, person.getY())){
				person.move(Const.RIGHT);
			}
			
		}
		else if(keycode == KeyEvent.VK_UP){
			if(map.moveable(person.getX(), person.getY()-1)){
				person.move(Const.UP);
			}
			
		}
		else if(keycode == KeyEvent.VK_DOWN){
			if(map.moveable(person.getX(), person.getY()+1)){
				person.move(Const.DOWN);
			}
			
		}
		this.repaint();
		if(map.moveabled(person.getX(), person.getY())){
			JOptionPane.showMessageDialog(null, "ͨ���ˣ���");
		}
		System.out.println("1");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
*/

