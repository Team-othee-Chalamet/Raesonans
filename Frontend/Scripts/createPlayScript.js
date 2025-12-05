
const play_BASE = "http://localhost:8080/api/play"

init();

function init(){}


// Create new Play
/*export function createPlay(playDto) {
    return post(BASE_URL, playDto);
}*/

function createPlay() {
    const form = document.getElementById("createPlayForm");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        const fd = new FormData(form); // Create FormData from the form

        // Build the JSON object using values from FormData
        const playData = {
            title: fd.get("titel"),
            description: fd.get("beskrivelse"),
            credits: [
                {
                    job: fd.get("job"),
                    name: fd.get("navn")
                }
            ]
        };

        try {
            const response = await fetch(play_BASE, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(playData) // Send the JSON object
            });

            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            form.reset();
        } catch (error) {
            console.error(error);
        }
    });
}


function addPerformer(){
const plusButton = document.getElementById("plusKnap");

form.addEventListener("click", async (e) => {
e.preventDefault();
    })
}
