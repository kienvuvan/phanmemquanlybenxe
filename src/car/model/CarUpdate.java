/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.model;

import java.sql.Timestamp;

/**
 *
 * @author kienanh2903
 */
public class CarUpdate {

    private String maXe;
    private String bsx;
    private double giaVe;
    private String lichTrinh;
    private Timestamp ngayGui;
    private int tinhTrang;

    public CarUpdate() {
    }

    public CarUpdate(String maXe, String bsx, double giaVe, String lichTrinh) {
        this.maXe = maXe;
        this.bsx = bsx;
        this.giaVe = giaVe;
        this.lichTrinh = lichTrinh;
    }

    public CarUpdate(String maXe, String bsx, double giaVe, String lichTrinh, Timestamp ngayGui, int tinhTrang) {
        this.maXe = maXe;
        this.bsx = bsx;
        this.giaVe = giaVe;
        this.lichTrinh = lichTrinh;
        this.ngayGui = ngayGui;
        this.tinhTrang = tinhTrang;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getBsx() {
        return bsx;
    }

    public void setBsx(String bsx) {
        this.bsx = bsx;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public String getLichTrinh() {
        return lichTrinh;
    }

    public void setLichTrinh(String lichTrinh) {
        this.lichTrinh = lichTrinh;
    }

    public Timestamp getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Timestamp ngayGui) {
        this.ngayGui = ngayGui;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}
