IF OBJECT_ID('Score', 'U') IS NOT NULL 
  DROP TABLE Score;
IF OBJECT_ID('Student', 'U') IS NOT NULL 
  DROP TABLE Student;
IF OBJECT_ID('Teacher', 'U') IS NOT NULL 
  DROP TABLE Teacher;  

CREATE TABLE Student
( 
	name nvarchar(50),
	gender nvarchar(50),
	birthday date,
	address nvarchar(50),
	username nvarchar(50) primary key,
	email nvarchar(50),
	password nvarchar(50),
	major nvarchar(50),
	placeofbirth nvarchar(50),
	intake int,
	access_role int
);
CREATE TABLE Teacher 
(
	tname nVARCHAR(50),
	course nVARCHAR(50),
	tusername nVARCHAR(50),
	tpassword nVARCHAR(50),
	PRIMARY KEY (tname)
)


CREATE TABLE Score
(
	username nVARCHAR(50) FOREIGN KEY REFERENCES Student(username),
	tname nvarchar(50) FOREIGN KEY REFERENCES Teacher(tname),
	score float,
	PRIMARY KEY (username, tname)
)
INSERT INTO Student([name], [gender], [birthday], [address], [username], [email], [password], [major], [placeofbirth],[intake],[access_role])
VALUES ('Elias Felix', 'male', '1998-12-15', 'Industriestr. 91, Pulsnitz, 01896', 'e1215' , 'elias1234@gmail.com', 'aa10001', 'Computer Science', 'Germany',2021,1),
	('Lena Kudo', 'female', '1999-12-21','Berliner Str. 26, Werdau, 08412', 'l1221', 'lena7739@gmail.com', 'dddc10002','Economic Law', 'Japan',2020,1),
	('Anna Hans', 'female', '1999-12-21','Südweg 3, Auerbach, 08209', 'a1221', 'anna4711@gmail.com', 'asdasd10004' , 'Business Administration', 'French',2018,1),
	('Elias Leon', 'female', '2000-1-18', 'Antwerpener Str. 47, Schildau, 04889', 'e2870', 'elias2870@gmail.com', 'qwsdsd','Business_ Administration','French',2019,1),
	('Fynn Fraen', 'male', '2001-2-14',  'Limbach-Oberfrohna, 09212', 'f9465', 'fynn9465@gmail.com', '123123a', 'Economic Law','China', 2016,1),
	('Traurott Friedlich', 'female', '2000-1-5', 'Berliner Str. 19, Groß-Gerau, 64531', 't0105', 'friedlich6603@gmail.com','asdasd','Engineering','Vietnam', 2019,1),
	('Leon Jonas', 'male', '2000-2-1','Industriestr. 91, Regis-Breitinge 04565', 'l0201', 'leon5390@gmail.com','ddaser23', 'Engineering','India', 2015,1),
	('Thomas Beckham', 'male', '1999-1-6','Dorfring 9, Salzatal, 06198','t0106','thomas3776@gmail.com','asdaszzxcc', 'Computer Science','UK',2016,1)

INSERT INTO Teacher ([tname], [course], [tusername], [tpassword])
VALUES 
('Tom', 'Math', 'tom', '3214'),
('Moi', 'Law', 'moi', '3489'),
('Long', 'Programming_Exercise', 'long', '4039'),
('Dat', 'Physics', 'dat', '8473');
INSERT INTO Score ([username], [tname], [score])
VALUES
('e1215', 'Tom', 1.3),
('e1215', 'Moi', 2.5),
('e1215', 'Long',3.3),
('e1215', 'Dat', 2.3),
('l1221', 'Tom', 1.6),
('l1221', 'Moi', 2.5),
('l1221', 'Long', 3.2),
('l1221', 'Dat', 2.1),
('a1221', 'Tom', 1.6),
('a1221', 'Moi', 2.5),
('a1221', 'Long', 3.2),
('a1221', 'Dat', 2.9),
('e2870', 'Tom', 3.1),
('e2870', 'Moi', 1.2),
('e2870', 'Long', 1.5),
('e2870', 'Dat', 1.7),
('f9465', 'Tom', 3.7),
('f9465', 'Moi', 1.5),
('f9465', 'Long', 1.5),
('f9465', 'Dat', 1.9),
('t0105', 'Tom', 3.8),
('t0105', 'Moi', 3.7),
('t0105', 'Long', 1),
('t0105', 'Dat', 1.7),
('l0201', 'Tom', 4),
('l0201', 'Moi', 3.2),
('l0201', 'Long', 1.7),
('l0201', 'Dat', 2.7),
('t0106', 'Tom', 1.2),
('t0106', 'Moi', 3.8),
('t0106', 'Long', 1.2),
('t0106', 'Dat', 3.5);
