using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FulkiWebForms
{
    public partial class Postavke : MyPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ProvjeriKorisnika();
            (Master as Main).ChangeSelectedButton(4);
        }

        protected void ddlTheme_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlTheme.SelectedIndex == 0)
                return;

            var kuki = new HttpCookie("theme", ddlTheme.SelectedValue);
            kuki.Expires = DateTime.Now.AddYears(50);
            //Response.Cookies.Remove("theme");
            Response.Cookies.Add(kuki);
            this.Refresh();
        }

        protected void ddlLanguage_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlLanguage.SelectedIndex == 0)
                return;

            var kuki = new HttpCookie("language", ddlLanguage.SelectedValue);
            kuki.Expires = DateTime.Now.AddYears(50);
            Response.Cookies.Add(kuki);
            this.Refresh();
        }

        protected void ddlRepository_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (ddlRepository.SelectedIndex == 0)
                return;

            var kuki = new HttpCookie("repository", ddlRepository.SelectedValue);
            kuki.Expires = DateTime.Now.AddYears(50);
            Response.Cookies.Add(kuki);
            this.Refresh();
        }

        // Eventi za ddl-ove (selected index changed) (nemoj zaboravit na ddl-ove auto post)
        // Svaki event postavlja dodaje kuki
        // Kukiji se citaju u MyPage-u
    }
}