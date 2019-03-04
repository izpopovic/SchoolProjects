<%@ Page Title="Lista Osoba" Language="C#" EnableEventValidation="false" MasterPageFile="~/Main.Master" AutoEventWireup="true" CodeBehind="ListaOsoba.aspx.cs" Inherits="FulkiWebForms.ListaOsoba" culture="auto" meta:resourcekey="PageResource1" uiculture="auto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        tr > th {
            background-color: #333;
            color: white;
            font-size: 1em;
            font-weight: normal;
        }

        th, td {
            padding: 5px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Content" runat="server">
    <asp:ObjectDataSource runat="server" TypeName="FulkiWebForms.Utils" SelectMethod="Statusi" ID="statusi" />
    <asp:ObjectDataSource runat="server" TypeName="FulkiWebForms.Utils" SelectMethod="Gradovi" ID="gradovi" />
    <asp:ObjectDataSource runat="server" TypeName="FulkiWebForms.Utils" SelectMethod="Osobe" ID="osobe" />

    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
                <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#gridViewContent">Grid View
                    </a>
                </h4>
            </div>
            <div id="gridViewContent" class="panel-collapse collapse in">
                <div class="panel-body">
                    <div>
                        <%--GridView--%>
                        <asp:GridView runat="server" ID="gridOsobe" AutoGenerateColumns="False" OnRowEditing="gridOsobe_RowEditing"
                             OnRowCancelingEdit="gridOsobe_RowCancelingEdit" OnRowUpdating="gridOsobe_RowUpdating" Width="100%" meta:resourcekey="gridOsobeResource1">
                            <Columns>
                                <asp:TemplateField Visible="false" meta:resourcekey="TemplateFieldResource1">
                                    <EditItemTemplate>
                                        <asp:HiddenField runat="server" Value='<%# Eval("IDOsoba") %>' />
                                    </EditItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="IDOsoba" Visible="false" meta:resourcekey="BoundFieldResource1" />
                                <asp:BoundField DataField="Ime" HeaderText="Ime" ControlStyle-CssClass="form-control" meta:resourcekey="BoundFieldResource2" >
<ControlStyle CssClass="form-control"></ControlStyle>
                                </asp:BoundField>
                                <asp:BoundField DataField="Prezime" HeaderText="Prezime" ControlStyle-CssClass="form-control" meta:resourcekey="BoundFieldResource3" >
<ControlStyle CssClass="form-control"></ControlStyle>
                                </asp:BoundField>
                                <asp:TemplateField HeaderText="E-mail adrese" meta:resourcekey="TemplateFieldResource2">
                                    <ItemTemplate>
                                        <asp:Repeater runat="server" DataSource='<%# Eval("Email") %>'>
                                            <ItemTemplate>
                                                <asp:LinkButton runat="server" PostBackUrl='<%# $"mailto:{Eval("Naziv")}" %>' Text='<%# Eval("Naziv") %>' meta:resourcekey="LinkButtonResource1" />
                                                <br />
                                            </ItemTemplate>
                                        </asp:Repeater>
                                    </ItemTemplate>
                                    <EditItemTemplate>
                                        <asp:Repeater runat="server" DataSource='<%# Eval("Email") %>'>
                                            <ItemTemplate>
                                                <asp:TextBox runat="server" Text='<%# Eval("Naziv") %>' CssClass="form-control" meta:resourcekey="TextBoxResource1" />
                                            </ItemTemplate>
                                        </asp:Repeater>
                                    </EditItemTemplate>
                                </asp:TemplateField>
                                <asp:BoundField DataField="Telefon" HeaderText="Telefon" ControlStyle-CssClass="form-control" meta:resourcekey="BoundFieldResource4" >
<ControlStyle CssClass="form-control"></ControlStyle>
                                </asp:BoundField>
                                <asp:TemplateField meta:resourcekey="TemplateFieldResource3">
                                    <ItemTemplate>
                                        <asp:DropDownList DataTextField="Naziv" DataValueField="IDStatus"
                                            Enabled="False" runat="server" DataSourceID="statusi" CssClass="form-control" meta:resourcekey="DropDownListResource2" />
                                    </ItemTemplate>
                                    <EditItemTemplate>
                                        <asp:DropDownList DataTextField="Naziv" DataValueField="IDStatus"
                                            runat="server" DataSourceID="statusi" CssClass="form-control" meta:resourcekey="DropDownListResource1" />
                                    </EditItemTemplate>
                                </asp:TemplateField>
                                <asp:CommandField EditText="Uredi" ShowEditButton="true" meta:resourcekey="CommandFieldResource1" />
                            </Columns>
                        </asp:GridView>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" id="headingOne">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#repeaterContent">Repeater
                    </a>
                </h4>
            </div>
            <div id="repeaterContent" class="panel-collapse collapse">
                <div class="panel-body">
                    <%--Repeater--%>

                    
                    <asp:Repeater runat="server" ID="repeater">
                        <HeaderTemplate>
                            <table class="table table-condensed table-hover">
                                <tr>
                                    <th>
                                        <asp:Label Text="Name" runat="server" meta:resourcekey="LabelResource1" />
                                    </th>
                                    <th>
                                        <asp:Label Text="Surname" runat="server" meta:resourcekey="LabelResource2" />
                                    </th>
                                    <th>
                                        <asp:Label Text="Email addresses" runat="server" meta:resourcekey="LabelResource3" />
                                    </th>
                                    <th>
                                        <asp:Label Text="Telephone" runat="server" meta:resourcekey="LabelResource4" />
                                    </th>
                                    <th>
                                        <asp:Label Text="Status" runat="server" meta:resourcekey="LabelResource5" />
                                    </th>
                                    <th>
                                        <asp:Label Text="City" runat="server" meta:resourcekey="LabelResource6" />
                                    </th>
                                    <th></th>
                                </tr>
                        </HeaderTemplate>
                        <ItemTemplate>
                            <tr>
                                <td>
                                    <asp:Label Text='<%# Eval("Ime") %>' runat="server" meta:resourcekey="LabelResource7" />
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Prezime") %>' runat="server" meta:resourcekey="LabelResource8" />
                                </td>
                                <td>
                                    <asp:Repeater runat="server" DataSource='<%# Eval("Email") %>'>
                                        <ItemTemplate>
                                            <asp:HyperLink NavigateUrl='<%# "mailto:" + Eval("Naziv") %>'
                                                runat="server" Text='<%# Eval("Naziv") %>' meta:resourcekey="HyperLinkResource2" />
                                            <br />
                                        </ItemTemplate>
                                    </asp:Repeater>
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Telefon") %>' runat="server" meta:resourcekey="LabelResource9" />
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Status.Naziv") %>' runat="server" meta:resourcekey="LabelResource10" />
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Grad.Naziv") %>' runat="server" meta:resourcekey="LabelResource11" />
                                </td>
                                <td>
                                    <asp:Button Text="Edit" runat="server" CssClass="btn btn-primary btn-sm" meta:resourcekey="ButtonResource1"
                                        CommandName="Click" CommandArgument='<%# Eval("IDOsoba") %>' OnCommand="Puce_Click"/>
                                </td>
                            </tr>
                        </ItemTemplate>
                        <FooterTemplate>
                            </table>
                        </FooterTemplate>
                    </asp:Repeater>

                    <%--
                    <asp:Repeater runat="server" ID="repeaterOsobe" OnItemCommand="repeaterOsobe_ItemCommand">
                        <HeaderTemplate>
                            <table class="table table-condensed table-hover">
                                <tbody>
                                    <tr>
                                        <th>Ime</th>
                                        <th>Prezime</th>
                                        <th>E-mail</th>
                                        <th>Telefon</th>
                                        <th>Status</th>
                                        <th>Grad</th>
                                        <th></th>
                                    </tr>
                        </HeaderTemplate>
                        <ItemTemplate>
                            <tr>
                                <td>
                                    <asp:Label Text='<%# Eval("Ime") %>' runat="server" />
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Prezime") %>' runat="server" />
                                </td>
                                <td>
                                    <asp:Repeater runat="server" DataSource='<%# Eval("Email") %>'>
                                        <ItemTemplate>
                                            <asp:LinkButton runat="server" PostBackUrl='<%# $"mailto:{Eval("Naziv")}" %>' Text='<%# Eval("Naziv") %>' />
                                            <br />
                                        </ItemTemplate>
                                    </asp:Repeater>
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Telefon") %>' runat="server" />
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Status.Naziv") %>' runat="server" />
                                </td>
                                <td>
                                    <asp:Label Text='<%# Eval("Grad.Naziv") %>' runat="server" />
                                </td>
                                <td>
                                    <asp:Button Text="Uredi" runat="server" ID="btnUredi" CssClass="btn btn-primary" />
                                </td>
                            </tr>
                        </ItemTemplate>
                        <FooterTemplate>
                            </tbody>
                            </table>
                        </FooterTemplate>
                    </asp:Repeater>
                    --%>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
