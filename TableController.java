package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableController implements Initializable{

    @FXML
    TableView<Costs> table;
    @FXML
    TableColumn<Costs, Date> columnDate;
    @FXML
    TableColumn<Costs, String> columnType;
    @FXML
    TableColumn<Costs, Integer> columnCost;

    public void createWindow(){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnDate.setCellValueFactory(new PropertyValueFactory<Costs, Date>("date"));
        columnType.setCellValueFactory(new PropertyValueFactory<Costs, String>("type"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Costs, Integer>("money"));
        SampleController.db.getDB();
        table.setItems(SampleController.db.list);

    }


}
