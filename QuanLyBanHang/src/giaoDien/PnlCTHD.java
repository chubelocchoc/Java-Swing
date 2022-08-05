package giaoDien;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;

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
import xuLyDuLieu.SanPhamDB;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlCTHD extends JPanel {
	private JButton btnHienCN;
	private JTable tblCHTD;
	private JTextField txtSoLuong;
	private JComboBox<String> cboChonHD;
	private JLabel lblMaHD;
	private JLabel lblNgHD;
	private JLabel lblTenKH;
	private JLabel lblTenNV;
	private JLabel lblMaHD2;
	
	//data
	private HoaDon hdHienTai = null;
	private List<ChiTietHoaDon> listCTHD = null;
	private HoaDonDB hdDB = new HoaDonDB();
	private KhachHangDB khDB = new KhachHangDB();
	private NhanVienDB nvDB = new NhanVienDB();
	private SanPhamDB spDB = new SanPhamDB();
	private ChiTietHoaDonDB cthdDB = new ChiTietHoaDonDB();
	private JLabel lblTongTriGia;
	private JLabel lblThue;
	private JLabel lblThanhTien;
	private JComboBox<String> cboSanPham;
	private JLabel lblTriGia;
	private JLabel lblDonGia;
	private JButton btnXoa;
	private JButton btnThem;
	private JPanel pnlEditCTHD;
	
	public void setHdHienTai(HoaDon hdHienTai) {
		this.hdHienTai = hdHienTai;
	}

	/**
	 * Create the panel.
	 */
	public PnlCTHD(HoaDon hoaDon) {
		setLayout(null);
		this.hdHienTai = hoaDon;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222, 226, 230));
		panel.setBounds(0, 0, 743, 814);
		add(panel);
		
		cboChonHD = new JComboBox();
		cboChonHD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selected = (String) cboChonHD.getSelectedItem();
				if (!selected.equals(AppConstants.CHON_HOA_DON)) {
					hdHienTai = hdDB.timTheoSoHD(Integer.parseInt(selected.substring(0, 4)));
					listCTHD = cthdDB.timTheoSoHD(hdHienTai.getSoHoaDon());
					cboSanPham.setSelectedItem(AppConstants.EMPTY);
					lblDonGia.setText(AppConstants.NO_INFO);
					txtSoLuong.setText("1");
					lblTriGia.setText(AppConstants.NO_INFO);
					pnlEditCTHD.setVisible(true);
				}else {
					listCTHD = null;
					hdHienTai = null;
					pnlEditCTHD.setVisible(false);
				}
				loadInfoHoaDon();
				hienThi(false);
			}
		});
		cboChonHD.setModel(new DefaultComboBoxModel(new String[] {"HD01", "HD02", "HD03"}));
		cboChonHD.setFont(new Font("Arial", Font.PLAIN, 20));
		cboChonHD.setBounds(264, 11, 469, 43);
		panel.add(cboChonHD);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 723, 488);
		panel.add(scrollPane);
		
		tblCHTD = new JTable();
		tblCHTD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblCHTD.getSelectedRow();
				cboSanPham.setSelectedItem(tblCHTD.getModel().getValueAt(row, 1).toString());
				txtSoLuong.setText(tblCHTD.getModel().getValueAt(row, 4).toString());
				lblDonGia.setText(tblCHTD.getModel().getValueAt(row, 3).toString());
				lblTriGia.setText(tblCHTD.getModel().getValueAt(row, 5).toString());
				btnXoa.setVisible(true);
				btnThem.setVisible(true);
				btnThem.setText("Cập nhật");
				btnThem.setBackground(new Color(AppConstants.VIOLET));
			}
		});
		scrollPane.setViewportView(tblCHTD);
		
		JLabel lblNewLabel_2_2 = new JLabel("Mã HD:");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_2_2.setBounds(20, 65, 107, 33);
		panel.add(lblNewLabel_2_2);
		
		lblMaHD = new JLabel("...");
		lblMaHD.setFont(new Font("Arial", Font.BOLD, 22));
		lblMaHD.setBounds(137, 65, 144, 33);
		panel.add(lblMaHD);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Tên KH:");
		lblNewLabel_2_2_2.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_2_2_2.setBounds(309, 66, 94, 33);
		panel.add(lblNewLabel_2_2_2);
		
		lblTenNV = new JLabel("...");
		lblTenNV.setFont(new Font("Arial", Font.BOLD, 22));
		lblTenNV.setBounds(413, 104, 303, 33);
		panel.add(lblTenNV);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("Ngày tạo:");
		lblNewLabel_2_2_2_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_2_2_2_1.setBounds(20, 103, 106, 33);
		panel.add(lblNewLabel_2_2_2_1);
		
		lblNgHD = new JLabel("...");
		lblNgHD.setFont(new Font("Arial", Font.BOLD, 22));
		lblNgHD.setBounds(137, 103, 144, 33);
		panel.add(lblNgHD);
		
		JLabel lblNewLabel_2_2_2_2 = new JLabel("Tên NV:");
		lblNewLabel_2_2_2_2.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_2_2_2_2.setBounds(309, 104, 94, 33);
		panel.add(lblNewLabel_2_2_2_2);
		
		lblTenKH = new JLabel("...");
		lblTenKH.setFont(new Font("Arial", Font.BOLD, 22));
		lblTenKH.setBounds(413, 65, 303, 33);
		panel.add(lblTenKH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 647, 723, 156);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("Tổng trị giá:");
		lblNewLabel_2_2_3.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_2_2_3.setBounds(180, 11, 120, 33);
		panel_2.add(lblNewLabel_2_2_3);
		
		lblTongTriGia = new JLabel("...");
		lblTongTriGia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongTriGia.setFont(new Font("Arial", Font.BOLD, 22));
		lblTongTriGia.setBounds(379, 11, 334, 33);
		panel_2.add(lblTongTriGia);
		
		JLabel lblNewLabel_2_2_3_1 = new JLabel("Thuế GTGT (10%)");
		lblNewLabel_2_2_3_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_2_2_3_1.setBounds(180, 55, 189, 33);
		panel_2.add(lblNewLabel_2_2_3_1);
		
		lblThue = new JLabel("...");
		lblThue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThue.setFont(new Font("Arial", Font.BOLD, 22));
		lblThue.setBounds(379, 55, 334, 33);
		panel_2.add(lblThue);
		
		JLabel lblNewLabel_2_2_3_2 = new JLabel("Thành tiền:");
		lblNewLabel_2_2_3_2.setForeground(new Color(AppConstants.MAU_DO));
		lblNewLabel_2_2_3_2.setFont(new Font("Arial", Font.PLAIN, 28));
		lblNewLabel_2_2_3_2.setBounds(180, 109, 189, 33);
		panel_2.add(lblNewLabel_2_2_3_2);
		
		lblThanhTien = new JLabel("...");
		lblThanhTien.setForeground(new Color(AppConstants.MAU_DO));
		lblThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThanhTien.setFont(new Font("Arial", Font.BOLD, 32));
		lblThanhTien.setBounds(379, 99, 334, 50);
		panel_2.add(lblThanhTien);
		
		JLabel lblChnHan = new JLabel("CHỌN HÓA ĐƠN:");
		lblChnHan.setForeground(Color.BLACK);
		lblChnHan.setFont(new Font("Arial", Font.BOLD, 24));
		lblChnHan.setBounds(20, 11, 234, 43);
		panel.add(lblChnHan);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		panel_1.setBounds(743, 0, 401, 814);
		add(panel_1);
		panel_1.setLayout(null);
		
		btnHienCN = new JButton("   In hóa đơn    ");
		btnHienCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppHelper.thongBao(getRootPane(), "Xin lỗi... Tính năng này đang phát triển!");
			}
		});
		btnHienCN.setIcon(new ImageIcon(PnlCTHD.class.getResource("/hinhAnh/IconIn.png")));
		btnHienCN.setForeground(Color.BLACK);
		btnHienCN.setFont(new Font("Arial", Font.BOLD, 18));
		btnHienCN.setFocusable(false);
		btnHienCN.setBorder(null);
		btnHienCN.setBackground(Color.WHITE);
		btnHienCN.setAlignmentX(0.5f);
		btnHienCN.setBounds(10, 738, 381, 65);
		panel_1.add(btnHienCN);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(116, 143, 252));
		panel_2_1.setBounds(10, 11, 381, 128);
		panel_1.add(panel_2_1);
		
		JLabel lblNewLabel = new JLabel("Mã hóa đơn");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(157, 37, 191, 35);
		panel_2_1.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(PnlCTHD.class.getResource("/hinhAnh/IconCTHDLonTrang.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 11, 125, 106);
		panel_2_1.add(lblNewLabel_4);
		
		lblMaHD2 = new JLabel("...");
		lblMaHD2.setForeground(Color.WHITE);
		lblMaHD2.setFont(new Font("Arial", Font.BOLD, 28));
		lblMaHD2.setBounds(157, 66, 191, 51);
		panel_2_1.add(lblMaHD2);
		
		pnlEditCTHD = new JPanel();
		pnlEditCTHD.setVisible(false);
		pnlEditCTHD.setBounds(10, 150, 381, 384);
		panel_1.add(pnlEditCTHD);
		pnlEditCTHD.setLayout(null);
		
		JLabel lblChiTitHa = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblChiTitHa.setBounds(10, 11, 358, 51);
		pnlEditCTHD.add(lblChiTitHa);
		lblChiTitHa.setForeground(Color.BLACK);
		lblChiTitHa.setFont(new Font("Arial", Font.BOLD, 24));
		
		JLabel lblSnPhm = new JLabel("Sản phẩm:");
		lblSnPhm.setBounds(10, 58, 107, 43);
		pnlEditCTHD.add(lblSnPhm);
		lblSnPhm.setForeground(Color.BLACK);
		lblSnPhm.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblnGi = new JLabel("Đơn giá:");
		lblnGi.setBounds(10, 112, 96, 43);
		pnlEditCTHD.add(lblnGi);
		lblnGi.setForeground(Color.BLACK);
		lblnGi.setFont(new Font("Arial", Font.PLAIN, 20));
		
		lblDonGia = new JLabel("...");
		lblDonGia.setBounds(116, 112, 252, 43);
		pnlEditCTHD.add(lblDonGia);
		lblDonGia.setForeground(Color.BLACK);
		lblDonGia.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblSnPhm_1 = new JLabel("Số lượng:");
		lblSnPhm_1.setBounds(10, 163, 96, 43);
		pnlEditCTHD.add(lblSnPhm_1);
		lblSnPhm_1.setForeground(Color.BLACK);
		lblSnPhm_1.setFont(new Font("Arial", Font.PLAIN, 20));
		
		cboSanPham = new JComboBox();
		cboSanPham.setBounds(117, 58, 251, 43);
		pnlEditCTHD.add(cboSanPham);
		cboSanPham.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (listCTHD == null) {
//					AppHelper.thongBao(getRootPane(), "Vui lòng chọn hóa đơn hiển thị!");
				}else {
				
					String sl = (String) cboSanPham.getSelectedItem();
					if (sl.equals(AppConstants.EMPTY)) {
						lblDonGia.setText(AppConstants.NO_INFO);
						lblTriGia.setText(AppConstants.NO_INFO);
						btnThem.setVisible(false);
						btnXoa.setVisible(false);
					}else {
						Locale lc = new Locale("vi","VN");
						NumberFormat nf = NumberFormat.getInstance(lc);
						SanPham sp = spDB.timTheoMaSP(sl.substring(0,4));
						lblDonGia.setText(nf.format(sp.getGiaBan())+" VNĐ");
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						lblTriGia.setText(nf.format(sp.getGiaBan()*soLuong)+" VNĐ");
	
						//change button
						for(ChiTietHoaDon cthd: listCTHD) {
							if (cthd.getMaSP().equals(sl.substring(0,4))) {
								btnXoa.setVisible(true);
								btnThem.setVisible(true);
								btnThem.setText("Cập nhật");
								btnThem.setBackground(new Color(AppConstants.VIOLET));
								txtSoLuong.setText(String.valueOf(cthd.getSoLuong()));
								return;
							}
						}
						txtSoLuong.setText("1");
						btnXoa.setVisible(false);
						btnThem.setText("Thêm mới");
						btnThem.setBackground(new Color(AppConstants.MAU_TIM_DAM));
						btnThem.setVisible(true);
					}
				}
			}
		});
		cboSanPham.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton btnGiam = new JButton("");
		btnGiam.setBounds(116, 163, 78, 43);
		pnlEditCTHD.add(btnGiam);
		btnGiam.setIcon(new ImageIcon(PnlCTHD.class.getResource("/hinhAnh/IconNhoHon.png")));
		btnGiam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sl = (String) cboSanPham.getSelectedItem();
				if (!sl.equals(AppConstants.EMPTY)) {
					try {
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						if (soLuong > 1) {
							txtSoLuong.setText(String.valueOf(soLuong-1));
							
							Locale lc = new Locale("vi","VN");
							NumberFormat nf = NumberFormat.getInstance(lc);
							SanPham sp = spDB.timTheoMaSP(sl.substring(0,4));
			
							lblTriGia.setText(nf.format(sp.getGiaBan()*(soLuong-1))+" VNĐ");
						}
					} catch (Exception e2) {
						AppHelper.thongBaoLoi(getRootPane(), "Số vừa nhập không hợp lệ!");
					}
				}else {
					AppHelper.thongBao(getRootPane(), "Bạn chưa chọn sản phẩm hiển thị!");
				}
			}
		});
		btnGiam.setForeground(Color.BLACK);
		btnGiam.setFont(new Font("Arial", Font.BOLD, 18));
		btnGiam.setFocusable(false);
		btnGiam.setBorder(null);
		btnGiam.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		btnGiam.setAlignmentX(0.5f);
		
		JButton btnTang = new JButton("");
		btnTang.setBounds(290, 163, 78, 43);
		pnlEditCTHD.add(btnTang);
		btnTang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sl = (String) cboSanPham.getSelectedItem();
				if (!sl.equals(AppConstants.EMPTY)) {
					try {
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						txtSoLuong.setText(String.valueOf(soLuong+1));
						
						Locale lc = new Locale("vi","VN");
						NumberFormat nf = NumberFormat.getInstance(lc);
						SanPham sp = spDB.timTheoMaSP(sl.substring(0,4));
		
						lblTriGia.setText(nf.format(sp.getGiaBan()*(soLuong+1))+" VNĐ");
					} catch (Exception e2) {
						AppHelper.thongBaoLoi(getRootPane(), "Số vừa nhập không hợp lệ!");
					}
				}else {
					AppHelper.thongBao(getRootPane(), "Bạn chưa chọn sản phẩm hiển thị!");
				}
			}
		});
		btnTang.setIcon(new ImageIcon(PnlCTHD.class.getResource("/hinhAnh/IconLonHon.png")));
		btnTang.setForeground(Color.BLACK);
		btnTang.setFont(new Font("Arial", Font.BOLD, 18));
		btnTang.setFocusable(false);
		btnTang.setBorder(null);
		btnTang.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		btnTang.setAlignmentX(0.5f);
		
		JLabel lblTmTnh = new JLabel("Trị giá:");
		lblTmTnh.setBounds(10, 217, 96, 43);
		pnlEditCTHD.add(lblTmTnh);
		lblTmTnh.setForeground(Color.BLACK);
		lblTmTnh.setFont(new Font("Arial", Font.PLAIN, 20));
		
		lblTriGia = new JLabel("...");
		lblTriGia.setBounds(116, 217, 252, 43);
		pnlEditCTHD.add(lblTriGia);
		lblTriGia.setForeground(Color.BLACK);
		lblTriGia.setFont(new Font("Arial", Font.BOLD, 20));
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sanPham =(String) cboSanPham.getSelectedItem();
					if (AppHelper.thongBaoXacNhan(getRootPane(), "Xóa mục "+sanPham+" khỏi hóa đơn "+hdHienTai.getSoHoaDon()+"?")
							==JOptionPane.OK_OPTION) {
						cthdDB.xoaCTHD(hdHienTai.getSoHoaDon(), sanPham.substring(0,4));
						hienThi(true);
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
				}
			}
		});
		btnXoa.setBounds(10, 294, 152, 65);
		pnlEditCTHD.add(btnXoa);
		btnXoa.setVisible(false);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Arial", Font.BOLD, 22));
		btnXoa.setFocusable(false);
		btnXoa.setBorder(null);
		btnXoa.setBackground(new Color(AppConstants.MAU_DO));
		btnXoa.setAlignmentX(0.5f);
		
		btnThem = new JButton(" Thêm mới");
		btnThem.setBounds(172, 294, 195, 65);
		pnlEditCTHD.add(btnThem);
		btnThem.setVisible(false);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int soLuong = Integer.parseInt(txtSoLuong.getText());
					if (soLuong <1) {
						AppHelper.thongBao(getRootPane(), "Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại!");
						return;
					}
					if (AppHelper.thongBaoXacNhan(getRootPane(),btnThem.getText()+" chi tiết cho hóa đơn "+hdHienTai.getSoHoaDon())
							== JOptionPane.OK_OPTION) {
						
						String chon = (String) cboSanPham.getSelectedItem();
						if (btnThem.getText().equals("Thêm mới")) {
							ChiTietHoaDon cthd = new ChiTietHoaDon(hdHienTai.getSoHoaDon(), chon.substring(0,4), soLuong);
							cthdDB.themCTHD(cthd);
							listCTHD.add(cthd);
							//chuyen nut
							btnXoa.setVisible(true);
							btnThem.setText("Cập nhật");
							btnThem.setBackground(new Color(AppConstants.VIOLET));
							
						}else {
							ChiTietHoaDon cthd = cthdDB.timTheoSoHDVaMaSP(hdHienTai.getSoHoaDon(), chon.substring(0,4));
							cthd.setSoLuong(soLuong);
							cthdDB.capNhatThongTin(cthd);
						}
						hienThi(true);
					}
				} catch (Exception e2) {
					AppHelper.thongBaoLoiQuaTrinhXuLy(getRootPane());
					e2.printStackTrace();
				}
			}
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial", Font.BOLD, 22));
		btnThem.setFocusable(false);
		btnThem.setBorder(null);
		btnThem.setBackground(new Color(AppConstants.MAU_TIM_DAM));
		btnThem.setAlignmentX(0.5f);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(203, 163, 77, 43);
		pnlEditCTHD.add(txtSoLuong);
		txtSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chon = (String) cboSanPham.getSelectedItem();
				if (!chon.equals(AppConstants.EMPTY)) {
					try {
						int soLuong = Integer.parseInt(txtSoLuong.getText());
						if (soLuong <1) {
							AppHelper.thongBao(getRootPane(), "Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại!");
						}else {
							Locale lc = new Locale("vi","VN");
							NumberFormat nf = NumberFormat.getInstance(lc);
							SanPham sp = spDB.timTheoMaSP(chon.substring(0,4));
							lblTriGia.setText(nf.format(sp.getGiaBan()*soLuong)+" VNĐ");
						}
					} catch (Exception e2) {
						AppHelper.thongBaoLoi(getRootPane(), "Số vừa nhập không hợp lệ!");
					}				
				}else {
					AppHelper.thongBao(getRootPane(), "Bạn chưa chọn sản phẩm hiển thị!");
				}
			}
		});
		txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 20));
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setText("1");
		txtSoLuong.setColumns(10);
		
		loadData();
	}
	
	private void loadData() {
		List<HoaDon> listAllHD = hdDB.tatCa();
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();
		dcm.addElement(AppConstants.CHON_HOA_DON);
		for(int i = listAllHD.size()-1;i>=0;i--) {
			KhachHang kh = khDB.timTheoMaKH(listAllHD.get(i).getMaKhachKhang());
			dcm.addElement(listAllHD.get(i).getSoHoaDon()+" - "+(kh==null?AppConstants.EMPTY:kh.getHoTen())
					+" - "+listAllHD.get(i).getNgayHoaDonToString());
		}
		cboChonHD.setModel(dcm);
		
		//load hoa don hien tai
		if (hdHienTai != null) {
			KhachHang kh = khDB.timTheoMaKH(hdHienTai.getMaKhachKhang());
			cboChonHD.setSelectedItem(hdHienTai.getSoHoaDon()+" - "+(kh==null?AppConstants.EMPTY:kh.getHoTen())
					+" - "+hdHienTai.getNgayHoaDonToString());
			loadInfoHoaDon();
			listCTHD = cthdDB.timTheoSoHD(hdHienTai.getSoHoaDon());
			hienThi(false);
		}
		
		//load combobox san pham
		DefaultComboBoxModel<String> dcmSP = new DefaultComboBoxModel<>();
		dcmSP.addElement(AppConstants.EMPTY);
		List<SanPham> listAllSP = spDB.tatCa();
		for(SanPham sp: listAllSP) {
			dcmSP.addElement(sp.getMaSP()+" - "+sp.getTenSP());
		}
		cboSanPham.setModel(dcmSP);
	}
	
	private void loadInfoHoaDon() {
		if (hdHienTai != null) {
			NhanVien nv = nvDB.timTheoMaNV(hdHienTai.getMaNhanVien());
			KhachHang kh = khDB.timTheoMaKH(hdHienTai.getMaKhachKhang());
			lblMaHD.setText(String.valueOf(hdHienTai.getSoHoaDon()));
			lblMaHD2.setText(String.valueOf(hdHienTai.getSoHoaDon()));
			lblNgHD.setText(hdHienTai.getNgayHoaDonToString());
			lblTenKH.setText((kh==null?AppConstants.EMPTY:kh.getHoTen()));
			lblTenNV.setText((nv==null?AppConstants.EMPTY:nv.getHoTen()));
		}else {
			lblMaHD.setText(AppConstants.NO_INFO);
			lblMaHD2.setText(AppConstants.NO_INFO);
			lblNgHD.setText(AppConstants.NO_INFO);
			lblTenKH.setText(AppConstants.NO_INFO);
			lblTenNV.setText(AppConstants.NO_INFO);
		}
	}
	
	private void hienThi(boolean needUpdateDb) {
		DefaultTableModel dtm = new DefaultTableModel(){
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
		dtm.addColumn("STT");
		dtm.addColumn("SẢN PHẨM");
		dtm.addColumn("ĐVT");
		dtm.addColumn("ĐƠN GIÁ");
		dtm.addColumn("SỐ LƯỢNG");
		dtm.addColumn("TRỊ GIÁ");
		Locale lc = new Locale("vi","VN");
		NumberFormat nf = NumberFormat.getInstance(lc);
		double tongTriGia = 0;
		if (hdHienTai != null) {
			int i = 1;
			listCTHD = new ArrayList<ChiTietHoaDon>();
			listCTHD.addAll(cthdDB.timTheoSoHD(hdHienTai.getSoHoaDon()));
			for (ChiTietHoaDon cthd : listCTHD) {
				SanPham sp = spDB.timTheoMaSP(cthd.getMaSP());
				String sanPham = sp.getMaSP()+" - "+sp.getTenSP();
				String giaBan = nf.format(sp.getGiaBan())+" VNĐ";
				String triGia = nf.format(sp.getGiaBan()*cthd.getSoLuong())+" VNĐ";
	
				Object[] data = {i++,sanPham,sp.getDonViTinh(),giaBan,cthd.getSoLuong(),triGia};
				dtm.addRow(data);
				tongTriGia += sp.getGiaBan()*cthd.getSoLuong();
			}
		}
		
		tblCHTD.setModel(dtm);
		tblCHTD.getColumnModel().getColumn(0).setPreferredWidth(60);
		tblCHTD.getColumnModel().getColumn(0).setMinWidth(60);
		tblCHTD.getColumnModel().getColumn(0).setMaxWidth(60);
		tblCHTD.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCHTD.getColumnModel().getColumn(2).setMinWidth(100);
		tblCHTD.getColumnModel().getColumn(2).setMaxWidth(100);
		tblCHTD.getColumnModel().getColumn(3).setPreferredWidth(120);
		tblCHTD.getColumnModel().getColumn(3).setMinWidth(120);
		tblCHTD.getColumnModel().getColumn(3).setMaxWidth(120);
		tblCHTD.getColumnModel().getColumn(4).setPreferredWidth(100);
		tblCHTD.getColumnModel().getColumn(4).setMinWidth(100);
		tblCHTD.getColumnModel().getColumn(4).setMaxWidth(100);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment( JLabel.RIGHT );
		tblCHTD.getColumnModel().getColumn(3).setCellRenderer(renderer);
		tblCHTD.getColumnModel().getColumn(4).setCellRenderer(renderer);
		tblCHTD.getColumnModel().getColumn(5).setCellRenderer(renderer);
		tblCHTD.setRowHeight(40);
		DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
		renderer2.setHorizontalAlignment( JLabel.CENTER );
		tblCHTD.getColumnModel().getColumn(2).setCellRenderer(renderer2);
		tblCHTD.getColumnModel().getColumn(4).setCellRenderer(renderer2);
		tblCHTD.setFont(new Font("Arial", Font.PLAIN, 18));
		tblCHTD.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));;
		
		// thanh Tien
		lblTongTriGia.setText(nf.format(tongTriGia)+" VNĐ");
		double thue = tongTriGia*AppConstants.THUE;
		lblThue.setText(nf.format(thue)+" VNĐ");
		lblThanhTien.setText(nf.format(tongTriGia+thue)+" VNĐ");
		
		// cap nhat tri gia cua hoa don
		if (needUpdateDb) {
			hdHienTai.setTriGia(tongTriGia);
			hdDB.capNhatThongTin(hdHienTai);
		}
	}
}
