import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Stack;

import javax.swing.JPanel;

public class Person extends JPanel {
	int[][] map,maptmp;
	int manX,manY,boxnum;
	Map Levelmap;
	Map Levelmaptmp;
	
	Stack<Integer> mystack = new Stack<Integer>();//peek()看栈顶元素，search()查看元素位置，pop()移除栈顶元素，push()把项压入堆栈顶部
	
	
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
				repaint();manY--;mystack.push(11);
			}
			else {map[manY][manX]=8;repaint();
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
/////////////////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////////////////
	void moveright()
	{
		if(map[manY][manX+1]==2||map[manY][manX+1]==4)
		{			
			if(maptmp[manY][manX]==4||maptmp[manY][manX]==9)
				map[manY][manX]=4;
			else {
				map[manY][manX]=2;
			}
			map[manY][manX+1]=7;			
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
/////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////
	boolean iswin()
	{
		boolean num=false;
		out:for(int i=0; i<20; i++)//这是标签，用于跳出循环的。break用于跳出包含它的最内层循环，break out可以直接跳出被out标记的循环
			for(int j=0; j<20; j++)
		{
			if(maptmp[i][j]==4||maptmp[i][j]==9)
				if(map[i][j]==9)num=true;
			    else {
			    	num=false;
			    break out;
			    }
		}
		return num;
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
	/*private int x, y;
	private Image person;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Person() {
		super();
		this.x = 0;
		this.y = 0;
		person = loadeImage(Const.PERSONPATH);

	}

	public Person(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		person = loadeImage(Const.PERSONPATH);
	}

	public void draw(Graphics g, ImageObserver io) {
		g.drawImage(person, x*Const.BLOCK, y*Const.BLOCK, Const.BLOCK, Const.BLOCK, io);
	}

	public void move(int direction) {
 
		if(direction==Const.LEFT){
			this.x--;
			
		}else if(direction==Const.RIGHT){
			this.x++;
		}
		else if(direction==Const.UP){
			this.y--;
		}
		else if(direction==Const.DOWN){
			this.y++;
		}
	}*/

}
