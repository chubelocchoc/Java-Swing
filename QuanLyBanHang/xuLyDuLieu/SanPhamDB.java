/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuLyDuLieu;


import java.sql.ResultSet;
import java.util.ArrayList;
import model.SanPham;
/**
 *
 * @author 20520
 */
public class SanPhamDB {
    private KetNoiCSDL csdl;

    public SanPhamDB() {
        csdl = new KetNoiCSDL();
    }
    
    private SanPham getSanPham(ResultSet rs)throws Exception{
        return new SanPham(rs.getString("masp"), rs.getString("tensp"), rs.getString("dvt"), rs.getString("nuocsx"), rs.getInt("gia"));
    }
    
    public ArrayList<SanPham> tatCa(){
        ArrayList<SanPham> list = new ArrayList();
        String query = "select * from sanpham";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getSanPham(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public SanPham timMaSanPham(String masp){
        SanPham list = null;
        String query = "select * from sanpham where masp = '" + masp + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list = getSanPham(rs);
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timTenSanPham(String ten){
        ArrayList<SanPham> list = new ArrayList();
        String query = "select * from sanpham where tensp like N'%" + ten + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timDonViTinhSanPham(String dvt){
        ArrayList<SanPham> list = new ArrayList();
        String query = "select * from sanpham where dvt = N'" + dvt + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timNuocSXSanPham(String nuoc){
        ArrayList<SanPham> list = new ArrayList();
        String query = "select * from sanpham where nuocsx like N'%" + nuoc + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timGiaSanPham(double gia){
        ArrayList<SanPham> list = new ArrayList();
        String query = "select * from sanpham where gia = '" + gia + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public void themSanPham(SanPham sp){
        String query = "insert into sanpham (masp,tensp,dvt,nuocsx,gia) values "
	+ "('"+sp.getMaSP()+"',N'"+sp.getTenSP()+"',N'"+sp.getDonViTinh()+"',N'"+sp.getNuocSX()+"','"+sp.getGiaBan()+"')";
        csdl.setDuLieu(query);
    }
    
    public void capNhatThongTin(SanPham sp){
        String query = "update sanpham set tensp = N'" +sp.getTenSP()+"', dvt = N'"+sp.getDonViTinh()
                +"', nuocsx = N'" + sp.getNuocSX()+"', gia = '"+sp.getGiaBan()+
                "' where masp = '" +sp.getMaSP()+"'";
        csdl.setDuLieu(query);
    }
    
    public void xoaSanPham(String masp){
        String query = "delete from sanpham where masp = '"+masp+"'";
        csdl.setDuLieu(query);
    }
    
    public ResultSet topSanPhamBanChay(int top){
        
        
        //rs include tabs =  soluongsanphamdaban /t masp /t tensp /t nuocsx
        String query = "select top "+top+" with ties abc.tong as soluongln, abc.masp, sp.tensp, sp.nuocsx\n" +
            "from (select cthd.masp, sum (sl) as 'tong' from cthd group by masp) abc join sanpham sp on abc.masp = sp.masp\n" +
            "order by soluongln desc";
        ResultSet rs = csdl.getDuLieu(query);
        return rs;
        //rs include tabs =  soluongsanphamdaban /t masp /t tensp /t nuocsx
    }
    
//    public static void main(String[] args) {
//        SanPhamDB sp = new SanPhamDB();
//        //SanPham p = new SanPham("XXX", "dâcpnhat", "cây", "Việt Nam", 5000);
//        sp.xoaSanPham("XXX");
//        ArrayList<SanPham> list = new ArrayList<>();
//        list = sp.tatCa();
//        list.forEach(s->System.out.println(s.getMaSP() + ", " + s.getTenSP()));
//    }
}
