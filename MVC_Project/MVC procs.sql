USE AdventureWorksOBP
go

select* from Racun	

create proc getUsers
as
begin
select* from AppUser
end

create proc getDrzave
as
begin
select* from Drzava
end

create proc insertAppUser
	@username nvarchar(10),
	@password nvarchar(10),
	@email nvarchar(25),
	@ime nvarchar(25),
	@prezime nvarchar(25)
as
begin
	insert into AppUser values (@username,@password,@email,@ime,@prezime)
end

create procedure GetKomercijalist
	@idKomercijalist int
as
begin
	select Ime, Prezime from Komercijalist where Komercijalist.IDKomercijalist = @idKomercijalist
end
go

CREATE PROCEDURE GetDrzava
	@gradID int
AS
BEGIN
	select * from Drzava where 
	IDDrzava in (select DrzavaID from Grad where IDGrad=@gradID)
END
GO

CREATE PROCEDURE GetGradovi
AS
BEGIN
	select * from Grad
END
GO

create procedure getGradoviForDrzava
	@IDDrzava int
as
begin
	select* from Grad where Grad.DrzavaID=@IDDrzava
end

create procedure getKupacFromGrad
	@IDKupac int
as
begin
	select* from Kupac inner join Grad 
	on Kupac.GradID=Grad.IDGrad
end

select* from Kupac

create procedure getRacunForKupac
	@IDKupac int
as
begin
	select* from Racun where KupacID=@IDKupac
end

alter procedure getRacuni
as
begin
	select top 50 * from Racun 
end

create procedure getKomercijalistaForRacun
	@IDKomercijalist int
as
begin
	select* from Komercijalist where IDKomercijalist=@IDKomercijalist
end

create procedure getKreditnaKarticaForRacun
	@IDKreditnaKartica int
as
begin
	select* from KreditnaKartica where IDKreditnaKartica=@IDKreditnaKartica
end

create procedure GetGrad
	@IDGrad int
as
begin
	select * from Grad where IDGrad = @IDGrad
end
go

select* from Racun

alter procedure getKupljeniProizvodi
	@idRacun int
as
begin
	select p.IDProizvod,p.Naziv,p.BrojProizvoda,p.Boja,
	Potkategorija.Naziv as 'Potkategorija'
	,Kategorija.Naziv as 'Kategorija' from Stavka as s
	inner join Proizvod as p
	on s.ProizvodID=p.IDProizvod
	inner join Potkategorija 
	on Potkategorija.IDPotkategorija=p.PotkategorijaID
	inner  join Kategorija on Kategorija.IDKategorija=Potkategorija.KategorijaID
	where s.RacunID=@idRacun
end
go

select* from Proizvod

create proc getStavkeByRacun
	 @IDRacun int
as
begin
	select* from Stavka where RacunID=@IDRacun
end


select* from Komercijalist

CREATE PROCEDURE GetKupac
	@id int
AS
BEGIN
	select * from Kupac where IDKupac=@id
END
GO


CREATE PROCEDURE GradKupca
	@gradID int
AS
BEGIN
	select * from Grad where IDGrad=@gradID
END
GO

alter proc DohvatiKupce
AS
BEGIN
	select top 10 *,Drzava.IDDrzava from Kupac 
	inner join Grad on Kupac.GradID=Grad.IDGrad
	inner join Drzava on Grad.DrzavaID=Drzava.IDDrzava
	--order by IDKupac desc
END

GO



CREATE PROCEDURE UpdateKupac
	@id int,
	@ime nvarchar(50),
	@prezime nvarchar(50),
	@email nvarchar(50),
	@telefon nvarchar(25),
	@gradID int
AS
BEGIN
	update Kupac set Ime=@ime, Prezime=@prezime, Email=@email, Telefon=@telefon, GradID=@gradID where IDKupac=@id 
END
GO

alter procedure deleteKupac
 @IDKupac int
 as
 begin
	delete from Stavka where RacunID in (SELECT IDRacun FROM Racun WHERE Racun.KupacID=@IDKupac)
	delete from Racun where Racun.KupacID=@IDKupac
	delete from Kupac where Kupac.IDKupac=@IDKupac
end

Select * from Kupac

CREATE PROCEDURE InsertKupac
	@ime nvarchar(50),
	@prezime nvarchar(50),
	@email nvarchar(50),
	@telefon nvarchar(25),
	@gradID int
AS
BEGIN
	insert into Kupac values(@ime,@prezime,@email,@telefon,@gradID)
END
GO

exec sp_help KreditnaKartica

select* from Kupac

create proc getKomercijaliste
as
begin
	select* from Komercijalist
end

create proc getKreditneKarticeTip
as
begin
	select distinct ID,Tip from KreditnaKartica
end

create proc getRacuni
as 
begin
	select top 50 * from Racun 
end

select * from Komercijalist

