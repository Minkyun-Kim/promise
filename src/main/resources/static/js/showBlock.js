function showBlockchain(){

    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/showBlocks', false);
    request.onload = function(){
        availablePromiseArray = JSON.parse(request.responseText);
    }
    request.send();

}