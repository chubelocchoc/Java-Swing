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
public class KhachHang {
    private String maKH;
    private String hoTen;
    private String diaChi;
    private String soDT;
    private Date ngaySinh;
    private Date ngayDKy;
    private double doanhSo;
    private String loaiKH;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, String diaChi, String soDT, Date ngaySinh, Date ngayDKy, double doanhSo, String loaiKH) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.ngaySinh = ngaySinh;
        this.ngayDKy = ngayDKy;
        this.doanhSo = doanhSo;
        this.loaiKH = loaiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }
    
    public String getNgaySinhToString() {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    	return formatter.format(ngaySinh);
    }

    public Date getNgayDKy() {
        return ngayDKy;
    }

    public String getNgayDKyToString() {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    	return formatter.format(ngayDKy);
    }
    
    public double getDoanhSo() {
        return doanhSo;
    }

    public String getLoaiKH() {
        return loaiKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setNgayDKy(Date ngayDKy) {
        this.ngayDKy = ngayDKy;
    }

    public void setDoanhSo(double doanhSo) {
        this.doanhSo = doanhSo;
    }

    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }
    
    
    
}
