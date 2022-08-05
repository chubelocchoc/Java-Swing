
--nhap du lieu
set dateformat dmy
GO

use QLBH
go

create table KHACHHANG (
	MAKH	char(4),
	HOTEN	nvarchar(40),
	DCHI	nvarchar(50),
	SODT	varchar(20),
	NGSINH	smalldatetime,
	NGDK	smalldatetime,
	DOANHSO money,
	LOAIKH	nvarchar(50),
	IS_DELETED varchar(5),
	primary key (MAKH)
)

create table NHANVIEN (
	MANV	char(4) primary key,
	HOTEN	nvarchar(40),
	SODT	varchar(20),
	NGVL	smalldatetime,
	MATKHAU varchar(50),
	VAITRO	nvarchar(10),
	IS_DELETED varchar(5)
)

create table SANPHAM (
	MASP	char(4) primary key,
	TENSP	nvarchar(40),
	DVT		nvarchar(20),
	NUOCSX	nvarchar(40),
	GIA		money,
	IS_DELETED varchar(5)
)

create table HOADON (
	SOHD	INT NOT NULL IDENTITY(1001,1) PRIMARY KEY,
	NGHD	smalldatetime,
	MAKH	char(4) foreign key references KHACHHANG(MAKH),
	MANV	char(4) foreign key references NHANVIEN(MANV),
	TRIGIA	money
)

create table CTHD (
	SOHD	int		foreign key references HOADON(SOHD),
	MASP	char(4) foreign key references SANPHAM(MASP),
	SL		int,
	constraint PK_CTHD primary key (SOHD,MASP)
)

--check database--

insert into nhanvien (MANV,HOTEN,SODT,NGVL,MATKHAU,VAITRO) values ('NV01', N'Nguyễn Như Nhựt', '0927456798', '13/4/2006', '123',N'Nhân Viên')
insert into nhanvien (MANV,HOTEN,SODT,NGVL,MATKHAU,VAITRO) values ('NV02', N'Lê Thị Phi Yến', '0927456546', '21/4/2006', '123',N'Nhân Viên')
insert into nhanvien (MANV,HOTEN,SODT,NGVL,MATKHAU,VAITRO) values ('NV03', N'Nguyễn Văn B', '0927789798', '27/4/2006', '123',N'Nhân Viên')
insert into nhanvien (MANV,HOTEN,SODT,NGVL,MATKHAU,VAITRO) values ('NV04', N'Ngô Thanh Tuấn', '0927684498', '24/6/2006', '123',N'Nhân Viên')
insert into nhanvien (MANV,HOTEN,SODT,NGVL,MATKHAU,VAITRO) values ('NV05', N'Nguyễn Thị Trúc Thanh', '0927452598', '20/7/2006', '123',N'Nhân Viên')
insert into nhanvien (MANV,HOTEN,SODT,NGVL,MATKHAU,VAITRO) values ('AD01', N'Admin', '0', '01/08/2022', 'AD00',N'Admin')

insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH01',N'Nguyễn Văn A',N'731 Trần Hưng Đạo, Q5, TpHCM','08823451','22/10/1960','13,060,000','22/07/2006',N'VIP')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH02',N'Trần Ngọc Hân',N'23/5 Nguyễn Trãi, Q5, TpHCM','0908256478','3/4/1974','280,000','30/07/2006',N'Thường xuyên')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH03',N'Trần Ngọc Linh',N'45 Nguyễn Cảnh Chân, Q1, TpHCM','0938776266','12/6/1980','3,860,000','05/08/2006',N'VIP')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH04',N'Trần Ngọc Long',N'50/34 Lê Đại Hành, Q10, TpHCM','0917325476','9/3/1965','250,000','02/10/2006',N'Vãng lai')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH05',N'Lê Nhật Minh',N'34 Trương Định, Q3, TpHCM','08246108','10/3/1950','21,000','28/10/2006',N'Vãng lai')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH06',N'Lê Hoài Thương',N'227 Nguyễn Văn Cừ, Q5, TpHCM','08631738','31/12/1981','915,000','24/11/2006',N'Vãng lai')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH07',N'Nguyễn Văn Tám',N'32/3 Trần Bình Trọng, Q5, TpHCM','0916783565','6/4/1971','12,500','01/12/2006',N'Vãng lai')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH08',N'Phan Thị Thanh',N'45/2 An Dương Vương, Q5, TpHCM','0938435756','10/1/1971','365,000','13/12/2006',N'Thường xuyên')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH09',N'Lê Hà Vinh',N'873 Lê Hồng Phong, Q5, TpHCM','08654763','3/9/1979','70,000','14/01/2007',N'Vãng lai')
insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH10',N'Hà Duy Lập',N'34/34B Nguyễn Trãi, Q1, TpHCM','08768904','2/5/1983','67,500','16/01/2007',N'Vãng lai')

