-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 14, 2021 at 09:08 PM
-- Server version: 10.3.29-MariaDB-0+deb10u1
-- PHP Version: 7.3.29-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smkcendekia`
--

-- --------------------------------------------------------

--
-- Table structure for table `absen`
--

CREATE TABLE `absen` (
  `idabsen` int(255) NOT NULL,
  `idrfid` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `tanggal` date NOT NULL,
  `jam` time NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `absen`
--
DELIMITER $$
CREATE TRIGGER `deletelapabsen` AFTER DELETE ON `absen` FOR EACH ROW IF(old.status='Hadir') THEN
    UPDATE lapabsen
    SET hadir= hadir - 1
    WHERE lapabsen.nis=old.nis;
ELSEIF(old.status='Terlambat') THEN
	UPDATE lapabsen
    SET terlambat= terlambat - 1
    WHERE lapabsen.nis=old.nis;
ELSEIF(old.status='Alpha') THEN
	UPDATE lapabsen
    SET alpha= alpha - 1
    WHERE lapabsen.nis=old.nis;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `inserttolapabsen` AFTER INSERT ON `absen` FOR EACH ROW IF(new.status='Hadir') THEN
    UPDATE lapabsen
    SET hadir= hadir + 1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Terlambat') THEN
	UPDATE lapabsen
    SET terlambat= terlambat + 1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Alpha') THEN
	UPDATE lapabsen
    SET alpha= alpha + 1, keterangan=CASE WHEN alpha>=3 AND alpha<5 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;                 
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatelapabsen` AFTER UPDATE ON `absen` FOR EACH ROW IF(new.status='Hadir' AND old.status='Sakit') THEN
    UPDATE lapabsen
    SET hadir= hadir + 1,sakit=sakit-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Hadir' AND old.status='Alpha') THEN
    UPDATE lapabsen
    SET hadir= hadir + 1,alpha=alpha-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Hadir' AND old.status='Izin') THEN
    UPDATE lapabsen
    SET hadir= hadir + 1,izin=izin-1
    WHERE lapabsen.nis=new.nis;
/* UPDATE SAKIT */
ELSEIF(new.status='Sakit' AND old.status='Hadir') THEN
	UPDATE lapabsen
    SET sakit= sakit + 1,hadir = hadir-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Sakit' AND old.status='Izin') THEN
	UPDATE lapabsen
    SET sakit= sakit + 1,izin = izin-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Sakit' AND old.status='Alpha') THEN
	UPDATE lapabsen
    SET sakit= sakit + 1,alpha = alpha-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
/* UPDATE IZIN */
ELSEIF(new.status='Izin' AND old.status='Hadir') THEN
	UPDATE lapabsen
    SET izin= izin + 1,hadir = hadir-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Izin' AND old.status='Sakit') THEN
	UPDATE lapabsen
    SET izin= izin + 1,sakit = sakit-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Izin' AND old.status='Alpha') THEN
	UPDATE lapabsen
    SET izin= izin + 1,alpha = alpha-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
/* UPDATE ALPHA */
ELSEIF(new.status='Alpha' AND old.status='Hadir') THEN
	UPDATE lapabsen
    SET alpha= alpha + 1,hadir = hadir-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Alpha' AND old.status='Sakit') THEN
	UPDATE lapabsen
    SET alpha= alpha + 1,sakit = sakit-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Alpha' AND old.status='Izin') THEN
	UPDATE lapabsen
    SET alpha= alpha + 1,izin = izin-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
/* UPDATE TERLAMBAT */
ELSEIF(new.status='Hadir' AND old.status='Terlambat') THEN
	UPDATE lapabsen
    SET hadir= hadir + 1,terlambat = terlambat-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Sakit' AND old.status='Terlambat') THEN
	UPDATE lapabsen
    SET sakit= sakit + 1,terlambat = terlambat-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Izin' AND old.status='Terlambat') THEN
	UPDATE lapabsen
    SET izin= izin + 1,terlambat = terlambat-1
    WHERE lapabsen.nis=new.nis;
