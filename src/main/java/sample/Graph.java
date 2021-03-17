package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class Graph {
    @FXML PieChart pieChart;
    public static int rangeScore[] = new int[4];
    public void setRangeScore(int[] rangeScore, Stage stage) {


        this.rangeScore = rangeScore;
        for (int i=0; i<4; i++) {
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("[3,4]", rangeScore[3]),
                        new PieChart.Data("5", rangeScore[0]),
                        new PieChart.Data("[1,2]", rangeScore[1]),
                        new PieChart.Data("[2,3]", rangeScore[2]));
        pieChart.setTitle("Score in range");
        pieChart.setData(pieChartData);
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Score in range");
//
//        ((Group) window.getRoot()).getChildren().add(chart);

    }
}

