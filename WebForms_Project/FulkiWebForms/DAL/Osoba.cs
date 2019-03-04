namespace DAL
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Osoba")]
    public partial class Osoba
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Osoba()
        {
            Email = new HashSet<Email>();
        }

        [Key]
        public int IDOsoba { get; set; }

        [Required]
        [StringLength(50)]
        public string Ime { get; set; }

        [Required]
        [StringLength(50)]
        public string Prezime { get; set; }

        [Required]
        [StringLength(35)]
        public string Telefon { get; set; }

        [Required]
        [StringLength(45)]
        public string Sifra { get; set; }

        public int StatusID { get; set; }

        public int GradID { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Email> Email { get; set; }

        public virtual Grad Grad { get; set; }

        public virtual Status Status { get; set; }
    }
}
