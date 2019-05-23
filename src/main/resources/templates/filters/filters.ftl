<#include "../base/base.ftl">

<#macro title>
    Filters
</#macro>
<#macro import>
    <link rel="shortcut icon" href="https://ssl.gstatic.com/ui/v1/icons/mail/images/favicon5.ico" type="image/x-icon">
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
                                    <li><a href="settings/general"><h6>General</h6></a><h6> |</h6></li>
                                    <li><a href="settings/folders"><h6>Folders</h6></a><h6> |</h6></li>
                                    <li class="settings/filters"><h6>Filters</h6></li>
                                </ul>
                            </div>
                        </td>
                </table>
                <div class=prf>
                    <table summary="filters settings" width="100%">
                        <tbody>
                        <tr class=hdn>
                            <th>Setting</th>
                            <th>Choices</th>
                        <tr>
                            <td><b>The following filters are applied to all incoming mail:</b></td>

                            <#if filters??>
                            <#list filters as filter>
                        <tr>
                            <td>
                                <form action="" name=f enctype=multipart/form-data method=POST>

                                    <div class="begin-container">Matches: <b>${filter.matches}</b>
                                        <b>${filter.words}</b>
                                        <br>Do this: ${filter.actions}<br>
                                    </div>
                                    <div class="end-container-align-begin"><input name="nvp_bu_eftb"
                                                                                  value="Edit"
                                                                                  type="submit"> &nbsp;
                                        <input name="nvp_bu_dftb" value="Delete" type="button">

                                </form>

                            </td>
                            </#list>
                            </#if>


                        <tr>
                            <td class="lr">
                                <form action="/settings/filters/new" enctype=multipart/form-data method=GET>
                                    <div class="middle-container"><input value="Create a new Filter"
                                                                         type="submit"></div>
                                </form>
                            </td>
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