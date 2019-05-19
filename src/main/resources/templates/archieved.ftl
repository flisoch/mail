<#include "base.ftl">

<#macro body>
    <td valign=top>
        <table width=100% cellpadding=0 cellspacing=0 border=0>
            <tr>
                <td width=5 bgcolor="#C3D9FF">&nbsp;</td>
                <td>
                    <form action="/mail/handle" name=f method=POST>
                        <h2 class=hdn>All Mail</h2>
                        <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                            <tr>
                                <td><input type="submit" name="nvp_a_ib"
                                           value="Move to Inbox">&nbsp;&nbsp;
                                    <input type="submit"
                                                                                    name="nvp_a_sp"
                                                                                    value="Report Spam">&nbsp;&nbsp;
                                    <input
                                            type="submit" name="nvp_a_tr" value="Delete">&nbsp;&nbsp;
                                    <select
                                            name=tact>
                                        <option value="">More Actions...</option>
                                        <option value=arch>Archive</option>
                                        <option value=rd>Mark as read</option>
                                        <option value=ur>Mark as unread</option>
                                        <option value=st>Add star</option>
                                        <option value=xst>Remove star</option>
                                    </select>&nbsp;
                                    <input type=submit name=nvp_tbu_go value="Go">&nbsp; <a
                                            href="/mail/all" class="searchPageLink">Refresh</a>
                                </td>
                                <td align=right><b>1</b>&nbsp;-&nbsp;<b>10</b> of about <b>17</b> &nbsp;<a
                                            href="?&amp;s=a&amp;st=10" class="searchPageLink"><b>Older
                                            &#8250;</b></a>
                        </table>
                        <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor=#e8eef7 class=th>
                            <tr bgcolor="#ffffff">
                                <td width=1% nowrap><input type=checkbox name=t
                                                           value="16ad184b480a8233"><img
                                            src="https://ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif"
                                            width=15 height=15 border=0 alt=""></td>
                                <td width=25%><b>me</b> (4)</td>
                                <td width=73%><a href="?&amp;th=16ad184b480a8233&amp;v=c&amp;s=a"><span
                                                class=ts><font size=1><font
                                                        color=#006633>Inbox</font></font> <b>(no subject)</b><font
                                                    color=#7777CC> - On 19/05/2019, Анатолий Никифоров &lt;flisochcs@gmail.com&gt; wrote: &gt; asdaf &gt; фыв</font></span></a>
                                </td>
                                <td width=1% nowrap><b>22:14</b>
                            <tr bgcolor="#E8EEF7">
                                <td><input type=checkbox name=t value="16ad12d8d29aae2e"></td>
                                <td>me</td>
                                <td><a href="?&amp;th=16ad12d8d29aae2e&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> superTestqwerty<font
                                                    color=#7777CC> - superTestqwerty</font></span></a></td>
                                <td nowrap>20:39
                            <tr bgcolor="#ffffff">
                                <td><input type=checkbox name=t value="16acc59f44b2ee38"></td>
                                <td><b>Heroku Notifications</b></td>
                                <td><a href="?&amp;th=16acc59f44b2ee38&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> <b>Automatic deployment of challenger-spring-app failed.</b><font
                                                    color=#7777CC> - An automatic deployment for challenger-spring-app failed. To inspect the failure you can view the build log. You can check that the user who connected the app or pipeline to GitHub still has access to</font></span></a>
                                </td>
                                <td nowrap><b>18&nbsp;May</b>
                            <tr bgcolor="#ffffff">
                                <td><input type=checkbox name=t value="16acc586be642bf9"></td>
                                <td><b>Azat Mukhametzyanov</b> (11)</td>
                                <td><a href="?&amp;th=16acc586be642bf9&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> <b>[phoenigm/challenger-spring-app] Dev (#3)</b><font
                                                    color=#7777CC> - You can view, comment on, or merge this pull request online at: https://github.com/phoenigm/challenger-spring-app/pull/3 Commit Summary configure added config added updates first vuetify page remove</font></span></a>
                                </td>
                                <td nowrap><b>18&nbsp;May</b>
                            <tr bgcolor="#ffffff">
                                <td><input type=checkbox name=t value="16aca2c8d729a686"></td>
                                <td><b>Госуслуги</b></td>
                                <td><a href="?&amp;th=16aca2c8d729a686&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> <b>Обновлен статус вашего заявления</b><font
                                                    color=#7777CC> - госуслуги Перейти на портал госуслуг Здравствуйте, Ниёз Бахтиёрович! Статус вашего заявления № 521365389 - &quot;Закрыто&quot; Услуга: Замена паспорта РФ в связи с непригодностью к использованию</font></span></a>
                                </td>
                                <td nowrap><b>18&nbsp;May</b>
                            <tr bgcolor="#ffffff">
                                <td><input type=checkbox name=t value="16ac9c12c9986ea6"></td>
                                <td><b>Planeta</b></td>
                                <td><a href="?&amp;th=16ac9c12c9986ea6&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633></font></font> <b>Ready, steady, DNA!</b><font
                                                    color=#7777CC> - Planeta.ru и не только Всем привет, это Planeta.ru! Надеемся, майские праздники не пролетели со скоростью кометы, и вы успели как следует отдохнуть, ведь силы еще понадобятся — сегодня будет много</font></span></a>
                                </td>
                                <td nowrap><b>18&nbsp;May</b>
                            <tr bgcolor="#E8EEF7">
                                <td><input type=checkbox name=t value="16ac6280e4039396"></td>
                                <td>me (4)</td>
                                <td><a href="?&amp;th=16ac6280e4039396&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> (no subject)<font
                                                    color=#7777CC> - qwerty On Fri, 17 May 2019 at 17:17, Анатолий Никифоров &lt;flisochcs@gmail.com&gt; wrote: dfsdfs On Fri, 17 May 2019 at 16:34, Анатолий Никифоров &lt;flisochcs@gmail.com&gt; wrote: </font></span></a>
                                </td>
                                <td nowrap>17&nbsp;May
                            <tr bgcolor="#ffffff">
                                <td><input type=checkbox name=t value="16ac60805edda684"></td>
                                <td><b>Puzzle English</b></td>
                                <td><a href="?&amp;th=16ac60805edda684&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> <b>Лучшее на Puzzle English: что общего у Sprite и Fairy, зачем нужны отрицательные вопрос...</b><font
                                                    color=#7777CC> - Puzzle English Logo читать на сайте Summer is coming! До начала лета осталось две недели — времени на раскачку больше нет! Ни на раскачку пресса, ни ягодиц. Но вот мозг! Для мозга никогда не поздно —</font></span></a>
                                </td>
                                <td nowrap><b>17&nbsp;May</b>
                            <tr bgcolor="#E8EEF7">
                                <td><input type=checkbox name=t value="16ac6035ae82437d"></td>
                                <td>me</td>
                                <td><a href="?&amp;th=16ac6035ae82437d&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633>Inbox</font></font> (no subject)<font
                                                    color=#7777CC> - cc</font></span></a></td>
                                <td nowrap>17&nbsp;May
                            <tr bgcolor="#E8EEF7">
                                <td><input type=checkbox name=t value="16ac5fd7d13153d8"></td>
                                <td>me (2), <font color="#DD4B39">Draft</font></td>
                                <td><img src="https://ssl.gstatic.com/ui/v1/icons/mail/images/paperclip.gif"
                                         width=15 height=15 border=0 alt="Attachment" class=p><a
                                            href="?&amp;th=16ac5fd7d13153d8&amp;v=c&amp;s=a"><span class=ts><font
                                                    size=1><font color=#006633></font></font> ITIS 11-701 03sem Yuldashev<font
                                                    color=#7777CC> - Forwarded message From: Анатолий Никифоров &lt;flisochcs@gmail.com&gt; Date: Sun, 17 Mar 2019 at 19:45 Subject: ITIS 11-701 03sem Yuldashev To: &lt;ma@it.kfu.ru&gt; 1) файл &quot;</font></span></a>
                                </td>
                                <td nowrap>17&nbsp;May
                        </table>
                        <table width=100% cellpadding=2 cellspacing=0 border=0 bgcolor="#C3D9FF">
                            <tr>
                                <td><input type="submit" name="nvp_a_ib"
                                           value="Move to Inbox">&nbsp;&nbsp;<input type="submit"
                                                                                    name="nvp_a_sp"
                                                                                    value="Report Spam">&nbsp;&nbsp;<input
                                            type="submit" name="nvp_a_tr" value="Delete">&nbsp;&nbsp;<select
                                            name=bact>
                                        <option value="">More Actions...</option>
                                        <option value=arch>Archive</option>
                                        <option value=rd>Mark as read</option>
                                        <option value=ur>Mark as unread</option>
                                        <option value=st>Add star</option>
                                        <option value=xst>Remove star</option>
                                    </select>&nbsp;<input type=submit name=nvp_bbu_go value="Go">&nbsp; <a
                                            href="?&amp;s=a" class="searchPageLink">Refresh</a></td>
                                <td align=right><b>1</b>&nbsp;-&nbsp;<b>10</b> of about <b>17</b> &nbsp;<a
                                            href="?&amp;s=a&amp;st=10" class="searchPageLink"><b>Older
                                            &#8250;</b></a>
                        </table>
            </tr>
            </form>
        </table>
</#macro>

<@page/>