using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FulkiProject2.Models
{
    public class Komercijalist
    {
        public int IDKomercijalist { get; set; }
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public bool StalniZaposlenik { get; set; }
    }
}