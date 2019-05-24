<#include "navbar.ftl">
<#include "leftSideBar.ftl">
<#macro title></#macro>
<#macro import></#macro>
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

</#macro>
<#macro body></#macro>


<#macro page>
    <!doctype html>
    <html lang="en">
    <pre style="font-size: 0;display: none;visibility: hidden;"></pre>
    <head>

        <@imports/>
        <@import/>
        <title><@title></@title></title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" defer></script>
        <script src="/static/js/message.js"></script>
        <script src="/static/js/filters.js"></script>
        <script src="/static/js/settings.js"></script>
        <script src="/static/js/contacts.js"></script>
        <script src="/static/js/dateFormatter.js"></script>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        
    </head>
    <body>
        <@navbar/>
        <table width=100% cellpadding=0 cellspacing=0 border=0>

                <@left_sidebar/>
                <@body/>
        </table>
    </body>
    </html>
</#macro>