insert into khachhang (makh,hoten,dchi,sodt,ngsinh,doanhso,ngdk, LOAIKH) values ('KH99',N'Test',N'34/34B Nguyễn Trãi, Q1, TpHCM','08768904','2/5/1983','67,500','16/01/2007',N'Vãng lai')

INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('BC01', N'Bút chì', N'cây', N'Singapore', 3000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('BC02', N'Bút chì', N'cây', N'Singapore', 5000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('BC03', N'Bút chì', N'cây', N'Việt Nam', 3500)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('BC04', N'Bút chì', N'hộp', N'Việt Nam', 30000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('BB01', N'Bút chì', N'cây', N'Việt Nam', 5000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('BB02', N'Bút bi', N'cây', N'Trung Quốc', 7000)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('BB03', N'Bút bi', N'hộp', N'Thái Lan', 100000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('TV01', N'Tập 100 giấy mỏng', N'quyển', N'Trung Quốc', 2500)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('TV02', N'Tập 200 giấy mỏng', N'quyển', N'Trung Quốc', 4500)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('TV03', N'Tập 100 giấy tốt', N'quyển', N'Việt Nam', 3000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('TV04', N'Tập 200 giấy tốt', N'quyển', N'Việt Nam', 5500)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('TV05', N'Tập 100 trang', N'chục', N'Việt Nam', 23000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('TV06', N'Tập 200 trang', N'chục', N'Việt Nam', 53000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('TV07', N'Tập 100 trang', N'chục', N'Việt Nam', 34000)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('ST01', N'Sổ tay 500 trang', N'quyển', N'Việt Nam', 40000)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('ST02', N'Sổ tay loại 1', N'quyển', N'Việt Nam', 55000)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('ST03', N'Sổ tay loại 2', N'quyển', N'Việt Nam', 51000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('ST04', N'Sổ tay', N'quyển', N'Thái Lan', 55000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('ST05', N'Sổ tay mỏng', N'quyển', N'Thái Lan', 20000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('ST06', N'Phấn viết bảng', N'hộp', N'Việt Nam', 5000)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('ST07', N'Phấn không bụi', N'hộp', N'Việt Nam', 5000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)    VALUES ('ST08', N'Bóng băng', N'cái', N'Việt Nam', 1000)
INSERT INTO sanpham (MASP,TENSP,DVT,NUOCSX,GIA)   VALUES ('ST09', N'Bút lông', N'cây', N'Việt Nam', 5000)
INSERT INTO sanpham  (MASP,TENSP,DVT,NUOCSX,GIA)  VALUES ('ST10', N'Bút lông', N'cây', N'Trung Quốc', 7000)

INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)  VALUES ('23/07/2006', 'KH01', 'NV01', 320000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('12/08/2006', 'KH01', 'NV02', 840000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('23/06/2006', 'KH02', 'NV01', 100000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('01/09/2006', 'KH02', 'NV01', 180000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('20/10/2006', 'KH01', 'NV02', 3800000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('16/10/2006', 'KH01', 'NV03', 2430000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('28/10/2006', 'KH03', 'NV03', 510000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('28/10/2006', 'KH01', 'NV03', 440000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('28/10/2006', 'KH03', 'NV04', 320000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('01/11/2006', 'KH01', 'NV01', 5200000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('04/11/2006', 'KH04', 'NV03', 250000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('30/11/2006', 'KH05', 'NV03', 21000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('12/12/2006', 'KH06', 'NV01', 5000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('31/12/2006', 'KH03', 'NV02', 3150000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ( '01/01/2007', 'KH06', 'NV01', 910000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ( '01/01/2007', 'KH07', 'NV02', 12500)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('02/01/2007', 'KH08', 'NV03', 35000)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('13/01/2007', 'KH08', 'NV03', 330000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('13/01/2007', 'KH01', 'NV03', 30000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('14/01/2007', 'KH09', 'NV04', 70000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ('16/01/2007', 'KH10', 'NV04', 67500)
INSERT INTO hoadon  (NGHD,MAKH,MANV,TRIGIA)  VALUES ('16/01/2007', Null, 'NV03', 7000)
INSERT INTO hoadon (NGHD,MAKH,MANV,TRIGIA)   VALUES ( '17/01/2007', Null, 'NV01', 330000)

insert into CTHD (SOHD,MASP,SL) values (1001,'TV02',10)
insert into CTHD (SOHD,MASP,SL) values (1001,'ST01',5)
insert into CTHD (SOHD,MASP,SL) values (1001,'BC01',5)
insert into CTHD (SOHD,MASP,SL) values (1001,'BC02',10)
insert into CTHD (SOHD,MASP,SL) values (1001,'ST08',10)
insert into CTHD (SOHD,MASP,SL) values (1002,'BC04',20)
insert into CTHD (SOHD,MASP,SL) values (1002,'BB01',20)
insert into CTHD (SOHD,MASP,SL) values (1002,'BB02',20)
insert into CTHD (SOHD,MASP,SL) values (1003,'BB03',10)
insert into CTHD (SOHD,MASP,SL) values (1004,'TV01',20)
insert into CTHD (SOHD,MASP,SL) values (1004,'TV02',10)
insert into CTHD (SOHD,MASP,SL) values (1004,'TV03',10)
insert into CTHD (SOHD,MASP,SL) values (1004,'TV04',10)
insert into CTHD (SOHD,MASP,SL) values (1005,'TV05',50)
insert into CTHD (SOHD,MASP,SL) values (1005,'TV06',50)
insert into CTHD (SOHD,MASP,SL) values (1006,'TV07',20)
insert into CTHD (SOHD,MASP,SL) values (1006,'ST01',30)
insert into CTHD (SOHD,MASP,SL) values (1006,'ST02',10)
insert into CTHD (SOHD,MASP,SL) values (1007,'ST03',10)
insert into CTHD (SOHD,MASP,SL) values (1008,'ST04',8)
insert into CTHD (SOHD,MASP,SL) values (1009,'ST05',10)
insert into CTHD (SOHD,MASP,SL) values (1010,'TV07',50)
insert into CTHD (SOHD,MASP,SL) values (1010,'ST07',50)
insert into CTHD (SOHD,MASP,SL) values (1010,'ST08',100)
insert into CTHD (SOHD,MASP,SL) values (1010,'ST04',50)
insert into CTHD (SOHD,MASP,SL) values (1010,'TV03',100)
insert into CTHD (SOHD,MASP,SL) values (1011,'ST06',50)
insert into CTHD (SOHD,MASP,SL) values (1012,'ST07',3)
insert into CTHD (SOHD,MASP,SL) values (1013,'ST08',5)
insert into CTHD (SOHD,MASP,SL) values (1014,'BC02',80)
insert into CTHD (SOHD,MASP,SL) values (1014,'BB02',100)
insert into CTHD (SOHD,MASP,SL) values (1014,'BC04',60)
insert into CTHD (SOHD,MASP,SL) values (1014,'BB01',50)
insert into CTHD (SOHD,MASP,SL) values (1015,'BB02',30)
insert into CTHD (SOHD,MASP,SL) values (1015,'BB03',7)
insert into CTHD (SOHD,MASP,SL) values (1016,'TV01',5)
insert into CTHD (SOHD,MASP,SL) values (1017,'TV02',1)
insert into CTHD (SOHD,MASP,SL) values (1017,'TV03',1)
insert into CTHD (SOHD,MASP,SL) values (1017,'TV04',5)
insert into CTHD (SOHD,MASP,SL) values (1018,'ST04',6)
insert into CTHD (SOHD,MASP,SL) values (1019,'ST05',1)
insert into CTHD (SOHD,MASP,SL) values (1019,'ST06',2)
insert into CTHD (SOHD,MASP,SL) values (1020,'ST07',10)
insert into CTHD (SOHD,MASP,SL) values (1021,'ST08',5)
insert into CTHD (SOHD,MASP,SL) values (1021,'TV01',7)
insert into CTHD (SOHD,MASP,SL) values (1021,'TV02',10)
insert into CTHD (SOHD,MASP,SL) values (1022,'ST07',1)
insert into CTHD (SOHD,MASP,SL) values (1023,'ST04',6)

INSERT INTO cthd   VALUES (1001, 'TV02', 10)
INSERT INTO cthd   VALUES (1001, 'ST01', 5)
INSERT INTO cthd   VALUES (1001, 'BC01', 5)
INSERT INTO cthd   VALUES (1001, 'BC02', 10)
INSERT INTO cthd   VALUES (1001, 'ST08', 10)
INSERT INTO cthd   VALUES (1002, 'BC04', 20)
INSERT INTO cthd   VALUES (1002, 'BB01', 20)
INSERT INTO cthd   VALUES (1002, 'BB02', 20)
INSERT INTO cthd   VALUES (1003, 'BB03', 10)
INSERT INTO cthd   VALUES (1004, 'TV01', 20)
INSERT INTO cthd   VALUES (1004, 'TV02', 10)
INSERT INTO cthd   VALUES (1004, 'TV03', 10)
INSERT INTO cthd   VALUES (1004, 'TV04', 10)
INSERT INTO cthd   VALUES (1005, 'TV05', 50)
INSERT INTO cthd   VALUES (1005, 'TV06', 50)
INSERT INTO cthd   VALUES (1001, 'TV07', 20)
INSERT INTO cthd   VALUES (1006, 'ST01', 30)
INSERT INTO cthd   VALUES (1006, 'ST02', 10)
INSERT INTO cthd   VALUES (1007, 'ST03', 10)
INSERT INTO cthd   VALUES (1008, 'ST04', 8)
INSERT INTO cthd   VALUES (1009, 'ST05', 10)
INSERT INTO cthd   VALUES (1010, 'TV07', 50)
INSERT INTO cthd   VALUES (1010, 'ST07', 50)
INSERT INTO cthd   VALUES (1010, 'ST08', 100)
INSERT INTO cthd   VALUES (1010, 'ST04', 50)
INSERT INTO cthd   VALUES (1010, 'TV03', 100)
INSERT INTO cthd   VALUES (1011, 'ST06', 50)
INSERT INTO cthd   VALUES (1012, 'ST07', 3)
INSERT INTO cthd   VALUES (1013, 'ST08', 5)
INSERT INTO cthd   VALUES (1014, 'BC02', 80)
INSERT INTO cthd   VALUES (1014, 'BB02', 100)
INSERT INTO cthd   VALUES (1014, 'BC04', 60)
INSERT INTO cthd   VALUES (1014, 'BB01', 50)
INSERT INTO cthd   VALUES (1015, 'BB02', 30)
INSERT INTO cthd   VALUES (1015, 'BB03', 7)
INSERT INTO cthd   VALUES (1016, 'TV01', 5)
INSERT INTO cthd   VALUES (1017, 'TV02', 1)
INSERT INTO cthd   VALUES (1017, 'TV03', 1)
INSERT INTO cthd   VALUES (1017, 'TV04', 5)
INSERT INTO cthd   VALUES (1018, 'ST04', 6)
INSERT INTO cthd   VALUES (1019, 'ST05', 1)
INSERT INTO cthd   VALUES (1019, 'ST06', 2)
INSERT INTO cthd   VALUES (1020, 'ST07', 10)
INSERT INTO cthd   VALUES (1021, 'ST08', 5)
INSERT INTO cthd   VALUES (1021, 'TV01', 7)
INSERT INTO cthd   VALUES (1021, 'TV02', 10)
INSERT INTO cthd   VALUES (1022, 'TV02', 10)
INSERT INTO cthd   VALUES (1023, 'ST04', 6)
INSERT INTO cthd   VALUES (1023, 'BC01', 5)
INSERT INTO cthd   VALUES (1023, 'BC02', 1)

select *
from HOADON
where SOHD = '1023'
select top 10 with ties abc.tong as soluongln 
        		 from (select cthd.masp, sum (sl) as 'tong' from cthd group by masp) abc join sanpham sp 
        		on abc.masp = sp.masp order by soluongln asc
