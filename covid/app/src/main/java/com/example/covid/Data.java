package com.example.covid;

import java.util.List;

public class Data {
    ChinaTotal chinaTotal;
    List<ChinaDay> chinaDayList;
    List<Area> areaTree;
    String lastUpdateTime;


    public Data(ChinaTotal chinaTotal, List<ChinaDay> chinaDayList, List<Area> areaTree, String lastUpdateTime) {
        this.chinaTotal = chinaTotal;
        this.chinaDayList = chinaDayList;
        this.areaTree = areaTree;
        this.lastUpdateTime = lastUpdateTime;
    }

    public ChinaTotal getChinaTotal() {
        return chinaTotal;
    }

    public void setChinaTotal(ChinaTotal chinaTotal) {
        this.chinaTotal = chinaTotal;
    }

    public List<ChinaDay> getChinaDayList() {
        return chinaDayList;
    }

    public void setChinaDayList(List<ChinaDay> chinaDayList) {
        this.chinaDayList = chinaDayList;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<Area> getAreaTree() {
        return areaTree;
    }

    public void setAreaTree(List<Area> areaTree) {
        this.areaTree = areaTree;
    }
}
