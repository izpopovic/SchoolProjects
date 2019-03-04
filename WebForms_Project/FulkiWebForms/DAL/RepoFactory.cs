using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    public class RepoFactory
    {
        //singletone, jednom ce se podesiti i nikad vise
        //same shit kao da u konstruktoru pozivamo new DataBaseRepo();
        //prek njega komuniciramo sa svim metodada koje smo implementirali
        //public static IRepo DatabaseRepo { get; } = new DataBaseRepo();

            // Postavlja se u OnLoad u MyPage 
        public static bool UseDatabase { get; set; } = true;

        private static DataBaseRepo dbRepo = new DataBaseRepo();
        private static DataTextRepo txtRepo = new DataTextRepo();

        public static IRepo Repozitorij
        {
            get
            {
                if (UseDatabase)
                    return dbRepo;
                else
                    return txtRepo;
            }
        }
    }
}
