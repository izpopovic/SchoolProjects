using HtmlAgilityPack;
using Newtonsoft.Json;
using ScrapySharp.Network;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Threading;
using System.Threading.Tasks;

namespace WebScraperFinal
{
    class Program
    {

        private const string startingUrl = "https://www.tvrtke.com/firme";
        private const string startingUrlCities = "https://www.tvrtke.com";
        private static readonly HtmlDocument doc = new HtmlDocument();
        private static readonly List<string> cityURLs = new List<string>();
        private static readonly List<string> companyURLs = new List<string>();
        private static readonly List<CompanyDetails> companyDetailsList = new List<CompanyDetails>();
        private static readonly List<string> userAgents = new List<string>();
        private static Random rng = new Random();



        static void Main(string[] args)
        {

            
            Testic().Wait();
        }

        private static async Task Testic()
        {
            using (var client = new WebClient())
            {


                GenerateRandomClientHeaders(client);
                var html = await client.DownloadStringTaskAsync(startingUrl);
                doc.LoadHtml(html);
                //SetClientHeaders(client);
                var pager = doc.DocumentNode.SelectSingleNode("/html/body/div/div[5]/div[1]/div[2]/div");
                var lastPage = int.Parse(pager.LastChild.PreviousSibling.PreviousSibling.PreviousSibling.InnerHtml);
                GetCities();

                for (int i = 2; i <= lastPage; i++)
                {
                    GenerateRandomClientHeaders(client);
                    html = await client.DownloadStringTaskAsync($"{startingUrl}?stranica={i}");

                    doc.LoadHtml(html);

                    GetCities();
                    Thread.Sleep((TimeSpan.FromSeconds(rng.Next(120, 140))));
                }

                await FetchCompanies(client);
                await GetCompanyDetails(client);
            }
        }



        private static void SetClientHeaders(WebClient client, string userAgent)
        {
            client.Headers[HttpRequestHeader.AcceptCharset] = "ISO-8859-1,utf-8;q=0.7,*;q=0.3";
            //client.Headers[HttpRequestHeader.KeepAlive] = "keep-alive";
            client.Headers[HttpRequestHeader.UserAgent] = userAgent;
            client.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
            client.Headers[HttpRequestHeader.Accept] = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
            //client.Headers[HttpRequestHeader.AcceptEncoding] = "gzip, deflate, br";
            client.Headers[HttpRequestHeader.AcceptLanguage] = "en-US,en;q=0.9";

        }


        private static void GenerateRandomClientHeaders(WebClient client)
        {

            var userAgent1 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";
            var userAgent2 = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36";
            var userAgent3 = "Mozilla / 5.0(Macintosh; Intel Mac OS X 10_8_0) AppleWebKit / 537.36(KHTML, like Gecko) Chrome / 32.0.1664.3 Safari / 537.36";
            var userAgent4 = "Mozilla / 5.0(Windows NT 6.1; WOW64; rv: 40.0) Gecko / 20100101 Firefox / 40.1";
            var userAgent5 = "Mozilla/5.0 (X11; Linux i586; rv:63.0) Gecko/20100101 Firefox/63.0";
            var userAgent6 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/29.0";
            var userAgent7 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246";
            var userAgent8 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14931";
            var userAgent9 = "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201";
            var userAgent10 = "Mozilla/5.0 (Windows; U; Windows NT 5.1; pl; rv:1.9.2.3) Gecko/20100401 Lightningquail/3.6.3";
            var userAgent11 = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9a3pre) Gecko/20070330";

            userAgents.Add(userAgent1);
            userAgents.Add(userAgent2);
            userAgents.Add(userAgent3);
            userAgents.Add(userAgent4);
            userAgents.Add(userAgent5);
            userAgents.Add(userAgent6);
            userAgents.Add(userAgent7);
            userAgents.Add(userAgent8);
            userAgents.Add(userAgent9);
            userAgents.Add(userAgent10);
            userAgents.Add(userAgent11);

            int r = rng.Next(userAgents.Count);
   
            SetClientHeaders(client, userAgents[r]);
        }

        private static void GetCities()
        {
            var cities = doc.DocumentNode.SelectNodes("/html/body/div/div[5]/div[1]/div[2]/ul");

            foreach (var tag in cities.Descendants())
            {
                if (tag.Name == "a")
                {

                    cityURLs.Add(startingUrlCities + tag.Attributes["href"].Value);

                    Console.WriteLine($"\t{cityURLs.Count,-5} - {cityURLs.Last()}");
                }
            }
        }


        private static async Task FetchCompanies(WebClient client)
        {
            foreach (var cityURL in cityURLs)
            {
                //Console.WriteLine(cityURL);

                GenerateRandomClientHeaders(client);
                var html = await client.DownloadStringTaskAsync(cityURL);
                doc.LoadHtml(html);
                //SetClientHeaders(client);

                var pager = doc.DocumentNode.SelectSingleNode("//*[@id=\"aspnetForm\"]/div[3]/div[6]/div[1]/div[2]/div[2]");
                var lastPage = int.Parse(pager.LastChild.PreviousSibling.PreviousSibling.PreviousSibling.InnerHtml);
                //Console.WriteLine(lastPage);


                GetCompanies();

                for (int i = 2; i <= lastPage; i++)
                {
                    Console.WriteLine("City companies page #" + i);

                    GenerateRandomClientHeaders(client);
                    html = await client.DownloadStringTaskAsync($"{cityURL}?stranica={i}");
                    Thread.Sleep(TimeSpan.FromSeconds((rng.Next(120, 140))));
                    doc.LoadHtml(html);
                    //SetClientHeaders(client);

                    GetCompanies();


                }

            }
        }

        private static void GetCompanies()
        {
            for (int i = 0; i < 9; i++)
            {

                var company = doc.DocumentNode.SelectSingleNode($"//*[@id=\"ctl00_ContentPlaceHolder1_Firme_rptCompany_ctrl{i}_linkImePrecica\"]");

                if (company != null)
                {
                    companyURLs.Add(startingUrl + company.Attributes["href"].Value);
                    Console.WriteLine($"{companyURLs.Count,-5}: {companyURLs.Last()}");
                }
            }
        }

        private static async Task GetCompanyDetails(WebClient client)
        {
            int id = 1;

            foreach (var companyURL in companyURLs)
            {
                GenerateRandomClientHeaders(client);
                var html = await client.DownloadStringTaskAsync(companyURL);
                Thread.Sleep(TimeSpan.FromSeconds((rng.Next(120, 140))));
                doc.LoadHtml(html);
                //SetClientHeaders(client);

                CompanyDetails companyDetails = new CompanyDetails();

                var companyAdress = doc.DocumentNode.SelectSingleNode("//*[@id=\"FirmaPodaci_divOsnovniPodaci\"]/div[2]/p[1]");

                if (companyAdress != null)
                {
                    companyDetails.Adress = companyAdress.InnerText;
                    companyDetails.ID = id;
                    Console.WriteLine($"{id}. Data to Scrap: ");
                    Console.WriteLine($"CompanyID:{companyDetails.ID} --- CompanyAdress: {companyDetails.Adress} ");

                    companyDetailsList.Add(companyDetails);
                    id++;
                }
            }

            WriteJsonData(companyDetailsList);
        }

        private static void WriteJsonData(List<CompanyDetails> companyDetails)
        {
            var jsonString = JsonConvert.SerializeObject(companyDetails);
            var path = @"C:\Users\ivanz\Desktop\HGSPOT\HocNMeTryWan\HocNMeTryWan\JsonData\json1.json";
            File.WriteAllText(path, jsonString);
        }
    }
}