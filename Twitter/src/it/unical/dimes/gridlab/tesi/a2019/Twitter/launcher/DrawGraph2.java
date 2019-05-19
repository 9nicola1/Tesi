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
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph2 extends JPanel {
   private static int MAX_SCORE = 100;
   private static final int PREF_W = 800;
   private static final int PREF_H = 650;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.ORANGE;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int Y_HATCH_CNT = 10;
   private List<Integer> scores;
   private List<String> strings;

   public DrawGraph2(List<Integer> scores) {
	   this.setBackground(Color.DARK_GRAY);
	   this.scores = scores;
   }
   
   public void removeAllHashtag() {
	   this.removeAll();
   }
   
   public void setList(List<Integer>scores,List<String>strings,  int max) {
	   this.scores=scores;
	   this.strings=strings;
	   this.MAX_SCORE=max;
   }//setList

   @Override
   protected void paintComponent(Graphics g) {
	  if(scores.size()!=0) {
	      super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D)g;
	      g2.setColor(Color.WHITE);
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
	      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);
	
	      List<Point> graphPoints = new ArrayList<Point>();
	      for (int i = 0; i < scores.size(); i++) {
	         int x1 = (int) (i * xScale + BORDER_GAP);
	         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
	         System.out.println(x1+" "+y1);
	         graphPoints.add(new Point(x1, y1));
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
	      for (int i = 0; i < scores.size() - 1; i++) {
	    	 g2.setColor(Color.WHITE);
	         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
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
	  //       g2.drawString("("+scores.get(i)+")"+strings.get(i), x, y);
	      }
	  }else {
		  super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D)g;
	      g2.setColor(Color.WHITE);
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (MAX_SCORE - 1);
	      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);
	      
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
	      for (int i = 0; i < MAX_SCORE - 1; i++) {
	    	 g2.setColor(Color.WHITE);
	         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (MAX_SCORE - 1) + BORDER_GAP;
	         int x1 = x0;
	         int y0 = getHeight() - BORDER_GAP+6;
	         int y1 = y0 - GRAPH_POINT_WIDTH+6;
	         g2.drawLine(x0, y0, x1, y1);
	         g2.setColor(Color.GRAY);
	         g2.drawLine(x0, y0-8, x0, getHeight()-y0+7);
	      }
	  }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }


   private static void createAndShowGui() {
      List<Integer> scores = new ArrayList<Integer>();
      Random random = new Random();
      int maxDataPoints = 50;
      int maxScore = 200;
      for (int i = 0; i < maxDataPoints ; i++) {
         scores.add(random.nextInt(maxScore));
      }
      DrawGraph2 mainPanel = new DrawGraph2(scores);

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}