CREATE DATABASE JavaProjectPopsy
GO
USE JavaProjectPopsy
GO
--Enums

CREATE TABLE PaymentType
(
	ID INT PRIMARY KEY IDENTITY NOT NULL,
	Name NVARCHAR(100) NOT NULL
)

INSERT INTO PaymentType VALUES
('Credit Card'),
('Cash')
select * from PaymentType

CREATE TABLE PredominantEatingOption
(
	ID INT PRIMARY KEY IDENTITY NOT NULL,
	Name NVARCHAR(100) NOT NULL
)

INSERT INTO PredominantEatingOption VALUES
('Home Food'),
('Outside')

CREATE TABLE Sex
(
	ID INT PRIMARY KEY IDENTITY NOT NULL,
	Name NVARCHAR(100) NOT NULL
)

INSERT INTO Sex VALUES
('Male'),
('Female')

CREATE TABLE Medicine
(
	ID INT PRIMARY KEY IDENTITY NOT NULL,
	Name NVARCHAR(100) NOT NULL
)

INSERT INTO Medicine VALUES
('Andol'),
('Aspirin'),
('Lupocet'),
('Linex'),
('Amoksicilin'),
('Penicilin'),
('Caffetin')

select * from Medicine

CREATE TABLE Patient
(
	IDPatient int primary key identity NOT NULL,
	RegistrationDate DATETIME NOT NULL DEFAULT GETDATE(),
	Name nvarchar(100) NOT NULL,
	OIB char(11) NULL,
	SexID INT NOT NULL,
	DateOfBirth DATETIME NOT NULL,
	TelephoneWork NVARCHAR(100) NOT NULL,
	TelephoneHome NVARCHAR(100) NOT NULL,
	Mobile NVARCHAR(100) NULL,
	Fax NVARCHAR(100) NULL,
	Email NVARCHAR(100) NULL,
	Pager NVARCHAR(100) NULL,
	MaritalStatus NVARCHAR(50) NULL,
	NumberOfDependents INT NULL,
	Height FLOAT NULL,
	Weight FLOAT NULL,
	BloodTypeRH NVARCHAR(5) NULL,
	Occupation NVARCHAR(200) NULL,
	GrossAnnualIncome MONEY NULL,
	Vegetarian BIT NULL,
	Smoker BIT NULL,
	ConsumesAlcoholicBeverage BIT NULL,
	UsesStimulants BIT NULL,
	StimulantsUsed NVARCHAR(1000) NULL,
	CoffeeConsumptionPerDay FLOAT NULL,
	TeaConsumptionPerDay FLOAT NULL,
	SoftDrinkConsumptionPerDay FLOAT NULL,
	RegularMeals BIT NULL,
	PredominantEatingOptionID INT NULL,
	StatementOfComplaint NVARCHAR(1000) NOT NULL,
	HistoryOfPreviousTreatment NVARCHAR(MAX) NULL,
	PhysicianOrHospitalTreated NVARCHAR(200) NULL,
	Diabetic BIT NULL,
	Hypertensive BIT NULL,
	CardiacCondition NVARCHAR(1000) NULL,
	RespiratoryCondition NVARCHAR(1000) NULL,
	DigestiveCondition NVARCHAR(1000) NULL,
	OrthopedicCondition NVARCHAR(1000) NULL,
	MuscularCondition NVARCHAR(1000) NULL,
	NeurologicalCondition NVARCHAR(1000) NULL,
	KnownAllergies NVARCHAR(1000) NULL,
	KnownAdverseReactionToSpecificDrugs NVARCHAR(3000) NULL,
	MajorSurgeries NVARCHAR(4000) NULL,
	NextOfKinName NVARCHAR(100) NOT NULL,
	NextOfKinRelationshipWithOutpatient NVARCHAR(MAX) NULL,
	NextOfKinContactAddress NVARCHAR(300) NULL,
	NextOfKinWorkTelephone NVARCHAR(100) NULL,
	NextOfKinHomeTelephone NVARCHAR(100) NULL,
	NextOfKinMobile NVARCHAR(100) NULL,
	NextOfKinPager NVARCHAR(100) NULL,
	NextOfKinFax NVARCHAR(100) NULL,
	NextOfKinEmail NVARCHAR(100) NULL,

	

	CONSTRAINT FK_Patient_Sex FOREIGN KEY(SexID) REFERENCES Sex(ID),
	CONSTRAINT FK_Patient_PredominantEatingOption FOREIGN KEY(PredominantEatingOptionID) REFERENCES PredominantEatingOption(ID)
)

