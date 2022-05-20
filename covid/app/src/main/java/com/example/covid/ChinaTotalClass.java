package com.example.covid;

public class ChinaTotalClass {
    String timestamp;
    Data data;

    public ChinaTotalClass(String timestamp, Data data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
