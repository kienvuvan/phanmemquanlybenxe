/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartravel.model;

import cartravel.dao.CarTravelDao;
import cartravel.dao.MysqlCarTravelDao;
import guest.model.Guest;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public class CarTravel {

    private String maChuyenXe;
    private String bienSoXe;
    private Date ngayDi;
    private String thoiGian;
    private List<CarTravelDetail> listDetail;

    public CarTravel(String maChuyenXe, String bienSoXe, Date ngayDi, String thoiGian, List<CarTravelDetail> listDetail) {
        this.maChuyenXe = maChuyenXe;
        this.bienSoXe = bienSoXe;
        this.ngayDi = ngayDi;
        this.thoiGian = thoiGian;
        this.listDetail = listDetail;
    }

    public CarTravel() {
    }

    public CarTravel(String maChuyenXe, String bienSoXe, Date ngayDi, String thoiGian) {
        this.maChuyenXe = maChuyenXe;
        this.bienSoXe = bienSoXe;
        this.ngayDi = ngayDi;
        this.thoiGian = thoiGian;
    }

    public String getMaChuyenXe() {
        return maChuyenXe;
    }

    public void setMaChuyenXe(String maChuyenXe) {
        this.maChuyenXe = maChuyenXe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
    
    public List<CarTravelDetail> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<CarTravelDetail> listDetail) {
        this.listDetail = listDetail;
    }
    
    public CarTravelDao carTravelDao() {
        return new MysqlCarTravelDao();
    }
    public String generateCarTravelId(String bienSoXe, Date ngayDi, String thoiGian) {
        return carTravelDao().generateCarTravelId(bienSoXe, ngayDi, thoiGian);
    }
    
    public boolean checkCarTravelIdExit(String maChuyenXe) {
        return carTravelDao().checkCarTravelIdExit(maChuyenXe);
    }
    
    public int bookTicket(CarTravel carTravel, Guest guest) {
        return carTravelDao().bookTicket(carTravel, guest);
    }
    
    public boolean checkTicketForDestroy(String maChuyenXe, String cmt){
        return carTravelDao().checkTicketForDestroy(maChuyenXe, cmt);
    }
    
    public int unbookTicket(String maChuyenXe, String cmt) {
        return carTravelDao().unbookTicket(maChuyenXe, cmt);
    }
    
    public boolean creatCarTravel() {
        return carTravelDao().creatCarTravel();
    }
}
