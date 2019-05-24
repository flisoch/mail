<#macro left_sidebar>
    <td width=120 valign=top>
        <table width=100% cellpadding=2 cellspacing=0 border=0 class=m>
            <tr>
                <td><a href="/mail/new">Compose Mail</a>
                    <#if folders??>
                    <#list folders as folder>
            <tr id="folder-${folder.id}">
                <#if currentFolder?? && currentFolder == folder.name>

                <td id="folder-${folder.id}-name" bgcolor="#C3D9FF"><a href="/mail/${folder.name}">${folder.name}</a>
                    <#else>
                <td id="folder-${folder.id}-name"><a href="/mail/${folder.name}">${folder.name}</a>

                    </#if>
                    </#list>
                    </#if>
        </table>
    </td>

</#macro>