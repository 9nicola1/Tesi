package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
   private static final int PREF_W = 800;
   private static final int PREF_H = 650;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.ORANGE;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int Y_HATCH_CNT = 10;
   private int max=0;
   private List<Integer>iterazioni=new LinkedList<Integer>();
   private List<Integer>alert=new LinkedList<Integer>();
   private List<Integer>noAlert=new LinkedList<Integer>();
   private boolean draw=false;
   

   public DrawGraph() {
	   this.setBackground(Color.DARK_GRAY);
   }//Constructor
   
   public void addTweetIteration(int numIterazione, int numAlert, int numNoAlert) {
	   System.out.println(numIterazione+" "+numAlert+" "+numNoAlert);
	   this.iterazioni.add(numIterazione);
	   this.alert.add(numAlert);
	   this.noAlert.add(numNoAlert);
	   if(this.iterazioni.size()>20) {
		   this.iterazioni.remove(0);
		   this.alert.remove(0);
		   this.noAlert.remove(0);
	   }
	   this.draw=true;
	   if(numIterazione>max)max=numIterazione;
   }//addTweetIteration

   @Override
   protected void paintComponent(Graphics g) {
	  if(draw) {
	      super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D)g;
	      g2.setColor(Color.WHITE);
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (iterazioni.size() - 1);
	      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (max - 1);
	
	      List<Point> graphPoints = new ArrayList<Point>();
	      List<Point>alertPoints=new ArrayList<Point>();
	      List<Point>noAlertPoints=new ArrayList<Point>();
	      for (int i = 0; i < iterazioni.size(); i++) {
	         int x1 = (int) (i * xScale + BORDER_GAP);
	         int y1 = (int) ((max - iterazioni.get(i)) * yScale + BORDER_GAP);
	         graphPoints.add(new Point(x1, y1));
	      }
	      
	      for (int i = 0; i < alert.size(); i++) {
	         int x1 = (int) (i * xScale + BORDER_GAP);
	         int y1 = (int) ((max - alert.get(i)) * yScale + BORDER_GAP);
	         alertPoints.add(new Point(x1, y1));
		  }
	      
	      for (int i = 0; i < noAlert.size(); i++) {
	         int x1 = (int) (i * xScale + BORDER_GAP);
	         int y1 = (int) ((max - noAlert.get(i)) * yScale + BORDER_GAP);
	         noAlertPoints.add(new Point(x1, y1));
		   }
	
	      // create x and y axes 
	      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
	      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
	
	      // create hatch marks for y axis. 
	      for (int i = 0; i < Y_HATCH_CNT; i++) {
	    	 g2.setColor(Color.WHITE);
	         int x0 = BORDER_GAP-13;
	         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP-13;
	         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
	         int y1 = y0;
	         g2.drawLine(x0, y0, x1, y1);
	         g2.setColor(Color.GRAY);
	         g2.drawLine(x0+13, y0, getWidth()-30, y0);
	      }
	
	      // and for x axis
	      for (int i = 0; i < iterazioni.size() - 1; i++) {
	    	 g2.setColor(Color.WHITE);
	         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (iterazioni.size() - 1) + BORDER_GAP;
	         int x1 = x0;
	         int y0 = getHeight() - BORDER_GAP+6;
	         int y1 = y0 - GRAPH_POINT_WIDTH+6;
	         g2.drawLine(x0, y0, x1, y1);
	         g2.setColor(Color.GRAY);
	         g2.drawLine(x0, y0-8, x0, getHeight()-y0+7);
	      }
	
	      Stroke oldStroke = g2.getStroke();
	      g2.setColor(GRAPH_COLOR);
	      g2.setStroke(GRAPH_STROKE);
	      for (int i = 0; i < graphPoints.size() - 1; i++) {
	         int x1 = graphPoints.get(i).x;
	         int y1 = graphPoints.get(i).y;
	         int x2 = graphPoints.get(i + 1).x;
	         int y2 = graphPoints.get(i + 1).y;
	         g2.drawLine(x1, y1, x2, y2);         
	      }
	
	      g2.setStroke(oldStroke);      
	      for (int i = 0; i < graphPoints.size(); i++) {
		     g2.setColor(GRAPH_POINT_COLOR);
	         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
	         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
	         int ovalW = GRAPH_POINT_WIDTH;
	         int ovalH = GRAPH_POINT_WIDTH;
	         g2.fillOval(x, y, ovalW, ovalH);
		     g2.setColor(Color.WHITE);
		     g2.drawString("[IT # "+iterazioni.get(i)+"]", x, y);
	      }
	      
	      Stroke oldStrokeAlert = g2.getStroke();
	      g2.setColor(new Color(0,255,124));
	      g2.setStroke(GRAPH_STROKE);
	      for (int i = 0; i < alertPoints.size() - 1; i++) {
	         int x1 = alertPoints.get(i).x;
	         int y1 = alertPoints.get(i).y;
	         int x2 = alertPoints.get(i + 1).x;
	         int y2 = alertPoints.get(i + 1).y;
	         g2.drawLine(x1, y1, x2, y2);         
	      }
	
	      g2.setStroke(oldStrokeAlert);      
	      for (int i = 0; i < alertPoints.size(); i++) {
	    	 g2.setColor(new Color(0,168,124,180));
	         int x = alertPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
	         int y = alertPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
	         int ovalW = GRAPH_POINT_WIDTH;
	         int ovalH = GRAPH_POINT_WIDTH;
	         g2.fillOval(x, y, ovalW, ovalH);
		     g2.setColor(Color.WHITE);
		     g2.drawString("[P # "+alert.get(i)+"]", x, y);
	      }
	      
	      Stroke oldStrokeNoAlert = g2.getStroke();
	      g2.setColor(new Color(255,22,22));
	      g2.setStroke(GRAPH_STROKE);
	      for (int i = 0; i < noAlertPoints.size() - 1; i++) {
	         int x1 = noAlertPoints.get(i).x;
	         int y1 = noAlertPoints.get(i).y;
	         int x2 = noAlertPoints.get(i + 1).x;
	         int y2 = noAlertPoints.get(i + 1).y;
	         g2.drawLine(x1, y1, x2, y2);         
	      }
	
	      g2.setStroke(oldStrokeNoAlert);      
	      for (int i = 0; i < noAlertPoints.size(); i++) {
		    
		     g2.setColor(new Color(140,2,0,180));
	         int x = noAlertPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
	         int y = noAlertPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
	         int ovalW = GRAPH_POINT_WIDTH;
	         int ovalH = GRAPH_POINT_WIDTH;
	         g2.fillOval(x, y, ovalW, ovalH);
		     g2.setColor(Color.WHITE);
		     g2.drawString("[N # "+noAlert.get(i)+"]", x, y);
	      }      
	   //   draw=false;
	  }else {
		  super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D)g;
	      g2.setColor(Color.WHITE);
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (10 - 1);
	      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (10 - 1);
	      
	      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
	      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
	   // create hatch marks for y axis. 
	      for (int i = 0; i < Y_HATCH_CNT; i++) {
	    	 g2.setColor(Color.WHITE);
	         int x0 = BORDER_GAP-13;
	         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP-13;
	         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
	         int y1 = y0;
	         g2.drawLine(x0, y0, x1, y1);
	         g2.setColor(Color.GRAY);
	         g2.drawLine(x0+13, y0, getWidth()-30, y0);
	      }
	
	      // and for x axis
	      for (int i = 0; i < 10 - 1; i++) {
	    	 g2.setColor(Color.WHITE);
	         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (10 - 1) + BORDER_GAP;
	         int x1 = x0;
	         int y0 = getHeight() - BORDER_GAP+6;
	         int y1 = y0 - GRAPH_POINT_WIDTH+6;
	         g2.drawLine(x0, y0, x1, y1);
	         g2.setColor(Color.GRAY);
	         g2.drawLine(x0, y0-8, x0, getHeight()-y0+7);
	      }
	  }
   }//paintComponent

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }//getPreferredSize

}//DrawGraph
