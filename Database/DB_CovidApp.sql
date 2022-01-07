CREATE DATABASE DB_CovidApp
GO
USE DB_CovidApp
GO

CREATE TABLE NGUOIDUNG
(
	CMND CHAR(12) NOT NULL,
	HOTEN NVARCHAR(200),
	NAMSINH INT,
	TRANGTHAI NCHAR(10),
	NGUOILIENQUAN CHAR(12),
	XA NVARCHAR(100),
	HUYEN NVARCHAR(100),
	TINH NVARCHAR(100),
	NOICACHLY CHAR(10),
	
	PRIMARY KEY(CMND)
)
GO
CREATE TABLE KHUCACHLY
(
	MAKCL CHAR(10) NOT NULL,
	TENKCL NVARCHAR(100),
	SUCCHUC INT,
	DATIEPNHAN INT

	PRIMARY KEY(MAKCL)
)
GO
CREATE TABLE DIACHI
(
	XA NVARCHAR(100),
	HUYEN NVARCHAR(100),
	TINH NVARCHAR(100)
)
GO
CREATE TABLE TAIKHOAN
(
	TAIKHOAN CHAR(50) NOT NULL,
	MATKHAU CHAR(40),
	PHANHE CHAR(10),
	SODU MONEY,
	DUNO MONEY,
	TINHTRANG BIT

	PRIMARY KEY(TAIKHOAN)
)
GO

GO
CREATE TABLE NHUYEUPHAM
(
	MANYP CHAR(10) NOT NULL,
	TENNYP NVARCHAR(100),
	HSD DATE,
	GIOIHANSL INT,
	GIATIEN MONEY

	PRIMARY KEY(MANYP)
)

GO
CREATE TABLE LSTRANGTHAI
(
	STT INT IDENTITY(1,1) NOT NULL,
	CMND CHAR(12) NOT NULL,
	TRANGTHAICU NCHAR(10),
	TRANGTHAIMOI NCHAR(10),
	THOIGIAN DATE

	PRIMARY KEY(STT, CMND)
)

GO
CREATE TABLE LSMUAHANG
(
	MAHD CHAR(10) NOT NULL,
	CMND CHAR(12) NOT NULL,
	LOAIHANG CHAR(10),
	SOLUONG INT,
	SOTIEN MONEY,
	THOIGIAN DATETIME

	PRIMARY KEY(MAHD, CMND)
)

GO
CREATE TABLE LSTHANHTOAN
(
	MAGD CHAR(10) NOT NULL,
	CMND CHAR(12) NOT NULL,
	DUNO MONEY,
	SOTIENTRA MONEY,
	THOIGIAN DATETIME

	PRIMARY KEY(MAGD, CMND)
)

GO
CREATE TABLE LSHOATDONGQL
(
	STT INT IDENTITY(1,1) NOT NULL,
	MATK CHAR(50) NOT NULL,
	HANHDONG NVARCHAR(10),
	BANG VARCHAR(20),
	MADT CHAR(15),
	THOIGIAN DATETIME

	PRIMARY KEY(STT)
)

GO
ALTER TABLE NGUOIDUNG ADD
	CONSTRAINT FK_NGUOIDUNG_KHUCACHLY FOREIGN KEY (NOICACHLY) REFERENCES KHUCACHLY(MAKCL)
GO
ALTER TABLE LSTRANGTHAI ADD
	CONSTRAINT FK_LSTRANGTHAI_NGUOIDUNG FOREIGN KEY (CMND) REFERENCES NGUOIDUNG(CMND)
GO
ALTER TABLE LSMUAHANG ADD
	CONSTRAINT FK_LSMUAHANG_NGUOIDUNG FOREIGN KEY (CMND) REFERENCES NGUOIDUNG(CMND)
GO
ALTER TABLE LSTHANHTOAN ADD
	CONSTRAINT FK_LSTHANHTOAN_NGUOIDUNG FOREIGN KEY (CMND) REFERENCES NGUOIDUNG(CMND)
