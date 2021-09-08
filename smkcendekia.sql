-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 08, 2021 at 07:30 PM
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
  `idabsen` int(10) NOT NULL,
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
('ADM001', '3411181108', 'Chania Ayu Lestari', 'chabs', 'chabs', 0),
('ADM002', '3411181123', 'Rafi Aziizi Muchtar', 'rafi', 'rafi', 0);

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `nip` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `alamat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`nip`, `nk`, `nama`, `email`, `jeniskelamin`, `notlp`, `alamat`) VALUES
('3411181108', '10TITL1', 'Chania Ayu Lestari', 'chaniaayulestari30@gmail.com', 'Perempuan', '081313490101', 'cimahi'),
('3411181123', '10TITL2', 'Rafi Aziizi Muchtar', 'rafiaziizi69@gmail.com', 'Laki-Laki', '081282407414', 'Cikampek');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `nk` varchar(15) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `namakelas` varchar(15) NOT NULL,
  `angkatan` int(5) NOT NULL,
  `jurusan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`nk`, `nis`, `nip`, `namakelas`, `angkatan`, `jurusan`) VALUES
('10TITL1', '3411181103', '3411181108', 'TITL1', 10, 'TITL'),
('10TITL2', '100', '3411181123', 'TITL2', 10, 'TITL'),
('10TITL3', '-', '3411181132', 'TITL3', 10, 'TITL');

-- --------------------------------------------------------

--
-- Table structure for table `rfidlog`
--

CREATE TABLE `rfidlog` (
  `no` int(255) NOT NULL,
  `rfid` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rfidlog`
--

INSERT INTO `rfidlog` (`no`, `rfid`) VALUES
(1, '55552527001'),
(2, '55552527001'),
(3, '672888867376'),
(4, '672888867376'),
(5, '55552527001'),
(6, '672888867376'),
(7, '672888867376'),
(8, '14756353782'),
(9, '1044418927102'),
(10, '1044418927102'),
(11, '14756353782'),
(12, '14756353782'),
(13, '672888867376'),
(14, '51871758571'),
(15, '1044418927102'),
(16, '1044418927102'),
(17, '1044418927102'),
(18, '1044418927102'),
(19, '1044418927102'),
(20, '672888867376'),
(21, '672888867376'),
(22, '672888867376'),
(23, '672888867376'),
(24, '672888867376'),
(25, '55552527001'),
(26, '51871758571'),
(27, '51871758571');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(15) NOT NULL,
  `idrfid` varchar(20) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `namaortu` varchar(15) NOT NULL,
  `noortu` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `idrfid`, `nk`, `nama`, `alamat`, `jeniskelamin`, `email`, `nip`, `notlp`, `namaortu`, `noortu`) VALUES
('100', '672888867376', '10TITL2', 'Rafi Aziizi Muchtar', 'Cimahi', 'Laki-Laki', 'rafiaziizi69@gmail.com', '3411181123', '081282407414', 'Sukino', '08174925763');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absen`
--
ALTER TABLE `absen`
  ADD PRIMARY KEY (`idabsen`),
  ADD KEY `nk` (`nk`),
  ADD KEY `nis` (`nis`);

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
  ADD PRIMARY KEY (`nip`),
  ADD KEY `nk` (`nk`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`nk`),
  ADD KEY `nis` (`nis`),
  ADD KEY `nip` (`nip`);

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
  ADD KEY `nip` (`nip`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `rfidlog`
--
ALTER TABLE `rfidlog`
  MODIFY `no` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `absen`
--
ALTER TABLE `absen`
  ADD CONSTRAINT `absen_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `guru` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `guru`
--
ALTER TABLE `guru`
  ADD CONSTRAINT `guru_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `kelas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `kelas` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
