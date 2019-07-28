function requestMakePromise(){
    var date = document.getElementById("promise-date");
    var location = document.getElementById("promise-location");
    var fund = document.getElementById("promise-fund");
    var participants = document.getElementById("promise-participants");

    prompt(date.value);
    prompt(location.value);
    prompt(fund.value);
    prompt(participants.value);

}