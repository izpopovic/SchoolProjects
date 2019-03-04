

--21.4 RWA baza podataka
CREATE DATABASE Fulkibase
GO
USE Fulkibase
GO

CREATE TABLE Grad(
IDGrad int primary key identity not null, --identity = auto increment (1,1)
Naziv nvarchar(50) not null
)

CREATE TABLE Status(
IDStatus int primary key identity not null,
Naziv nvarchar(50) not null
)

INSERT INTO Grad VALUES 
('Zagreb'), --1 red u tablici 
('Varaždin'),--2red itd itd...
('Split'),
('Rijeka'),
('Pula'),
('Osijek'),
('Dubrovnik')


InSERT INTO Status VALUES
('User'),
('Administrator')


CREATE TABLE Osoba(
IDOsoba int primary key identity,
Ime nvarchar(50) not null,
Prezime nvarchar(50) not null,
Telefon nvarchar(35) not null,
Sifra nvarchar(45) not null,
StatusID int not null,
GradID int not null,


foreign key (StatusID) REFERENCES Status(IDStatus),
foreign key (GradID) REFERENCES Grad(IDGrad)
)


CREATE TABLE Email(
IDEmail int primary key identity,
Naziv nvarchar(50) not null,
OsobaID int not null,

foreign key (OsobaID) references Osoba(IDOsoba)--ovisno o ID osobe on ce imati te i te emaile
)
