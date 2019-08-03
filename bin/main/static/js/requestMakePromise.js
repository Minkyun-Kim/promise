function requestMakePromise(){
    var date = document.getElementById("promise-date").value;
    var location = document.getElementById("promise-location").value;
    var fund = document.getElementById("promise-fund").value;
    var participants = document.getElementById("promise-participants").value;
    participants = participants.split(' ');
    
    var body = {
        "date":date,
        "location":location,
        "fund":fund,
        "participants":participants
    };
    var request = new XMLHttpRequest();
    request.open('POST', 'http://localhost:8080/createPromise',false);
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    request.onload=function(){}
    request.send(JSON.stringify(body));
    
    location.href="../myPromises.html";
    
}