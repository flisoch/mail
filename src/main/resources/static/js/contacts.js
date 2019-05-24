const contactsByName = (searchBarId, username) => {

    // let search = $(`#${searchBarId}`);
    let search = $(`#${searchBarId}`);
    let typedNames = search[0].value.split(", ");
    let lastName = typedNames[typedNames.length - 1];
    if (lastName.length < 2) {
        return;
    }
    console.log(lastName);
    let data = {
        username: lastName
    };
    fetch("/contacts/like", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
    }).then(response => response.json())
        .then(data => {
                let list = $(`#contacts-${searchBarId}`);
                list[0].hidden = false;
                list.empty();
                for (let contact of data) {
                    createContact(list, contact);
                }
                list.on('click', 'div', {names: typedNames, searchBarId: searchBarId}, myDoStuffFunc);
            }
        );
};

const myDoStuffFunc = (event) => {
    let targetName = $(event.target).text();
    let searchBarId = event.data.searchBarId;
    names = event.data.names;
    names[names.length - 1] = targetName;
    let stringNames = "";
    for (let name of names) {
        stringNames += name + ", ";
    }

    stringNames = stringNames.slice(0, stringNames.length -2);
    console.log(stringNames);
    $(`#${searchBarId}`).empty();
    $(`#${searchBarId}`)[0].value = stringNames;
    $(`#${searchBarId}`).focus();
    $(`#contacts-${searchBarId}`).empty();


};

const createContact = (list, contact) => {
    list.append(
        `<div ${contact.id} class="item">
            <h6>${contact.username}</h6>
        </div>
        `
    );
};