GO
ALTER TABLE LSHOATDONGQL ADD
	CONSTRAINT FK_LSHOATDONGQL_TAIKHOAN FOREIGN KEY (MATK) REFERENCES TAIKHOAN(TAIKHOAN)
GO

INSERT INTO NGUOIDUNG(CMND,HOTEN,NAMSINH,TRANGTHAI,XA,HUYEN,TINH) -- NGUOILIENQUAN,NOICACHLY
	VALUES  ('0312614186', N'Nguyễn Văn An',1997,'F1',N'Phường 4',N'Quận 5',N'TP.HCM'),
			('0191818716', N'Nguyễn Đức Quân',1976,'F1',N'Phường Cát Linh', N'Quận Đống Đa',N'TP.Hà Nội'),
			('0137692698', N'Trần Văn Đức',1996, 'F1',N'Xã Ngọc Đường',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			('0342107164', N'Phạm Đăng Phú',1992, N'Khỏi bệnh',N'Phường 21',N'Quận Bình Thạnh',N'TP.HCM'),
			('0809669861', N'Hoàng Gia Bảo',1989, 'F1',N'Xã Quảng Văn',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			('0745271814', N'Lê Hoàng Bảo Nam',1975,'F1',N'Thị Trấn Nam Phước',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			('0497492975', N'Cao Hoàng Ánh Duyên',1995, N'Khỏi bệnh',N'Xã Đức Lợi',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			('0528984542', N'Trần Thanh Như',1981, 'F0',N'Phường Phú Thuỷ',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			('0213489216', N'Nguyễn Minh Bảo',1990, 'F1',N'Phường Long Phước',N'Thị Xã Phước Long',N'Tỉnh Bình Phước')
GO

INSERT INTO KHUCACHLY(MAKCL,TENKCL,SUCCHUC,DATIEPNHAN)
	VALUES	('BV001',N'Bệnh viện Quận 7',300,150),
			('BV002',N'Bệnh viện Cần Giờ',366,300),
			('BV004',N'Bệnh viện Bệnh Nhiệt đới Q.5',700,150),
			('BV005',N'Trung tâm Y tế Cần Giờ',300,300),
			('KS002',N'Khách sạn Holiday Inn',350,50),
			('KS003',N'Khách sạn Bát Đạt',77,50),
			('KS004',N'Khách sạn Đệ Nhất',152,0),
			('KS005',N'Khách sạn Des Arts Saigon',165,35),
			('KS006',N'Khách sạn Norfolk',104,20)
GO

INSERT INTO TAIKHOAN(TAIKHOAN,MATKHAU,PHANHE,SODU,TINHTRANG) --DUNO
	VALUES  ('0312614186','1073d23e818eb7f5ed7c7b53bccbe243','NGUOIDUNG',3782644,1),
			('0191818716','4be9398735592feed4adc4eabc569338','NGUOIDUNG',2747664,1),
			('0137692698','5ea081a4bbe4ca47b462d98b90c10ee2','NGUOIDUNG',4923005,1),
			('0342107164','4bf22813092afa65bbfcb4904f9daeb1','NGUOIDUNG',2478551,1),
			('0809669861','c0276a58414e89040b85d5754a7d7323','NGUOIDUNG',9058035,1),
			('0745271814','e30c50806ffc25a25167f0874b223b75','NGUOIDUNG',7411665,1),
			('0497492975','28f781fe40a21ad618a7780859bcbb51','NGUOIDUNG',3090293,0),
			('0528984542','fed9111868f367b2316650576f6044b','NGUOIDUNG',8112845,0),
			('0213489216','1a802f29b5af3c1e8a9bf266592f8141','NGUOIDUNG',6586918,0),
			('QuanLyA','fc88f5e8091c65d49577d129e9ac6654','QUANLY',NULL,1),
			('Admin_Covid','e3abd4c5a4ae7d81dff1bc240d2950f0','QUANTRI',NULL,1)
GO

INSERT INTO NHUYEUPHAM(MANYP,TENNYP,HSD,GIOIHANSL,GIATIEN)
	VALUES 	('NYP01',N'Gói Đồ đóng hộp','2023/01/01', 2, 120000),
			('NYP02',N'Gói Bổ sung vitamin C','2022/01/01', 1, 550000),
			('NYP03',N'Gói Chăm sóc cá nhân','2023/01/01', 5, 200000),
			('NYP04',N'Gói Thực phẩm','2022/01/01', 1, 150000),
			('NYP05',N'Gói Bảo vệ sức khoẻ','2023/01/01', 7, 300000)
GO

INSERT INTO DIACHI(XA,HUYEN,TINH)
	VALUES  (N'Phường Cát Linh', N'Quận Đống Đa',N'Thủ Đô Hà Nội'),
			(N'Phường Thổ Quan', N'Quận Đống Đa',N'Thủ Đô Hà Nội'),
			(N'Phường Láng Hạ', N'Quận Đống Đa',N'Thủ Đô Hà Nội'),
			(N'Phường Láng Thượng', N'Quận Đống Đa',N'Thủ Đô Hà Nội'),
			(N'Phường Văn Miếu', N'Quận Đống Đa',N'Thủ Đô Hà Nội'),
			(N'Phường Dịch Vọng', N'Quận Cầu Giấy',N'Thủ Đô Hà Nội'),
			(N'Phường Mai Dịch', N'Quận Cầu Giấy',N'Thủ Đô Hà Nội'),
			(N'Phường Nghĩa Đô', N'Quận Cầu Giấy',N'Thủ Đô Hà Nội'),
			(N'Phường Quan Hoa', N'Quận Cầu Giấy',N'Thủ Đô Hà Nội'),
			(N'Phường Trung Hòa', N'Quận Cầu Giấy',N'Thủ Đô Hà Nội'),
			(N'Xã An Khánh', N'Huyện Hoài Đức',N'Thủ Đô Hà Nội'),
			(N'Xã Đông La', N'Huyện Hoài Đức',N'Thủ Đô Hà Nội'),
			(N'Xã Di Trạch', N'Huyện Hoài Đức',N'Thủ Đô Hà Nội'),
			(N'Xã Minh Khai', N'Huyện Hoài Đức',N'Thủ Đô Hà Nội'),
			(N'Xã Tiều Yên', N'Huyện Hoài Đức',N'Thủ Đô Hà Nội'),
			(N'Xã Bát Tràng', N'Huyện Gia Lâm',N'Thủ Đô Hà Nội'),
			(N'Xã Đông Dư', N'Huyện Gia Lâm',N'Thủ Đô Hà Nội'),
			(N'Xã Lệ Chi', N'Huyện Gia Lâm',N'Thủ Đô Hà Nội'),
			(N'Xã Dương Xá', N'Huyện Gia Lâm',N'Thủ Đô Hà Nội'),
			(N'Xã Kim Sơn', N'Huyện Gia Lâm',N'Thủ Đô Hà Nội'),
			(N'Xã Bắc Hồng', N'Huyện Đông Anh',N'Thủ Đô Hà Nội'),
			(N'Xã Cổ Loa', N'Huyện Đông Anh',N'Thủ Đô Hà Nội'),
			(N'Xã Mai Lâm', N'Huyện Đông Anh',N'Thủ Đô Hà Nội'),
			(N'Xã Đại Mạch', N'Huyện Đông Anh',N'Thủ Đô Hà Nội'),
			(N'Xã Tiên Dương', N'Huyện Đông Anh',N'Thủ Đô Hà Nội'),
			--
			(N'Phường Minh Khai',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			(N'Phường Ngọc Hà',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			(N'Phường Trần Phú',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			(N'Xã Phương Độ',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			(N'Xã Ngọc Đường',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			(N'Xã Bạch Ngọc',N'Huyện Vị Xuyên',N'Tỉnh Hà Giang'),
			(N'Xã Cao Bồ',N'Huyện Vị Xuyên',N'Tỉnh Hà Giang'),
			(N'Xã Đạo Đức',N'Huyện Vị Xuyên',N'Tỉnh Hà Giang'),
			(N'Xã Thuận Hòa',N'Huyện Vị Xuyên',N'Tỉnh Hà Giang'),
			(N'Xã Thượng Sơn',N'Huyện Vị Xuyên',N'Tỉnh Hà Giang'),	
			(N'Xã Đường Âm',N'Huyện Bắc Mê',N'Tỉnh Hà Giang'),
			(N'Xã Đường Hồng',N'Huyện Bắc Mê',N'Tỉnh Hà Giang'),
			(N'Xã Giáp Trung',N'Huyện Bắc Mê',N'Tỉnh Hà Giang'),
			(N'Xã Lạc Nông',N'Huyện Bắc Mê',N'Tỉnh Hà Giang'),
			(N'Xã Minh Ngọc',N'Huyện Bắc Mê',N'Tỉnh Hà Giang'),
			(N'Thị Trấn Vĩnh Tuy',N'Huyện Bắc Quang',N'Tỉnh Hà Giang'),
			(N'Xã Bằng Hành',N'Huyện Bắc Quang',N'Tỉnh Hà Giang'),
			(N'Xã Đồng Tâm',N'Huyện Bắc Quang',N'Tỉnh Hà Giang'),
			(N'Xã Đông Thành',N'Huyện Bắc Quang',N'Tỉnh Hà Giang'),
			(N'Xã Vô Điếm',N'Huyện Bắc Quang',N'Tỉnh Hà Giang'),
			(N'Thị Trấn Yên Bình',N'Huyện Quang Bình',N'Tỉnh Hà Giang'),
			(N'Xã Tân Trịnh',N'Huyện Quang Bình',N'Tỉnh Hà Giang'),
			(N'Xã Tiên Nguyên',N'Huyện Quang Bình',N'Tỉnh Hà Giang'),
			(N'Xã Vĩ Thượng',N'Huyện Quang Bình',N'Tỉnh Hà Giang'),
			(N'Xã Xuân Giang',N'Huyện Quang Bình',N'Tỉnh Hà Giang'),
			--
			(N'Phường Quảng Long',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			(N'Phường Quảng Phong',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			(N'Phường Quảng Phúc',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			(N'Phường Quảng Thọ',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			(N'Xã Quảng Văn',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			(N'Xã Trọng Hóa',N'Huyện Minh Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Hóa Phúc',N'Huyện Minh Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Hồng Hóa',N'Huyện Minh Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Hóa Thanh',N'Huyện Minh Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Dân Hóa',N'Huyện Minh Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Quảng Kim',N'Huyện Quảng Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Quảng Đông',N'Huyện Quảng Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Quảng Phú',N'Huyện Quảng Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Quảng Châu',N'Huyện Quảng Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Quảng Thạch',N'Huyện Quảng Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Kim Hóa',N'Huyện Tuyên Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Thanh Thạch',N'Huyện Tuyên Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Thuận Hóa',N'Huyện Tuyên Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Lâm Hóa',N'Huyện Tuyên Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Lê Hóa',N'Huyện Tuyên Hóa',N'Tỉnh Quảng Bình'),
			(N'Xã Xuân Trạch',N'Huyện Bố Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Mỹ Trạch',N'Huyện Bố Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Hạ Trạch',N'Huyện Bố Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Bắc Trạch',N'Huyện Bố Trạch',N'Tỉnh Quảng Bình'),
			(N'Xã Lâm Trạch',N'Huyện Bố Trạch',N'Tỉnh Quảng Bình'),
			--
			(N'Phường An Mỹ',N'TP.Tam Kỳ',N'Tỉnh Quảng Nam'),
			(N'Phường An Phú',N'TP.Tam Kỳ',N'Tỉnh Quảng Nam'),
			(N'Phường Hoà Thuận',N'TP.Tam Kỳ',N'Tỉnh Quảng Nam'),
			(N'Phường Phước Hoà',N'TP.Tam Kỳ',N'Tỉnh Quảng Nam'),
			(N'Phường Trường Xuân',N'TP.Tam Kỳ',N'Tỉnh Quảng Nam'),
			(N'Phường Cẩm An',N'TP.Hội An',N'Tỉnh Quảng Nam'),
			(N'Phường Cẩm Châu',N'TP.Hội An',N'Tỉnh Quảng Nam'),
			(N'Phường Cẩm Nam',N'TP.Hội An',N'Tỉnh Quảng Nam'),
			(N'Phường Sơn Phong',N'TP.Hội An',N'Tỉnh Quảng Nam'),
			(N'Phường Cửa Đại',N'TP.Hội An',N'Tỉnh Quảng Nam'),
			(N'Thị Trấn Nam Phước',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			(N'Xã Duy Thu',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			(N'Xã Duy Tân',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			(N'Xã Duy Châu',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			(N'Xã Duy Trinh',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			(N'Xã Đại Sơn',N'Huyện Đại Lộc',N'Tỉnh Quảng Nam'),
			(N'Xã Đại Lãnh',N'Huyện Đại Lộc',N'Tỉnh Quảng Nam'),
			(N'Xã Đại Hưng',N'Huyện Đại Lộc',N'Tỉnh Quảng Nam'),
			(N'Xã Đại Hồng',N'Huyện Đại Lộc',N'Tỉnh Quảng Nam'),
			(N'Xã Đại Đồng',N'Huyện Đại Lộc',N'Tỉnh Quảng Nam'),
			(N'Xã Quế Phú',N'Huyện Quế Sơn',N'Tỉnh Quảng Nam'),
			(N'Xã Quế Hiệp',N'Huyện Quế Sơn',N'Tỉnh Quảng Nam'),
			(N'Xã Quế Mỹ',N'Huyện Quế Sơn',N'Tỉnh Quảng Nam'),
			(N'Xã Quế Long',N'Huyện Quế Sơn',N'Tỉnh Quảng Nam'),
			(N'Xã Quế Thuận',N'Huyện Quế Sơn',N'Tỉnh Quảng Nam'),
			--
			(N'Phường Chánh Lộ',N'TP.Quảng Ngãi',N'Tỉnh Quảng Ngãi'),
			(N'Phường Nghĩa Chánh',N'TP.Quảng Ngãi',N'Tỉnh Quảng Ngãi'),
			(N'Phường Nguyễn Nghiêm',N'TP.Quảng Ngãi',N'Tỉnh Quảng Ngãi'),
			(N'Phường Trần Phú',N'TP.Quảng Ngãi',N'Tỉnh Quảng Ngãi'),
			(N'Phường Lê Hồng Phong',N'TP.Quảng Ngãi',N'Tỉnh Quảng Ngãi'),
			(N'Xã Đức Chánh',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			(N'Xã Đức Hiệp',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			(N'Xã Đức Nhuận',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			(N'Xã Đức Thắng',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			(N'Xã Đức Lợi',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			(N'Thị Trấn Sông Vệ',N'Huyện Tư Nghĩa',N'Tỉnh Quảng Ngãi'),
			(N'Xã Nghĩa Hoà',N'Huyện Tư Nghĩa',N'Tỉnh Quảng Ngãi'),
			(N'Xã Nghĩa Hiệp',N'Huyện Tư Nghĩa',N'Tỉnh Quảng Ngãi'),
			(N'Xã Nghĩa Phương',N'Huyện Tư Nghĩa',N'Tỉnh Quảng Ngãi'),
			(N'Xã Nghĩa Kỳ',N'Huyện Tư Nghĩa',N'Tỉnh Quảng Ngãi'),
			(N'Thị Trấn Châu Ổ',N'Huyện Bình Sơn',N'Tỉnh Quảng Ngãi'),
			(N'Xã Bình An',N'Huyện Bình Sơn',N'Tỉnh Quảng Ngãi'),
			(N'Xã Bình Chánh',N'Huyện Bình Sơn',N'Tỉnh Quảng Ngãi'),
			(N'Xã Bình Châu',N'Huyện Bình Sơn',N'Tỉnh Quảng Ngãi'),
			(N'Xã Bình Chương',N'Huyện Bình Sơn',N'Tỉnh Quảng Ngãi'),
			(N'Xã Hành Minh',N'Huyện Nghĩa Hành',N'Tỉnh Quảng Ngãi'),
			(N'Xã Hành Đức',N'Huyện Nghĩa Hành',N'Tỉnh Quảng Ngãi'),
			(N'Xã Hành Trung',N'Huyện Nghĩa Hành',N'Tỉnh Quảng Ngãi'),
			(N'Xã Hành Dũng',N'Huyện Nghĩa Hành',N'Tỉnh Quảng Ngãi'),
			(N'Xã Hành Thiện',N'Huyện Nghĩa Hành',N'Tỉnh Quảng Ngãi'),
			--
			(N'Phường Bình Hưng',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			(N'Phường Hàm Tiến',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			(N'Phường Mũi Né',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			(N'Phường Phú Tài',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			(N'Phường Phú Thuỷ',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			(N'Xã Phan Dũng',N'Huyện Tuy Phong',N'Tỉnh Bình Thuận'),
			(N'Xã Phong Phú',N'Huyện Tuy Phong',N'Tỉnh Bình Thuận'),
			(N'Xã Vĩnh Hảo',N'Huyện Tuy Phong',N'Tỉnh Bình Thuận'),
			(N'Xã Vĩnh Tân',N'Huyện Tuy Phong',N'Tỉnh Bình Thuận'),
			(N'Xã Phú Lạc',N'Huyện Tuy Phong',N'Tỉnh Bình Thuận'),
			(N'Xã Phan Sơn',N'Huyện Bắc Bình',N'Tỉnh Bình Thuận'),
			(N'Xã Phan Lâm',N'Huyện Bắc Bình',N'Tỉnh Bình Thuận'),
			(N'Xã Bình An',N'Huyện Bắc Bình',N'Tỉnh Bình Thuận'),
			(N'Xã Phan Điền',N'Huyện Bắc Bình',N'Tỉnh Bình Thuận'),
			(N'Xã Hải Ninh',N'Huyện Bắc Bình',N'Tỉnh Bình Thuận'),
			(N'Xã La Dạ',N'Huyện Hàm Thuận Bắc',N'Tỉnh Bình Thuận'),
			(N'Xã Đông Tiến',N'Huyện Hàm Thuận Bắc',N'Tỉnh Bình Thuận'),
			(N'Xã Thuận Hòa',N'Huyện Hàm Thuận Bắc',N'Tỉnh Bình Thuận'),
			(N'Xã Đông Giang',N'Huyện Hàm Thuận Bắc',N'Tỉnh Bình Thuận'),
			(N'Xã Hàm Phú',N'Huyện Hàm Thuận Bắc',N'Tỉnh Bình Thuận'),
			(N'Xã Hàm Cường',N'Huyện Hàm Thuận Nam',N'Tỉnh Bình Thuận'),
			(N'Xã Hàm Mỹ',N'Huyện Hàm Thuận Nam',N'Tỉnh Bình Thuận'),
			(N'Xã Tân Lập',N'Huyện Hàm Thuận Nam',N'Tỉnh Bình Thuận'),
			(N'Xã Hàm Minh',N'Huyện Hàm Thuận Nam',N'Tỉnh Bình Thuận'),
			(N'Xã Thuận Quý',N'Huyện Hàm Thuận Nam',N'Tỉnh Bình Thuận'),
			--
			(N'Phường Long Phước',N'Thị Xã Phước Long',N'Tỉnh Bình Phước'),
			(N'Phường Long Thuỷ',N'Thị Xã Phước Long',N'Tỉnh Bình Phước'),
			(N'Phường Phước Bình',N'Thị Xã Phước Long',N'Tỉnh Bình Phước'),
			(N'Phường Sơn Giang',N'Thị Xã Phước Long',N'Tỉnh Bình Phước'),
			(N'Phường Thác Mơ',N'Thị Xã Phước Long',N'Tỉnh Bình Phước'),
			(N'Xã Lộc Hòa',N'Huyện Lộc Ninh',N'Tỉnh Bình Phước'),
			(N'Xã Lộc An',N'Huyện Lộc Ninh',N'Tỉnh Bình Phước'),
			(N'Xã Lộc Tấn',N'Huyện Lộc Ninh',N'Tỉnh Bình Phước'),
			(N'Xã Lộc Thạnh',N'Huyện Lộc Ninh',N'Tỉnh Bình Phước'),
			(N'Xã Lộc Hiệp',N'Huyện Lộc Ninh',N'Tỉnh Bình Phước'),
			(N'Xã Hưng Phước',N'Huyện Bù Đốp',N'Tỉnh Bình Phước'),
			(N'Xã Phước Thiện',N'Huyện Bù Đốp',N'Tỉnh Bình Phước'),
			(N'Xã Thiện Hưng',N'Huyện Bù Đốp',N'Tỉnh Bình Phước'),
			(N'Xã Thanh Hòa',N'Huyện Bù Đốp',N'Tỉnh Bình Phước'),
			(N'Xã Tân Thành',N'Huyện Bù Đốp',N'Tỉnh Bình Phước'),
			(N'Xã An Khương',N'Huyện Hớn Quản',N'Tỉnh Bình Phước'),
			(N'Xã An Phú',N'Huyện Hớn Quản',N'Tỉnh Bình Phước'),
			(N'Xã Tân Lợi',N'Huyện Hớn Quản',N'Tỉnh Bình Phước'),
			(N'Xã Tân Hưng',N'Huyện Hớn Quản',N'Tỉnh Bình Phước'),
			(N'Xã Minh Đức',N'Huyện Hớn Quản',N'Tỉnh Bình Phước'),
			(N'Xã Thuận Lợi',N'Huyện Đồng Phú',N'Tỉnh Bình Phước'),
			(N'Xã Đồng Tâm',N'Huyện Đồng Phú',N'Tỉnh Bình Phước'),
			(N'Xã Tân Phước',N'Huyện Đồng Phú',N'Tỉnh Bình Phước'),
			(N'Xã Tân Hưng',N'Huyện Đồng Phú',N'Tỉnh Bình Phước'),
			(N'Xã Tân Lợi',N'Huyện Đồng Phú',N'Tỉnh Bình Phước'),
			--
			(N'Phường 4',N'Quận 5',N'TP.HCM'),
			(N'Phường 5',N'Quận 5',N'TP.HCM'),
			(N'Phường 11',N'Quận 5',N'TP.HCM'),
			(N'Phường 10',N'Quận 5',N'TP.HCM'),
			(N'Phường 14',N'Quận 5',N'TP.HCM'),
			(N'Phường Bến Nghé',N'Quận 1',N'TP.HCM'),
			(N'Phường Bến Thành',N'Quận 1',N'TP.HCM'),
			(N'Phường Cô Giang',N'Quận 1',N'TP.HCM'),
			(N'Phường Đa Kao',N'Quận 1',N'TP.HCM'),
			(N'Phường Tân Định',N'Quận 1',N'TP.HCM'),
			(N'Phường 14',N'Quận Bình Thạnh',N'TP.HCM'),
			(N'Phường 24',N'Quận Bình Thạnh',N'TP.HCM'),
			(N'Phường 21',N'Quận Bình Thạnh',N'TP.HCM'),
			(N'Phường 10',N'Quận Bình Thạnh',N'TP.HCM'),
			(N'Phường 3',N'Quận Bình Thạnh',N'TP.HCM'),
			(N'Thị Trấn Hóc Môn',N'Huyện Hóc Môn',N'TP.HCM'),
			(N'Xã Bà Điểm',N'Huyện Hóc Môn',N'TP.HCM'),
			(N'Xã Xuân Thới Thượng',N'Huyện Hóc Môn',N'TP.HCM'),
			(N'Xã Thới Tam Thôn',N'Huyện Hóc Môn',N'TP.HCM'),
			(N'Xã Tân Hiệp',N'Huyện Hóc Môn',N'TP.HCM'),
			(N'Phường Bình Hưng Hoà A',N'Quận Bình Tân',N'TP.HCM'),
			(N'Phường Bình Hưng Hoà B',N'Quận Bình Tân',N'TP.HCM'),
			(N'Phường Bình Trị Đông A',N'Quận Bình Tân',N'TP.HCM'),
			(N'Phường Bình Trị Đông B',N'Quận Bình Tân',N'TP.HCM'),
			(N'Phường An Lạc',N'Quận Bình Tân',N'TP.HCM')
GO

INSERT INTO LSTRANGTHAI(CMND, TRANGTHAICU, TRANGTHAIMOI, THOIGIAN)
	VALUES	('0191818716', 'F1', 'F0', '2020/12/19'),
			
			('0137692698', 'F2', 'F1', '2021/04/27'),
			
			('0342107164', 'F2', 'F1', '2021/12/14'),
			('0342107164', 'F1', 'F0', '2021/12/30'),
			('0342107164', 'F0', N'Khỏi bệnh', '2022/01/09'),

			('0809669861', 'F2', 'F1', '2022/01/06'),

			('0745271814', 'F2', 'F1', '2021/12/27'),
			('0745271814', 'F1', 'F0', '2022/01/08'),
			
			('0497492975', 'F2', 'F1', '2021/12/20'),
			('0497492975', 'F1', 'F0', '2021/12/31'),
			('0497492975', 'F0', N'Khỏi bệnh', '2022/01/10'),
			
			('0528984542', 'F1', 'F0', '2022/01/09'),

			('0213489216', 'F2', 'F1', '2021/04/27'),

			('0312614186', 'F2', 'F1', '2022/01/04'),

			('0191818716', 'F2', 'F1', '2022/01/07'),

			('0137692698', 'F2', 'F1', '2022/01/10')
GO

INSERT INTO LSMUAHANG(MAHD, CMND, LOAIHANG, SOLUONG, SOTIEN, THOIGIAN)
	VALUES	('C7311', '0191818716', 'NYP03', 2, 400000, '2020/01/12'),
			('D2946', '0809669861', 'NYP04', 1, 150000, '2020/04/02'),
			('A2555', '0213489216', 'NYP01', 1, 120000, '2020/05/30'),
			('B9089', '0745271814', 'NYP02', 1, 550000, '2020/07/23'),
			('B1214', '0137692698', 'NYP04', 1, 150000, '2020/09/11'),
			('C2057', '0342107164', 'NYP03', 2, 400000, '2020/10/21'),
			('A6627', '0528984542', 'NYP01', 2, 240000, '2020/11/22'),
			('D2938', '0213489216', 'NYP04', 1, 150000, '2020/12/29')
GO

UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0191818716', NOICACHLY = 'KS003' WHERE CMND = '0137692698'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0213489216', NOICACHLY = 'KS002' WHERE CMND = '0809669861'
-- UPDATE NGUOIDUNG
-- 	SET NGUOILIENQUAN = '0745271814', NOICACHLY = 'KS002' WHERE CMND = '0497492975'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0312614186', NOICACHLY = 'KS003' WHERE CMND = '0191818716'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0528984542', NOICACHLY = 'KS004' WHERE CMND = '0745271814'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0528984542', NOICACHLY = 'KS005' WHERE CMND = '0213489216'
UPDATE NGUOIDUNG
	SET NOICACHLY = 'BV001' WHERE CMND = '0312614186'
UPDATE NGUOIDUNG
	SET NOICACHLY = 'BV004' WHERE CMND = '0528984542'

-- GO
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0312614186'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0191818716'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0137692698'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0342107164'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0809669861'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0497492975'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0528984542'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0213489216'
-- UPDATE TAIKHOAN
-- 	SET DUNO = '1000000' WHERE TAIKHOAN = '0745271814'