<#include "base.ftl">

<#macro title>
    Sent Mail
</#macro>
<#macro body>
    <td valign=top>
    <table width=100% cellpadding=0 cellspacing=0 border=0>
        <tr>
            <td width=5 bgcolor="#C3D9FF">&nbsp;</td>
            <td>
                <form action="/mail/handle" method=POST>
                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                        <tr>
                            <td><b>Sent Mail</b>&nbsp;&nbsp;<select name=tact>
                                    <option value="">More Actions...</option>
                                    <option value=ib>Move to Inbox</option>
                                    <option value=rd>Mark as read</option>
                                    <option value=ur>Mark as unread</option>
                                    <option value=st>Add star</option>
                                    <option value=xst>Remove star</option>
                                    <option value=tr>Delete</option>
                                    <option value=ig>Mute</option>
                                </select>&nbsp;<input type=submit name=nvp_tbu_go value="Go">&nbsp; <a
                                        href="?&amp;s=s" class="searchPageLink">Refresh</a></td>
                            <td align=right><b>1</b>&nbsp;-&nbsp;<b>10</b> of about <b>13</b> &nbsp;<a
                                        href="?&amp;s=s&amp;st=10" class="searchPageLink"><b>Older
                                        &#8250;</b></a>
                    </table>
                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor=#e8eef7 class=th>
                        <tr bgcolor="#ffffff">
                            <td width=1% nowrap><input type=checkbox name=t
                                                       value="16ad184b480a8233"><img
                                        src="https://ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif"
                                        width=15 height=15 border=0 alt=""></td>
                            <td width=25%>To: me (4)</td>
                            <td width=73%><a href="?&amp;th=16ad184b480a8233&amp;v=c&amp;s=s"><span
                                            class=ts><font size=1><font
                                                    color=#006633>Inbox</font></font> <b>(no subject)</b><font
                                                color=#7777CC> - On 19/05/2019, Анатолий Никифоров &lt;flisochcs@gmail.com&gt; wrote: &gt; asdaf &gt; фыв</font></span></a>
                            </td>
                            <td width=1% nowrap><b>22:14</b>
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="16ad12d8d29aae2e"></td>
                            <td>To: me</td>
                            <td><a href="?&amp;th=16ad12d8d29aae2e&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font color=#006633>Inbox</font></font> superTestqwerty<font
                                                color=#7777CC> - superTestqwerty</font></span></a></td>
                            <td nowrap>20:39
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="16ac6280e4039396"></td>
                            <td>To: flisoch, me (4)</td>
                            <td><a href="?&amp;th=16ac6280e4039396&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font color=#006633>Inbox</font></font> (no subject)<font
                                                color=#7777CC> - qwerty On Fri, 17 May 2019 at 17:17, Анатолий Никифоров &lt;flisochcs@gmail.com&gt; wrote: dfsdfs On Fri, 17 May 2019 at 16:34, Анатолий Никифоров &lt;flisochcs@gmail.com&gt; wrote: </font></span></a>
                            </td>
                            <td nowrap>17&nbsp;May
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="16ac6035ae82437d"></td>
                            <td>To: me, flisoch</td>
                            <td><a href="?&amp;th=16ac6035ae82437d&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font color=#006633>Inbox</font></font> (no subject)<font
                                                color=#7777CC> - cc</font></span></a></td>
                            <td nowrap>17&nbsp;May
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="16ac5fd7d13153d8"></td>
                            <td>To: ma (2)</td>
                            <td><img src="https://ssl.gstatic.com/ui/v1/icons/mail/images/paperclip.gif"
                                     width=15 height=15 border=0 alt="Attachment" class=p><a
                                        href="?&amp;th=16ac5fd7d13153d8&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font
                                                    color=#006633></font></font> ITIS 11-701 03sem Yuldashev<font
                                                color=#7777CC> - Forwarded message From: Анатолий Никифоров &lt;flisochcs@gmail.com&gt; Date: Sun, 17 Mar 2019 at 19:45 Subject: ITIS 11-701 03sem Yuldashev To: &lt;ma@it.kfu.ru&gt; 1) файл &quot;</font></span></a>
                            </td>
                            <td nowrap>17&nbsp;Mar
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="1680584e97fb1d08"></td>
                            <td>To: list-unsubscribe+767.</td>
                            <td><a href="?&amp;th=1680584e97fb1d08&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font
                                                    color=#006633></font></font> unsubscribe<font
                                                color=#7777CC> - This message was automatically generated by Gmail.</font></span></a>
                            </td>
                            <td nowrap>31/12/2018
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="1674bd5815607693"></td>
                            <td>To: dostavka</td>
                            <td><a href="?&amp;th=1674bd5815607693&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font color=#006633></font></font> Выбор способа доставки &quot;Гарри Поттера и методов рационального мышления&quot;<font
                                                color=#7777CC> - Добрый день, Номер заказа: 1014032 Я выбираю Почту России</font></span></a>
                            </td>
                            <td nowrap>25/11/2018
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="167141f0c61d69ee"></td>
                            <td>To: leave-fc881574746205.</td>
                            <td><a href="?&amp;th=167141f0c61d69ee&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font
                                                    color=#006633></font></font> unsubscribe<font
                                                color=#7777CC> - This message was automatically generated by Gmail.</font></span></a>
                            </td>
                            <td nowrap>15/11/2018
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="16707fa401632902"></td>
                            <td>To: unsubscribe</td>
                            <td><a href="?&amp;th=16707fa401632902&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font color=#006633></font></font> unsubscribe:0NxjqWitHoZyg38vpmLiqQpVo2GR2S9iI-Hlus9OtZ0~|eyAicmNwdF90byI6ICJmbGlzb2NoY3...<font
                                                color=#7777CC> - This message was automatically generated by Gmail.</font></span></a>
                            </td>
                            <td nowrap>12/11/2018
                        <tr bgcolor="#E8EEF7">
                            <td><input type=checkbox name=t value="163185a60515cb56"></td>
                            <td>To: katerina.paleha</td>
                            <td><img src="https://ssl.gstatic.com/ui/v1/icons/mail/images/paperclip.gif"
                                     width=15 height=15 border=0 alt="Attachment" class=p><a
                                        href="?&amp;th=163185a60515cb56&amp;v=c&amp;s=s"><span class=ts><font
                                                size=1><font color=#006633></font></font> Итис Резюме 1 курс<font
                                                color=#7777CC> - Юлдашев Ниёз 11-701</font></span></a>
                            </td>
                            <td nowrap>30/04/2018
                    </table>
                    <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                        <tr>
                            <td><b>Sent Mail</b>&nbsp;&nbsp;<select name=bact>
                                    <option value="">More Actions...</option>
                                    <option value=ib>Move to Inbox</option>
                                    <option value=rd>Mark as read</option>
                                    <option value=ur>Mark as unread</option>
                                    <option value=st>Add star</option>
                                    <option value=xst>Remove star</option>
                                    <option value=tr>Delete</option>
                                    <option value=ig>Mute</option>
                                </select>&nbsp;<input type=submit name=nvp_bbu_go value="Go">&nbsp; <a
                                        href="/mail/sent" class="searchPageLink">Refresh</a></td>
                            <td align=right><b>1</b>&nbsp;-&nbsp;<b>10</b> of about <b>13</b> &nbsp;<a
                                        href="?&amp;s=s&amp;st=10" class="searchPageLink"><b>Older
                                        &#8250;</b></a>
                    </table>
        </tr>
        </form>
    </table>
</#macro>

<@page/>