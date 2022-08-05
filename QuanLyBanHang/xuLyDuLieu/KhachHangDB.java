/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuLyDuLieu;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.KhachHang;

/**
 *
 * @author 20520
 */
public class KhachHangDB {
    private KetNoiCSDL csdl;

    public KhachHangDB() {
        csdl = new KetNoiCSDL();
    }
   
    private KhachHang getKhachHang(ResultSet rs) throws Exception{
       return new KhachHang(rs.getString("makh"), rs.getString("hoten"), rs.getString("dchi"), rs.getString("sodt"), rs.getDate("ngsinh"), rs.getDate("ngdk"), rs.getDouble("doanhso"), rs.getString("loaikh"));
    }
   
    public ArrayList<KhachHang> tatCa(){
       ArrayList<KhachHang> list = new ArrayList<>();
       String query = "select * from khachhang";
       ResultSet rs = csdl.getDuLieu(query);
       try {
           while (rs.next()) {               
               list.add(getKhachHang(rs));
           }
           csdl.getStmt().close();
       } catch (Exception e) {
           e.printStackTrace();
       } finally{
            csdl.offStatement();
        }
       return list;
    }
   
    public KhachHang timMaKhachHang(String makh){
        KhachHang list = null;
        String query = "select * from khachhang where makh = '" + makh + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list = getKhachHang(rs);
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTenKhachHang(String hoten){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where hoten like N'%" + hoten + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timDiaChiKhachHang(String dchi){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where dchi like N'%" + dchi + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timSoDienThoaiKhachHang(String sodt){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where sodt like '%" + sodt + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timNgaySinhKhachHang(Date date){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where ngsinh = '" + date + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timNgayDKYKhachHang(Date date){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where ngdk = '" + date + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timDoanhSoKhachHang(double doanhso){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where doanhso = '" + doanhso + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timLoaiKhachHang(String loaikh){
        ArrayList<KhachHang> list = new ArrayList();
        String query = "select * from khachhang where loaikh like N'%" + loaikh + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
   
    public void themKhachHang(KhachHang kh){
        String query = "insert into khachhang (makh,hoten,dchi,sodt,ngsinh,ngdk,doanhso,loaikh) values "
	+ "('"+kh.getMaKH()+"',N'"+kh.getHoTen()+"',N'"+kh.getDiaChi()+"','"+kh.getSoDT()+"','"+kh.getNgaySinh()+"','"+kh.getNgayDKy()+"','"+kh.getDoanhSo()+"',N'"+kh.getLoaiKH()+"')";
        csdl.setDuLieu(query);
    }
    
    public void capNhatThongTin(KhachHang kh){
        String query = "update khachhang set hoten = N'" +kh.getHoTen()+"', dchi = N'"+kh.getDiaChi()
                +"', sodt = '" + kh.getSoDT()+"', ngsinh = '"+kh.getNgaySinh()+"', ngdk = '"+kh.getNgayDKy()+ "', doanhso = '"+kh.getDoanhSo()+"', loaikh = N'"+kh.getLoaiKH()+
                "' where makh = '" +kh.getMaKH()+"'";
        csdl.setDuLieu(query);
    }
    
    
    public void xoaKhachHang(String makh){
        String query = "delete from khachhang where makh = '"+makh+"'";
        csdl.setDuLieu(query);
    }
    
    public int tongSoLuongKhachHang(){
        int tong = 0;
        String query = "select count(*) tong from khachhang";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                tong =tong + rs.getInt("tong");
            }
            
        } catch (Exception e) {
        } finally{
            csdl.offStatement();
        }
        
        return tong;
    }
    
    public ArrayList<KhachHang> topKhachHangMuaNhieuNhat(int top){
        ArrayList<KhachHang> list = new ArrayList<>();
        String query = "select top "+top+" with ties doanhso as doanhso, makh, hoten, sodt"
                + " from khachhang order by doanhso desc";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getKhachHang(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
   
   
    public static void main(String[] args) {
        KhachHangDB kh = new KhachHangDB();
        //KhachHang p = new KhachHang("XXX", "dacapnhat", "dacapnhat", "dacapnhat", java.sql.Date.valueOf("1993-10-10"), java.sql.Date.valueOf("2006-10-10"), 598632.23, "XXXXX");
        //kh.xoaKhachHang("XXX");
        int tong = kh.tongSoLuongKhachHang();
        System.out.println("tong = " + tong);
        ArrayList<KhachHang> list = new ArrayList<>();
        list = kh.tatCa();
        list.forEach(s->System.out.println(s.getMaKH()+ ", " + s.getHoTen()+", " + s.getNgaySinh()+", " + s.getDoanhSo()));
    }
}
