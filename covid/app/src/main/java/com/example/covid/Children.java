package com.example.covid;

public class Children {
    Total total;
    Today today;
    String name;

    public Children(Total total, Today today, String name) {
        this.total = total;
        this.today = today;
        this.name = name;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
