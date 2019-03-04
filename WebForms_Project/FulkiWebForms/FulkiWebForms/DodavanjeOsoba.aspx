<%@ Page Title="Dodavanje osoba" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="DodavanjeOsoba.aspx.cs" Inherits="FulkiWebForms.DodavanjeOsoba" culture="auto" meta:resourcekey="PageResource1" uiculture="auto" %>


<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Content" runat="server">
    <script>
        
    </script>
    <div class="content">
        <%--mos ovo deletat svugdje--%>
        <div class="col-sm-4">
            <div class="form-group">
                <asp:Label Text="Name:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource1" />
                &nbsp;
                <asp:RequiredFieldValidator ErrorMessage="Ime je obavezno polje" ControlToValidate="txtName" runat="server" CssClass="validatori" Display="Dynamic" Text="*" ValidationGroup="valAddPerson" meta:resourcekey="RequiredFieldValidatorResource1"></asp:RequiredFieldValidator>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtName" meta:resourcekey="txtNameResource1" />
            </div>

            <div class="form-group">
                <asp:Label Text="Prezime" runat="server" Font-Bold="True" meta:resourcekey="LabelResource2" />
                &nbsp;
                <asp:RequiredFieldValidator ErrorMessage="Prezime je obavezno polje" ValidationGroup="valAddPerson" Text="*" ControlToValidate="txtSurname" runat="server" Display="Dynamic" meta:resourcekey="RequiredFieldValidatorResource2" />
                <asp:TextBox runat="server" CssClass="form-control" ID="txtSurname" meta:resourcekey="txtSurnameResource1" />
            </div>

            <div class="form-group">
                <asp:Label Text="Email:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource3" />
                &nbsp;
                <asp:RequiredFieldValidator ValidationGroup="valAddPerson" ErrorMessage="Email je obavezan" Text="*" ControlToValidate="txtEmail" runat="server" Display="Dynamic" meta:resourcekey="RequiredFieldValidatorResource3" />
                <asp:RegularExpressionValidator ValidationGroup="valAddPerson" ErrorMessage="Kriva E-mail adresa" Text="*" ControlToValidate="txtEmail" runat="server" Display="Dynamic" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" meta:resourcekey="RegularExpressionValidatorResource1" />

                <asp:TextBox ValidationGroup="valAddPerson" runat="server" CssClass="form-control" ID="txtEmail" meta:resourcekey="txtEmailResource1" />

                <asp:TextBox runat="server" CssClass="form-control" ID="txtHiddenEmail2" meta:resourcekey="txtHiddenEmail2Resource1" />
                <asp:TextBox runat="server" CssClass="form-control" ID="txtHiddenEmail3" meta:resourcekey="txtHiddenEmail3Resource1" />

                <asp:LinkButton Text="Add more email addresses..." runat="server" ID="btnDodajtxtEmail" OnClick="btnDodajtxtEmail_Click" OnClientClick="PokaziElemente" CausesValidation="False" meta:resourcekey="btnDodajtxtEmailResource1" />
            </div>
        </div>

        <div class="col-lg-4 col-md-4 col-sm-4">
            <div class="form-group">
                <asp:Label Text="Telephone:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource4" /> 
                &nbsp;
                <asp:TextBox runat="server" ID="txtTelephone" CssClass="form-control" meta:resourcekey="txtTelephoneResource1" />
                </div>
            <div class="form-group">
                <asp:Label Text="City:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource5" />
               <asp:DropDownList runat="server" CssClass="form-control input-sm" ID="ddlCity" DataTextField="Naziv" DataValueField="IDGrad" meta:resourcekey="ddlCityResource1" />
            </div>
            <div class="form-group">
                <asp:Label Text="Status:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource6"/>
                <asp:DropDownList runat="server" CssClass="form-control input-sm" ID="ddlStatus" DataTextField="Naziv" DataValueField="IDStatus" meta:resourcekey="ddlStatusResource1"/>
            </div>
        </div>

        <div class="col-lg-4 col-md-4 col-sm-4">
            <div class="form-group">
                <asp:Label Text="Password:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource7" />
                &nbsp;
                <asp:RequiredFieldValidator ValidationGroup="valAddPerson" ErrorMessage="Password is required" Text="*" Display="Dynamic" 
                    ControlToValidate="txtPassword" runat="server" meta:resourcekey="RequiredFieldValidatorResource4" />
                <asp:TextBox runat="server" ID="txtPassword" CssClass="form-control" meta:resourcekey="txtPasswordResource1"/>
            </div>
            <div class="form-group">
                <asp:Label Text="Confirm password:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource8"/>
                &nbsp;
                <asp:RequiredFieldValidator ValidationGroup="valAddPerson" ErrorMessage="Password confirmation is required" Text="*" Display="Dynamic" ControlToValidate="txtConfirmPassword" runat="server" meta:resourcekey="RequiredFieldValidatorResource5" />
                <asp:CompareValidator ErrorMessage="Wrong password conformation" Text="*" Display="Dynamic" ControlToValidate="txtPassword" ControlToCompare="txtConfirmPassword" runat="server" ValidationGroup="valAddPerson" meta:resourcekey="CompareValidatorResource1"  />
                <asp:TextBox runat="server" ID="txtConfirmPassword" Font-Bold="True" CssClass="form-control" meta:resourcekey="txtConfirmPasswordResource1"/>
            </div>
            <asp:Button Text="Add" ValidationGroup="valAddPerson" runat="server" ID="btnAdd" OnClick="btnAdd_Click" CssClass="btn btn-primary" meta:resourcekey="btnAddResource1"/>
        </div>

        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="validationSummary">
                <asp:ValidationSummary runat="server" ForeColor="Red" ValidationGroup="valAddPerson" meta:resourcekey="ValidationSummaryResource1"/>
            </div>
        </div>

    </div>
</asp:Content>
