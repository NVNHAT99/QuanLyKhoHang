-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 27, 2020 lúc 09:19 AM
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
(13, 'Quat Hoi Nuoc L3', NULL, b'0');

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `companyorders`
--

CREATE TABLE `companyorders` (
  `Id` int(11) NOT NULL,
  `SupplierId` int(11) NOT NULL,
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
  `PayMoney` float NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customerorderdetails`
--

CREATE TABLE `customerorderdetails` (
  `CustomerOrderId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `ProductUnit` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Cost` float NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `Id` int(11) NOT NULL,
  `PhoneNumber` varchar(10) CHARACTER SET utf8 NOT NULL,
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
(2, '0123456788', 'Trần phương Nam', 'HCM', 'Male', b'0');

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
  `PhoneNumber` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `Salary` float DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Gender` varchar(6) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`Id`, `Username`, `Password`, `Email`, `Name`, `RoleId`, `PhoneNumber`, `Salary`, `Birthdate`, `Gender`, `IsDelete`) VALUES
(1, 'root', '$2a$10$j2fhwjfsaqGYet5jwywOUOUIl617KY9ECQ/Zwi1k4I.ywumTCVI.G', 'root@root.com', 'Root', 1, NULL, NULL, NULL, NULL, b'0'),
(2, 'janedoe', 'RLHDPuWEmJ6SMqm0+tLur4zBwf+ucP/xUahXcL1/UQQ2MZ0B', 'janedoe@gmail.com', 'Jane Doe', 2, NULL, NULL, NULL, NULL, b'0'),
(3, 'johndoe', 'urSyQ3bpZLu6Z6URAOPbnv/f2lrfTr9Rpy4Q8Bo9xFoSvPPj', 'johndoe@gmail.com', 'John Doe', 3, NULL, NULL, NULL, NULL, b'0');

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
  `UnitsInStock` int(11) NOT NULL,
  `ImagePath` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `IsDelete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`Id`, `Name`, `Price`, `SupplierId`, `CategoryId`, `Unit`, `UnitsInStock`, `ImagePath`, `IsDelete`) VALUES
(1, 'Sting', 10000, 1, 1, 'Chai', 100, NULL, b'0');

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
  `PhoneNumber` varchar(10) CHARACTER SET utf8 NOT NULL,
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
(5, '0369601010', 'Tiger', 'Binh Duong', 'cauut6@gmail.com', b'0'),
(6, '036961011', 'Haniken', 'Binh Duong', 'hetvidu@gmail.com', b'0');

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
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `companyorders`
--
ALTER TABLE `companyorders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `companypayfororder`
--
ALTER TABLE `companypayfororder`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customerorders`
--
ALTER TABLE `customerorders`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customerpayfororder`
--
ALTER TABLE `customerpayfororder`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `employees`
--
ALTER TABLE `employees`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `models`
--
ALTER TABLE `models`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
