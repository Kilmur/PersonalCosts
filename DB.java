package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class DB {

    ObservableList<Costs> list = FXCollections.observableArrayList();
    ArrayList<Costs> listNewCosts = new ArrayList<Costs>();

//  jdbc:mysql://localhost/dbname?useUnicode=true&characterEncoding=utf8
    private static final String url = "jdbc:mysql://localhost:3306/db1?characterEncoding=utf8";
    private static final String user = "root";
    private static final String password = "fertan";

    private static Connection con;
    private static Statement stmt;
    private static PreparedStatement prstmt;
    private static ResultSet rs;

//    Был конструктор. Сейчас заполняет list по вызову
    public DB(){
        try {
            String sql = "SELECT * FROM test"; // дописать норм назв табл ! ! !
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Date date = rs.getDate(2);
                String type = rs.getString(3);
                int money = rs.getInt(4);
                Costs costs = new Costs(date, type, money);
                list.add(costs);
            }
            System.out.println(list);// для теста
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { stmt.close(); } catch(SQLException se) { se.printStackTrace(); }
            try { con.close(); } catch(SQLException se) { se.printStackTrace(); }
        }
    }

    public void closeWindow(){

        String sql = "INSERT INTO test (date, type, money) VALUES (?, ?, ?)";
        try {
            con = DriverManager.getConnection(url, user, password);
            prstmt = con.prepareStatement(sql);
            for(Costs list: listNewCosts){
                prstmt.setDate(1, list.getDate());
                prstmt.setString(2, list.getType());
                prstmt.setInt(3, list.getMoney());
                prstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try { prstmt.close(); } catch(SQLException se) { se.printStackTrace(); }
            try { con.close(); } catch(SQLException se) { se.printStackTrace(); }
        }

    }



}
