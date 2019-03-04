using FulkiProject2.Models;
using Microsoft.ApplicationBlocks.Data;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;

public class Repo
{
    public static DataSet ds { get; set; }
    private static string cs = ConfigurationManager.ConnectionStrings["cs"].ConnectionString;


    public static Kupac GetKupac(int IDKupac)
    {
        return GetTop10Kupaca().Single(k => k.IDKupac == IDKupac);
    }

    public static Drzava GetDrzava(int GradID)
    {
        ds = SqlHelper.ExecuteDataset(cs, "GetDrzava", GradID);
        DataRow row = ds.Tables[0].Rows[0];

        Drzava d = new Drzava();
        d.IDDrzava = (int)row["IDDrzava"];
        d.Naziv = row["Naziv"].ToString();

        return d;
    }

    public static IEnumerable<Kupac> GetTop10Kupaca()
    {
        ds = SqlHelper.ExecuteDataset(cs, "DohvatiKupce");
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            yield return new Kupac
            {
                IDKupac = (int)row["IDKupac"],
                Ime = row["Ime"].ToString(),
                Prezime = row["Prezime"].ToString(),
                Email = row["Email"].ToString(),
                Telefon = row["Telefon"].ToString(),
                GradID = row["GradID"] != DBNull.Value ? (int)row["GradID"] : 1,
                DrzavaID = row["IDDrzava"] != DBNull.Value ? (int)row["IDDrzava"] : 1
            };
        }
    }

    public static Grad GetGrad(int IDGrad)
    {
        DataRow row = SqlHelper.ExecuteDataset(cs, "GetGrad", IDGrad).Tables[0].Rows[0];
        return new Grad
        {
            IDGrad = (int)row["IDGrad"],
            DrzavaID = (int)row["DrzavaID"],
            Naziv = row["Naziv"].ToString()
        };
    }

    public static List<Grad> GetGradovi()
    {
        List<Grad> kolekcija = new List<Grad>();

        ds = SqlHelper.ExecuteDataset(cs, "GetGradovi");
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            kolekcija.Add(new Grad
            {
                IDGrad = (int)row["IDGrad"],
                Naziv = row["Naziv"].ToString()
            });
        }
        return kolekcija;
    }

    public static int UpdateKupac(Kupac kupac)
    {
        return SqlHelper.ExecuteNonQuery(cs, "UpdateKupac", kupac.IDKupac, kupac.Ime, kupac.Prezime, kupac.Email, kupac.Telefon, kupac.GradID);
    }

    public static int DeleteKupac(int id)
    {
        return SqlHelper.ExecuteNonQuery(cs, "DeleteKupac", id);
    }

    public static int InsertKupac(Kupac kupac)
    {
        return SqlHelper.ExecuteNonQuery(cs, "InsertKupac", kupac.Ime, kupac.Prezime, kupac.Email, kupac.Telefon, kupac.GradID);
    }

    public static List<Racun> GetRacuni()
    {
        List<Racun> list = new List<Racun>();

        ds = SqlHelper.ExecuteDataset(cs, "GetRacuni");
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            list.Add(new Racun
            {
                IDracun = (int)row["IDRacun"],
                DatumIzdavanja = (DateTime)row["DatumIzdavanja"],
                BrojRacuna = (string)row["BrojRacuna"],
                KupacID = (int)row["KupacID"],
                //ako nije null vrati ovo prvo , ako je vrati 279
                KomercijalistID = row["KomercijalistID"] != DBNull.Value ?
                (int)row["KomercijalistID"] : 279,
                KreditnaKarticaID = row["KreditnaKarticaID"] != DBNull.Value ?
                (int)row["KreditnaKarticaID"] : 5618
            });
        }
        return list;
    }

    public static KreditnaKartica GetKreditnaKarticaForRacun(int id)
    {
        KreditnaKartica kk = new KreditnaKartica();
        ds = SqlHelper.ExecuteDataset(cs, "getKreditnaKarticaForRacun", id);
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            kk.IDKreditnaKartica = (int)row["IDKreditnaKartica"];
            kk.Tip = row["Tip"].ToString();
            kk.Broj = row["Broj"].ToString();
            kk.IstekMjesec = Convert.ToInt16(row["IstekMjesec"]);
            kk.IstekGodina = Convert.ToInt16(row["IstekGodina"]);

        }
        return kk;
    }

    public static Komercijalist GetKomercijalistaForRacun(int id)
    {
        Komercijalist k = new Komercijalist();
        ds = SqlHelper.ExecuteDataset(cs, "getKomercijalistaForRacun", id);
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            k.IDKomercijalist = (int)row["IDKomercijalist"];
            k.Ime = row["Ime"].ToString();
            k.Prezime = row["Prezime"].ToString();
            k.StalniZaposlenik = (bool)row["StalniZaposlenik"];
        }
        return k;
    }

    public static List<Stavka> GetStavkeForRacun(int id)
    {

        Stavka s = new Stavka();
        List<Stavka> list = new List<Stavka>();
        ds = SqlHelper.ExecuteDataset(cs, "getStavkeForRacun", id);
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            list.Add(new Stavka
            {
                IDStavka = (int)row["IDStavka"],
                RacunID = (int)row["RacunID"],
                Kolicina = Convert.ToInt16(row["Kolicina"]),
                ProizvodID = (int)row["ProizvodID"],
                CijenaPoKomadu = (decimal)row["CijenaPoKomadu"],
                PopustUPostocima = (decimal)row["PopustUPostocima"],
                UkupnaCijena = (decimal)row["UkupnaCijena"],
            });
        }
        return list;
    }

    public static Proizvod GetProizvodForStavka(int id)
    {
        Proizvod p = new Proizvod();
        ds = SqlHelper.ExecuteDataset(cs, "getKupljeniProizvodi", id);
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            p.IDProizvod = (int)row["IDProizvod"];
            p.Naziv= row["Naziv"].ToString();
            p.BrojProizvoda = row["BrojProizvoda"].ToString();
            p.Boja = row["Boja"] != DBNull.Value ? row["Boja"].ToString() : "None";
            p.Potkategorija = row["Potkategorija"].ToString();
            p.Kategorija = row["Kategorija"].ToString();
        }
        return p;

    }

    public static List<Drzava> GetDrzave()
    {
        Drzava d = new Drzava();
        List<Drzava> list = new List<Drzava>();
        ds = SqlHelper.ExecuteDataset(cs, "GetDrzave");
        foreach (DataRow row in ds.Tables[0].Rows)
        {
            list.Add(new Drzava
            {
                IDDrzava = (int)row["IDDrzava"],
                Naziv = row["Naziv"].ToString(),
            });
        }
        return list;
    }
}
