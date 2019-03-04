using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class DodavanjeOsoba : MyPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ProvjeriKorisnika();
            (Master as Main).ChangeSelectedButton(1);

            IRepo repo = RepoFactory.Repozitorij;

            if (!IsPostBack)
            {
                SakrijKomponente();

                ddlCity.DataSource = repo.GetGradovi();
                ddlCity.DataBind();

                ddlStatus.DataSource = repo.GetStatuse();
                ddlStatus.DataBind();
            }

        }

        private void SakrijKomponente()
        {
            btnDodajtxtEmail.Visible = true;
            txtHiddenEmail2.Visible = false;
            txtHiddenEmail3.Visible = false;
        }

        protected void btnDodajtxtEmail_Click(object sender, EventArgs e)
        {
            if (txtHiddenEmail2.Visible)
            {
                txtHiddenEmail3.Visible = true;
                btnDodajtxtEmail.Visible = false;
            }
            else
            {
                txtHiddenEmail2.Visible = true;
            }
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            //List<Osoba> list = repo.GetOsobe();
            //Osoba zadnji = list.Last();
            //int zadnjiID = zadnji.IDOsoba;

            List<Email> mailovi = new List<Email>();
            mailovi.Add(new Email
            {
                Naziv = txtEmail.Text,
            });
            if (txtHiddenEmail2.Visible)
            {
                mailovi.Add(new Email
                {
                    Naziv = txtHiddenEmail2.Text
                });
            }
            if (txtHiddenEmail3.Visible)
            {
                mailovi.Add(new Email
                {
                    Naziv = txtHiddenEmail3.Text
                });
            }


            Osoba osoba = new Osoba
            {
                Ime = txtName.Text,
                Prezime = txtSurname.Text,
                Telefon = txtTelephone.Text,
                GradID = ddlCity.SelectedIndex + 1,
                StatusID = ddlStatus.SelectedIndex + 1,
                Sifra = txtPassword.Text,
                Email = mailovi
            };

            RepoFactory.Repozitorij.DodajOsobu(osoba);

            this.Toast(Utils.ToastrSuccess, "Korisnik uspješno dodan!");
            txtName.Text = "";
            txtSurname.Text = "";
            txtEmail.Text = "";
            txtHiddenEmail2.Text = "";
            txtHiddenEmail3.Text = "";
            txtTelephone.Text = "";
            ddlCity.SelectedIndex = 0;
            ddlStatus.SelectedIndex = 0;
            txtPassword.Text = "";
            txtConfirmPassword.Text = "";

            SakrijKomponente();
        }
    }
}