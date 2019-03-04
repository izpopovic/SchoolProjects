namespace DAL.Converters
{
    public class StatusKonverter
    {
        private const char DELIMITER = '^';

        public static string ParseForFile(Status status)
        {
            return string.Join(DELIMITER.ToString(), status.IDStatus, status.Naziv);
        }

        public static Status ParseFromFile(string line)
        {
            string[] lajnaO = line.Split(DELIMITER);

            return new Status
            {
                IDStatus = int.Parse(lajnaO[0]),
                Naziv = lajnaO[1]
            };
        }
    }
}
