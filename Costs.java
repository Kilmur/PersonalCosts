package application;

import java.sql.Date;

public class Costs {

    private Date date;
    private String type;
    private int money;

    public Costs(Date date, String type, int money) {
        this.date = date;
        this.type = type;
        this.money = money;
    }

    @Override
    public String toString() {
        return "date=" + date + ", type=" + type + ", money=" + money + "\n";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }





}
