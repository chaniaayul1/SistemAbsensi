-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Agu 2021 pada 12.26
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
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
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `idadmin` varchar(8) NOT NULL,
  `nip` varchar(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`idadmin`, `nip`, `username`, `nama`, `password`) VALUES
('ADM01', '3411181108', 'Chabs', 'Chania Ayu Lestari', 'temanhidup1'),
(' ADM02', '341118123', ' teman123', ' Rafi Aziizi', ' raf'),
('ADM03', '3411181103', 'emakcangtip', 'Nurul Innayah', 'Emak'),
('ADM04', '3411181109', 'nes', 'Nesa', 'tes123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `guru`
--

CREATE TABLE `guru` (
  `nip` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `email` varchar(45) NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `guru`
--

INSERT INTO `guru` (`nip`, `nk`, `nama`, `email`, `jeniskelamin`, `notlp`, `alamat`) VALUES
('3411181108', 'TBO01', 'Chabs', 'cal30@gmail.com', 'perempuan', '087822209880', 'disini'),
('3411181123', 'BK01', 'Rafi Aziizi', 'rafiaziizi@gmail.com', 'Laki-Laki', '087823321818', 'cimohay');

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(15) NOT NULL,
  `nk` varchar(15) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `alamat` text NOT NULL,
  `jeniskelamin` varchar(15) NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nip` int(15) NOT NULL,
  `namaortu` varchar(40) NOT NULL,
  `noortu` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`nis`, `nk`, `nama`, `alamat`, `jeniskelamin`, `notlp`, `email`, `nip`, `namaortu`, `noortu`) VALUES
('3411181108', 'TBO', 'Chan', 'cimhai', 'perempuan', '087822209880', 'chani@gmail.com', 54321, 'ood', '087823321919');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`nip`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`),
  ADD KEY `nk` (`nk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