CREATE TABLE Doctor
(
	IDDoctor INT PRIMARY KEY IDENTITY NOT NULL,
	OIB CHAR(11) NULL,
	Name NVARCHAR(100) NOT NULL, 
	SexID INT NOT NULL, 
	DateOfBirth DATETIME NOT NULL, 

	CONSTRAINT FK_Doctor_Sex FOREIGN KEY(SexID) REFERENCES Sex(ID)
)

INSERT INTO Doctor VALUES 
('12345678901','Doctor Ivan Pops',1,'19971010'),
('12345678955','Doctor Pero',1,'19660910'),
('12345678944','Doctor Jure',1,'19210310'),
('12345678933','Doctor Marica',2,'19830210'),
('12345678922','Doctor Krmpotic',2,'19530110')


ALTER TABLE Bill
(
	IDBill INT PRIMARY KEY IDENTITY NOT NULL,
	DateIssued DATETIME DEFAULT GETDATE() NOT NULL,
	PaymentTypeID INT NOT NULL,
	PatientID INT NOT NULL,
	Amount MONEY NOT NULL,

	CONSTRAINT FK_Bill_PaymentType FOREIGN KEY(PaymentTypeID) REFERENCES PaymentType(ID),
	CONSTRAINT FK_Bill_Patient FOREIGN KEY(PatientID) REFERENCES Patient(IDPatient),
)

CREATE TABLE Appointment
(
	IDAppointment INT PRIMARY KEY IDENTITY NOT NULL,
	PatientID INT NOT NULL,
	DoctorID INT NOT NULL,
	Name NVARCHAR(100) NOT NULL,
	Details NVARCHAR(1000) NULL,
	AppointmentDateTime DATETIME NOT NULL,

	CONSTRAINT FK_Appointment_Patient FOREIGN KEY(PatientID) REFERENCES Patient(IDPatient),
	CONSTRAINT FK_Appointment_Doctor FOREIGN KEY(DoctorID) REFERENCES Doctor(IDDoctor)
)

CREATE TABLE PatientMedicine
(
	ID INT PRIMARY KEY IDENTITY NOT NULL,
	DateIssued DATETIME DEFAULT GETDATE() NOT NULL,
	Quantity FLOAT NOT NULL,
	MedicineID INT NOT NULL,
	PatientID INT NOT NULL,
	DoctorID INT NOT NULL,

	CONSTRAINT FK_PatientMedicine_Medicine FOREIGN KEY(MedicineID) REFERENCES Medicine(ID),
	CONSTRAINT FK_PatientMedicine_Patient FOREIGN KEY(PatientID) REFERENCES Patient(IDPatient),
	CONSTRAINT FK_PatientMedicine_Doctor FOREIGN KEY(DoctorID) REFERENCES Doctor(IDDoctor)
)


--PROCEDURES


GO
CREATE PROCEDURE GetPatientMedicine
	@id int
as
	SELECT * FROM PatientMedicine WHERE PatientMedicine.PatientID = @id
go
select * from Medicine
CREATE PROCEDURE GetAppointments
	@id int
as
	SELECT * FROM Appointment WHERE Appointment.PatientID = @id
go

CREATE PROCEDURE GetBills
	@id int
as
	SELECT * FROM Bill WHERE Bill.PatientID = @id
go

CREATE PROCEDURE GetDoctors
as
	SELECT * FROM Doctor 
go

CREATE PROCEDURE GetAllMedicines
as
	SELECT * FROM Medicine
go

CREATE PROCEDURE GetPatients	
as
	SELECT * FROM Patient 
go

CREATE PROCEDURE GetSexes
as
	SELECT * FROM Sex
go

CREATE PROCEDURE GetPaymentType
as
	SELECT * FROM PaymentType
