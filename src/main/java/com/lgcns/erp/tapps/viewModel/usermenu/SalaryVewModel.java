package com.lgcns.erp.tapps.viewModel.usermenu;

import java.sql.Date;

/**
 * Created by Muslimbek on 11/14/2016.
 */
public class SalaryVewModel {
    private String net;
    private String gross;
    private Date date;
    private double pit;
    private double inps;
    private double pf;
    private int id;
    private int currency;

    public SalaryVewModel(String gross, String net, Date date, double pit, double inps, double pf, int id, int currency) {
        this.net = net;
        this.gross = gross;
        this.date = date;
        this.pit = pit;
        this.inps = inps;
        this.pf = pf;
        this.id = id;
        this.currency = currency;
    }

    public SalaryVewModel() {

    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPit() {
        return pit;
    }

    public void setPit(double pit) {
        this.pit = pit;
    }

    public double getInps() {
        return inps;
    }

    public void setInps(double inps) {
        this.inps = inps;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}
