/**
 * SDEV 372
 * Green River College
 * File: load_data.js
 *
 * Description: Loads GET data from database. Posts user submission info back onto page.
 *
 * @author: Tarsem Bhachu
 * @version 1.0
 */

window.onload = function() {
  let uri = "http://localhost:8081/api/v1/raag";
  let uri2 = 'http://localhost:8081/api/v1/instrument';
  let params = {
      method: "get"
  };

  fetch(uri, params)
      .then(function(response) {
          return response.json();
      })
      .then(function (data) {
          getRaags(data)
      });

    fetch(uri2, params)
        .then(function(response) {
            return response.json();
        })
        .then(function (data) {
            getInstruments(data)
        });
};

function getRaags(data)
{
    /*
    <select>
        <option value="none">None</option>
        <option>Select Raag</option>
        <option>Kalyan</option>
        <option>Bihag</option>
        <option>Desh</option>
    </select>
    */

    // access the list in our HTML
    let raagsList = document.getElementById("raags-list");
    let select = document.createElement("select");
    select.setAttribute('id', 'raagChoice');

    for (let i = 0; i < data.length; i++) {
        let raag = data[i];

        // create all elements
        let option = document.createElement("option");


        // add contents
        option.innerText = raag.raagName;

        // connect them
        select.appendChild(option);

        // add the section item to the list
        raagsList.appendChild(select);
    }
}

function getInstruments(data)
{
    /*
    <select>
        <option value="instrument">Select Instrument</option>
        <option value="none">None</option>
        <option>Tabla</option>
        <option>Esraj</option>
        <option>Rabab</option>
    </select>
    */

    // access the list in our HTML
    let instrumentList = document.getElementById("instruments-list");
    let select = document.createElement("select");
    select.setAttribute('id', 'instrumentChoice');

    for (let i = 0; i < data.length; i++) {
        let instrument = data[i];

        // create all elements
        let option = document.createElement("option");


        // add contents
        option.innerText = instrument.instrumentName;

        // connect them
        select.appendChild(option);

        // add the section item to the list
        instrumentList.appendChild(select);
    }
}



const form1 = document.getElementById('register');
form1.addEventListener('submit', addStudent);

function addStudent(event) {
    event.preventDefault();

    let fName = document.getElementById('fname').value;
    let lName = document.getElementById('lname').value;

    let srChoice = document.getElementById('raagChoice');
    let srcValue = srChoice.options[srChoice.selectedIndex].text;
    let siChoice = document.getElementById('instrumentChoice');
    let sicValue = siChoice.options[siChoice.selectedIndex].text;


    fetch("http://localhost:8081/api/v1/student", {
        method: 'post',
        body: JSON.stringify({
            studentFName: fName,
            studentLName: lName,
            studentRaag: srcValue,
            studentInstrument: sicValue
        }),
        header: {
            "Content-Type": "text/plain;charset=UTF-8"
        }
    })
        .then(function (response) {
            return response.json()
        })
        .then(function (data) {
            console.log(data)
            let results = document.getElementById('class-details');

            results.innerHTML = '<h4>Class Details</h4><hr>' + "Thank you " + fName + " " + lName + ", " +
            "your lesson will be Raag " + srcValue + ". Your instrument of choice is " +
            sicValue + ".";

        })
}



