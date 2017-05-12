import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Map{
	private int level,mx,my;
	private int[][] mymap=new int[20][20];
	FileReader r;
	BufferedReader br;
	String bb="";
	int[] x;
	int c=0;
	Map(int k){
		level=k;
		String s;
		
		try {
			File f=new File("src/maps//"+level+".map");
			r=new FileReader(f);
			br=new BufferedReader(r);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		try {
			while((s=br.readLine())!=null){
				bb=bb+s;
			}
		} catch (IOException g) {
			System.out.println(g);
		}
		byte[] d=bb.getBytes();
		int len = bb.length();
		int [] x= new int[len];
		for(int i=0;i<bb.length();i++)x[i]=d[i]-48;
		for(int i=0;i<20;i++){
			for(int j=0;j<20;j++){
				mymap[i][j]=x[c];
				if(mymap[i][j]==5){
					mx=j;my=i;
				}
				c++;
			}
		}
	}
	int [][] getmap(){
		return mymap;
	}
	int getmanX(){
		return mx;
	}
	int getmanY(){
		return my;
	}
	/*Image tree,grass,wall,start,end;
	private int map[][]={
			{1,2,1,2,1,2,4,2,1,2,1,2,1,2,1},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,0,0,0,0,0,2,2,2,2,0,0,2,0,0},
			{1,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
			{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
			{1,0,0,0,1,0,0,0,0,0,2,0,1,0,1},
			{0,0,0,0,2,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,2,0,0,2,2,2,2,0,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,2,0,0},
			{1,0,0,0,0,0,1,0,0,2,0,0,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,1,0,0,2,0,0,1,0,0,2,0,3},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
			
			};
	Map(){
		tree= super.loadeImage(Const.TREEPATH);
		grass= super.loadeImage(Const.GRASSPATH);
		wall= super.loadeImage(Const.WALLPATH);
		start= super.loadeImage(Const.STARTPATH);
		end= super.loadeImage(Const.ENDPATH);
	}
	public void draw(Graphics g,ImageObserver io){
		//g.drawImage(person, x, y, Const.BLOCK,Const.BLOCK,io);
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				switch(map[i][j]){
				case 0:
					g.drawImage(grass, j*Const.BLOCK, i*Const.BLOCK,Const.BLOCK,Const.BLOCK, io);
					break;
				case 1:
					g.drawImage(tree, j*Const.BLOCK, i*Const.BLOCK,Const.BLOCK,Const.BLOCK, io);
					break;
				case 2:
					g.drawImage(wall, j*Const.BLOCK, i*Const.BLOCK,Const.BLOCK,Const.BLOCK, io);
					break;
				case 3:
					g.drawImage(end, j*Const.BLOCK, i*Const.BLOCK,Const.BLOCK,Const.BLOCK, io);
					break;
				case 4:
					g.drawImage(start, j*Const.BLOCK, i*Const.BLOCK,Const.BLOCK,Const.BLOCK, io);
					break;
				}
			}
		}
	}
	
	public boolean  moveable(int x,int y){
		if(y>=0&&y<map.length&x>=0&&x<map[0].length){
			if(map[y][x]==3){
				
			return true;
				
			}else{
				if(map[y][x]==0||map[y][x]==3){
					return true;
				}else{
						return false;
					
					
				}
			}
			
		}
	else{
		return false;
	}
		

	}
	public boolean moveabled(int x,int y){
		if(map[y][x]==3){
			return true;
		}
	return false;
	}*/

}
