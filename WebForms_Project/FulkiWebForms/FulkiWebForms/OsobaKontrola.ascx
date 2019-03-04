<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="OsobaKontrola.ascx.cs" Inherits="FulkiWebForms.OsobaKontrola" %>


<div class="osoba" >
    <asp:HiddenField runat="server" ID="txtId" />
    <table>
        <tbody class="userKontrolaTablica">
            <tr>
                <td>
                    <asp:Label Text="Ime:" runat="server" meta:resourcekey="LabelResource1" />
                </td>
                <td>
                    <asp:TextBox runat="server" ID="txtImeUserKontrola" CssClass="form-control input-sm" meta:resourcekey="txtImeUserKontrolaResource1" />
                <td>
                    <asp:RequiredFieldValidator ErrorMessage="*" ControlToValidate="txtImeUserKontrola" runat="server" Display="Dynamic" CssClass="validatori" meta:resourcekey="RequiredFieldValidatorResource1" />
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label Text="Prezime" runat="server" meta:resourcekey="LabelResource2" />
                </td>
                <td>
                    <asp:TextBox runat="server" ID="txtPrezimeUserKontrola" CssClass="form-control input-sm" meta:resourcekey="txtPrezimeUserKontrolaResource1" />
                </td>
                <td>
                    <asp:RequiredFieldValidator ErrorMessage="*" ControlToValidate="txtPrezimeUserKontrola" runat="server" Display="Dynamic" CssClass="validatori" ValidationGroup="valOsobaKontrola" meta:resourcekey="RequiredFieldValidatorResource2"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 5px"></td>
                <td>
                    <asp:DropDownList ID="ddlEmail" runat="server" CssClass="form-control input-sm" 
                        DataValueField="IDEmail" DataTextField="Naziv"
                        OnSelectedIndexChanged="ddlEmail_SelectedIndexChanged" AutoPostBack="True" meta:resourcekey="ddlEmailResource1"></asp:DropDownList>
                </td>
                <td>&nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label Text="E-mail:" runat="server" meta:resourcekey="LabelResource3" />
                </td>
                <td>
                    <div class="input-group">
                        <asp:TextBox runat="server" ID="txtEmailUserKontrola" CssClass="form-control input-sm" meta:resourcekey="txtEmailUserKontrolaResource1" />
                      
                        <asp:LinkButton runat="server" CssClass="input-group-btn btn btn-default btn-sm"
                           ID="btnUpdateEmail" OnClick="btnUpdateEmail_Click" meta:resourcekey="btnUpdateEmailResource1">
                            <span class="glyphicon glyphicon-share"></span>
                            <%--<span class="glyphicon glyphicon-download-alt"></span>--%>
                        </asp:LinkButton>
                    </div>
                </td>
                <td>
                    <asp:RegularExpressionValidator ID="RegularExpressionValidatorEmail" runat="server" ErrorMessage="*" Display="Dynamic" CssClass="validatori" ValidationExpression="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" ControlToValidate="txtEmailUserKontrola" ValidationGroup="valOsobaKontrola" meta:resourcekey="RegularExpressionValidatorEmailResource1" ></asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label Text="Telefon" runat="server" meta:resourcekey="LabelResource4" />
                </td>
                <td>
                    <asp:TextBox runat="server" ID="txtTelefonUserKontrola" CssClass="form-control input-sm" meta:resourcekey="txtTelefonUserKontrolaResource1" />
                </td>
                <td>&nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label Text="Lozinka" runat="server" meta:resourcekey="LabelResource5" />
                </td>
                <td>
                    <asp:TextBox runat="server" ID="txtPasswordUserKontrola" CssClass="form-control input-sm" meta:resourcekey="txtPasswordUserKontrolaResource1" />
                </td>
                <td>
                    <asp:RequiredFieldValidator ErrorMessage="*" ControlToValidate="txtPasswordUserKontrola" runat="server" Display="Dynamic" CssClass="validatori" ValidationGroup="valOsobaKontrola" meta:resourcekey="RequiredFieldValidatorResource3" />
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label Text="Status" runat="server" meta:resourcekey="LabelResource6" />
                </td>
                <td>
                    <asp:DropDownList runat="server" ID="ddlStatus" CssClass="form-control input-sm"
                        DataTextField="Naziv" DataValueField="IDStatus" meta:resourcekey="ddlStatusResource1"/>
                </td>
                <td>&nbsp;
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label Text="Grad" runat="server" meta:resourcekey="LabelResource7" />
                </td>
                <td>
                    <asp:DropDownList runat="server" ID="ddlGradovi" CssClass="form-control input-sm"  DataTextField="Naziv" DataValueField="IDGrad" meta:resourcekey="ddlGradoviResource1"/>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <asp:Button Text="Uredi" runat="server" ID="btnUredi" CssClass="btn btn-primary btn-sm btnCtrl" ValidationGroup="valOsobaKontrola" OnClick="btnUredi_Click" meta:resourcekey="btnUrediResource1" />
                    <asp:Button Text="Obriši" runat="server" ID="btnDelete" CssClass="btn btn-warning btn-sm btnCtrl" ValidationGroup="valOsobaKontrola" OnClick="btnDelete_Click" meta:resourcekey="btnDeleteResource1" />
                </td>
                <td>&nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <asp:ValidationSummary runat="server" Style="display: none" ID="validationSummaryUserControl" CssClass="validatori"
                        ValidationGroup="valOsobaKontrola" meta:resourcekey="validationSummaryUserControlResource1"/>
                </td>
            </tr>
        </tbody>
    </table>
</div>
