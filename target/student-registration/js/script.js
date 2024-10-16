

 function validateForm() {
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    if (name == "") {
        alert("Name must be filled out");
        return false;
    }
    if (email == "") {
        alert("Email must be filled out");
        return false;
    }
    return true;
}