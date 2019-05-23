const newFilter = () => {
    let copyTo;
    let moveTo;
    let markAs;

    let move = $("#ch_mv")[0].checked;
    let copy = $("#ch_cp")[0].checked;
    let mark = $("#ch_mrk")[0].checked;

    let from = $("#ch_fr")[0].value;
    let to = $("#ch_to")[0].value;
    let subject = $("#ch_sub")[0].value;
    let containingWords = $("#ch_wrd")[0].value;

    if (move) {
        moveTo = $("#move-select")[0].value;
    }
    if (copy) {
        copyTo = $("#copy-select")[0].value;
    }
    if (mark) {
        markAs = $("#mark-select")[0].value;
    }

    let data = {
        moveTo: moveTo,
        markAs: markAs,
        copyTo: copyTo,
        from: from,
        to: to,
        containingWords: containingWords,
        subject: subject
    };
    console.log(JSON.stringify(data));

    fetch("/settings/filters", {
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