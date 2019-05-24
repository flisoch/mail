const contactsByName = (searchBarId, username) => {

    // let search = $(`#${searchBarId}`);
    let search = $(`#to`);
    let typedNames = search[0].value.split(", ");
    let lastName = typedNames[typedNames.length - 1];
    if (lastName.length < 2) {
        return;
    }
    console.log(lastName);
    let data = {
        username: lastName
    };
    console.log(data)
    fetch("/contacts/like", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    }).then(response => response.json())
        .then(data => {
                console.log(data);
                let list = $('#contacts');
                list[0].hidden = false;
                list.empty();
                for (let contact of data) {
                    createContact(list, contact);
                }
                list.on('click', 'div', {names: typedNames}, myDoStuffFunc);
            }
        );
};

const myDoStuffFunc = (event) => {
    let targetName = $(event.target).text();
    names = event.data.names;
    names[names.length - 1] = targetName;
    let stringNames = "";
    for (let name of names) {
        stringNames += name + ", ";
    }
    console.log(stringNames);
    $(`#to`).empty();
    $(`#to`)[0].value = stringNames;

    $('#contacts').empty();


};

const createContact = (list, contact) => {
    list.append(
        `<div ${contact.id} class="item">
            <h4>${contact.username}</h4>
        </div>
        `
    );
};