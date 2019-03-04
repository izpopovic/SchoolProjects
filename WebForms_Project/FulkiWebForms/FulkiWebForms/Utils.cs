using DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;

namespace FulkiWebForms
{
    
    public static class Utils
    {
        public const string ToastrInformational = "info";
        public const string ToastrError = "error";
        public const string ToastrWarning = "warning";
        public const string ToastrSuccess = "success";

        //Utilsi za dohvacanje gradova i statusa
        public static List<Grad> Gradovi() => RepoFactory.Repozitorij.GetGradovi();
        public static List<Status> Statusi() => RepoFactory.Repozitorij.GetStatuse();
        public static List<Osoba> Osobe() => RepoFactory.Repozitorij.GetOsobe();

        public static void Refresh(this Page page)
        {
            page.Response.Redirect(page.Request.RawUrl);
        }

        public static void RunJS(this Control control, string script)
        {
            ScriptManager.RegisterStartupScript(control, control.GetType(), Guid.NewGuid().ToString(), script, true);
        }

        public static void Toast(this Control control, string type, string message, string title = null)
        {
            string titlePart = title == null ? "" : $", '{title}'";
            string script = $"toastr.{type}('{message}'{titlePart})";

            RunJS(control, script);
        }
    }
}