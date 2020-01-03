﻿-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- 생성 시간: 20-01-02 13:42
-- 서버 버전: 5.7.24
-- PHP 버전: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `testdb`
--
CREATE DATABASE IF NOT EXISTS `testdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `testdb`;

-- --------------------------------------------------------

--
-- 테이블 구조 `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE IF NOT EXISTS `restaurant` (
  `registerNum` int(11) NOT NULL AUTO_INCREMENT,
  `restaurantName` varchar(50) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `address` varchar(200) NOT NULL,
  `cuisine` varchar(20) CHARACTER SET utf8 COLLATE utf8_estonian_ci NOT NULL,
  PRIMARY KEY (`registerNum`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `restaurant`
--

INSERT INTO `restaurant` (`registerNum`, `restaurantName`, `tel`, `address`, `cuisine`) VALUES
(1, '용이초밥', '051-804-1292', '부산광역시 부산진구 부전동 170-6', '일식'),
(2, 'YK네', '051-808-9463', '부산광역시 부산진구 부전동 522-10', '일식'),
(3, '꾸이점빵', '051-802-9235', '부산광역시 부산진구 부전동 198-2', '한식'),
(4, '미스터스시', '051-755-6000', '부산광역시 부산진구 부전동 256-3', '일식'),
(5, '정을담다', '051-808-4004', '부산광역시 부산진구 부전동 217-1', '한식'),
(6, '3found', '051-803-1982', '부산광역시 부산진구 부전동 198-2', '양식'),
(7, '다옴', '051-520-3778', '부산광역시 부산진구 부전동 227-2', '한식'),
(8, '쉐이크쉑', '051-520-3707', '부산광역시 부산진구 부전동 227-2', '양식'),
(9, '구워삶기', '051-819-0093', '부산광역시 부산진구 부전동 168-385', '한식'),
(10, '라화쿵부', '051-818-1912', '부산광역시 부산진구 부전동 189-8', '중식'),
(11, '그집곱도리탕', '051-816-4186', '부산광역시 부산진구 부전동 142-4', '한식'),
(12, '써리힐', '010-5578-1444', '부산광역시 부산진구 전포동 229-13 2층', '양식'),
(13, '페이센동', '051-080-9399', '부산광역시 부산진구 전포동 674-14', '일식'),
(14, '대쿠이', '051-802-0597', '부산광역시 부산진구 전포동 882-6 1층', '일식'),
(15, '브루티마부오니', '010-5559-7962', '부산광역시 부산진구 전포동 309-4 1층', '양식'),
(16, '석화연', '010-4733-6547', '부산광역시 부산진구 전포동 661-6', '한식'),
(17, '시월양식당', '010-6828-1528', '부산광역시 부산진구 전포동 660-16', '양식'),
(18, '송정3대국밥', '051-806-5722', '부산광역시 부산진구 부전동 255-15', '한식'),
(19, '칠린', '010-8701-3575', '부산광역시 부산진구 전포동 664-5', '양식'),
(20, '스테레오타입 오브 부산', '051-803-5678', '부산광역시 부산진구 전포동 686-4', '양식'),
(21, '밥집', '051-818-2330', '부산광역시 부산진구 전포동 355-11', '한식'),
(22, '더블링', '070-8712-2200', '부산광역시 부산진구 전포동 671-13', '양식'),
(23, '옥천사세탁', '010-7711-1215', '부산광역시 부산진구 전포동 207-5', '양식'),
(24, '옥반', '051-802-9986', '부산광역시 부산진구 전포동 664-9', '한식'),
(25, '소인수분해', '051-803-2177', '부산광역시 부산진구 전포동 662-13', '한식'),
(26, '개금밀면', '051-892-3466', '부산광역시 부산진구 개금동 171-34', '한식'),
(27, '부산꼴통라면', '051-808-4778', '부산광역시 부산진구 부전동 517-13 지하1층', '한식'),
(28, 'Bistrot de 구구', '070-7543-8727', '부산광역시 부산진구 전포동 309-21 1층', '양식'),
(29, '상호미지수', '010-4779-2836', '부산광역시 부산진구 전포동 646-2', '한식'),
(30, '수호족발감자탕', '051-807-7800', '부산광역시 부산진구 부전동 515-14', '한식'),
(31, '컨트리맨즈', '070-7313-4348', '부산광역시 부산진구 전포동 683-5 3층', '양식'),
(32, '삼미식당', '051-819-3766', '부산광역시 부산진구 부전동 168-181 2층', '일식'),
(33, '프렌즈클럽', '010-9125-8975', '부산광역시 부산진구 전포동 202-6 1층', '양식'),
(34, '양산도 히츠마부시', '051-803-1092', '부산광역시 부산진구 전포동 692-2', '일식'),
(35, '마장동김씨', '051-804-8077', '부산광역시 부산진구 부전동 168-402', '한식'),
(36, 'BENVENUTO', '070-7311-6760', '부산광역시 부산진구 부전2동 515-2 2층', '양식'),
(37, '회사원', '051-806-1150', '부산광역시 부산진구 부전동 172-2', '일식'),
(38, '가련한부엌', '051-802-8896', '부산광역시 부산진구 부전동 156-45', '일식'),
(39, '경찰서옆초밥집', '051-818-3189', '부산광역시 부산진구 부전동 415-1', '일식'),
(40, '기장손칼국수', '051-806-6832', '부산광역시 부산진구 부전2동 256-6', '한식'),
(41, '명성양곱창', '051-804-9279', '부산광역시 부산진구 부전동 168-459', '한식'),
(42, '오바리', '010-5575-9549', '부산광역시 부산진구 부전동 168-368', '중식'),
(43, '미뜨보루', '051-818-2233', '부산광역시 부산진구 전포동 687-10', '일식'),
(44, '나랑히요', '010-3208-4825', '부산광역시 부산진구 전포동 193-10 1층', '일식'),
(45, '야타이미세', '070-7358-0932', '부산광역시 부산진구 전포동 664-9', '일식'),
(46, '피플스', '010-6436-2558', '부산광역시 부산진구 전포동 681-4 1층', '양식'),
(47, '209St', '051-816-2764', '부산광역시 부산진구 전포동 687-11 1층', '양식'),
(48, '칸다소바', '051-809-1662', '부산광역시 부산진구 부전동 144-1', '일식'),
(49, '포항돼지국밥', '051-807-5439', '부산광역시 부산진구 부전동 255-14', '한식'),
(50, '면식수행', '051-805-8882', '부산광역시 부산진구 부전동 168-263', '한식'),
(51, '파스타빌라', '051-805-0777', '부산광역시 부산진구 전포동 357-4', '양식'),
(52, '도우개러지', '010-2940-2330', '부산광역시 부산진구 전포동 674-15', '양식'),
(53, '솔탭하우스', '051-520-3678', '부산광역시 부산진구 부전동 227-2 삼정타워 8층', '양식'),
(54, '옥함', '051-802-8025', '부산광역시 부산진구 부전동 187-6', '양식'),
(55, '개미집', '051-819-8809', '부산광역시 부산진구 부전동 240-5', '한식'),
(56, '모루식당', '010-3676-6949', '부산광역시 부산진구 전포1동 680-20', '일식'),
(57, '장승진스시', '070-7799-3990', '부산광역시 부산진구 부전동 153-5', '일식'),
(58, '낭만장작', '051-804-9799', '부산광역시 부산진구 부전동 168-146 1층', '한식'),
(59, '수수하지만굉장해', '051-808-6860', '부산광역시 부산진구 전포동 347-1', '일식'),
(60, '라멘야', '051-911-1088', '부산광역시 부산진구 부전동 168-402', '일식'),
(61, '롸촨촨300', '051-818-3388', '부산광역시 부산진구 부전동 168-33 1층', '중식'),
(62, '스시미르네', '051-810-3089', '부산광역시 부산진구 부전동 503-15 롯데백화점 부산본점 지하 1층', '일식'),
(63, '고복샤브샤브', '051-804-7141', '부산광역시 부산진구 부전2동 170-1', '일식'),
(64, '초밥먹고갈래', '010-2555-8966', '부산광역시 부산진구 전포동 687-15', '일식'),
(65, '화남정돼지국밥', '051-809-8853', '부산광역시 부산진구 연지동 19-6', '한식'),
(66, '삿뽀로참치', '051-817-2663', '부산광역시 부산진구 부전동 521-15', '일식'),
(67, '라라코스트', '051-817-3230', '부산광역시 부산진구 부전동 222-1', '양식'),
(68, '음주양식당 오스테리아 어부', '051-802-8858', '부산광역시 부산진구 전포동 692-2', '양식'),
(69, '가곱', '051-807-2177', '부산광역시 부산진구 부전동 168-394', '한식'),
(70, '고정점', '051-819-7792', '부산광역시 부산진구 부전동 168-438', '한식'),
(71, '홍유단', '051-808-4001', '부산광역시 부산진구 부전동 524-43', '중식'),
(72, '이녁', '010-9552-1770', '부산광역시 부산진구 전포동 688-2', '일식'),
(73, '마마도마', '051-817-9711', '부산광역시 부산진구 부전동 220-5', '일식'),
(74, '맛찬들왕소금구이', '051-808-6088', '부산광역시 부산진구 부전동 516-51', '한식'),
(75, '각방', '051-809-0138', '부산광역시 부산진구 부전동 113-8', '일식'),
(76, '자연별곡', '051-794-7330', '부산광역시 부산진구 전포동 668-1', '한식'),
(77, '한끼맛있다', '051-802-6094', '부산광역시 부산진구 부전동 170-31', '양식'),
(78, '다시구월', '051-791-0910', '부산광역시 부산진구 전포동 691-1 4층', '양식'),
(79, '아담스테이블', '010-3398-0257', '부산광역시 부산진구 전포동 680-4', '양식'),
(80, '디노브런치', '010-2103-9947', '부산광역시 부산진구 전포동 686-5', '양식'),
(81, '까미노블랑코', '070-7311-5936', '부산광역시 부산진구 부전동 168-408 4층', '양식'),
(82, '라짜이마라훠궈', '051-818-9229', '부산광역시 부산진구 부전동 168-15', '중식'),
(83, '더시카고짬뽕', '051-806-6094', '부산광역시 부산진구 부전동 168-182', '중식'),
(84, '쫜쫜훠궈', '051-914-4114', '부산광역시 부산진구 전포동 680-4', '중식'),
(85, '마녀마라집', '051-807-7780', '부산광역시 부산진구 전포동 687-13', '중식'),
(86, '쏘핫마라탕마라샹궈', '010-5607-2963', '부산광역시 부산진구 부전동 502-5', '중식'),
(87, '홍콩반점0410', '070-8818-0410', '부산광역시 부산진구 부전동 175 109호', '중식'),
(88, '흥화반점', '051-809-3202', '부산광역시 부산진구 전포2동 637-5', '중식'),
(89, '부산롯데호텔 도림', '051-810-6340', '부산광역시 부산진구 부전동 503-15 43층', '중식'),
(90, '금홍', '051-897-0133', '부산광역시 부산진구 당감동 731-2 우리빌딩 1층', '중식'),
(91, '용이네탕슉', '051-623-2959', '부산광역시 부산진구 부전동 168-429', '중식'),
(92, '미미루', '051-817-8881', '부산광역시 부산진구 부전2동 516-71', '중식'),
(93, '태화반점', '010-9498-0201', '부산광역시 부산진구 부암동 334-12', '중식'),
(94, '탕화쿵푸마라탕', '051-710-2288', '부산광역시 부산진구 부전동 205-1 2층', '중식'),
(95, '팔미엔', '051-807-5565', '부산광역시 부산진구 전포동 691-1 1층 팔미엔', '중식'),
(96, '신성각', '051-851-3460', '부산광역시 부산진구 양정1동 458-5 현대아파트1차 상가2층', '중식'),
(97, '샤오시안', '051-555-7744', '부산광역시 부산진구 부전동 168-96', '중식'),
(98, '용하훠궈무한리필', '070-8800-0102', '부산광역시 부산진구 부전동 524-18', '중식'),
(99, '범태옛날손짜장', '051-809-8823', '부산광역시 부산진구 부전2동 502-21', '중식'),
(100, '차이봉', '051-802-0025', '부산광역시 부산진구 부전2동 256-6', '중식'),
(101, '무짬뽕', '051-892-5630', '부산광역시 부산진구 개금2동 540-54 2층', '중식'),
(102, '하오칭짬뽕201', '051-988-0201', '부산광역시 부산진구 초읍동 215-3', '중식');

-- --------------------------------------------------------

--
-- 테이블 구조 `reviews`
--

DROP TABLE IF EXISTS `reviews`;
CREATE TABLE IF NOT EXISTS `reviews` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `storeNum` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `reviewContent` varchar(500) NOT NULL,
  `regDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rate` double NOT NULL,
  PRIMARY KEY (`num`),
  KEY `FK_storeNum` (`storeNum`),
  KEY `FK_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `reviews`
--

INSERT INTO `reviews` (`num`, `storeNum`, `email`, `userName`, `reviewContent`, `regDate`, `rate`) VALUES
(39, 1, 'aaa', 'test', '맛있어요', '2020-01-02 13:25:35', 3.5),
(40, 1, 'aaa', 'test', '굿맛', '2020-01-02 13:26:10', 3.5),
(41, 3, 'aaa', 'test', 'aaa', '2020-01-02 13:35:07', 3);

-- --------------------------------------------------------

--
-- 테이블 구조 `testmember`
--

DROP TABLE IF EXISTS `testmember`;
CREATE TABLE IF NOT EXISTS `testmember` (
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `userName` varchar(30) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `username` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `testmember`
--

INSERT INTO `testmember` (`email`, `password`, `userName`) VALUES
('aa@ss.com', '1234', 'hi'),
('aaa', '1234', 'test'),
('bbb@bb.com', '123', 'bbb'),
('dddd@222.com', 'aaaa', 'dksiajd'),
('dkswodud12345@gmail.com', 'anjae156', '안대영'),
('hhh@xx.com', '123', 'hhhg'),
('suerush7', '1234', 'dongho'),
('vvv', '123', 'vvv');

--
-- 덤프된 테이블의 제약사항
--

--
-- 테이블의 제약사항 `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FK_email` FOREIGN KEY (`email`) REFERENCES `testmember` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_storeNum` FOREIGN KEY (`storeNum`) REFERENCES `restaurant` (`registerNum`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;