go

CREATE PROCEDURE GetPredominantEatingOptions
as
	SELECT * FROM PredominantEatingOption
go

select * from Appointment
go
CREATE PROCEDURE InsertPatientsAppointments
	@patientID int,
	@doctorID int,
	@name nvarchar(50),
	@details nvarchar(1000),
	@appointmentDateTime datetime
as
	INSERT INTO Appointment(PatientID,DoctorID,Name,Details,AppointmentDateTime)
	VALUES (@patientID,@doctorID,@name,@details,@appointmentDateTime)
go

CREATE PROCEDURE InsertPatientMedicine
	@quantity int,
	@medicineID int,
	@patientID int,
	@doctorID int,
	@dateIssued datetime output
as
	SET @dateIssued = GETDATE()

	INSERT INTO PatientMedicine(DateIssued, Quantity, MedicineID, PatientID, DoctorID)
	VALUES(@dateIssued, @quantity, @medicineID, @patientID, @doctorID)
go

go
CREATE PROCEDURE InsertPatientBill
	@paymentTypeID int,
	@patientID int,
	@amount money,
	@billDateIssued datetime
as
	INSERT INTO Bill(PaymentTypeID,PatientID,Amount,DateIssued)
	VALUES(@paymentTypeID,@patientID,@amount,@billDateIssued)
go


select * from Appointment
select * from Bill
Insert into Appointment(Name,DoctorID,PatientID,Details,AppointmentDateTime)
values ('Receptionist',2,3,'Patient is experiencing mental problems','20181010')
go

CREATE PROCEDURE InsertPatientMini
	@name nvarchar(50),
	@sexID int,
	@dateOfBirth DATETIME,
	@telephoneNumberWork nvarchar(50),
	@telephoneNumberHome nvarchar(50),
	@nextOfKinName nvarchar(100),
	@statementOfComplaint nvarchar(max),
	@insertedID int output,
	@registrationDate datetime output
as
	-- Privremena tablicna varijabla za pohranu više insertanih podataka
	-- Treba nam da popunimo output parametre
	DECLARE @insertedData TABLE
	(
		ID INT,
		RegistrationDate DATETIME
	)

	INSERT INTO Patient(Name,SexID,DateOfBirth,TelephoneWork,TelephoneHome,NextOfKinName,StatementOfComplaint)
	-- Punimo tablicnu varijablu tako da uzimamo podatke iz privremene (temp) tablice 'inserted'
	OUTPUT inserted.IDPatient, inserted.RegistrationDate INTO @insertedData 
	VALUES (@name,@sexID,@dateOfBirth,@telephoneNumberWork,@telephoneNumberHome,@nextOfKinName,@statementOfComplaint)

	-- Popunjavamo output parametre tako da ih dohvatimo iz tablicne varijable
	SELECT
		@insertedID = ID,
		@registrationDate = RegistrationDate
	FROM @insertedData
go

exec GetAppointments 3
select * FROM Appointment
go
CREATE PROCEDURE InsertPatientFull
	
	@name nvarchar(100),
	@oib char(11),
	@sexID int,
	@dateOfBirth datetime,
	@telephoneWork nvarchar(100),
	@telephoneHome nvarchar(100),
	@mobile nvarchar(100),
	@fax nvarchar(100),
	@email nvarchar(100),
	@pager nvarchar(100),
	@maritalStatus nvarchar(50),
	@numberOfDependents int,
	@height float,
	@weight float,
	@bloodTypeRH nvarchar(5),
	@occupation nvarchar(200),
	@grossAnnualIncome money,
	@vegetarian bit,
	@smoker bit,
	@consumesAlcoholicBeverage bit,
	@usesStimulants bit,
	@stimulantsUsed nvarchar(1000),
	@coffeeConsumptionPerDay float,
	@teaConsumptionPerDay float,
	@softDrinkConsumptionPerDay float,
	@regularMeals bit,
	@predominantEatingOptionID int,
	@statementOfComplaint nvarchar(1000),
	@historyOfPreviousTreatment nvarchar(MAX),
	@physicianOrHospitalTreated nvarchar(200),
	@diabetic bit,
	@hypertensive bit,
	@cardiacCondition nvarchar(1000),
	@respiratoryCondition nvarchar(1000),
	@digestiveCondition nvarchar(1000),
	@orthopedicCondition nvarchar(1000),
	@muscularCondition nvarchar(1000),
	@neurologicalCondition nvarchar(1000),
	@knownAllergies nvarchar(1000),
	@knownAdverseReactionToSpecificDrugs nvarchar(3000),
	@majorSurgeries nvarchar(4000),
	@nextOfKinName nvarchar(100),
	@nextOfKinRelationshipWithOutpatient nvarchar(MAX),
	@nextOfKinContactAddress nvarchar(300),
	@nextOfKinWorkTelephone nvarchar(100),
	@nextOfKinHomeTelephone nvarchar(100),
	@nextOfKinMobile nvarchar(100),
	@nextOfKinPager nvarchar(100),
	@nextOfKinFax nvarchar(100),
	@nextOfKinEmail nvarchar(100),
	@insertedID int output,
	@registrationDate datetime output
