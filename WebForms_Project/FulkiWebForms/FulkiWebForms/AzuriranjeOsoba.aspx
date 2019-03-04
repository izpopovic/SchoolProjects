<%@ Page Title="Ažuriranje osoba" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="AzuriranjeOsoba.aspx.cs" Inherits="FulkiWebForms.AzuriranjeOsoba" culture="auto" meta:resourcekey="PageResource1" uiculture="auto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Content" runat="server">
    <asp:PlaceHolder ID="OsobeKontrola" runat="server"></asp:PlaceHolder>

    <div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" style="display: inline-block;" id="exampleModalLabel">
                        <asp:Label Text="Obrisati osobu?" runat="server" meta:resourcekey="LabelResource1" />
                    </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <asp:Label Text="Jeste li sigurni da želite obrisati odabranu osobu?" runat="server" meta:resourcekey="LabelResource2" />
                </div>
                <div class="modal-footer">
                    <asp:Button ID="btnDelete" Text="Obriši" CssClass="btn btn-primary" runat="server" OnClick="btnDelete_Click" meta:resourcekey="btnDeleteResource1" />

                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <asp:Label Text="Odustani" runat="server" meta:resourcekey="LabelResource3" />
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div style="clear: both;"></div>
</asp:Content>
