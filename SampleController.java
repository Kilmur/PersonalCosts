package application;

import java.io.IOException;
import java.sql.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {

    static DB db = new DB();

    @FXML
    ChoiceBox<String> choiceList;
    @FXML
    TextField textField;
    @FXML
    TableView<Costs> table;
    @FXML
    TableColumn<Costs, Date> columnDate;
    @FXML
    TableColumn<Costs, String> columnType;
    @FXML
    TableColumn<Costs, Integer> columnCost;


//             КНОПКА ДОБАВЛЕНИЯ
    public void btnAdd(ActionEvent actionEvent){

        String type = choiceList.getValue();
        try {
            int money = Integer.parseInt(textField.getText());
            java.util.Date utilDate = new java.util.Date();
            Date sqlDate = new Date(utilDate.getTime());
            Costs costs = new Costs(sqlDate, type, money);
            db.listNewCosts.add(costs);
            textField.setText("");
            System.out.println(db.listNewCosts);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            textField.requestFocus();
            infoDialog();
        }
    }
//                     КНОПКА ТАБЛИЦЫ
    public void btnTable(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("table.fxml"));
            stage.setTitle("Таблица расходов");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            setTable();

        } catch (IOException e) {
          e.printStackTrace();
        }
    }


    @FXML
    public void setTable(){
        columnDate.setCellValueFactory(new PropertyValueFactory<Costs, Date>("date"));
        columnType.setCellValueFactory(new PropertyValueFactory<Costs, String>("type"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Costs, Integer>("money"));
//        db.getDB();

        table.setItems(db.list);


    }







//                      КНОПКА ДИАГРАММЫ
    public void btnChart(ActionEvent actionEvent){

    }


//               ДИАЛОГОВОЕ ОКНО
    private void infoDialog(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Заголовок");
        alert.setHeaderText(null);
        alert.setContentText("Введите сумму целым числом");
        alert.showAndWait();
    }

    }
