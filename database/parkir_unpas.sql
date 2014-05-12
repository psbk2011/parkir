-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 03, 2014 at 10:24 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `parkir_unpas`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE IF NOT EXISTS `anggota` (
  `barcode` int(11) NOT NULL,
  `id_anggota` varchar(12) NOT NULL,
  `nama_lengkap` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `kendaraan_operasional`
--

CREATE TABLE IF NOT EXISTS `kendaraan_operasional` (
  `id_kendaraan` int(11) NOT NULL AUTO_INCREMENT,
  `no_polisi` varchar(11) DEFAULT NULL,
  `keterangan_kendaraan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_kendaraan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `idlog` int(11) NOT NULL,
  `kejadian` varchar(30) NOT NULL,
  `deskripsi` text NOT NULL,
  `tanggal_kejadian` datetime NOT NULL,
  PRIMARY KEY (`idlog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `operator`
--

CREATE TABLE IF NOT EXISTS `operator` (
  `idoperator` int(11) NOT NULL AUTO_INCREMENT,
  `nama_lengkap` varchar(30) NOT NULL,
  `nama_pengguna` varchar(12) NOT NULL,
  `kata_sandi` text NOT NULL,
  `alamat` text,
  `nomor_telepon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idoperator`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=103 ;

--
-- Dumping data for table `operator`
--

INSERT INTO `operator` (`idoperator`, `nama_lengkap`, `nama_pengguna`, `kata_sandi`, `alamat`, `nomor_telepon`) VALUES
(102, 'asasssss', 'asas', 'asas', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `opsi`
--

CREATE TABLE IF NOT EXISTS `opsi` (
  `idopsi` int(11) NOT NULL AUTO_INCREMENT,
  `nama_opsi` varchar(12) NOT NULL,
  `nilai_opsi` text NOT NULL,
  PRIMARY KEY (`idopsi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `idtransaksi` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` text,
  `tipe_kendaraan` varchar(5) DEFAULT NULL,
  `waktu_masuk` datetime DEFAULT NULL,
  `gambar_masuk` varchar(225) DEFAULT NULL,
  `waktu_keluar` datetime DEFAULT NULL,
  `gambar_keluar` varchar(225) DEFAULT NULL,
  `total_biaya` int(11) DEFAULT NULL,
  `total_pembayaran` int(11) DEFAULT NULL,
  `no_polisi` varchar(11) DEFAULT NULL,
  `tipe_bayar` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idtransaksi`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idtransaksi`, `barcode`, `tipe_kendaraan`, `waktu_masuk`, `gambar_masuk`, `waktu_keluar`, `gambar_keluar`, `total_biaya`, `total_pembayaran`, `no_polisi`, `tipe_bayar`) VALUES
(6, '-389805304', 'Motor', '2014-05-03 01:16:28', NULL, '2014-05-03 01:17:01', NULL, NULL, 1000, 'B 3554 FBC', 'Cash'),
(7, '383672881', 'Motor', '2014-05-03 01:22:08', NULL, '2014-05-03 01:22:40', NULL, NULL, 1000, 'D 5445 HHI', 'Cash'),
(8, '-556580628	', 'Motor', '2014-05-03 13:08:22', NULL, '2014-05-03 13:23:13', NULL, NULL, 1000, 'B 3554 FBE', 'Cash'),
(9, '-852396486', 'Motor', '2014-05-03 13:37:27', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_anggota`
--

CREATE TABLE IF NOT EXISTS `transaksi_anggota` (
  `id_history` int(11) NOT NULL,
  `id_anggota` int(11) DEFAULT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_history`),
  KEY `transaksi_idx` (`id_transaksi`),
  KEY `anggota_idx` (`id_anggota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi_anggota`
--
ALTER TABLE `transaksi_anggota`
  ADD CONSTRAINT `anggota` FOREIGN KEY (`id_anggota`) REFERENCES `anggota` (`barcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`idtransaksi`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