ELSEIF(new.status='Alpha' AND old.status='Terlambat') THEN
	UPDATE lapabsen
    SET alpha= alpha + 1,terlambat = terlambat-1,keterangan=CASE WHEN alpha >= 3 AND alpha<6 THEN 'SP1'
                             WHEN alpha = 6 THEN 'SP2'
                             WHEN alpha = 9 THEN 'SP3'
                             WHEN alpha <3 THEN NULL
                        END
    WHERE lapabsen.nis=new.nis;
END IF
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `idadmin` varchar(15) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` int(2) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`idadmin`, `nip`, `nama`, `username`, `password`, `level`, `status`) VALUES
('ADM002', '3411181123', 'Rafi Aziizi M', 'rafi', 'c4ca4238a0b923820dcc509a6f75849b', 0, 'Aktif'),
('ADM003', '3411181121', 'Rizko Ayundra', 'rizko', '1', 0, 'Aktif'),
('ADM005', '321001', 'Dandi Rusdani, S.Pd.', 'dandir', 'c4ca4238a0b923820dcc509a6f75849b', 0, 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `nip` varchar(15) NOT NULL,
  `jabatan` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`nip`, `jabatan`, `nama`, `email`, `jeniskelamin`, `notlp`, `alamat`, `status`) VALUES
('31', 'OB', 'Yoman', 'yo@gmail.com', 'Laki-Laki', '081282408414', 'weqdsadsa', 'Aktif'),
('321001', 'Bagian IT', 'Dandi Rusdani, S.Pd.', 'dandirusdani@gmail.com', 'Laki-Laki', '081282407414', 'Blok Carik Rt 06 Rw 01 Ds. Batujajar Barat Kec. Batujajar Kab. Bandung Barat', 'Aktif'),
('3411181121', 'Bagian IT', 'Rizko Ayundra', 'rizko@gmail.com', 'Laki-Laki', '0811', 'Karawang', 'Aktif'),
('3411181122', 'Bagian IT', 'Chania Ayu', 'chaniaayu@gmail.com', 'Perempuan', '08112', 'Cikalong', 'Pasif'),
('3411181123', 'Guru BK', 'Rafi Aziizi M', 'rafi69@gmail.com', 'Laki-Laki', '081282407414', 'Cimahi', 'Aktif');

--
-- Triggers `guru`
--
DELIMITER $$
CREATE TRIGGER `updatedataadmin` AFTER UPDATE ON `guru` FOR EACH ROW UPDATE admin
SET    admin.nama = new.nama
WHERE  nip=new.nip
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatedataguru` AFTER UPDATE ON `guru` FOR EACH ROW UPDATE walikelas
SET    walikelas.nama = new.nama,
walikelas.email = new.email,
walikelas.jeniskelamin = new.jeniskelamin,
walikelas.notlp = new.notlp,
walikelas.alamat = new.alamat
WHERE  nip=new.nip
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `historyabsen`
--

