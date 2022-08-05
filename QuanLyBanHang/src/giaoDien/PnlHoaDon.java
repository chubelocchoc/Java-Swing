package giaoDien;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import tienIch.AppConstants;
import tienIch.AppHelper;
import xuLyDuLieu.ChiTietHoaDonDB;
import xuLyDuLieu.HoaDonDB;
import xuLyDuLieu.KhachHangDB;
import xuLyDuLieu.NhanVienDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class PnlHoaDon extends JPanel {
	private JPanel pnlDoiTTHD;
	private JTextField txtTiemKiem;
	private JTable tblHoaDon;
	private JPanel pnlThemHD;
	private JButton btnHienCN;
	private JButton btnHienThem;
	private JLabel lblChuyenCTHD;
//	private JPanel jpnView;
	private ChuyenPanelTheoDanhMuc controller;
	private JLabel lblSoHD;
	private JLabel lblNgHD;
	private JLabel lblNhanVien;
	private JLabel lblKhachHang;
	private JLabel lblTriGia;
	private JLabel lblSoHDTimThay;
	private JLabel lblHDTriGiaMax;
	private JLabel lblHDTriGiaMin;
	private JLabel lblTriGiaTB;
	private JLabel lblTongTriGia;
	
	//data
	private List<HoaDon> listHD = null;
	private HoaDon hdHienTai = null;
	private JDateChooser txtNgHDCapNhat;
	private JComboBox<String> cboNhanVienCN;
	private JComboBox<String> cboKhachHangCN;
	private JComboBox<String> cboKhachHangThem;
	private NhanVien user;
	private HoaDonDB hdDB = new HoaDonDB();


//	public void setJpnView(JPanel jpnView) {
//		this.jpnView = jpnView;
//	}
	public void setController(ChuyenPanelTheoDanhMuc controller) {
		this.controller = controller;
	}
	public void setUser(NhanVien user) {
		this.user = user;
	}
	public NhanVien getUser() {
		return user;
	}
	public void setHdHienTai(HoaDon hdHienTai) {
		this.hdHienTai = hdHienTai;
	}
	public HoaDon getHdHienTai() {
		return hdHienTai;
	}
	/**
	 * Create the panel.
	 */
	public PnlHoaDon() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(AppConstants.MAU_XAM_NHAT));
		panel.setBounds(0, 0, 743, 814);
		add(panel);
		panel.setLayout(null);
		
		txtTiemKiem = new JTextField();
		txtTiemKiem.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTiemKiem.setBounds(10, 11, 326, 43);
		panel.add(txtTiemKiem);
		txtTiemKiem.setColumns(10);
		
		JComboBox<String> cboTimKiem = new JComboBox<String>();
		cboTimKiem.setFont(new Font("Arial", Font.PLAIN, 20));
		cboTimKiem.setModel(new DefaultComboBoxModel<String>(new String[] {AppConstants.SO_HOA_DON, AppConstants.MA_KH, AppConstants.TEN_KH, 
				AppConstants.MA_NV, AppConstants.TEN_NV, AppConstants.NGAY_HD, AppConstants.THANG_HD,AppConstants.NAM_HD,
				AppConstants.TRIGIA_CAOHON, AppConstants.TRIGIA_THAPHON}));
		cboTimKiem.setBounds(346, 11, 227, 43);
		panel.add(cboTimKiem);
		
		JButton btnTiemKiem = new JButton("Tìm kiếm");
		btnTiemKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String thongTin = txtTiemKiem.getText();
					if (thongTin.equals("")) {
						listHD = hdDB.tatCa();
						hienThi();
						return;
					}
					if (cboTimKiem.getSelectedItem().equals(AppConstants.SO_HOA_DON)) {
						int soHD = Integer.parseInt(thongTin);
						listHD = new ArrayList<HoaDon>();
						HoaDon hd = hdDB.timTheoSoHD(soHD);
						if (hd!=null) listHD.add(hd);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.MA_NV)) {
						listHD = hdDB.timTheoMaNV(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.MA_KH)){
						listHD = hdDB.timTheoMaKH(thongTin);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.TEN_NV)) {
//						listHD = hdDB.timTheoMaNV(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.TEN_KH)){
//						listHD = hdDB.timTheoMaKH(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NGAY_HD)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NGAY);
						if (date != null) {
							listHD = hdDB.timTheoNgHD(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.THANG_HD)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_THANG);
						if (date != null) {
//							listHD = hdDB.timTheoNgHD(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.NAM_HD)) {
						java.sql.Date date = AppHelper.kiemTraNgayHopLe(getParent(), thongTin, AppConstants.DD_NAM);
						if (date != null) {
//							listHD = hdDB.timTheoNgHD(date);
						}
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.TRIGIA_CAOHON)) {
//						listHD = hdDB.timTheoNgHD();
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.TRIGIA_THAPHON)) {
//						listHD = hdDB.timTheoNgHD();
					}
					hienThi();
				}catch (Exception e1) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
		btnTiemKiem.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconTimKiem.png")));
		btnTiemKiem.setForeground(Color.WHITE);
		btnTiemKiem.setFont(new Font("Arial", Font.BOLD, 22));
		btnTiemKiem.setFocusable(false);
		btnTiemKiem.setBorder(null);
		btnTiemKiem.setBackground(new Color(73, 80, 87));
		btnTiemKiem.setBounds(583, 11, 150, 43);
		panel.add(btnTiemKiem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 723, 393);
		panel.add(scrollPane);
		
		tblHoaDon = new JTable();
		tblHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblHoaDon.getSelectedRow();
				String soHD = tblHoaDon.getModel().getValueAt(row, 0).toString();
				lblSoHD.setText(soHD);
				lblNgHD.setText(tblHoaDon.getModel().getValueAt(row, 1).toString());
				lblNhanVien.setText(tblHoaDon.getModel().getValueAt(row, 3).toString());
				lblKhachHang.setText(tblHoaDon.getModel().getValueAt(row, 2).toString());
				lblTriGia.setText(tblHoaDon.getModel().getValueAt(row, 4).toString());
				hdHienTai = hdDB.timTheoSoHD(Integer.parseInt(soHD));
				//reset
				pnlDoiTTHD.setVisible(false);
				pnlThemHD.setVisible(false);
				btnHienCN.setBackground(Color.WHITE);
				btnHienThem.setBackground(Color.WHITE);
			}
		});
		tblHoaDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tblHoaDon.setFont(new Font("Arial", Font.PLAIN, 16));
		tblHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));;
		scrollPane.setViewportView(tblHoaDon);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 469, 316, 334);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tiện ích");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2.setBounds(10, 11, 99, 33);
		panel_2.add(lblNewLabel_2);
		
		JButton btnHC = new JButton("HĐ có trị giá cao nhất      ");
		btnHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listHD = hdDB.topHoaDonCaoNhat(1);
				hienThi();
			}
		});
		btnHC.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconStarFull.png")));
		btnHC.setForeground(Color.WHITE);
		btnHC.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC.setFocusable(false);
		btnHC.setBorder(null);
		btnHC.setBackground(new Color(AppConstants.VIOLET));
		btnHC.setAlignmentX(0.5f);
		btnHC.setBounds(10, 106, 296, 46);
		panel_2.add(btnHC);
		
		JButton btnHC_2 = new JButton("10 HĐ có trị giá cao nhất ");
		btnHC_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listHD = hdDB.topHoaDonCaoNhat(10);
				hienThi();
			}
		});
		btnHC_2.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconHoaDon.png")));
		btnHC_2.setForeground(Color.WHITE);
		btnHC_2.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2.setFocusable(false);
		btnHC_2.setBorder(null);
		btnHC_2.setBackground(new Color(54, 79, 199));
		btnHC_2.setAlignmentX(0.5f);
		btnHC_2.setBounds(10, 220, 296, 46);
		panel_2.add(btnHC_2);
		
		JButton btnHC_2_1 = new JButton("10 HĐ có trị giá thấp nhất");
		btnHC_2_1.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconHoaDon.png")));
		btnHC_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listHD = hdDB.topHoaDonThapNhat(10);
				hienThi();
			}
		});
		btnHC_2_1.setForeground(Color.WHITE);
		btnHC_2_1.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2_1.setFocusable(false);
		btnHC_2_1.setBorder(null);
		btnHC_2_1.setBackground(new Color(54, 79, 199));
		btnHC_2_1.setAlignmentX(0.5f);
		btnHC_2_1.setBounds(10, 277, 296, 46);
		panel_2.add(btnHC_2_1);
		
		JButton btnHC_2_2 = new JButton("HĐ có trị giá thấp nhất     ");
		btnHC_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listHD = hdDB.topHoaDonThapNhat(1);
				hienThi();
			}
		});
		btnHC_2_2.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconStar.png")));
		btnHC_2_2.setForeground(Color.WHITE);
		btnHC_2_2.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2_2.setFocusable(false);
		btnHC_2_2.setBorder(null);
		btnHC_2_2.setBackground(new Color(AppConstants.VIOLET));
		btnHC_2_2.setAlignmentX(0.5f);
		btnHC_2_2.setBounds(10, 163, 296, 46);
		panel_2.add(btnHC_2_2);
		
		JButton btnHanCa = new JButton("Hóa đơn của tôi                ");
		btnHanCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listHD = hdDB.timTheoMaNV(user.getMaNV());
				txtTiemKiem.setText(user.getMaNV());
				cboTimKiem.setSelectedItem(AppConstants.MA_NV);
				hienThi();
			}
		});
		btnHanCa.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconNhanVienNho.png")));
		btnHanCa.setForeground(Color.WHITE);
		btnHanCa.setFont(new Font("Arial", Font.BOLD, 18));
		btnHanCa.setFocusable(false);
		btnHanCa.setBorder(null);
		btnHanCa.setBackground(new Color(112, 72, 232));
		btnHanCa.setAlignmentX(0.5f);
		btnHanCa.setBounds(10, 49, 296, 46);
		panel_2.add(btnHanCa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(336, 469, 397, 334);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Thống kê");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2_1.setBounds(10, 11, 130, 33);
		panel_3.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Số HĐ tìm thấy: ");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2.setBounds(20, 54, 149, 33);
		panel_3.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("TỔNG TRỊ GIÁ:");
		lblNewLabel_2_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_1.setBounds(20, 287, 141, 33);
		panel_3.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("HD có trị giá cao nhất:");
		lblNewLabel_2_1_2_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2.setBounds(20, 96, 209, 33);
		panel_3.add(lblNewLabel_2_1_2_2);
		
		JLabel lblNewLabel_2_1_2_2_1 = new JLabel("HD có trị giá thấp nhất:");
		lblNewLabel_2_1_2_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1.setBounds(20, 168, 209, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1);
		
		lblSoHDTimThay = new JLabel(AppConstants.NO_INFO);
		lblSoHDTimThay.setFont(new Font("Arial", Font.BOLD, 18));
		lblSoHDTimThay.setBounds(171, 54, 216, 33);
		panel_3.add(lblSoHDTimThay);
		
		lblTongTriGia = new JLabel(AppConstants.NO_INFO);
		lblTongTriGia.setForeground(new Color(AppConstants.MAU_DO));
		lblTongTriGia.setFont(new Font("Arial", Font.BOLD, 22));
		lblTongTriGia.setBounds(171, 289, 216, 33);
		panel_3.add(lblTongTriGia);
		
		lblHDTriGiaMax = new JLabel(AppConstants.NO_INFO);
		lblHDTriGiaMax.setForeground(Color.BLACK);
		lblHDTriGiaMax.setFont(new Font("Arial", Font.BOLD, 18));
		lblHDTriGiaMax.setBounds(102, 133, 285, 33);
		panel_3.add(lblHDTriGiaMax);
		
		lblHDTriGiaMin = new JLabel(AppConstants.NO_INFO);
		lblHDTriGiaMin.setForeground(Color.BLACK);
		lblHDTriGiaMin.setFont(new Font("Arial", Font.BOLD, 18));
		lblHDTriGiaMin.setBounds(102, 207, 285, 33);
		panel_3.add(lblHDTriGiaMin);
		
		JLabel lblNewLabel_2_1_2_2_1_1 = new JLabel("Trị giá trung bình:");
		lblNewLabel_2_1_2_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1_1.setBounds(20, 247, 149, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1_1);
		
		lblTriGiaTB = new JLabel(AppConstants.NO_INFO);
		lblTriGiaTB.setForeground(new Color(54, 79, 199));
		lblTriGiaTB.setFont(new Font("Arial", Font.BOLD, 18));
		lblTriGiaTB.setBounds(171, 247, 216, 33);
		panel_3.add(lblTriGiaTB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(AppConstants.MAU_TIM_NHAT_3));
		panel_1.setBounds(743, 0, 401, 814);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN HÓA ĐƠN");
		lblNewLabel.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconThongTinHoaDonNho.png")));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 381, 59);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Số HD:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 92, 85, 30);
		panel_1.add(lblNewLabel_1);
		
		lblSoHD = new JLabel(AppConstants.NO_INFO);
		lblSoHD.setForeground(Color.BLACK);
		lblSoHD.setFont(new Font("Arial", Font.BOLD, 28));
		lblSoHD.setBounds(122, 81, 157, 44);
		panel_1.add(lblSoHD);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ngày tạo:");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 136, 85, 30);
		panel_1.add(lblNewLabel_1_1);
		
		lblNgHD = new JLabel(AppConstants.NO_INFO);
		lblNgHD.setForeground(Color.BLACK);
		lblNgHD.setFont(new Font("Arial", Font.BOLD, 18));
		lblNgHD.setBounds(122, 136, 269, 30);
		panel_1.add(lblNgHD);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhân viên: ");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 177, 95, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		lblNhanVien = new JLabel(AppConstants.NO_INFO);
		lblNhanVien.setForeground(Color.BLACK);
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 18));
		lblNhanVien.setBounds(122, 177, 269, 30);
		panel_1.add(lblNhanVien);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Khách hàng:");
		lblNewLabel_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(10, 218, 106, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		lblKhachHang = new JLabel(AppConstants.NO_INFO);
		lblKhachHang.setForeground(Color.BLACK);
		lblKhachHang.setFont(new Font("Arial", Font.BOLD, 18));
		lblKhachHang.setBounds(122, 218, 269, 30);
		panel_1.add(lblKhachHang);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Trị giá:");
		lblNewLabel_1_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(10, 273, 106, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		lblTriGia = new JLabel(AppConstants.NO_INFO);
		lblTriGia.setForeground(new Color(AppConstants.MAU_TIM_DAM));
		lblTriGia.setFont(new Font("Arial", Font.BOLD, 24));
		lblTriGia.setBounds(122, 271, 269, 30);
		panel_1.add(lblTriGia);
		
		btnHienCN = new JButton("   Cập nhật thông tin    ");
		btnHienCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hdHienTai==null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn hóa đơn cần cập nhật");
				}else {
					btnHienCN.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
					btnHienThem.setBackground(Color.WHITE);
					pnlDoiTTHD.setVisible(true);
					pnlThemHD.setVisible(false);
					
					// load data
					txtNgHDCapNhat.setDate(hdHienTai.getNgayHoaDon());
					cboKhachHangCN.setSelectedItem(lblKhachHang.getText());
					cboNhanVienCN.setSelectedItem(lblNhanVien.getText());
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
		btnHienCN.setBounds(10, 390, 381, 49);
		panel_1.add(btnHienCN);
		
		pnlDoiTTHD = new JPanel();
		pnlDoiTTHD.setVisible(false);
		pnlDoiTTHD.setBounds(10, 510, 381, 217);
		panel_1.add(pnlDoiTTHD);
		pnlDoiTTHD.setLayout(null);
		
		JLabel lblNewLabel_1_1_11 = new JLabel("Ngày tạo:");
		lblNewLabel_1_1_11.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_11.setBounds(10, 14, 122, 31);
		pnlDoiTTHD.add(lblNewLabel_1_1_11);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Nhân viên:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_1.setBounds(10, 60, 122, 31);
		pnlDoiTTHD.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Khách hàng:");
		lblNewLabel_3_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(10, 106, 122, 31);
		pnlDoiTTHD.add(lblNewLabel_3_1_1);
		
		JButton btnCapNhat = new JButton("Đổi");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Cập nhật lại hóa đơn "+hdHienTai.getSoHoaDon()+"?") 
							== JOptionPane.OK_OPTION) {
						Date ngHD = txtNgHDCapNhat.getDate();
						String maNV = (String) cboNhanVienCN.getSelectedItem();
						String maKH = (String) cboKhachHangCN.getSelectedItem();
						
						//update database
						java.sql.Date date = new java.sql.Date(ngHD.getTime());
						hdHienTai.setNgayHoaDon(date);
						hdHienTai.setMaNhanVien(maNV.equals(AppConstants.EMPTY)?null:maNV.substring(0, 4));
						hdHienTai.setMaKhachKhang(maKH.equals(AppConstants.EMPTY)?null:maKH.substring(0,4));
						hdDB.capNhatThongTin(hdHienTai);
						
						//update panel
						lblNgHD.setText(hdHienTai.getNgayHoaDonToString());
						lblNhanVien.setText(maNV);
						lblKhachHang.setText(maKH);
						listHD = new ArrayList<HoaDon>();
						listHD.add(hdHienTai);
						hienThi();
						pnlDoiTTHD.setVisible(false);
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
		btnCapNhat.setBounds(142, 150, 229, 48);
		pnlDoiTTHD.add(btnCapNhat);
		
		JButton btnHuyCN = new JButton("Hủy");
		btnHuyCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTHD.setVisible(false);
			}
		});
		btnHuyCN.setFocusable(false);
		btnHuyCN.setForeground(Color.WHITE);
		btnHuyCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyCN.setBorder(null);
		btnHuyCN.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyCN.setBounds(10, 150, 122, 48);
		pnlDoiTTHD.add(btnHuyCN);
		
		cboNhanVienCN = new JComboBox<String>();
		cboNhanVienCN.setModel(new DefaultComboBoxModel<String>(new String[] {"<Trống>"}));
		cboNhanVienCN.setFont(new Font("Arial", Font.PLAIN, 16));
		cboNhanVienCN.setBounds(142, 58, 229, 35);
		pnlDoiTTHD.add(cboNhanVienCN);
		
		cboKhachHangCN = new JComboBox<String>();
		cboKhachHangCN.setModel(new DefaultComboBoxModel<String>(new String[] {"<Trống>"}));
		cboKhachHangCN.setFont(new Font("Arial", Font.PLAIN, 16));
		cboKhachHangCN.setBounds(142, 104, 229, 35);
		pnlDoiTTHD.add(cboKhachHangCN);
		
		txtNgHDCapNhat = new JDateChooser();
		txtNgHDCapNhat.setDateFormatString("dd-MM-yyyy");
		txtNgHDCapNhat.setBounds(142, 12, 229, 34);
		pnlDoiTTHD.add(txtNgHDCapNhat);
		txtNgHDCapNhat.getCalendarButton().setBackground(Color.WHITE);
		txtNgHDCapNhat.setLocale(new Locale("vi", "VN"));
		txtNgHDCapNhat.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton btnHienCN_1 = new JButton(" Xóa");
		btnHienCN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hdHienTai == null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn hóa đơn cần xóa!");
					return;
				}
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Xóa hóa đơn "+hdHienTai.getSoHoaDon()+" và các CTHD liên quan?") 
							== JOptionPane.OK_OPTION) {
						// xoa CTHD
						ChiTietHoaDonDB cthdDB = new ChiTietHoaDonDB();
						List<ChiTietHoaDon> listCTHD = cthdDB.timTheoSoHD(hdHienTai.getSoHoaDon());
						for (ChiTietHoaDon cthd: listCTHD) {
							cthdDB.xoaCTHD(cthd.getSoHoaDon(),cthd.getMaSP());
						}
						//update database
						hdDB.xoaHoaDon(hdHienTai.getSoHoaDon());
						
						//update panel
						hdHienTai = null;
						listHD = new ArrayList<HoaDon>();
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
		btnHienCN_1.setBounds(285, 81, 106, 44);
		panel_1.add(btnHienCN_1);
		
		btnHienThem = new JButton("   Thêm hóa đơn mới   ");
		btnHienThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTHD.setVisible(false);
				pnlThemHD.setVisible(true);
				cboKhachHangThem.setSelectedItem(AppConstants.EMPTY);
			}
		});
		btnHienThem.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconThemDen.png")));
		btnHienThem.setForeground(Color.BLACK);
		btnHienThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienThem.setFocusable(false);
		btnHienThem.setBorder(null);
		btnHienThem.setBackground(Color.WHITE);
		btnHienThem.setAlignmentX(0.5f);
		btnHienThem.setBounds(10, 450, 381, 49);
		panel_1.add(btnHienThem);
		
		pnlThemHD = new JPanel();
		pnlThemHD.setVisible(false);
		pnlThemHD.setBounds(10, 510, 381, 168);
		panel_1.add(pnlThemHD);
		pnlThemHD.setLayout(null);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Khách hàng:");
		lblNewLabel_3_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1.setBounds(10, 11, 122, 31);
		pnlThemHD.add(lblNewLabel_3_1_1_1);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themHoaDon();
				pnlThemHD.setVisible(false);
				btnHienThem.setBackground(Color.WHITE);
			}
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnThem.setFocusable(false);
		btnThem.setBorder(null);
		btnThem.setBackground(new Color(AppConstants.MAU_TIM));
		btnThem.setBounds(142, 53, 229, 48);
		pnlThemHD.add(btnThem);
		
		JButton btnHuyThem = new JButton("Hủy");
		btnHuyThem.setForeground(Color.WHITE);
		btnHuyThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyThem.setFocusable(false);
		btnHuyThem.setBorder(null);
		btnHuyThem.setBackground(new Color(73, 80, 87));
		btnHuyThem.setBounds(10, 53, 122, 48);
		pnlThemHD.add(btnHuyThem);
		btnHuyThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(Color.WHITE);
				pnlThemHD.setVisible(false);
			}
		});
		
		cboKhachHangThem = new JComboBox<String>();
		cboKhachHangThem.setModel(new DefaultComboBoxModel<String>(new String[] {"<Trống>"}));
		cboKhachHangThem.setFont(new Font("Arial", Font.PLAIN, 16));
		cboKhachHangThem.setBounds(142, 9, 229, 35);
		pnlThemHD.add(cboKhachHangThem);
		
		JButton btnThem2 = new JButton("Thêm và chuyển tới CTHD");
		btnThem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (themHoaDon()) controller.setViewCTHD();
			}
		});
		btnThem2.setForeground(Color.WHITE);
		btnThem2.setFont(new Font("Arial", Font.BOLD, 18));
		btnThem2.setFocusable(false);
		btnThem2.setBorder(null);
		btnThem2.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		btnThem2.setBounds(10, 112, 361, 48);
		pnlThemHD.add(btnThem2);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 330, 381, 49);
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblChuyenCTHD = new JLabel("   Xem chi tiết hóa đơn");
		lblChuyenCTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hdHienTai!=null) {
					controller.setViewCTHD();					
				}else {
					AppHelper.thongBao(getRootPane(), "Bạn chưa chọn hóa đơn!");
				}
			}
		});

		panel_4.add(lblChuyenCTHD);
		lblChuyenCTHD.setBackground(Color.WHITE);
		lblChuyenCTHD.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconCTHDMauDen.png")));
		lblChuyenCTHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenCTHD.setFont(new Font("Arial", Font.BOLD, 18));
		
		loadData();
	}
	
	private void hienThi() {
		DefaultTableModel dtm = new DefaultTableModel(){
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
		dtm.addColumn("SỐ HĐ");
		dtm.addColumn("NGÀY HĐ");
		dtm.addColumn("TT KHÁCH HÀNG");
		dtm.addColumn("TT NHÂN VIÊN");
		dtm.addColumn("TRỊ GIÁ");
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		if (listHD != null) {
			for (HoaDon hd : listHD) {
				KhachHangDB khDB = new KhachHangDB();
				NhanVienDB nvDB = new NhanVienDB();
				KhachHang kh = khDB.timTheoMaKH(hd.getMaKhachKhang());
				NhanVien nv = nvDB.timTheoMaNV(hd.getMaNhanVien());
	
				Object[] data = {hd.getSoHoaDon(),hd.getNgayHoaDonToString(),(kh==null?AppConstants.EMPTY:kh.getMaKH()+" - "+ kh.getHoTen()),
						(nv==null?AppConstants.EMPTY:nv.getMaNV()+" - "+nv.getHoTen()),nf.format(hd.getTriGia())+" VNĐ"};
				dtm.addRow(data);
			}
		}
		
		tblHoaDon.setModel(dtm);
		tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(60);
		tblHoaDon.getColumnModel().getColumn(0).setMinWidth(60);
		tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(60);
		tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblHoaDon.getColumnModel().getColumn(1).setMinWidth(100);
		tblHoaDon.getColumnModel().getColumn(1).setMaxWidth(100);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment( JLabel.RIGHT );
		tblHoaDon.getColumnModel().getColumn(4).setCellRenderer(renderer);
		tblHoaDon.setRowHeight(40);
		tblHoaDon.setFont(new Font("Arial", Font.PLAIN, 18));
		tblHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		
		thongKe();
	}
	
	private void resetInfo() {
		if (hdHienTai == null) {
			lblSoHD.setText(AppConstants.NO_INFO);
			lblNgHD.setText(AppConstants.NO_INFO);
			lblNhanVien.setText(AppConstants.NO_INFO);
			lblKhachHang.setText(AppConstants.NO_INFO);
			lblTriGia.setText(AppConstants.NO_INFO);
		}else {
			KhachHangDB khDB = new KhachHangDB();
			NhanVienDB nvDB = new NhanVienDB();
			KhachHang kh = khDB.timTheoMaKH(hdHienTai.getMaKhachKhang());
			NhanVien nv = nvDB.timTheoMaNV(hdHienTai.getMaNhanVien());
			
			lblSoHD.setText(String.valueOf(hdHienTai.getSoHoaDon()));
			lblNgHD.setText(hdHienTai.getNgayHoaDonToString());
			lblNhanVien.setText(nv==null?AppConstants.EMPTY:nv.getMaNV()+" - "+nv.getHoTen());
			lblKhachHang.setText(kh==null?AppConstants.EMPTY:kh.getMaKH()+" - "+ kh.getHoTen());
			lblTriGia.setText(String.valueOf(hdHienTai.getTriGia()));
		}
	}
	
	private void thongKe() {
		if (listHD == null || listHD.isEmpty()) {
			lblSoHDTimThay.setText("0");
			lblHDTriGiaMax.setText(AppConstants.NO_INFO);
			lblHDTriGiaMin.setText(AppConstants.NO_INFO);
			lblTriGiaTB.setText(AppConstants.NO_INFO);
			lblTongTriGia.setText(AppConstants.NO_INFO);
			return;
		}
		
		lblSoHDTimThay.setText(String.valueOf(listHD.size()));
		// hoa don max - min - sum
		HoaDon max = listHD.get(0);
		HoaDon min = listHD.get(0);
		double sum = 0;
		
		for (HoaDon hd: listHD) {
			if (hd.getTriGia() > max.getTriGia()) max = hd;
			if (hd.getTriGia() < min.getTriGia()) min = hd;
			sum += hd.getTriGia();
		}
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		
		lblHDTriGiaMax.setText(max.getSoHoaDon()+" - "+nf.format(max.getTriGia())+" VNĐ");
		lblHDTriGiaMin.setText(min.getSoHoaDon()+" - "+nf.format(min.getTriGia())+" VNĐ");
		lblTriGiaTB.setText(nf.format(Math.round(sum/listHD.size()))+" "+ "VNĐ");
		lblTongTriGia.setText(nf.format(sum)+"VNĐ");
	}
	
	private void loadData() {
		// cbo nhan vien
		NhanVienDB nvDB = new NhanVienDB();
		List<NhanVien> listNV = nvDB.tatCa();
		DefaultComboBoxModel<String> dcmNV = new DefaultComboBoxModel<>();
		dcmNV.addElement(AppConstants.EMPTY);
		for(NhanVien nv: listNV) {
			if (!nv.getVaiTro().equals(AppConstants.VT_ADMIN)) dcmNV.addElement(nv.getMaNV()+" - "+nv.getHoTen());
		}
		cboNhanVienCN.setModel(dcmNV);
		
		// cbo khach hang
		KhachHangDB khDB = new KhachHangDB();
		List<KhachHang> listKh = khDB.tatCa();
		DefaultComboBoxModel<String> dcmKH = new DefaultComboBoxModel<>();
		dcmKH.addElement(AppConstants.EMPTY);
		for(KhachHang kh: listKh) {
			dcmKH.addElement(kh.getMaKH()+" - "+kh.getHoTen());
		}
		cboKhachHangCN.setModel(dcmKH);
		cboKhachHangThem.setModel(dcmKH);
	}
	
	private boolean themHoaDon() {
		boolean flag = false;
		try {
			HoaDonDB hdDB = new HoaDonDB();
			if (AppHelper.thongBaoXacNhan(getRootPane(), "Thêm hóa đơn mới?") == JOptionPane.OK_OPTION) {
				String maKH = (String) cboKhachHangCN.getSelectedItem();
				
				//update database
				Date now = new Date();
				java.sql.Date date = new java.sql.Date(now.getTime());
				HoaDon newHD = new HoaDon(0,date,(maKH.equals(AppConstants.EMPTY)?null:maKH.substring(0,4)),user.getMaNV(),0);
				
				hdDB.themHoaDon(newHD);
				
				//update panel
				listHD = hdDB.tatCa();
				hdHienTai = listHD.get(listHD.size()-1);
				listHD = new ArrayList<HoaDon>();
				listHD.add(hdHienTai);
				resetInfo();
				hienThi();
				flag = true;
			}
		} catch (Exception e2) {
			AppHelper.thongBaoLoiThem(getRootPane());
		}	
		return flag;
	}
	
	protected void hienThiHoaDonKhachHang(KhachHang kh) {
		listHD = hdDB.timTheoMaKH(kh.getMaKH());
		hienThi();
	}
	
	protected void hienThiHoaDonNhanVien(NhanVien nv) {
		listHD = hdDB.timTheoMaNV(nv.getMaNV());
		hienThi();
	}
	
	protected void hienThiHoaDon(HoaDon hoaDon) {
		hdHienTai = hoaDon;
		resetInfo();
		listHD = new ArrayList<HoaDon>();
		listHD.add(hoaDon);
		hienThi();
		tblHoaDon.setRowSelectionInterval(0, 0);
	}
}
