-- СОЗДАНИЕ БАЗЫ ДАННЫХ

CREATE DATABASE cinema;

-- СОЗДАНИЕ ТАБЛИЦ

CREATE TABLE halls (
                       HallID SMALLINT AUTO_INCREMENT,
                       HallName VARCHAR(20) NOT NULL,
                       Type ENUM('standard', '3D', 'Dolby Atoms', 'IMAX') NOT NULL,
                       NumberOfRows TINYINT NOT NULL,
                       NumberOfPlaces TINYINT NOT NULL,
                       PRIMARY KEY (HallID)
) ENGINE=INNODB;

CREATE TABLE movies (
                        MovieID SMALLINT AUTO_INCREMENT,
                        MovieName VARCHAR(30) NOT NULL,
                        Duration SMALLINT NOT NULL,
                        Country VARCHAR(20) NOT NULL,
                        Genre VARCHAR(30) NOT NULL,
                        ProductionYear SMALLINT NOT NULL,
                        Producer VARCHAR(30) NOT NULL,
                        PRIMARY KEY (MovieID)
) ENGINE=INNODB;

CREATE TABLE visitors (
                          VisitorID SMALLINT AUTO_INCREMENT,
                          FirstName VARCHAR(20) NOT NULL,
                          LastName VARCHAR(20) NOT NULL,
                          PhoneNumber VARCHAR(20) NOT NULL,
                          Balance MEDIUMINT NOT NULL,
                          Password VARCHAR(20) NOT NULL,
                          Status ENUM('current', 'former') DEFAULT 'current',
                          RegistrationDate DATETIME DEFAULT NOW(),
                          DeletionDate DATETIME DEFAULT NULL,
                          PRIMARY KEY (VisitorID)
) ENGINE=INNODB;

CREATE TABLE admins (
                        AdminID SMALLINT AUTO_INCREMENT,
                        FirstName VARCHAR(20) NOT NULL,
                        LastName VARCHAR(20) NOT NULL,
                        PhoneNumber VARCHAR(20) NOT NULL,
                        Password VARCHAR(20) NOT NULL,
                        Status ENUM('current', 'former') DEFAULT 'current',
                        NumberOfCompletedAssignments TINYINT DEFAULT 0,
                        DateOfAppointment DATETIME DEFAULT NOW(),
                        DateOfDismissal DATETIME DEFAULT NULL,
                        PRIMARY KEY (AdminID)
) ENGINE=INNODB;

CREATE TABLE managers (
                          ManagerID SMALLINT AUTO_INCREMENT,
                          FirstName VARCHAR(20) NOT NULL,
                          LastName VARCHAR(20) NOT NULL,
                          PhoneNumber VARCHAR(20) NOT NULL,
                          Password VARCHAR(20) NOT NULL,
                          Status ENUM('current', 'former') DEFAULT 'current',
                          DateOfAppointment DATETIME DEFAULT NOW(),
                          DateOfDismissal DATETIME DEFAULT NULL,
                          PRIMARY KEY (ManagerID)
) ENGINE=INNODB;

