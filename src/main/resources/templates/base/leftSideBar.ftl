<#macro left_sidebar>
    <td width=120 valign=top>
        <table width=100% cellpadding=2 cellspacing=0 border=0 class=m>
            <tr>
                <td><a href="/mail/new">Compose Mail</a>
                    <#list folders as folder>
            <tr>
                <#if currentFolder?? && currentFolder == folder.name>

                <td bgcolor="#C3D9FF"><a href="/mail/${folder.name}">${folder.name}&nbsp;</a>

                    <#else>
                    <td><a href="/mail/${folder.name}">${folder.name}&nbsp;</a>

                    </#if>
                    </#list>
        </table>
    </td>

</#macro>