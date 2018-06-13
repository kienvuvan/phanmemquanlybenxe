/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carfee.model;

import carfee.dao.CarFeeDetailDao;
import carfee.dao.MysqlCarFeeDetailDao;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author kienanh2903
 */
public class CarFeeDetail {

    private String cmtChuXe;
    private String bsx;
    private Date ngayThu;
    private int soChuyen;
    private Double phi;

    public CarFeeDetail() {
    }

    public CarFeeDetail(String cmtChuXe, String bsx, Date ngayThu, int soChuyen, Double phi) {
        this.cmtChuXe = cmtChuXe;
        this.bsx = bsx;
        this.ngayThu = ngayThu;
        this.soChuyen = soChuyen;
        this.phi = phi;
    }

    public CarFeeDetail(String bsx, int soChuyen, Double phi) {
        this.bsx = bsx;
        this.soChuyen = soChuyen;
        this.phi = phi;
    }

    public String getCmtChuXe() {
        return cmtChuXe;
    }

    public void setCmtChuXe(String cmtChuXe) {
        this.cmtChuXe = cmtChuXe;
    }

    public String getBsx() {
        return bsx;
    }

    public void setBsx(String bsx) {
        this.bsx = bsx;
    }

    public Date getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(Date ngayThu) {
        this.ngayThu = ngayThu;
    }

    public int getSoChuyen() {
        return soChuyen;
    }

    public void setSoChuyen(int soChuyen) {
        this.soChuyen = soChuyen;
    }

    public Double getPhi() {
        return phi;
    }

    public void setPhi(Double phi) {
        this.phi = phi;
    }

    public CarFeeDetailDao carFeeDetailDao() {
        return new MysqlCarFeeDetailDao();
    }

    public List<CarFeeDetail> getCarFeeDetailByCarOwner(String cmtChuXe, Date ngayThu) {
        return carFeeDetailDao().getCarFeeDetailByCarOwner(cmtChuXe, ngayThu);
    }
    
    public List<CarFeeDetail> getAllCarFeeDetailByCarOwner(String cmtChuXe) {
        return carFeeDetailDao().getAllCarFeeDetailByCarOwner(cmtChuXe);
    }
    
    public List<CarFeeDetail> searchCarFeeDetailByCarOwner(String cmtChuXe, String key) {
        return carFeeDetailDao().searchCarFeeDetailByCarOwner(cmtChuXe, key);
    }
}
