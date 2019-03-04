using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FulkiProject2.Models
{
    public class Racun
    {
        public int IDracun { get; set; }
        public DateTime DatumIzdavanja { get; set; }
        public string BrojRacuna { get; set; }
        public int KupacID { get; set; }
        public int KomercijalistID { get; set; }
        public int KreditnaKarticaID { get; set; }
    }
}