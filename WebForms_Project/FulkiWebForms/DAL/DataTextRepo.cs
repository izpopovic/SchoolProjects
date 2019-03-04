using DAL.Converters;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    //na personu radis metode parfsefromfile i parseforfile 
    //zoves ih ovdje i vuces podatke kao i u database repo
    //mailovi zasebno parsaas
    // napraviti auto increment za id, dohvatis zadnju i povecas za 1
    class DataTextRepo : IRepo
    {
        private readonly string desktop = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
        private const string osobeTxt = "osobe.txt";
        private const string gradoviTxt = "gradovi.txt";
        private const string statusiTxt = "statusi.txt";

        public DataTextRepo()
        {
            RepoFactory.UseDatabase = true;

            IRepo repo = RepoFactory.Repozitorij;

            string osobePutanja = Putanja(osobeTxt);
            string gradoviPutanja = Putanja(gradoviTxt);
            string statusiPutanja = Putanja(statusiTxt);

            if (!File.Exists(osobePutanja))
                File.WriteAllLines(osobePutanja, repo.GetOsobe().Select(osoba => OsobaKonverter.ParseForFile(osoba)));
            if (!File.Exists(gradoviPutanja))
                File.WriteAllLines(gradoviPutanja, repo.GetGradovi().Select(osoba => GradKonverter.ParseForFile(osoba)));
            if (!File.Exists(statusiPutanja))
                File.WriteAllLines(statusiPutanja, repo.GetStatuse().Select(osoba => StatusKonverter.ParseForFile(osoba)));

            RepoFactory.UseDatabase = false;
        }

        private string Putanja(string datoteka) => Path.Combine(desktop, datoteka);

        public void AzurirajEmail(Email noviEmail)
        { 
            List<Osoba> osobe = GetOsobe();

            foreach (Osoba osoba in osobe)
            {
                foreach (Email email in osoba.Email)
                {
                    if (email.IDEmail == noviEmail.IDEmail)
                    {
                        email.Naziv = noviEmail.Naziv;
                        File.WriteAllLines(Putanja(osobeTxt), osobe.Select(o => OsobaKonverter.ParseForFile(o)));
                        return;
                    }
                }
            }
        }

        public void AzurirajOsobu(Osoba osoba)
        {
            List<Osoba> osobe = GetOsobe();

            for (int i = 0; i < osobe.Count; i++)
            {
                if (osobe[i].IDOsoba == osoba.IDOsoba)
                {
                    if (string.IsNullOrEmpty(osoba.Sifra))
                        osoba.Sifra = osobe[i].Sifra;
                    if (osoba.Email == null || osoba.Email.Count == 0)
                        osoba.Email = osobe[i].Email;

                    osobe[i] = osoba;
                    File.WriteAllLines(Putanja(osobeTxt), osobe.Select(o => OsobaKonverter.ParseForFile(o)));
                    return;
                }
            }
        }

        public void DodajOsobu(Osoba osoba)
        {
            List<Osoba> osobe = GetOsobe();

            osobe.Add(osoba);

            File.WriteAllLines(Putanja(osobeTxt), osobe.Select(o => OsobaKonverter.ParseForFile(o)));
        }

        public List<Grad> GetGradovi()
        {
            return File.ReadAllLines(Putanja(gradoviTxt))
                .Select(line => GradKonverter.ParseFromFile(line))
                .ToList();
        }

        public List<Osoba> GetOsobe()
        {
            return File.ReadAllLines(Putanja(osobeTxt))
                .Select(line => OsobaKonverter.ParseFromFile(line))
                .ToList();
        }

        public List<Status> GetStatuse()
        {
            return File.ReadAllLines(Putanja(statusiTxt))
                .Select(line => StatusKonverter.ParseFromFile(line))
                .ToList();
        }

        public void ObrisiOsobu(int idOsoba)
        {
            List<Osoba> osobe = GetOsobe();

            Osoba osobaKojuTrebaObrisat = osobe.Find(osoba => osoba.IDOsoba == idOsoba);

            osobe.Remove(osobaKojuTrebaObrisat);

            File.WriteAllLines(Putanja(osobeTxt), osobe.Select(osoba => OsobaKonverter.ParseForFile(osoba)));
        }

        public bool Prijava(string email, string lozinka, out Osoba ulogiranaOsoba)
        {
            foreach (Osoba osoba in GetOsobe())
            {
                
                if (osoba.Sifra == lozinka)
                {
                    foreach (Email mail in osoba.Email)
                    {
                        if (mail.Naziv == email)
                        {
                            ulogiranaOsoba = osoba;
                            return true;
                        }
                    }
                }
            }
            ulogiranaOsoba = null;
            return false;
        }
    }
}
