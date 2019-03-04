using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FulkiProject2.Models
{
    public class KreditnaKartica
    {
        public int IDKreditnaKartica { get; set; }
        public string Tip { get; set; }
        public string Broj { get; set; }
        public int IstekMjesec { get; set; }
        public int IstekGodina { get; set; }
    }
}