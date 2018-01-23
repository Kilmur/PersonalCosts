package application;

import java.io.IOException;
import java.sql.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableController {

    @FXML
    TableView<Costs> table;
    @FXML
    TableColumn<Costs, Date> columnDate;
    @FXML
    TableColumn<Costs, String> columnType;
    @FXML
    TableColumn<Costs, Integer> columnCost;

    public void createTableWindow(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
            stage.setTitle("Таблица расходов");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        columnDate.setCellValueFactory(new PropertyValueFactory<Costs, Date>("date"));
        columnType.setCellValueFactory(new PropertyValueFactory<Costs, String>("type"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Costs, Integer>("money"));
        SampleController.db.getDB();
        table.setItems(SampleController.db.list);

    }


}
