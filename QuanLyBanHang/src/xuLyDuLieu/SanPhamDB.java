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
        return new SanPham(rs.getString("masp"), rs.getString("tensp"), rs.getString("dvt"), rs.getString("nuocsx"), rs.getDouble("gia"));
    }
    
    public ArrayList<SanPham> tatCa(){
        ArrayList<SanPham> list = new ArrayList<SanPham>();
        String query = "select * from sanpham";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getSanPham(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public SanPham timTheoMaSP(String maSP){
        SanPham sp = null;
        String query = "select * from sanpham where masp = '" + maSP + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            if (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) sp = getSanPham(rs);
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return sp;
    }
    
    public SanPham kiemTraTonTaiMaSP(String maSP){
        SanPham sp = null;
        String query = "select * from sanpham where masp = '" + maSP + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            if (rs.next()) {                
            	sp = getSanPham(rs);
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return sp;
    }
    
    public ArrayList<SanPham> timTheoTenSP(String tenSP){
        ArrayList<SanPham> list = new ArrayList<SanPham>();
        String query = "select * from sanpham where tensp like N'%" + tenSP + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timTheoDonViTinh(String donViTinh){
        ArrayList<SanPham> list = new ArrayList<SanPham>();
        String query = "select * from sanpham where dvt = N'" + donViTinh + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timTheoNuocSX(String nuocSX){
        ArrayList<SanPham> list = new ArrayList<SanPham>();
        String query = "select * from sanpham where nuocsx like N'%" + nuocSX + "%'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getSanPham(rs));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> timTheoGiaBan(double giaBan){
        ArrayList<SanPham> list = new ArrayList<SanPham>();
        String query = "select * from sanpham where gia = '" + giaBan + "'";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
            	if (rs.getString("IS_DELETED")==null) list.add(getSanPham(rs));
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
    
    public int xoaSanPham(String masp){
        String query = "delete from sanpham where masp = '"+masp+"'";
        return csdl.setDuLieu(query);
    }
    
    public int xoaMem(String masp){
        String query = "UPDATE SANPHAM SET IS_DELETED = 'TRUE' WHERE MASP = '"+masp+"'";
        return csdl.setDuLieu(query);
    }
    
    public ArrayList<SanPham> topSanPhamBanChay(int top){
    	ArrayList<SanPham> list = new ArrayList<SanPham>();
        
        //rs include tabs =  soluongsanphamdaban /t masp /t tensp /t nuocsx
        String query = "select top "+top+" with ties abc.tong as soluongln, abc.masp, sp.tensp, sp.nuocsx, sp.DVT, sp.GIA "
        		+ "from (select cthd.masp, sum (sl) as 'tong' from cthd group by masp) abc join sanpham sp "
        		+ "on abc.masp = sp.masp order by soluongln desc";
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
    
    public ArrayList<String> slSanPhamBanChay(int top){
    	ArrayList<String> list = new ArrayList();
        
        //rs include tabs =  soluongsanphamdaban /t masp /t tensp /t nuocsx
        String query = "select top "+top+" with ties abc.tong as soluongln "
        		+ "from (select cthd.masp, sum (sl) as 'tong' from cthd group by masp) abc join sanpham sp "
        		+ "on abc.masp = sp.masp order by soluongln desc";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(String.valueOf(rs.getInt("soluongln")));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
    }
    
    public ArrayList<SanPham> topSanPhamBanCham(int top){
    	ArrayList<SanPham> list = new ArrayList<SanPham>();
        
        //rs include tabs =  soluongsanphamdaban /t masp /t tensp /t nuocsx
        String query = "select top "+top+" with ties abc.tong as soluongln, abc.masp, sp.tensp, sp.nuocsx, sp.DVT, sp.GIA "
        		+ "from (select cthd.masp, sum (sl) as 'tong' from cthd group by masp) abc join sanpham sp "
        		+ "on abc.masp = sp.masp order by soluongln asc";
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
    
    public ArrayList<String> slSanPhamBanCham(int top){
    	ArrayList<String> list = new ArrayList();
        
        //rs include tabs =  soluongsanphamdaban /t masp /t tensp /t nuocsx
        String query = "select top "+top+" with ties abc.tong as soluongnn "
        		+ "from (select cthd.masp, sum (sl) as 'tong' from cthd group by masp) abc join sanpham sp "
        		+ "on abc.masp = sp.masp order by soluongnn asc";
        ResultSet rs = csdl.getDuLieu(query);
        try {
            while (rs.next()) {                
                list.add(String.valueOf(rs.getInt("soluongnn")));
            }
            csdl.getStmt().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            csdl.offStatement();
        }
        return list;
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
