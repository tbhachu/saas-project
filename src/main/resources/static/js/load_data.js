window.onload = function() {
  let uri = "http://localhost:8081/api/v1/raag";
  let params = {
      method: "get"
  };

  fetch(uri, params)
      .then(function(response) {
          return response.json();
      })
      .then(function (data) {
          showRaags(data)
      });
};

function showRaags(data)
{
    // access the list in our HTML
    let raagsList = document.getElementById("raags-list");

    for (let i = 0; i < data.length; i++) {
        let raag = data[i];

        // create all elements
        let section = document.createElement("section");
        let h2 = document.createElement("h2");
        let thaat = document = document.createElement("that");

        // add contents
        h2.innerText = "Raag: " + raag.raagName;
        thaat.innerText = "Thaat: " + raag.thaat;

        // connect them
        section.appendChild(h2);
        section.appendChild(thaat);


        // add the section item to the list
        raagsList.appendChild(section);
    }


}