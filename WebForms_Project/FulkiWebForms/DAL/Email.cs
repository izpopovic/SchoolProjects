namespace DAL
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Email")]
    public partial class Email
    {
        [Key]
        public int IDEmail { get; set; }

        [Required]
        [StringLength(50)]
        public string Naziv { get; set; }

        public int OsobaID { get; set; }

        public virtual Osoba Osoba { get; set; }
    }
}
