namespace DAL.Converters
{
    public class GradKonverter
    {
        private const char DELIMITER = '^';

        public static string ParseForFile(Grad grad)
        {
            return string.Join(DELIMITER.ToString(), grad.IDGrad, grad.Naziv);
        }

        public static Grad ParseFromFile(string line)
        {
            string[] lajnaO = line.Split(DELIMITER);

            return new Grad
            {
                IDGrad = int.Parse(lajnaO[0]),
                Naziv = lajnaO[1]
            };
        }
    }
}
