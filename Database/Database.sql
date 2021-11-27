CREATE DATABASE DB_CovidApp
GO
USE DB_CovidApp
GO

CREATE TABLE NGUOIDUNG
(
	CMND CHAR(12) NOT NULL,
	HOTEN NVARCHAR(200),
	NAMSINH INT,
	TRANGTHAI CHAR(5),
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
	CHOTRONG INT

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
	MATKHAU CHAR(24),
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
	TRANGTHAICU CHAR(10),
	TRANGTHAIMOI CHAR(10),
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
	THOIGIAN DATE

	PRIMARY KEY(MAHD, CMND)
)

GO
CREATE TABLE LSTHANHTOAN
(
	MAGD CHAR(10) NOT NULL,
	CMND CHAR(12) NOT NULL,
	DUNO MONEY,
	SOTIENTRA MONEY,
	THOIGIAN DATE

	PRIMARY KEY(MAGD, CMND)
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
ALTER TABLE LSMUAHANG ADD
	CONSTRAINT FK_LSMUAHANG_NHUYEUPHAM FOREIGN KEY (LOAIHANG) REFERENCES NHUYEUPHAM(MANYP)
GO
ALTER TABLE LSTHANHTOAN ADD
	CONSTRAINT FK_LSTHANHTOAN_NGUOIDUNG FOREIGN KEY (CMND) REFERENCES NGUOIDUNG(CMND)
GO


INSERT INTO NGUOIDUNG(CMND,HOTEN,NAMSINH,TRANGTHAI,XA,HUYEN,TINH) -- NGUOILIENQUAN,NOICACHLY
	VALUES  ('0312614186', N'Nguyễn Văn An',1997,'F0',N'Phường 4',N'Quận 5',N'TP.HCM'),
			('0191818716', N'Nguyễn Đức Quân',1976,'F1',N'Phường Cát Linh', N'Quận Đống Đa',N'TP.Hà Nội'),
			('0137692698', N'Trần Văn Đức',1996, 'F2',N'Xã Ngọc Đường',N'TP. Hà Giang',N'Tỉnh Hà Giang'),
			('0342107164', N'Phạm Đăng Phú',1992, 'F0',N'Phường 21',N'Quận Bình Thạnh',N'TP.HCM'),
			('0809669861', N'Hoàng Gia Bảo',1989, 'F2',N'Xã Quảng Văn',N'Thị Xã Ba Đồn',N'Tỉnh Quảng Bình'),
			('0745271814', N'Lê Hoàng Bảo Nam',1975,'F1',N'Thị Trấn Nam Phước',N'Huyện Duy Xuyên',N'Tỉnh Quảng Nam'),
			('0497492975', N'Cao Hoàng Ánh Duyên',1995, 'F2',N'Xã Đức Lợi',N'Huyện Mộ Đức',N'Tỉnh Quảng Ngãi'),
			('0528984542', N'Trần Thanh Như',1981, 'F0',N'Phường Phú Thuỷ',N'TP. Phan Thiết',N'Tỉnh Bình Thuận'),
			('0213489216', N'Nguyễn Minh Bảo',1990, 'F1',N'Phường Long Phước',N'Thị Xã Phước Long',N'Tỉnh Bình Phước')
GO

INSERT INTO KHUCACHLY(MAKCL,TENKCL,SUCCHUC,CHOTRONG)
	VALUES	('BV001',N'Bệnh viện Quận 7',300,150),
			('BV002',N'Bệnh viện Cần Giờ',366,14),
			('BV003',N'Bệnh viện dã chiến Củ Chi',1000,150),
			('BV004',N'Bệnh viện Bệnh Nhiệt đới Q.5',700,150),
			('BV005',N'Trung tâm Y tế Cần Giờ',300,5),
			('KS002',N'Khách sạn Holiday Inn',350,350),
			('KS003',N'Khách sạn Bát Đạt',77,70),
			('KS004',N'Khách sạn Đệ Nhất',152,120),
			('KS005',N'Khách sạn Des Arts Saigon',165,100),
			('KS006',N'Khách sạn Norfolk',104,100)

INSERT INTO TAIKHOAN(TAIKHOAN,MATKHAU,PHANHE,SODU,TINHTRANG) --DUNO
	VALUES  ('0312614186',NULL,'NGUOIDUNG',3782644,0),
			('0191818716','ndquan','NGUOIDUNG',2747664,1),
			('0137692698',NULL,'NGUOIDUNG',4923005,0),
			('0342107164',NULL,'NGUOIDUNG',2478551,0),
			('0809669861',NULL,'NGUOIDUNG',9058035,0),
			('0745271814',NULL,'NGUOIDUNG',7411665,0),
			('0497492975',NULL,'NGUOIDUNG',3090293,0),
			('0528984542',NULL,'NGUOIDUNG',8112845,0),
			('0213489216',NULL,'NGUOIDUNG',6586918,0),
			('QuanLyA','QuanLyA','QUANLY',NULL,1),
			('QuanTriThanhToan','NguoiThanhToan','QUANTRI',48370183,1),
			('QuanTriCOVID','COVID_19','QUANTRI',NULL,1),
			('a','a','QUANLY',NULL,1),
			('b','b','NGUOIDUNG',NULL,1),
			('c','c','QUANTRI',100000,1)
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
	VALUES	('0312614186', 'F2', 'F1', '2020/11/20'),
			('0312614186', 'F1', 'F0', '2020/12/19'),
			('0745271814', 'F2', 'F1', '2021/04/27'),
			('0342107164', 'F2', 'F1', '2021/07/07'),
			('0342107164', 'F2', 'F1', '2021/07/14'),
			('0342107164', 'F1', 'F0', '2021/08/02')
GO

INSERT INTO LSMUAHANG(MAHD, CMND, LOAIHANG, SOLUONG, THOIGIAN)
	VALUES	('C7311', '0191818716', 'NYP03', 2, '2020/01/12'),
			('D2946', '0809669861', 'NYP04', 1, '2020/04/02'),
			('A2555', '0213489216', 'NYP01', 1, '2020/05/30'),
			('B9089', '0745271814', 'NYP02', 1, '2020/07/23'),
			('B1214', '0137692698', 'NYP04', 1, '2020/09/11'),
			('C2057', '0342107164', 'NYP03', 2, '2020/10/21'),
			('A6627', '0528984542', 'NYP01', 2, '2020/11/22'),
			('D2938', '0213489216', 'NYP04', 1, '2020/12/29')
GO

INSERT INTO LSTHANHTOAN(MAGD, CMND, DUNO, SOTIENTRA, THOIGIAN)
	VALUES	('6961605057', '0191818716', 200000, 200000, '2020/01/12')

GO
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0528984542', NOICACHLY = 'BV001' WHERE CMND = '0312614186'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0312614186', NOICACHLY = 'BV005' WHERE CMND = '0191818716'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0191818716', NOICACHLY = 'KS002' WHERE CMND = '0137692698'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0312614186', NOICACHLY = 'BV002' WHERE CMND = '0342107164'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0528984542', NOICACHLY = 'KS002' WHERE CMND = '0809669861'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0528984542', NOICACHLY = 'KS003' WHERE CMND = '0745271814'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0809669861', NOICACHLY = 'KS004' WHERE CMND = '0497492975'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0342107164', NOICACHLY = 'KS005' WHERE CMND = '0528984542'
UPDATE NGUOIDUNG
	SET NGUOILIENQUAN = '0528984542', NOICACHLY = 'KS006' WHERE CMND = '0213489216'