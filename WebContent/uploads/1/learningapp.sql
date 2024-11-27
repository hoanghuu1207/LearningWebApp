-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for learningonline
CREATE DATABASE IF NOT EXISTS `learningonline` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `learningonline`;

-- Dumping structure for table learningonline.assignments
CREATE TABLE IF NOT EXISTS `assignments` (
  `assignmentID` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `startTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `endTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `classroomID` int(11) DEFAULT NULL,
  PRIMARY KEY (`assignmentID`),
  KEY `classroomID` (`classroomID`),
  CONSTRAINT `assignments_ibfk_1` FOREIGN KEY (`classroomID`) REFERENCES `classrooms` (`classroomID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.assignments: ~0 rows (approximately)

-- Dumping structure for table learningonline.classrooms
CREATE TABLE IF NOT EXISTS `classrooms` (
  `classroomID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `teacherID` int(11) DEFAULT NULL,
  PRIMARY KEY (`classroomID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `classrooms_ibfk_1` FOREIGN KEY (`teacherID`) REFERENCES `users` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.classrooms: ~0 rows (approximately)

-- Dumping structure for event learningonline.delete_expired_rows
DELIMITER //
CREATE EVENT `delete_expired_rows` ON SCHEDULE EVERY 1 MINUTE STARTS '2024-10-21 21:56:55' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM forgot_password WHERE expireAt < NOW()//
DELIMITER ;

-- Dumping structure for table learningonline.forgot_password
CREATE TABLE IF NOT EXISTS `forgot_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `otp` varchar(6) NOT NULL,
  `expireAt` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table learningonline.forgot_password: ~0 rows (approximately)

-- Dumping structure for table learningonline.materials
CREATE TABLE IF NOT EXISTS `materials` (
  `materialID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `filePath` varchar(255) NOT NULL,
  `classroomID` int(11) DEFAULT NULL,
  PRIMARY KEY (`materialID`),
  KEY `classroomID` (`classroomID`),
  CONSTRAINT `materials_ibfk_1` FOREIGN KEY (`classroomID`) REFERENCES `classrooms` (`classroomID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.materials: ~0 rows (approximately)

-- Dumping structure for table learningonline.meetings
CREATE TABLE IF NOT EXISTS `meetings` (
  `meetingID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `startTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `endTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `classroomID` int(11) DEFAULT NULL,
  `duration` time NOT NULL,
  PRIMARY KEY (`meetingID`),
  KEY `classroomID` (`classroomID`),
  CONSTRAINT `meetings_ibfk_1` FOREIGN KEY (`classroomID`) REFERENCES `classrooms` (`classroomID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.meetings: ~0 rows (approximately)

-- Dumping structure for table learningonline.notification
CREATE TABLE IF NOT EXISTS `notification` (
  `notificationID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 0,
  `type` enum('schedule','assignment') DEFAULT NULL,
  `relatedID` int(11) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`notificationID`),
  KEY `userID` (`userID`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.notification: ~0 rows (approximately)

-- Dumping structure for table learningonline.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` enum('teacher','student','admin') NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.roles: ~3 rows (approximately)
INSERT INTO `roles` (`roleID`, `roleName`) VALUES
	(1, 'admin'),
	(2, 'teacher'),
	(3, 'student');

-- Dumping structure for table learningonline.schedule
CREATE TABLE IF NOT EXISTS `schedule` (
  `scheduleID` int(11) NOT NULL AUTO_INCREMENT,
  `meetingID` int(11) DEFAULT NULL,
  `timeCreate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `timeAccess` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`scheduleID`),
  KEY `meetingID` (`meetingID`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`meetingID`) REFERENCES `meetings` (`meetingID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.schedule: ~0 rows (approximately)

-- Dumping structure for table learningonline.students_classrooms
CREATE TABLE IF NOT EXISTS `students_classrooms` (
  `studentID` int(11) NOT NULL,
  `classroomID` int(11) NOT NULL,
  PRIMARY KEY (`studentID`,`classroomID`),
  KEY `classroomID` (`classroomID`),
  CONSTRAINT `students_classrooms_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `users` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `students_classrooms_ibfk_2` FOREIGN KEY (`classroomID`) REFERENCES `classrooms` (`classroomID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.students_classrooms: ~0 rows (approximately)

-- Dumping structure for table learningonline.submissions
CREATE TABLE IF NOT EXISTS `submissions` (
  `submissionID` int(11) NOT NULL AUTO_INCREMENT,
  `assignmentID` int(11) DEFAULT NULL,
  `studentID` int(11) DEFAULT NULL,
  `submissionDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`submissionID`),
  KEY `assignmentID` (`assignmentID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `submissions_ibfk_1` FOREIGN KEY (`assignmentID`) REFERENCES `assignments` (`assignmentID`) ON DELETE CASCADE,
  CONSTRAINT `submissions_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `users` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.submissions: ~0 rows (approximately)

-- Dumping structure for table learningonline.users
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `roleID` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT '',
  `tokenUser` varchar(255) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`),
  KEY `roleID` (`roleID`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `roles` (`roleID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table learningonline.users: ~9 rows (approximately)
INSERT INTO `users` (`userID`, `firstname`, `lastname`, `email`, `password`, `roleID`, `avatar`, `tokenUser`) VALUES
	(3, 'Hoang', 'Huu', 'h1@gmail.com', '$2a$12$K9mDTUQw7cNyWXTzKeAZBetP7Dnbm629rmiRzaBKHDf1QWj1Y.uVi', 3, '', 'kbp5JHEIgwSv9!hp0^^b&TTfWtUzt4tpf8ZEZncci#f2Wrsbypt6fEP1@80d1x(7$1@sBF'),
	(4, 'Tran', 'C', 'anh@gmail.com', '$2a$12$0Fyd7380vb/7BEX.udvzQOf2PrFge0Hzwi4a7tUWUZEShDClGBnMG', 3, '', 'Jg$fn%!xpI2o(USM@@qj)#b!zpe9g@T4MsMz9Ku8utK&4WT#VQfUAR4^wPVF%0hUGnVNyw'),
	(5, 'Le', 'Tam', 'h@gmail.com', '$2a$12$d7ub6dYiZDa.mbYWJxnyNu0SEzPv5FzC.HO5AHHiNf4FEfZ5t3nXq', 3, '', '6qdpiCZH81^)45ZgOVMkn0IRz#umkHdPIP#PsqR6DGa0uk0h1aw%&XDRA7wphxFUSZ(rTG'),
	(6, 'Mail', 'Alo', 'younstar@ciuwin.com', '$2a$12$JTwI9Dfm7XlYGoKXx195q.ohoDaD6BzO.lHLlr/FBThQ84jyUA4o2', 3, '', 'ebZY#Uf&2c9!fY&*pGfM2nK6z$@c0cv$DZtr4%!1iqdV86jXbVLDDZs8I73Git#zamia%!'),
	(7, 'Huu', 'Mail', 'layoung@ciuwin.com', '$2a$12$NYD0OXudUkhBB.klYFes2u0SqazaOpWIrsgCbMg8k5XDAI7Wxzbgy', 2, '', '#SJq%XzBWF(HZN(uWmabIENtsq6drF2OGe@bijj3L&bNUt28skK)sZtndd$dXY!f9^Cm8X'),
	(8, 'H', 'Hoang', 'huynhhoangitf1207@gmail.com', '$2a$12$3lOfknvkYEsAzFOIAFZtUOzMvNg3s6QsSFuuJzNZ1FhMXbGbcAeJu', 3, '', 'VD1Y1A1Q(Pb8s76To8H)Mkl$S$0atvYw@x1Rp&X2pJw@VMD)fduUZjMt)Q#QLVG@EyruYK'),
	(9, 'K', '+', 'huy@gmail.com', '$2a$12$z/Z3QPNQuM3cGEtP.QrijuRm2fLMItxqhwgaJBdstr0rWEZzpi3KW', 3, '', 'ce5XciS%SSdWvHbOD&Zf3b^lbG5dtff*zdjLwL#hKXnid8tEg8)K0FZ0VeHV%)T$W7tBpb'),
	(10, 'A', 'B', 'shane@gmail.com', '$2a$12$yMLWyYHkm7TTUmSBH0Ewd.nI.xSwiPgDysRsX1zJEFryMgJfBhhtS', 2, '', 'ckjsIVGpsJkl$dFqKfV!)eUidtgkBzirD6eq35E02Ibi^gNr69mZL6GM&PulsQX5UFt#4K'),
	(11, 'Le', 'Vo', 'hhh12@gmail.com', '$2a$12$hqxDo8IV.lmPzPJ6/c3CIOkCzVdJBTs5PgVfgY5j1wDBrbzxAoXUa', 2, '', 'C^#S$&Eel%EkyU8rW51SOG5RoSzProduTh9)BrZCP$X)BYBi38!3H*&yHpx5of$w29qpMf');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
