-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 18, 2021 at 06:10 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cendekia`
--
CREATE DATABASE IF NOT EXISTS `cendekia` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cendekia`;

-- --------------------------------------------------------

--
-- Table structure for table `alat`
--

CREATE TABLE `alat` (
  `kode_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `spesifiaksi` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `kondisi_b` int(11) NOT NULL,
  `kondisi_rr` int(11) NOT NULL,
  `kondisi_rb` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alat`
--

INSERT INTO `alat` (`kode_barang`, `nama_barang`, `merk`, `spesifiaksi`, `quantity`, `kondisi_b`, `kondisi_rr`, `kondisi_rb`) VALUES
('A-01.01.01', 'MCB 1 Fasa', 'Shukaku SKU-899', '4 A', 32, 31, 1, 0),
('A-01.01.02', 'MCB 1 Fasa', 'Shukaku SKU-899', '6 A', 8, 5, 3, 0),
('A-01.01.03', 'MCB 1 Fasa', 'Shukaku SKU-899', '10 A', 7, 5, 2, 0),
('A-01.02.01', 'MCB 1 Fasa', 'Schneider Domae', '4 A', 4, 1, 3, 0),
('A-01.02.02', 'MCB 1 Fasa', 'Schneider Domae', '6 A', 10, 6, 4, 0),
('A-01.02.03', 'MCB 1 Fasa', 'Schneider Domae', '25 A', 1, 1, 0, 0),
('A-01.02.04', 'MCB 1 Fasa', 'Schneider Domae', '32 A', 2, 2, 0, 0),
('A-01.03.01', 'MCB 1 Fasa', 'Merlin Gerin', '6 A', 2, 2, 0, 0),
('A-01.04.01', 'MCB 1 Fasa', 'Hager', '6 A', 2, 2, 0, 0),
('A-01.04.02', 'MCB 1 Fasa', 'Hager', '10 A', 1, 1, 0, 0),
('A-01.04.03', 'MCB 1 Fasa', 'Hager', '16 A', 1, 1, 0, 0),
('A-01.05.01', 'MCB 1 Fasa', 'Broco', '6 A', 3, 2, 1, 0),
('A-01.05.02', 'MCB 1 Fasa', 'Broco', '20 A', 1, 1, 0, 0),
('A-01.06.01', 'MCB 1 Fasa', 'LS - BKN', '16 A', 1, 1, 0, 0),
('A-01.07.01', 'MCB 1 Fasa', 'ABB', '6 A', 2, 2, 0, 0),
('A-02.01.01', 'MCB 3 Fasa', 'Shukaku SKU-899', '16 A', 5, 5, 0, 0),
('A-02.01.02', 'MCB 3 Fasa', 'Shukaku SKU-899', '20 A', 4, 4, 0, 0),
('A-02.02.01', 'MCB 3 Fasa', 'Chint NXB', '10 A', 5, 5, 0, 0),
('A-02.03.01', 'MCB 3 Fasa', 'Chint eB', '16 A', 6, 6, 0, 0),
('A-02.04.01', 'MCB 3 Fasa', 'Schneider Domae', '16 A', 1, 1, 0, 0),
('A-02.04.02', 'MCB 3 Fasa', 'Schneider Domae', '32 A', 2, 2, 0, 0),
('A-02.05.01', 'MCB 3 Fasa', 'LS - BKN-B', '16 A', 2, 2, 0, 0),
('A-02.06.01', 'MCB 3 Fasa', 'Merlin Gerin NC45A', '10 A', 1, 0, 1, 0),
('A-03.01.01', 'ELCB 1 Fasa', 'Chint-D247 LE', '32 A', 5, 5, 0, 0),
('A-03.02.01', 'ELCB 1 Fasa', 'ABB FH202', '25 A', 1, 1, 0, 0),
('A-04.01.01', 'ELCB 3 Fasa', 'Schneider DCKB4', '25 A', 1, 1, 0, 0),
('A-04.02.01', 'ELCB 3 Fasa', 'Chint-D257 LE', '25 A', 5, 5, 0, 0),
('A-05.01.01', 'Relay', 'Omron MK2P-I', 'AC 220V', 13, 2, 1, 0),
('A-05.02.01', 'Relay', 'Fort MK2P', 'AC 220V', 3, 3, 0, 0),
('A-05.03.01', 'Relay', 'KTN MK2P-I', 'AC 220V', 1, 1, 0, 0),
('A-06.01.01', 'PLC', 'Omron CP1E', 'E30SDR-A', 1, 1, 0, 0),
('A-06.01.02', 'PLC', 'Omron CP1E', 'E20SDR-A', 4, 4, 0, 0),
('A-07.01.01', 'Power Supply PLC', 'Omron S8FS', 'CO3524J', 4, 4, 0, 0),
('A-08.01.01', 'Smart Relay', 'Schneider Zelio SR3', 'PACK2FU', 1, 1, 0, 0),
('A-09.01.01', 'HMI', 'Autonics LP-S070', 'T9D6-C5T', 1, 1, 0, 0),
('A-10.01.01', 'Time Delay Relay', 'Omron H3CR', 'A8', 5, 5, 0, 0),
('A-10.02.01', 'Time Delay Relay', 'Autonics AT', 'AT8N', 6, 6, 0, 0),
('A-11.01.01', 'Floatless Level Switch', 'OTTO ', 'AFS-GR', 1, 1, 0, 0),
('A-11.02.01', 'Floatless Level Switch', 'Klar Stern ', '61F-G', 1, 1, 0, 0),
('A-12.01.01', 'Timer Switch Analog', 'Theben SUL 181 d', '181 d', 1, 1, 0, 0),
('A-13.01.01', 'Kontaktor', 'Chint NXC-09', 'AC-3 20A', 1, 1, 0, 0),
('A-13.02.01', 'Kontaktor', 'Schneider LC 1 D09', 'AC-3 25A', 9, 9, 0, 0),
('A-13.02.02', 'Kontaktor', 'Schneider LC 1 K09', 'AC-3 20A', 1, 1, 0, 0),
('A-13.03.01', 'Kontaktor', 'Mitsubishi S-T25', 'AC-3 30A', 0, 0, 0, 0),
('A-13.03.02', 'Kontaktor', 'Mitsubishi S-N10', 'AC-3 20A', 1, 1, 0, 0),
('A-14.01.01', 'TOR', 'Chint NXR-25', '4-6 A', 20, 20, 0, 0),
('A-14.02.01', 'TOR', 'Schneider LRD 08', '2.5-4 A', 0, 0, 4, 1),
('A-14.03.01', 'TOR', 'Mitsubishi TH T25', '4-6 A', 2, 2, 0, 0),
('A-14.03.02', 'TOR', 'Mitsubishi TH N20 KP', '9-13 A', 1, 1, 0, 0),
('A-15.01.01', 'Kontak Bantu', 'Chint AX 3X', '2NO / 2NC', 27, 27, 0, 0),
('B-01.01.01', 'Pilot Lamp', 'Shemco - AD22', 'Merah', 20, 19, 1, 0),
('B-01.01.02', 'Pilot Lamp', 'Shemco - AD23', 'Kuning', 44, 43, 1, 0),
('B-01.01.03', 'Pilot Lamp', 'Shemco - AD24', 'Hijau', 18, 16, 2, 0),
('B-01.02.01', 'Pilot Lamp', 'Fort', 'Merah', 21, 20, 1, 0),
('B-01.02.02', 'Pilot Lamp', 'Fort', 'Kuning', 4, 3, 0, 1),
('B-01.02.03', 'Pilot Lamp', 'Fort', 'Hijau', 19, 18, 0, 1),
('B-02.01.01', 'Push Button', 'Hanyoung CRF - F25M1R', 'Merah', 35, 34, 1, 0),
('B-02.01.02', 'Push Button', 'Hanyoung CRF - F25M1G', 'Hijau', 52, 52, 0, 0),
('B-02.02.01', 'Push Button', 'Fort LAX5 BW', 'Merah', 2, 2, 0, 0),
('B-02.02.02', 'Push Button', 'Fort LAX5 BW', 'Hijau', 1, 0, 1, 0),
('B-03.01.01', 'Emergency', 'Hanyoung CRE - 25R1R', 'NO/NC', 26, 26, 0, 0),
('B-03.02.01', 'Emergency', 'Shemco XB5 - A5542', 'NC', 7, 7, 0, 0),
('B-04.01.01', 'Selektor', 'Fort LA 115-A', 'SPST', 0, 0, 0, 0),
('B-05.01.01', 'Kontrol Box', 'Ewig BX 22 Yellow', '1 Hole', 15, 15, 0, 0),
('B-05.01.02', 'Kontrol Box', 'Ewig BX 22 Yellow', '3 Hole', 16, 16, 0, 0),
('B-05.01.03', 'Kontrol Box', 'Ewig BX 22 Yellow', '4 Hole', 15, 15, 0, 0),
('B-06.01.01', 'Limit Switch', 'Ewig Z-15GO (1307)', 'NO/NC', 6, 6, 0, 0),
('B-07.01.01', 'Amperee Meter', 'Ewig - BE 50', '5 A', 9, 9, 0, 0),
('B-07.01.02', 'Amperee Meter', 'Ewig - BE 50', '10 A', 9, 9, 0, 0),
('B-07.01.03', 'Amperee Meter', 'Ewig - BE 50', '20 A', 9, 9, 0, 0),
('B-07.01.04', 'Amperee Meter', 'Ewig - BE 50', '30 A', 9, 9, 0, 0),
('B-08.01.01', 'Volt Meter', 'Ewig - BE 50', '300 V AC', 5, 5, 0, 0),
('B-08.01.02', 'Volt Meter', 'Ewig - BE 50', '500 V AC', 15, 15, 0, 0),
('B-09.01.01', 'Cam Starter', 'SKYO QS 5 30 A', 'TPST', 0, 5, 5, 0),
('B-09.01.02', 'Cam Starter', 'FATO QS 5 15 A', 'TPST', 0, 5, 5, 0),
('B-10.01.01', 'Soket Relay', 'Fort PF083 A', '8 Hole', 30, 27, 1, 2),
('C-01.01.01', 'Kotak Sambung', 'PVC', '3 Cabang', 160, 152, 8, 0),
('C-01.01.02', 'Kotak Sambung', 'PVC', '4 Cabang', 96, 94, 2, 0),
('C-02.01.01', 'Saklar Tunggal', 'Broco', 'In Bow', 18, 18, 0, 0),
('C-02.01.02', 'Saklar Tunggal', 'Broco', 'Out Bow', 28, 25, 2, 1),
('C-03.01.01', 'Saklar Seri', 'Broco', 'In Bow', 22, 21, 1, 0),
('C-03.01.02', 'Saklar Seri', 'Broco', 'Out Bow', 46, 46, 0, 0),
('C-04.01.01', 'Saklar Tunggal', 'Broco', 'In Bow', 10, 9, 1, 0),
('C-04.01.02', 'Saklar Tunggal', 'Panasonic', 'In Bow', 43, 43, 0, 0),
('C-05.01.01', 'Dimmer', 'Inlite', 'In Bow', 8, 8, 0, 0),
('C-06.01.01', 'Fiting', 'Panasonic', 'Plafon', 52, 51, 0, 1),
('C-06.02.01', 'Fiting', 'Broco', 'Plafon', 8, 7, 1, 0),
('C-06.03.01', 'Fiting', 'Pentana', 'Plafon', 14, 12, 1, 1),
('C-07.01.01', 'Kotak Kontak', 'Broco', 'In Bow', 18, 18, 0, 0),
('C-07.01.02', 'Kotak Kontak', 'Broco', 'Out Bow', 16, 16, 0, 0),
('C-08.01.01', 'IB Dus', 'Topaim', '-', 67, 67, 0, 0),
('C-09.01.01', 'Lampu TL', 'Broco', '18 W', 18, 18, 0, 0),
('C-09.02.01', 'Lampu TL', 'Inlite', '18 W', 1, 1, 0, 0),
('C-10.01.01', 'Lampu Pijar', 'Philips', '100 W', 5, 5, 0, 0),
('C-10.01.02', 'Lampu Pijar', 'Philips', '60 W', 7, 7, 0, 0),
('C-10.01.03', 'Lampu Pijar', 'Philips', '25 W', 33, 32, 0, 1),
('C-10.01.04', 'Lampu Pijar', 'Philips', '15 W', 7, 7, 0, 0),
('C-10.02.01', 'Lampu Pijar', 'Inlite', 'LED 3 W', 8, 8, 0, 0),
('C-10.02.02', 'Lampu Pijar', 'Inlite', 'LED 9 W', 2, 1, 0, 1),
('C-10.03.01', 'Lampu Pijar', 'Pesona LED', 'LED 3 W', 2, 2, 0, 0),
('C-10.03.02', 'Lampu Pijar', 'Pesona LED', 'LED 20 W', 1, 1, 0, 0),
('C-10.04.01', 'Lampu Pijar', 'Osram', 'Halogen 10 W', 1, 1, 0, 0),
('C-11.01.01', 'LDR', '-', '-', 0, 0, 0, 0),
('C-12.01.01', 'Box Panel', '-', '40x50', 0, 0, 0, 0),
('C-12.01.02', 'Box Panel', '-', '40x60', 0, 0, 0, 0),
('D-01.01.01', 'Obeng Plus', 'Oris', '150 mm', 5, 5, 0, 0),
('D-01.01.02', 'Obeng Plus', 'Oris', '125 mm', 3, 3, 0, 0),
('D-01.01.03', 'Obeng Plus', 'Oris', '100 mm', 6, 6, 0, 0),
('D-01.02.01', 'Obeng Plus', 'X', '100 mm', 5, 5, 0, 0),
('D-01.02.02', 'Obeng Plus', 'X', '150 mm', 1, 1, 0, 0),
('D-01.02.03', 'Obeng Plus', 'X', '200 mm', 1, 1, 0, 0),
('D-02.01.01', 'Obeng Min', 'Oris', '125 mm', 4, 4, 0, 0),
('D-02.01.02', 'Obeng Min', 'Oris', '100 mm', 1, 1, 0, 0),
('D-02.02.01', 'Obeng Min', 'X', '100 mm', 3, 3, 0, 0),
('D-02.02.02', 'Obeng Min', 'X', '150 mm', 2, 2, 0, 0),
('D-02.02.03', 'Obeng Min', 'X', '200 mm', 1, 1, 0, 0),
('D-03.01.01', 'Tang Potong', 'Tekiro', '-', 5, 5, 0, 0),
('D-03.02.01', 'Tang Potong', 'BLTZ', '-', 6, 6, 0, 0),
('D-03.03.01', 'Tang Potong', 'M, Ultrapro', '-', 2, 2, 0, 0),
('D-03.04.01', 'Tang Potong', 'GS', '-', 1, 1, 0, 0),
('D-03.05.01', 'Tang Potong', 'Prohe X', '-', 2, 1, 0, 1),
('D-04.01.01', 'Tang Lancip', 'Tekiro', '-', 7, 7, 0, 0),
('D-04.02.01', 'Tang Lancip', 'GS', '-', 1, 1, 0, 0),
('D-04.03.01', 'Tang Lancip', 'Prohe X', '-', 2, 2, 0, 0),
('D-04.04.01', 'Tang Lancip', '()-NI', '-', 2, 2, 0, 0),
('D-05.01.01', 'Tang Kombinasi', 'BLTZ', '-', 2, 2, 0, 0),
('D-05.02.01', 'Tang Kombinasi', 'IWT', '-', 2, 2, 0, 0),
('D-05.03.01', 'Tang Kombinasi', 'GS', '-', 2, 2, 0, 0),
('D-05.04.01', 'Tang Kombinasi', 'X', '-', 4, 4, 0, 0),
('D-06.01.01', 'Tang Kupas', 'Winner', '-', 5, 5, 0, 0),
('D-06.02.01', 'Tang Kupas', 'Nankai', '-', 4, 3, 0, 1),
('D-06.03.01', 'Tang Kupas', 'Kier', '-', 1, 0, 0, 1),
('D-06.04.01', 'Tang Kupas', 'Grip On', '-', 2, 0, 0, 2),
('D-07.01.01', 'Kunci Pas', 'Sellery', '6-22 inch', 1, 1, 0, 0),
('D-08.01.01', 'Kunci Inggris', 'Heavy', 'Max 8 inch', 1, 1, 0, 0),
('D-09.01.01', 'Palu', 'Junior', '30 cm', 6, 6, 0, 0),
('D-09.01.02', 'Palu', 'Junior', '25 cm', 3, 3, 0, 0),
('D-09.02.01', 'Palu', 'American Tools', '30 cm', 1, 1, 0, 0),
('D-09.03.01', 'Palu', 'X', '25', 3, 3, 0, 0),
('D-09.03.02', 'Palu', 'X', 'Karet', 3, 3, 0, 0),
('D-10.01.01', 'Gergaji', 'Wipro', 'Besi', 1, 1, 0, 0),
('D-10.02.01', 'Gergaji', 'Robtol', 'Kayu', 1, 1, 0, 0),
('D-11.01.01', 'Solder', 'Deko', '60 W', 3, 3, 0, 0),
('D-12.01.01', 'Tangga', 'Sankin', '2 m', 1, 1, 0, 0),
('D-12.02.01', 'Tangga', 'Krisbow / YQJT5L', '1.5 m', 2, 2, 0, 0),
('D-13.01.01', 'Troli', 'Krisbow', '150 kg', 1, 0, 1, 0),
('D-14.01.01', 'Ragum', '-', '-', 4, 4, 0, 0),
('D-15.01.01', 'Meteran', 'Essen', '5 m', 5, 4, 1, 0),
('D-16.01.01', 'Pahat', 'Starex', '200 mm', 4, 4, 0, 0),
('D-16.01.02', 'Pahat', 'Starex', '300 mm', 1, 1, 0, 0),
('D-17.01.01', 'Kikir', 'File', 'Set Lingkaran', 2, 2, 0, 0),
('D-17.01.02', 'Kikir', 'File', 'Segitiga', 1, 1, 0, 0),
('E-01.01.01', 'Drill Press', 'Giant ZJ4113', '250 W', 1, 1, 0, 0),
('E-02.01.01', 'Hand Impact', 'DCA Z1JFF13', '500 W', 1, 1, 0, 0),
('F-01.01.01', 'Multimeter Analog', 'Sanwa / YX360TRD', 'DC 1000V AC 750V', 1, 1, 0, 0),
('F-01.01.02', 'Multimeter Analog', 'Samwa / YX360TRD', 'DC-AC 1000V', 2, 2, 0, 0),
('F-01.02.01', 'Multimeter Analog', 'Heles / YX360TRD', 'DC 1000V AC 750V', 1, 0, 0, 1),
('F-01.03.01', 'Multimeter Analog', 'Rocia / YX360TR-E-L-B', 'DC-AC 1000V', 1, 1, 0, 0),
('F-01.03.02', 'Multimeter Analog', 'Rocia / YX360TRE-B2', 'DC-AC 1000V', 5, 2, 2, 1),
('F-01.03.03', 'Multimeter Analog', 'Rocia / YX360TRN', 'DC-AC 1000V', 1, 0, 1, 0),
('F-01.04.01', 'Multimeter Analog', 'Sunwa / YX360TRN', 'DC-AC 1000V', 1, 0, 0, 1),
('F-01.05.01', 'Multimeter Analog', 'Winner / YX360TRN', 'DC-AC 1000V', 1, 1, 0, 0),
('F-01.05.02', 'Multimeter Analog', 'Winner / YX368TR', 'DC-AC 1000V', 2, 0, 0, 2),
('F-01.06.01', 'Multimeter Analog', 'Sinhwa / DT380 ', 'DC 1000V AC 750V', 2, 0, 2, 0),
('F-02.01.01', 'Tang Amperee', 'Aldo / EM306', '400 A', 4, 4, 0, 0),
('F-02.02.01', 'Tang Amperee', 'Wynns / W0647', '400 A', 1, 1, 0, 0),
('F-02.03.01', 'Tang Amperee', 'X', '1000 A', 1, 1, 0, 0),
('F-03.01.01', 'Megger', 'SEW', '1160 IN', 1, 1, 0, 0),
('F-03.01.02', 'Megger', 'Smart Sensor', 'AR907A+', 1, 1, 0, 0),
('F-04.01.01', 'Earth Tester', 'Kyoritsu', 'KEW4 105A', 1, 1, 0, 0),
('F-05.01.01', 'Vernier Caliper', 'Tricle Brand', '0-100 mm', 1, 1, 0, 0),
('G-01.01.01', 'Helm', 'NSA', 'Kuning', 32, 32, 0, 0),
('G-01.02.01', 'Helm', 'VGARD', 'Kuning', 2, 2, 0, 0),
('G-01.03.01', 'Helm', 'NSA', 'Putih', 5, 5, 0, 0),
('G-01.04.01', 'Helm', 'MSA', 'Putih', 1, 1, 0, 0),
('G-01.05.01', 'Helm', 'B', 'Putih', 1, 1, 0, 0),
('G-02.01.01', 'Sarung Tangan', 'Safeguard', '-', 2, 2, 0, 0),
('G-02.02.01', 'Sarung Tangan', 'Safety Jogger', 'Size 8', 3, 3, 0, 0),
('G-02.02.02', 'Sarung Tangan', 'Safety Jogger', 'Size 9', 2, 2, 0, 0),
('G-02.03.01', 'Sarung Tangan', 'Krisbow', 'Size 10', 1, 0, 1, 0),
('G-02.04.01', 'Sarung Tangan', 'Sataka', 'Karet', 2, 2, 0, 0),
('G-03.01.01', 'Kacamata', 'EN 166 F', 'Kaca Bening', 10, 10, 0, 0),
('G-04.01.01', 'Sepatu', 'Kings', 'Hitam', 1, 1, 0, 0),
('G-05.01.01', 'Kotak P3K', 'Best Choice of', '-', 1, 1, 0, 0),
('G-06.01.01', 'APAR', 'Powder', '1 kg', 1, 0, 1, 0),
('G-07.01.01', 'Safety Vest', 'Gosave', 'Hijau', 3, 3, 0, 0),
('G-07.02.01', 'Safety Vest', 'Konggo', 'Orange', 2, 2, 0, 0),
('G-07.03.01', 'Safety Vest', 'Pyo', 'Orange', 3, 3, 0, 0),
('H-01.01.01', 'Meja', 'Kayu', '245 x 82 x 87', 2, 2, 0, 0),
('H-01.01.02', 'Meja', 'Kayu', '236 x 66 x 76', 3, 3, 0, 0),
('H-01.01.03', 'Meja', 'Kayu', '252 x 130 x 60', 4, 4, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` text NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` enum('Kepala','Administrator') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nama`, `jenis_kelamin`, `tanggal_lahir`, `no_telp`, `alamat`, `email`, `username`, `password`, `level`) VALUES
(1, 'Kepala Lab', 'Laki-Laki', '0000-00-00', '', '', 'user@kalab.com', 'kalab', 'kalab', 'Kepala'),
(2, 'Administrator', 'Perempuan', '0000-00-00', '', '', 'user@admin.com', 'admin', 'admin', 'Administrator');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alat`
--
ALTER TABLE `alat`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
