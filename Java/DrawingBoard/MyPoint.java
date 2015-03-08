/*

Author: Piyoros Vephula
Date  : 12/18/14
Course: CS111B
Homework     : 6
Program Name : DrawShapes.java
Objective    : This Program works with DrawingBoard.java to store values of colors, 
               brushes and coordinates in ArrayList and get/set all the values again when
               saving and loading. 
                               
*/

import java.util.*;
import java.io.*; 
import java.awt.*;

public class MyPoint implements Serializable
{
	private ArrayList<Point> mycoord;
	private ArrayList<String> mybrush;
	private ArrayList<Color> mycolor;
	private ArrayList<Point> myerase;
//*******************************MyPoint()********************************
	public MyPoint()
	{
		mycoord = new ArrayList<Point>();
		mybrush = new ArrayList<String>();
		mycolor = new ArrayList<Color>();
		myerase = new ArrayList<Point>();
	}
//******************************setMyPoint()******************************
	public void setMyPoint(int n)
	{
		mycoord.clear();
		mybrush.clear();
		mycolor.clear();
	}
//******************************setMyPoint()******************************
	public void setMyPoint(Point p,String b,Color c)
	{
		mycoord.add(p);
		mybrush.add(b);
		mycolor.add(c);
	}	
//*******************************getCoord()*******************************
	public ArrayList getCoord()
	{
		return mycoord;
	}
//*******************************getBrush()*******************************
	public ArrayList getBrush()
	{
		return mybrush;
	}
//*******************************getColor()*******************************
	public ArrayList getColor()
	{
		return mycolor;
	}
//*******************************getErase()*******************************
	public ArrayList getErase()
	{
		return myerase;
	}
//*******************************setErase()*******************************
	public void setErase(Point p)
	{
		myerase.add(p);
	}
//*******************************setErase()*******************************
	public void setErase(int n)
	{
		myerase.clear();
	}
}
