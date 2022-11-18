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

/**
 * Onload function retrieves data from API endpoint using fetch() function. Data is loaded on page-load.
 */
window.onload = function() {
  let uri = "http://localhost:8081/api/v1/raag";
  let uri2 = 'http://localhost:8081/api/v1/instrument';
  let params = {
      method: "get"
  };

    const options = {
        method: 'GET',
        headers: {
            'X-RapidAPI-Key': 'e1b717b857msha21732689797170p13d8fdjsn503ecce181cd',
            'X-RapidAPI-Host': 'weatherapi-com.p.rapidapi.com'
        }
    };

    fetch('https://weatherapi-com.p.rapidapi.com/current.json?q=Seattle%2C%20WA', options)
        .then(response => response.json())
        .then((data) => {
            displayWeather(data)
        })

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


/**
 * Function retrieves all Raags currently in database and displays them in drop-down menu for selection.
 *
 * @param data Accepts json data of the Raag object from the API endpoint.
 */
function getRaags(data)
{
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

/**
 * Function retrieves all instrument objects currently in database and displays them in
 * drop-down menu for selection.
 *
 * @param data Accepts json data of the instrument object from the API endpoint.
 */
function getInstruments(data)
{
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

// Storing the form
const form1 = document.getElementById('register');
form1.addEventListener('submit', addStudent);

// Variables for popup animation
let popup = document.getElementById("popup");
let body = document.getElementById("backgroundImg");

/**
 * Adds a new student to the database once form is filled. Form is posted to database.
 *
 * @param event Accepts the form data to be posted to the database.
 */
function addStudent(event) {
    event.preventDefault();

    // Success screen popup shows up when Submit button is clicked
    popup.classList.add("open-popup");
    body.classList.add("s1-popup");

    // Collect inputs and store into variables
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
        headers: {
            "Content-Type": "application/json"
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


/**
 * Helps close the pop-up after user's form submission is successful.
 */
function closePopUp() {
    popup.classList.remove("open-popup");
    body.classList.remove("s1-popup");
}

/**
 * Integrates json from the public api weather data and displays real-time weather
 * information to the front-end. Info includes city/state, current date, current temperature,
 * and a small icon indicating the current weather.
 *
 * @param data Accepts json data of the weather data object from the public API endpoint.
 */
function displayWeather(data)
{
    // store all divs
    let location = document.getElementById("divLocation");
    let date = document.getElementById("divDate");
    let icon = document.getElementById("divIcon");
    let temp = document.getElementById("divTemp");

    // Create all tags
    let locationRegion = document.createElement("h6");
    let locationDate = document.createElement("h7");
    let img = document.createElement("img");
    let pTemp = document.createElement("p");

    // Add contents
    locationRegion.innerText = data.location.name + ", " +  data.location.region;

        // Creating a String for Date
        let currentDate = new Date(data.location.localtime);
        let currentDay = currentDate.getDate();
        let currentMonth = currentDate.toLocaleString('default', {
            month: 'short'
        });
        let currentYear = currentDate.getFullYear();
        locationDate.innerText = (currentMonth) + " " + currentDay + ", " + currentYear;

    img.setAttribute('src', data.current.condition.icon);
    pTemp.innerText = data.current.temp_f + '°F';

    // connect them
    location.appendChild(locationRegion);
    date.appendChild(locationDate);
    icon.appendChild(img);
    temp.appendChild(pTemp);

}



