USE Student_Info;
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
	PRIMARY KEY (tusername)
)


CREATE TABLE Score
(
	username nVARCHAR(50) FOREIGN KEY REFERENCES Student(username),
	tusername nvarchar(50) FOREIGN KEY REFERENCES Teacher(tusername),
	score float,
	PRIMARY KEY (username, tusername)
)
INSERT INTO Student([name], [gender], [birthday], [address], [username], [email], [password], [major], [placeofbirth],[intake],[access_role])
VALUES ('Elias Felix', 'male', '1998-12-15', 'Industriestr. 91, Pulsnitz, 01896', 'e1215' , 'elias1234@gmail.com', 'aa10001', 'Computer Science', 'Germany',2021,1),
	('Lena Kudo', 'female', '1999-12-21','Berliner Str. 26, Werdau, 08412', 'l1221', 'lena7739@gmail.com', 'dddc10002','Economic Law', 'Japan',2020,1),
	('Anna Hans', 'female', '1999-12-21','Südweg 3, Auerbach, 08209', 'a1221', 'anna4711@gmail.com', 'asdasd10004' , 'Business Administration', 'French',2018,1),
	('Elias Leon', 'female', '2000-1-18', 'Antwerpener Str. 47, Schildau, 04889', 'e2870', 'elias2870@gmail.com', 'qwsdsd','Business_ Administration','French',2019,1),
	('Fynn Fraen', 'male', '2001-2-14',  'Limbach-Oberfrohna, 09212', 'f9465', 'fynn9465@gmail.com', '123123a', 'Economic Law','China', 2016,1),
	('Traurott Friedlich', 'female', '2000-1-5', 'Berliner Str. 19, Gross-Gerau, 64531', 't0105', 'friedlich6603@gmail.com','asdasd','Engineering','Vietnam', 2019,1),
	('Leon Jonas', 'male', '2000-2-1','Industriestr. 91, Regis-Breitinge 04565', 'l0201', 'leon5390@gmail.com','ddaser23', 'Engineering','India', 2015,1),
	('Thomas Beckham', 'male', '1999-1-6','Dorfring 9, Salzatal, 06198','t0106','thomas3776@gmail.com','1', 'Computer Science','UK',2016,1)


INSERT INTO Teacher ([tname], [course], [tusername], [tpassword])
VALUES 
('Tom', 'Math', 'tom', '3214'),
('Moi', 'Law', 'moi', '3489'),
('Long', 'Programming_Exercise', 'long', '4039'),
('Dat', 'Physics', 'dat', '8473');
INSERT INTO Score ([username], [tusername], [score])
VALUES
('e1215', 'tom', 1.3),
('e1215', 'moi', 2.5),
('e1215', 'long',3.3),
('e1215', 'dat', 2.3),
('l1221', 'tom', 1.6),
('l1221', 'moi', 2.5),
('l1221', 'long', 3.2),
('l1221', 'dat', 2.1),
('a1221', 'tom', 1.6),
('a1221', 'moi', 2.5),
('a1221', 'long', 3.2),
('a1221', 'dat', 2.9),
('e2870', 'tom', 3.1),
('e2870', 'moi', 1.2),
('e2870', 'long', 1.5),
('e2870', 'dat', 1.7),
('f9465', 'tom', 3.7),
('f9465', 'moi', 1.5),
('f9465', 'long', 1.5),
('f9465', 'dat', 1.9),
('t0105', 'tom', 3.8),
('t0105', 'moi', 3.7),
('t0105', 'long', 1),
('t0105', 'dat', 1.7),
('l0201', 'tom', 4),
('l0201', 'moi', 3.2),
('l0201', 'long', 1.7),
('l0201', 'dat', 2.7),
('t0106', 'tom', 1.2),
('t0106', 'moi', 3.8),
('t0106', 'long', 1.2),
('t0106', 'dat', 3.5);

insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Cammy Farnes', 'Female', '1997-08-18', '99 Eastlawn Hill', 'cfarnes0', 'cfarnes0@mashable.com', 'KGlOa4mC', 'Business Administratrion', 'Vietnam', 2020, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Pearle Swancott', 'Female', '1998-12-14', '697 Muir Center', 'pswancott1', 'pswancott1@behance.net', 'rC2vW8zEN3yV', 'Finance Accounting', 'China', 2016, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Tabbitha Doble', 'Female', '2001-04-06', '36690 Summit Alley', 'tdoble2', 'tdoble2@princeton.edu', 'zOR6veN', 'Electrical Engineering', 'Colombia', 2016, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Brietta Burgise', 'Female', '2000-05-20', '76030 Nova Junction', 'bburgise3', 'bburgise3@networkadvertising.org', '4MdR7dBGV', 'Electrical Engineering', 'Brazil', 2016, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Teena Opdenort', 'Female', '1996-10-01', '165 Butternut Trail', 'topdenort4', 'topdenort4@free.fr', 'EJnNiC6', 'Business Administratrion', 'America', 2018, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Bernhard Dankov', 'Female', '1998-10-19', '7 Ohio Place', 'bdankov5', 'bdankov5@163.com', 'pQJK9lw', 'Business Administratrion', 'Netherlands', 2019, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Tomasine Degg', 'Male', '1997-10-05', '9742 Sunbrook Crossing', 'tdegg6', 'tdegg6@cornell.edu', 'STZ4tCM', 'Finance Accounting', 'France', 2016, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Claudius Green', 'Female', '2000-12-06', '32 Mendota Avenue', 'cgreen7', 'cgreen7@hubpages.com', 'mwcSa6C', 'Finance Accounting', 'Germany', 2017, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Melli Benedyktowicz', 'Male', '2001-01-03', '6559 Lotheville Crossing', 'mbenedyktowicz8', 'mbenedyktowicz8@ebay.com', 'zJK7wrc', 'CIvil Engineering', 'India', 2017, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Peggi Witheridge', 'Female', '1999-06-06', '58 Springview Plaza', 'pwitheridge9', 'pwitheridge9@e-recht24.de', '5bStXxqmj1Bf', 'CIvil Engineering', 'China', 2019, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Case Sorrill', 'Male', '2001-07-04', '0 Ilene Lane', 'csorrilla', 'csorrilla@t.co', 'rM0hiFJmpG', 'CIvil Engineering', 'Vietnam', 2020, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Adrea Portt', 'Male', '2001-06-24', '4 Stone Corner Point', 'aporttb', 'aporttb@gmpg.org', 'gKA6aiGfC', 'CIvil Engineering', 'Thailand', 2017, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Lindie Niccols', 'Female', '2001-06-12', '6 Mcguire Junction', 'lniccolsc', 'lniccolsc@washington.edu', 'Ot8juCuCKOw8', 'Computer Science', 'Japan', 2019, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Dinah Cressar', 'Female', '1997-12-31', '15953 Lake View Drive', 'dcressard', 'dcressard@forbes.com', 'ZTiPC4Gk7', 'Electrical Engineering', 'Korea', 2017, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Luciana Rennels', 'Female', '1998-02-11', '6 Katie Hill', 'lrennelse', 'lrennelse@tripadvisor.com', 'CBRjgG9m', 'CIvil Engineering', 'Mexico', 2019, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Robinett Baldetti', 'Female', '1999-03-14', '374 Waywood Parkway', 'rbaldettif', 'rbaldettif@printfriendly.com', 'ofrmWkrJjGC', 'Electrical Engineering', 'Canada', 2020, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Caresa Sharpin', 'Male', '2001-07-08', '597 Darwin Crossing', 'csharping', 'csharping@admin.ch', 'CN6Yt7DRtt', 'Business Administratrion', 'France', 2020, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Sanders Yarrall', 'Male', '1996-05-14', '633 Mariners Cove Way', 'syarrallh', 'syarrallh@imdb.com', 'Hw2KbnsEy', 'Electrical Engineering', 'Vietnam', 2020, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Humfried Blockey', 'Female', '1996-03-19', '169 Holy Cross Road', 'hblockeyi', 'hblockeyi@spotify.com', 'zKhBloLk', 'CIvil Engineering', 'Singapore', 2016, 1);
insert into Student (name, gender, birthday, address, username, email, password, major, placeofbirth, intake, access_role) values ('Link Bernardelli', 'Female', '1996-09-01', '4 Dawn Parkway', 'lbernardellij', 'lbernardellij@tiny.cc', 'tHhS5vzfe', 'Business Administratrion', 'Vietnam', 2019, 1);


