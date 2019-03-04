using DAL;
using DAL.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class Login : MyPage
    {
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            if(DohvatiUlogiranuOsobu() != null)
                Response.Redirect("~/ListaOsoba.aspx");
        }

        protected void ButtonLogin_Click(object sender, EventArgs e)
        {
            IRepo repo = RepoFactory.Repozitorij;
            string email = txtUsername.Text;
            string sifra = txtPassword.Text;

            // ili se puni ako je admin s 123 ili ako je uspjesna prijava
            Osoba osoba = null;

            //odma deklarira i incijalizira osobu

            if (email == "admin@email.com" && sifra == "123")
                osoba = new Osoba
                {
                    Email = new List<Email>
                    {
                        new Email
                        {
                            Naziv = email
                        }
                    },
                    Ime = "ADMIN",
                    Prezime = "ADMIN",
                    StatusID = 2
                };

            if (osoba != null || repo.Prijava(email, sifra, out osoba))
            {
                Session["user"] = osoba;

                if (checkBoxZapamtiMe.Checked)
                {
                    var kuki = new HttpCookie("user", OsobaKonverter.ParseForFile(osoba));//kuki i treba imati swe podatke o osobi
                    kuki.Expires = DateTime.Now.AddYears(50);
                    Response.Cookies.Add(kuki);
                }
                //kuki koristis za zapamti me, i za postavke (setup.aspx)
                //za sve ostalo session

                Response.Redirect("~/ListaOsoba.aspx");

            }
            else
            {
                txtUsername.Text = string.Empty;
                txtPassword.Text = string.Empty;
            }


        }
    }
}