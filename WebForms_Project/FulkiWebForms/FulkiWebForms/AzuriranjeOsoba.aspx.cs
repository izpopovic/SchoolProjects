using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class AzuriranjeOsoba : MyPage
    {

        IRepo repo = RepoFactory.Repozitorij;
        protected void Page_Load(object sender, EventArgs e)
        {
            ProvjeriKorisnika();
            (Master as Main).ChangeSelectedButton(2);

            List<Osoba> osobe = repo.GetOsobe();

            // Ako dode id u parametru, uredujemo samo jednu osobu
            if (int.TryParse(Request.QueryString["Id"], out int idOsobe))
            {
                Osoba osoba = osobe.First(o => o.IDOsoba == idOsobe);

                DodajOsobu(osoba);
            }
            else
            {
                //za svaku osobu povuc njene podatke iz repozitorija te ih dodati u osobu kontrola

                foreach (Osoba osoba in osobe)
                {
                    DodajOsobu(osoba);
                }
            }

            string azuriranje = (string)Session["azuriranje"];
            string brisanje = (string)Session["brisanje"];

            if (azuriranje != null)
            {
                this.Toast(Utils.ToastrSuccess, azuriranje);
                Session.Remove("azuriranje");
            }
            if (brisanje != null) { 
                this.Toast(Utils.ToastrWarning, brisanje);
                Session.Remove("brisanje");
            }
        }

        private void DodajOsobu(Osoba osoba)
        {
            OsobaKontrola kontrola = (OsobaKontrola)LoadControl("~/OsobaKontrola.ascx");
            kontrola.InsertDataForPerson(osoba);
            OsobeKontrola.Controls.Add(kontrola);
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            int deleteId = (int)Session["deleteId"];
            RepoFactory.Repozitorij.ObrisiOsobu(deleteId);
            this.RunJS("$('#modalDialog').modal('hide');");
            Session["brisanje"] = "Korisnik uspješno obrisan.";

            if (string.IsNullOrEmpty(Request.QueryString["Id"]))
                this.Refresh();
            else
                Response.Redirect("~/ListaOsoba.aspx");
        }
    }
}