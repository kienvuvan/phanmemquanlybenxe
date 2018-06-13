-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 13, 2018 lúc 06:44 PM
-- Phiên bản máy phục vụ: 10.1.31-MariaDB
-- Phiên bản PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlybenxe`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `Cmt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `HoTen` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `Sdt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`Cmt`, `HoTen`, `GioiTinh`, `NgaySinh`, `Sdt`, `Email`, `DiaChi`, `MatKhau`) VALUES
('004197001988', 'Vũ Văn Tuyên', 'Nam', '1997-03-29', '01634742309', 'vantuyen2903@gmail.com', 'Duy Nhất - Vũ Thư - Thái Bình', 'c4ca4238a0b923820dcc509a6f75849b');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baixe`
--

CREATE TABLE `baixe` (
  `id` int(11) NOT NULL,
  `ViTriDoXe` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `baixe`
--

INSERT INTO `baixe` (`id`, `ViTriDoXe`) VALUES
(1, 'A1.1'),
(2, 'A1.2'),
(3, 'A1.3'),
(4, 'A1.4'),
(5, 'A1.5'),
(6, 'A1.6');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chiphi`
--

CREATE TABLE `chiphi` (
  `CmtChuXe` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Phi` double NOT NULL,
  `Ngay` date NOT NULL,
  `CmtAdmin` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chiphi`
--

INSERT INTO `chiphi` (`CmtChuXe`, `Phi`, `Ngay`, `CmtAdmin`) VALUES
('022096111666', 3600000, '2018-05-15', '004197001988'),
('034097001988', 3600000, '2018-05-15', '004197001988'),
('123456789', 1800000, '2018-05-15', '004197001988'),
('123456987', 1800000, '2018-05-15', '004197001988');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietchiphi`
--

CREATE TABLE `chitietchiphi` (
  `CmtChuXe` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `BienSoXe` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Ngay` date NOT NULL,
  `SoChuyen` int(11) NOT NULL,
  `Phi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `chitietchiphi`
--

INSERT INTO `chitietchiphi` (`CmtChuXe`, `BienSoXe`, `Ngay`, `SoChuyen`, `Phi`) VALUES
('022096111666', '16M2-2903', '2018-05-15', 1, 1800000),
('022096111666', '16M2-3456', '2018-05-15', 1, 1800000),
('034097001988', '16M1-1234', '2018-05-15', 2, 1800000),
('123456789', '17M2-1234', '2018-05-15', 1, 1800000),
('123456987', '17M3-4567', '2018-05-15', 1, 1800000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietchuyenxe`
--

CREATE TABLE `chitietchuyenxe` (
  `MaChuyenXe` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `MaHanhKhach` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietchuyenxe`
--

INSERT INTO `chitietchuyenxe` (`MaChuyenXe`, `MaHanhKhach`) VALUES
('16M2-34561306181', '123456789'),
('17M2-12341306181', '123456987');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuxe`
--

CREATE TABLE `chuxe` (
  `Cmt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `HoTen` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `NhaXe` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Sdt` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chuxe`
--

INSERT INTO `chuxe` (`Cmt`, `HoTen`, `NhaXe`, `Sdt`, `Email`, `GioiTinh`, `NgaySinh`, `DiaChi`, `MatKhau`) VALUES
('022096111666', 'Nguyễn Thị Hằng', 'Nguyễn Hằng', '01634742309', 'nguyenhang62@gmail.com', 'Nữ', '1962-05-06', 'Hà Nam', 'c4ca4238a0b923820dcc509a6f75849b'),
('034097001988', 'Vũ Xuân Tuyến', 'Xuân Tuyến', '0985266492', 'xuantuyen86@gmail.com', 'Nam', '1986-05-01', 'Hải Hậu - Nam Định', 'c4ca4238a0b923820dcc509a6f75849b'),
('123456789', 'Vũ Văn Kiên', 'Vũ Kiên', '01636428930', 'vukien2903@gmail.com', 'Nam', '1999-05-03', 'Thái Bình', 'c4ca4238a0b923820dcc509a6f75849b'),
('123456987', 'Lê Vân Anh', 'Vân Anh', '01647176093', 'leanh2903@gmail.com', 'Nữ', '1999-05-03', 'Thái Bình', 'c4ca4238a0b923820dcc509a6f75849b');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuyenxe`
--

CREATE TABLE `chuyenxe` (
  `MaChuyenXe` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `BienSoXe` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Ngay` date NOT NULL,
  `ThoiGian` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chuyenxe`
--

INSERT INTO `chuyenxe` (`MaChuyenXe`, `BienSoXe`, `Ngay`, `ThoiGian`) VALUES
('16M1-12341206181', '16M1-1234', '2018-06-12', '7h00-9h00'),
('16M1-12341206182', '16M1-1234', '2018-06-12', '9h30-11h30'),
('16M1-12341206183', '16M1-1234', '2018-06-12', '13h00-15h00'),
('16M1-12341206184', '16M1-1234', '2018-06-12', '15h30-17h30'),
('16M2-34561206181', '16M2-3456', '2018-06-12', '7h30-9h00'),
('16M2-34561206182', '16M2-3456', '2018-06-12', '9h30-11h00'),
('16M2-34561306181', '16M2-3456', '2018-06-13', '7h30-9h00'),
('17M2-12341206181', '17M2-1234', '2018-06-12', '6h00-9h00'),
('17M2-12341206182', '17M2-1234', '2018-06-12', '9h30-12h30'),
('17M2-12341306181', '17M2-1234', '2018-06-13', '6h00-9h00'),
('17M3-45671206181', '17M3-4567', '2018-06-12', '12h30-15h00'),
('17M3-45671206182', '17M3-4567', '2018-06-12', '15h30-18h0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hanhkhach`
--

CREATE TABLE `hanhkhach` (
  `Cmt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `HoTen` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Sdt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hanhkhach`
--

INSERT INTO `hanhkhach` (`Cmt`, `HoTen`, `NgaySinh`, `GioiTinh`, `Sdt`, `Email`, `DiaChi`) VALUES
('123456789', 'Vũ Văn Kiên', '1997-03-29', 'Nam', '01636428930', 'vukien29031997@gmail.com', 'Duy Nhất - Vũ Thư - Thái Bình'),
('123456987', 'Nguyễn Văn An', '1997-03-29', 'Nam', '0985266492', 'vukien1997@gmail.com', 'Vũ Thư - Thái Bình'),
('987654321', 'Vũ Văn Kiên', '1997-03-29', 'Nam', '01636428930', 'vukien29031997@gmail.com', 'Duy Nhất - Vũ Thư - Thái Bình');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongbao`
--

CREATE TABLE `thongbao` (
  `Id` int(11) NOT NULL,
  `Ngay` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TieuDe` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `NoiDung` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CmtNhanVien` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thongbao`
--

INSERT INTO `thongbao` (`Id`, `Ngay`, `TieuDe`, `NoiDung`, `CmtNhanVien`) VALUES
(1, '2018-06-05 16:28:06', 'Hello', 'Ngày tháng vẫn êm đêm trôi, nó cuốn đi theo biết bao điều trong cuộc sống.Tuổi thơ của mỗi con người chúng ta cũng được thời gian lần lượt cuốn theo. Cái khoảng trời thơ ấu đó giờ đây chỉ còn là những hoài niệm,nhưng,những cảm xúc của tuổi thơ thì cứ mới như là hôm qua !Những xúc cảm đầu đời về tự nhiên,về con người sao mà tràn trề, mạnh mẽ quá !\r\nTuổi thơ của tôi đã trải dài trên vùng đất cư xá, trải dài trên con sông Sài Gòn. Con sông ôm chặc cù lao Thanh Đa và ngày ngày dòng sông hiền hoà,nhẫn nại chăm mớm cho vùng đất này biết bao là phù sa,nắng vàng gió mát.Nó như bầu sữa căng đầy của người mẹ thấm truyền từng giọt vào cơ thể của con.Từ đó, Thanh Đa xanh mượt mà,màu xanh đầy ăm ắp sinh lực.Dòng sông Thanh Đa đã tràn vào tuổi thơ của tôi,của bạn bè tôi…nó chảy vào tận cùng trong từng nỗi nhớ.', '004197001988'),
(2, '2018-06-05 16:01:31', 'Hello,Vũ Văn Kiên.I\'m student Hust', 'Ngày tháng vẫn êm đêm trôi, nó cuốn đi theo biết bao điều trong cuộc sống.Tuổi thơ của mỗi con người chúng ta cũng được thời gian lần lượt cuốn theo. Cái khoảng trời thơ ấu đó giờ đây chỉ còn là những hoài niệm,nhưng,những cảm xúc của tuổi thơ thì cứ mới như là hôm qua !Những xúc cảm đầu đời về tự nhiên,về con người sao mà tràn trề, mạnh mẽ quá !\r\nTuổi thơ của tôi đã trải dài trên vùng đất cư xá, trải dài trên con sông Sài Gòn. Con sông ôm chặc cù lao Thanh Đa và ngày ngày dòng sông hiền hoà,nhẫn nại chăm mớm cho vùng đất này biết bao là phù sa,nắng vàng gió mát.Nó như bầu sữa căng đầy của người mẹ thấm truyền từng giọt vào cơ thể của con.Từ đó, Thanh Đa xanh mượt mà,màu xanh đầy ăm ắp sinh lực.Dòng sông Thanh Đa đã tràn vào tuổi thơ của tôi,của bạn bè tôi…nó chảy vào tận cùng trong từng nỗi nhớ.', '004197001988'),
(5, '2018-06-06 17:04:09', 'My name is Kiên', 'I love my school', '004197001988');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vitrido`
--

CREATE TABLE `vitrido` (
  `ViTriDoXe` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `BienSoXe` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `ThoiGianDo` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `vitrido`
--

INSERT INTO `vitrido` (`ViTriDoXe`, `BienSoXe`, `ThoiGianDo`) VALUES
('A1.1', '16M1-1234', '9h00-9h30'),
('A1.1', '16M2-2903', '13h00-13h30'),
('A1.1', '17M3-4567', '15h00-15h30'),
('A1.3', '16M1-1234', '15h00-15h30'),
('A1.3', '17M2-1234', '9h00-9h30'),
('A1.4', '16M2-3456', '8h30-9h00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xe`
--

CREATE TABLE `xe` (
  `Id` int(11) NOT NULL,
  `BienSoXe` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `CmtNhaXe` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `SoGhe` int(11) NOT NULL,
  `GiaVe` double NOT NULL,
  `LoTrinh` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `LichTrinh` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `xe`
--

INSERT INTO `xe` (`Id`, `BienSoXe`, `CmtNhaXe`, `SoGhe`, `GiaVe`, `LoTrinh`, `LichTrinh`) VALUES
(1, '17M2-1234', '123456789', 50, 75000, 'Thái Bình-Hà Nội', '6h00-9h00'),
(2, '17M2-1234', '123456789', 50, 75000, 'Hà Nội - Thái Bình', '9h30-12h30'),
(3, '16M2-3456', '022096111666', 50, 50000, 'Hà Nam - Hà Nội', '7h00-8h30'),
(4, '16M2-3456', '022096111666', 50, 50000, 'Hà Nội - Hà Nam', '9h00-10h30'),
(5, '17M3-4567', '123456987', 50, 75000, 'Thái Bình - Hà Nội', '12h30-15h00'),
(6, '17M3-4567', '123456987', 50, 75000, 'Hà Nội - Thái Bình', '15h30-18h0'),
(7, '16M1-1234', '034097001988', 50, 60000, 'Nam Định - Hà Nội', '7h00-9h00'),
(8, '16M1-1234', '034097001988', 50, 60000, 'Hà Nội - Nam Định', '9h30-11h30'),
(9, '16M1-1234', '034097001988', 50, 60000, 'Nam Định - Hà Nội', '13h00-15h00'),
(10, '16M1-1234', '034097001988', 50, 60000, 'Hà Nội - Nam Định', '15h30-17h30'),
(11, '16M2-2903', '022096111666', 30, 60000, 'Hà Nam - Hà Nội', '11h30-13h00'),
(12, '16M2-2903', '022096111666', 30, 60000, 'Hà Nội - Hà Nam', '13h30-15h0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xechocapnhat`
--

CREATE TABLE `xechocapnhat` (
  `Id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `BienSoXe` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `CmtChuXe` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `GiaVe` double NOT NULL,
  `LichTrinh` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `NgayGui` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TinhTrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `xechocapnhat`
--

INSERT INTO `xechocapnhat` (`Id`, `BienSoXe`, `CmtChuXe`, `GiaVe`, `LichTrinh`, `NgayGui`, `TinhTrang`) VALUES
('11-12', '16M2-2903', '022096111666 ', 50000, '13h30-15h00', '2018-06-13 16:38:39', 1),
('7-8', '16M1-1234', '034097001988 ', 50000, '7h00-9h00', '2018-06-13 16:38:44', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Cmt`);

--
-- Chỉ mục cho bảng `baixe`
--
ALTER TABLE `baixe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_vitridoxe` (`ViTriDoXe`);

--
-- Chỉ mục cho bảng `chiphi`
--
ALTER TABLE `chiphi`
  ADD PRIMARY KEY (`CmtChuXe`,`Ngay`),
  ADD KEY `3_idx` (`CmtAdmin`);

--
-- Chỉ mục cho bảng `chitietchiphi`
--
ALTER TABLE `chitietchiphi`
  ADD PRIMARY KEY (`CmtChuXe`,`BienSoXe`,`Ngay`);

--
-- Chỉ mục cho bảng `chitietchuyenxe`
--
ALTER TABLE `chitietchuyenxe`
  ADD PRIMARY KEY (`MaChuyenXe`,`MaHanhKhach`),
  ADD KEY `MaHanhKhach` (`MaHanhKhach`);

--
-- Chỉ mục cho bảng `chuxe`
--
ALTER TABLE `chuxe`
  ADD PRIMARY KEY (`Cmt`),
  ADD KEY `20_idx` (`NhaXe`),
  ADD KEY `200_idx` (`NhaXe`);

--
-- Chỉ mục cho bảng `chuyenxe`
--
ALTER TABLE `chuyenxe`
  ADD PRIMARY KEY (`MaChuyenXe`),
  ADD KEY `6_idx` (`BienSoXe`);

--
-- Chỉ mục cho bảng `hanhkhach`
--
ALTER TABLE `hanhkhach`
  ADD PRIMARY KEY (`Cmt`);

--
-- Chỉ mục cho bảng `thongbao`
--
ALTER TABLE `thongbao`
  ADD PRIMARY KEY (`Id`,`Ngay`,`TieuDe`),
  ADD KEY `1_idx` (`CmtNhanVien`);

--
-- Chỉ mục cho bảng `vitrido`
--
ALTER TABLE `vitrido`
  ADD PRIMARY KEY (`ViTriDoXe`,`BienSoXe`,`ThoiGianDo`),
  ADD KEY `fk_bsx_idx` (`BienSoXe`),
  ADD KEY `fk_1111_idx` (`BienSoXe`);

--
-- Chỉ mục cho bảng `xe`
--
ALTER TABLE `xe`
  ADD PRIMARY KEY (`Id`,`BienSoXe`,`CmtNhaXe`),
  ADD KEY `fk_bsx_idx` (`BienSoXe`),
  ADD KEY `1234567` (`CmtNhaXe`);

--
-- Chỉ mục cho bảng `xechocapnhat`
--
ALTER TABLE `xechocapnhat`
  ADD PRIMARY KEY (`Id`,`BienSoXe`),
  ADD KEY `fk_cmtNhaXe1_idx` (`BienSoXe`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chiphi`
--
ALTER TABLE `chiphi`
  ADD CONSTRAINT `3` FOREIGN KEY (`CmtAdmin`) REFERENCES `admin` (`Cmt`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `chiphi_ibfk_1` FOREIGN KEY (`CmtChuXe`) REFERENCES `chuxe` (`Cmt`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `chitietchiphi`
--
ALTER TABLE `chitietchiphi`
  ADD CONSTRAINT `sdf` FOREIGN KEY (`CmtChuXe`) REFERENCES `chuxe` (`Cmt`);

--
-- Các ràng buộc cho bảng `chitietchuyenxe`
--
ALTER TABLE `chitietchuyenxe`
  ADD CONSTRAINT `111222` FOREIGN KEY (`MaChuyenXe`) REFERENCES `chuyenxe` (`MaChuyenXe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `chitietchuyenxe_ibfk_1` FOREIGN KEY (`MaHanhKhach`) REFERENCES `hanhkhach` (`Cmt`);

--
-- Các ràng buộc cho bảng `chuyenxe`
--
ALTER TABLE `chuyenxe`
  ADD CONSTRAINT `123456789` FOREIGN KEY (`BienSoXe`) REFERENCES `xe` (`BienSoXe`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `thongbao`
--
ALTER TABLE `thongbao`
  ADD CONSTRAINT `fk_thongbao` FOREIGN KEY (`CmtNhanVien`) REFERENCES `admin` (`Cmt`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `vitrido`
--
ALTER TABLE `vitrido`
  ADD CONSTRAINT `fk_1111` FOREIGN KEY (`BienSoXe`) REFERENCES `xe` (`BienSoXe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_vitridoxe` FOREIGN KEY (`ViTriDoXe`) REFERENCES `baixe` (`ViTriDoXe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `xe`
--
ALTER TABLE `xe`
  ADD CONSTRAINT `1234567` FOREIGN KEY (`CmtNhaXe`) REFERENCES `chuxe` (`Cmt`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `xechocapnhat`
--
ALTER TABLE `xechocapnhat`
  ADD CONSTRAINT `fk_cmtNhaXe1` FOREIGN KEY (`BienSoXe`) REFERENCES `xe` (`BienSoXe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
