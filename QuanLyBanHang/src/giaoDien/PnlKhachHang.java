package giaoDien;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;
import tienIch.AppConstants;
import tienIch.AppHelper;
import xuLyDuLieu.ChiTietHoaDonDB;
import xuLyDuLieu.HoaDonDB;
import xuLyDuLieu.KhachHangDB;
import xuLyDuLieu.NhanVienDB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PnlKhachHang extends JPanel {
	
	private JPanel pnlDoiTTKH;
	private JTextField txtTimKiem;
	private JTable tblHienThi;
	private JPanel pnlThemKH;
	private JButton btnHienCN;
	private JButton btnHienThem;
	private JLabel lblChuyenHD;
	private JTextField txtMaKHThem;
	private JTextField txtHoTenThem;
	private JTextField txtDiaChiThem;
	private JTextField txtLoaiKHThem;
	private JTextField txtHoTenCN;
	private JTextField txtDiaChiCN;
	private JTextField txtLoaiKHCN;
	private JTextField txtSoDTThem;
	private JTextField txtSoDTCN;
	private JButton btnTimKiem;
	private JLabel lblSoKHTimThay;
	private JLabel lblTongDS;
	private JLabel lblDoanhSoTB;
	private JLabel lblKHCoDSThapNhat;
	private JLabel lblKHCoDSCaoNhat;
	private JLabel lblMaKH;
	private JLabel lblHoTen;
	private JLabel lblNgaySinh;
	private JLabel lblNgayDK;
	private JLabel lblSDT;
	private JLabel lblLoaiKH;
	private JLabel lblDiaChi;
	private JLabel lblDoanhSo;
	
	//data
	private List<KhachHang> listKH = null;
	private KhachHang khHienTai = null;
	private KhachHangDB khDB = new KhachHangDB();
	private JDateChooser txtNgSinhCN;
	private JDateChooser txtNgayDKCN;
	private JDateChooser txtNgSinhThem;
	private JDateChooser txtNgDKThem;
	private ChuyenPanelTheoDanhMuc controller;

	public KhachHang getKhHienTai() {
		return khHienTai;
	}
	public void setController(ChuyenPanelTheoDanhMuc controller) {
		this.controller = controller;
	}
	public ChuyenPanelTheoDanhMuc getController() {
		return controller;
	}
	/**
	 * Create the panel.
	 */
	public PnlKhachHang() {
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(AppConstants.MAU_TIM_NHAT_3));
		panel_1.setBounds(743, 0, 401, 814);
		add(panel_1);
		panel_1.setLayout(null);
		
		pnlDoiTTKH = new JPanel();
		pnlDoiTTKH.setVisible(false);
		
		pnlThemKH = new JPanel();
		pnlThemKH.setVisible(false);
		pnlThemKH.setBounds(10, 454, 381, 349);
		panel_1.add(pnlThemKH);
		pnlThemKH.setLayout(null);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Thêm khách hàng mới?") == JOptionPane.OK_OPTION) {
						String maKH = txtMaKHThem.getText();
						String hoTen = txtHoTenThem.getText();
						Date ngSinhRaw = txtNgSinhThem.getDate();
						Date ngDKRaw = txtNgDKThem.getDate();
						String loaiKH = txtLoaiKHThem.getText();
						String diaChi = txtDiaChiThem.getText();
						String soDT = txtSoDTThem.getText();
						
						//kiem tra nhap
						if (maKH.equals("")||hoTen.equals("")||diaChi.equals("")||soDT.equals("")||loaiKH.equals("")) {
							AppHelper.thongBao(getRootPane(), "Vui lòng nhập đầy đủ thông tin!");
							return;
						}
						if (ngSinhRaw == null||ngDKRaw == null) {
							AppHelper.thongBao(getRootPane(), "Ngày không hợp lệ. Vui lòng ấn nút bên phải để chọn ngày!");
							return;
						}
						
						//Kiem tra maKH
						if (khDB.kiemTraTonTaiMaKH(maKH)!=null) {
							AppHelper.thongBao(getRootPane(), "Mã khách hàng đã tồn tại!");
							return;
						}
						
						//update database
						java.sql.Date ngSinh = new java.sql.Date(ngSinhRaw.getTime());
						java.sql.Date ngDK = new java.sql.Date(ngDKRaw.getTime());
						khHienTai = new KhachHang(maKH, hoTen, diaChi, soDT, ngSinh, ngDK, 0, loaiKH);					
						khDB.themKhachHang(khHienTai);
				
						//update panel
						listKH = new ArrayList<KhachHang>();
						listKH.add(khHienTai);
						hienThi();
						
						// set info
						resetInfo();
						pnlThemKH.setVisible(false);
						btnHienThem.setBackground(Color.WHITE);
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiThem(getRootPane());
				}	
			}
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnThem.setFocusable(false);
		btnThem.setBorder(null);
		btnThem.setBackground(new Color(AppConstants.MAU_TIM));
		btnThem.setBounds(122, 296, 249, 38);
		pnlThemKH.add(btnThem);
		
		JButton btnHuyThem = new JButton("Hủy");
		btnHuyThem.setForeground(Color.WHITE);
		btnHuyThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyThem.setFocusable(false);
		btnHuyThem.setBorder(null);
		btnHuyThem.setBackground(new Color(73, 80, 87));
		btnHuyThem.setBounds(10, 297, 102, 38);
		pnlThemKH.add(btnHuyThem);
		btnHuyThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(Color.WHITE);
				pnlThemKH.setVisible(false);
			}
		});
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Mã KH:");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1.setBounds(10, 10, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1);
		
		txtMaKHThem = new JTextField();
		txtMaKHThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtMaKHThem.setBounds(122, 9, 249, 32);
		pnlThemKH.add(txtMaKHThem);
		txtMaKHThem.setColumns(10);
		
		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("Họ tên:");
		lblNewLabel_3_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1.setBounds(10, 51, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1_1);
		
		txtHoTenThem = new JTextField();
		txtHoTenThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtHoTenThem.setColumns(10);
		txtHoTenThem.setBounds(122, 50, 249, 32);
		pnlThemKH.add(txtHoTenThem);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_3_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1.setBounds(10, 215, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1_1_1);
		
		txtDiaChiThem = new JTextField();
		txtDiaChiThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtDiaChiThem.setColumns(10);
		txtDiaChiThem.setBounds(122, 214, 249, 32);
		pnlThemKH.add(txtDiaChiThem);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_1 = new JLabel("Ngày sinh:");
		lblNewLabel_3_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_1.setBounds(10, 92, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2 = new JLabel("Ngày ĐK:");
		lblNewLabel_3_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2.setBounds(10, 133, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1_1_1_2);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2_1 = new JLabel("Loại KH:");
		lblNewLabel_3_1_1_1_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2_1.setBounds(10, 174, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1_1_1_2_1);
		
		txtLoaiKHThem = new JTextField();
		txtLoaiKHThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtLoaiKHThem.setColumns(10);
		txtLoaiKHThem.setBounds(122, 173, 249, 32);
		pnlThemKH.add(txtLoaiKHThem);
		
		txtNgSinhThem = new JDateChooser();
		txtNgSinhThem.setLocale(new Locale("vi", "VN"));
		txtNgSinhThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNgSinhThem.setBounds(122, 91, 249, 32);
		pnlThemKH.add(txtNgSinhThem);
		
		txtNgDKThem = new JDateChooser();
		txtNgDKThem.setLocale(new Locale("vi", "VN"));
		txtNgDKThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNgDKThem.setBounds(122, 132, 249, 32);
		pnlThemKH.add(txtNgDKThem);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_4 = new JLabel("Số ĐT:");
		lblNewLabel_3_1_1_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_4.setBounds(10, 256, 102, 31);
		pnlThemKH.add(lblNewLabel_3_1_1_1_1_1_1_4);
		
		txtSoDTThem = new JTextField();
		txtSoDTThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtSoDTThem.setColumns(10);
		txtSoDTThem.setBounds(122, 255, 249, 32);
		pnlThemKH.add(txtSoDTThem);
		pnlDoiTTKH.setBounds(10, 454, 381, 327);
		panel_1.add(pnlDoiTTKH);
		pnlDoiTTKH.setLayout(null);
		
		JButton btnCapNhat = new JButton("Đổi");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Cập nhật thông tin khách hàng có mã \""+khHienTai.getMaKH()+"\"?") 
							== JOptionPane.OK_OPTION) {
						String hoTen = txtHoTenCN.getText();
						Date ngSinhRaw = txtNgSinhCN.getDate();
						Date ngDKRaw = txtNgayDKCN.getDate();
						String loaiKH = txtLoaiKHCN.getText();
						String diaChi = txtDiaChiCN.getText();
						String soDT = txtSoDTCN.getText();
						
						//update database
						java.sql.Date ngSinh = new java.sql.Date(ngSinhRaw.getTime());
						java.sql.Date ngDK = new java.sql.Date(ngDKRaw.getTime());
						khHienTai.setHoTen(hoTen);
						khHienTai.setNgaySinh(ngSinh);
						khHienTai.setNgayDKy(ngDK);
						khHienTai.setLoaiKH(loaiKH);
						khHienTai.setDiaChi(diaChi);
						khHienTai.setSoDT(soDT);
						khDB.capNhatThongTin(khHienTai);
						
						//update panel
						listKH = new ArrayList<KhachHang>();
						listKH.add(khHienTai);
						hienThi();
						
						// set info
						Locale lc = new Locale("vi","VN");
						NumberFormat nf = NumberFormat.getInstance(lc);
						lblMaKH.setText(khHienTai.getMaKH());
						lblHoTen.setText(khHienTai.getHoTen());
						lblNgaySinh.setText(khHienTai.getNgaySinhToString());
						lblSDT.setText(khHienTai.getSoDT());
						lblNgayDK.setText(khHienTai.getNgayDKyToString());
						lblLoaiKH.setText(khHienTai.getLoaiKH());
						lblDiaChi.setText("<HTML>"+khHienTai.getDiaChi()+"<HTML>");
						lblDoanhSo.setText(nf.format(khHienTai.getDoanhSo())+" VNĐ");
						pnlDoiTTKH.setVisible(false);
						btnHienCN.setBackground(Color.WHITE);
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiCapNhat(getRootPane());
				}	
			}
		});
		btnCapNhat.setFocusable(false);
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 18));
		btnCapNhat.setBorder(null);
		btnCapNhat.setBackground(new Color(AppConstants.VIOLET));
		btnCapNhat.setBounds(122, 269, 249, 44);
		pnlDoiTTKH.add(btnCapNhat);
		
		JButton btnHuyCN = new JButton("Hủy");
		btnHuyCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTKH.setVisible(false);
			}
		});
		btnHuyCN.setFocusable(false);
		btnHuyCN.setForeground(Color.WHITE);
		btnHuyCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyCN.setBorder(null);
		btnHuyCN.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyCN.setBounds(10, 269, 102, 44);
		pnlDoiTTKH.add(btnHuyCN);
		
		JLabel lblNewLabel_3_1_1_1_1_1_2 = new JLabel("Họ tên:");
		lblNewLabel_3_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_2.setBounds(10, 12, 102, 31);
		pnlDoiTTKH.add(lblNewLabel_3_1_1_1_1_1_2);
		
		txtHoTenCN = new JTextField();
		txtHoTenCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtHoTenCN.setColumns(10);
		txtHoTenCN.setBounds(122, 11, 249, 32);
		pnlDoiTTKH.add(txtHoTenCN);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_3 = new JLabel("Địa chỉ:");
		lblNewLabel_3_1_1_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_3.setBounds(10, 184, 102, 31);
		pnlDoiTTKH.add(lblNewLabel_3_1_1_1_1_1_1_3);
		
		txtDiaChiCN = new JTextField();
		txtDiaChiCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtDiaChiCN.setColumns(10);
		txtDiaChiCN.setBounds(122, 183, 249, 32);
		pnlDoiTTKH.add(txtDiaChiCN);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_1_1 = new JLabel("Ngày sinh:");
		lblNewLabel_3_1_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_1_1.setBounds(10, 55, 102, 31);
		pnlDoiTTKH.add(lblNewLabel_3_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2_2 = new JLabel("Ngày ĐK:");
		lblNewLabel_3_1_1_1_1_1_1_2_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2_2.setBounds(10, 98, 102, 31);
		pnlDoiTTKH.add(lblNewLabel_3_1_1_1_1_1_1_2_2);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2_1_1 = new JLabel("Loại KH:");
		lblNewLabel_3_1_1_1_1_1_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2_1_1.setBounds(10, 141, 102, 31);
		pnlDoiTTKH.add(lblNewLabel_3_1_1_1_1_1_1_2_1_1);
		
		txtLoaiKHCN = new JTextField();
		txtLoaiKHCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtLoaiKHCN.setColumns(10);
		txtLoaiKHCN.setBounds(122, 140, 249, 32);
		pnlDoiTTKH.add(txtLoaiKHCN);
		
		txtNgSinhCN = new JDateChooser();
		txtNgSinhCN.setLocale(new Locale("vi", "VN"));
		txtNgSinhCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNgSinhCN.setBounds(122, 54, 249, 32);
		pnlDoiTTKH.add(txtNgSinhCN);
		
		txtNgayDKCN = new JDateChooser();
		txtNgayDKCN.setLocale(new Locale("vi", "VN"));
		txtNgayDKCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNgayDKCN.setBounds(122, 98, 249, 32);
		pnlDoiTTKH.add(txtNgayDKCN);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_3_1 = new JLabel("Số ĐT:");
		lblNewLabel_3_1_1_1_1_1_1_3_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_3_1.setBounds(10, 227, 102, 31);
		pnlDoiTTKH.add(lblNewLabel_3_1_1_1_1_1_1_3_1);
		
		txtSoDTCN = new JTextField();
		txtSoDTCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtSoDTCN.setColumns(10);
		txtSoDTCN.setBounds(122, 226, 249, 32);
		pnlDoiTTKH.add(txtSoDTCN);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblNewLabel.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconThongTinHoaDonNho.png")));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 381, 59);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã KH:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 78, 72, 30);
		panel_1.add(lblNewLabel_1);
		
		lblMaKH = new JLabel("...");
		lblMaKH.setForeground(Color.BLACK);
		lblMaKH.setFont(new Font("Arial", Font.BOLD, 28));
		lblMaKH.setBounds(112, 67, 163, 44);
		panel_1.add(lblMaKH);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 107, 72, 30);
		panel_1.add(lblNewLabel_1_1);
		
		lblHoTen = new JLabel("...");
		lblHoTen.setForeground(Color.BLACK);
		lblHoTen.setFont(new Font("Arial", Font.BOLD, 18));
		lblHoTen.setBounds(112, 107, 279, 30);
		panel_1.add(lblHoTen);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 226, 95, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		lblDiaChi = new JLabel("...");
		lblDiaChi.setVerticalAlignment(SwingConstants.TOP);
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(new Font("Arial", Font.BOLD, 18));
		lblDiaChi.setBounds(112, 232, 279, 59);
		panel_1.add(lblDiaChi);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ngày sinh:");
		lblNewLabel_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(10, 138, 95, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		lblNgaySinh = new JLabel("...");
		lblNgaySinh.setForeground(Color.BLACK);
		lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 18));
		lblNgaySinh.setBounds(112, 138, 106, 30);
		panel_1.add(lblNgaySinh);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Doanh số:");
		lblNewLabel_1_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(10, 296, 106, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		lblDoanhSo = new JLabel("...");
		lblDoanhSo.setForeground(new Color(AppConstants.MAU_TIM_DAM));
		lblDoanhSo.setFont(new Font("Arial", Font.BOLD, 24));
		lblDoanhSo.setBounds(122, 294, 269, 30);
		panel_1.add(lblDoanhSo);
		
		btnHienCN = new JButton("Cập nhật TT ");
		btnHienCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (khHienTai==null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn khách hàng cần cập nhật");
				}else {
					btnHienCN.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
					btnHienThem.setBackground(Color.WHITE);
					pnlDoiTTKH.setVisible(true);
					pnlThemKH.setVisible(false);
					
					//load data
					txtHoTenCN.setText(khHienTai.getHoTen());
					txtNgSinhCN.setDate(khHienTai.getNgaySinh());
					txtNgayDKCN.setDate(khHienTai.getNgayDKy());
					txtLoaiKHCN.setText(khHienTai.getLoaiKH());
					txtDiaChiCN.setText(khHienTai.getDiaChi());
					txtSoDTCN.setText(khHienTai.getSoDT());
				}
			}
		});
		btnHienCN.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconCapNhat.png")));
		btnHienCN.setForeground(Color.BLACK);
		btnHienCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienCN.setFocusable(false);
		btnHienCN.setBorder(null);
		btnHienCN.setBackground(Color.WHITE);
		btnHienCN.setAlignmentX(0.5f);
		btnHienCN.setBounds(207, 334, 184, 49);
		panel_1.add(btnHienCN);
		
		JButton btnHienCN_1 = new JButton(" Xóa");
		btnHienCN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (khHienTai == null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn khách hàng cần xóa!");
					return;
				}
				try {
					if (AppHelper.canhBaoXacNhan(getRootPane(), "Xóa khách hàng có mã \""+khHienTai.getMaKH()+"\"?") 
							== JOptionPane.OK_OPTION) {
					
						//update database
						khDB.xoaMem(khHienTai.getMaKH());
						
						//update panel
						khHienTai = null;
						listKH = new ArrayList<KhachHang>();
						resetInfo();
						hienThi();
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}	
			}
		});
		btnHienCN_1.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconXoa.png")));
		btnHienCN_1.setForeground(Color.WHITE);
		btnHienCN_1.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienCN_1.setFocusable(false);
		btnHienCN_1.setBorder(null);
		btnHienCN_1.setBackground(new Color(AppConstants.MAU_DO));
		btnHienCN_1.setAlignmentX(0.5f);
		btnHienCN_1.setBounds(285, 67, 106, 41);
		panel_1.add(btnHienCN_1);
		
		btnHienThem = new JButton("   Thêm KH mới   ");
		btnHienThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTKH.setVisible(false);
				pnlThemKH.setVisible(true);
			}
		});
		btnHienThem.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconThemDen.png")));
		btnHienThem.setForeground(Color.BLACK);
		btnHienThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienThem.setFocusable(false);
		btnHienThem.setBorder(null);
		btnHienThem.setBackground(Color.WHITE);
		btnHienThem.setAlignmentX(0.5f);
		btnHienThem.setBounds(10, 394, 381, 49);
		panel_1.add(btnHienThem);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(13, 334, 184, 49);
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblChuyenHD = new JLabel("Xem hóa đơn");
		lblChuyenHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (khHienTai!=null) {
					controller.setViewFromKhachHangToHoaDon();					
				}else {
					AppHelper.thongBao(getRootPane(), "Bạn chưa chọn khách hàng!");
				}
			}
		});

		panel_4.add(lblChuyenHD);
		lblChuyenHD.setBackground(Color.WHITE);
		lblChuyenHD.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/hinhAnh/IconThongTinHoaDonNho.png")));
		lblChuyenHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenHD.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Ngày ĐK:");
		lblNewLabel_1_1_1_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_2.setBounds(10, 167, 95, 30);
		panel_1.add(lblNewLabel_1_1_1_1_2);
		
		lblNgayDK = new JLabel("...");
		lblNgayDK.setForeground(Color.BLACK);
		lblNgayDK.setFont(new Font("Arial", Font.BOLD, 18));
		lblNgayDK.setBounds(112, 167, 279, 30);
		panel_1.add(lblNgayDK);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("Loại KH:");
		lblNewLabel_1_1_1_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_2_1.setBounds(10, 197, 85, 30);
		panel_1.add(lblNewLabel_1_1_1_1_2_1);
		
		lblLoaiKH = new JLabel("...");
		lblLoaiKH.setForeground(Color.BLACK);
		lblLoaiKH.setFont(new Font("Arial", Font.BOLD, 18));
		lblLoaiKH.setBounds(112, 197, 279, 30);
		panel_1.add(lblLoaiKH);
		
		JLabel lblNewLabel_1_1_1_1_3 = new JLabel("SĐT:");
		lblNewLabel_1_1_1_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_3.setBounds(224, 138, 51, 30);
		panel_1.add(lblNewLabel_1_1_1_1_3);
		
		lblSDT = new JLabel("...");
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setFont(new Font("Arial", Font.BOLD, 18));
		lblSDT.setBounds(276, 138, 115, 30);
		panel_1.add(lblSDT);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(AppConstants.MAU_XAM_NHAT));
		panel.setBounds(0, 0, 743, 814);
		add(panel);
		panel.setLayout(null);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTimKiem.setBounds(10, 11, 326, 43);
		panel.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JComboBox cboTimKiem = new JComboBox();
		cboTimKiem.setFont(new Font("Arial", Font.PLAIN, 20));
		cboTimKiem.setModel(new DefaultComboBoxModel(new String[] {AppConstants.MA_KH, AppConstants.HO_TEN,
				AppConstants.DIA_CHI,AppConstants.SO_DT,AppConstants.NGAY_SINH, AppConstants.NGAY_DK, AppConstants.THANG_DK,
				AppConstants.NAM_DK, AppConstants.LOAI_KH, AppConstants.DS_CAOHON, AppConstants.DS_THAPHON}));
		cboTimKiem.setBounds(346, 11, 227, 43);
		panel.add(cboTimKiem);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String thongTin = txtTimKiem.getText();
					if (thongTin.equals("")) {
						listKH = khDB.tatCa();
						hienThi();
						return;
					}
					if (cboTimKiem.getSelectedItem().equals(AppConstants.MA_KH)) {
						listKH = new ArrayList<KhachHang>();
						KhachHang kh = khDB.timTheoMaKH(thongTin);
						if (kh!=null) listKH.add(kh);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.HO_TEN)) {
						listKH = khDB.timTheoHoTen(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.DIA_CHI)){
						listKH = khDB.timTheoDiaChi(thongTin);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.SO_DT)) {
						listKH = khDB.timTheoSDT(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NGAY_SINH)){
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NGAY);
						if (date != null) {
							listKH = khDB.timTheoNgaySinh(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NGAY_DK)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NGAY);
						if (date != null) {
							listKH = khDB.timTheoNgayDKy(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.THANG_DK)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_THANG);
						if (date != null) {
//							listKH = khDB.timTheoNgaySinh(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NAM_DK)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NAM);
						if (date != null) {
//							listKH = khDB.timTheoNgaySinh(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.LOAI_KH)) {
						listKH = khDB.timTheoLoaiKH(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.DS_CAOHON)) {
//						listHD = hdDB.timTheoNgHD();
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.DS_THAPHON)) {
//						listHD = hdDB.timTheoNgHD();
					}
					hienThi();
				}catch (Exception e1) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
		btnTimKiem.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconTimKiem.png")));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 22));
		btnTimKiem.setFocusable(false);
		btnTimKiem.setBorder(null);
		btnTimKiem.setBackground(new Color(73, 80, 87));
		btnTimKiem.setBounds(583, 11, 150, 43);
		panel.add(btnTimKiem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 723, 443);
		panel.add(scrollPane);
		
		tblHienThi = new JTable();
		tblHienThi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHienThi.getSelectedRow();
				String maKH = tblHienThi.getModel().getValueAt(row, 0).toString();
				lblMaKH.setText(maKH);
				lblHoTen.setText(tblHienThi.getModel().getValueAt(row, 1).toString());
				lblNgaySinh.setText(tblHienThi.getModel().getValueAt(row, 4).toString());
				lblSDT.setText(tblHienThi.getModel().getValueAt(row, 3).toString());
				lblNgayDK.setText(tblHienThi.getModel().getValueAt(row, 5).toString());
				lblLoaiKH.setText(tblHienThi.getModel().getValueAt(row, 6).toString());
				lblDiaChi.setText("<HTML>"+tblHienThi.getModel().getValueAt(row, 2).toString()+"<HTML>");
				lblDoanhSo.setText(tblHienThi.getModel().getValueAt(row, 7).toString());
				khHienTai = khDB.timTheoMaKH(maKH);
				//reset
				pnlDoiTTKH.setVisible(false);
				pnlThemKH.setVisible(false);
				btnHienCN.setBackground(Color.WHITE);
				btnHienThem.setBackground(Color.WHITE);
			}
		});
		scrollPane.setViewportView(tblHienThi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 519, 316, 284);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tiện ích");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2.setBounds(10, 11, 99, 33);
		panel_2.add(lblNewLabel_2);
		
		JButton btnKHCoDSMax = new JButton("KH có DS cao nhất      ");
		btnKHCoDSMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listKH = khDB.topKhachHangMuaNhieuNhat(1);
				hienThi();
			}
		});
		btnKHCoDSMax.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconStarFull.png")));
		btnKHCoDSMax.setForeground(Color.WHITE);
		btnKHCoDSMax.setFont(new Font("Arial", Font.BOLD, 18));
		btnKHCoDSMax.setFocusable(false);
		btnKHCoDSMax.setBorder(null);
		btnKHCoDSMax.setBackground(new Color(AppConstants.VIOLET));
		btnKHCoDSMax.setAlignmentX(0.5f);
		btnKHCoDSMax.setBounds(10, 44, 296, 49);
		panel_2.add(btnKHCoDSMax);
		
		JButton btnKHCoDSMax5 = new JButton("5 KH có DS\r\n cao nhất   ");
		btnKHCoDSMax5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listKH = khDB.topKhachHangMuaNhieuNhat(5);
				hienThi();
			}
		});
		btnKHCoDSMax5.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/hinhAnh/IconKhachHang.png")));
		btnKHCoDSMax5.setForeground(Color.WHITE);
		btnKHCoDSMax5.setFont(new Font("Arial", Font.BOLD, 18));
		btnKHCoDSMax5.setFocusable(false);
		btnKHCoDSMax5.setBorder(null);
		btnKHCoDSMax5.setBackground(new Color(54, 79, 199));
		btnKHCoDSMax5.setAlignmentX(0.5f);
		btnKHCoDSMax5.setBounds(10, 164, 296, 49);
		panel_2.add(btnKHCoDSMax5);
		
		JButton btnKHCoDSMin5 = new JButton("5 KH có DS thấp nhất  ");
		btnKHCoDSMin5.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/hinhAnh/IconKhachHang.png")));
		btnKHCoDSMin5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listKH = khDB.topKhachHangMuaItNhat(5);
				hienThi();
			}
		});
		btnKHCoDSMin5.setForeground(Color.WHITE);
		btnKHCoDSMin5.setFont(new Font("Arial", Font.BOLD, 18));
		btnKHCoDSMin5.setFocusable(false);
		btnKHCoDSMin5.setBorder(null);
		btnKHCoDSMin5.setBackground(new Color(54, 79, 199));
		btnKHCoDSMin5.setAlignmentX(0.5f);
		btnKHCoDSMin5.setBounds(10, 224, 296, 49);
		panel_2.add(btnKHCoDSMin5);
		
		JButton btnKHCoDSMin = new JButton("KH có DS thấp nhất     ");
		btnKHCoDSMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listKH = khDB.topKhachHangMuaItNhat(1);
				hienThi();
			}
		});
		btnKHCoDSMin.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconStar.png")));
		btnKHCoDSMin.setForeground(Color.WHITE);
		btnKHCoDSMin.setFont(new Font("Arial", Font.BOLD, 18));
		btnKHCoDSMin.setFocusable(false);
		btnKHCoDSMin.setBorder(null);
		btnKHCoDSMin.setBackground(new Color(AppConstants.VIOLET));
		btnKHCoDSMin.setAlignmentX(0.5f);
		btnKHCoDSMin.setBounds(10, 104, 296, 49);
		panel_2.add(btnKHCoDSMin);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(336, 519, 397, 284);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Thống kê");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2_1.setBounds(10, 11, 130, 33);
		panel_3.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Số KH tìm thấy: ");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2.setBounds(20, 43, 149, 33);
		panel_3.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("TỔNG DS:");
		lblNewLabel_2_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_1.setBounds(20, 240, 141, 33);
		panel_3.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("KH có doanh số cao nhất:");
		lblNewLabel_2_1_2_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2.setBounds(20, 74, 367, 33);
		panel_3.add(lblNewLabel_2_1_2_2);
		
		JLabel lblNewLabel_2_1_2_2_1 = new JLabel("KH có doanh số thấp nhất:");
		lblNewLabel_2_1_2_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1.setBounds(20, 142, 367, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1);
		
		lblSoKHTimThay = new JLabel("...");
		lblSoKHTimThay.setFont(new Font("Arial", Font.BOLD, 18));
		lblSoKHTimThay.setBounds(171, 43, 216, 33);
		panel_3.add(lblSoKHTimThay);
		
		lblTongDS = new JLabel("...");
		lblTongDS.setForeground(new Color(AppConstants.MAU_DO));
		lblTongDS.setFont(new Font("Arial", Font.BOLD, 22));
		lblTongDS.setBounds(171, 239, 216, 33);
		panel_3.add(lblTongDS);
		
		lblKHCoDSCaoNhat = new JLabel("...");
		lblKHCoDSCaoNhat.setForeground(Color.BLACK);
		lblKHCoDSCaoNhat.setFont(new Font("Arial", Font.BOLD, 18));
		lblKHCoDSCaoNhat.setBounds(102, 106, 285, 33);
		panel_3.add(lblKHCoDSCaoNhat);
		
		lblKHCoDSThapNhat = new JLabel("...");
		lblKHCoDSThapNhat.setForeground(Color.BLACK);
		lblKHCoDSThapNhat.setFont(new Font("Arial", Font.BOLD, 18));
		lblKHCoDSThapNhat.setBounds(102, 173, 285, 33);
		panel_3.add(lblKHCoDSThapNhat);
		
		JLabel lblNewLabel_2_1_2_2_1_1 = new JLabel("DS trung bình:");
		lblNewLabel_2_1_2_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1_1.setBounds(20, 205, 149, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1_1);
		
		lblDoanhSoTB = new JLabel("...");
		lblDoanhSoTB.setForeground(new Color(54, 79, 199));
		lblDoanhSoTB.setFont(new Font("Arial", Font.BOLD, 18));
		lblDoanhSoTB.setBounds(171, 205, 216, 33);
		panel_3.add(lblDoanhSoTB);

	}
	
	private void hienThi() {
		DefaultTableModel dtm = new DefaultTableModel(){
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
		dtm.addColumn("MÃ KH");
		dtm.addColumn("HỌ TÊN");
		dtm.addColumn("ĐỊA CHỈ");
		dtm.addColumn("SỐ ĐT");
		dtm.addColumn("NGÀY SINH");
		dtm.addColumn("NGÀY ĐK");
		dtm.addColumn("LOẠI KH");
		dtm.addColumn("DOANH SỐ");
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		if (listKH != null) {
			for (KhachHang item : listKH) {
				Object[] data = {item.getMaKH(),item.getHoTen(),item.getDiaChi(),item.getSoDT(), item.getNgaySinhToString(),
						item.getNgayDKyToString(),item.getLoaiKH(),nf.format(item.getDoanhSo())+" VNĐ"};
				dtm.addRow(data);
			}
		}
		
		tblHienThi.setModel(dtm);
		tblHienThi.setRowHeight(40);
		tblHienThi.setFont(new Font("Arial", Font.PLAIN, 18));
		tblHienThi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));;
		
		thongKe();
	}
	
	private void thongKe() {
		if (listKH == null || listKH.isEmpty()) {
			lblSoKHTimThay.setText("0");
			lblKHCoDSCaoNhat.setText(AppConstants.NO_INFO);
			lblKHCoDSThapNhat.setText(AppConstants.NO_INFO);
			lblDoanhSoTB.setText(AppConstants.NO_INFO);
			lblTongDS.setText(AppConstants.NO_INFO);
			return;
		}
		
		lblSoKHTimThay.setText(String.valueOf(listKH.size()));
		// hoa don max - min - sum
		KhachHang max = listKH.get(0);
		KhachHang min = listKH.get(0);
		double sum = 0;
		
		for (KhachHang item: listKH) {
			if (item.getDoanhSo() > max.getDoanhSo()) max = item;
			if (item.getDoanhSo() < min.getDoanhSo()) min = item;
			sum += item.getDoanhSo();
		}
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		
		lblKHCoDSCaoNhat.setText(max.getMaKH()+" - "+nf.format(max.getDoanhSo())+" VNĐ");
		lblKHCoDSThapNhat.setText(min.getMaKH()+" - "+nf.format(min.getDoanhSo())+" VNĐ");
		lblDoanhSoTB.setText(nf.format(Math.round(sum/listKH.size()))+" "+ "VNĐ");
		lblTongDS.setText(nf.format(sum)+"VNĐ");
	}
	
	private void resetInfo() {
		if (khHienTai == null) {
			lblMaKH.setText(AppConstants.NO_INFO);
			lblHoTen.setText(AppConstants.NO_INFO);
			lblNgaySinh.setText(AppConstants.NO_INFO);
			lblSDT.setText(AppConstants.NO_INFO);
			lblNgayDK.setText(AppConstants.NO_INFO);
			lblLoaiKH.setText(AppConstants.NO_INFO);
			lblDiaChi.setText("<HTML>"+AppConstants.NO_INFO+"<HTML>");
			lblDoanhSo.setText(AppConstants.NO_INFO);
		}else {
			Locale lc = new Locale("vi","VN");
			NumberFormat nf = NumberFormat.getInstance(lc);
			lblMaKH.setText(khHienTai.getMaKH());
			lblHoTen.setText(khHienTai.getHoTen());
			lblNgaySinh.setText(khHienTai.getNgaySinhToString());
			lblSDT.setText(khHienTai.getSoDT());
			lblNgayDK.setText(khHienTai.getNgayDKyToString());
			lblLoaiKH.setText(khHienTai.getLoaiKH());
			lblDiaChi.setText("<HTML>"+khHienTai.getDiaChi()+"<HTML>");
			lblDoanhSo.setText(nf.format(khHienTai.getDoanhSo())+" VNĐ");
		}
	}
	
	protected void hienThiKhachHang(KhachHang kh) {
		khHienTai = kh;
		resetInfo();
		listKH = new ArrayList<KhachHang>();
		listKH.add(kh);
		hienThi();
		tblHienThi.setRowSelectionInterval(0, 0);
	}
}
