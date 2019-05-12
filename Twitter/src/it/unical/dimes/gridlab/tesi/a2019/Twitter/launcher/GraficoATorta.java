package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.util.Collection;

import javax.swing.JLabel;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GraficoATorta extends Application{
	private Scene scene;


	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		
	}
	private void init(Stage primaryStage) {
		HBox root=new HBox();
		scene=new Scene(root, 450, 330);
		CategoryAxis xAxis=new CategoryAxis();
		xAxis.setLabel("HASHTAG");
		NumberAxis yAxis=new NumberAxis();
		yAxis.setLabel("OCCORRENZE");
		LineChart<String, Number>lineChart=new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("ANALISI HASHTAG");
		XYChart.Series<String, Number>data=new XYChart.Series<>();
		data.getData().add(new XYChart.Data<String, Number>("#EARTHQUAKE", 150));
		data.getData().add(new XYChart.Data<String, Number>("#TERREMOTO", 101));
		data.getData().add(new XYChart.Data<String, Number>("#CALIFORNIA", 80));
		data.getData().add(new XYChart.Data<String, Number>("#QUAKE", 525));
		data.getData().add(new XYChart.Data<String, Number>("#ALLARM", 78));
	
		
		lineChart.getData().add(data);
		root.getChildren().add(lineChart);
	
		
		primaryStage.setTitle("ANALISI");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public static void main(String...args) {
		GraficoATorta g=new GraficoATorta();
		g.launch();
	}

}
