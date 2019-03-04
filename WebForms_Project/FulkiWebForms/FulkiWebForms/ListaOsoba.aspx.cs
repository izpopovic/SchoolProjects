using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class ListaOsoba : MyPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            korisnikMozeUletit = true;
            ProvjeriKorisnika();

            (Master as Main).ChangeSelectedButton(3);

            var lista = osobe.Select();

            gridOsobe.DataSource = lista;
            gridOsobe.DataBind();
            repeater.DataSource = lista;
            repeater.DataBind();
            //repeaterOsobe.DataSource = lista;
            //repeaterOsobe.DataBind();

            string azuriranje = (string)Session["azuriranje"];
            string brisanje = (string)Session["brisanje"];

            if (azuriranje != null)
            {
                this.Toast(Utils.ToastrSuccess, azuriranje);
                Session.Remove("azuriranje");
            }
            if (brisanje != null)
            {
                this.Toast(Utils.ToastrWarning, brisanje);
                Session.Remove("brisanje");
            }
        }

        protected void gridOsobe_RowEditing(object sender, GridViewEditEventArgs e)
        {
            gridOsobe.EditIndex = e.NewEditIndex;
            gridOsobe.DataSource = osobe.Select();
            gridOsobe.DataBind();
        }

        protected void gridOsobe_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
        {
            gridOsobe.EditIndex = -1;
            gridOsobe.DataSource = osobe.Select();
            gridOsobe.DataBind();
        }

        protected void gridOsobe_RowUpdating(object sender, GridViewUpdateEventArgs e)
        {
            // Ne dolaze updateane vrijednosti...
            GridViewRow row = gridOsobe.Rows[e.RowIndex];
            HiddenField txtID = row.Cells[0].Controls[1] as HiddenField;
            int idOsobe = int.Parse(txtID.Value);
            List<Email> mailovi = null;
            using (var model = new Model())
                mailovi = model.Osoba.Include("Email").First(osoba => osoba.IDOsoba == idOsobe).Email.ToList();

            TextBox txtIme = row.Cells[2].Controls[0] as TextBox;
            TextBox txtPrezime = row.Cells[3].Controls[0] as TextBox;
            TextBox txtTelefon = row.Cells[5].Controls[0] as TextBox;
            DropDownList ddlStatusi = row.Cells[6].Controls[1] as DropDownList;

            Repeater listEmailovi = row.Cells[4].Controls[1] as Repeater;

            for (int i = 0; i < listEmailovi.Controls.Count; i++)
            {
                RepeaterItem child = listEmailovi.Controls[i] as RepeaterItem;
                TextBox txtEmail = child.Controls[1] as TextBox;
                mailovi[i].Naziv = txtEmail.Text;
            }


            RepoFactory.Repozitorij.AzurirajOsobu(new Osoba
            {
                IDOsoba = idOsobe,
                Ime = txtIme.Text,
                Prezime = txtPrezime.Text,
                Email = mailovi,
                Telefon = txtTelefon.Text,
                StatusID = int.Parse(ddlStatusi.SelectedValue)
            });

            gridOsobe.EditIndex = -1;
            gridOsobe.DataSource = osobe.Select();
            gridOsobe.DataBind();
            this.Refresh();
        }

        protected void Puce_Click(object sender, CommandEventArgs e)
        {
            Response.Redirect("~/AzuriranjeOsoba.aspx?Id=" + e.CommandArgument);
        }
    }
}