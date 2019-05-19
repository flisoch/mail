<#include "navbar.ftl">
<#include "leftSideBar.ftl">
<#macro title></#macro>
<#macro imports></#macro>
<#macro body></#macro>


<#macro page>
    <!doctype html>
    <html lang="en">
    <head>

        <@imports/>
        <title><@title></@title></title>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" defer></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" defer></script>

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