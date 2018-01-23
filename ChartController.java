package application;

import java.io.IOException;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ChartController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    ObservableList<String> xList = FXCollections.observableArrayList();

    public void createChartWindow(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("chart.fxml"));
            stage.setTitle("Диаграмма расходов");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){

        SampleController.db.getDB();

        for(Costs costs: SampleController.db.list){
            Date date = costs.getDate();
            String s = "" + date;
            if(!xList.contains(s)){
                xList.add(s);
            }
        }
        System.out.println(xList);

        int[] mas = new int[xList.size()];
        
        int j = 0;
        
        for(String s: xList){
            for(Costs c: SampleController.db.list){
                String m = "" + c.getDate();
                if(m.equals(s)){
                    mas[j] = mas[j] + c.getMoney();
                }
            }
            j++;
        }
        for(int x = 0; x<mas.length; x++){
            System.out.println(mas[x]);
        }

        xAxis.setCategories(xList);
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for(int i = 0; i<mas.length; i++){
            series.getData().add(new XYChart.Data<>(xList.get(i), mas[i]));
        }
        barChart.getData().add(series);




    }








}
