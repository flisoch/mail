<#include "base/base.ftl">


<#macro title>
    Compose Mail
</#macro>


<#macro body>
    <td valign=top>
    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=5 bgcolor="#C3D9FF">&nbsp;</td>
            <td bgcolor=#e0ecff>
                <form action="/mail/new" name=f
                      enctype=multipart/form-data method=POST>

                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                        <tr>
                            <td><input type=submit name=nvp_bu_send value="Send">
                    </table>
                    <h2 class=hdn>Compose Form</h2>
                    <table width=100% cellpadding=2 cellspacing=0 border=0 class=compose>
                        <tr>
                            <td width=1% align=right valign=top nowrap><b id=l-to>To:</b></td>
                            <td><textarea onkeyup="contactsByName('to');" name=to id=to rows=3 cols=55 wrap=virtual
                                          class=i
                                          autofocus="autofocus" aria-labelledby=l-to></textarea>
                                <div valign=top id="contacts-to" hidden></div>
                        <tr>
                            <td align=right nowrap><b id=l-cc>Cc:</b></td>
                            <td><input onkeyup="contactsByName('cc');" name=cc id=cc value="" type=text size=40 class=i
                                       autocomplete=off
                                       aria-labelledby=l-cc>
                                <div valign=top id="contacts-cc" hidden></div>
                        <tr>
                            <td align=right nowrap><b id=l-bcc>Bcc:</b></td>
                            <td><input onkeyup="contactsByName('bcc');" name=bcc id=bcc value="" type=text size=40
                                       class=i
                                       autocomplete=off aria-labelledby=l-bcc>
                                <div valign=top id="contacts-bcc" hidden></div>
                        <tr>
                            <td align=right nowrap><b id=l-s>Subject:</b></td>
                            <td><input name=subject value="" type=text size=40 class=i
                                       aria-labelledby=l-s>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <textarea name=text title="Message Body" rows=15 cols=50 wrap=virtual class=mi
                                          aria-label="Message Body"></textarea>
                    </table>
                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                        <tr>
                            <td><input type=submit name=nvp_bu_send value="Send">&nbsp;
                    </table>
        </tr>
        </form>
    </table>
</#macro>

<@page/>