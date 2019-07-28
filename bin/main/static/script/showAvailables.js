function showAvailablePromises(){
    /*
    서버로부터 참여 가능한 약속 정보 가져오기

    */

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
    var fundDataText = document.createTextNode("10 coins");
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
    participateButton.setAttribute("value", "참가하기");
    participateButton.setAttribute("id", "participateButton");
    participateButton.setAttribute("onclick", "participatePromise();return false");
    participateButton.setAttribute("background-color", "#1D82FF");
    participateButton.setAttribute("font-color", "white");


    var fieldsetElement = document.querySelector("#notYetFieldset");
    fieldsetElement.appendChild(tableRoot);
    fieldsetElement.appendChild(participateButton);

}



function showParticipatedPromises(){
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

    var fieldsetElement = document.querySelector("#participatedFieldset");
    fieldsetElement.appendChild(tableRoot);
    fieldsetElement.appendChild(participateButton);
}

function participatePromise(){
    if(confirm("회비를 내고 모임에 참여 하시겠습니까?")){
        alert("모임에 참여하셨습니다.")
        location.href="mypromises.html";
    }
}