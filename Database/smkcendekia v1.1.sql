-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 30, 2021 at 06:06 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `idadmin` varchar(15) NOT NULL,
  `nip` varchar(15) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `level` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`idadmin`, `nip`, `nama`, `username`, `password`, `level`) VALUES
('ADM001', '3411181123', 'Rafi Aziizi Muchtar', 'rafi', 'rafi', 0),
('ADM002', '3411181122', 'Chania Ayu', 'chaniaaa', 'chan', 0);

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
('3411181121', 'Bagian IT', 'Rizko Ayundra', 'rizko@gmail.com', 'Laki-Laki', '0811', 'Karawang'),
('3411181122', 'Bagian IT', 'Chania Ayu', 'chaniaayu@gmail.com', 'Perempuan', '0811', 'Cikalong'),
('3411181123', 'Kepala Sekolah', 'Rafi Aziizi Muchtar', 'rafi69@gmail.com', 'Laki-Laki', '081282407414', 'Cimahi');

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
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `nk` varchar(15) NOT NULL,
  `idwalikelas` varchar(15) DEFAULT NULL,
  `namakelas` varchar(15) NOT NULL,
  `angkatan` int(5) NOT NULL,
  `jurusan` varchar(15) NOT NULL,
  `jl` int(3) NOT NULL,
  `jp` int(3) NOT NULL,
  `js` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`nk`, `idwalikelas`, `namakelas`, `angkatan`, `jurusan`, `jl`, `jp`, `js`) VALUES
('10TITL2', 'WLS001', '2', 10, 'TITL', 0, 0, 0),
('10TITL3', 'WLS001', '3', 10, 'TITL', 1, 0, 1),
('10TITL9', 'WLS003', '9', 10, 'TITL', 0, 0, 0),
('12TITL1', 'WLS002', 'TITL1', 12, 'TITL', 1, 1, 2);

--
-- Triggers `kelas`
--
DELIMITER $$
CREATE TRIGGER `updatedatakelas` AFTER UPDATE ON `kelas` FOR EACH ROW IF (old.idwalikelas!=new.idwalikelas) THEN
	UPDATE siswa
	SET    idwalikelas = new.idwalikelas,
		nk = new.nk
	WHERE  nk=new.nk;
END IF
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `lapabsen`
--

CREATE TABLE `lapabsen` (
  `idlapabsen` int(15) NOT NULL,
  `idabsen` int(255) NOT NULL,
  `idwalikelas` varchar(15) NOT NULL,
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
  `idmnkelas` int(10) NOT NULL,
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
('1044418927102', 'NIS102', 'Chania'),
('51871758571', 'NIS103', 'Roki'),
('672888867376', 'NIS103', 'Rafi Aziizi Muchtar');

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
(108, '51871758571');

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
  `noortu` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `idrfid`, `nk`, `idwalikelas`, `nama`, `alamat`, `jeniskelamin`, `email`, `notlp`, `namaortu`, `noortu`) VALUES
('NIS101', '672888867376', '12TITL1', 'WLS002', 'Rafi Aziizi Muchtar', 'Cimahi', 'Perempuan', 'rafi@gmail.com', '081282407414', 'Sukino', '08174925763'),
('NIS102', '1044418927102', '10TITL3', 'WLS002', 'Chania', 'Cikalong', 'Laki-Laki', 'chania@gmail.com', '083421', 'Koko', '0844'),
('NIS103', '51871758571', '12TITL1', 'WLS002', 'Roki', 'Cikampek', 'Laki-Laki', 'Roki@gmail.com', '0811233', 'Kaka', '033213');

--
-- Triggers `siswa`
--
DELIMITER $$
CREATE TRIGGER `deletejumlahjk` AFTER DELETE ON `siswa` FOR EACH ROW IF(jeniskelamin='Laki-Laki') THEN
    UPDATE kelas
    SET jl= kelas.jl - 1,js=js-1
    WHERE kelas.nk=old.nk;
