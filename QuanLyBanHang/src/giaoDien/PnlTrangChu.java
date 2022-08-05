package giaoDien;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;

import tienIch.AppConstants;
import tienIch.AppHelper;
import xuLyDuLieu.ChiTietHoaDonDB;
import xuLyDuLieu.HoaDonDB;
import xuLyDuLieu.KhachHangDB;
import xuLyDuLieu.NhanVienDB;
import xuLyDuLieu.SanPhamDB;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;

import java.util.Locale;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlTrangChu extends JPanel {
	private JTextField txtHoTenCapNhat;
	private JTextField txtSDTCapNhat;
	private JPasswordField txtMKCu;
	private JPasswordField txtMKMoi;
	private JPasswordField txtMKMoi2;
	private JPanel pnlCapNhat;
	private JPanel pnlDoiMK;
	private JDateChooser txtNgayVLCapNhat;
	private JButton btnHienCN;
	private JButton btnHienDoiMK;
	private JTable tblHienThi;
	private JLabel lblMaUser;
	private JLabel lblHoTenUser;
	private JLabel lblSDTUser;
	private JLabel lblVaiTroUser;
	private JLabel lblNgayVLUser;
	private JLabel lblTongSoHD;
	private JLabel lblSoLuongDaBan;
	private JLabel lblSoLoaiSP;
	private JLabel lblTongSoNV;
	private JLabel lblTongSoKH;
	private JLabel lblTongDoanhSo;
	
	//data
	private NhanVien user;
	private KhachHangDB khDB = new KhachHangDB();
	private NhanVienDB nvDB = new NhanVienDB();
	private HoaDonDB hdDB = new HoaDonDB();
	private SanPhamDB spDB = new SanPhamDB();
	private ChiTietHoaDonDB cthdDB = new ChiTietHoaDonDB();
	private List<HoaDon> listHD = null;
	private List<SanPham> listSP = null;
        private List<String> listSoLuong = null;
	private List<KhachHang> listKH = null;
	private ChuyenPanelTheoDanhMuc controller;
	
	public void setUser(NhanVien user) {
		this.user = user;
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
	public PnlTrangChu(NhanVien user) {
		setLayout(null);
		
		this.user = nvDB.timTheoMaNV(user.getMaNV());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(AppConstants.MAU_XAM_NHAT));
		panel.setBounds(0, 0, 767, 814);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(AppConstants.MAU_XANH_LA));
		panel_3.setBounds(10, 11, 375, 129);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconDoanhSo.png")));
		lblNewLabel_5.setBounds(10, 11, 105, 107);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tổng doanh Số");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(123, 11, 242, 51);
		panel_3.add(lblNewLabel_6);
		
		lblTongDoanhSo = new JLabel("1,000,000 VNĐ");
		lblTongDoanhSo.setForeground(Color.WHITE);
		lblTongDoanhSo.setFont(new Font("Arial", Font.BOLD, 28));
		lblTongDoanhSo.setBounds(125, 59, 240, 59);
		panel_3.add(lblTongDoanhSo);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(new Color(AppConstants.MAU_DO));
		panel_3_1.setBounds(395, 11, 362, 129);
		panel.add(panel_3_1);
		panel_3_1.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconHoaDonLon.png")));
		lblNewLabel_5_1.setBounds(10, 11, 105, 107);
		panel_3_1.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Tổng số hóa đơn");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_6_1.setBounds(123, 11, 229, 51);
		panel_3_1.add(lblNewLabel_6_1);
		
		lblTongSoHD = new JLabel("100");
		lblTongSoHD.setForeground(Color.WHITE);
		lblTongSoHD.setFont(new Font("Arial", Font.BOLD, 36));
		lblTongSoHD.setBounds(123, 59, 229, 59);
		panel_3_1.add(lblTongSoHD);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBackground(new Color(AppConstants.MAU_CAM));
		panel_3_2.setBounds(10, 151, 375, 129);
		panel.add(panel_3_2);
		panel_3_2.setLayout(null);
		
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconCTHDLonTrang.png")));
		lblNewLabel_5_2.setBounds(10, 11, 105, 107);
		panel_3_2.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_6_2 = new JLabel("Số lượng sản phẩm đã bán");
		lblNewLabel_6_2.setForeground(Color.WHITE);
		lblNewLabel_6_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_6_2.setBounds(123, 11, 242, 51);
		panel_3_2.add(lblNewLabel_6_2);
		
		lblSoLuongDaBan = new JLabel("100");
		lblSoLuongDaBan.setForeground(Color.WHITE);
		lblSoLuongDaBan.setFont(new Font("Arial", Font.BOLD, 36));
		lblSoLuongDaBan.setBounds(125, 59, 240, 59);
		panel_3_2.add(lblSoLuongDaBan);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBackground(new Color(AppConstants.VIOLET));
		panel_3_1_1.setBounds(395, 151, 362, 129);
		panel.add(panel_3_1_1);
		panel_3_1_1.setLayout(null);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconSanPhamLon.png")));
		lblNewLabel_5_1_1.setBounds(10, 11, 105, 107);
		panel_3_1_1.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Số loại sản phẩm");
		lblNewLabel_6_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_6_1_1.setBounds(123, 11, 229, 51);
		panel_3_1_1.add(lblNewLabel_6_1_1);
		
		lblSoLoaiSP = new JLabel("100");
		lblSoLoaiSP.setForeground(Color.WHITE);
		lblSoLoaiSP.setFont(new Font("Arial", Font.BOLD, 36));
		lblSoLoaiSP.setBounds(125, 59, 227, 59);
		panel_3_1_1.add(lblSoLoaiSP);
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.setBackground(new Color(AppConstants.MAU_TIM_NHAT));
		panel_3_3.setBounds(10, 291, 375, 129);
		panel.add(panel_3_3);
		panel_3_3.setLayout(null);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("");
		lblNewLabel_5_2_1.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconKhachHangLon.png")));
		lblNewLabel_5_2_1.setBounds(10, 11, 105, 107);
		panel_3_3.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_6_2_1 = new JLabel("Tổng số khách hàng");
		lblNewLabel_6_2_1.setForeground(Color.WHITE);
		lblNewLabel_6_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_6_2_1.setBounds(123, 11, 242, 51);
		panel_3_3.add(lblNewLabel_6_2_1);
		
		lblTongSoKH = new JLabel("24");
		lblTongSoKH.setForeground(Color.WHITE);
		lblTongSoKH.setFont(new Font("Arial", Font.BOLD, 36));
		lblTongSoKH.setBounds(125, 59, 240, 59);
		panel_3_3.add(lblTongSoKH);
		
		JPanel panel_3_1_2 = new JPanel();
		panel_3_1_2.setBackground(new Color(AppConstants.MAU_XAM_NHAT_2));
		panel_3_1_2.setBounds(395, 291, 362, 129);
		panel.add(panel_3_1_2);
		panel_3_1_2.setLayout(null);
		
		JLabel lblNewLabel_5_2_1_1 = new JLabel("");
		lblNewLabel_5_2_1_1.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconNhanVienLon.png")));
		lblNewLabel_5_2_1_1.setBounds(10, 11, 105, 107);
		panel_3_1_2.add(lblNewLabel_5_2_1_1);
		
		JLabel lblNewLabel_6_2_1_1 = new JLabel("Tổng số nhân viên");
		lblNewLabel_6_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_6_2_1_1.setBounds(123, 11, 242, 51);
		panel_3_1_2.add(lblNewLabel_6_2_1_1);
		
		lblTongSoNV = new JLabel("24");
		lblTongSoNV.setForeground(Color.WHITE);
		lblTongSoNV.setFont(new Font("Arial", Font.BOLD, 36));
		lblTongSoNV.setBounds(125, 59, 240, 59);
		panel_3_1_2.add(lblTongSoNV);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(AppConstants.MAU_XAM_NHAT));
		panel_4.setBounds(10, 431, 747, 362);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("THỐNG KÊ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_10.setBounds(0, 11, 123, 36);
		panel_4.add(lblNewLabel_10);
		
		JComboBox cboTimKiem = new JComboBox();
		cboTimKiem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					switch((String)cboTimKiem.getSelectedItem()) {
						case AppConstants.HOA_DON_GAN_NHAT:
							listHD = hdDB.topHoaDonMoiNhat(10);
							hienThiHoaDon();
							break;
						case AppConstants.HOA_DON_CU_NHAT:
							listHD = hdDB.topHoaDonCuNhat(10);
							hienThiHoaDon();
							break;
						case AppConstants.HOA_DON_CAO_NHAT:
							listHD = hdDB.topHoaDonCaoNhat(10);
							hienThiHoaDon();
							break;
						case AppConstants.HOA_DON_THAP_NHAT:
							listHD = hdDB.topHoaDonThapNhat(10);
							hienThiHoaDon();
							break;
						case AppConstants.SAN_PHAM_BAN_CHAY:
							listSP = spDB.topSanPhamBanChay(10);
                                                        listSoLuong = spDB.slSanPhamBanChay(10);
							hienThiSanPham(listSoLuong);
							break;
						case AppConstants.SAN_PHAM_BAN_CHAM:
							listSP = spDB.topSanPhamBanCham(10);
                                                        listSoLuong = spDB.slSanPhamBanCham(10);
							hienThiSanPham(listSoLuong);
							break;
						case AppConstants.KHACH_HANG_DS_CAO_NHAT:
							listKH = khDB.topKhachHangMuaNhieuNhat(10);
							hienThiKhachHang();
							break;
						case AppConstants.KHACH_HANG_DS_THAP_NHAT:
							listKH = khDB.topKhachHangMuaItNhat(10);
							hienThiKhachHang();
							break;
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
		cboTimKiem.setFont(new Font("Arial", Font.PLAIN, 16));
		cboTimKiem.setModel(new DefaultComboBoxModel(new String[] {AppConstants.HOA_DON_GAN_NHAT, AppConstants.HOA_DON_CU_NHAT, 
				AppConstants.HOA_DON_CAO_NHAT,AppConstants.HOA_DON_THAP_NHAT,AppConstants.SAN_PHAM_BAN_CHAY,
				AppConstants.SAN_PHAM_BAN_CHAM,AppConstants.KHACH_HANG_DS_CAO_NHAT,AppConstants.KHACH_HANG_DS_THAP_NHAT}));
		cboTimKiem.setBorder(null);
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setBounds(133, 11, 614, 33);
		panel_4.add(cboTimKiem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 58, 747, 314);
		panel_4.add(scrollPane);
		
		tblHienThi = new JTable();
		tblHienThi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHienThi.getSelectedRow();
				try {
					switch((String)cboTimKiem.getSelectedItem()) {
						case AppConstants.HOA_DON_GAN_NHAT:
						case AppConstants.HOA_DON_CU_NHAT:
						case AppConstants.HOA_DON_CAO_NHAT:
						case AppConstants.HOA_DON_THAP_NHAT:
							int soHD = Integer.parseInt(tblHienThi.getModel().getValueAt(row, 1).toString());
							if (AppHelper.thongBaoXacNhan(getRootPane(), "Chuyển sang xem thông tin hóa đơn số \""+soHD+"\"?")
									== JOptionPane.OK_OPTION) {
								controller.setViewFromTrangChuToHoaDon(hdDB.timTheoSoHD(soHD));					
							}
							break;
						case AppConstants.SAN_PHAM_BAN_CHAY:
						case AppConstants.SAN_PHAM_BAN_CHAM:
							String maSP = tblHienThi.getModel().getValueAt(row, 1).toString();
							if (AppHelper.thongBaoXacNhan(getRootPane(), "Chuyển sang xem thông tin sản phẩm có mã \""+maSP+"\"?")
									== JOptionPane.OK_OPTION) {
								controller.setViewFromTrangChuToSanPham(spDB.timTheoMaSP(maSP));					
							}
							break;
						case AppConstants.KHACH_HANG_DS_CAO_NHAT:
						case AppConstants.KHACH_HANG_DS_THAP_NHAT:
							String maKH = tblHienThi.getModel().getValueAt(row, 1).toString();
							if (AppHelper.thongBaoXacNhan(getRootPane(), "Chuyển sang xem thông tin khách hàng có mã \""+maKH+"\"?")
									== JOptionPane.OK_OPTION) {
								controller.setViewFromTrangChuToKhachHang(khDB.timTheoMaKH(maKH));					
							}
							break;
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
					e2.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(tblHienThi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Arial", Font.BOLD, 18));
		panel_1.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		panel_1.setBounds(766, 0, 378, 814);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(AppConstants.MAU_TIM_NHAT));
		panel_2.setBounds(10, 11, 358, 128);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUser1 = new JLabel("Mã nhân viên: ");
		lblUser1.setForeground(Color.WHITE);
		lblUser1.setBounds(157, 37, 191, 35);
		panel_2.add(lblUser1);
		lblUser1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconUser.png")));
		lblNewLabel_4.setBounds(10, 11, 125, 106);
		panel_2.add(lblNewLabel_4);
		
		lblMaUser = new JLabel("NV00");
		lblMaUser.setForeground(Color.WHITE);
		lblMaUser.setBounds(157, 66, 191, 51);
		panel_2.add(lblMaUser);
		lblMaUser.setFont(new Font("Arial", Font.BOLD, 24));
		
		JLabel lblTextHoTen = new JLabel("Họ tên:");
		lblTextHoTen.setForeground(Color.WHITE);
		lblTextHoTen.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTextHoTen.setBounds(10, 153, 85, 30);
		panel_1.add(lblTextHoTen);
		
		JLabel lblNewLabel_2 = new JLabel("SĐT:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 193, 85, 30);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblTextNgVL = new JLabel("Ngày VL:");
		lblTextNgVL.setForeground(Color.WHITE);
		lblTextNgVL.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTextNgVL.setBounds(10, 271, 85, 30);
		panel_1.add(lblTextNgVL);
		
		lblHoTenUser = new JLabel("NGUYỄN NGỌC MẪN");
		lblHoTenUser.setForeground(Color.WHITE);
		lblHoTenUser.setFont(new Font("Arial", Font.BOLD, 18));
		lblHoTenUser.setBounds(105, 153, 265, 30);
		panel_1.add(lblHoTenUser);
		
		lblSDTUser = new JLabel("0912345678");
		lblSDTUser.setForeground(Color.WHITE);
		lblSDTUser.setFont(new Font("Arial", Font.BOLD, 18));
		lblSDTUser.setBounds(103, 193, 265, 30);
		panel_1.add(lblSDTUser);
		
		lblNgayVLUser = new JLabel("28-07-2022");
		lblNgayVLUser.setForeground(Color.WHITE);
		lblNgayVLUser.setFont(new Font("Arial", Font.BOLD, 18));
		lblNgayVLUser.setBounds(103, 271, 265, 30);
		panel_1.add(lblNgayVLUser);
		
		btnHienCN = new JButton("   Cập nhật thông tin");
		btnHienCN.setFocusable(false);
		btnHienCN.setAlignmentX(0.5f);
		btnHienCN.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconCapNhat.png")));
		btnHienCN.setForeground(Color.BLACK);
		btnHienCN.setBackground(Color.WHITE);
		btnHienCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienCN.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
				btnHienDoiMK.setBackground(Color.WHITE);
				pnlCapNhat.setVisible(true);
				pnlDoiMK.setVisible(false);
				
				txtHoTenCapNhat.setText(user.getHoTen());
				txtSDTCapNhat.setText(user.getSoDT());
				txtNgayVLCapNhat.setDate(user.getNgVL());
			}
		});
		btnHienCN.setBorder(null);
		btnHienCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienCN.setBounds(10, 323, 358, 49);
		panel_1.add(btnHienCN);
		
		btnHienDoiMK = new JButton("   Đổi mật khẩu         ");
		btnHienDoiMK.setFocusable(false);
		btnHienDoiMK.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/hinhAnh/IconDoiMatKhau.png")));
		btnHienDoiMK.setForeground(Color.BLACK);
		btnHienDoiMK.setBackground(Color.WHITE);
		btnHienDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienDoiMK.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
				btnHienCN.setBackground(Color.WHITE);
				pnlCapNhat.setVisible(false);
				pnlDoiMK.setBounds(10, 454, 358, 217);
				pnlDoiMK.setVisible(true);
			}
		});
		btnHienDoiMK.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienDoiMK.setBorder(null);
		btnHienDoiMK.setBounds(10, 383, 358, 49);
		panel_1.add(btnHienDoiMK);
		
		pnlCapNhat = new JPanel();
		pnlCapNhat.setBounds(10, 454, 358, 217);
		panel_1.add(pnlCapNhat);
		pnlCapNhat.setLayout(null);
		pnlCapNhat.setVisible(false);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setBounds(10, 14, 122, 31);
		pnlCapNhat.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		
		txtHoTenCapNhat = new JTextField();
		txtHoTenCapNhat.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHoTenCapNhat.setBounds(142, 12, 203, 35);
		pnlCapNhat.add(txtHoTenCapNhat);
		txtHoTenCapNhat.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Số điện thoại:");
		lblNewLabel_2_1.setBounds(10, 60, 122, 31);
		pnlCapNhat.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		
		txtSDTCapNhat = new JTextField();
		txtSDTCapNhat.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSDTCapNhat.setBounds(142, 58, 203, 35);
		pnlCapNhat.add(txtSDTCapNhat);
		txtSDTCapNhat.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ngày vào làm:");
		lblNewLabel_3_1.setBounds(10, 106, 122, 31);
		pnlCapNhat.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JButton btnCapNhatTT = new JButton("Cập nhật");
		btnCapNhatTT.setFocusable(false);
		btnCapNhatTT.setForeground(Color.WHITE);
		btnCapNhatTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Câp nhật thông tin tài khoản của bạn?")==JOptionPane.OK_OPTION) {
						// get data
						String hoTen = txtHoTenCapNhat.getText();
						java.util.Date date = txtNgayVLCapNhat.getDate();
						String soDT = txtSDTCapNhat.getText();
						// check valid
						if (hoTen.equals("") || date == null || soDT.equals("")) {
							AppHelper.thongBaoNhapThieuTruong(getRootPane(), "đầy đủ thông tin");
							return;
						}
						// update
						Date ngVL = new Date(date.getTime());
						user.setHoTen(hoTen);
						user.setNgVL(ngVL);
						user.setSoDT(soDT);
						if(nvDB.capNhatThongTin(user)!= 0) {
							// update panel
							loadUserInfo();
							pnlCapNhat.setVisible(false);	
							btnHienCN.setBackground(Color.WHITE);
							AppHelper.thongBao(getRootPane(), "Cập nhật thành công!");
						}
					}
				}catch (Exception e1) {
					AppHelper.thongBaoLoiCapNhat(getRootPane());
					e1.printStackTrace();
				}
			}
		});
		btnCapNhatTT.setBackground(new Color(AppConstants.VIOLET));
		btnCapNhatTT.setFont(new Font("Arial", Font.BOLD, 18));
		btnCapNhatTT.setBorder(null);
		btnCapNhatTT.setBounds(142, 150, 203, 48);
		pnlCapNhat.add(btnCapNhatTT);
		
		JButton btnHuyCN = new JButton("Hủy");
		btnHuyCN.setFocusable(false);
		btnHuyCN.setForeground(Color.WHITE);
		btnHuyCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienCN.setBackground(Color.WHITE);
				pnlCapNhat.setVisible(false);
			}
		});
		btnHuyCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyCN.setBorder(null);
		btnHuyCN.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyCN.setBounds(10, 150, 122, 48);
		pnlCapNhat.add(btnHuyCN);
		
		txtNgayVLCapNhat = new JDateChooser();
		txtNgayVLCapNhat.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayVLCapNhat.setLocale(new Locale("vi", "VN"));
		txtNgayVLCapNhat.getCalendarButton().setBackground(Color.WHITE);
		txtNgayVLCapNhat.setBounds(142, 105, 203, 34);
		pnlCapNhat.add(txtNgayVLCapNhat);
		
		pnlDoiMK = new JPanel();
		pnlDoiMK.setBounds(10, 718, 358, 217);
		panel_1.add(pnlDoiMK);
		pnlDoiMK.setLayout(null);
		pnlDoiMK.setVisible(false);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("MK cũ:");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 14, 122, 31);
		pnlDoiMK.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("MK mới:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_1.setBounds(10, 60, 122, 31);
		pnlDoiMK.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Nhập lại MK:");
		lblNewLabel_3_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(10, 106, 122, 31);
		pnlDoiMK.add(lblNewLabel_3_1_1);
		
		JButton btnDoiMK = new JButton("Đổi");
		btnDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Câp nhật mật khẩu cho tài khoản của bạn?")==JOptionPane.OK_OPTION) {
						// get data
						String mkCu = String.valueOf(txtMKCu.getPassword());
						String mkMoi = String.valueOf(txtMKMoi.getPassword());
						String mkMoi2 = String.valueOf(txtMKMoi2.getPassword());
						// check valid
						if (mkCu.equals("") || mkMoi == null || mkMoi2.equals("")) {
							AppHelper.thongBaoNhapThieuTruong(getRootPane(), "đầy đủ thông tin");
							return;
						}
						if (!mkCu.equals(user.getMatKhau())) {
							AppHelper.thongBaoLoi(getRootPane(), "Mật khẩu cũ không đúng. Vui lòng kiểm tra lại!");
							return;
						}
						if (!mkMoi.equals(mkMoi2)) {
							AppHelper.thongBaoLoi(getRootPane(), "Mật khẩu nhập lại không khớp!");
							return;
						}
						
						// update
						user.setMatKhau(mkMoi);
						if(nvDB.capNhatThongTin(user)!= 0) {
							AppHelper.thongBao(getRootPane(), "Cập nhật thành công!");
							btnHienDoiMK.setBackground(Color.WHITE);
							pnlDoiMK.setVisible(false);	
						}
					}
				}catch (Exception e1) {
					AppHelper.thongBaoLoiCapNhat(getRootPane());
					e1.printStackTrace();
				}
			}
		});
		btnDoiMK.setFocusable(false);
		btnDoiMK.setForeground(Color.WHITE);
		btnDoiMK.setFont(new Font("Arial", Font.BOLD, 18));
		btnDoiMK.setBorder(null);
		btnDoiMK.setBackground(new Color(AppConstants.VIOLET));
		btnDoiMK.setBounds(142, 150, 203, 48);
		pnlDoiMK.add(btnDoiMK);
		
		txtMKCu = new JPasswordField();
		txtMKCu.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMKCu.setBounds(142, 12, 203, 35);
		pnlDoiMK.add(txtMKCu);
		
		txtMKMoi = new JPasswordField();
		txtMKMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMKMoi.setBounds(142, 58, 203, 35);
		pnlDoiMK.add(txtMKMoi);
		
		txtMKMoi2 = new JPasswordField();
		txtMKMoi2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMKMoi2.setBounds(142, 104, 203, 35);
		pnlDoiMK.add(txtMKMoi2);
		
		JButton btnHuyDoiMK = new JButton("Hủy");
		btnHuyDoiMK.setFocusable(false);
		btnHuyDoiMK.setForeground(Color.WHITE);
		btnHuyDoiMK.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyDoiMK.setBorder(null);
		btnHuyDoiMK.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyDoiMK.setBounds(10, 150, 122, 48);
		pnlDoiMK.add(btnHuyDoiMK);
		
		JLabel lblTextVaiTro = new JLabel("Vai trò:");
		lblTextVaiTro.setForeground(Color.WHITE);
		lblTextVaiTro.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTextVaiTro.setBounds(10, 234, 85, 30);
		panel_1.add(lblTextVaiTro);
		
		lblVaiTroUser = new JLabel("Admin");
		lblVaiTroUser.setForeground(Color.WHITE);
		lblVaiTroUser.setFont(new Font("Arial", Font.BOLD, 18));
		lblVaiTroUser.setBounds(103, 234, 265, 30);
		panel_1.add(lblVaiTroUser);
		btnHuyDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienDoiMK.setBackground(Color.WHITE);
				pnlDoiMK.setVisible(false);
			}
		});
		
		loadData();
		listHD = hdDB.topHoaDonMoiNhat(10);
		hienThiHoaDon() ;
	}
	
	private void loadData() {
		loadUserInfo();
		
		//load feature number
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
				
		lblTongSoHD.setText(String.valueOf(hdDB.tatCa().size()));
		lblTongSoKH.setText(String.valueOf(khDB.tatCa().size()));
		lblTongSoNV.setText(String.valueOf(nvDB.tatCa().size()));
		lblSoLoaiSP.setText(String.valueOf(spDB.tatCa().size()));
		lblTongDoanhSo.setText(nf.format(hdDB.tongDoanhSo())+" VNĐ");
		lblSoLuongDaBan.setText(String.valueOf(cthdDB.getSoLuongSPDaBan()));
	}
	
	private void loadUserInfo() {
		lblMaUser.setText(user.getMaNV());
		lblHoTenUser.setText(user.getHoTen());
		lblSDTUser.setText(user.getSoDT());
		lblVaiTroUser.setText(user.getVaiTro());
		lblNgayVLUser.setText(user.getNgayVLToString());
	}
	
	private void hienThiHoaDon() {		
		DefaultTableModel dtm = new DefaultTableModel(){
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
		dtm.addColumn("STT");
		dtm.addColumn("SỐ HĐ");
		dtm.addColumn("NGÀY HĐ");
		dtm.addColumn("TT KHÁCH HÀNG");
		dtm.addColumn("TT NHÂN VIÊN");
		dtm.addColumn("TRỊ GIÁ");
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		int i = 1;
		if (listHD != null) {
			for (HoaDon hd : listHD) {
				KhachHangDB khDB = new KhachHangDB();
				NhanVienDB nvDB = new NhanVienDB();
				KhachHang kh = khDB.timTheoMaKH(hd.getMaKhachKhang());
				NhanVien nv = nvDB.timTheoMaNV(hd.getMaNhanVien());
	
				Object[] data = {i++,hd.getSoHoaDon(),hd.getNgayHoaDonToString(),(kh==null?AppConstants.EMPTY:kh.getMaKH()+" - "+ kh.getHoTen()),
						(nv==null?AppConstants.EMPTY:nv.getMaNV()+" - "+nv.getHoTen()),nf.format(hd.getTriGia())+" VNĐ"};
				dtm.addRow(data);
			}
		}
		
		tblHienThi.setModel(dtm);
		tblHienThi.getColumnModel().getColumn(0).setMaxWidth(40);
		tblHienThi.getColumnModel().getColumn(1).setMaxWidth(60);
		tblHienThi.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblHienThi.getColumnModel().getColumn(2).setMaxWidth(120);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment( JLabel.RIGHT );
		tblHienThi.getColumnModel().getColumn(5).setCellRenderer(renderer);
		tblHienThi.setRowHeight(40);
		tblHienThi.setFont(new Font("Arial", Font.PLAIN, 18));
		tblHienThi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
			
	}
	
	private void hienThiSanPham(List<String> listsl) {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("MÃ SP");
		dtm.addColumn("TÊN SP");
		dtm.addColumn("ĐVT");
		dtm.addColumn("NƯỚC SX");
		dtm.addColumn("Đã Bán");
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		if (listSP != null) {
			int i = 1;
			for (SanPham item : listSP) {
				Object[] data = {i++,item.getMaSP(),item.getTenSP(),item.getDonViTinh(),item.getNuocSX(),listsl.get(i-2)};
				dtm.addRow(data);
			}
		}
		tblHienThi.setModel(dtm);
		tblHienThi.getColumnModel().getColumn(0).setMaxWidth(40);
		tblHienThi.getColumnModel().getColumn(1).setMaxWidth(60);
		tblHienThi.setRowHeight(40);
		tblHienThi.setFont(new Font("Arial", Font.PLAIN, 18));
		tblHienThi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
	}
	
	private void hienThiKhachHang() {
		DefaultTableModel dtm = new DefaultTableModel(){
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
		dtm.addColumn("STT");
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
		int i = 1;
		if (listKH != null) {
			for (KhachHang item : listKH) {
				Object[] data = {i++,item.getMaKH(),item.getHoTen(),item.getDiaChi(),item.getSoDT(), item.getNgaySinhToString(),
						item.getNgayDKyToString(),item.getLoaiKH(),nf.format(item.getDoanhSo())+" VNĐ"};
				dtm.addRow(data);
			}
		}
		
		tblHienThi.setModel(dtm);
		tblHienThi.getColumnModel().getColumn(0).setMaxWidth(40);
		tblHienThi.getColumnModel().getColumn(1).setMaxWidth(60);
		tblHienThi.setRowHeight(40);
		tblHienThi.setFont(new Font("Arial", Font.PLAIN, 18));
		tblHienThi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));;
	}
}
