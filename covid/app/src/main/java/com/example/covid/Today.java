package com.example.covid;

public class Today {
    String confirm,suspect,heal,dead,severe,storeConfirm,input;

    public Today(String confirm, String suspect, String heal, String dead, String severe, String storeConfirm, String input) {
        this.confirm = confirm;
        this.suspect = suspect;
        this.heal = heal;
        this.dead = dead;
        this.severe = severe;
        this.storeConfirm = storeConfirm;
        this.input = input;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getHeal() {
        return heal;
    }

    public void setHeal(String heal) {
        this.heal = heal;
    }

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    public String getSevere() {
        return severe;
    }

    public void setSevere(String severe) {
        this.severe = severe;
    }

    public String getStoreConfirm() {
        return storeConfirm;
    }

    public void setStoreConfirm(String storeConfirm) {
        this.storeConfirm = storeConfirm;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
