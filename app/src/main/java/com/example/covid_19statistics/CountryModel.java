package com.example.covid_19statistics;

public class CountryModel {
    private String flag;
    private String cntry;
    private String cnfrm;
    private String todaycnfrm;
    private String totaldth;
    private String todaydth;
    private String recvr;
    private String active;
    private String totaltst;
    private String update;

    public CountryModel(String flag, String cntry, String cnfrm, String todaycnfrm, String totaldth, String todaydth, String recvr, String active, String totaltst,String update) {
        this.flag = flag;
        this.cntry = cntry;
        this.cnfrm = cnfrm;
        this.todaycnfrm = todaycnfrm;
        this.totaldth = totaldth;
        this.todaydth = todaydth;
        this.recvr = recvr;
        this.active = active;
        this.totaltst = totaltst;
        this.update=update;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCntry() {
        return cntry;
    }

    public void setCntry(String cntry) {
        this.cntry = cntry;
    }

    public String getCnfrm() {
        return cnfrm;
    }

    public void setCnfrm(String cnfrm) {
        this.cnfrm = cnfrm;
    }

    public String getTodaycnfrm() {
        return todaycnfrm;
    }

    public void setTodaycnfrm(String todaycnfrm) {
        this.todaycnfrm = todaycnfrm;
    }

    public String getTotaldth() {
        return totaldth;
    }

    public void setTotaldth(String totaldth) {
        this.totaldth = totaldth;
    }

    public String getTodaydth() {
        return todaydth;
    }

    public void setTodaydth(String todaydth) {
        this.todaydth = todaydth;
    }

    public String getRecvr() {
        return recvr;
    }

    public void setRecvr(String recvr) {
        this.recvr = recvr;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTotaltst() {
        return totaltst;
    }

    public void setTotaltst(String totaltst) {
        this.totaltst = totaltst;
    }
    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

}
