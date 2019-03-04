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
                GradID = row["GradID"] != DBNull.Value ? (int)row["GradID"] : 1
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

}
