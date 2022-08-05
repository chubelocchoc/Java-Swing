package giaoDien;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import model.HoaDon;
import model.KhachHang;
import model.NhanVien;
import model.SanPham;
import tienIch.AppConstants;
import tienIch.AppHelper;
import xuLyDuLieu.SanPhamDB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlSanPham extends JPanel {
	
	private JPanel pnlDoiTTSP;
	private JPanel pnlThemSP;
	private JTextField txtTiemKiem;
	private JTable tblHienThi;
	private JButton btnHienCN;
	private JButton btnHienThem;
	private JTextField txtTenSPCN;
	private JTextField txtTenSPThem;
	private JTextField txtGiaCN;
	private JTextField txtGiaThem;
	private JTextField txtMaSPThem;
	private JLabel lblTongGia;
	private JLabel lblGiaTB;
	private JLabel lblSPThapNhat;
	private JLabel lblSPCaoNhat;
	private JLabel lblSoSPTimThay;
	private JLabel lblMaSP;
	private JLabel lblTenSP;
	private JLabel lblDVT;
	private JLabel lblNuocSX;
	private JLabel lblGia;
	
	//data
	private List<SanPham> listSP;
	private SanPhamDB spDB = new SanPhamDB();
	private SanPham spHienTai = null;
	private JComboBox<String> cboNuocSXCN;
	private JComboBox cboDVTCN;
	private JComboBox<String> cboNuocSXThem;
	private JComboBox cboDVTThem;
	/**
	 * Create the panel.
	 */
	public PnlSanPham() {
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
		
		JComboBox cboTimKiem = new JComboBox();
		cboTimKiem.setFont(new Font("Arial", Font.PLAIN, 20));
		cboTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Mã sản phẩm", "Tên sản phẩm", "ĐVT (cây, hộp, cái, quyển, chục)", "Nước sản xuất", "Giá thấp hơn", "Giá cao hơn"}));
		cboTimKiem.setBounds(346, 11, 227, 43);
		panel.add(cboTimKiem);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String thongTin = txtTiemKiem.getText();
					if (thongTin.equals("")) {
						listSP = spDB.tatCa();
						hienThi();
						return;
					}
					if (cboTimKiem.getSelectedItem().equals(AppConstants.MA_SP)) {
						listSP = new ArrayList<SanPham>();
						SanPham sp = spDB.timTheoMaSP(thongTin);
						if (sp!=null) listSP.add(sp);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.TEN_SP)) {
						listSP = spDB.timTheoTenSP(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.DVT)){
						listSP = spDB.timTheoDonViTinh(thongTin);
					}else if(cboTimKiem.getSelectedItem().equals(AppConstants.NUOC_SX)) {
						listSP = spDB.timTheoNuocSX(thongTin);
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.GIA_CAOHON)) {
//						listSP = hdDB.timTheoNgHD();
					}else if (cboTimKiem.getSelectedItem().equals(AppConstants.GIA_THAPHON)) {
//						listSP = hdDB.timTheoNgHD();
					}
					hienThi();
				}catch (Exception e1) {
					e1.printStackTrace();
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
		btnTimKiem.setIcon(new ImageIcon(PnlSanPham.class.getResource("/hinhAnh/IconTimKiem.png")));
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
				String maSP = tblHienThi.getModel().getValueAt(row, 1).toString();
				lblMaSP.setText(maSP);
				lblTenSP.setText(tblHienThi.getModel().getValueAt(row, 2).toString());
				lblDVT.setText(tblHienThi.getModel().getValueAt(row, 3).toString());
				lblNuocSX.setText(tblHienThi.getModel().getValueAt(row, 4).toString());
				lblGia.setText(tblHienThi.getModel().getValueAt(row, 5).toString());
				spHienTai = spDB.timTheoMaSP(maSP);
				
				//reset
				pnlDoiTTSP.setVisible(false);
				pnlThemSP.setVisible(false);
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
		
		JButton btnHC = new JButton("SP bán chạy nhất      ");
		btnHC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listSP = spDB.topSanPhamBanChay(1);
					hienThi();
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
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
		
		JButton btnHC_2 = new JButton("10 SP bán chạy nhất ");
		btnHC_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listSP = spDB.topSanPhamBanChay(10);
					hienThi();
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
		btnHC_2.setIcon(new ImageIcon(PnlSanPham.class.getResource("/hinhAnh/IconSanPham.png")));
		btnHC_2.setForeground(Color.WHITE);
		btnHC_2.setFont(new Font("Arial", Font.BOLD, 18));
		btnHC_2.setFocusable(false);
		btnHC_2.setBorder(null);
		btnHC_2.setBackground(new Color(54, 79, 199));
		btnHC_2.setAlignmentX(0.5f);
		btnHC_2.setBounds(10, 164, 296, 49);
		panel_2.add(btnHC_2);
		
		JButton btnHC_2_1 = new JButton("10 SP bán chậm nhất");
		btnHC_2_1.setIcon(new ImageIcon(PnlSanPham.class.getResource("/hinhAnh/IconSanPham.png")));
		btnHC_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listSP = spDB.topSanPhamBanCham(10);
					hienThi();
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
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
		
		JButton btnHC_2_2 = new JButton("SP bán chậm nhất     ");
		btnHC_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listSP = spDB.topSanPhamBanCham(1);
					hienThi();
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
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
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Số SP tìm thấy: ");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2.setBounds(20, 43, 149, 33);
		panel_3.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("TỔNG GIÁ:");
		lblNewLabel_2_1_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_1.setBounds(20, 240, 141, 33);
		panel_3.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("SP có giá cao nhất:");
		lblNewLabel_2_1_2_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2.setBounds(20, 74, 209, 33);
		panel_3.add(lblNewLabel_2_1_2_2);
		
		JLabel lblNewLabel_2_1_2_2_1 = new JLabel("SP có giá thấp nhất:");
		lblNewLabel_2_1_2_2_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1.setBounds(20, 142, 209, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1);
		
		lblSoSPTimThay = new JLabel("...");
		lblSoSPTimThay.setFont(new Font("Arial", Font.BOLD, 18));
		lblSoSPTimThay.setBounds(171, 43, 216, 33);
		panel_3.add(lblSoSPTimThay);
		
		lblTongGia = new JLabel("...");
		lblTongGia.setForeground(new Color(AppConstants.MAU_DO));
		lblTongGia.setFont(new Font("Arial", Font.BOLD, 22));
		lblTongGia.setBounds(171, 239, 216, 33);
		panel_3.add(lblTongGia);
		
		lblSPCaoNhat = new JLabel("...");
		lblSPCaoNhat.setForeground(Color.BLACK);
		lblSPCaoNhat.setFont(new Font("Arial", Font.BOLD, 18));
		lblSPCaoNhat.setBounds(102, 106, 285, 33);
		panel_3.add(lblSPCaoNhat);
		
		lblSPThapNhat = new JLabel("...");
		lblSPThapNhat.setForeground(Color.BLACK);
		lblSPThapNhat.setFont(new Font("Arial", Font.BOLD, 18));
		lblSPThapNhat.setBounds(102, 173, 285, 33);
		panel_3.add(lblSPThapNhat);
		
		JLabel lblNewLabel_2_1_2_2_1_1 = new JLabel("Giá trung bình:");
		lblNewLabel_2_1_2_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2_1_1.setBounds(20, 205, 149, 33);
		panel_3.add(lblNewLabel_2_1_2_2_1_1);
		
		lblGiaTB = new JLabel("...");
		lblGiaTB.setForeground(new Color(54, 79, 199));
		lblGiaTB.setFont(new Font("Arial", Font.BOLD, 18));
		lblGiaTB.setBounds(171, 205, 216, 33);
		panel_3.add(lblGiaTB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(AppConstants.MAU_TIM_NHAT_3));
		panel_1.setBounds(743, 0, 401, 814);
		add(panel_1);
		panel_1.setLayout(null);
		
		pnlThemSP = new JPanel();
		pnlThemSP.setVisible(false);
		
		pnlDoiTTSP = new JPanel();
		pnlDoiTTSP.setVisible(false);
		pnlDoiTTSP.setBounds(10, 452, 381, 254);
		panel_1.add(pnlDoiTTSP);
		pnlDoiTTSP.setLayout(null);
		
		JLabel lblNewLabel_1_1_11 = new JLabel("Tên SP:");
		lblNewLabel_1_1_11.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_11.setBounds(10, 11, 122, 31);
		pnlDoiTTSP.add(lblNewLabel_1_1_11);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Đơn vị tính:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_1.setBounds(10, 56, 122, 31);
		pnlDoiTTSP.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Nước SX:");
		lblNewLabel_3_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(10, 102, 122, 31);
		pnlDoiTTSP.add(lblNewLabel_3_1_1);
		
		JButton btnCapNhat = new JButton("Đổi");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Cập nhật thông tin sản phẩm có mã \""+spHienTai.getMaSP()+"\"?") 
							== JOptionPane.OK_OPTION) {
						String tenSP = txtTenSPCN.getText();
						String dvt = (String) cboDVTCN.getSelectedItem();
						String nuocSX = (String) cboNuocSXCN.getSelectedItem();
						double giaBan = Double.parseDouble(txtGiaCN.getText());
						
						//update database
						spHienTai.setTenSP(tenSP);
						spHienTai.setDonViTinh(dvt);
						spHienTai.setNuocSX(nuocSX);
						spHienTai.setGiaBan(giaBan);
						spDB.capNhatThongTin(spHienTai);
						
						//update panel
						listSP = new ArrayList<SanPham>();
						listSP.add(spHienTai);
						hienThi();
						
						// set info
						Locale lc = new Locale("vi","VN");
						NumberFormat nf = NumberFormat.getInstance(lc);
						lblTenSP.setText(spHienTai.getTenSP());
						lblDVT.setText(spHienTai.getDonViTinh());
						lblNuocSX.setText(spHienTai.getNuocSX());
						lblGia.setText(nf.format(spHienTai.getGiaBan())+" VNĐ");
						pnlDoiTTSP.setVisible(false);
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
		btnCapNhat.setBounds(142, 189, 229, 48);
		pnlDoiTTSP.add(btnCapNhat);
		
		JButton btnHuyCN = new JButton("Hủy");
		btnHuyCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienCN.setBackground(Color.WHITE);
				pnlDoiTTSP.setVisible(false);
			}
		});
		btnHuyCN.setFocusable(false);
		btnHuyCN.setForeground(Color.WHITE);
		btnHuyCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyCN.setBorder(null);
		btnHuyCN.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyCN.setBounds(10, 189, 122, 48);
		pnlDoiTTSP.add(btnHuyCN);
		
		cboDVTCN = new JComboBox();
		cboDVTCN.setModel(new DefaultComboBoxModel(new String[] {AppConstants.EMPTY, AppConstants.CAY, AppConstants.CHUC, AppConstants.CAI, AppConstants.QUYEN, AppConstants.HOP}));
		cboDVTCN.setFont(new Font("Arial", Font.PLAIN, 16));
		cboDVTCN.setBounds(142, 54, 229, 35);
		pnlDoiTTSP.add(cboDVTCN);
		
		cboNuocSXCN = new JComboBox<String>();
		cboNuocSXCN.setEditable(true);
		cboNuocSXCN.setModel(new DefaultComboBoxModel<String>(new String[] {AppConstants.EMPTY}));
		cboNuocSXCN.setFont(new Font("Arial", Font.PLAIN, 16));
		cboNuocSXCN.setBounds(142, 100, 229, 35);
		pnlDoiTTSP.add(cboNuocSXCN);
		
		txtTenSPCN = new JTextField();
		txtTenSPCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtTenSPCN.setBounds(142, 11, 229, 34);
		pnlDoiTTSP.add(txtTenSPCN);
		txtTenSPCN.setColumns(10);
		
		JLabel lblNewLabel_1_1_11_1 = new JLabel("Giá:");
		lblNewLabel_1_1_11_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_11_1.setBounds(10, 147, 122, 31);
		pnlDoiTTSP.add(lblNewLabel_1_1_11_1);
		
		txtGiaCN = new JTextField();
		txtGiaCN.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGiaCN.setFont(new Font("Arial", Font.PLAIN, 18));
		txtGiaCN.setColumns(10);
		txtGiaCN.setBounds(142, 144, 177, 34);
		pnlDoiTTSP.add(txtGiaCN);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("VNĐ");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1_1.setBounds(324, 146, 47, 30);
		pnlDoiTTSP.add(lblNewLabel_1_1_1_1_1_1);
		pnlThemSP.setBounds(10, 452, 381, 299);
		panel_1.add(pnlThemSP);
		pnlThemSP.setLayout(null);
		
		JLabel lblNewLabel_1_1_111 = new JLabel("Tên SP:");
		lblNewLabel_1_1_111.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_111.setBounds(10, 58, 122, 31);
		pnlThemSP.add(lblNewLabel_1_1_111);
		
		JLabel lblNewLabel_2_1_11 = new JLabel("Đơn vị tính:");
		lblNewLabel_2_1_11.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2_1_11.setBounds(10, 103, 122, 31);
		pnlThemSP.add(lblNewLabel_2_1_11);
		
		JLabel lblNewLabel_3_1_11 = new JLabel("Nước SX:");
		lblNewLabel_3_1_11.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1_11.setBounds(10, 149, 122, 31);
		pnlThemSP.add(lblNewLabel_3_1_11);
		
		JButton btnThemSP = new JButton("Thêm");
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Thêm sản phẩm mới?") == JOptionPane.OK_OPTION) {
						String maSP = txtMaSPThem.getText();
						String tenSP = txtTenSPThem.getText();
						String dvt = (String) cboDVTThem.getSelectedItem();
						String nuocSX = (String) cboNuocSXThem.getSelectedItem();
						
						//kiem tra nhap
						if (maSP.equals("")||tenSP.equals("")||nuocSX.equals("")||nuocSX.equals(AppConstants.EMPTY)||txtGiaThem.getText().equals("")) {
							AppHelper.thongBao(getRootPane(), "Vui lòng nhập đầy đủ thông tin!");
							return;
						}
						double gia = Double.parseDouble(txtGiaThem.getText());
						if (gia <= 0) {
							AppHelper.thongBao(getRootPane(), "Giá bán phải lớn hơn 0. Vui lòng kiểm tra lại!");
							return;
						}
						if (dvt.equals(AppConstants.EMPTY)) {
							AppHelper.thongBao(getRootPane(), "Vui lòng chọn đơn vị tính!");
							return;
						}
						
						//Kiem tra maKH
						if (spDB.kiemTraTonTaiMaSP(maSP)!=null) {
							AppHelper.thongBao(getRootPane(), "Mã sản phẩm đã tồn tại!");
							return;
						}
						
						//update database

						spHienTai = new SanPham(maSP, tenSP, dvt, nuocSX, gia);			
						spDB.themSanPham(spHienTai);
				
						//update panel
						listSP = new ArrayList<SanPham>();
						listSP.add(spHienTai);
						hienThi();
						
						// set info
						resetInfo();
						pnlThemSP.setVisible(false);
						btnHienThem.setBackground(Color.WHITE);
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiThem(getRootPane());
				}	
			}
		});
		btnThemSP.setFocusable(false);
		btnThemSP.setForeground(Color.WHITE);
		btnThemSP.setFont(new Font("Arial", Font.BOLD, 18));
		btnThemSP.setBorder(null);
		btnThemSP.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		btnThemSP.setBounds(142, 236, 229, 48);
		pnlThemSP.add(btnThemSP);
		
		JButton btnHuyCN1 = new JButton("Hủy");
		btnHuyCN1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(Color.WHITE);
				pnlThemSP.setVisible(false);
			}
		});
		btnHuyCN1.setFocusable(false);
		btnHuyCN1.setForeground(Color.WHITE);
		btnHuyCN1.setFont(new Font("Arial", Font.BOLD, 18));
		btnHuyCN1.setBorder(null);
		btnHuyCN1.setBackground(new Color(AppConstants.MAU_XAM));
		btnHuyCN1.setBounds(10, 236, 122, 48);
		pnlThemSP.add(btnHuyCN1);
		
		cboDVTThem = new JComboBox();
		cboDVTThem.setModel(new DefaultComboBoxModel(new String[] {AppConstants.EMPTY, AppConstants.CAY, AppConstants.CHUC, AppConstants.CAI, AppConstants.QUYEN, AppConstants.HOP}));
		cboDVTThem.setFont(new Font("Arial", Font.PLAIN, 16));
		cboDVTThem.setBounds(142, 101, 229, 35);
		pnlThemSP.add(cboDVTThem);
		
		cboNuocSXThem = new JComboBox();
		cboNuocSXThem.setEditable(true);
		cboNuocSXThem.setModel(new DefaultComboBoxModel(new String[] {AppConstants.EMPTY}));
		cboNuocSXThem.setFont(new Font("Arial", Font.PLAIN, 16));
		cboNuocSXThem.setBounds(142, 147, 229, 35);
		pnlThemSP.add(cboNuocSXThem);
		
		txtTenSPThem = new JTextField();
		txtTenSPThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtTenSPThem.setBounds(142, 58, 229, 34);
		pnlThemSP.add(txtTenSPThem);
		txtTenSPThem.setColumns(10);
		
		JLabel lblNewLabel_1_1_11_11 = new JLabel("Giá:");
		lblNewLabel_1_1_11_11.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_11_11.setBounds(10, 194, 122, 31);
		pnlThemSP.add(lblNewLabel_1_1_11_11);
		
		txtGiaThem = new JTextField();
		txtGiaThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtGiaThem.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGiaThem.setColumns(10);
		txtGiaThem.setBounds(142, 191, 177, 34);
		pnlThemSP.add(txtGiaThem);
		
		JLabel lblNewLabel_1_1_11_21 = new JLabel("Mã SP:");
		lblNewLabel_1_1_11_21.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_11_21.setBounds(10, 11, 122, 31);
		pnlThemSP.add(lblNewLabel_1_1_11_21);
		
		txtMaSPThem = new JTextField();
		txtMaSPThem.setFont(new Font("Arial", Font.PLAIN, 18));
		txtMaSPThem.setColumns(10);
		txtMaSPThem.setBounds(142, 11, 229, 34);
		pnlThemSP.add(txtMaSPThem);
		
		JLabel lblCurrency = new JLabel("VNĐ");
		lblCurrency.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrency.setForeground(Color.BLACK);
		lblCurrency.setFont(new Font("Arial", Font.BOLD, 18));
		lblCurrency.setBounds(324, 193, 47, 30);
		pnlThemSP.add(lblCurrency);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN SẢN PHẨM");
		lblNewLabel.setIcon(new ImageIcon(PnlSanPham.class.getResource("/hinhAnh/IconSanPhamNhoDen.png")));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 381, 59);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã SP:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 92, 85, 30);
		panel_1.add(lblNewLabel_1);
		
		lblMaSP = new JLabel("...");
		lblMaSP.setForeground(Color.BLACK);
		lblMaSP.setFont(new Font("Arial", Font.BOLD, 28));
		lblMaSP.setBounds(122, 81, 157, 44);
		panel_1.add(lblMaSP);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên SP:");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 136, 85, 30);
		panel_1.add(lblNewLabel_1_1);
		
		lblTenSP = new JLabel("...");
		lblTenSP.setForeground(Color.BLACK);
		lblTenSP.setFont(new Font("Arial", Font.BOLD, 18));
		lblTenSP.setBounds(122, 136, 269, 30);
		panel_1.add(lblTenSP);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Đơn vị tính:");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 177, 102, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		lblDVT = new JLabel("...");
		lblDVT.setForeground(Color.BLACK);
		lblDVT.setFont(new Font("Arial", Font.BOLD, 18));
		lblDVT.setBounds(122, 177, 269, 30);
		panel_1.add(lblDVT);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nước SX:");
		lblNewLabel_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(10, 218, 106, 30);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		lblNuocSX = new JLabel("...");
		lblNuocSX.setForeground(Color.BLACK);
		lblNuocSX.setFont(new Font("Arial", Font.BOLD, 18));
		lblNuocSX.setBounds(122, 218, 269, 30);
		panel_1.add(lblNuocSX);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Giá:");
		lblNewLabel_1_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(10, 273, 106, 30);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		lblGia = new JLabel("...");
		lblGia.setForeground(new Color(AppConstants.MAU_TIM_DAM));
		lblGia.setFont(new Font("Arial", Font.BOLD, 24));
		lblGia.setBounds(122, 271, 269, 30);
		panel_1.add(lblGia);
		
		btnHienCN = new JButton("   Cập nhật thông tin    ");
		btnHienCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (spHienTai==null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn sản phẩm cần cập nhật");
				}else {
					btnHienCN.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
					btnHienThem.setBackground(Color.WHITE);
					pnlDoiTTSP.setVisible(true);
					pnlThemSP.setVisible(false);
					
					//load data
					txtTenSPCN.setText(spHienTai.getTenSP());
					cboDVTCN.setSelectedItem(spHienTai.getDonViTinh());
					cboNuocSXCN.setSelectedItem(spHienTai.getNuocSX());
					txtGiaCN.setText(String.valueOf(spHienTai.getGiaBan()));
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
		btnHienCN.setBounds(10, 332, 381, 49);
		panel_1.add(btnHienCN);
		
		JButton btnHienCN_1 = new JButton(" Xóa");
		btnHienCN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (spHienTai == null) {
					AppHelper.thongBao(getRootPane(), "Vui lòng chọn sản phẩm cần xóa!");
					return;
				}
				try {
					if (AppHelper.canhBaoXacNhan(getRootPane(), "Xóa sản phẩm có mã \""+spHienTai.getMaSP()+"\"?") 
							== JOptionPane.OK_OPTION) {
					
						//update database
						spDB.xoaMem(spHienTai.getMaSP());
						
						//update panel
						spHienTai = null;
						listSP = new ArrayList<SanPham>();
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
		
		btnHienThem = new JButton("   Thêm sản phẩm mới");
		btnHienThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHienThem.setBackground(new Color(AppConstants.MAU_TIM_NHAT_2));
				btnHienCN.setBackground(Color.WHITE);
				cboNuocSXThem.setSelectedItem(AppConstants.EMPTY);
				pnlDoiTTSP.setVisible(false);
				pnlThemSP.setVisible(true);
			
			}
		});
		btnHienThem.setIcon(new ImageIcon(PnlHoaDon.class.getResource("/hinhAnh/IconThemDen.png")));
		btnHienThem.setForeground(Color.BLACK);
		btnHienThem.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienThem.setFocusable(false);
		btnHienThem.setBorder(null);
		btnHienThem.setBackground(Color.WHITE);
		btnHienThem.setAlignmentX(0.5f);
		btnHienThem.setBounds(10, 392, 381, 49);
		panel_1.add(btnHienThem);
		
		loadData();

	}
	
	private void loadData() {
		//cbo nuoc san xuat
		Set<String> set = new HashSet<>();
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();
		dcm.addElement(AppConstants.EMPTY);
		for (SanPham sp : spDB.tatCa()) {
			set.add(sp.getNuocSX());
		}
		set.forEach(s->dcm.addElement(s));
		cboNuocSXCN.setModel(dcm);
		cboNuocSXThem.setModel(dcm);
	}
	
	private void hienThi() {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("MÃ SP");
		dtm.addColumn("TÊN SP");
		dtm.addColumn("ĐVT");
		dtm.addColumn("NƯỚC SX");
		dtm.addColumn("GIÁ");
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		if (listSP != null) {
			int i = 1;
			for (SanPham item : listSP) {
				Object[] data = {i++,item.getMaSP(),item.getTenSP(),item.getDonViTinh(),item.getNuocSX(),nf.format(item.getGiaBan())+" VNĐ"};
				dtm.addRow(data);
			}
		}
		tblHienThi.setModel(dtm);
		tblHienThi.getColumnModel().getColumn(0).setPreferredWidth(60);
		tblHienThi.getColumnModel().getColumn(0).setMinWidth(60);
		tblHienThi.getColumnModel().getColumn(0).setMaxWidth(60);
		tblHienThi.setRowHeight(40);
		tblHienThi.setFont(new Font("Arial", Font.PLAIN, 18));
		tblHienThi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));;
		
		thongKe();
	}
	
	private void thongKe() {
		if (listSP == null || listSP.isEmpty()) {
			lblSoSPTimThay.setText("0");
			lblSPCaoNhat.setText(AppConstants.NO_INFO);
			lblSPThapNhat.setText(AppConstants.NO_INFO);
			lblGiaTB.setText(AppConstants.NO_INFO);
			lblTongGia.setText(AppConstants.NO_INFO);
			return;
		}
		
		lblSoSPTimThay.setText(String.valueOf(listSP.size()));
		// hoa don max - min - sum
		SanPham max = listSP.get(0);
		SanPham min = listSP.get(0);
		double sum = 0;
		
		for (SanPham item: listSP) {
			if (item.getGiaBan() > max.getGiaBan()) max = item;
			if (item.getGiaBan() < min.getGiaBan()) min = item;
			sum += item.getGiaBan();
		}
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		
		lblSPCaoNhat.setText(max.getMaSP()+" - "+nf.format(max.getGiaBan())+" VNĐ");
		lblSPThapNhat.setText(min.getMaSP()+" - "+nf.format(min.getGiaBan())+" VNĐ");
		lblGiaTB.setText(nf.format(Math.round(sum/listSP.size()))+" "+ "VNĐ");
		lblTongGia.setText(nf.format(sum)+"VNĐ");
	}
	
	private void resetInfo() {
		if (spHienTai == null) {
			lblMaSP.setText(AppConstants.NO_INFO);
			lblTenSP.setText(AppConstants.NO_INFO);
			lblDVT.setText(AppConstants.NO_INFO);
			lblNuocSX.setText(AppConstants.NO_INFO);
			lblGia.setText(AppConstants.NO_INFO);
		}else {
			Locale lc = new Locale("vi","VN");
			NumberFormat nf = NumberFormat.getInstance(lc);
			lblMaSP.setText(spHienTai.getMaSP());
			lblTenSP.setText(spHienTai.getTenSP());
			lblDVT.setText(spHienTai.getDonViTinh());
			lblNuocSX.setText(spHienTai.getNuocSX());
			lblGia.setText(nf.format(spHienTai.getGiaBan())+" VNĐ");
		}
	}
	
	protected void hienThiSanPham(SanPham sp) {
		spHienTai = sp;
		resetInfo();
		listSP = new ArrayList<SanPham>();
		listSP.add(sp);
		hienThi();
		tblHienThi.setRowSelectionInterval(0, 0);
	}
}
