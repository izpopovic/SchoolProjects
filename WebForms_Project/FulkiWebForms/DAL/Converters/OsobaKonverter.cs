using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Converters
{
    public class OsobaKonverter
    {
        private const char DELIMITER = '|';
        private const char EMAIL_DELIMITER = '%';


        public static string ParseForFile(Osoba o)
        {
            IEnumerable<string> emailsForFile = o.Email.Select(email => MailKonverter.FormatForFile(email));
            string emails = string.Join(EMAIL_DELIMITER.ToString(), emailsForFile);

            return string.Join(DELIMITER.ToString(), o.Ime, o.Prezime, o.GradID, o.IDOsoba, o.Sifra, o.StatusID, o.Telefon, emails);
        }

        public static Osoba ParseFromFile(string line)
        {
            if (line == null)
                return null;
            //Ivan|Popovic|4|10|123|2|019321321|ipopovic@racunarstvo.hr&2&3%ipopov@racunarstvo.hr&2&4

            string[] lajnaO = line.Split(DELIMITER);

            Osoba osoba = new Osoba
            {
                Ime = lajnaO[0],
                Prezime = lajnaO[1],
                GradID = int.Parse(lajnaO[2]),
                IDOsoba = int.Parse(lajnaO[3]),
                Sifra = lajnaO[4],
                StatusID = int.Parse(lajnaO[5]),
                Telefon = lajnaO[6]
            };
            List<Email> mailovi = new List<Email>();
            string[] lajnaMajlovi = lajnaO[7].Split(EMAIL_DELIMITER);
            foreach (string mail in lajnaMajlovi)
            {
                if (!string.IsNullOrEmpty(mail))
                    mailovi.Add(MailKonverter.FormatFromFile(mail));
            }
            osoba.Email = mailovi;
            return osoba;
        }
    }
}
