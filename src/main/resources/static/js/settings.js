const addFolder = () => {
    let folderName = $("#new-folder").value;
    let data = {
        folderName: folderName,
    };
    console.log(JSON.stringify(data));

    fetch("/settings/folders", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    })
        .then(response => {

            }
        );
};

const renameFolder = (folderId) => {
    let folderName = $(`#change-folder-${folderId}-text`)[0].value;
    let data = {
        name: folderName,
    };
    console.log(JSON.stringify(data));

    fetch(`/settings/folders/${folderId}`, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    })
        .then(response => {
            $(`#folder-${folderId}-name`)[0].children[0].childNodes[0].data = folderName;
            $(`#custom-folder-${folderId}-name`)[0].children[0].children[0].childNodes[0].data= folderName;
            $(`#change-folder-${folderId}-text`)[0].value = '';

            }
        );
};
const removeFolder = (folderId) => {

    fetch(`/settings/folders/${folderId}`, {
        method: 'DELETE',
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    })
        .then(response => {
            $(`#folder-${folderId}`).empty();
            $(`#custom-folder-${folderId}`).empty();
            }
        );
};

const addSignature = () => {

    let signature = $("#signature-text")[0].value;
    let checked = $("#signature")[0].checked;
    let data;
    if (checked) {
        data = {
            signature: signature,
        };
    }
    else {
        data = {
            signature: null,
        };
    }
    if (confirm('save changes?')) {
        fetch("/settings", {
            method: 'POST',
            body: JSON.stringify(data),
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
            .then(response => {
                if (checked) {
                    $("#signature-text")[0].value = signature;
                }
                else {
                    $("#signature-text")[0].value = null;
                }

                }
            );
    }



};
