/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuLyDuLieu;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.ChiTietHoaDon;
/**
 *
 * @author 20520
 */
public class ChiTietHoaDonDB {
    private KetNoiCSDL csdl;

    public ChiTietHoaDonDB() {
        csdl = new KetNoiCSDL();
    }
    
    private ChiTietHoaDon getChiTietHoaDon(ResultSet rs) throws Exception{
        return new ChiTietHoaDon(rs.getInt("sohd"), rs.getString("masp"), rs.getInt("sl"));
    }
    
    public ArrayList<ChiTietHoaDon> tatCa(){
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        String query = "select * from cthd";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getChiTietHoaDon(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<ChiTietHoaDon> timCTHD(int sohd){
        ArrayList<ChiTietHoaDon> list = new ArrayList();
        String query = "select * from cthd where sohd = '"
                + sohd + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getChiTietHoaDon(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    public ArrayList<ChiTietHoaDon> timCTHD(String masp){
        ArrayList<ChiTietHoaDon> list = new ArrayList();
        String query = "select * from cthd where masp = '"
                + masp + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getChiTietHoaDon(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public void themCTHD(ChiTietHoaDon cthd){
        String query = "insert into cthd (sohd,masp,sl) values "
	+ "('"+cthd.getSoHoaDon()+"','"+cthd.getMaSP()+"','"+cthd.getSoLuong()+"')";
        csdl.setDuLieu(query);
    }
    
    public void capNhatThongTin(ChiTietHoaDon cthd){
        String query = "update cthd set sl = '" +cthd.getSoLuong()+
                "' where sohd = '" +cthd.getSoHoaDon()+"' and masp = '" + cthd.getMaSP()+"'";
        csdl.setDuLieu(query);
    }
    
    public void xoaCTHD(int mahd, String masp){
        String query = "delete from cthd where sohd = '"+mahd+"' and masp = '" + masp + "'";
        csdl.setDuLieu(query);
    }
    
    
//    public static void main(String[] args) {
//        ChiTietHoaDonDB kh = new ChiTietHoaDonDB();
//        ChiTietHoaDon p = new ChiTietHoaDon(1023, "ST01", 222);
//        //kh.themCTHD(p);
//        kh.xoaCTHD(1023, "ST01");
//        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
//        list = kh.tatCa();
//        list.forEach(s->System.out.println(s.getSoHoaDon()+ ", " + s.getMaSP()+", " + s.getSoLuong()));
//    }
}
