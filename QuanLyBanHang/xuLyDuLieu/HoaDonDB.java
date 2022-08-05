/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuLyDuLieu;

import model.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 20520
 */
public class HoaDonDB {
    private KetNoiCSDL csdl;

    public HoaDonDB() {
        csdl = new KetNoiCSDL();
    }
    
    private HoaDon getHoaDon(ResultSet rs)throws Exception{
        return new HoaDon(rs.getInt("sohd"), rs.getDate("nghd"), rs.getString("makh"), rs.getString("makh"), rs.getDouble("trigia"));
    }
    
    public ArrayList<HoaDon> tatCa(){
        ArrayList<HoaDon> list = new ArrayList<>();
        String query = "select * from hoadon";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getHoaDon(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public HoaDon timHoaDon(int sohd){
        HoaDon list = null;
        String query = "select * from hoadon where sohd = '"
                + sohd + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list = getHoaDon(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<HoaDon> timHoaDon(Date date){
        ArrayList<HoaDon> list = new ArrayList();
        String query = "select * from hoadon where nghd = '"
                + date + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getHoaDon(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<HoaDon> timHoaDon(String maso){
        ArrayList<HoaDon> list = new ArrayList();
        String query = "select * from hoadon where makh = '"
                + maso + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getHoaDon(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<HoaDon> timHoaDon(double trigia){
        ArrayList<HoaDon> list = new ArrayList();
        String query = "select * from hoadon where trigia = '"
                + trigia + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getHoaDon(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public void themHoaDon(HoaDon hd){
        String query = "insert into hoadon (sohd,nghd,makh,manv,trigia) values "
	+ "('"+hd.getSoHoaDon()+"','"+hd.getNgayHoaDon()+"','"+hd.getMaKhachKhang()+"','"+hd.getMaNhanVien()+"','"+hd.getTriGia()+"')";
        csdl.setDuLieu(query);
    }
    
    public void capNhatThongTin(HoaDon hd){
        String query = "update hoadon set nghd = '" +hd.getNgayHoaDon()+"', makh = '"+hd.getMaKhachKhang()
                +"', manv = '" + hd.getMaNhanVien()+"', trigia = '"+hd.getTriGia()+
                "' where sohd = '" +hd.getSoHoaDon()+"'";
        csdl.setDuLieu(query);
    }
    
    public double tongDoanhSo(){
        double tong = 0;
        String query = "select SUM(hoadon.trigia) tong from hoadon";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                tong = tong + rs.getDouble("tong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return tong;
    }
    
    public ArrayList<HoaDon> topHoaDonCaoNhat(int top){
        ArrayList<HoaDon> list = new ArrayList<>();
        String query = "select top "+top+" with ties trigia as trigia,sohd, makh"
                + " from hoadon order by trigia desc";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getHoaDon(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public void xoaHoaDon(int mahd){
        String query = "delete from hoadon where sohd = '"+mahd+"'";
        csdl.setDuLieu(query);
    }
    
//    public static void main(String[] args) {
//        HoaDonDB kh = new HoaDonDB();
//        //HoaDon p = new HoaDon(1111, java.sql.Date.valueOf("2010-10-10"), "KH01", "NV01", 10210.25);
//        //kh.themHoaDon(p);
//        //kh.xoaHoaDon(1111);
//        double tong = kh.tongDoanhSo();
//        System.out.println("tong la: " + tong);
//        ArrayList<HoaDon> list = new ArrayList<>();
//        list = kh.tatCa();
//        list.forEach(s->System.out.println(s.getSoHoaDon()+ ", " + s.getNgayHoaDon()));
//    }
}
