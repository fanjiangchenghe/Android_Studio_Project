package com.example.covid;

import java.util.List;

public class Area {
    Today today;
    Total total;
    String name;
    List<Children> childrenList;

    public Area(Today today, Total total, String name, List<Children> childrenList) {
        this.today = today;
        this.total = total;
        this.name = name;
        this.childrenList = childrenList;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }
}