CREATE TABLE reviews (
                         ReviewID SMALLINT PRIMARY KEY AUTO_INCREMENT,
                         VisitorID SMALLINT NOT NULL,
                         AdminID SMALLINT DEFAULT NULL,
                         Review VARCHAR(80) NOT NULL,
                         DateOfReview DATETIME NOT NULL,
                         Reply VARCHAR(80) DEFAULT NULL,
                         FOREIGN KEY (VisitorID) REFERENCES visitors(VisitorID) ON DELETE CASCADE,
                         FOREIGN KEY (AdminID) REFERENCES admins(AdminID) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE assignments (
                             AssignmentID SMALLINT PRIMARY KEY AUTO_INCREMENT,
                             ManagerID SMALLINT NOT NULL,
                             AdminID SMALLINT DEFAULT NULL,
                             Assignment VARCHAR(80) NOT NULL,
                             Status ENUM('done', 'not done') DEFAULT 'not done',
                             DateOfAppointment DATETIME DEFAULT NOW(),
                             DateOfCompletion DATETIME DEFAULT NULL,
                             FOREIGN KEY (ManagerID) REFERENCES managers(ManagerID) ON DELETE CASCADE,
                             FOREIGN KEY (AdminID) REFERENCES admins(AdminID) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE sessions (
                          SessionID SMALLINT PRIMARY KEY AUTO_INCREMENT,
                          HallID SMALLINT NOT NULL,
                          MovieID SMALLINT NOT NULL,
                          AdminID SMALLINT NOT NULL,
                          StartTime DATETIME NOT NULL,
                          EndTime DATETIME NOT NULL,
                          NumberOfEconomyTickets SMALLINT NOT NULL,
                          NumberOfStandardTickets SMALLINT NOT NULL,
                          NumberOfExpensiveTickets SMALLINT NOT NULL,
                          NumberOfAvailableTickets SMALLINT NOT NULL,
                          NumberOfSoldTickets SMALLINT DEFAULT 0,
                          FOREIGN KEY (HallID) REFERENCES halls(HallID) ON DELETE CASCADE,
                          FOREIGN KEY (MovieID) REFERENCES movies(MovieID) ON DELETE CASCADE,
                          FOREIGN KEY (AdminID) REFERENCES admins(AdminID) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE tickets (
                         TicketID SMALLINT PRIMARY KEY AUTO_INCREMENT,
                         SessionID SMALLINT NOT NULL,
                         VisitorID SMALLINT DEFAULT NULL,
                         TicketPrice SMALLINT NOT NULL,
                         Roww SMALLINT NOT NULL,
                         Place SMALLINT NOT NULL,
                         Type ENUM('economy', 'standard', 'expensive') NOT NULL,
                         Status ENUM('available', 'sold') DEFAULT 'available',
                         DateOfPurchase DATETIME DEFAULT NULL,
                         FOREIGN KEY (SessionID) REFERENCES sessions(SessionID) ON DELETE CASCADE,
                         FOREIGN KEY (VisitorID) REFERENCES visitors(VisitorID) ON DELETE CASCADE
) ENGINE=INNODB;

-- ЗАПОЛНЕНИЕ ТАБЛИЦ

INSERT INTO halls (hallName, type, numberOfRows, numberOfPlaces)
VALUES('H1', 'standard', 6, 4),
      ('H2', 'standard', 5, 4),
      ('H3', 'standard', 6, 4),
      ('H4', 'standard', 4, 4),
      ('H5', '3D', 4, 4),
      ('H6', 'Dolby Atoms', 4, 4),
      ('H7', 'IMAX', 4, 4);

INSERT INTO movies(movieName, duration, country, genre, productionYear, producer)
VALUES('The Green Mile', 189, 'USA', 'drama', 1999, 'Frank Darabont'),
      ('The Shawshank Redemption', 142, 'USA', 'drama', 1994, 'Frank Darabont'),
      ('Forrest Gump', 142, 'USA', 'drama', 1994, 'Robert Zemekis'),
      ('LR: The Return of the King', 201, 'New Zealand', 'fantasy', 2003, 'Peter Jackson'),
      ('Coco', 105, 'USA', 'cartoon', 2017, 'Lee Ankric'),
      ('Back to the Future', 116, 'USA', 'fantastic', 1985, 'Robert Zemekis'),
      ('Interstellar', 169, 'USA', 'fantastic', 2014, 'Christopher Nolan');

INSERT INTO visitors(firstName, lastName, phoneNumber, password, balance, status, registrationDate, deletionDate)
VALUES('Toge', 'Inumaki', '+996 700 123 321',  'toin', 150000, 'current', '2022-09-19 16:52:00', null),
      ('Maki', 'Zenin', '+996 700 234 432', 'maze', 150000, 'current', '2022-09-20 16:52:00', null),
      ('Aoi', 'Todo', '+996 700 345 543', 'aoto', 100000, 'current', '2022-09-21 16:52:00', null),
      ('Noritoshi', 'Kamo', '+996 700 456 654', 'noka', 100000, 'current', '2022-09-22 16:52:00', null),
      ('Kento', 'Nanami', '+996 700 567 765', 'kena', 100000, 'former', '2022-09-23 16:52:00', '2022-10-31 17:00:00');

INSERT INTO managers(firstName, lastName, phoneNumber, password, status,
                     dateOfAppointment, dateOfDismissal)
VALUES('Satoru', 'Gojo', '+996 700 111 111', 'sago', 'former', '2022-09-19 16:52:00', '2022-10-31 16:52:00'),
      ('Suguru', 'Geto', '+996 700 222 222', 'suge', 'current', '2022-10-31 17:00:00', null);

INSERT INTO admins(firstName, lastName, phoneNumber, password, status,
                   numberOfCompletedAssignments, dateOfAppointment, dateOfDismissal)
VALUES('Ryomen', 'Sukuna', '+996 700 333 333', 'rysu', 'current', 3, '2022-09-19 16:52:00', null),
      ('Toji', 'Fushiguro', '+996 700 444 444', 'tofu', 'former', 2, '2022-09-20 16:52:00', '2022-10-31 16:52:00'),
      ('Yuta', 'Okkotsu', '+996 700 555 555', 'yuok', 'current', 2, '2022-09-21 16:52:00', null);

INSERT INTO assignments(managerID, adminID, assignment, status, dateOfAppointment, dateOfCompletion)
VALUES(1, 1, 'add movie "The Green Mile" (1999)', 'done', '2022-10-01 10:00:00', '2022-10-01 15:30:00'),
      (1, 2, 'add movie "The Shawshank Redemption" (1994) ', 'done', '2022-10-03 11:00:00', '2022-10-03 16:30:00'),
      (1, 3, 'add movie "Forrest Gump"(1994) ', 'done', '2022-10-05 12:00:00', '2022-10-05 17:30:00'),
      (1, 2, 'add movie "LR: The Return of the King" (1994) ', 'done', '2022-10-03 11:00:00', '2022-10-03 16:30:00'),
      (1, 2, 'add movie "Coco"(2017)', 'done', '2022-10-07 10:00:00', '2022-10-01 15:30:00'),
      (2, 1, 'add movie "Back to the Future"(1985) ', 'done', '2022-11-05 11:00:00', '2022-11-05 16:30:00'),
      (2, 3, 'add movie "Interstellar"(2014) ', 'done', '2022-11-10 12:00:00', '2022-11-10 17:30:00');

INSERT INTO reviews(visitorID, adminID, review, dateOfReview, reply)
VALUES(1, 3, 'Bonito flakes', '2022-10-07 17:00:00', 'mmm...okay. Thanks for review!'),
      (3, 1, 'I went to movie with Takada. She liked it', '2022-10-14 17:00:00', 'Baka.'),
      (4, null, 'I want more bloody scenes in movies.', '2022-10-21 17:00:00', null);




CREATE TABLE sessions (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          id_hall INT NOT NULL,
                          id_movie INT NOT NULL,
                          start_time DATETIME NOT NULL,
                          end_time DATETIME NOT NULL,
                          NumberOfAvailableTickets SMALLINT NOT NULL,
                          NumberOfSoldTickets SMALLINT DEFAULT 0,
                          FOREIGN KEY (HallID) REFERENCES halls(HallID) ON DELETE CASCADE,
                          FOREIGN KEY (MovieID) REFERENCES movies(MovieID) ON DELETE CASCADE,
                          FOREIGN KEY (AdminID) REFERENCES admins(AdminID) ON DELETE CASCADE
) ENGINE=INNODB;a
