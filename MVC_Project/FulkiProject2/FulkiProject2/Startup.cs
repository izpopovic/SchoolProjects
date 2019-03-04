using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(FulkiProject2.Startup))]
namespace FulkiProject2
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
