package model;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMucBean {
	private String kind;
	private JPanel jpn;
	private JLabel jlb;
	
	public DanhMucBean(String kind, JPanel jpn, JLabel jlb) {
		super();
		this.kind = kind;
		this.jpn = jpn;
		this.jlb = jlb;
	}
	public DanhMucBean() {
		super();
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public JPanel getJpn() {
		return jpn;
	}
	public void setJpn(JPanel jpn) {
		this.jpn = jpn;
	}
	public JLabel getJlb() {
		return jlb;
	}
	public void setJlb(JLabel jlb) {
		this.jlb = jlb;
	}	
}
