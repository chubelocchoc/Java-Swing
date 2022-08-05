/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 20520
 */
public class HoaDon {
    private int soHoaDon;
    private Date ngayHoaDon;
    private String maKhachKhang;
    private String maNhanVien;
    private double triGia;

    public HoaDon() {
    }

    public HoaDon(int soHoaDon, Date ngayHoaDon, String maKhachKhang, String maNhanVien, double triGia) {
        this.soHoaDon = soHoaDon;
        this.ngayHoaDon = ngayHoaDon;
        this.maKhachKhang = maKhachKhang;
        this.maNhanVien = maNhanVien;
        this.triGia = triGia;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public Date getNgayHoaDon() {
        return ngayHoaDon;
    }

    public String getMaKhachKhang() {
        return maKhachKhang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public double getTriGia() {
        return triGia;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public void setNgayHoaDon(Date ngayHoaDon) {
        this.ngayHoaDon = ngayHoaDon;
    }

    public void setMaKhachKhang(String maKhachKhang) {
        this.maKhachKhang = maKhachKhang;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTriGia(double triGia) {
        this.triGia = triGia;
    }
    
    public String getNgayHoaDonToString() {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    	return formatter.format(ngayHoaDon);
    }
}