as

DECLARE @insertedData TABLE
	(
		ID INT,
		RegistrationDate DATETIME
	)

INSERT INTO Patient
(Name,OIB,SexID,DateOfBirth,TelephoneWork,TelephoneHome,Mobile,Fax,Email,Pager,MaritalStatus,NumberOfDependents,Height,Weight,BloodTypeRH,Occupation,GrossAnnualIncome,Vegetarian,Smoker,ConsumesAlcoholicBeverage,UsesStimulants,StimulantsUsed,
CoffeeConsumptionPerDay,TeaConsumptionPerDay,SoftDrinkConsumptionPerDay,RegularMeals,PredominantEatingOptionID,StatementOfComplaint,HistoryOfPreviousTreatment,PhysicianOrHospitalTreated,Diabetic,Hypertensive,CardiacCondition,RespiratoryCondition,
DigestiveCondition,OrthopedicCondition,MuscularCondition,NeurologicalCondition,KnownAllergies,KnownAdverseReactionToSpecificDrugs,MajorSurgeries,NextOfKinName,NextOfKinRelationshipWithOutpatient,NextOfKinContactAddress,NextOfKinWorkTelephone,
NextOfKinHomeTelephone,NextOfKinMobile,NextOfKinPager,NextOfKinFax,NextOfKinEmail)
OUTPUT inserted.IDPatient, inserted.RegistrationDate INTO @insertedData 
VALUES
(@name,@oIB,@sexID,@dateOfBirth,@telephoneWork,@telephoneHome,@mobile,@fax,@email,@pager,@maritalStatus,@numberOfDependents,@height,@weight,@bloodTypeRH,@occupation,@grossAnnualIncome,@vegetarian,@smoker,@consumesAlcoholicBeverage,@usesStimulants,@stimulantsUsed,@coffeeConsumptionPerDay,@teaConsumptionPerDay,@softDrinkConsumptionPerDay,@regularMeals,@predominantEatingOptionID,@statementOfComplaint,@historyOfPreviousTreatment,@physicianOrHospitalTreated,@diabetic,@hypertensive,@cardiacCondition,@respiratoryCondition,@digestiveCondition,@orthopedicCondition,@muscularCondition,@neurologicalCondition,@knownAllergies,@knownAdverseReactionToSpecificDrugs,@majorSurgeries,@nextOfKinName,@nextOfKinRelationshipWithOutpatient,@nextOfKinContactAddress,@nextOfKinWorkTelephone,@nextOfKinHomeTelephone,@nextOfKinMobile,@nextOfKinPager,@nextOfKinFax,@nextOfKinEmail)

go
--SELECT * FROM Appointment
CREATE PROCEDURE UpdateAppointment
	@appointmentID int,
	@details nvarchar(1000),
	@name nvarchar(100)
as
UPDATE Appointment SET
	Details = @details,
	Name = @name
WHERE IDAppointment = @appointmentID

go
SELECT * FROM Doctor
SELECT * FROM Appointment
SELECT * FROM Sex
SELECT * FROM Patient
SELECT * FROM Bill
select * from PredominantEatingOption