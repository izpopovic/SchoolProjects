namespace DAL
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    //DAL -> NEW ITEM -> DATA -> ADO.NET Entity Data Model
    public partial class Model : DbContext
    {
        public Model()
            : base("name=ModelConnectionString")
        {
        }
        //uu ove tablice ja insertiram podatke
        public virtual DbSet<Email> Email { get; set; }
        public virtual DbSet<Grad> Grad { get; set; }
        public virtual DbSet<Osoba> Osoba { get; set; }
        public virtual DbSet<Status> Status { get; set; }
        public virtual DbSet<sysdiagrams> sysdiagrams { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Grad>()
                .HasMany(e => e.Osoba)
                .WithRequired(e => e.Grad)
                .HasForeignKey(e => e.GradID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Osoba>()
                .HasMany(e => e.Email)
                .WithRequired(e => e.Osoba)
                .HasForeignKey(e => e.OsobaID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Status>()
                .HasMany(e => e.Osoba)
                .WithRequired(e => e.Status)
                .HasForeignKey(e => e.StatusID)
                .WillCascadeOnDelete(false);
        }
    }
}
