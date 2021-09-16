-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 09, 2021 at 09:01 PM
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
  `idabsen` varchar(15) NOT NULL,
  `idrfid` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `tanggal` date NOT NULL,
  `jam` time NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `idadmin` varchar(10) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(15) NOT NULL,
  `level` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`idadmin`, `nip`, `nama`, `username`, `password`, `level`) VALUES
('ADM001', '3411181123', 'Rafi Aziizi Muchtar', 'rafi', 'rafi', 0);

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
  `alamat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`nip`, `jabatan`, `nama`, `email`, `jeniskelamin`, `notlp`, `alamat`) VALUES
('3411181122', 'Bagian IT', 'Chachan', 'cha@gmail.com', 'Perempuan', '083211223344', 'Cikampek'),
('3411181123', 'Kepala Sekolah', 'Rafi Aziizi Muchtar', 'rafi@gmail.com', 'Laki-Laki', '081282407414', 'Cimahis'),
('3411181124', 'Bagian IT', 'Rizko', 'rizko@gmail.com', 'Laki-Laki', '081282274925', 'Medan');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `nk` varchar(15) NOT NULL,
  `idwalikelas` varchar(15) NOT NULL,
  `namakelas` varchar(15) NOT NULL,
  `angkatan` int(5) NOT NULL,
  `jurusan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`nk`, `idwalikelas`, `namakelas`, `angkatan`, `jurusan`) VALUES
('10TITL1', 'WLS001', '1', 10, 'TITL'),
('10TITL2', 'WLS002', '2', 10, 'TITL');

-- --------------------------------------------------------

--
-- Table structure for table `lapabsen`
--

CREATE TABLE `lapabsen` (
  `idlapabsen` varchar(15) NOT NULL,
  `idabsen` varchar(15) NOT NULL,
  `idwalas` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `hadir` int(3) NOT NULL,
  `sakit` int(3) NOT NULL,
  `izin` int(3) NOT NULL,
  `alpha` int(3) NOT NULL,
  `terlambat` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mnkelas`
--

CREATE TABLE `mnkelas` (
  `id_mnkelas` int(10) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `idwalikelas` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rfid`
--

CREATE TABLE `rfid` (
  `idrfid` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rfid`
--

INSERT INTO `rfid` (`idrfid`, `nis`, `nama`) VALUES
('1044418927102', 'NIS102', 'Chanias'),
('55552527001', 'NIS101', 'Rokis');

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
(58, '672888867376'),
(59, '672888867376'),
(60, '672888867376'),
(61, '672888867376'),
(62, '51871758571'),
(63, '51871758571'),
(64, '51871758571'),
(65, '51871758571'),
(66, '51871758571'),
(67, '51871758571'),
(68, '55552527001'),
(69, '55552527001'),
(70, '55552527001'),
(71, '51871758571'),
(72, '51871758571'),
(73, '51871758571'),
(74, '51871758571'),
(75, '51871758571'),
(76, '51871758571'),
(77, '51871758571'),
(78, '51871758571');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(15) NOT NULL,
  `idrfid` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `idwalikelas` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `namaortu` varchar(15) NOT NULL,
  `noortu` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `idrfid`, `nk`, `idwalikelas`, `nama`, `alamat`, `jeniskelamin`, `email`, `notlp`, `namaortu`, `noortu`) VALUES
('NIS101', '55552527001', '10TITL2', 'WLS002', 'Rokis', 'Mama', 'Laki-Laki', 'ras', '0833', 'Doglo', '012'),
('NIS102', '1044418927102', '10TITL2', 'WLS002', 'Chanias', 'Karawang', 'Laki-Laki', 'chania@gmail.com', '0813', 'Emil', '0814');

-- --------------------------------------------------------

--
-- Table structure for table `walikelas`
--

CREATE TABLE `walikelas` (
  `idwalikelas` varchar(15) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `alamat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `walikelas`
--

INSERT INTO `walikelas` (`idwalikelas`, `nip`, `nama`, `email`, `jeniskelamin`, `notlp`, `alamat`) VALUES
('WLS001', '3411181123', 'Rafi Aziizi Muchtar', 'rafi@gmail.com', 'Laki-Laki', '081282407414', 'Cimahi'),
('WLS002', '3411181122', 'Roki Firmansyah', 'roki@gmail.com', 'Laki-Laki', '0811', 'Cikampek'),
('WLS003', '3411181124', 'Rizko F', 'rizko@gmail.com', 'Laki-Laki', '0877', 'Medan');

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
  ADD KEY `idwalas` (`idwalas`),
  ADD KEY `idabsen` (`idabsen`);

--
-- Indexes for table `mnkelas`
--
ALTER TABLE `mnkelas`
  ADD PRIMARY KEY (`id_mnkelas`),
  ADD KEY `nk` (`nk`),
  ADD KEY `nip` (`idwalikelas`),
  ADD KEY `nis` (`nis`);

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
  ADD KEY `nip` (`nip`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mnkelas`
--
ALTER TABLE `mnkelas`
  MODIFY `id_mnkelas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `rfidlog`
--
ALTER TABLE `rfidlog`
  MODIFY `no` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `absen`
--
ALTER TABLE `absen`
  ADD CONSTRAINT `absen_ibfk_1` FOREIGN KEY (`idrfid`) REFERENCES `rfid` (`idrfid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absen_ibfk_2` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absen_ibfk_3` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `guru` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kelas`
--
ALTER TABLE `kelas`
  ADD CONSTRAINT `kelas_ibfk_1` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mnkelas`
--
ALTER TABLE `mnkelas`
  ADD CONSTRAINT `mnkelas_ibfk_1` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mnkelas_ibfk_2` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mnkelas_ibfk_3` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`idrfid`) REFERENCES `rfid` (`idrfid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `siswa_ibfk_2` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `siswa_ibfk_3` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `walikelas`
--
ALTER TABLE `walikelas`
  ADD CONSTRAINT `walikelas_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `guru` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;