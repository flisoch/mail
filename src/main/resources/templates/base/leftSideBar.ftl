<#macro left_sidebar>
    <td width=120 valign=top>
        <table width=100% cellpadding=2 cellspacing=0 border=0 class=m>
            <tr>
                <td><a href="/mail/new">Compose Mail</a>
                    <#if folders??>
                    <#list folders as folder>
            <tr>
                <#if currentFolder?? && currentFolder == folder.name>

                <td bgcolor="#C3D9FF"><a href="/mail/${folder.name}">${folder.name}&nbsp;</a>

                    <#else>
                <td><a href="/mail/${folder.name}">${folder.name}&nbsp;</a>

                    </#if>
                    </#list>
                    </#if>

            <tr>
                <td>
                    <form action="/folders" method=POST>
                        <input id="new-folder" width="75%" size=14 maxlength=40 title="new folder"
                               name="folderName" placeholder="new folder">
                        <span><input type="submit" width="25%" value="Add"></span>

                    </form>
                </td>
            </tr>
        </table>
    </td>

</#macro>