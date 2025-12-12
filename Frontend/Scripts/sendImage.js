console.log("Script started")
import {get,post,put,del} from "../Scripts/fetchUtil.js";

document.addEventListener("DOMContentLoaded", () => initApp());

function initApp(){
    addEventListeners();


    function addEventListeners(){
        const form = document.getElementById("form");
        form.addEventListener("submit", (event) => handleSendClick(event));

        function handleSendClick(event){
            event.preventDefault();
            console.log("Hello")

            const file = document.getElementById("file").files[0];

            const formInfo = new FormData(event.target);

            const additionalInfo = {
                "galleryVis": formInfo.get("galleryVis") === "on",
                "playTitle": formInfo.get("playDropDown"),
                "setSplash": formInfo.get("setSplash") === "on"
            }

            console.log(additionalInfo);

            const formData = new FormData();

            formData.append("file", file);
            formData.append("meta", new Blob //En "Blob" er et objekt som JS behandler som en fil, dette er nødvendigt her fordi v isender den til Spring, og den skal kunne skelne mellem "meta" delen og "file" delen
                (
                    [JSON.stringify(additionalInfo)], //Vi skal Json stringify det additionalInfo objekt vi lavede før, så vi kan parse det til en DTO i backenden
                    {type: "application/json"}) //Vi sætter en header på denne blob, så Spring kan se det er god gammeldags json
                );
                //Vi har derfor nu en formData med to specifikke dele, en som er fil delen, og en som er Json delen (som vi kender den)


            post("http://127.0.0.1:8080/api/images", formData);
        }
    }
}