ELSE
    UPDATE kelas
    SET jp= kelas.jp - 1,js=js-1
    WHERE kelas.nk=old.nk;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatejk` AFTER UPDATE ON `siswa` FOR EACH ROW IF (new.nis!=old.nis AND new.jeniskelamin!=old.jeniskelamin) THEN
	IF(new.jeniskelamin='Laki-Laki') THEN
    	UPDATE kelas
    	SET jl= jl
    	WHERE kelas.nk=old.nk;
        UPDATE kelas
    	SET jl= jl +1,jp=jp-1
    	WHERE kelas.nk=old.nk;
	ELSE
    	UPDATE kelas
    	SET jp= jp
    	WHERE kelas.nk=old.nk;
        UPDATE kelas
    	SET jp= jp +1,jl=jl-1
    	WHERE kelas.nk=old.nk;      
	END IF;
ELSEIF (new.nis!=old.nis) THEN
	IF(old.jeniskelamin='Laki-Laki') THEN
    	UPDATE kelas
    	SET jl= jl
    	WHERE kelas.nk=old.nk;
	ELSE
    	UPDATE kelas
    	SET jp= jp
    	WHERE kelas.nk=old.nk;      
	END IF;
ELSEIF (new.jeniskelamin!=old.jeniskelamin) THEN
	IF(new.jeniskelamin='Laki-Laki') THEN
    	UPDATE kelas
    	SET jl= jl +1,jp=jp-1
    	WHERE kelas.nk=old.nk;
	ELSE
    	UPDATE kelas
    	SET jp= jp +1,jl=jl-1
    	WHERE kelas.nk=old.nk;      
	END IF;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatejumlahjk` AFTER INSERT ON `siswa` FOR EACH ROW IF(new.jeniskelamin='Laki-Laki') THEN
    UPDATE kelas
    SET jl= kelas.jl + 1,js=kelas.js+1
    WHERE kelas.nk=new.nk;
ELSE
    UPDATE kelas
    SET jp= kelas.jp + 1,js=kelas.js+1
    WHERE kelas.nk=new.nk;
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
  `email` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `alamat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `walikelas`
--

INSERT INTO `walikelas` (`idwalikelas`, `nip`, `nama`, `email`, `jeniskelamin`, `notlp`, `alamat`) VALUES
('WLS001', '3411181123', 'Rafi Aziizi Muchtar', 'rafi69@gmail.com', 'Laki-Laki', '081282407414', 'Cimahi'),
('WLS002', '3411181122', 'Chania Ayu', 'chaniaayu@gmail.com', 'Perempuan', '0811', 'Cikalong'),
('WLS003', '3411181121', 'Rizko Ayundra', 'rizko@gmail.com', 'Laki-Laki', '0811', 'Karawang');

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
  ADD KEY `idwalas` (`idwalikelas`),
  ADD KEY `idabsen` (`idabsen`),
  ADD KEY `nk` (`nk`);

--
-- Indexes for table `mnkelas`
--
ALTER TABLE `mnkelas`
  ADD PRIMARY KEY (`idmnkelas`),
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
-- AUTO_INCREMENT for table `absen`
--
ALTER TABLE `absen`
  MODIFY `idabsen` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `lapabsen`
--
ALTER TABLE `lapabsen`
  MODIFY `idlapabsen` int(15) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `mnkelas`
--
ALTER TABLE `mnkelas`
  MODIFY `idmnkelas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `rfidlog`
--
ALTER TABLE `rfidlog`
  MODIFY `no` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `absen`
--
ALTER TABLE `absen`
  ADD CONSTRAINT `absen_ibfk_1` FOREIGN KEY (`idrfid`) REFERENCES `rfid` (`idrfid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absen_ibfk_3` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absen_ibfk_4` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Constraints for table `mnkelas`
--
ALTER TABLE `mnkelas`
  ADD CONSTRAINT `mnkelas_ibfk_2` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mnkelas_ibfk_3` FOREIGN KEY (`idwalikelas`) REFERENCES `walikelas` (`idwalikelas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mnkelas_ibfk_4` FOREIGN KEY (`nk`) REFERENCES `kelas` (`nk`) ON DELETE CASCADE ON UPDATE CASCADE;

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
