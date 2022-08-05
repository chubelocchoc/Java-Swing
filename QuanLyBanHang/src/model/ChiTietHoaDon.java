/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 20520
 */
public class ChiTietHoaDon {
    private int soHoaDon;
    private String maSP;
    private int soLuong;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int soHoaDon, String maSP, int soLuong) {
        this.soHoaDon = soHoaDon;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
