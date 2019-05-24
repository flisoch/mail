<#include "../base/base.ftl">

<#macro title>
    Folders settings
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
                                    <li><a href="/settings/general"><h6>General</h6></a><h6> |</h6></li>
                                    <li><h6>Folders</h6><h6> |</h6></li>
                                    <li><a href="/settings/filters"><h6>Filters</h6></a></li>
                                </ul>
                            </div>
                        </td>
                </table>
                <div class=prf>
                    <table summary="labels settings" width="100%" cellspacing="0">
                        <tbody>

                        <#if customFolders??>
                            <#list customFolders as folder>
                                <tr id="custom-folder-${folder.id}">
                                    <td id="custom-folder-${folder.id}-name"><b><a href="/mail/${folder.name}">${folder.name}</a></b><br></span>
                                    </td>
                                    <td align=right>
                                        <form>
                                            <input id="change-folder-${folder.id}-text" size="10" maxlength="40" name=nct type=text>
                                            <input name=nvp_bu_cl onclick="renameFolder(${folder.id})" value="Rename"
                                                   type=button>
                                            <input name=nvp_bu_dl onclick="removeFolder(${folder.id})" value="Remove"
                                                   type=button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                        </#if>

                        <tr>
                            <form action="/settings/folders" method=POST>
                                <td colspan=2 align="left" class=lr><b>Create a new folder:</b><br>
                                    <input size="20" maxlength="40" name="folderName" type="text">
                                    <input name="nvp_bu_nl" value="Create" type="submit">
                                </td>
                            </form>
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