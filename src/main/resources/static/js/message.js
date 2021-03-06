const handleMail = (folderFrom) => {
    let boxes = $("input[type='checkbox']");
    let copyTo = $("#copy-select")[0].value;
    let moveTo = $("#move-select")[0].value;
    let moreAction = $("#more-select")[0].value;
    let actions = [];

    if (moveTo !== "") {
        actions.push("MOVETOFOLDER");
    }
    if (copyTo !== "") {
        actions.push("COPYTOFOLDER");
    }
    if (moreAction !== "") {
        actions.push(moreAction);
    }

    let messagesId = [];
    for (let i = 0; i < boxes.length - 1; i++) {
        if (boxes[i].checked) {
            messagesId.push(boxes[i].value)
        }
    }
    let data = {
        folderFrom: folderFrom,
        messagesId: messagesId,
        actions: actions,
        moveTo: moveTo,
        copyTo: copyTo,
    };
    console.log(JSON.stringify(data));

    fetch("/mail", {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    })
        .then(response => {
                cleanUp(actions, boxes, messagesId);
            }
        );
};

const cleanUp = (actions, boxes, messagesId) => {
    for (let j = 0; j < boxes.length - 1; j++) {
        if (boxes[j].checked) {
            boxes[j].checked = false;
        }
    }

    $("#copy-select")[0].value = "";
    $("#move-select")[0].value = "";
    $("#more-select")[0].value = "";


    for (let i = 0; i < messagesId.length; i++) {
        let message = $(`#message_${messagesId[i]}`);
        if (actions.includes('MOVETOFOLDER') || actions.includes('DELETE')) {
            message.empty();
        } else {
            if (actions.includes('MARKREAD')) {
                message[0].bgColor = '#ffffff';
            } else if (actions.includes('MARKUNREAD')) {
                message[0].bgColor = '#E8EEF7';
            }
        }

    }
    if ($('#table-filler').length != 0) {
        $('#table-filler').empty();
        fillSpace();
    }
};

const deleteMessages = (folderFrom) => {
    let boxes = $("input[type='checkbox']");
    let messagesId = [];
    let actions = [];
    for (var i = 0; i < boxes.length - 1; i++) {
        if (boxes[i].checked) {
            messagesId.push(boxes[i].value)
        }
    }

    if (folderFrom === 'BIN') {
        let data = {
            messagesId: messagesId,
        };
        actions.push('DELETE');

        fetch("/mail", {
            method: 'DELETE',
            body: JSON.stringify(data),
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
            .then(response => {
                    cleanUp(actions, boxes, messagesId);
                }
            );
    } else {
        actions.push("MOVETOFOLDER");
        let data = {
            folderFrom: folderFrom,
            messagesId: messagesId,
            actions: actions,
            moveTo: "BIN"
        };

        fetch("/mail", {
            method: 'PUT',
            body: JSON.stringify(data),
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
            .then(response => {
                    cleanUp(actions, boxes, messagesId);
                }
            );
    }
};

const fillSpace = () => {
    let messages = $('#messages');
    if (messages.height() < 125) {
        let height = 125 - messages.height();
        messages.append(`
                                            <tr id="table-filler">
                                                <td colspan=4
                                                 bgcolor=#ffffff>&nbsp;`);
        $('#messages :last').height(height);
    }
};

const handleMailBottom = (folderFrom) => {
    let boxes = $("input[type='checkbox']");
    let copyTo = $("#copy-select-bottom")[0].value;
    let moveTo = $("#move-select-bottom")[0].value;
    let moreAction = $("#more-select-bottom")[0].value;
    let actions = [];

    if (moveTo !== "") {
        actions.push("MOVETOFOLDER");
    }
    if (copyTo !== "") {
        actions.push("COPYTOFOLDER");
    }
    if (moreAction !== "") {
        actions.push(moreAction);
    }

    let messagesId = [];
    for (var i = 0; i < boxes.length - 1; i++) {
        if (boxes[i].checked) {
            messagesId.push(boxes[i].value)
        }
    }
    data = {
        folderFrom: folderFrom,
        messagesId: messagesId,
        actions: actions,
        moveTo: moveTo,
        copyTo: copyTo,
    };
    console.log(JSON.stringify(data));

    fetch("/mail", {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    })
        .then(response => {

            cleanUp(actions, boxes, messagesId);
            }
        );
};
