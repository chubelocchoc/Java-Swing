package giaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DanhMucBean;
import model.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import tienIch.AppConstants;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FrmMain extends JFrame {

	private JPanel contentPane;
	private JPanel jpnView;
	private JPanel pnlTrangChu;
	private JLabel lblTrangChu;
	private JPanel pnlHoaDon;
	private JLabel lblHoaDon;
	private JPanel pnlCTHD;
	private JLabel lblCTHD;
	private JPanel pnlSanPham;
	private JLabel lblSanPham;
	private JPanel pnlKhachHang;
	private JLabel lblKhachHang;
	private JLabel lblDangXuat;
	private JPanel pnlDangXuat;
	private JPanel pnlNhanVien;
	private JLabel lblNhanVien;
	private JLabel lblThoat;
	private JPanel pnlThoat;
	private JPanel pnlMenu;
	private ChuyenPanelTheoDanhMuc controller;
	private NhanVien user;
	
	public ChuyenPanelTheoDanhMuc getController() {
		return controller;
	}
	
	public JPanel getJpnView() {
		return jpnView;
	}
	
	public void setUser(NhanVien user) {
		this.user = user;
	}
	
	public NhanVien getUser() {
		return user;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
////					FrmMain frame = new FrmMain();
////					System.out.println("DEBUG");
////					frame.setVisible(true);
////					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmMain(NhanVien user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/hinhAnh/LogoApp.jpg")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 860);
		contentPane = new JPanel();
		contentPane.setForeground(Color.decode("0"));
		contentPane.setBackground(Color.decode("11384253"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlMenu = new JPanel();
		pnlMenu.setBounds(0, 0, 283, 814);
		pnlMenu.setBackground(new Color(AppConstants.MAU_XAM));
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ BÁN HÀNG");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 250, 61);
		pnlMenu.add(lblNewLabel);
		
		pnlTrangChu = new JPanel();
		
		pnlTrangChu.setBounds(10, 109, 250, 80);
		pnlMenu.add(pnlTrangChu);
		pnlTrangChu.setLayout(new BorderLayout(0, 0));
		
		lblTrangChu = new JLabel("   Trang chủ              ");
		lblTrangChu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrangChu.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTrangChu.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconTrangChu.png")));
		lblTrangChu.setForeground(Color.WHITE);
		lblTrangChu.setBorder(UIManager.getBorder("Button.border"));
		lblTrangChu.setFont(new Font("Arial", Font.BOLD, 18));
		pnlTrangChu.add(lblTrangChu);
		
		pnlHoaDon = new JPanel();
		pnlHoaDon.setBounds(10, 200, 250, 80);
		pnlMenu.add(pnlHoaDon);
		pnlHoaDon.setLayout(new BorderLayout(0, 0));
		
		lblHoaDon = new JLabel("   Quản lý hóa đơn     ");
		lblHoaDon.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconHoaDon.png")));
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setFont(new Font("Arial", Font.PLAIN, 18));
		lblHoaDon.setBorder(UIManager.getBorder("Button.border"));
		pnlHoaDon.add(lblHoaDon, BorderLayout.CENTER);
		
		pnlCTHD = new JPanel();
		pnlCTHD.setBounds(10, 291, 250, 80);
		pnlMenu.add(pnlCTHD);
		pnlCTHD.setLayout(new BorderLayout(0, 0));
		
		lblCTHD = new JLabel("   Chi tiết hóa đơn      ");
		lblCTHD.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconCTHD.png")));
		lblCTHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCTHD.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCTHD.setBorder(UIManager.getBorder("Button.border"));
		pnlCTHD.add(lblCTHD, BorderLayout.CENTER);
		
		pnlSanPham = new JPanel();
		pnlSanPham.setBounds(10, 382, 250, 80);
		pnlMenu.add(pnlSanPham);
		pnlSanPham.setLayout(new BorderLayout(0, 0));
		
		lblSanPham = new JLabel("   Quản lý sản phẩm   ");
		lblSanPham.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconSanPham.png")));
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPham.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSanPham.setBorder(UIManager.getBorder("Button.border"));
		pnlSanPham.add(lblSanPham, BorderLayout.CENTER);
		
		pnlKhachHang = new JPanel();
		pnlKhachHang.setBounds(10, 473, 250, 80);
		pnlMenu.add(pnlKhachHang);
		pnlKhachHang.setLayout(new BorderLayout(0, 0));
		
		lblKhachHang = new JLabel("   Quản lý khách hàng");
		lblKhachHang.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconKhachHang.png")));
		lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhachHang.setFont(new Font("Arial", Font.PLAIN, 18));
		lblKhachHang.setBorder(UIManager.getBorder("Button.border"));
		pnlKhachHang.add(lblKhachHang, BorderLayout.CENTER);
		
		pnlDangXuat = new JPanel();
		pnlDangXuat.setBackground(new Color(AppConstants.MAU_XAM));
		pnlDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rep = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn đăng xuất khỏi tài khoản này?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
				if (rep == JOptionPane.OK_OPTION) {
					setVisible(false);
					FrmDangNhap frmDangNhap = new FrmDangNhap();
					frmDangNhap.setVisible(true);				
				}
			}
		});
		pnlDangXuat.setBounds(10, 713, 115, 80);
		pnlMenu.add(pnlDangXuat);
		pnlDangXuat.setLayout(new BorderLayout(0, 0));
		
		lblDangXuat = new JLabel("Đăng xuất");
		lblDangXuat.setForeground(Color.WHITE);
		lblDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				FrmDangNhap frmDangNhap = new FrmDangNhap();
				frmDangNhap.setVisible(true);
			}
		});
		lblDangXuat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangXuat.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDangXuat.setBorder(UIManager.getBorder("Button.border"));
		pnlDangXuat.add(lblDangXuat, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconDangXuat.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDangXuat.add(lblNewLabel_1, BorderLayout.CENTER);
		
		pnlNhanVien = new JPanel();
		pnlNhanVien.setBounds(10, 564, 250, 80);
		pnlMenu.add(pnlNhanVien);
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		
		lblNhanVien = new JLabel("   Quản lý nhân viên    ");
		lblNhanVien.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconNhanVien.png")));
		lblNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanVien.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNhanVien.setBorder(UIManager.getBorder("Button.border"));
		pnlNhanVien.add(lblNhanVien, BorderLayout.CENTER);
		
		jpnView = new JPanel();
		jpnView.setBounds(284, 0, 1144, 814);
		contentPane.add(jpnView);
		
		pnlThoat = new JPanel();
		pnlThoat.setBackground(new Color(AppConstants.MAU_XAM));
		pnlThoat.setBounds(145, 713, 115, 80);
		pnlMenu.add(pnlThoat);
		pnlThoat.setLayout(new BorderLayout(0, 0));
		pnlThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rep = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn ĐÓNG chương trình?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
				if (rep == JOptionPane.OK_OPTION) {
					setVisible(false);	
                                        System.exit(0);
				}
			}
		});
		
		lblThoat = new JLabel("Thoát");
		lblThoat.setForeground(Color.WHITE);
		lblThoat.setHorizontalAlignment(SwingConstants.CENTER);
		lblThoat.setFont(new Font("Arial", Font.PLAIN, 18));
		lblThoat.setBorder(UIManager.getBorder("Button.border"));
		pnlThoat.add(lblThoat, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmMain.class.getResource("/hinhAnh/IconDong.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		pnlThoat.add(lblNewLabel_2, BorderLayout.CENTER);
		
		this.user =user;
		initFrame();
	}
	
	private void initFrame() {
		changeBackgroundDanhMuc(8818326);
		changeFontColorDanhMuc(16777215);

		controller = new ChuyenPanelTheoDanhMuc(this);
		controller.setView(pnlTrangChu, lblTrangChu);
		
		List<DanhMucBean> listItem = new ArrayList<DanhMucBean>();
		listItem.add(new DanhMucBean(AppConstants.TRANG_CHU, pnlTrangChu, lblTrangChu));
		listItem.add(new DanhMucBean(AppConstants.HOA_DON, pnlHoaDon, lblHoaDon));
		listItem.add(new DanhMucBean(AppConstants.CTHD, pnlCTHD, lblCTHD));
		listItem.add(new DanhMucBean(AppConstants.SAN_PHAM, pnlSanPham, lblSanPham));
		listItem.add(new DanhMucBean(AppConstants.KHACH_HANG, pnlKhachHang, lblKhachHang));
		listItem.add(new DanhMucBean(AppConstants.NHAN_VIEN, pnlNhanVien, lblNhanVien));
		controller.setEvent(listItem);
		
		if (!user.getVaiTro().equals("Admin")) {
			pnlNhanVien.setVisible(false);
		}
	}
	
	private void changeBackgroundDanhMuc(int color) {
		pnlTrangChu.setBackground(new Color(color));
		pnlCTHD.setBackground(new Color(color));
		pnlHoaDon.setBackground(new Color(color));
		pnlKhachHang.setBackground(new Color(color));
		pnlNhanVien.setBackground(new Color(color));
		pnlSanPham.setBackground(new Color(color));
	}
	
	private void changeFontColorDanhMuc(int color) {
		lblTrangChu.setForeground(new Color(color));
		lblCTHD.setForeground(new Color(color));
		lblHoaDon.setForeground(new Color(color));
		lblKhachHang.setForeground(new Color(color));
		lblNhanVien.setForeground(new Color(color));
		lblSanPham.setForeground(new Color(color));
	}
}
