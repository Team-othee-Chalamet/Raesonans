import { fetchAllPlays } from '../API/playApi.js';
import { getPerformances, createPerformance, updatePerformance, deletePerformance } from '../API/performanceApi.js';

window.addEventListener('DOMContentLoaded', initApp);

async function initApp() {
    loadPlays();
    setUpEventListeners();
}

async function setUpEventListeners() {
    const createForm = document.querySelector('#createPerformanceForm');
    createForm.addEventListener('submit', savePerformance);
}

async function savePerformance(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const performance = {
        playPreviewDto: { id: Number(formData.get("play")) },
        location: formData.get("location"),
        performanceDate: formData.get("performance-date"),
        time: formData.get("time"),
        ticketLink: formData.get("ticket-link")
    }
    await createPerformance(performance);
    form.reset();
    window.location.href = "../Pages/performances.html";

}

async function loadPlays() {
    const plays = await fetchAllPlays();

    const dropdown = document.querySelector('#play')
    dropdown.innerHTML = ''; 
    plays.forEach(play => {
        const option = document.createElement('option');
        option.value = play.id;
        option.textContent = play.title;
        dropdown.appendChild(option);
    });
}
