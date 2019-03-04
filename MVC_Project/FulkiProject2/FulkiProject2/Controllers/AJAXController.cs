using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace FulkiProject2.Controllers
{
    public class AJAXController : Controller
    {
        // GET: AJAX
        public ActionResult GetKupci()
        {
            return Json(Repo.GetTop10Kupaca(),JsonRequestBehavior.AllowGet);
        }

        public ActionResult GetKupac(int id)
        {
            return Json(Repo.GetKupac(id), JsonRequestBehavior.AllowGet);
        }

        public ActionResult UpdateKupac(Kupac k)
        {
            if (Repo.UpdateKupac(k)>0)
            {
                return new HttpStatusCodeResult(HttpStatusCode.OK);
            }
            else
            {
                return new HttpStatusCodeResult(HttpStatusCode.NotModified);
            }
        }
    }
}