CREATE TABLE `historyabsen` (
  `idabsen` int(255) NOT NULL,
  `idrfid` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `tanggal` date NOT NULL,
  `jam` time NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `historyabsen`
--

INSERT INTO `historyabsen` (`idabsen`, `idrfid`, `nk`, `nis`, `nama`, `tanggal`, `jam`, `status`) VALUES
(1, '14756353782', '11TITL1', '2021.10.001', 'Rafi Aziizi Muchtar', '2021-04-04', '06:47:02', 'Hadir'),
(2, '14756353782', '11TITL1', '2021.10.001', 'Rafi Aziizi Muchtar', '2021-11-14', '06:15:03', 'Sakit');

-- --------------------------------------------------------

--
-- Table structure for table `historylapabsen`
--

CREATE TABLE `historylapabsen` (
  `idlapabsen` varchar(11) NOT NULL,
  `idwalikelas` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `hadir` int(3) NOT NULL,
  `sakit` int(3) NOT NULL,
  `izin` int(3) NOT NULL,
  `alpha` int(3) NOT NULL,
  `terlambat` int(3) NOT NULL,
  `status` varchar(15) NOT NULL,
  `keterangan` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `historylapabsen`
--

INSERT INTO `historylapabsen` (`idlapabsen`, `idwalikelas`, `nis`, `nk`, `nama`, `hadir`, `sakit`, `izin`, `alpha`, `terlambat`, `status`, `keterangan`) VALUES
('LAP0000001', 'WLS002', '2021', '12TITL4', 'Rafi Aziizi Muchtar', 0, 0, 0, 0, 0, 'Pasif', NULL),
('LAP0000001', 'WLS002', '20211131', '12TITL4', 'Raaaff', 1, 0, 0, 0, 0, 'Aktif', NULL),
('LAP0000001', 'WLS003', '2021.10.001', '11TITL1', 'Rafi Aziizi Muchtar', 0, 1, 0, 0, 0, 'Aktif', NULL),
('LAP0000001', 'WLS003', '2021.10.001', '11TITL1', 'Rafi Aziizi Muchtar', 0, 0, 0, 0, 0, 'Pasif', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `historysiswa`
--

CREATE TABLE `historysiswa` (
  `nis` varchar(15) NOT NULL,
  `idrfid` varchar(15) NOT NULL,
  `nk` varchar(15) DEFAULT NULL,
  `idwalikelas` varchar(15) DEFAULT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `namaortu` varchar(15) NOT NULL,
  `noortu` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `historysiswa`
--

INSERT INTO `historysiswa` (`nis`, `idrfid`, `nk`, `idwalikelas`, `nama`, `alamat`, `jeniskelamin`, `email`, `notlp`, `namaortu`, `noortu`, `status`) VALUES
('2021.10.001', '14756353782', '11TITL1', 'WLS003', 'Rafi Aziizi Muchtar', 'Kp.bojong asih rt 6 rw 10 desa cikalong kec. Cikalong wetan 40556', 'Laki-Laki', 'rafiaziizi69@gmail.com', '081282407414', 'Tatang', '08174925763', 'Pasif');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `nk` varchar(15) NOT NULL,
  `idwalikelas` varchar(15) DEFAULT NULL,
  `namakelas` varchar(15) NOT NULL,
  `angkatan` int(5) NOT NULL,
  `jurusan` varchar(15) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `tahunajaran` varchar(10) NOT NULL,
  `jl` int(3) NOT NULL,
  `jp` int(3) NOT NULL,
  `js` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`nk`, `idwalikelas`, `namakelas`, `angkatan`, `jurusan`, `semester`, `tahunajaran`, `jl`, `jp`, `js`) VALUES
('11TITL1', 'WLS003', 'TITL1', 11, 'TITL', 'Ganjil', '2021/2022', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `lapabsen`
--

CREATE TABLE `lapabsen` (
  `idlapabsen` varchar(11) NOT NULL,
  `idwalikelas` varchar(15) DEFAULT NULL,
  `nis` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `hadir` int(3) NOT NULL,
  `sakit` int(3) NOT NULL,
  `izin` int(3) NOT NULL,
  `alpha` int(3) NOT NULL,
  `terlambat` int(3) NOT NULL,
  `status` varchar(15) NOT NULL,
  `keterangan` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rfid`
--

CREATE TABLE `rfid` (
  `idrfid` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rfidlog`
--

CREATE TABLE `rfidlog` (
  `no` int(255) NOT NULL,
  `idrfid` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rfidlog`
--

INSERT INTO `rfidlog` (`no`, `idrfid`) VALUES
(1, '1044418927102'),
(2, '1044418927102'),
(3, '1044418927102'),
(4, '1044418927102'),
(5, '1044418927102'),
(6, '1044418927102'),
(7, '426100135628'),
(8, '426100135628'),
(9, '55552527001'),
(10, '55552527001'),
(11, '672888867376'),
(12, '672888867376'),
(13, '426100135628'),
(14, '426100135628'),
(15, '426100135628'),
(16, '289154865906'),
(17, '289154865906'),
(18, '14756353782'),
(19, '14756353782'),
(20, '14756353782'),
(21, '14756353782'),
(22, '14756353782'),
(23, '672888867376'),
(24, '672888867376'),
(25, '672888867376'),
(26, '55552527001'),
(27, '55552527001'),
(28, '55552527001'),
(29, '55552527001'),
(30, '14756353782'),
(31, '14756353782'),
(32, '51871758571'),
(33, '51871758571'),
(34, '51871758571'),
(35, '51871758571'),
(36, '672888867376'),
(37, '672888867376'),
(38, '672888867376'),
(39, '14756353782'),
(40, '14756353782'),
(41, '55552527001'),
(42, '55552527001'),
(43, '55552527001'),
(44, '55552527001'),
(45, '55552527001'),
(46, '55552527001'),
(47, '51871758571'),
(48, '51871758571'),
(49, '51871758571'),
(50, '51871758571'),
(51, '51871758571'),
(52, '51871758571'),
(53, '51871758571'),
(54, '1044418927102'),
(55, '1044418927102'),
(56, '1044418927102'),
(57, '1044418927102'),
(79, '51871758571'),
(80, '51871758571'),
(81, '51871758571'),
(82, '51871758571'),
(83, '672888867376'),
(84, '672888867376'),
(85, '51871758571'),
(86, '51871758571'),
(87, '51871758571'),
(88, '672888867376'),
(89, '672888867376'),
(90, '672888867376'),
(91, '672888867376'),
(92, '51871758571'),
(93, '51871758571'),
(94, '51871758571'),
(95, '51871758571'),
(96, '51871758571'),
(97, '51871758571'),
(98, '51871758571'),
(99, '51871758571'),
(100, '51871758571'),
(101, '51871758571'),
(102, '51871758571'),
(103, '51871758571'),
(104, '51871758571'),
(105, '51871758571'),
(106, '51871758571'),
(107, '51871758571'),
(108, '51871758571'),
(109, '632745563716'),
(110, '359445304937'),
(111, '359445304937'),
(112, '359445304937'),
(113, '359445304937'),
(114, '359445304937'),
(115, '632745563716'),
(116, '632745563716'),
(117, '632745563716'),
(118, '632745563716'),
(119, '632745563716'),
(120, '672888867376'),
(121, '672888867376'),
(122, '632745563716'),
(123, '632745563716'),
(124, '51871758571'),
(125, '51871758571'),
(126, '51871758571'),
(127, '51871758571'),
(128, '632745563716'),
(129, '632745563716'),
(130, '672888867376'),
(131, '672888867376'),
(132, '359445304937'),
(133, '359445304937'),
(134, '359445304937'),
(135, '359445304937'),
(136, '359445304937'),
(137, '359445304937'),
(138, '359445304937'),
(139, '672888867376'),
(140, '672888867376'),
(141, '51871758571'),
(142, '51871758571'),
(143, '51871758571'),
(144, '51871758571'),
(145, '51871758571'),
(146, '672888867376'),
(147, '672888867376'),
(148, '672888867376'),
(149, '359445304937'),
(150, '359445304937'),
(151, '632745563716'),
(152, '632745563716'),
(153, '632745563716'),
(154, '359445304937'),
(155, '359445304937'),
(156, '632745563716'),
(157, '632745563716'),
(158, '359445304937'),
(159, '359445304937'),
(160, '359445304937'),
(161, '51871758571'),
(162, '51871758571'),
(163, '672888867376'),
(164, '672888867376'),
(165, '672888867376'),
(166, '672888867376'),
(167, '672888867376'),
(168, '51871758571'),
(169, '51871758571'),
(170, '672888867376'),
(171, '672888867376'),
(172, '672888867376'),
(173, '672888867376'),
(174, '672888867376'),
(175, '51871758571'),
(176, '51871758571'),
(177, '359445304937'),
(178, '359445304937'),
(179, '359445304937'),
(180, '359445304937'),
(181, '672888867376'),
(182, '359445304937'),
(183, '359445304937'),
(184, '672888867376'),
(185, '672888867376'),
(186, '51871758571'),
(187, '51871758571'),
(188, '359445304937'),
(189, '359445304937'),
(190, '632745563716'),
(191, '632745563716'),
(192, '359445304937'),
(193, '359445304937'),
(194, '632745563716'),
(195, '632745563716'),
(196, '359445304937'),
(197, '359445304937'),
(198, '632745563716'),
(199, '632745563716'),
(200, '632745563716'),
(201, '632745563716'),
(202, '632745563716'),
(203, '632745563716'),
(204, '632745563716'),
(205, '632745563716'),
(206, '632745563716'),
(207, '632745563716'),
(208, '632745563716'),
(209, '359445304937'),
(210, '359445304937'),
(211, '359445304937'),
(212, '359445304937'),
(213, '359445304937'),
(214, '359445304937'),
(215, '359445304937'),
(216, '359445304937'),
(217, '359445304937'),
(218, '359445304937'),
(219, '359445304937'),
(220, '359445304937'),
(221, '359445304937'),
(222, '359445304937'),
(223, '632745563716'),
(224, '632745563716'),
(225, '359445304937'),
(226, '359445304937'),
(227, '359445304937'),
(228, '359445304937'),
(229, '632745563716'),
(230, '632745563716'),
(231, '672888867376'),
(232, '672888867376'),
(233, '51871758571'),
(234, '51871758571'),
(235, '51871758571'),
(236, '51871758571'),
(237, '51871758571'),
(238, '51871758571'),
(239, '51871758571'),
(240, '51871758571'),
(241, '51871758571'),
(242, '51871758571'),
(243, '51871758571'),
(244, '51871758571'),
(245, '51871758571'),
(246, '51871758571'),
(247, '51871758571'),
(248, '51871758571'),
(249, '51871758571'),
(250, '51871758571'),
(251, '632745563716'),
(252, '632745563716'),
(253, '632745563716'),
(254, '51871758571'),
(255, '566390232631'),
(256, '51871758571'),
(257, '632745563716'),
(258, '566390232631'),
(259, '566390232631'),
(260, '632745563716'),
(261, '359445304937'),
(262, '14756353782'),
(263, '14756353782'),
(264, '359445304937'),
(265, '632745563716'),
(266, '566390232631'),
(267, '14756353782'),
(268, '566390232631'),
(269, '566390232631'),
(270, '14756353782');

-- --------------------------------------------------------

--
-- Table structure for table `semester`
--

CREATE TABLE `semester` (
  `idsemester` varchar(15) NOT NULL,
  `nama` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL,
  `tahunajaran` varchar(15) NOT NULL,
  `tanggalpertama` date NOT NULL,
  `tanggalterakhir` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `semester`
--

INSERT INTO `semester` (`idsemester`, `nama`, `status`, `tahunajaran`, `tanggalpertama`, `tanggalterakhir`) VALUES
('SMT005', 'Semester 2', 'Genap', '2021/2022', '2021-05-19', '2022-01-19');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(15) NOT NULL,
  `idrfid` varchar(15) NOT NULL,
  `nk` varchar(15) DEFAULT NULL,
  `idwalikelas` varchar(15) DEFAULT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `namaortu` varchar(15) NOT NULL,
  `noortu` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `siswa`
--
DELIMITER $$
CREATE TRIGGER `movelapabsen` BEFORE DELETE ON `siswa` FOR EACH ROW INSERT INTO historylapabsen
SELECT * FROM lapabsen
WHERE nis=old.nis
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `movesiswa` BEFORE DELETE ON `siswa` FOR EACH ROW INSERT INTO historysiswa
SELECT * FROM siswa
where nis=old.nis
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tambahjk` AFTER INSERT ON `siswa` FOR EACH ROW IF (new.jeniskelamin='Laki-Laki') THEN
    UPDATE kelas
    SET jl= jl+1,js=js+1
    WHERE kelas.nk=new.nk;
ELSEIF (new.jeniskelamin='Perempuan') THEN
    UPDATE kelas
    SET jp= jp+1,js=js+1
    WHERE kelas.nk=new.nk;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatedatalapabsen` AFTER UPDATE ON `siswa` FOR EACH ROW IF (new.status!=old.status) THEN
	IF (new.status='Aktif') THEN
    	UPDATE lapabsen
        set status=new.status
        WHERE nis=new.nis;
        UPDATE rfid
        set status=new.status
        WHERE nis=new.nis;
    ELSEIF (new.status='Pasif') THEN
    	UPDATE lapabsen
        SET status=new.status
        WHERE nis=new.nis;
        UPDATE rfid
        set status=new.status
        WHERE nis=new.nis;
    END IF;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatejknis` AFTER UPDATE ON `siswa` FOR EACH ROW IF (new.nis!=old.nis) THEN
	IF(old.jeniskelamin='Laki-Laki') THEN
    	UPDATE kelas
    	SET jl= jl
    	WHERE kelas.nk=old.nk;
	ELSE
    	UPDATE kelas
    	SET jp= jp
    	WHERE kelas.nk=old.nk;      
	END IF;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatejumlahsiswa` AFTER UPDATE ON `siswa` FOR EACH ROW IF (new.status!=old.status) THEN
	IF(new.status='Aktif') THEN
    	IF (new.jeniskelamin!=old.jeniskelamin) THEN
        	IF (new.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jl= jl+1,js=js+1
    			WHERE kelas.nk=old.nk;
            ELSEIF (new.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jp= jp+1,js=js+1
    			WHERE kelas.nk=old.nk;
         	END IF;
        ELSEIF (new.jeniskelamin=old.jeniskelamin) THEN
        	IF (old.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jl= jl+1,js=js+1
    			WHERE kelas.nk=old.nk;
            ELSEIF (old.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jp= jp+1,js=js+1
    			WHERE kelas.nk=old.nk;
         	END IF;
        END IF;
	ELSEIF(new.status='Pasif')THEN
    	IF (new.jeniskelamin!=old.jeniskelamin) THEN
        	IF (new.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jp= jp-1,js=js-1
    			WHERE kelas.nk=old.nk;
            ELSEIF (new.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jl= jl-1,js=js-1
    			WHERE kelas.nk=old.nk;
         	END IF;
        ELSEIF (new.jeniskelamin=old.jeniskelamin) THEN
        	IF (old.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jl= jl-1,js=js-1
    			WHERE kelas.nk=old.nk;
            ELSEIF (old.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jp= jp-1,js=js-1
    			WHERE kelas.nk=old.nk;
         	END IF;
        END IF;      
	END IF;
ELSEIF (new.status=old.status) THEN
	IF(old.status='Aktif') THEN
    	IF (new.jeniskelamin!=old.jeniskelamin) THEN
        	IF (new.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jl= jl+1,jp=jp-1
    			WHERE kelas.nk=old.nk;
            ELSEIF (new.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jp= jp+1,jl=jl-1
    			WHERE kelas.nk=old.nk;
         	END IF;
        ELSEIF (new.jeniskelamin=old.jeniskelamin) THEN
        	IF (old.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jl= jl
    			WHERE kelas.nk=old.nk;
            ELSEIF (old.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jp= jp
    			WHERE kelas.nk=old.nk;
         	END IF;
        END IF;
	ELSEIF(old.status='Pasif')THEN
    	IF (new.jeniskelamin!=old.jeniskelamin) THEN
        	IF (new.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jp= jp,js=js
    			WHERE kelas.nk=old.nk;
            ELSEIF (new.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jl= jl,js=js
    			WHERE kelas.nk=old.nk;
         	END IF;
        ELSEIF (new.jeniskelamin=old.jeniskelamin) THEN
        	IF (old.jeniskelamin='Laki-Laki') THEN
    			UPDATE kelas
    			SET jl= jl,js=js
    			WHERE kelas.nk=old.nk;
            ELSEIF (old.jeniskelamin='Perempuan') THEN
            	UPDATE kelas
    			SET jp= jp,js=js
    			WHERE kelas.nk=old.nk;
         	END IF;
        END IF;      
	END IF;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatenamaabsen` AFTER UPDATE ON `siswa` FOR EACH ROW UPDATE absen
set nama=new.nama
WHERE nis=new.nis
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatenamahisabsen` AFTER UPDATE ON `siswa` FOR EACH ROW UPDATE historyabsen
set nama=new.nama
WHERE nis=new.nis
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatenamahislapabsen` AFTER UPDATE ON `siswa` FOR EACH ROW UPDATE historylapabsen
set nama=new.nama
WHERE nis=new.nis
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatenamalapabsen` AFTER UPDATE ON `siswa` FOR EACH ROW UPDATE lapabsen
set nama=new.nama
WHERE nis=new.nis
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updaterfid` AFTER UPDATE ON `siswa` FOR EACH ROW IF(new.idrfid!=old.idrfid AND new.nis!=old.nis AND new.nama!=old.nama) THEN
    UPDATE rfid
    SET idrfid=new.idrfid,nis=new.nis,nama=new.nama
    WHERE nis=old.nis;
ELSEIF(new.idrfid!=old.idrfid AND new.nis!=old.nis) THEN
    UPDATE rfid
    SET idrfid=new.idrfid,nis=new.nis
    WHERE nis=old.nis;
ELSEIF(new.idrfid!=old.idrfid AND new.nama!=old.nama) THEN
    UPDATE rfid
    SET idrfid=new.idrfid,nama=new.nama
    WHERE nis=old.nis;
ELSEIF(new.nis!=old.nis AND new.nama!=old.nama) THEN
    UPDATE rfid
    SET nis=new.nis,nama=new.nama
    WHERE nis=old.nis;
ELSEIF(new.idrfid!=old.idrfid) THEN
    UPDATE rfid
    SET idrfid=new.idrfid
    WHERE nis=old.nis;
ELSEIF(new.nis!=old.nis) THEN
    UPDATE rfid
    SET nis=new.nis
    WHERE nis=old.nis;
ELSEIF(new.nama!=old.nama) THEN
    UPDATE rfid
    SET nama=new.nama
    WHERE nis=old.nis;
END IF
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `walikelas`
--

CREATE TABLE `walikelas` (
  `idwalikelas` varchar(15) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `nk` varchar(15) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `walikelas`
--

INSERT INTO `walikelas` (`idwalikelas`, `nip`, `nama`, `nk`, `email`, `jeniskelamin`, `notlp`, `alamat`, `status`) VALUES
('WLS001', '3411181123', 'Rafi Aziizi M', NULL, 'rafi69@gmail.com', 'Laki-Laki', '081282407414', 'Cimahi', 'Pasif'),
('WLS002', '3411181122', 'Chania Ayu', NULL, 'chaniaayu@gmail.com', 'Perempuan', '08112', 'Cikalong', 'Pasif'),
('WLS003', '3411181121', 'Rizko Ayundra', '11TITL1', 'rizko@gmail.com', 'Laki-Laki', '0811', 'Karawang', 'Aktif'),
('WLS005', '31', 'Yoman', NULL, 'yo@gmail.com', 'Laki-Laki', '081282408414', 'weqdsadsa', 'Pasif'),
('WLS006', '321001', 'Dandi Rusdani, S.Pd.', NULL, 'dandirusdani@gmail.com', 'Laki-Laki', '081282407414', 'Blok Carik Rt 06 Rw 01 Ds. Batujajar Barat Kec. Batujajar Kab. Bandung Barat', 'Aktif');

--
-- Triggers `walikelas`
--
DELIMITER $$
CREATE TRIGGER `updatewalaskelas` AFTER UPDATE ON `walikelas` FOR EACH ROW IF (new.status='Pasif') THEN
	UPDATE kelas
    SET idwalikelas=NULL 
    WHERE kelas.idwalikelas=new.idwalikelas;
    UPDATE siswa
    SET idwalikelas=NULL 
    WHERE siswa.idwalikelas=new.idwalikelas;
    UPDATE lapabsen
    SET idwalikelas=NULL
    WHERE lapabsen.idwalikelas=new.idwalikelas;
END IF
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absen`
--
ALTER TABLE `absen`
  ADD PRIMARY KEY (`idabsen`),
  ADD KEY `nk` (`nk`),
  ADD KEY `nis` (`nis`),
  ADD KEY `idrfid` (`idrfid`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idadmin`),
  ADD KEY `nip` (`nip`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `historyabsen`
--
ALTER TABLE `historyabsen`
  ADD PRIMARY KEY (`idabsen`);

--
-- Indexes for table `historysiswa`
--
ALTER TABLE `historysiswa`
  ADD PRIMARY KEY (`nis`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`nk`),
  ADD KEY `idwalikelas` (`idwalikelas`);

--
-- Indexes for table `lapabsen`
--
ALTER TABLE `lapabsen`
  ADD PRIMARY KEY (`idlapabsen`),
  ADD KEY `nis` (`nis`,`nk`),
  ADD KEY `idwalas` (`idwalikelas`),
  ADD KEY `nk` (`nk`);

--
-- Indexes for table `rfid`
--
ALTER TABLE `rfid`
  ADD PRIMARY KEY (`idrfid`),
  ADD KEY `nis` (`nis`);

--
-- Indexes for table `rfidlog`
--
ALTER TABLE `rfidlog`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `semester`
--
ALTER TABLE `semester`
  ADD PRIMARY KEY (`idsemester`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`),
  ADD KEY `idrfid` (`idrfid`),
  ADD KEY `nk` (`nk`),
  ADD KEY `idwalikelas` (`idwalikelas`);

--
-- Indexes for table `walikelas`
--
ALTER TABLE `walikelas`
  ADD PRIMARY KEY (`idwalikelas`),
  ADD KEY `nip` (`nip`),
  ADD KEY `nk` (`nk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absen`
--
ALTER TABLE `absen`
  MODIFY `idabsen` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `historyabsen`
--
ALTER TABLE `historyabsen`
  MODIFY `idabsen` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `rfidlog`
--
ALTER TABLE `rfidlog`
  MODIFY `no` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=271;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `absen`
--
ALTER TABLE `absen`
  ADD CONSTRAINT `absen_ibfk_1` FOREIGN KEY (`idrfid`) REFERENCES `rfid` (`idrfid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absen_ibfk_3` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absen_ibfk_4` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `guru` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kelas`
--
ALTER TABLE `kelas`
  ADD CONSTRAINT `kelas_ibfk_1` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `lapabsen`
--
ALTER TABLE `lapabsen`
  ADD CONSTRAINT `lapabsen_ibfk_1` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lapabsen_ibfk_2` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lapabsen_ibfk_3` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`idrfid`) REFERENCES `rfid` (`idrfid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `siswa_ibfk_2` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `siswa_ibfk_3` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `walikelas`
--
ALTER TABLE `walikelas`
  ADD CONSTRAINT `walikelas_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `guru` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `walikelas_ibfk_2` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;