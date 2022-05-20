package com.example.covid;

public class ChinaDay {
    String date;
    Today today;
    Total total;

    public ChinaDay(String date, Today today, Total total) {
        this.date = date;
        this.today = today;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }
}
