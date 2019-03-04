using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    public interface IRepo
    {
        List<Osoba> GetOsobe();
        List<Grad> GetGradovi();
        List<Status> GetStatuse();
        void DodajOsobu(Osoba osoba);
        void AzurirajOsobu(Osoba osoba);
        void AzurirajEmail(Email email); 
        bool Prijava(string email, string lozinka,out Osoba ulogiranaOsoba);
        void ObrisiOsobu(int idOsoba);



    }
}
