<#include "base/base.ftl">


<#macro title>
    message
</#macro>

<#macro body>

    <td valign=top>
        <table width=100% cellpadding=0 cellspacing=0 border=0>
            <tr>
                <td width=5 bgcolor="#C3D9FF"></td>
                <td>
                    <#if messages??>
                        <#list messages as message>
                            <table width=98% cellpadding=1 cellspacing=0 border=0 bgcolor=#cccccc align=center>
                                <tr>
                                    <td>
                                        <table width=100% cellpadding=1 cellspacing=0 border=0 bgcolor=#efefef>
                                            <tr>
                                                <td>
                                                    <h3>
                                                        <b>${user.username}</b>
                                                    </h3>
                                                </td>
                                                <td id="send_time_${message.id}"align=right
                                                    valign=top>${message.sendTime}</td>
                                                <script>
                                                    document.getElementById('send_time_${message.id}').innerText
                                                        = formatDate(${message.sendTime?long?c});
                                                </script>
                                            </tr>
                                            <tr>
                                                <#if message.to??>
                                                    <#list message.to as to>
                                                <td colspan=2>
                                                        <span>To:${to.username}</span>
                                                    </#list>
                                                </#if>

                                                <#if message.cc??>
                                                    <#list message.cc as cc>
                                                <td colspan=2>
                                                        <span>Cc:${cc.username}</span>
                                                    </#list>
                                                </#if>
                                            </tr>
                                            <tr>
                                                <td colspan=2>

                                            <tr bgcolor=#ffffff>
                                                <td colspan=2>
                                                    <div class=msg><font color=#550055>

                                                        <td id="send_time_${message.id}-r" align=right
                                                            valign=left>${message.sendTime}
                                                        </td>
                                                        <td>${message.sender.username}</td>

                                                wrote:<br/>
                                                &gt; ${message.text}<br/>
                                                <#--&gt;<br/>
                                                &gt; On 24/05/2019, Анатолий Никифоров &lt;
                                                <a href="">flisochcs@gmail.com</a>&gt;
                                                wrote:<br/>
                                                &gt;&gt; READ1<br/>
                                                &gt;&gt;<br/>
                                                &gt;<br/>
                                                </font>READ2?<br/>-->
                                                </div></tr>
                                            <script>
                                                document.getElementById('send_time_${message.id}-r').innerText
                                                    = formatDate(${message.sendTime?long?c});
                                            </script>
                                        </table>
                                    </td>
                                </tr>
                            </table>

                        </#list>
                    </#if>

                    <table width=100% cellpadding=1 cellspacing=0 border=0 bgcolor=#e0ecff class=qr>
                        <tr>
                            <td bgcolor=#c3d9ff><b>Quick Reply</b>
                        <tr>
                            <td>
                                <table width=1% cellpadding=0 cellspacing=0 border=0
                                       bgcolor=#e0ecff>
                                    <form action=""
                                          name=qrf method=POST>
                                        <tr>
                                            <td colspan=2>
                                                <table width=100% cellpadding=1 cellspacing=0
                                                       border=0>
                                                    <tr>
                                                        <td width=99%><b>To:</b> <input
                                                                    type=hidden name=qrr
                                                                    value=o>Анатолий Никифоров
                                                            &lt;flisochcs@gmail.com&gt;
                                                        </td>
                                                        <td width=1% valign=bottom><input
                                                                    type=submit name=nvp_bu_qtf
                                                                    value="More Reply Options">
                                                        </td>
                                                </table>
                                        <tr>
                                            <td><textarea name=body rows=10 cols=100
                                                          wrap=virtual></textarea>
                                        <tr>
                                            <td><input type=submit name=nvp_bu_send
                                                       value="Send"></td>

                                        </tr>
                                    </form>
                                </table>
                    </table>
                </td>
            </tr>
        </table>
    </td>
    <br>

</#macro>

<@page/>