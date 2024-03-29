function showAvailablePromises(){
    /*
    서버로부터 참여 가능한 약속 정보 가져오기

    */
   var request = new XMLHttpRequest();
   request.open('GET', 'http://localhost:8080/showUnjoinPromisesOfMember?name=Kim', false);
   request.onload = function(){
       availablePromiseArray = JSON.parse(request.responseText);
   }
   request.send();

   var availablePromise = JSON.parse(availablePromiseArray[0]);

    //table 삽입

    var tableRoot = document.createElement("table");
    tableRoot.setAttribute("border", "1px;");
    tableRoot.setAttribute("border-collapse", "collapse");

    tableRoot.setAttribute("align", "center");//이건 왜 text-align이 아니라 align인가....

    var dateRow = document.createElement("tr");
    var dateHeader= document.createElement("th");
    var dateHeaderText = document.createTextNode("Date");
    dateHeader.appendChild(dateHeaderText);
    var dateData = document.createElement("th");
    var dateDataText = document.createTextNode(availablePromise.date);
    dateData.appendChild(dateDataText);
    dateRow.appendChild(dateHeader);
    dateRow.appendChild(dateData);

    var locationRow = document.createElement("tr");
    var locationHeader= document.createElement("th");
    var locationHeaderText = document.createTextNode("Location");
    locationHeader.appendChild(locationHeaderText);
    var locationData = document.createElement("th");
    var locationDataText = document.createTextNode(availablePromise.location);
    locationData.appendChild(locationDataText);
    locationRow.appendChild(locationHeader);
    locationRow.appendChild(locationData);

    var fundRow = document.createElement("tr");
    var fundHeader= document.createElement("th");
    var fundHeaderText = document.createTextNode("Fund");
    fundHeader.appendChild(fundHeaderText);
    var fundData = document.createElement("th");
    var fundDataText = document.createTextNode(availablePromise.fund + " coins");
    fundData.appendChild(fundDataText);
    fundRow.appendChild(fundHeader);
    fundRow.appendChild(fundData);

    var participantRow = document.createElement("tr");
    var participantHeader = document.createElement("th");
    var participantHeaderText = document.createTextNode("Participant");
    participantHeader.appendChild(participantHeaderText);
    var participantData = document.createElement("th");
    var participantDataText = document.createTextNode(availablePromise.participants);
    participantData.appendChild(participantDataText);
    participantRow.appendChild(participantHeader);
    participantRow.appendChild(participantData);

    tableRoot.appendChild(dateRow);
    tableRoot.appendChild(locationRow);
    tableRoot.appendChild(fundRow);
    tableRoot.appendChild(participantRow);

    var participateButton = document.createElement("input");
    participateButton.setAttribute("type", "submit");
    participateButton.setAttribute("value", "참가하기");
    participateButton.setAttribute("id", "participateButton");
    participateButton.addEventListener('click', function(){
        participatePromise("Kim", availablePromise.promiseId)
    });
    participateButton.setAttribute("background-color", "#1D82FF");
    participateButton.setAttribute("font-color", "white");


    var fieldsetElement = document.querySelector("#notYetFieldset");
    fieldsetElement.appendChild(tableRoot);
    fieldsetElement.appendChild(participateButton);

}
function participatePromise(name, promiseId){
    var realName = prompt("성함을 입력해주세요");
    if(confirm("회비를 납부하고 모임에 참여하시겠습니까?")){
        var body = {
            "name":realName,
            "promiseId": promiseId
        };
        var request = new XMLHttpRequest();
        request.open('PUT', 'http://localhost:8080/joinPromise', false);
        request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        request.onload = function(){
            var data = JSON.parse(request.responseText);
        }
        request.send(JSON.stringify(body));
        alert("모임에 참여 하셨습니다");  
        location.href= "myPromises.html";
    }
}



function showParticipatedPromises(){

    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/showJoinPromisesOfMember?name=Kim', false);
    request.onload = function(){
        availablePromiseArray = JSON.parse(request.responseText);
        console.log(JSON.parse(availablePromisesArray[0].date));
    }
    request.send();
    var availablePromise = JSON.parse(availablePromiseArray[0]);

    var tableRoot = document.createElement("table");
    tableRoot.setAttribute("border", "1px;");
    tableRoot.setAttribute("border-collapse", "collapse");

    tableRoot.setAttribute("align", "center");//이건 왜 text-align이 아니라 align인가....

    var dateRow = document.createElement("tr");
    var dateHeader= document.createElement("th");
    var dateHeaderText = document.createTextNode("Date");
    dateHeader.appendChild(dateHeaderText);
    var dateData = document.createElement("th");
    var dateDataText = document.createTextNode("2019-07-21");
    dateData.appendChild(dateDataText);
    dateRow.appendChild(dateHeader);
    dateRow.appendChild(dateData);

    var locationRow = document.createElement("tr");
    var locationHeader= document.createElement("th");
    var locationHeaderText = document.createTextNode("Location");
    locationHeader.appendChild(locationHeaderText);
    var locationData = document.createElement("th");
    var locationDataText = document.createTextNode("Seoul");
    locationData.appendChild(locationDataText);
    locationRow.appendChild(locationHeader);
    locationRow.appendChild(locationData);

    var fundRow = document.createElement("tr");
    var fundHeader= document.createElement("th");
    var fundHeaderText = document.createTextNode("Fund");
    fundHeader.appendChild(fundHeaderText);
    var fundData = document.createElement("th");
    var fundDataText = document.createTextNode("40 coins");
    fundData.appendChild(fundDataText);
    fundRow.appendChild(fundHeader);
    fundRow.appendChild(fundData);

    var participantRow = document.createElement("tr");
    var participantHeader = document.createElement("th");
    var participantHeaderText = document.createTextNode("Participant");
    participantHeader.appendChild(participantHeaderText);
    var participantData = document.createElement("th");
    var participantDataText = document.createTextNode("Kim Lee Choi Park");
    participantData.appendChild(participantDataText);
    participantRow.appendChild(participantHeader);
    participantRow.appendChild(participantData);

    tableRoot.appendChild(dateRow);
    tableRoot.appendChild(locationRow);
    tableRoot.appendChild(fundRow);
    tableRoot.appendChild(participantRow);

    var participateButton = document.createElement("input");
    participateButton.setAttribute("type", "submit");
    participateButton.setAttribute("value", "블록체인 보기");
    participateButton.setAttribute("id", "showBlockChainButton");
    participateButton.setAttribute("background-color", "#1D82FF");
    participateButton.setAttribute("font-color", "#000000");
    participateButton.setAttribute('onclick', 'goToShowBlock(); return false');

    var fieldsetElement = document.querySelector("#participatedFieldset");
    fieldsetElement.appendChild(tableRoot);
    fieldsetElement.appendChild(participateButton);
}

function goToShowBlock(){
    location.href="showBlock.html";
}
