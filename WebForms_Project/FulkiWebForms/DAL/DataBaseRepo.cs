using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{//ovdje zapravo imamo iRepo jer se spajamo i na system i na bazu
    class DataBaseRepo : IRepo
    {

        public List<Grad> GetGradovi()
        {
            using (var model = new Model())//Model se zapravo spaja na bazu, stavljamo ga u using jer je disposable
            {
                return model.Grad.ToList();
            }
        }


        public List<Osoba> GetOsobe()
        {
            using (var model = new Model())
            {
                //sve tablice koje smo mi pretvorili u liste
                return model.Osoba.Include("Email").Include("Grad").Include("Status").ToList();
            }
        }


        public List<Status> GetStatuse()
        {
            using (var model = new Model())
            {
                return model.Status.ToList();
            }
        }




        public void DodajOsobu(Osoba osoba)
        {
            using (Model model = new Model())
            {

                model.Osoba.Add(osoba);
                //try
                //{

                model.SaveChanges();//znaci jedino kada mijenjas nes u bazi moras sejvat promjene
                                    //}
                                    //catch (Exception e)
                                    //{

                //}
            }
        }

        public bool Prijava(string email, string lozinka, out Osoba ulogiranaOsoba)//prosljedjujemo referencu
        {
            using (var model = new Model())
            {
                foreach (Osoba osoba in model.Osoba.ToList())//daj mi unutar baze model dohvati tablicu "Osoba"
                {
                    //model.Osoba
                    if (osoba.Sifra == lozinka)
                    {
                        foreach (Email mail in osoba.Email.ToList())//kolekcija mailova zato jer smo u bazi linkali mailove na osobu opcenito pomocu FK
                        {
                            if (mail.Naziv == email)
                            {
                                ulogiranaOsoba = osoba;//postavljamo referencu
                                return true;
                            }
                        }
                    }

                }
                ulogiranaOsoba = null;//ako se nije ulogiro ne postoji
                return false;//
            }
        }

        public void AzurirajOsobu(Osoba osoba)
        {
            using (var model = new Model())
            {
                Osoba osobaUBazi = model.Osoba.Find(osoba.IDOsoba);

                osobaUBazi.Ime = osoba.Ime;
                osobaUBazi.Prezime = osoba.Prezime;
                osobaUBazi.Telefon = osoba.Telefon;
                osobaUBazi.StatusID = osoba.StatusID;
                if (osoba.GradID != 0)
                    osobaUBazi.GradID = osoba.GradID;

                model.SaveChanges();
            }
        }

        public void AzurirajEmail(Email email)
        {
            using (Model model = new Model())
            {
                Email dbEmail = model.Email.Find(email.IDEmail);

                dbEmail.Naziv = email.Naziv;

                model.SaveChanges();
            }
        }

        public void ObrisiOsobu(int idOsoba)
        {
            using (Model model = new Model())
            {
                var osoba = model.Osoba.Find(idOsoba);
                var mailovi = model.Email.Where(mail => mail.OsobaID == idOsoba);

                model.Email.RemoveRange(mailovi);
                model.Osoba.Remove(osoba);

                model.SaveChanges();
            }
        }
    }
}
