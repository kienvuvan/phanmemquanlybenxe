/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.model;

/**
 *
 * @author kienanh2903
 */
public class CarTravelDetail {

    private String maChuyenXe;
    private String maHanhKhach;

    public CarTravelDetail() {
    }

    public CarTravelDetail(String maChuyenXe, String maHanhKhach) {
        this.maChuyenXe = maChuyenXe;
        this.maHanhKhach = maHanhKhach;
    }

    public String getMaChuyenXe() {
        return maChuyenXe;
    }

    public void setMaChuyenXe(String maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
    }

    public String getMaHanhKhach() {
        return maHanhKhach;
    }

    public void setMaHanhKhach(String maHanhKhach) {
        this.maHanhKhach = maHanhKhach;
    }
}
