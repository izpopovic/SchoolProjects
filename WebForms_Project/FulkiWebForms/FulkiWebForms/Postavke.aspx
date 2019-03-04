<%@ Page Title="Postavke" Language="C#" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="Postavke.aspx.cs" Inherits="FulkiWebForms.Postavke" culture="auto" meta:resourcekey="PageResource1" uiculture="auto" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Content" runat="server">
    <div class="content">
        <div class="row">

            <div class="col-lg-4 col-md-4 col-sm-4"></div>

            <div class="col-lg-4 col-md-4 col-sm-4">
                <div class="form-group">
                    <asp:Label Text="Theme:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource1"/>
                    <asp:DropDownList runat="server" CssClass="form-control input-sm" ID="ddlTheme" OnSelectedIndexChanged="ddlTheme_SelectedIndexChanged" AutoPostBack="True" meta:resourcekey="ddlThemeResource1">
                        <asp:ListItem Text="---choose---" Selected="True" Value="INITALT" meta:resourcekey="ListItemResource1"/>
                        <asp:ListItem Text="Default" Value="#efefef" meta:resourcekey="ListItemResource2"/>
                        <asp:ListItem Text="Blue" Value="#2a329e" meta:resourcekey="ListItemResource3" />
                        <asp:ListItem Text="Red" Value="#c16c60" meta:resourcekey="ListItemResource4" />
                    </asp:DropDownList>
                </div>

                <div class="form-group">
                    <asp:Label Text="Language:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource2" />
                    <asp:DropDownList runat="server" CssClass="form-control input-sm" ID="ddlLanguage" OnSelectedIndexChanged ="ddlLanguage_SelectedIndexChanged" AutoPostBack ="True" meta:resourcekey="ddlLanguageResource1">
                        <asp:ListItem Text="---choose---" Selected="True" Value="INITALL" meta:resourcekey="ListItemResource5"/>
                        <asp:ListItem Text="Croatian" Value="CRO" meta:resourcekey="ListItemResource6"/>
                        <asp:ListItem Text="English" Value="ENG" meta:resourcekey="ListItemResource7" />
                    </asp:DropDownList>
                </div>

                <div class="form-group">
                    <asp:Label Text="Repozitorij:" runat="server" Font-Bold="True" meta:resourcekey="LabelResource3" />
                    <asp:DropDownList runat="server" CssClass="form-control input-sm" ID="ddlRepository" OnSelectedIndexChanged="ddlRepository_SelectedIndexChanged" AutoPostBack="True" meta:resourcekey="ddlRepositoryResource1">
                        <asp:ListItem Text="---choose---" Selected="True" Value="INITALR" meta:resourcekey="ListItemResource8"/>
                        <asp:ListItem Text="Tekstualna datoteka" Value="TXT" meta:resourcekey="ListItemResource9"/>
                        <asp:ListItem Text="Baza podataka" Value="DTB" meta:resourcekey="ListItemResource10" />
                    </asp:DropDownList>
                </div>

            </div>
        </div>
    </div>
</asp:Content>
