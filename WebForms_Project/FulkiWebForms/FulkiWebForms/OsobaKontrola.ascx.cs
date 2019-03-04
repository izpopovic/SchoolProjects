using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class OsobaKontrola : System.Web.UI.UserControl
    {
        public void InsertDataForPerson(Osoba osoba)
        {
            txtId.Value = osoba.IDOsoba.ToString();
            txtImeUserKontrola.Text = osoba.Ime;
            txtPrezimeUserKontrola.Text = osoba.Prezime;
            if (osoba.Email.Count > 0)
                txtEmailUserKontrola.Text = osoba.Email.First().Naziv;
            txtPasswordUserKontrola.Text = osoba.Sifra;
            txtTelefonUserKontrola.Text = osoba.Telefon;
            ddlEmail.DataSource = osoba.Email;
            ddlEmail.DataBind();
            ddlGradovi.SelectedValue = osoba.GradID.ToString();
            ddlStatus.SelectedValue = osoba.StatusID.ToString();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                IRepo repo = RepoFactory.Repozitorij;

                ddlStatus.DataSource = repo.GetStatuse();
                ddlStatus.DataBind();

                ddlGradovi.DataSource = repo.GetGradovi();
                ddlGradovi.DataBind();
            }

        }

        protected void ddlEmail_SelectedIndexChanged(object sender, EventArgs e)
        {
            txtEmailUserKontrola.Text = ddlEmail.SelectedItem.Text;
        }

        protected void btnUpdateEmail_Click(object sender, EventArgs e)
        {
            ddlEmail.SelectedItem.Text = txtEmailUserKontrola.Text;

            RepoFactory.Repozitorij.AzurirajEmail(new Email
            {
                Naziv = txtEmailUserKontrola.Text,
                IDEmail = int.Parse(ddlEmail.SelectedValue)
            });

            Session["azuriranje"] = "Korisnikov email je uspješno ažuriran.";

            Page.Refresh();
        }

        protected void btnUredi_Click(object sender, EventArgs e)
        {
            RepoFactory.Repozitorij.AzurirajOsobu(new Osoba
            {
                GradID = int.Parse(ddlGradovi.SelectedValue),
                IDOsoba = int.Parse(txtId.Value),
                Ime = txtImeUserKontrola.Text,
                Prezime = txtPrezimeUserKontrola.Text,
                Telefon = txtTelefonUserKontrola.Text,
                StatusID = int.Parse(ddlStatus.SelectedValue),
                Sifra = txtPasswordUserKontrola.Text
            });

            Session["azuriranje"] = "Korisnik je uspješno ažuriran.";

            if (Request.QueryString["Id"] == null)
            {
                Page.Refresh();
            }
            else
            {
                Response.Redirect("~/ListaOsoba.aspx");
            }
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            Session["deleteId"] = int.Parse(txtId.Value);
            this.RunJS("$('#modalDialog').modal('show');");
        }
    }
}