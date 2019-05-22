const handleMail = (folderFrom, action) => {
    let boxes = $("input[type='checkbox']");
    let selects = $("select");
    if (action == null) {
        action = selects[0].value;
        if (action === "") {
            action = selects[1].value;
        }
    }

    let messagesId = [];
    for (var i = 0; i < boxes.length - 1; i++) {
        if (boxes[i].checked) {
            messagesId.push(boxes[i].value)
        }
    }
    data = {
        messagesId: messagesId,
        action: action,
        folderFrom: folderFrom
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

                let boxes = $("input[type='checkbox']");
                for (let j = 0; j < boxes.length - 1; j++) {
                    if (boxes[j].checked) {
                        boxes[j].checked = false;
                    }
                }

                for (let i = 0; i < messagesId.length; i++) {
                    let message = $(`#message_${messagesId[i]}`);
                    if (action === 'MOVETOFOLDER' || action === 'ARCHIVE' || action === 'DELETE') {
                        message.empty();
                    } else if (action === 'MARKREAD') {
                        message[0].bgColor = '#ffffff';
                    } else if (action === 'MARKUNREAD') {
                        message[0].bgColor = '#E8EEF7';
                    }
                }
                if($('#table-filler').length != 0) {
                    $('#table-filler').empty();
                    fillSpace();
                }

            }
        );
};

const fillSpace = () => {
    let messages = $('#messages');
    if(messages.height() < 125) {
        let height = 125 - messages.height();
        messages.append(`
                                            <tr id="table-filler">
                                                <td colspan=4
                                                 bgcolor=#ffffff>&nbsp;`);
        $('#messages :last').height(height);
    }
}