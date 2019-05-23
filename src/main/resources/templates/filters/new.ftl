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
            <td width=3 bgcolor=#fad163></td>
            <td bgcolor=#fff7d7>
                <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=#fad163>
                    <tr>
                        <td><font size=+1><h2>Create a filter</h2></font></td>
                </table>
                <div class=prf>
                    <table summary="filters settings" width="100%">
                        <tbody>
                        <tr>
                            <td class="lr">
                                <form action="./?v=prf" name=f enctype=multipart/form-data method=POST>
                                    <div class="middle-container-padded"><b>Choose search criteria</b> - Specify the
                                        criteria that you'd like to use for determining what to do with a message as
                                        it arrives. Use "Test Search" to see which messages would have been filtered
                                        using these criteria.<br><br>
                                        <div class="begin-container-align-end"><b>From:</b>&nbsp;
                                            <input id="ch_fr" name="cf1_from" type="text" size="40" maxlength="96"
                                                   value=""
                                                   style="vertical-align: middle;"><br><b>To:</b>&nbsp;
                                            <input id="ch_to" name="cf1_to" value="" type="text" size="40"
                                                   maxlength="96"
                                                   style="vertical-align: middle;"><br><b>Subject:</b>&nbsp;
                                            <input id="ch_sub" name="cf1_subj" value="" type="text" size="40"
                                                   maxlength="96"
                                                   style="vertical-align: middle;"><br><b>Has the words:</b>&nbsp;
                                            <input id="ch_wrd" name="cf1_has" value="" type="text" size="40" maxlength="96"
                                                   style="vertical-align: middle;"><br>
                                        </div>
                                        <div class="space-line"></div>
                                        <br>
                                </form>
                            </td>
                    </table>
                </div>
                <div class=prf>
                    <table summary="filters settings" width="100%">
                        <tbody>
                        <tr>
                            <td class="lr">
                                <form action="./?v=prf" name=f enctype=multipart/form-data method=POST>
                                    <div class="middle-container-padded"><b>Choose action</b> - Now select the
                                        action that you'd like to take on messages that match the criteria you've
                                        specified.<br><br>When a message arrives that matches this search, do the
                                        following:<br><br>
                                        <div class="middle-container-padded">

                                            <input id="ch_cp" type="checkbox" name="ch_cp"
                                                   style="vertical-align: middle;" value=true>
                                            <label for="ch_cp"><b>Copy to:</b></label> &nbsp;
                                            <select id="copy-select" name=tact>
                                                <option value="">...</option>
                                                <#if folders??>
                                                    <#list folders as folder>
                                                        <option value="${folder.name}">${folder.name}</option>
                                                    </#list>
                                                </#if>
                                            </select><br>
                                            <input id="ch_mv" type="checkbox" name="ch_mv"
                                                   style="vertical-align: middle;" value=true>
                                            <label for="ch_mv"><b>Move to:</b></label> &nbsp;
                                            <select id="move-select" name=tact>
                                                <option value="">...</option>
                                                <#if folders??>
                                                    <#list folders as folder>
                                                        <option value="${folder.name}">${folder.name}</option>
                                                    </#list>
                                                </#if>
                                            </select><br>
                                            <input id="ch_mrk" type="checkbox" name="ch_mrk"
                                                   style="vertical-align: middle;" value=true>
                                            <label for="ch_mrk"><b>Mark as:</b></label> &nbsp;
                                            <select id="mark-select" name=tact>
                                                <option value="">...</option>
                                                <option value="MARKREAD">Read</option>
                                                <option value="MARKUNREAD">Unread</option>
                                            </select><br>

                                            <div class="middle-container"><br>
                                                <input name="create" value="Create Filter" type="button"
                                                       onclick="newFilter();"
                                                       style="font-weight: bold;"> &nbsp;
                                            </div>
                                            <br></div>
                                </form>
                            </td>
                    </table>
                </div>
                <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=#fad163>
                    <tr>
                        <td height=3><img height=1 width=1 alt=""></td>
                </table>
            </td>
            <td width="3" bgcolor="#fad163"></td>
    </table>
</#macro>

<@page/>