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
public class NhanVien {
    private String maNV;
    private String hoTen;
    private String soDT;
    private Date ngVL;
    private String matKhau;
    private String vaiTro;

    public NhanVien(String maNV, String hoTen, String soDT, Date ngVL, String matKhau, String vaiTro) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.soDT = soDT;
        this.ngVL = ngVL;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public NhanVien() {
    }

    public String getMaNV() {
        return maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDT() {
        return soDT;
    }

    public Date getNgVL() {
        return ngVL;
    }
    
    public String getNgayVLToString() {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
    	return formatter.format(ngVL);
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public void setNgVL(Date ngVL) {
        this.ngVL = ngVL;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
}
