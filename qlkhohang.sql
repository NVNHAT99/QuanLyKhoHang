-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 03, 2020 lúc 10:41 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlkhohang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`Id`, `Name`, `Description`, `IsDelete`) VALUES
(1, 'Nuoc Ngot', NULL, b'0'),
(2, 'Nuoc Suoi', NULL, b'0'),
(3, 'Bia', NULL, b'0'),
(4, 'Vai L1', NULL, b'0'),
(5, 'Vai L2', NULL, b'0'),
(6, 'Vai L3', NULL, b'0'),
(7, 'Quat Dien L1', NULL, b'0'),
(8, 'Quat Dien L2', NULL, b'0'),
(9, 'Quat Dien L3', NULL, b'0'),
(10, 'Quat Dien L4', NULL, b'0'),
(11, 'Quat Hoi Nuoc L1', NULL, b'0'),
(12, 'Quat Hoi Nuoc L2', NULL, b'0'),
(13, 'Quat Hoi Nuoc L3', NULL, b'0'),
(14, 've que nao1', 'thang sau ve que di thoi, met moi qua di', b'1'),
(15, 'Nuoc Ngot 123', 'sao gio nang nguoi the nhi, ahhhh chan the', b'1'),
(16, 've nha thoi', 'kiem viec hoac ve nha\n', b'0'),
(17, 've nha 2', '222222', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `companyorderdetails`
--

CREATE TABLE `companyorderdetails` (
  `CompanyOrderId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `ProductUnit` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `Cost` float NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `companyorderdetails`
--

INSERT INTO `companyorderdetails` (`CompanyOrderId`, `ProductId`, `ProductUnit`, `Quantity`, `Cost`, `Description`) VALUES
(10, 1, 'Chai', 123, 12000, ''),
(11, 1, 'Chai', 5, 100500, ''),
(11, 2, 'chainhua', 6, 10000, ''),
(12, 1, 'Chai', 1, 10000, ''),
(12, 2, 'chainhua', 2, 20000, ''),
(13, 1, 'Chai', 200, 100, ''),
(14, 1, 'Chai', 200, 10000, 'chiu'),
(14, 2, 'chainhua', 100, 250000, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `companyorders`
--

CREATE TABLE `companyorders` (
  `Id` int(11) NOT NULL,
  `SupplierId` int(11) NOT NULL,
  `EmployeeId` int(11) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  `VAT` double DEFAULT 0,
  `CK` double DEFAULT 0,
  `TotalMoney` double NOT NULL,
  `HavePaid` double DEFAULT 0,
  `StillOwe` double NOT NULL,
  `Status` bit(1) DEFAULT b'0',
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `companyorders`
--

INSERT INTO `companyorders` (`Id`, `SupplierId`, `EmployeeId`, `TimeStamp`, `VAT`, `CK`, `TotalMoney`, `HavePaid`, `StillOwe`, `Status`, `Description`, `IsDelete`) VALUES
(10, 5, 5, '2020-09-29 00:00:00', 0.05000000074505806, 0.10000000149011612, 1402199.999, 1000000, 402199.99900000007, b'0', '', b'1'),
(11, 2, 2, '2020-10-03 00:00:00', 0.10000000149011612, 0.05000000074505806, 590625, 1.111111111111111e17, -1.1111111111052048e17, b'0', '', b'1'),
(12, 1, 1, '2020-10-03 00:00:00', 0.10000000149011612, 0.05000000074505806, 52500, 52500, 0, b'1', '', b'1'),
(13, 1, 1, '2020-10-03 00:00:00', 0.1, 0.05, 21000, 0, 21000, b'0', 'thu lan 2', b'1'),
(14, 1, 1, '2020-10-03 00:00:00', 0.10000000149011612, 0.05000000074505806, 28350000.02, 28350000, 0, b'1', 'cai gi', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `companypayfororder`
--

CREATE TABLE `companypayfororder` (
  `Id` int(11) NOT NULL,
  `CompanyOrderId` int(11) NOT NULL,
  `EmployeerId` int(11) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  `SupplierId` int(11) NOT NULL,
  `PayMoney` double NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `companypayfororder`
--

INSERT INTO `companypayfororder` (`Id`, `CompanyOrderId`, `EmployeerId`, `TimeStamp`, `SupplierId`, `PayMoney`, `Description`, `IsDelete`) VALUES
(1, 10, 5, '2020-09-30 00:00:00', 5, 1000000, '', b'0'),
(2, 11, 2, '2020-10-03 00:00:00', 2, 1.111111111111111e17, '', b'0'),
(3, 12, 1, '2020-10-03 00:00:00', 1, 52500, 'tra tien mua hang', b'0'),
(4, 14, 1, '2020-10-03 00:00:00', 1, 28350000, 'tra tien mua hang', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customerorderdetails`
--

CREATE TABLE `customerorderdetails` (
  `CustomerOrderId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `ProductUnit` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Cost` double NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `customerorderdetails`
--

INSERT INTO `customerorderdetails` (`CustomerOrderId`, `ProductId`, `ProductUnit`, `Quantity`, `Cost`, `Description`) VALUES
(10, 1, 'Chai', 125, 123000, ''),
(11, 1, 'Chai', 100, 123, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customerorders`
--

CREATE TABLE `customerorders` (
  `Id` int(11) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `EmployeeId` int(11) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  `VAT` float DEFAULT 0,
  `CK` float DEFAULT 0,
  `TotalMoney` float NOT NULL,
  `HavePaid` float DEFAULT 0,
  `StillOwe` float NOT NULL,
  `Status` bit(1) DEFAULT b'0',
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `customerorders`
--

INSERT INTO `customerorders` (`Id`, `CustomerId`, `EmployeeId`, `TimeStamp`, `VAT`, `CK`, `TotalMoney`, `HavePaid`, `StillOwe`, `Status`, `Description`, `IsDelete`) VALUES
(10, 1, 1, '2020-10-02 00:00:00', 0.1, 0.06, 15990000, 0, 15990000, b'0', '', b'1'),
(11, 1, 4, '2020-10-02 00:00:00', 0, 0, 12300, 0, 12300, b'0', 'cai gi', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customerpayfororder`
--

CREATE TABLE `customerpayfororder` (
  `Id` int(11) NOT NULL,
  `CustomerOrderId` int(11) NOT NULL,
  `EmployeerId` int(11) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  `PayMoney` float NOT NULL,
  `IsDelete` bit(1) DEFAULT b'0',
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `customerpayfororder`
--

INSERT INTO `customerpayfororder` (`Id`, `CustomerOrderId`, `EmployeerId`, `CustomerId`, `TimeStamp`, `PayMoney`, `IsDelete`, `Description`) VALUES
(1, 11, 4, 1, '2020-10-02 00:00:00', 2000000000000, b'0', ''),
(2, 11, 4, 1, '2020-10-02 00:00:00', 1250000, b'0', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `Id` int(11) NOT NULL,
  `PhoneNumber` varchar(15) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Gender` varchar(6) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`Id`, `PhoneNumber`, `Name`, `Address`, `Gender`, `IsDelete`) VALUES
(1, '0123456789', 'Nguyễn Văn Nhật', 'Vinh', 'Male', b'0'),
(2, '0123456788', 'Trần phương Nam', 'HCM', 'Male', b'0'),
(3, '036 960 1006', 'Nguyen Thi Ve Que', 'Nghe An Vinh', 'Female', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `Id` int(11) NOT NULL,
  `Username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `RoleId` int(11) NOT NULL,
  `PhoneNumber` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `Salary` float DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Gender` varchar(6) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`Id`, `Username`, `Password`, `Email`, `Name`, `RoleId`, `PhoneNumber`, `Salary`, `Birthdate`, `Gender`, `IsDelete`) VALUES
(1, 'root', '$2a$10$ZTmrY9SNXlziswNZfpihUufgxPrb1mspS0ZrVcvsW9JTKn2YBGszy', 'root@root.com', 'Root', 1, '987 654 4444', 2000, '2000-05-15', 'Male', b'0'),
(2, 'janedoe', '$2a$10$6i385xsyE2PP9DbEwbuFv.Xfhs89dveGUmKYI1Cor7rp53YI4JYoS', 'janedoe@gmail.com', 'Jane Doe', 2, NULL, NULL, NULL, NULL, b'0'),
(3, 'johndoe', '$2a$10$vDfP/Ca90Zggcd9qXBD3f.xeauGFyq6gaiQjd5GB8p51sxuvYzRy6', 'johndoe@gmail.com', 'John Doe', 3, NULL, NULL, NULL, NULL, b'1'),
(4, 'nhat', '$2a$10$WjYGI5Ii0eeVT7ih6WbIi.FDWz1.qwl44fFaCzEq04jl6znyyiHgK', 'nhat@gmail.com', 'nhat nguyen', 3, NULL, NULL, NULL, NULL, b'0'),
(5, 'test', '$2a$10$F3PZ1IgqQ2rpXgqw5Hs6veYcyQCxe9sSduF/af1hB49ZQuOmHWPIq', 'Nam@gmail.com', 'Nam', 3, NULL, NULL, NULL, NULL, b'0'),
(6, 'test2', '$2a$10$slyGYGCdsPac5Ki4ok7my.fqRr04gj5bR3.sd0tWKyZ04g9/1yiiK', 'test2@gmail.com', 'test2', 3, NULL, NULL, NULL, NULL, b'0'),
(7, 'test3', '$2a$10$zt4COpnSdHKONd0MLkg7teyiQl6UcLDs3q0r1LXTexlNAxI/XQNqm', '12@gmail.com', 'nhat123', 3, NULL, NULL, NULL, NULL, b'0'),
(8, 'test4', '$2a$10$Tfq1PKolr750cxqXqdxKV.f/NN.uWMtsbRGyYM2jSpOklX3o/L9Ia', 'test4@gmail.com', 'test4', 3, NULL, NULL, NULL, NULL, b'0'),
(9, 'test5', '$2a$10$m6kHSE6szzHWPQUsW7P60eUKEz3kE2IskPgIuJRj/trgXuoe5KoNu', 'test5@gmail.com', 'test54', 3, NULL, NULL, NULL, NULL, b'0'),
(10, 'test11', '$2a$10$g1fkOcZoDMajDEpIxA2Kb.IlqdvKF9MsEhFbvQX9EsJ7FErwIneQS', 'test11@gmail.com', 'test11', 3, NULL, NULL, NULL, NULL, b'0'),
(11, 'test12', '$2a$10$blyvZwEO8km1jB0WiQoTS.J7mD8nzY08qiIwZh0bLRk2vU14.9tMm', 'test12@gmail.com', 'test12', 3, NULL, NULL, NULL, NULL, b'0'),
(12, 'test14', '$2a$10$h/EtdU8qX0/sUnFXLJzF5O/L0NdXQg2t619dFfKvW4QqMO58p7Kue', 'test14@yahoo.com', 'test14', 3, NULL, NULL, NULL, NULL, b'0'),
(13, 'test15', '$2a$10$AJO0/llmtJNi8cRVv1utFOe/q84P7Tn1sSXhO7HA82gQAvdH1.L3u', 'aaa@gmail.com', 'aaa', 3, NULL, NULL, NULL, NULL, b'0'),
(14, 'test16', '$2a$10$iwWCEAdELC.7MCpFF/USfuKWEqqLccqeFgD/hc2Wa550ebEUSOv9O', 'a1@gmail.com', 'a1', 3, NULL, NULL, NULL, NULL, b'0'),
(15, 'test112', '$2a$10$0qrZ2ZCTfBQbqg0U8SyJs.dncByPfi91Nb8eD3AJBizPX/UMyq6xK', 'a2@gmail.com', 'a2', 3, NULL, NULL, NULL, NULL, b'0'),
(16, 'test121', '$2a$10$Nsdtzb6t3DoYIkmkbP.W/uiodzTwENgz9mSYfRDEWkGg67eOo286C', 'test121@gmail.com', 'test121', 3, NULL, NULL, NULL, NULL, b'0'),
(17, 'test122', '$2a$10$hxSmy7rD4v0yRS8.w.oc6ut4LZB6HqfmumVdGCSNnXhDO7ZLLE5uS', 'test122@gmail.com', 'test122', 3, NULL, NULL, NULL, NULL, b'0'),
(18, 'test124', '$2a$10$.k3mTKKhPaTAG1nOnoCKPecfM57OBEquwNSXARHMCwtGTjBXdu006', 'test124@gmail.com', 'test124', 3, NULL, NULL, NULL, NULL, b'0'),
(19, 'tesst125', '$2a$10$/NgA/xvHKdJIf7AnDu2Tr.4mzYj0XsDqbF8S1wjrg29O3Q2UbqLxq', 'a125@gmail.com', 'test125', 3, NULL, NULL, NULL, NULL, b'0'),
(20, 'test126', '$2a$10$PzEQvn4dIYy/4JTTUEsQjO635pI3YpiOIqo1zNrVlNXsmF9DJ6/xC', 'test126@gmail.com', 'test126', 3, NULL, NULL, NULL, NULL, b'0'),
(21, 'test128', '$2a$10$8W/2LFZD60oymc0Lh0fBEueDlk21JK30IyJLzUEzu228HTVHIRUii', 'test128@gmail.com', 'test128', 3, NULL, NULL, NULL, NULL, b'0'),
(22, 'test188', '$2a$10$mDtFocH1hyyDJzio3GMT4uHwiBFBtfZ88M3g2mnHzWxMT7NBTdqAW', 'test188@gmail.com', 'test188', 3, NULL, NULL, NULL, NULL, b'0'),
(23, 'test388', '$2a$10$uk2cMbXqN6XzlINBac5Vtu4wLycS0gI7t4TGqgD8.90bTpeieq5Ue', 'test388@gmail.com', 'test388', 3, '036 960 1005', 123, '1999-03-16', 'Male', b'0'),
(24, 'test688', '$2a$10$35pbDVIGhK0jVdXrg52P8e/WG3lW3/3Wtzmx1kK/acXW2bmLIfaFq', 'test688@gmail.com', 'test688', 3, '123 456 7899', 123, '1998-10-10', 'Male', b'0'),
(25, 'test68', '$2a$10$N4OAqiYqf3bYTDVNC0HLj.wdF7YUX7pOZg57zAaR4xaxr0e7nByGi', 'test68@gmail.com', 'test68', 3, NULL, NULL, NULL, NULL, b'0'),
(26, 'test333', '$2a$10$wdXNFVWv47SbOWReKL0qfOFFStM2KVmwd/P2ABrfISu7Ximw8V46e', 'test333@gmail.com', 'test333', 3, NULL, NULL, NULL, NULL, b'0'),
(27, 'test666', '$2a$10$m/Y/75eimgU2a6UG8Hv.SOemnIj8rDF6e0OH6pgSxJImyuaOAW5Yi', 'test666@gmail.com', 'test666', 3, '036 960 1005', 123, '1999-03-16', 'Male', b'1'),
(28, 'a123', '$2a$10$iLpgroRHdIL4xKTQmsE.vuRwFB.zDWW6CV/6GJHCkQW41KAbdTph2', 'a123@gmail.com', 'a123', 3, '123 456 7890', 123, '1999-03-16', 'Item 1', b'0'),
(29, 'finalTest', '$2a$10$rSP1EdBuZdpjHMSMHH1VKO9LYY4FMYwsYTc7KIxKUowchSR/wZ89q', 'finalTest@gmail.com', 'finalTest', 3, '123 456 7899', 6689, '2000-12-21', 'Female', b'1'),
(30, 'finalTest1', '$2a$10$TW3yV9moxnfXh1AV08L01ejf/XRxoHb86b8d6YfMqrYAUQoOol27K', 'finalTest1@gmail.com', 'finalTest1', 4, '123 456 7899', 124, '2000-12-03', 'Female', b'1'),
(31, 'aaaaa', '$2a$10$wQEGx3.5GwyJFZTW1KuoSOBJAopPmrsoIHDTHTcHFI.O184b9zV82', 'aaaaa@gmail.com', 'aaaaa', 3, '987 654 3211', 123, '2000-12-20', 'Male', b'1'),
(32, 'finaltest3', '$2a$10$GYxNfIFysn3pB/l6gQsB9.TIpE4hIA61.BktkJSzAgiY7MLfatBaG', 'finaltest3@gmail.com', 'finaltest333', 4, '036 960 1005', 12321300, '2200-12-31', 'Female', b'1'),
(33, 'check1', '$2a$10$giRTRWzfjmm5BivVyUAUQOTWUmq9CnN/PcVI9r2ugSGcb8ssATkEW', 'check@gmail.com', 'check', 3, NULL, NULL, NULL, NULL, b'1'),
(34, 'check3', '$2a$10$U6HRSoKkxW6uOchZmRf4GOXBxgH2cv0RmCxwbFfBoIGTWLaIos7lu', '123a321@gmail.com', 'check3', 3, NULL, NULL, NULL, NULL, b'1'),
(35, 'final99', '$2a$10$Gyz5WqhklaBnmtJRL5ERfOEhWBunNlnG2/D6Gnz80MjBtzxkI4tpi', 'final99@gmail.com', 'finnal', 3, '036 960 1005', 10000, '2000-12-30', 'Male', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `logs`
--

CREATE TABLE `logs` (
  `EmployeeId` int(11) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  `Action` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `models`
--

CREATE TABLE `models` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `models`
--

INSERT INTO `models` (`Id`, `Name`, `Description`) VALUES
(1, 'Role', NULL),
(2, 'Employee', NULL),
(3, 'Category', NULL),
(4, 'Supplier', NULL),
(5, 'Product', NULL),
(6, 'Customer', NULL),
(7, 'Customer Order', NULL),
(8, 'Company Order', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permissions`
--

CREATE TABLE `permissions` (
  `RoleId` int(11) NOT NULL,
  `ModelId` int(11) NOT NULL,
  `AllowInsert` bit(1) DEFAULT NULL,
  `AllowSelect` bit(1) DEFAULT NULL,
  `AllowUpdate` bit(1) DEFAULT NULL,
  `AllowDelete` bit(1) DEFAULT NULL,
  `AllowImport` bit(1) DEFAULT NULL,
  `AllowExport` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `permissions`
--

INSERT INTO `permissions` (`RoleId`, `ModelId`, `AllowInsert`, `AllowSelect`, `AllowUpdate`, `AllowDelete`, `AllowImport`, `AllowExport`) VALUES
(1, 1, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 2, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 3, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 4, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 5, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 6, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 7, b'1', b'1', b'1', b'1', b'1', b'1'),
(1, 8, b'1', b'1', b'1', b'1', b'1', b'1'),
(2, 1, b'0', b'0', b'0', b'0', b'0', b'0'),
(2, 2, b'1', b'1', b'0', b'0', b'0', b'0'),
(2, 3, b'1', b'1', b'1', b'0', b'1', b'1'),
(2, 4, b'1', b'0', b'0', b'0', b'0', b'0'),
(2, 5, b'1', b'1', b'1', b'1', b'1', b'1'),
(2, 6, b'1', b'1', b'0', b'0', b'0', b'0'),
(2, 7, b'1', b'1', b'1', b'1', b'1', b'1'),
(2, 8, b'1', b'0', b'0', b'0', b'0', b'0'),
(3, 1, b'0', b'0', b'0', b'0', b'0', b'0'),
(3, 2, b'1', b'1', b'0', b'0', b'0', b'0'),
(3, 3, b'1', b'1', b'1', b'0', b'1', b'1'),
(3, 4, b'0', b'0', b'0', b'0', b'0', b'0'),
(3, 5, b'1', b'1', b'1', b'1', b'1', b'1'),
(3, 6, b'0', b'1', b'0', b'0', b'0', b'0'),
(3, 7, b'1', b'1', b'1', b'1', b'1', b'1'),
(3, 8, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 1, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 2, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 3, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 4, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 5, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 6, b'0', b'1', b'0', b'0', b'0', b'0'),
(4, 7, b'0', b'0', b'0', b'0', b'0', b'0'),
(4, 8, b'0', b'0', b'0', b'0', b'0', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Price` float NOT NULL,
  `SupplierId` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `Unit` varchar(100) CHARACTER SET utf8 NOT NULL,
  `UnitsInStock` double NOT NULL,
  `ImagePath` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`Id`, `Name`, `Price`, `SupplierId`, `CategoryId`, `Unit`, `UnitsInStock`, `ImagePath`, `IsDelete`) VALUES
(1, 'StingLoai1', 10000, 1, 1, 'Chai', 222, 'E:\\picture\\Anh Nen\\1.jpg', b'0'),
(2, 'cocacola', 11000, 2, 4, 'chainhua', 200, 'E:\\picture\\Anh Nen\\151144.jpg', b'0'),
(3, 'pepsi12', 25000, 5, 3, 'lon', 125, 'D:\\Picturce\\anh1.jpg', b'1'),
(4, 'Test', 15000, 1, 1, 'chai 1.5l', 150, 'E:\\picture\\Anh Nen\\jungle_fantasy_deer_butterflies_night_trees_102121_1920x1080 (1).jpg', b'0'),
(5, 'test3', 17000, 2, 4, 'm2', 12, 'E:\\picture\\Anh Nen\\jungle_fantasy_deer_butterflies_night_trees_102121_1920x1080 (1).jpg', b'1'),
(6, 'test 3', 16000, 1, 1, 'chai 1.5l', 105, 'E:\\picture\\Anh Nen\\dragon_fly_jaws_rocks_night_69221_1920x1080.jpg', b'1'),
(7, 'finalTest1', 12345600, 3, 2, 'chai 500ml 1', 108, 'E:\\picture\\Anh Nen\\horizon_sunrise_sea_sun_118911_1920x1080.jpg', b'1'),
(8, 'cai gi the', 123, 3, 3, '321', 100, '', b'0'),
(9, 'Demo', 123432000, 3, 7, 'cai', 1, '', b'1'),
(10, 'DEMO321', 123, 1, 1, 'cai', 1321, 'C:\\Users\\Nhat\\Documents\\Image 3.png', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`Id`, `Name`, `Description`) VALUES
(1, 'Admin', 'Admin'),
(2, 'Moderator', 'Moderator'),
(3, 'User', 'User'),
(4, 'Delete', 'User Deleted In Here');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
--

CREATE TABLE `suppliers` (
  `Id` int(11) NOT NULL,
  `PhoneNumber` varchar(15) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `suppliers`
--

INSERT INTO `suppliers` (`Id`, `PhoneNumber`, `Name`, `Address`, `Email`, `IsDelete`) VALUES
(1, '0369601006', 'Aqua', 'HCM', 'cauut2@gmail.com', b'0'),
(2, '0369601007', 'Det May A', 'Hai Phong', 'cauut3@gmail.com', b'0'),
(3, '0369601008', 'Quat Dien Senko', 'HCM', 'cauut4@gmail.com', b'0'),
(4, '0369601009', 'Huda', 'Thu Duc', 'cauut5@gmail.com', b'0'),
(5, '036 960 1010', 'Tiger', 'Binh Duong', 'cauut6@gmail.com', b'0'),
(6, '036 961 0119', 'Haniken haha', 'Binh Duong1', 'hetvid123u@gmail.com', b'0'),
(7, '036 960 1000', 'Aqua1', 'hooc mon', '123@gmail.com', b'0'),
(8, '036 960 1005', 'thulan1', 'det', 'chanthe@gmail.com', b'1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Chỉ mục cho bảng `companyorderdetails`
--
ALTER TABLE `companyorderdetails`
  ADD PRIMARY KEY (`CompanyOrderId`,`ProductId`);

--
-- Chỉ mục cho bảng `companyorders`
--
ALTER TABLE `companyorders`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD KEY `FK_CompanyOrders_Employees` (`EmployeeId`),
  ADD KEY `FK_CompanyOrders_Suppliers` (`SupplierId`);

--
-- Chỉ mục cho bảng `companypayfororder`
--
ALTER TABLE `companypayfororder`
  ADD PRIMARY KEY (`Id`,`CompanyOrderId`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD KEY `FK_CompanyPayForOrder_CompanyOrders` (`CompanyOrderId`),
  ADD KEY `FK_CompanyPayForOrder_Employeer` (`EmployeerId`);

--
-- Chỉ mục cho bảng `customerorderdetails`
--
ALTER TABLE `customerorderdetails`
  ADD PRIMARY KEY (`CustomerOrderId`,`ProductId`),
  ADD KEY `FK_CustomerOrderDetails_Product` (`ProductId`);

--
-- Chỉ mục cho bảng `customerorders`
--
ALTER TABLE `customerorders`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD KEY `FK_CustomerOrders_Employees` (`EmployeeId`),
  ADD KEY `FK_CustomerOrders_Customers` (`CustomerId`);

--
-- Chỉ mục cho bảng `customerpayfororder`
--
ALTER TABLE `customerpayfororder`
  ADD PRIMARY KEY (`Id`,`CustomerOrderId`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD KEY `FK_CustomerPayforOrder_CompanyOrders` (`CustomerOrderId`),
  ADD KEY `FK_CustomerPayforOrder_Employeer` (`EmployeerId`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD UNIQUE KEY `PhoneNumber` (`PhoneNumber`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Password` (`Password`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `FK_Employees_Roles` (`RoleId`);

--
-- Chỉ mục cho bảng `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`EmployeeId`,`TimeStamp`);

--
-- Chỉ mục cho bảng `models`
--
ALTER TABLE `models`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Chỉ mục cho bảng `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`RoleId`,`ModelId`),
  ADD KEY `FK_Permissions_Models` (`ModelId`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD KEY `FK_Products_Categories` (`CategoryId`),
  ADD KEY `FK_Products_Suppliers` (`SupplierId`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`),
  ADD UNIQUE KEY `PhoneNumber` (`PhoneNumber`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `companyorders`
--
ALTER TABLE `companyorders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `companypayfororder`
--
ALTER TABLE `companypayfororder`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `customerorders`
--
ALTER TABLE `customerorders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `customerpayfororder`
--
ALTER TABLE `customerpayfororder`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `employees`
--
ALTER TABLE `employees`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `models`
--
ALTER TABLE `models`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `companyorderdetails`
--
ALTER TABLE `companyorderdetails`
  ADD CONSTRAINT `FK_CompanyOrderDetails_CompanyOrders` FOREIGN KEY (`CompanyOrderId`) REFERENCES `companyorders` (`Id`);

--
-- Các ràng buộc cho bảng `companyorders`
--
ALTER TABLE `companyorders`
  ADD CONSTRAINT `FK_CompanyOrders_Employees` FOREIGN KEY (`EmployeeId`) REFERENCES `employees` (`Id`),
  ADD CONSTRAINT `FK_CompanyOrders_Suppliers` FOREIGN KEY (`SupplierId`) REFERENCES `suppliers` (`Id`);

--
-- Các ràng buộc cho bảng `companypayfororder`
--
ALTER TABLE `companypayfororder`
  ADD CONSTRAINT `FK_CompanyPayForOrder_CompanyOrders` FOREIGN KEY (`CompanyOrderId`) REFERENCES `companyorders` (`Id`),
  ADD CONSTRAINT `FK_CompanyPayForOrder_Employeer` FOREIGN KEY (`EmployeerId`) REFERENCES `employees` (`Id`);

--
-- Các ràng buộc cho bảng `customerorderdetails`
--
ALTER TABLE `customerorderdetails`
  ADD CONSTRAINT `FK_CustomerOrderDetails_CustomerOrders` FOREIGN KEY (`CustomerOrderId`) REFERENCES `customerorders` (`Id`),
  ADD CONSTRAINT `FK_CustomerOrderDetails_Product` FOREIGN KEY (`ProductId`) REFERENCES `products` (`Id`);

--
-- Các ràng buộc cho bảng `customerorders`
--
ALTER TABLE `customerorders`
  ADD CONSTRAINT `FK_CustomerOrders_Customers` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`),
  ADD CONSTRAINT `FK_CustomerOrders_Employees` FOREIGN KEY (`EmployeeId`) REFERENCES `employees` (`Id`);

--
-- Các ràng buộc cho bảng `customerpayfororder`
--
ALTER TABLE `customerpayfororder`
  ADD CONSTRAINT `FK_CustomerPayforOrder_CompanyOrders` FOREIGN KEY (`CustomerOrderId`) REFERENCES `customerorders` (`Id`),
  ADD CONSTRAINT `FK_CustomerPayforOrder_Employeer` FOREIGN KEY (`EmployeerId`) REFERENCES `employees` (`Id`);

--
-- Các ràng buộc cho bảng `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FK_Employees_Roles` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`Id`);

--
-- Các ràng buộc cho bảng `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `FK_Logs_Employees` FOREIGN KEY (`EmployeeId`) REFERENCES `employees` (`Id`);

--
-- Các ràng buộc cho bảng `permissions`
--
ALTER TABLE `permissions`
  ADD CONSTRAINT `FK_Permissions_Models` FOREIGN KEY (`ModelId`) REFERENCES `models` (`Id`),
  ADD CONSTRAINT `FK_Permissions_Roles` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`Id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK_Products_Categories` FOREIGN KEY (`CategoryId`) REFERENCES `categories` (`Id`),
  ADD CONSTRAINT `FK_Products_Suppliers` FOREIGN KEY (`SupplierId`) REFERENCES `suppliers` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
