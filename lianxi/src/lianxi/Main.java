package lianxi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Pattern;


enum tempChar { 
    B, C, J; 
    public static tempChar getChar(String s) { 
        return valueOf(s); 
    } 
} 
 
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num =scan.nextInt();
        int i=0;
        int a[]={0,0,0};//记录A的胜、平、负
//      int b[]={0,0,0};//记录B的胜、平、负
        int c[]={0,0,0};//记录A胜利最多的手势，分别是B，C，J
        int d[]={0,0,0};
        while(i<num){
            String temp = scan.next();
            String temp2= scan.next();
            switch (tempChar.getChar(temp)) {
            case B:
                switch (tempChar.getChar(temp2)) {
                case B:
                    a[1]++;
                    break;
                case C:
                    a[0]++;
                    c[0]++;
                    break;
                case J:
                    a[2]++;
                    d[2]++;
                    break;
                }
                break;
            case C:
                switch (tempChar.getChar(temp2)) {
                case B:
                    a[2]++;
                    d[0]++;
                    break;
                case C:
                    a[1]++;
                    break;
                case J:
                    a[0]++;
                    c[1]++;
                    break;
                }
                break;
            case J:
                switch (tempChar.getChar(temp2)) {
                case B:
                    a[0]++;
                    c[2]++;
                    break;
                case C:
                    a[2]++;
                    d[1]++;
                    break;
                case J:
                    a[1]++;
                    break;
                }
                break;
            }
            i++;
        }
        int max1=0,max2=0;
         
        for(i=1;i<3;i++){
            if(c[i]>c[max1]){
                max1 = i;
            }
            if(d[i]>d[max2]){
                max2 = i;
            }
        }
        System.out.println(a[0]+" "+a[1]+" "+a[2]);
        System.out.println(a[2]+" "+a[1]+" "+a[0]);
        switch (max1) {
        case 0:
            System.out.print("B"+" ");
            break;
        case 1:
            System.out.print("C"+" ");
            break;
        case 2:
            System.out.print("J"+" ");
            break;
        }
        switch (max2) {
        case 0:
            System.out.print("B");
            break;
        case 1:
            System.out.print("C");
            break;
        case 2:
            System.out.print("J");
            break;
        }


	}
}

/*
 * import java.io.BufferedInputStream; import java.util.ArrayList; import
 * java.util.Scanner; import java.util.regex.Pattern;
 * 
 * public class Main {
 * 
 * public static void main(String[] args) { Scanner sc = new Scanner(new
 * BufferedInputStream(System.in)); int n =Integer.parseInt(sc.nextLine());
 * 
 * for(int i=0;i<n;i++){ boolean isTrue = false; String s = sc.nextLine();
 * isTrue = Pattern.matches("A*PA+PA*", s); if(isTrue){ char []
 * arr=s.toCharArray(); int index = 0; int [] check=new int[]{0,0,0}; for(int
 * j=0;j<arr.length;j++){ if(arr[j]=="A"){ check[index]+=1; } } }
 * 
 * }
 * 
 * } }
 */
/*
 * int n = sc.nextInt(); sc.nextLine(); ArrayList ss = new ArrayList(); String
 * str=sc.nextLine(); StringBuffer sb = new StringBuffer(str);
 * 
 * // str=sb.reverse().toString(); char arr[] = str.toCharArray(); double lg =
 * (double)(str.length())/(n); int ln = str.length()/n; if(lg>ln){
 * lg=Math.round(lg); sb.append(" "); }
 * 
 * for(int i=0;i<str.length();i++){ if(str.charAt(i*ln)<str.length()){
 * System.out.print(arr[i*ln]); }
 * 
 * } Scanner sc = new Scanner(System.in); int n = sc.nextInt();
 * ArrayList<String> ss = new ArrayList<String>(); for(int i=0;i<n;i++){ String
 * str = sc.nextLine(); ss.add(str); } for(int i=0;i<ss.size();i++){
 * 
 * }
 * 
 * 
 * for(;;y++,year++){ a[0]=y%10; a[1]=y/10%10; a[2]=y/100%10; a[3]=y/1000%100; }
 * int ans =0;//与不同的个数n做比较 for(int i=0;i<a.length;i++){
 * if(!String.valueOf(a[i]).equals(a[i+1])){//有几个数不为0则说明有几个数不同 ans++; break; } }
 * if(ans==n){ System.out.print(year+y); }
 */
/*
 * String str = sc.nextLine(); String strr = sc.nextLine(); char a[]= new
 * char[10005]; a = str.toCharArray(); char b[] = new char[10005]; b =
 * strr.toCharArray(); int i; int aa = a.length; int bb = b.length; boolean boo
 * = false; for(i=0;i<aa;i++) { boo = false; for(int j=0;j<bb;j++){
 * if(b[j]==a[i]){ boo=true; break; } } if(!boo){ System.out.print(a[i]); } }
 */