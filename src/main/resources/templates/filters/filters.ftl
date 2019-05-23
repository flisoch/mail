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
                        <tr>
                            <td>
                                <#if filters??>
                                    <#list filters as filter>
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
                                    </#list>
                                </#if>

                            </td>

                        <tr>
                            <td class="lr">
                                <form action="/settings/filters/new" name=f enctype=multipart/form-data method=POST>
                                    <div class="middle-container"><input name="nvp_bu_nftb"
                                                                         value="Create a new Filter"
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
                                    <div class="middle-container-padded"><input id="cf2_ar" type="checkbox"
                                                                                name="cf2_ar"
                                                                                style="vertical-align: middle;"
                                                                                value=true checked/> <label
                                                for="cf2_ar"><b>Skip the Inbox</b></label> (Archive
                                        it)<br><input id="cf2_st" type="checkbox" name="cf2_st"
                                                      style="vertical-align: middle;" value=true/> <label
                                                for="cf2_st"><b>Star it</b></label><br><input id="cf2_cat"
                                                                                              type="checkbox"
                                                                                              name="cf2_cat"
                                                                                              style="vertical-align: middle;"
                                                                                              value=true/>
                                        <label for="cf2_cat"><b>Apply the label:</b></label> &nbsp; <select
                                                style="vertical-align: middle;" name="cf2_sel">
                                            <option value="Choose label...">Choose label...</option>
                                            <option value="qwert">qwert</option>
                                        </select><br><input id="cf2_emc" type="checkbox" name="cf2_emc"
                                                            style="vertical-align: middle;" value=true disabled>
                                        <label for="cf2_emc"><b>Forward it to:</b></label> &nbsp; No verified
                                        forwarding addresses. You must <a href="?v=prfap">set up a verified
                                            forwarding address first</a>.<br><input id="cf2_tr" type="checkbox"
                                                                                    name="cf2_tr"
                                                                                    style="vertical-align: middle;"
                                                                                    value=true/> <label
                                                for="cf2_tr"><b>Delete it</b></label><br><br><br><input id="irf"
                                                                                                        type="checkbox"
                                                                                                        style="vertical-align: middle;"
                                                                                                        name="irf"/>
                                        <label for="irf" style="font-weight: normal;">Also apply filter to
                                            <b>3</b> conversations below</label><input name="cf1_from"
                                                                                       type="hidden"
                                                                                       value=""><input
                                                name="cf1_to" type="hidden" value=""><input name="cf1_subj"
                                                                                            type="hidden"
                                                                                            value=""><input
                                                name="cf1_has" type="hidden" value="necunos"><input
                                                name="cf1_hasnot" type="hidden" value=""><input
                                                name="cf1_attach" type="hidden" value="false"><input
                                                name="cf1_excludechats" type="hidden" value="false"><input
                                                name="tfi" id="tfi" type="hidden"
                                                value="z0000001558287538469*0462745563070394999"><input name="s"
                                                                                                        id="z"
                                                                                                        type="hidden"
                                                                                                        value="z"><input
                                                name="at" type="hidden"
                                                value="AF6bupP082uaNuFb6fTFq7eaF-ESA5uLyA"></div>
                                    <div class="space-line"></div>
                                    <div class="middle-container"><br><input name="nvp_bu_xftb" value="Cancel"
                                                                             type="submit"> &nbsp; <input
                                                name="nvp_bu_pvsb" value="Back" type="submit"> &nbsp; <input
                                                name="nvp_bu_cftb" value="Create Filter" type="submit"
                                                style="font-weight: bold;"> &nbsp;
                                    </div>
                                    <br></div>
                            </form>
                        </td>
                </table>
            </div>
    </table>
</#macro>

<@page/>