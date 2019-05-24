<#macro navbar>
    <div id=guser width=100%>
        <nobr><span id=gbn class=gbi></span>
            <span id=gbf class=gbf></span>

            <#if user??>
            <b class=gb4>${user.username}</b> |
            <a href="/settings" class=gb4>Settings</a> | </nobr>
            <a target=_top id=gb_71 href="/logout" class=gb4>Sign out</a></nobr>
            <#else>
                Anonimus
                <a target=_top id=gb_71 href="/auth" class=gb4>Sign in</a></nobr>
            </#if>

    </div>
    <div class=gbh style=left:0></div>
    <div class=gbh style=right:0></div>

    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=143 rowspan=3><h1><a href="/mail/INBOX"><img src="//www.gmail.com/mail/help/images/logonew.gif"
                                                                   width=143
                                                                   height=59 border=0></a></h1></td>
            <td width=1 rowspan=3>&nbsp;</td>
            <td height=25 colspan=2 align=right valign=top>
        </tr>
        <tr>
            <form action="/mail/search" method=POST
            <table width=100% cellpadding=0 cellspacing=0 border=0>
                <<#--td width=1% height=25 nowrap><input size=28 maxlength=2048 title="Search"
                                                     value=""><input type=submit value="Search Mail">
                </td>
                <td><font size=1><a href="/mail/search-options" st=70>Show search options</a></font>
                </td>-->
            </table>
            </form>
        </tr>
        <tr>
            <td height=25 colspan=2>

    </table>
</#macro>