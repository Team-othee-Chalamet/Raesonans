import {fetchPlayById} from "../API/playApi.js";


async function loadPlay() {
const playId = 28;

const play = await fetchPlayById(playId);
console.log(play);

const titleEl = document.getElementById("playTitleMiddle");
        if (titleEl) titleEl.textContent = play.title;

const descEl = document.getElementById("playDescription");
        if (descEl) descEl.textContent = play.description || "Ingen beskrivelse";;   


}

loadPlay();











