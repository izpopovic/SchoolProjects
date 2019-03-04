using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Converters
{
    public class MailKonverter
    {
        private const char DELIMITER = '&';

        public static string FormatForFile(Email email)
        {
            return string.Join(DELIMITER.ToString(), email.Naziv, email.OsobaID, email.IDEmail);
        }

        public static Email FormatFromFile(string line)
        {
            //ipopovic@racunarstvo.hr & 2 & 3 
            string[] lajna = line.Split(DELIMITER);

            Email email = new Email()
            {
                Naziv = lajna[0],
                OsobaID = int.Parse(lajna[1]),
                IDEmail = int.Parse(lajna[2])
            };

            return email;
        }
    }
}
