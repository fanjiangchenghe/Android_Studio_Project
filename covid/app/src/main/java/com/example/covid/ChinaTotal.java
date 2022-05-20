package com.example.covid;

public class ChinaTotal {
    Total total;
    Today today;

    public ChinaTotal(Total total, Today today) {
        this.total = total;
        this.today = today;
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
}

