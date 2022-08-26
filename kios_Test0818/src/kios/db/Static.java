package kios.db;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Static {
	public static int count = 1;
	public static JPanel panel_3= new JPanel(new GridLayout(20, 1, 80, 0));
	public static ArrayList<ArrayList<Object>> outer_ArrayList = new ArrayList<ArrayList<Object>>();
	// 2차원 ArrayList 외부ArrayList 객체 생성
	public static int value;
	public static String phone="";
	public static String str="";
}
