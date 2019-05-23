<#include "base/base.ftl">


<#macro title>
    ${currentFolder}
</#macro>


<#macro body>
    <td valign=top>

    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=5 bgcolor="#C3D9FF">&nbsp;</td>
            <td>
                <form action="?&amp;" name=f method=POST>

                    <h2 class=hdn>Inbox</h2>

                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                        <tr>
                            <td>

                                <input type="button"
                                       onclick="deleteMessages('${currentFolder}');"
                                       name="nvp_a_tr" value="Delete">&nbsp;&nbsp;


                                <select id="copy-select" name=tact>
                                    <option value="">Copy To...</option>
                                    <#if folders??>
                                        <#list folders as folder>
                                            <option value="${folder.name}">${folder.name}</option>
                                        </#list>
                                    </#if>
                                </select>

                                <select id="move-select" name=tact>
                                    <option value="">Move To...</option>

                                    <#if folders??>
                                        <#list folders as folder>
                                            <option value="${folder.name}">${folder.name}</option>
                                        </#list>
                                    </#if>
                                </select>

                                <select id="more-select" name=tact>
                                    <option value="">More Actions...</option>
                                    <option value="MARKREAD">Mark as read</option>
                                    <option value="MARKUNREAD">Mark as unread</option>
                                </select>&nbsp;

                                <input type=button onclick="handleMail('${currentFolder}')"
                                       value="Go">
                                <a href="?&amp;" class="searchPageLink">Refresh</a>
                            </td>
                            <td align=right><b>1</b>&nbsp;-&nbsp;<b>10</b> of about <b>17</b> &nbsp;
                                <a href="?&amp;st=10" class="searchPageLink">
                                    <b>Older &#8250;</b>
                                </a>
                    </table>

                    <table id="messages" width=100% cellpadding=2 cellspacing=0 border=0 bgcolor=#e8eef7
                           class=th>
                        <#if messages??>
                            <#if messages?has_content>


                                <#list messages as message>

                                    <tr id="message_${message.id}"
                                        <#if message.status="READ">bgcolor="#ffffff"</#if>
                                            <#if message.status="RECEIVED">bgcolor="#E8EEF7"</#if>
                                    >
                                        <td width=1% nowrap>
                                            <input type=checkbox name=t value="${message.id}">
                                            <img src="https://ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif"
                                                 width=15 height=15 border=0 alt="">
                                        </td>
                                        <td width=25%> ${message.sender.username} (4)</td>
                                        <td width=73%>
                                            <a href="?&amp;th=16ad184b480a8233&amp;v=c">
                                    <span class=ts>
                                        <#if message.subject??>
                                            <font size=1><font color=#006633></font></font> ${message.subject}
                                        <#else>
                                            <font size=1><font color=#006633></font></font>(no subject)
                                        </#if>
                                        <font color=#7777CC>
                                            ${message.text}
                                            ${message}
                                        </font>
                                    </span>
                                            </a>
                                        </td>
                                        <td id="send_time_${message.id}" width=1% nowrap> ${message.sendTime}
                                            <script>
                                                document.getElementById('send_time_${message.id}').innerText
                                                    = formatDate(${message.sendTime?long?c});
                                            </script>
                                    </tr>
                                </#list>
                            </#if>

                        </#if>
                    </table>
                    <script>
                        fillSpace();
                    </script>

                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                        <tr>
                            <td>
                                <input type="button"
                                       onclick="deleteMessages('${currentFolder}');"
                                       name="nvp_a_tr" value="Delete">&nbsp;&nbsp;

                                <select id="copy-select-bottom" name=tact>
                                    <option value="">Copy To...</option>
                                    <#if folders??>
                                        <#list folders as folder>
                                            <option value="${folder.name}">${folder.name}</option>
                                        </#list>
                                    </#if>
                                </select>

                                <select id="move-select-bottom" name=tact>
                                    <option value="">Move To...</option>

                                    <#if folders??>
                                        <#list folders as folder>
                                            <option value="${folder.name}">${folder.name}</option>
                                        </#list>
                                    </#if>
                                </select>

                                <select id="more-select-bottom" name=tact>
                                    <option value="">More Actions...</option>
                                    <option value="MARKREAD">Mark as read</option>
                                    <option value="MARKUNREAD">Mark as unread</option>
                                </select>&nbsp;

                                <input type=button onclick="handleMailBottom('${currentFolder}')"
                                       value="Go">
                                <a href="?&amp;" class="searchPageLink">Refresh</a>
                            </td>
                            <td align=right><b>1</b>&nbsp;-&nbsp;<b>10</b> of about <b>17</b> &nbsp;
                                <a href="?&amp;st=10" class="searchPageLink">
                                    <b>Older &#8250;</b>
                                </a>
                    </table>

        </tr>
        </form></table>
</#macro>


<@page/>