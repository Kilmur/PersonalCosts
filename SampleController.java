package application;

import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController {

    static DB db = new DB();

    @FXML
    ChoiceBox<String> choiceList;
    @FXML
    TextField textField;



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
        new TableController().createWindow();
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
