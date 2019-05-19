<#include "base.ftl">
<#macro title>
    Search Options
</#macro>
<#macro body>
<td valign=top>
    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=5 bgcolor=#74d982>&nbsp;</td>
            <td bgcolor=#b5edbc>
                <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor=#74d982>
                    <tr>
                        <td><font size=+1><b>Search Options</b></font></td>
                        <td align=right><a href="?&amp;s=m&amp;st=70">Hide search options</a>
                </table>
                <form action="?&amp;fv=as&amp;s=v" name=asf method=POST><input type=hidden name=redir
                                                                               value="?&amp;s=m&amp;st=70">
                    <table width=100% cellpadding=2 cellspacing=0 border=0 class=search>
                        <tr>
                            <td width=1% align=right nowrap><b>From:</b></td>
                            <td width=43%><input type=text tabindex=51 size=20 class=i name=as_from value="">
                            </td>
                            <td width=1%>&nbsp;</td>
                            <td width=1% align=right nowrap><b>Has the words:</b></td>
                            <td width=44% colspan=2><input type=text tabindex=55 size=20 class=i name=as_has
                                                           value="">
                        <tr>
                            <td align=right nowrap><b>To:</b></td>
                            <td><input type=text tabindex=52 size=20 class=i name=as_to value=""></td>
                            <td>&nbsp;</td>
                            <td align=right nowrap><b>Doesn't have:</b></td>
                            <td colspan=2><input type=text tabindex=56 size=20 class=i name=as_hasnot value="">
                        <tr>
                            <td align=right nowrap><b>Subject:</b></td>
                            <td><input type=text tabindex=53 size=20 class=i name=as_subj value=""></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan=2><input type=checkbox tabindex=57 id=as_attach name=as_attach value=1>&nbsp;&nbsp;<label
                                        for=as_attach><b>Has attachment</b></label>
                        <tr>
                            <td align=right nowrap><b>Search:</b></td>
                            <td><select name=as_subset tabindex=54>
                                    <option value="all">All Mail</option>
                                    <option value="inbox">Inbox</option>
                                    <option value="starred">Starred</option>
                                    <option value="sent">Sent Mail</option>
                                    <option value="drafts">Drafts</option>
                                    <option value="spam">Spam</option>
                                    <option value="trash">Bin</option>
                                    <option disabled>--------</option>
                                    <option value="ast">Mail &amp; Spam &amp; Bin</option>
                                    <option disabled>--------</option>
                                    <option value="read">Read Mail</option>
                                    <option value="unread">Unread Mail</option>
                                </select></td>
                            <td>&nbsp;</td>
                            <td align=right nowrap><b>Date within:</b></td>
                            <td width=1% nowrap><select name=as_within tabindex=58>
                                    <option value="1d">1 day</option>
                                    <option value="3d">3 days</option>
                                    <option value="1w">1 week</option>
                                    <option value="2w">2 weeks</option>
                                    <option value="1m">1 month</option>
                                    <option value="2m">2 months</option>
                                    <option value="6m">6 months</option>
                                    <option value="1y">1 year</option>
                                </select>&nbsp;<b>of</b>&nbsp;
                            </td>
                            <td><input type=text tabindex=59 size=20 class=i name=as_date value="">
                        <tr>
                            <td colspan=5></td>
                            <td align=right style="padding:0 6px 0 0">
                                <div><font size=1>e.g. today, Tuesday, 7 Jan, 7/1/2014</font></div>
                    </table>
                    <input type="hidden" name="at" value="AF6bupP082uaNuFb6fTFq7eaF-ESA5uLyA">
                    <table width=100% cellpadding=2 cellspacing=0 border=0 class=h>
                        <tr>
                            <td align=center><input type=submit tabindex=60 name=nvp_bu_sm value="Search Mail">
                                <input type=submit tabindex=61 name=nvp_bu_canc value="Cancel">&nbsp;
                    </table>
                </form>
                <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=#74d982>
                    <tr>
                        <td height=5><img height=1 width=1 alt=""></td>
                </table>
            </td>
            <td width=5 bgcolor=#74d982>&nbsp;</td>
    </table>
    </#macro>
    <@page/>
    <#--
    </pre>
    <head>
        <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
        <title>Gmail - Search Options</title>
        <link rel="canonical" href="https://mail.google.com/mail/"/>
        <link rel="shortcut icon" href="https://ssl.gstatic.com/ui/v1/icons/mail/images/favicon5.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css"
              href="https://mail.google.com/mail/u/0/h/_//?&amp;name=c&amp;ver=1u0jjnc8m0l4h&amp;v=ss">
        <style type="text/css" nonce="BjsGl2b8pPi1q1pjtbXGAA">
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
        <base href="https://mail.google.com/mail/u/0/h/1ra7skimtqgkr/">
        <script type="text/javascript" nonce="BjsGl2b8pPi1q1pjtbXGAA"><!--
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
            //&ndash;&gt;</script>
        <script type="text/javascript" nonce="BjsGl2b8pPi1q1pjtbXGAA"><!--

            //&ndash;&gt;</script>
    </head>
    <body bgcolor=#ffffff>
    <div id=gbar>
        <nobr><a target=_blank class=gb1 href="https://www.google.ru/webhp?tab=mw">Search</a> <a target=_blank class=gb1
                                                                                                 href="http://www.google.ru/imghp?hl=en-GB&tab=mi">Images</a>
            <a target=_blank class=gb1 href="https://maps.google.ru/maps?hl=en-GB&tab=ml">Maps</a> <a target=_blank
                                                                                                      class=gb1
                                                                                                      href="https://play.google.com/?hl=en-GB&tab=m8">Play</a>
            <a target=_blank class=gb1 href="https://www.youtube.com/?gl=RU&tab=m1">YouTube</a> <b class=gb1>Gmail</b> <a
                    target=_blank class=gb1 href="https://drive.google.com/?ogsrc=32&tab=mo&authuser=0">Drive</a> <a
                    target=_blank class=gb1 href="https://www.google.com/calendar?tab=mc">Calendar</a> <a target=_blank
                                                                                                          class=gb1
                                                                                                          style="text-decoration:none"
                                                                                                          href="https://www.google.ru/intl/en-GB/about/products?tab=mh"><u>More</u>
                &raquo;</a></nobr>
    </div>
    <div id=guser width=100%><h2 class=hdn>Account Options</h2>
        <nobr><span id=gbn class=gbi></span><span id=gbf class=gbf></span><b class=gb4>flisochcs@gmail.com</b> | <span
                    id=gbe></span><a target=_blank href="https://myaccount.google.com/?utm_source=OGB&tab=mk" class=gb4>Google
                Account</a> | <a href="?&v=prg" class=gb4>Settings</a> | <a target=_blank
                                                                            href="/support/?&ctx=mail&hl=en-GB" class=gb4>Help</a>
            | <a target=_top id=gb_71 href="https://mail.google.com/mail/logout?hl=en-GB" class=gb4>Sign out</a></nobr>
    </div>
    <div class=gbh style=left:0></div>
    <div class=gbh style=right:0></div>
    <table width=100% cellpadding=4 cellspacing=0 border=0 class=bn>
        <tr>
            <td id=bm bgcolor=#FAE5B0><b>You are currently viewing Gmail in basic HTML. &nbsp; <a
                            href="?&amp;at=AF6bupP082uaNuFb6fTFq7eaF-ESA5uLyA&amp;redir=/mail/u/0/&amp;a=stsv">Switch to
                        standard view</a></b> | <a
                        href="?&amp;at=AF6bupP082uaNuFb6fTFq7eaF-ESA5uLyA&amp;redir=?&amp;a=pbhtml">Set basic HTML as
                    default view</a></td>
        </tr>
    </table>
    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=143 rowspan=3><h1><a href="?"><img src="//www.gmail.com/mail/help/images/logonew.gif" width=143
                                                         height=59 border=0 alt="Gmail by Google"></a></h1></td>
            <td width=1 rowspan=3>&nbsp;</td>
            <td height=25 colspan=2 align=right valign=top>
        <tr>
            <td height=25>
        <tr>
            <td height=25 colspan=2>
                <script type="text/javascript" nonce="BjsGl2b8pPi1q1pjtbXGAA">
                    var searchButtonElements = document.getElementsByClassName("search-form-submit");
                    for (i = 0; i < searchButtonElements.length; i++) searchButtonElements[i].onclick = function (event) {
                        var urlParams = {"s": "q", "q": document.getElementById("sbq").value};
                        urlParams[event.target.getAttribute("name")] = event.target.getAttribute("value");
                        var encodedParams = [];
                        for (var param in urlParams) encodedParams.push(encodeURIComponent(param) + "\x3d" + encodeURIComponent(urlParams[param]));
                        document.getElementById("sbf").setAttribute("action", "?" + encodedParams.join("\x26"))
                    };
                </script>
    </table>
    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=120 valign=top>
                <table width=100% cellpadding=2 cellspacing=0 border=0 class=m>
                    <tr>
                        <td><b><a href="?&amp;cs=b&amp;pv=tl&amp;v=b&amp;s=m&amp;st=70"
                                  accesskey=c>Compose&nbsp;Mail</a></b>
                    <tr>
                        <td height=5><h2 class=hdn>Folders</h2>
                    <tr>
                        <td><h3><b><a href="?&amp;" accesskey=i>Inbox&nbsp;(165)</a></b></h3>
                    <tr>
                        <td><a href="?&amp;s=r">Starred&nbsp;<img
                                        src="https://ssl.gstatic.com/ui/v1/icons/mail/images/star_on_sm_2.gif" width="13"
                                        height="13" border="0" alt="star"/></a>
                    <tr>
                        <td><a href="?&amp;s=s">Sent Mail</a>
                    <tr>
                        <td><h3><b><a href="?&amp;s=d">Drafts&nbsp;(1)</a></b></h3>
                    <tr>
                        <td><a href="?&amp;s=a">All Mail</a>
                    <tr>
                        <td><h3><b><a href="?&amp;s=m">Spam&nbsp;(108)</a></b></h3>
                    <tr>
                        <td><a href="?&amp;s=t">Bin</a>
                    <tr>
                        <td height=8>
                    <tr>
                        <td><h3><b><a href="?&amp;v=cl">Contacts</a></b></h3>
                    <tr>
                        <td height=8>
                </table>
                <table width=100% cellpadding=2 cellspacing=0 border=0 class=l>
                    <tr>
                        <td class=lb><h2><font color=#000000>Labels</font><br></h2><a class="ml" href="?&amp;v=prl">Edit
                                labels</a></td>
                </table>

                <table cellpadding=2 cellspacing=0 border=0 align=center class=ft>
                    <tr>
                        <td align=center>Search accurately with <a style=color:#0000CC target=_blank
                                                                   href="https://support.google.com/mail/bin/answer.py?ctx=gmail&answer=7190&hl=en-GB&authuser=0">operators</a>
                            including <b>from:</b> &nbsp;<b>to:</b> &nbsp;<b>subject:</b>.
                    <tr>
                        <td align=center><span style="color: #006633; font-weight: bold;">You're currently using 278 MB (1%) of your 15360 MB</span>
                            <div style="font-size: 11px; line-height: 17px;">This account is open in 1 other location at
                                this IP (176.52.4.6). &nbsp; Last account activity: 1 minute ago on this computer. &nbsp;<a
                                        href="?&amp;v=ac" target="details">Details</a></div>
                            <div style="margin:5 0 10">
                                <table cellpadding=0 cellspacing=0 border=0 class=bn>
                                    <tr>
                                        <td id=bm bgcolor=#FAE5B0>
                                            <div style="font-size:85%;margin:3 15 3 15;">Gmail view: <a
                                                        href="?&amp;at=AF6bupP082uaNuFb6fTFq7eaF-ESA5uLyA&amp;redir=/mail/u/0/&amp;a=stsv">standard</a>
                                                | <b>basic HTML</b>&nbsp; <a
                                                        href="https://support.google.com/mail/bin/answer.py?ctx=%67mail&amp;answer=15049"
                                                        target="_blank">Learn more</a></div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                    <tr>
                        <td align=center><font size=1><a href="https://www.google.com/intl/en-GB/policies/terms/"
                                                         target="_blank">Terms</a> - <a
                                        href="https://www.google.com/intl/en-GB/policies/privacy/"
                                        target="_blank">Privacy</a> - <a href="https://www.google.com" target=_blank>Google
                                    Home</a></font>
                </table>
                <script type="text/javascript"
                        src="https://mail.google.com/mail/u/0/h/_//?&amp;name=tz&amp;ver=y9x2bdduin5g&amp;v=mjs"
                        nonce="BjsGl2b8pPi1q1pjtbXGAA"></script>
                <script type="text/javascript" nonce="BjsGl2b8pPi1q1pjtbXGAA">
                    _tz("_44_44_123780_40_436260", "?\x26v\x3dtz");
                </script>
                <script type="text/javascript" nonce="BjsGl2b8pPi1q1pjtbXGAA">
                    'name\x3d"at" value\x3d0\x26name\x3dat value\x3d0\x26at\x3d0\x26amp;at\x3d0\x26';
                </script>
    </table>
    </body>
    </html>-->
