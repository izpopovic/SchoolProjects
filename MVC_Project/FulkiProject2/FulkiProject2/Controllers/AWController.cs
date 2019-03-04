using FulkiProject2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FulkiProject2.Controllers
{
    public class AWController : Controller
    {
        // GET: AW
        public ActionResult Kupci()
        {
            return View(Repo.GetTop10Kupaca());
        }
        [HttpGet]
        public ActionResult Edit(int id)
        {
            ViewBag.drzave = Repo.GetDrzave();
            ViewBag.gradovi = Repo.GetGradovi();
            return View(Repo.GetKupac(id));
        }
        [HttpPost]
        public ActionResult Edit(Kupac k)
        {
            Repo.UpdateKupac(k);
            return RedirectToAction("Kupci");
        }

        public ActionResult Details(int id)
        {
            return View(Repo.GetKupac(id));
        }
        [HttpGet]
        public ActionResult Delete(int id)
        {
            return View(Repo.GetKupac(id));
        }
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Repo.DeleteKupac(id);
            return RedirectToAction("Kupci");
        }

        [HttpGet]
        public ActionResult InsertKupac()
        {
            ViewBag.gradovi = Repo.GetGradovi();
            return View();
        }

        [HttpPost]
        public ActionResult InsertKupac(Kupac k)
        {
            Repo.InsertKupac(k);
            return RedirectToAction("Kupci");
        }

        public ActionResult GetRacuni()
        {
            List<KreditnaKartica> kk = new List<KreditnaKartica>();
            List<Racun> racuni = Repo.GetRacuni();
            foreach (var racun in racuni)
            {
                kk.Add(Repo.GetKreditnaKarticaForRacun(racun.KreditnaKarticaID));
            }
            ViewBag.kartice = kk;
            return View(Repo.GetRacuni());
        }

        public ActionResult RacuniKupca(int id)
        {
            List<Racun> racuni = Repo.GetRacuni();
            return View(racuni.Where(r => r.KupacID == id));
        }

        public ActionResult KarticaDetails(int id)
        {
            return View(Repo.GetKreditnaKarticaForRacun(id));
        }

        public ActionResult KomercijalistDetails(int id,int kupacid)
        {
            ViewBag.IDKupac = kupacid;
            return View(Repo.GetKomercijalistaForRacun(id));
        }

        public ActionResult Stavke(int id)
        {
            return View(Repo.GetStavkeForRacun(id));
        }

        public ActionResult Proizvod(int id,int racunid)
        {
            ViewBag.IDRacun= racunid;
            return View(Repo.GetProizvodForStavka(id));
        }

    }
}