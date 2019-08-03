function showBlockchain(){

    var request = new XMLHttpRequest();
    request.open('GET', 'http://localhost:8080/showBlocks', false);
    request.onload = function(){
        availablePromiseArray = JSON.parse(request.responseText);
    }
    request.send();

    var fieldsetElement = document.querySelector("#notYetFieldset");

    for(var i = availablePromiseArray.length-1; i >= 0; i--){
        var availablePromise = availablePromiseArray[i];

        var tableRoot = document.createElement("table");
        tableRoot.setAttribute("align", "center");
        tableRoot.setAttribute("border", "1px");

        var blockNumberRow = document.createElement("tr");
        var blockNumberRowHeader = document.createElement("th");
        var blockNumberRowHeaderText = document.createTextNode("Block #" + i);
        blockNumberRow.appendChild(blockNumberRowHeader);
        blockNumberRowHeader.appendChild(blockNumberRowHeaderText);
        blockNumberRowHeader.colSpan = 2;
        tableRoot.appendChild(blockNumberRow);

        var blockHashRow = document.createElement("tr");
        var blockHashRowHeader = document.createElement("th");
        var blockHashRowHeaderText = document.createTextNode("Hash: " + availablePromise.curBlockHash);
        blockHashRow.appendChild(blockHashRowHeader);
        blockHashRowHeader.appendChild(blockHashRowHeaderText);
        blockHashRowHeader.colSpan = 2;
        tableRoot.appendChild(blockHashRow);

        var blockHeaderRow = document.createElement("tr");
        var blockHeaderRowHeader = document.createElement("th");
        var blockHeaderRowHeaderText = document.createTextNode("Block Header");
        blockHeaderRow.appendChild(blockHashRowHeader);
        blockHeaderRowHeader.appendChild(blockHeaderRowHeaderText);
        blockHeaderRowHeader.colSpan = 2;
        tableRoot.appendChild(blockHeaderRow);

        var hashPrevBlockRow = document.createElement("tr");
        var hashPrevBlockRowHeader = document.createElement("td");
        var hashPrevBlockRowHeaderText = document.createTextNode("hashPrevBlock");
        var hashPrevBlockRowData = document.createElement("td");
        var hashPrevBlockRowDataText = document.createTextNode(availablePromise.blockHeader.hashPrevBlock);
        hashPrevBlockRow.appendChild(hashPrevBlockRowHeader);
        hashPrevBlockRow.appendChild(hashPrevBlockRowData);
        hashPrevBlockRowHeader.appendChild(hashPrevBlockRowHeaderText);
        hashPrevBlockRowData.appendChild(hashPrevBlockRowDataText);
        tableRoot.appendChild(hashPrevBlockRow);

        var hashMerkleRootRow = document.createElement("tr");
        var hashMerkleRootRowHeader = document.createElement("td");
        var hashMerkleRootRowHeaderText = document.createTextNode("hashMerkleRoot");
        var hashMerkleRootRowHeaderData = document.createElement("td");
        var hashMerkleRootRowHeaderDataText = document.createTextNode(availablePromise.blockHeader.hashMerkleRoot);
        hashMerkleRootRow.appendChild(hashMerkleRootRowHeader);
        hashMerkleRootRow.appendChild(hashMerkleRootRowHeaderData);
        hashMerkleRootRowHeader.appendChild(hashMerkleRootRowHeaderText);
        hashMerkleRootRowHeaderData.appendChild(hashMerkleRootRowHeaderDataText);
        tableRoot.appendChild(hashMerkleRootRow);

        var timestampRow = document.createElement("tr");
        var timestampRowHeader = document.createElement("td");
        var timestampRowHeaderText = document.createTextNode("timestamp");
        var timestampRowData = document.createElement("td");
        var timestampRowDataText = document.createTextNode(availablePromise.blockHeader.timestamp);
        timestampRow.appendChild(timestampRowHeader);
        timestampRow.appendChild(timestampRowData);
        timestampRowHeader.appendChild(timestampRowHeaderText);
        timestampRowData.appendChild(timestampRowDataText);
        tableRoot.appendChild(timestampRow);

        var txHeaderRow = document.createElement("tr");
        var txHeaderRowHeader = document.createElement("td");
        var txHeaderRowHeaderText = document.createTextNode("Transactions");
        txHeaderRow.appendChild(txHeaderRowHeader);
        txHeaderRowHeader.appendChild(txHeaderRowHeaderText);
        txHeaderRowHeader.colSpan = 2;
        tableRoot.appendChild(txHeaderRow);
            
        console.log(availablePromise.transactions.length);
        for(var j = 0; j < availablePromise.transactions.length; j++){
            var txNumberRow = document.createElement("tr");
            var txNumberRowHeader = document.createElement("th");
            var txNumberRowHeaderText = document.createTextNode("Tx #" + j);
            txNumberRow.appendChild(txNumberRowHeader);
            txNumberRowHeader.appendChild(txNumberRowHeaderText);
            txNumberRowHeader.colSpan = 2;
            tableRoot.appendChild(txNumberRow);

            var txContentRow = document.createElement("tr");
            var txContentRowHeader = document.createElement("td");
            var txContentRowHeaderText = document.createTextNode("content");
            txContentRow.appendChild(txContentRowHeader);
            txContentRowHeader.appendChild(txContentRowHeaderText);
            var txContentRowData = document.createElement("td");
            var txContentRowDataText = document.createTextNode(availablePromise.transactions[j].content);
            txContentRow.appendChild(txContentRowData);
            txContentRowData.appendChild(txContentRowDataText);
            tableRoot.appendChild(txContentRow);

            var txValueRow = document.createElement("tr");
            var txValueRowHeader = document.createElement("td");
            var txValueRowHeaderText = document.createTextNode("value");
            txValueRow.appendChild(txValueRowHeader);
            txValueRowHeader.appendChild(txValueRowHeaderText);
            var txValueRowData = document.createElement("td");
            var txValueRowDataText = document.createTextNode(availablePromise.transactions[j].value);
            txValueRow.appendChild(txValueRowData);
            txValueRowData.appendChild(txValueRowDataText);
            tableRoot.appendChild(txValueRow);

            var txTimestampRow = document.createElement("tr");
            var txTimestampRowHeader = document.createElement("td");
            var txTimestampRowHeaderText = document.createTextNode("timestamp");
            txTimestampRow.appendChild(txTimestampRowHeader);
            txTimestampRowHeader.appendChild(txTimestampRowHeaderText);
            var txTimestampData = document.createElement("td");
            var txTimestampDataText = document.createTextNode(availablePromise.transactions[j].timestamp);
            txTimestampRow.appendChild(txTimestampData);
            txTimestampData.appendChild(txTimestampDataText);
            tableRoot.appendChild(txTimestampRow);
        }

        fieldsetElement.appendChild(tableRoot);
    }

}