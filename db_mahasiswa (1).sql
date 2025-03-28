-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2025 at 10:28 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `jenjang` varchar(10) NOT NULL DEFAULT 'S1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `jenjang`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'S1'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'S1'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'S2'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'D4'),
(5, '2202046', 'Nurainun', 'Perempuan', 'S1'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'S2'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'D4'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'S1'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'S2'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'D4'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'S1'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'S2'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'D4'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'S1'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'S2'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'D4'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'S1'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'S2'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'D4'),
(20, '2204343', 'Meiva Labibah Putri', 'Perempuan', 'S1'),
(21, '2301093', 'Marco', 'Laki-laki', 'S2'),
(22, '2301091', 'Michael', 'Laki-laki', 'S2'),
(23, '2301092', 'Ks', 'Laki-laki', 'S2');

--
-- Triggers `mahasiswa`
--
DELIMITER $$
CREATE TRIGGER `before_insert_mahasiswa` BEFORE INSERT ON `mahasiswa` FOR EACH ROW BEGIN
    DECLARE new_id INT;
    SET new_id = (SELECT MIN(t1.id + 1) FROM mahasiswa t1 LEFT JOIN mahasiswa t2 ON t1.id + 1 = t2.id WHERE t2.id IS NULL);
    IF new_id IS NOT NULL THEN
        SET NEW.id = new_id;
    END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
