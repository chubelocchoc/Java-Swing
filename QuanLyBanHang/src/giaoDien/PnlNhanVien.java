package giaoDien;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import tienIch.AppConstants;
import tienIch.AppHelper;
import xuLyDuLieu.ChiTietHoaDonDB;
import xuLyDuLieu.KhachHangDB;
import xuLyDuLieu.NhanVienDB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlNhanVien extends JPanel {
	
	private JPanel pnlDoiTTNV;
	private JTextField txtTimKiem;
	private JTable tblHienThi;
	private JPanel pnlThemNV;
	private JButton btnHienCN;
	private JButton btnHienThem;
	private JLabel lblChuyenCTHD;
	private JTextField txtMaNVThem;
	private JTextField txtHoTenThem;
	private JTextField txtSoDTThem;
	private JTextField txtVaiTroThem;
	private JTextField txtHoTenCN;
	private JTextField txtSoDTCN;
	private JTextField txtVaiTroCN;
	private JLabel lblSoNVTimThay;
	private JLabel lblDoanhSoNVMax;
	private JLabel lblDoanhSoNVMin;
	private JLabel lblDoanhSoTB;
	private JLabel lblDoanhSoTong;
	private JLabel lblMaNV;
	private JLabel lblHoTen;
	private JLabel lblSoDT;
	private JLabel lblNgVL;
	private JLabel lblVaiTro;
	private JDateChooser txtNgVLCN;
	private JDateChooser txtNgVLThem;
	
	// data
	private List<NhanVien> listNV = null;
	private NhanVien nvHienTai = null;
	private NhanVienDB nvDB = new NhanVienDB();
	private ChuyenPanelTheoDanhMuc controller;
	
	public ChuyenPanelTheoDanhMuc getController() {
		return controller;
	}
	
	public void setController(ChuyenPanelTheoDanhMuc controller) {
		this.controller = controller;
	}
	
	public NhanVien getNvHienTai() {
		return nvHienTai;
	}
	
	/**
	 * Create the panel.
	 */
	public PnlNhanVien() {
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(AppConstants.MAU_TIM_NHAT_3));
		panel_1.setBounds(743, 0, 401, 814);
		add(panel_1);
		panel_1.setLayout(null);
		
		pnlDoiTTNV = new JPanel();
		pnlDoiTTNV.setVisible(false);
		
		pnlThemNV = new JPanel();
		pnlThemNV.setVisible(false);
		pnlThemNV.setBounds(10, 460, 381, 269);
		panel_1.add(pnlThemNV);
		pnlThemNV.setLayout(null);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Thêm nhân viên mới?") == JOptionPane.OK_OPTION) {
						String maNV = txtMaNVThem.getText();
						String hoTen = txtHoTenThem.getText();
						Date ngVLRaw = txtNgVLThem.getDate();
						String vaiTro = txtVaiTroThem.getText();
						String soDT = txtSoDTThem.getText();
						
						//kiem tra nhap
						if (maNV.equals("")||hoTen.equals("")||vaiTro.equals("")||soDT.equals("")) {
							AppHelper.thongBao(getRootPane(), "Vui lòng nhập đầy đủ thông tin!");
							return;
						}
						if (ngVLRaw == null) {
							AppHelper.thongBao(getRootPane(), "Ngày không hợp lệ. Vui lòng ấn nút bên phải để chọn ngày!");
							return;
						}
						
						//Kiem tra maNV
						if (nvDB.kiemTraTonTaiMaNV(maNV)!=null) {
							AppHelper.thongBao(getRootPane(), "Mã nhân viên đã tồn tại!");
							return;
						}
						
						//update database
						java.sql.Date ngVL = new java.sql.Date(ngVLRaw.getTime());
						nvHienTai = new NhanVien(maNV, hoTen, soDT, ngVL, AppConstants.MATKHAU_MACDINH, vaiTro);					
						nvDB.themNhanVien(nvHienTai);
				
						//update panel
						listNV = new ArrayList<NhanVien>();
						listNV.add(nvHienTai);
						hienThi();
						
						// set info
//						Locale lc = new Locale("vi","VN");
//						NumberFormat nf = NumberFormat.getInstance(lc);
						resetInfo();
						pnlThemNV.setVisible(false);
						btnHienThem.setBackground(Color.WHITE);
						AppHelper.thongBao(getRootPane(), "Đã tạo nhân viên mới với mật khẩu mặc định là "+AppConstants.MATKHAU_MACDINH+"!");
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
		btnThem.setBounds(122, 216, 249, 38);
		pnlThemNV.add(btnThem);
		
		JButton btnHuyThem = new JButton("Hủy");
		btnHuyThem.setForeground(Color.WHITE);
		btnHuyThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyThem.setFocusable(false);
		btnHuyThem.setBorder(null);
		btnHuyThem.setBackground(new Color(73, 80, 87));
		btnHuyThem.setBounds(10, 217, 102, 38);
		pnlThemNV.add(btnHuyThem);
		btnHuyThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(Color.WHITE);
				pnlThemNV.setVisible(false);
			}
		});
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Mã NV:");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1.setBounds(10, 10, 102, 31);
		pnlThemNV.add(lblNewLabel_3_1_1_1_1);
		
		txtMaNVThem = new JTextField();
		txtMaNVThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtMaNVThem.setBounds(122, 9, 249, 32);
		pnlThemNV.add(txtMaNVThem);
		txtMaNVThem.setColumns(10);
		
		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("Họ tên:");
		lblNewLabel_3_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1.setBounds(10, 51, 102, 31);
		pnlThemNV.add(lblNewLabel_3_1_1_1_1_1);
		
		txtHoTenThem = new JTextField();
		txtHoTenThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtHoTenThem.setColumns(10);
		txtHoTenThem.setBounds(122, 50, 249, 32);
		pnlThemNV.add(txtHoTenThem);
		
		txtSoDTThem = new JTextField();
		txtSoDTThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtSoDTThem.setColumns(10);
		txtSoDTThem.setBounds(122, 93, 249, 32);
		pnlThemNV.add(txtSoDTThem);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_1 = new JLabel("Số ĐT:");
		lblNewLabel_3_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_1.setBounds(10, 92, 102, 31);
		pnlThemNV.add(lblNewLabel_3_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2 = new JLabel("Ngày VL:");
		lblNewLabel_3_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2.setBounds(10, 133, 102, 31);
		pnlThemNV.add(lblNewLabel_3_1_1_1_1_1_1_2);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2_1 = new JLabel("Vai trò:");
		lblNewLabel_3_1_1_1_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2_1.setBounds(10, 174, 102, 31);
		pnlThemNV.add(lblNewLabel_3_1_1_1_1_1_1_2_1);
		
		txtVaiTroThem = new JTextField();
		txtVaiTroThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtVaiTroThem.setColumns(10);
		txtVaiTroThem.setBounds(122, 173, 249, 32);
		pnlThemNV.add(txtVaiTroThem);
		
		txtNgVLThem = new JDateChooser();
		txtNgVLThem.setLocale(new Locale("vi", "VN"));
		txtNgVLThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNgVLThem.setBounds(122, 132, 249, 32);
		pnlThemNV.add(txtNgVLThem);
		pnlDoiTTNV.setBounds(10, 460, 381, 246);
		panel_1.add(pnlDoiTTNV);
		pnlDoiTTNV.setLayout(null);
		
		JButton btnCapNhat = new JButton("Đổi");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Cập nhật thông tin nhân viên có mã \""+nvHienTai.getMaNV()+"\"?") 
							== JOptionPane.OK_OPTION) {
						String hoTen = txtHoTenCN.getText();
						String soDT = txtSoDTCN.getText();
						Date ngVLRaw = txtNgVLCN.getDate();
						String vaiTro = txtVaiTroCN.getText();
						
						//update database
						java.sql.Date ngVL = new java.sql.Date(ngVLRaw.getTime());
						nvHienTai.setHoTen(hoTen);
						nvHienTai.setNgVL(ngVL);
						nvHienTai.setSoDT(soDT);
						nvHienTai.setVaiTro(vaiTro);
						nvDB.capNhatThongTin(nvHienTai);
						
						//update panel
						listNV = new ArrayList<NhanVien>();
						listNV.add(nvHienTai);
						hienThi();
						
						// set info
//						Locale lc = new Locale("vi","VN");
//						NumberFormat nf = NumberFormat.getInstance(lc);
						lblMaNV.setText(nvHienTai.getMaNV());
						lblHoTen.setText(nvHienTai.getHoTen());
						lblNgVL.setText(nvHienTai.getNgayVLToString());
						lblSoDT.setText(nvHienTai.getSoDT());
						lblVaiTro.setText(nvHienTai.getVaiTro());
						pnlDoiTTNV.setVisible(false);
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
		btnCapNhat.setBounds(122, 183, 249, 44);
		pnlDoiTTNV.add(btnCapNhat);
		
		JButton btnHuyCN = new JButton("Hủy");
		btnHuyCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTNV.setVisible(false);
			}
		});
		btnHuyCN.setFocusable(false);
		btnHuyCN.setForeground(Color.WHITE);
		btnHuyCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyCN.setBorder(null);
		btnHuyCN.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyCN.setBounds(10, 183, 102, 44);
		pnlDoiTTNV.add(btnHuyCN);
		
		JLabel lblNewLabel_3_1_1_1_1_1_2 = new JLabel("Họ tên:");
		lblNewLabel_3_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_2.setBounds(10, 12, 102, 31);
		pnlDoiTTNV.add(lblNewLabel_3_1_1_1_1_1_2);
		
		txtHoTenCN = new JTextField();
		txtHoTenCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtHoTenCN.setColumns(10);
		txtHoTenCN.setBounds(122, 11, 249, 32);
		pnlDoiTTNV.add(txtHoTenCN);
		
		txtSoDTCN = new JTextField();
		txtSoDTCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtSoDTCN.setColumns(10);
		txtSoDTCN.setBounds(122, 54, 249, 32);
		pnlDoiTTNV.add(txtSoDTCN);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_1_1 = new JLabel("Số ĐT:");
		lblNewLabel_3_1_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_1_1.setBounds(10, 55, 102, 31);
		pnlDoiTTNV.add(lblNewLabel_3_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2_2 = new JLabel("Ngày VL:");
		lblNewLabel_3_1_1_1_1_1_1_2_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2_2.setBounds(10, 98, 102, 31);
		pnlDoiTTNV.add(lblNewLabel_3_1_1_1_1_1_1_2_2);
		
		JLabel lblNewLabel_3_1_1_1_1_1_1_2_1_1 = new JLabel("Vai trò:");
		lblNewLabel_3_1_1_1_1_1_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1_1_1_2_1_1.setBounds(10, 141, 102, 31);
		pnlDoiTTNV.add(lblNewLabel_3_1_1_1_1_1_1_2_1_1);
		
		txtVaiTroCN = new JTextField();
		txtVaiTroCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtVaiTroCN.setColumns(10);
		txtVaiTroCN.setBounds(122, 140, 249, 32);
		pnlDoiTTNV.add(txtVaiTroCN);
		
		txtNgVLCN = new JDateChooser();
		txtNgVLCN.setLocale(new Locale("vi", "VN"));
		txtNgVLCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNgVLCN.setBounds(122, 98, 249, 32);
		pnlDoiTTNV.add(txtNgVLCN);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblNewLabel.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/hinhAnh/IconNhanVienDen.png")));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 381, 59);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã NV:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 78, 72, 30);
		panel_1.add(lblNewLabel_1);
		
		lblMaNV = new JLabel("...");
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 28));
		lblMaNV.setBounds(112, 67, 163, 44);
		panel_1.add(lblMaNV);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 119, 72, 30);
		panel_1.add(lblNewLabel_1_1);
		
		lblHoTen = new JLabel("...");
		lblHoTen.setForeground(Color.BLACK);
		lblHoTen.setFont(new Font("Arial", Font.BOLD, 18));
		lblHoTen.setBounds(112, 119, 279, 30);
		panel_1.add(lblHoTen);
		
		btnHienCN = new JButton("Cập nhật thông tin                ");
		btnHienCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (nvHienTai==null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn nhân viên cần cập nhật");
				}else {
					btnHienCN.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
					btnHienThem.setBackground(Color.WHITE);
					pnlDoiTTNV.setVisible(true);
					pnlThemNV.setVisible(false);
					
					//load data
					txtHoTenCN.setText(nvHienTai.getHoTen());
					txtSoDTCN.setText(nvHienTai.getSoDT());
					txtNgVLCN.setDate(nvHienTai.getNgVL());
					txtVaiTroCN.setText(nvHienTai.getVaiTro());
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
		btnHienCN.setBounds(10, 340, 381, 49);
		panel_1.add(btnHienCN);
		
		JButton btnHienCN_1 = new JButton(" Xóa");
		btnHienCN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nvHienTai == null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn nhân viên cần xóa!");
					return;
				}
				try {
					if (AppHelper.canhBaoXacNhan(getRootPane(), "Xóa nhân viên có mã \""+nvHienTai.getMaNV()+"\"?") 
							== JOptionPane.OK_OPTION) {
					
						//update database
						nvDB.xoaMem(nvHienTai.getMaNV());
						
						//update panel
						nvHienTai = null;
						listNV = new ArrayList<NhanVien>();
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
		
		btnHienThem = new JButton("Thêm nhân viên mới            ");
		btnHienThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTNV.setVisible(false);
				pnlThemNV.setVisible(true);
			}
		});
		btnHienThem.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconThemDen.png")));
		btnHienThem.setForeground(Color.BLACK);
		btnHienThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienThem.setFocusable(false);
		btnHienThem.setBorder(null);
		btnHienThem.setBackground(Color.WHITE);
		btnHienThem.setAlignmentX(0.5f);
		btnHienThem.setBounds(10, 400, 381, 49);
		panel_1.add(btnHienThem);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 280, 381, 49);
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblChuyenCTHD = new JLabel("Xem hóa đơn của nhân viên");
		lblChuyenCTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nvHienTai!=null) {
					controller.setViewFromNhanVienToHoaDon();					
				}else {
					AppHelper.thongBao(getRootPane(), "Bạn chưa chọn nhân viên!");
				}
			}
		});
		
		panel_4.add(lblChuyenCTHD);
		lblChuyenCTHD.setBackground(Color.WHITE);
		lblChuyenCTHD.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/hinhAnh/IconThongTinHoaDonNho.png")));
		lblChuyenCTHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenCTHD.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Ngày VL:");
		lblNewLabel_1_1_1_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_2.setBounds(10, 198, 95, 30);
		panel_1.add(lblNewLabel_1_1_1_1_2);
		
		lblNgVL = new JLabel("...");
		lblNgVL.setForeground(Color.BLACK);
		lblNgVL.setFont(new Font("Arial", Font.BOLD, 18));
		lblNgVL.setBounds(112, 198, 279, 30);
		panel_1.add(lblNgVL);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("Vai trò:");
		lblNewLabel_1_1_1_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_2_1.setBounds(10, 239, 85, 30);
		panel_1.add(lblNewLabel_1_1_1_1_2_1);
		
		lblVaiTro = new JLabel("...");
		lblVaiTro.setForeground(Color.BLACK);
		lblVaiTro.setFont(new Font("Arial", Font.BOLD, 18));
		lblVaiTro.setBounds(112, 239, 279, 30);
		panel_1.add(lblVaiTro);
		
		JLabel lblNewLabel_1_1_1_1_3 = new JLabel("SĐT:");
		lblNewLabel_1_1_1_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_3.setBounds(10, 157, 51, 30);
		panel_1.add(lblNewLabel_1_1_1_1_3);
		
		lblSoDT = new JLabel("...");
		lblSoDT.setForeground(Color.BLACK);
		lblSoDT.setFont(new Font("Arial", Font.BOLD, 18));
		lblSoDT.setBounds(112, 157, 255, 30);
		panel_1.add(lblSoDT);
		
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
		cboTimKiem.setModel(new DefaultComboBoxModel(new String[] {AppConstants.MA_NV, AppConstants.HO_TEN, AppConstants.SO_DT, 
				AppConstants.NGAY_VL, AppConstants.THANG_VL, AppConstants.NAM_VL, AppConstants.VAITRO, AppConstants.DS_CAOHON, AppConstants.DS_THAPHON}));
		cboTimKiem.setBounds(346, 11, 227, 43);
		panel.add(cboTimKiem);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String thongTin = txtTimKiem.getText();
					if (thongTin.equals("")) {
						listNV = nvDB.tatCa();
						hienThi();
						return;
					}
					if (cboTimKiem.getSelectedItem().equals(AppConstants.MA_NV)) {
						listNV = new ArrayList<NhanVien>();
						NhanVien nv = nvDB.timTheoMaNV(thongTin);
						if (nv!=null) listNV.add(nv);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.HO_TEN)) {
						listNV = nvDB.timTheoHoTen(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.SO_DT)){
						listNV = nvDB.timTheoSoDT(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NGAY_VL)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NGAY);
						if (date != null) {
							listNV = nvDB.timTheoNgVL(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.THANG_VL)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_THANG);
						if (date != null) {
//							listKH = khDB.timTheoNgaySinh(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NAM_VL)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NAM);
						if (date != null) {
//							listKH = khDB.timTheoNgaySinh(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.VAITRO)) {
						listNV = nvDB.timTheoVaiTro(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.DS_CAOHON)) {
//						listHD = hdDB.timTheoNgHD();
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.DS_THAPHON)) {
//						listHD = hdDB.timTheoNgHD();
					}
					hienThi();
				}catch (Exception e1) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
					e1.printStackTrace();
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
				String maNV = tblHienThi.getModel().getValueAt(row, 0).toString();
				lblMaNV.setText(maNV);
				lblHoTen.setText(tblHienThi.getModel().getValueAt(row, 1).toString());
				lblSoDT.setText(tblHienThi.getModel().getValueAt(row, 2).toString());
				lblNgVL.setText(tblHienThi.getModel().getValueAt(row, 3).toString());
				lblVaiTro.setText(tblHienThi.getModel().getValueAt(row, 4).toString());
				nvHienTai = nvDB.timTheoMaNV(maNV);
				//reset
				pnlDoiTTNV.setVisible(false);
				pnlThemNV.setVisible(false);
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
		
		JButton btnHC = new JButton("NV có DS cao nhất      ");
		btnHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHC.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconStarFull.png")));
		btnHC.setForeground(Color.WHITE);
		btnHC.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC.setFocusable(false);
		btnHC.setBorder(null);
		btnHC.setBackground(new Color(AppConstants.VIOLET));
		btnHC.setAlignmentX(0.5f);
		btnHC.setBounds(10, 44, 296, 49);
		panel_2.add(btnHC);
		
		JButton btnHC_2 = new JButton("10 NV có DS\r\n cao nhất ");
		btnHC_2.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/hinhAnh/IconNhanVien.png")));
		btnHC_2.setForeground(Color.WHITE);
		btnHC_2.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2.setFocusable(false);
		btnHC_2.setBorder(null);
		btnHC_2.setBackground(new Color(54, 79, 199));
		btnHC_2.setAlignmentX(0.5f);
		btnHC_2.setBounds(10, 164, 296, 49);
		panel_2.add(btnHC_2);
		
		JButton btnHC_2_1 = new JButton("10 NV có DS thấp nhất");
		btnHC_2_1.setIcon(new ImageIcon(PnlNhanVien.class.getResource("/hinhAnh/IconNhanVien.png")));
		btnHC_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHC_2_1.setForeground(Color.WHITE);
		btnHC_2_1.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2_1.setFocusable(false);
		btnHC_2_1.setBorder(null);
		btnHC_2_1.setBackground(new Color(54, 79, 199));
		btnHC_2_1.setAlignmentX(0.5f);
		btnHC_2_1.setBounds(10, 224, 296, 49);
		panel_2.add(btnHC_2_1);
		
		JButton btnHC_2_2 = new JButton("NV có DS thấp nhất     ");
		btnHC_2_2.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconStar.png")));
		btnHC_2_2.setForeground(Color.WHITE);
		btnHC_2_2.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2_2.setFocusable(false);
		btnHC_2_2.setBorder(null);
		btnHC_2_2.setBackground(new Color(AppConstants.VIOLET));
		btnHC_2_2.setAlignmentX(0.5f);
		btnHC_2_2.setBounds(10, 104, 296, 49);
		panel_2.add(btnHC_2_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(336, 519, 397, 284);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Thống kê");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2_1.setBounds(10, 11, 130, 33);
		panel_3.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Số NV tìm thấy: ");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2.setBounds(20, 43, 149, 33);
		panel_3.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("TỔNG DS:");
		lblNewLabel_2_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_1.setBounds(20, 240, 141, 33);
		panel_3.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("NV có doanh số cao nhất:");
		lblNewLabel_2_1_2_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2.setBounds(20, 74, 367, 33);
		panel_3.add(lblNewLabel_2_1_2_2);
		
		JLabel lblNewLabel_2_1_2_2_1 = new JLabel("NV có doanh số thấp nhất:");
		lblNewLabel_2_1_2_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1.setBounds(20, 142, 367, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1);
		
		lblSoNVTimThay = new JLabel("...");
		lblSoNVTimThay.setFont(new Font("Arial", Font.BOLD, 18));
		lblSoNVTimThay.setBounds(171, 43, 216, 33);
		panel_3.add(lblSoNVTimThay);
		
		lblDoanhSoTong = new JLabel("...");
		lblDoanhSoTong.setForeground(new Color(AppConstants.MAU_DO));
		lblDoanhSoTong.setFont(new Font("Arial", Font.BOLD, 22));
		lblDoanhSoTong.setBounds(171, 239, 216, 33);
		panel_3.add(lblDoanhSoTong);
		
		lblDoanhSoNVMax = new JLabel("...");
		lblDoanhSoNVMax.setForeground(Color.BLACK);
		lblDoanhSoNVMax.setFont(new Font("Arial", Font.BOLD, 18));
		lblDoanhSoNVMax.setBounds(102, 106, 285, 33);
		panel_3.add(lblDoanhSoNVMax);
		
		lblDoanhSoNVMin = new JLabel("...");
		lblDoanhSoNVMin.setForeground(Color.BLACK);
		lblDoanhSoNVMin.setFont(new Font("Arial", Font.BOLD, 18));
		lblDoanhSoNVMin.setBounds(102, 173, 285, 33);
		panel_3.add(lblDoanhSoNVMin);
		
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
		dtm.addColumn("MÃ NV");
		dtm.addColumn("HỌ TÊN");
		dtm.addColumn("SỐ ĐT");
		dtm.addColumn("NGÀY VL");
		dtm.addColumn("VAI TRÒ");
//		Locale lc = new Locale("vi","VN");
//		NumberFormat nf = NumberFormat.getInstance(lc);
		if (listNV != null) {
			for (NhanVien item : listNV) {
				Object[] data = {item.getMaNV(),item.getHoTen(),item.getSoDT(),item.getNgayVLToString(),
						item.getVaiTro()};
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
		if (listNV == null || listNV.isEmpty()) {
			lblSoNVTimThay.setText("0");
			lblDoanhSoNVMax.setText(AppConstants.NO_INFO);
			lblDoanhSoNVMin.setText(AppConstants.NO_INFO);
			lblDoanhSoTB.setText(AppConstants.NO_INFO);
			lblDoanhSoTong.setText(AppConstants.NO_INFO);
			return;
		}
		
//		lblSoNVTimThay.setText(String.valueOf(listNV.size()));
//		// hoa don max - min - sum
//		NhanVien max = listNV.get(0);
//		NhanVien min = listNV.get(0);
//		double sum = 0;
//		
//		for (NhanVien item: listNV) {
//			if (item.getDoanhSo() > max.getDoanhSo()) max = item;
//			if (item.getDoanhSo() < min.getDoanhSo()) min = item;
//			sum += item.getDoanhSo();
//		}
//		Locale lc = new Locale("vi","VN");
//		NumberFormat nf = NumberFormat.getInstance(lc);
//		
//		lblKHCoDSCaoNhat.setText(max.getMaKH()+" - "+nf.format(max.getDoanhSo())+" VNĐ");
//		lblKHCoDSThapNhat.setText(min.getMaKH()+" - "+nf.format(min.getDoanhSo())+" VNĐ");
//		lblDoanhSoTB.setText(nf.format(Math.round(sum/listKH.size()))+" "+ "VNĐ");
//		lblTongDS.setText(nf.format(sum)+"VNĐ");
	}
	
	private void resetInfo() {
		if (nvHienTai == null) {
			lblMaNV.setText(AppConstants.NO_INFO);
			lblHoTen.setText(AppConstants.NO_INFO);
			lblNgVL.setText(AppConstants.NO_INFO);
			lblSoDT.setText(AppConstants.NO_INFO);
			lblVaiTro.setText(AppConstants.NO_INFO);
		}else {
			lblMaNV.setText(nvHienTai.getMaNV());
			lblHoTen.setText(nvHienTai.getHoTen());
			lblNgVL.setText(nvHienTai.getNgayVLToString());
			lblSoDT.setText(nvHienTai.getSoDT());
			lblVaiTro.setText(nvHienTai.getVaiTro());
		}
	}
}
