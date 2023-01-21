function editToSaveButton(newid, parentid, oldbuttonid, funcname) {
    const newbutton = document.createElement("input");
    newbutton.setAttribute("type", "button");
    newbutton.setAttribute("id", newid);
    newbutton.setAttribute("class", "button-primary-m rounded edit-button");
    newbutton.setAttribute("value", "Salva");
    newbutton.setAttribute("onclick", funcname);
    const parent = document.getElementById(parentid);
    const oldbutton = document.getElementById(oldbuttonid);
    parent.replaceChild(newbutton, oldbutton);
}

function addDeleteButton(text, newid,parentid, nextbuttonid, funcname) {
    const newbutton = document.createElement("input");
    newbutton.setAttribute("type", "button");
    newbutton.setAttribute("id", newid);
    newbutton.setAttribute("class", "button-tertiary-m rounded edit-button");
    newbutton.setAttribute("value", text);
    newbutton.setAttribute("onclick", funcname);
    const parent = document.getElementById(parentid);
    const nextbutton = document.getElementById(nextbuttonid);
    parent.insertBefore(newbutton, nextbutton);
}