<%@ Page Title="Login" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="FulkiWebForms.Login" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Content" runat="server">

    <div class="content">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4">
            </div>

            <div class="col-lg-4 col-md-4 col-sm-4">
                <div style="margin-top: 20px">
                    <div class="form-group">
                        <asp:Label Text="E-mail:" ID="lblEmail" runat="server" />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidatorUsername" runat="server" Text="*"
                            ErrorMessage="Email je obavezno polje" ControlToValidate="txtUsername" Display="Dynamic" ForeColor="Red"></asp:RequiredFieldValidator>
                        <asp:RegularExpressionValidator ErrorMessage="Kriva E-mail adresa" Text="*" ControlToValidate="txtUsername" runat="server" Display="Dynamic"
                            ValidationExpression="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" ForeColor="Red" />
                        <br />
                        <asp:TextBox CssClass="form-control" runat="server" ID="txtUsername"/>
                    </div>
                </div>

                <div class="form-group">
                    <asp:Label Text="Lozinka:" ID="lblPassword" runat="server" />

                    <asp:RequiredFieldValidator ID="RequiredFieldValidatorPassword" runat="server" Text="*"
                        ErrorMessage="Lozinka je obavezno polje" ControlToValidate="txtPassword" Display="Dynamic" ForeColor="Red"></asp:RequiredFieldValidator>
                    <br />
                    <asp:TextBox runat="server" ID="txtPassword" CssClass="form-control" />
                </div>



                <div class="checkbox">
                    <asp:CheckBox Text="Zapamti me" runat="server" ID="checkBoxZapamtiMe" />
                </div>

                <asp:Button ID="ButtonLogin" runat="server" Text="Login" CssClass="btn btn-primary" OnClick="ButtonLogin_Click" />
                <div style="margin-top: 20px">
                    <asp:ValidationSummary ID="ValidationSummaryLogin" runat="server" ForeColor="#FF3300" />
                </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4">
            </div>

        </div>
    </div>


</asp:Content>
