var input1 = document.getElementById('input1');
var input2 = document.getElementById('input2');
var submit = document.getElementById('submit');
input1.oninput = checkmail();
function checkmail(){
    var mail = document.getElementById('input1').value;
    var mail_pattern = /\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}/i;
    var check1 = mail_pattern.test(mail);
    if (check1 === true) {
        document.getElementById('inc1').innerHTML = "";
        submit.disabled=false;
    } else {
        document.getElementById('inc1').innerHTML = "incorrect";
        submit.disabled=true;
    }
}
input2.oninput = checkpass();
function checkpass(){
    var pass = document.getElementById('input2').value;
    var pass_pattern = /\w{4,}/i;
    var check2 = pass_pattern.test(pass);
    if (check2 === true) {
        document.getElementById('inc2').innerHTML = "";
        submit.disabled=false;
    } else {
        document.getElementById('inc2').innerHTML = "incorrect";
        submit.disabled=true;
    }
}