insert into score (username, tusername, score) values ('cfarnes0', 'tom', 1.9);
insert into score (username, tusername, score) values ('cfarnes0', 'moi', 5);
insert into score (username, tusername, score) values ('cfarnes0', 'long', 5);
insert into score (username, tusername, score) values ('cfarnes0', 'dat', 3.0);
insert into score (username, tusername, score) values ('pswancott1', 'tom', 3.0);
insert into score (username, tusername, score) values ('pswancott1', 'moi', 1.4);
insert into score (username, tusername, score) values ('pswancott1', 'long', 5);
insert into score (username, tusername, score) values ('pswancott1', 'dat', 5);
insert into score (username, tusername, score) values ('tdoble2', 'tom', 5);
insert into score (username, tusername, score) values ('tdoble2', 'moi', 1.4);
insert into score (username, tusername, score) values ('tdoble2', 'long', 3.6);
insert into score (username, tusername, score) values ('tdoble2', 'dat', 3.9);
insert into score (username, tusername, score) values ('bburgise3', 'tom', 1.0);
insert into score (username, tusername, score) values ('bburgise3', 'moi', 3.7);
insert into score (username, tusername, score) values ('bburgise3', 'long', 2.6);
insert into score (username, tusername, score) values ('bburgise3', 'dat', 5);
insert into score (username, tusername, score) values ('topdenort4', 'tom', 3.4);
insert into score (username, tusername, score) values ('topdenort4', 'moi', 1.3);
insert into score (username, tusername, score) values ('topdenort4', 'long', 5);
insert into score (username, tusername, score) values ('topdenort4', 'dat', 5);
insert into score (username, tusername, score) values ('bdankov5', 'tom', 2.2);
insert into score (username, tusername, score) values ('bdankov5', 'moi', 3.9);
insert into score (username, tusername, score) values ('bdankov5', 'long', 5);
insert into score (username, tusername, score) values ('bdankov5', 'dat', 2.7);
insert into score (username, tusername, score) values ('tdegg6', 'tom', 2.5);
insert into score (username, tusername, score) values ('tdegg6', 'moi', 1.3);
insert into score (username, tusername, score) values ('tdegg6', 'long', 2.9);
insert into score (username, tusername, score) values ('tdegg6', 'dat', 5);
insert into score (username, tusername, score) values ('cgreen7', 'tom', 1.1);
insert into score (username, tusername, score) values ('cgreen7', 'moi', 3.9);
insert into score (username, tusername, score) values ('cgreen7', 'long', 3.8);
insert into score (username, tusername, score) values ('cgreen7', 'dat', 1.3);
insert into score (username, tusername, score) values ('mbenedyktowicz8', 'tom', 3.2);
insert into score (username, tusername, score) values ('mbenedyktowicz8', 'moi', 5);
insert into score (username, tusername, score) values ('mbenedyktowicz8', 'long', 3.6);
insert into score (username, tusername, score) values ('mbenedyktowicz8', 'dat', 5);
insert into score (username, tusername, score) values ('pwitheridge9', 'tom', 2.8);
insert into score (username, tusername, score) values ('pwitheridge9', 'moi', 3.7);
insert into score (username, tusername, score) values ('pwitheridge9', 'long', 1.5);
insert into score (username, tusername, score) values ('pwitheridge9', 'dat', 1.9);
insert into score (username, tusername, score) values ('csorrilla', 'tom', 3.1);
insert into score (username, tusername, score) values ('csorrilla', 'moi', 1.9);
insert into score (username, tusername, score) values ('csorrilla', 'long', 5);
insert into score (username, tusername, score) values ('csorrilla', 'dat', 5);
insert into score (username, tusername, score) values ('aporttb', 'tom', 3.7);
insert into score (username, tusername, score) values ('aporttb', 'moi', 1.9);
insert into score (username, tusername, score) values ('aporttb', 'long', 1.2);
insert into score (username, tusername, score) values ('aporttb', 'dat', 1.3);
insert into score (username, tusername, score) values ('lniccolsc', 'tom', 2.8);
insert into score (username, tusername, score) values ('lniccolsc', 'moi', 3.9);
insert into score (username, tusername, score) values ('lniccolsc', 'long', 3.6);
insert into score (username, tusername, score) values ('lniccolsc', 'dat', 5);
insert into score (username, tusername, score) values ('dcressard', 'tom', 5);
insert into score (username, tusername, score) values ('dcressard', 'moi', 5);
insert into score (username, tusername, score) values ('dcressard', 'long', 1.0);
insert into score (username, tusername, score) values ('dcressard', 'dat', 5);
insert into score (username, tusername, score) values ('lrennelse', 'tom', 5);
insert into score (username, tusername, score) values ('lrennelse', 'moi', 3.2);
insert into score (username, tusername, score) values ('lrennelse', 'long', 3.7);
insert into score (username, tusername, score) values ('lrennelse', 'dat', 1.6);
insert into score (username, tusername, score) values ('rbaldettif', 'tom', 5);
insert into score (username, tusername, score) values ('rbaldettif', 'moi', 3.5);
insert into score (username, tusername, score) values ('rbaldettif', 'long', 3.1);
insert into score (username, tusername, score) values ('rbaldettif', 'dat', 3.6);
insert into score (username, tusername, score) values ('csharping', 'tom', 2.4);
insert into score (username, tusername, score) values ('csharping', 'moi', 2.1);
insert into score (username, tusername, score) values ('csharping', 'long', 5);
insert into score (username, tusername, score) values ('csharping', 'dat', 3.0);
insert into score (username, tusername, score) values ('syarrallh', 'tom', 3.8);
insert into score (username, tusername, score) values ('syarrallh', 'moi', 3.2);
insert into score (username, tusername, score) values ('syarrallh', 'long', 3.1);
insert into score (username, tusername, score) values ('syarrallh', 'dat', 5);
insert into score (username, tusername, score) values ('hblockeyi', 'tom', 3.7);
insert into score (username, tusername, score) values ('hblockeyi', 'moi', 5);
insert into score (username, tusername, score) values ('hblockeyi', 'long', 1.4);
insert into score (username, tusername, score) values ('hblockeyi', 'dat', 2.1);
insert into score (username, tusername, score) values ('lbernardellij', 'tom', 3.9);
insert into score (username, tusername, score) values ('lbernardellij', 'moi', 3.4);
insert into score (username, tusername, score) values ('lbernardellij', 'long', 2.6);
insert into score (username, tusername, score) values ('lbernardellij', 'dat', 5);
