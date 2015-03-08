/*

Author: Piyoros Vephula
Date  : 12/18/14
Course: CS111B
Homework     : 6
Program Name : DrawShapes.java
Objective    : This Program provides a drawing board for user to draw any picture as 
               desired. User can pick a color by clicking a radio button and choose 
               brush by typing character(s)in a textbox. Then draw picture by dragging 
               mouse within the program window or erase by choosing "erase" from radio 
               button and drag mouse. There are also buttons for saving, loading or 
               clearing the artwork.  
                               
*/

import java.util.*;
import java.io.*; 
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class DrawingBoard extends Frame implements MouseMotionListener,ItemListener,ActionListener,Serializable
{
	ArrayList<Color> alcolor = new ArrayList<>();
	ArrayList<String> albrush = new ArrayList<>();
	ArrayList<Point> coord = new ArrayList<>();
	ArrayList<Point> erase = new ArrayList<>();
	MyPoint mp = new MyPoint();
	Point point = new Point();
	Point e = new Point();	
	CheckboxGroup cg1;
   	Checkbox c1,c2,c3,c4,ce;
	TextField brush;
	String drawb;
	Color drawc;
	static String filename;

//*****************************DrawingBoard()*****************************
	public DrawingBoard()
	{
		setSize(800,600);
		setTitle("Drawing Board");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		cg1 = new CheckboxGroup ();
   		c1 = new Checkbox("Red",cg1,false);
    	c2 = new Checkbox("Green",cg1,false);
    	c3 = new Checkbox("Blue",cg1,false);
    	c4 = new Checkbox("Black",cg1,false);
    	add(c1); add(c2); add(c3); add(c4);
    	c1.addItemListener(this);
    	c2.addItemListener(this);
    	c3.addItemListener(this);
    	c4.addItemListener(this);
    	
    	Label l = new Label("Brush: ",Label.RIGHT);
    	brush = new TextField(10);
    	ce = new Checkbox("Erase",cg1,false);
    	add(l); add(brush); add(ce);
    	ce.addItemListener(this);

		Button bclear = new Button("Clear");
		Button bsave = new Button("Save");
		Button bload = new Button("Load");
		add(bclear); add(bsave); add(bload);
		bclear.addActionListener(this);
		bsave.addActionListener(this);
		bload.addActionListener(this);

		addMouseMotionListener(this);
		setVisible(true);
	}
//***************************itemStateChanged()***************************
	public void itemStateChanged(ItemEvent ie){}
//******************************mouseMoved()******************************
	public void mouseMoved(MouseEvent me){}
//*****************************mouseDragged()*****************************
	public void mouseDragged(MouseEvent me)
	{
		point = me.getPoint();
		repaint();
	}
//***************************actionPerformed()****************************
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		Graphics g = getGraphics();
		if(str.equals("Clear"))
		{			
			g.setColor(getBackground());
			g.fillRect(0,0,800,600);
			g.setColor(getForeground());
			g.dispose();
			coord.clear();
			alcolor.clear();
			albrush.clear();
			erase.clear();
			mp.setMyPoint(0);
			mp.setErase(0);
			return;
		}
		if(str.equals("Save"))
		{
			serialize(filename,mp);
		}
		if(str.equals("Load"))
		{	
			coord.clear();
			alcolor.clear();
			albrush.clear();
			erase.clear();
			point.x = 0;point.y = 0;
			drawc = getBackground();
			drawb = "";
 			coord = mp.getCoord();
			alcolor = mp.getColor();
			albrush = mp.getBrush();
			erase = mp.getErase();
			deserialize(filename);
			
			//System.err.println("ALCOLOR: " + alcolor.size());
			//System.err.println("ALBRUSH: " + albrush.size());
			//System.err.println("COORD: " + coord.size());
			//System.err.println("ERASE: " + erase.size());
			for(int i=0;i<alcolor.size();i++)
			{
				Point p = coord.get(i);
				g.setColor(alcolor.get(i));
				g.drawString(albrush.get(i),p.x,p.y);
				//System.out.println(alcolor.get(i));
				mp.setMyPoint(coord.get(i),albrush.get(i),alcolor.get(i));			
			}						
			
			for(int j=0;j<erase.size();j++)
			{
				Point e = erase.get(j);
				g.setColor(getBackground());
				g.fillOval(e.x-10,e.y-10,20,20);
				mp.setErase(erase.get(j));
				//System.out.println(e.x + " , " + e.y);
			}
			repaint();
		}
	}
//********************************paint()*********************************
	public void paint(Graphics g)
	{
		drawb = brush.getText();
		if(ce.getState())
		{	
			e.setLocation(point.x,point.y);
			g.setColor(getBackground());
			g.fillOval(e.x-10,e.y-10,20,20);
			//System.out.println(e.x + " , " + e.y);
			mp.setErase(point);
		}
		else
		{
			if(c1.getState())
			{
				g.setColor(Color.red);
				drawc = g.getColor();
				mp.setMyPoint(point,drawb,drawc);
			}
			else if(c2.getState())
			{
				g.setColor(Color.green);
				drawc = g.getColor();
				mp.setMyPoint(point,drawb,drawc);
			}
			else if(c3.getState())
			{
				g.setColor(Color.blue);
				drawc = g.getColor();
				mp.setMyPoint(point,drawb,drawc);
			}
			else if(c4.getState())
			{
				g.setColor(Color.black);
				drawc = g.getColor();
				mp.setMyPoint(point,drawb,drawc);
			}
			g.drawString(drawb,point.x,point.y);	
		}
	}
//********************************update()********************************
	public void update(Graphics g)
	{
		paint(g);
	}
//******************************serialize()*******************************
	public void serialize(String fname,MyPoint mp)
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname)))
		{
			oos.writeObject(mp);
			oos.flush();
		}catch(IOException e){System.err.println("error:" + e);}
	}
//*****************************deserialize()******************************
	public void deserialize(String fname)
	{
		MyPoint myObject = null;
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname)))
		{
			myObject = (MyPoint)ois.readObject();
			coord = myObject.getCoord();
			alcolor = myObject.getColor();
			albrush = myObject.getBrush();
			erase = myObject.getErase();			
		}catch(Exception e){System.err.println("error:"+e);}
	}
//*********************************main()*********************************
	public static void main(String args[])
	{
		if(args.length == 0)
			filename = "save.ser";
		else if(args.length == 1)
			filename = args[0];
		else
		{
			System.err.println("USAGE: java DrawingBoard [filename]");
			System.exit(1);
		}	
		new DrawingBoard();	
	}
}
