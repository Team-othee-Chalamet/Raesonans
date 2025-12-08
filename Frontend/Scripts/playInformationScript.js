import {fetchPlayById} from "../API/playApi.js";


// læser sendt id fra url
const urlParams = new URLSearchParams(window.location.search);
const playId = urlParams.get("id");

async function loadPlay() { //metoden kan godt loade et play, playId'et er dog hardcodet, det skal lige ændres


const play = await fetchPlayById(playId);
console.log(play);

const titleEl = document.getElementById("playTitleMiddle");
        if (titleEl) titleEl.textContent = play.title;

const descEl = document.getElementById("playDescription");
        if (descEl) descEl.textContent = play.description || "Ingen beskrivelse";;   


}

loadPlay();











