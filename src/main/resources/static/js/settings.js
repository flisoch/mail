const addFolder = () => {
    let folderName = $("#new-folder").value;
    let data = {
        folderName: folderFrom,
    };
    console.log(JSON.stringify(data));

    fetch("/folders", {
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
