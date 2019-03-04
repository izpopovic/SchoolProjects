using DAL;
using DAL.Converters;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.UI;

namespace FulkiWebForms
{
    public class MyPage : Page
    {
        protected bool korisnikMozeUletit;

        protected override void OnPreInit(EventArgs e)
        {
            base.OnPreInit(e);

            string theme = Request.Cookies["theme"]?.Value;
            string repo = Request.Cookies["repository"]?.Value;

            // Kuki za teme
            //if (Response.Cookies["theme"] != null)
            if (!string.IsNullOrEmpty(theme))
            {
                (Master as Main).ChangeBackground(theme);
            }

            // Kuki za repo

            if (!string.IsNullOrEmpty(repo))
            {
                RepoFactory.UseDatabase = repo != "TXT";
            }

            //Request.Cookies.Remove("user");
            //Request.Cookies.Remove("language");
            //Request.Cookies.Remove("theme");
            //Request.Cookies.Remove("repository");
        }

        protected void ProvjeriKorisnika()
        {
            Osoba ulogiranaOsoba = DohvatiUlogiranuOsobu();

            //ako nisi ulogiran... 
            if (ulogiranaOsoba == null)
            {
                    //onda redirejktaj 
                    Response.Redirect("~/Login.aspx");
            }

            //ako jesi ulogiran...
            else
            {
                if(ulogiranaOsoba.StatusID != 2 && korisnikMozeUletit == false)
                {
                    Response.Redirect("~/ListaOsoba.aspx");
                }
            }
        }

        protected override void InitializeCulture()
        {
            base.InitializeCulture();

            // Kuki za jezik
            string jezik = Request.Cookies["language"]?.Value;

            if (!string.IsNullOrEmpty(jezik))
            {
                CultureInfo cultureInfo;

                if (jezik == "CRO")
                    cultureInfo = new CultureInfo("hr");
                else
                    cultureInfo = new CultureInfo("en");

                Thread.CurrentThread.CurrentCulture = cultureInfo;
                Thread.CurrentThread.CurrentUICulture = cultureInfo;
            }
        }

        public Osoba DohvatiUlogiranuOsobu()
        {
            if (Session["user"] != null)
            {
                return Session["user"] as Osoba;
            }

            string userString = Request.Cookies["user"]?.Value;

            if (!string.IsNullOrEmpty(userString))
            {
                //mi smo u kuki zapisali osobu koja je primpremljena u obliku nama pogodnom za konveritranje nazad u osobu sve pomocu osoba konvertera
                return OsobaKonverter.ParseFromFile(userString);
            }

            return null;//ak nis neprodje cao
        }

    }
}