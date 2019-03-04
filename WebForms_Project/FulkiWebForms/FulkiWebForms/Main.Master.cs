using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class Main : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Osoba ulogiranaOsoba = (Page as MyPage).DohvatiUlogiranuOsobu();

            if (ulogiranaOsoba != null)
            {
                btnAdmin.Text = $"{ulogiranaOsoba.Ime} {ulogiranaOsoba.Prezime}";

                if (ulogiranaOsoba.Email.Count > 0)
                    btnAdmin.PostBackUrl = $"mailto:{ulogiranaOsoba.Email.First().Naziv}";
            }
            else
            {
                btnAdmin.Visible = false;
                btnLogout.Visible = false;
            }
        }

        protected void btnAddNewPerson_Click(object sender, EventArgs e)
        {

            Response.Redirect("~/DodavanjeOsoba.aspx");
        }

        protected void btnEditData_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/AzuriranjeOsoba.aspx");
        }

        protected void btnPersonList_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/ListaOsoba.aspx");
        }

        protected void btnSetup_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Postavke.aspx");
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            Session.Remove("user");
            Response.Cookies["user"].Value = null;

            Response.Redirect("~/Login.aspx");
        }

        public void ChangeBackground(string colorHex)
        {
            body.Attributes["style"] = $"background-color: {colorHex};";
        }

        public void ChangeSelectedButton(int button)
        {
            switch (button)
            {
                case 1:
                    btnAddNewPerson.CssClass = "btn btn-primary";
                    break;
                case 2:
                    btnEditData.CssClass = "btn btn-primary";
                    break;
                case 3:
                    btnPersonList.CssClass = "btn btn-primary";
                    break;
                case 4:
                    btnSetup.CssClass = "btn btn-primary";
                    break;
            }
        }
    }
}