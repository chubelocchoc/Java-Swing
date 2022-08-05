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
               if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
           }
           csdl.getStmt().close();
       } catch (Exception e) {
           e.printStackTrace();
       } finally{
            csdl.offStatement();
        }
       return list;
    }

    
    public KhachHang timTheoMaKH(String maKH){
    	KhachHang kh = null;
        String query = "select * from khachhang where makh = '" + maKH + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            if (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) kh = getKhachHang(rs);
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return kh;
    }
    
    public KhachHang kiemTraTonTaiMaKH(String maKH){
    	KhachHang kh = null;
        String query = "select * from khachhang where makh = '" + maKH + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            if (rs.next()) {                
            	kh = getKhachHang(rs);
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return kh;
    }
    
    public ArrayList<KhachHang> timTheoHoTen(String hoTen){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where hoten like N'%" + hoTen + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTheoDiaChi(String diaChi){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where dchi like N'%" + diaChi + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTheoSDT(String soDT){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where sodt like '%" + soDT + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTheoNgaySinh(Date ngaySinh){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where ngsinh = '" + ngaySinh + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTheoNgayDKy(Date ngayDKy){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where ngdk = '" + ngayDKy + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTheoDoanhSo(double doanhSo){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where doanhso = '" + doanhSo + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<KhachHang> timTheoLoaiKH(String loaiKH){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        String query = "select * from khachhang where loaikh like N'%" + loaiKH + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getKhachHang(rs));
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
    
    public int xoaMem(String makh){
        String query = "UPDATE KHACHHANG SET IS_DELETED = 'TRUE' WHERE MAKH = '"+makh+"'";
        return csdl.setDuLieu(query);
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
        String query = "select top "+top+" with ties * from khachhang order by doanhso desc";
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
    
    public ArrayList<KhachHang> topKhachHangMuaItNhat(int top){
        ArrayList<KhachHang> list = new ArrayList<>();
        String query = "select top "+top+" with ties * from khachhang order by doanhso asc";
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
   
   
//    public static void main(String[] args) {
//        KhachHangDB kh = new KhachHangDB();
//        //KhachHang p = new KhachHang("XXX", "dacapnhat", "dacapnhat", "dacapnhat", java.sql.Date.valueOf("1993-10-10"), java.sql.Date.valueOf("2006-10-10"), 598632.23, "XXXXX");
//        //kh.xoaKhachHang("XXX");
//        int tong = kh.tongSoLuongKhachHang();
//        System.out.println("tong = " + tong);
//        ArrayList<KhachHang> list = new ArrayList<>();
//        list = kh.tatCa();
//        list.forEach(s->System.out.println(s.getMaKH()+ ", " + s.getHoTen()+", " + s.getNgaySinh()+", " + s.getDoanhSo()));
//        kh.tatCa().forEach(s->System.out.println(s.getHoTen()));;
//        
//    }
}
