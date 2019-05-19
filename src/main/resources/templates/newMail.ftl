<#include "base.ftl">


<#macro title>
    Compose Mail
</#macro>


<#macro imports>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <link rel="canonical" href="/mail"/>
    <link rel="stylesheet" type="text/css"
          href="https://mail.google.com/mail/u/0/h/_//?&amp;name=c&amp;ver=1u0jjnc8m0l4h&amp;v=ss">
    <style type="text/css" nonce="D7dsVOj0ZdsYRcMT2DHAvw">
        @import url("https://mail.google.com/mail/u/0/h/_//?&name=a&ver=1sthyqjwgl8hj&v=ss");

        #gbar, #guser {
            font-size: 13px;
            padding-right: 8px;
            padding-top: 4px !important;
        }

        #gbar {
            padding-left: 8px;
            height: 22px
        }

        #guser {
            padding-bottom: 7px !important;
            text-align: right
        }

        .gbh, .gbd {
            border-top: 1px solid #c9d7f1;
            font-size: 1px
        }

        .gbh {
            height: 0;
            position: absolute;
            top: 24px;
            width: 100%
        }

        @media all {
            .gb1 {
                height: 22px;
                margin-right: .5em;
                vertical-align: top
            }

            #gbar {
                float: left
            }
        }

        a.gb1, a.gb4 {
            text-decoration: underline !important
        }

        a.gb1, a.gb4 {
            color: #00c !important
        }

        .gbi .gb4 {
            color: #dd8e27 !important
        }

        .gbf .gb4 {
            color: #900 !important
        }
    </style>
    <script type="text/javascript" nonce="D7dsVOj0ZdsYRcMT2DHAvw"><!--
        (function () {
            try {
                var win = this;
                while (true) {
                    if (win.parent == win) break;
                    win.frameElement.src.substr(0, 1);
                    win = win.parent
                }
                if (win.frameElement != null) throw"busted";
            } catch (e) {
                document.write("--\x3e\x3cplaintext style\x3ddisplay:none\x3e\x3c!--");
                try {
                    if (!open(location, "_top")) alert("This content cannot be framed");
                    top.location = location
                } catch (d) {
                }
            }
        })();
        //--></script>
    <script type="text/javascript" nonce="D7dsVOj0ZdsYRcMT2DHAvw"><!--

        //--></script>
</#macro>


<#macro body>
<td valign=top>
            <table width=100% cellpadding=0 cellspacing=0 border=0>
                <tr>
                    <td width=5 bgcolor="#C3D9FF">&nbsp;</td>
                    <td bgcolor=#e0ecff>
                        <form action="?&amp;fv=b&amp;cs=c&amp;pv=tl&amp;cpt=c&amp;v=b&amp;s=m&amp;st=70" name=f
                              enctype=multipart/form-data method=POST><input type=hidden name=redir
                                                                             value="?&amp;s=m&amp;st=70"><input
                                    type=hidden name=at value="AF6bupP082uaNuFb6fTFq7eaF-ESA5uLyA">
                            <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                                <tr>
                                    <td><input type=submit name=nvp_bu_send value="Send">&nbsp; <input
                                                type=submit name=nvp_bu_sd value="Save Draft">&nbsp; <input
                                                type=submit name=nvp_bu_d value="Discard">&nbsp;
                            </table>
                            <h2 class=hdn>Compose Form</h2>
                            <table width=100% cellpadding=2 cellspacing=0 border=0 class=compose>
                                <tr>
                                    <td width=1% align=right valign=top nowrap><b id=l-to>To:</b></td>
                                    <td><textarea name=to id=to rows=3 cols=55 wrap=virtual class=i
                                                  autocomplete=off aria-labelledby=l-to>
</textarea>
                                <tr>
                                    <td align=right nowrap><b id=l-cc>Cc:</b></td>
                                    <td><input name=cc id=cc value="" type=text size=40 class=i autocomplete=off
                                               aria-labelledby=l-cc>
                                <tr>
                                    <td align=right nowrap><b id=l-bcc>Bcc:</b></td>
                                    <td><input name=bcc id=bcc value="" type=text size=40 class=i
                                               autocomplete=off aria-labelledby=l-bcc>
                                <tr>
                                    <td align=right nowrap><b id=l-s>Subject:</b></td>
                                    <td><input name=subject value="" type=text size=40 class=i
                                               aria-labelledby=l-s>
                                <tr>
                                    <td align=right nowrap><img
                                                src="https://ssl.gstatic.com/ui/v1/icons/mail/images/paperclip.gif"
                                                width=15 height=15 border=0 alt="Attachment"></td>
                                    <td valign=bottom><b>Attachments:</b>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><input name=file0 type=file size=42 aria-label="Attach File">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><input type=submit name=nvp_bu_amf value="Attach More Files">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><textarea name=body title="Message Body" rows=15 cols=50 wrap=virtual
                                                  class=mi aria-label="Message Body">
</textarea>
                            </table>
                            <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                                <tr>
                                    <td><input type=submit name=nvp_bu_send value="Send">&nbsp; <input
                                                type=submit name=nvp_bu_sd value="Save Draft">&nbsp; <input
                                                type=submit name=nvp_bu_d value="Discard">&nbsp;
                            </table>
                </tr>
                </form>
            </table>
</#macro>

<@page/>