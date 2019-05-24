<#include "../base/base.ftl">

<#macro title>
    General settings
</#macro>
<#macro body>
    <td valign=top>
    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=5 bgcolor=#fad163>&nbsp;</td>
            <td bgcolor=#fff7d7>
                <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=#fad163>
                    <tr>
                        <td><font size=+1><h2>Settings</h2></font></td>
                    <tr>
                        <td>
                            <div class=nav>
                                <ul>
                                    <li><h6>General</h6><h6> |</h6></li>
                                    <li><a href="settings/folders"><h6>Folders</h6></a><h6> |</h6></li>
                                    <li><a href="settings/filters"><h6>Filters</h6></a></li>
                                </ul>
                            </div>
                        </td>
                </table>
                <div class=prf>
                    <table summary="general settings" width="100%">
                        <tbody>

                        <tr>
                            <td>
                                <b>Signature:</b><br>
                                <span class="s">(appended at the end of all outgoing messages)</span>
                            </td>
                            <td>
                                <#if signature??>
                                    <form>
                                        <input id="nosignature" name="signature" value="no" type="radio">
                                        <label for="nosignature">
                                            <b>No Signature</b>
                                        </label><br>
                                        <input id="signature" name="signature" value="yes" type="radio" checked="checked">
                                    <textarea name="signature" id="signature-text" rows="5" cols="40"
                                              title="signature text" accept-charset="utf-8">${signature}</textarea>
                                    </form>
                                <#else>
                                    <form>
                                        <input id="nosignature" name="signature" value="no" type="radio"
                                               checked="checked">
                                        <label for="nosignature">
                                            <b>No Signature</b>
                                        </label><br>
                                        <input id="signature" name="signature" value="yes" type="radio">
                                        <textarea name="signature" id="signature-text" rows="5" cols="40"
                                                  title="signature text" accept-charset="utf-8"></textarea>
                                    </form>
                                </#if>

                            </td>

                            <td colspan="2" align="center" class=lr>
                                <input onclick="addSignature()" value="Save Changes" type="button"></td>

                        </tbody>
                    </table>
                </div>
                <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=#fad163>
                    <tr>
                        <td height=5><img height=1 width=1 alt=""></td>
                </table>
            </td>
            <td width=5 bgcolor=#fad163>&nbsp;</td>
    </table>
</#macro>

<@page/>