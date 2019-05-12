package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JPanel;

public class BarChart extends JPanel{
	private static final long serialVersionUID = 1L;
	private TreeMap<String, Integer> bars = new TreeMap<String, Integer>();
	private Font font = new Font("Arial", Font.BOLD,12);
	
	public void add(TreeMap<String, Integer>hashtagNumber, int threshold) {
		if(hashtagNumber!=null) {
			int occ=0;
			Set<String> keySet = hashtagNumber.keySet();
			for(String s:keySet){
				Integer value = hashtagNumber.get(s);
				if(value>occ) {
					addBar(s, value);
					System.out.println(s+" "+value);
					occ=value;
				}
			}
		}
		setSize(500,500);
	}//add
	
	public void removeAllHashtag() {
		bars.clear();
	}
	
	public void addBar(String string, int value){
		bars.put(string, value);
		this.setBackground(Color.DARK_GRAY);
		//key.add(string);
		repaint();
	}

	@Override	
	protected void paintComponent(Graphics g)	{
		if(bars.size()!=0) {
			// determine longest bar
			int max = Integer.MIN_VALUE;
			for (Integer value : bars.values()){
				max = Math.max(max, value);
			}
			// paint bars
			System.out.println(bars.size());
			int width = (getWidth() / bars.size()) - 120;
			g.setColor(Color.WHITE);	
			g.drawLine(30, 10, 30, getHeight()-25);
			g.drawLine(30, getHeight()-25, getWidth()-30, getHeight()-25);
			for (String s : bars.keySet()){
				int value = bars.get(s);
				int height = (int)((getHeight()-25) * ((double)value / max));
				g.setFont(font);
				g.setColor(Color.WHITE);
				g.drawString("- "+value, 10,getHeight()-height);
			}
			int x = 35;
			for (String s : bars.keySet()){
				g.setFont(font);
				int value = bars.get(s);		
				int height = (int)((getHeight()-25) * ((double)value / max));		
				g.setColor(Color.DARK_GRAY);
				g.fillRect(x, getHeight() - height, width, height-30);
				g.setColor(Color.WHITE);		
				g.drawRect(x, getHeight() - height, width, height-30);	
				g.setColor(Color.WHITE);	
				g.drawString(s.toUpperCase()+" #"+value, x, getHeight()-10);
				x += (width + 4);	
			}	
		}
	}//paintComponent

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 10 + 2, 50);
	}//getPreferredSize

 

//	public static void main(String[] args){	
		/*JFrame frame = new JFrame("Bar Chart");		
		BarChart chart = new BarChart();		
		chart.addBar("EARTHQUAKE",100);
		chart.addBar("TERREMOTO",8);		
		chart.addBar("SCOSSA",54);		
		chart.addBar("QUAKE",23);     		
		chart.addBar("dsfa",23);
		chart.addBar("EARTHQfdgUAKE",100);
		chart.addBar("TERsdfgREMOTO",8);		
		chart.addBar("SCOsdfgSSA",54);		
		chart.addBar("QUsdfAKE",23);     		
		chart.addBar("dsdffgfa",23);   
		frame.getContentPane().add(chart);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.pack();		
		frame.setBackground(Color.DARK_GRAY);
		frame.setSize(300, 300);
		frame.setVisible(true);	*/
//	}//main

}//BarChartmvb 