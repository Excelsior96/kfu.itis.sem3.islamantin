var textarea = document.getElementsByName("about");
var counter = document.getElementById("counter");
var button = document.getElementById("button");
counter.style.color="black"
textarea.oninput = count();
function count(){
    counter.innerHTML = textarea.value.length.toString();
    if (counter>200) {
        button.disabled=true;
        counter.style.color="red";
    } else {
        button.disabled=false;
        counter.style.color="black"
    }
}