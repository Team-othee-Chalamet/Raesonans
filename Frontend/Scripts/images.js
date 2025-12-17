import {get,post,put,del} from "../Scripts/fetchUtil.js";
document.addEventListener("DOMContentLoaded", (event) => initApp(event));

const listOfTitles = [
        "nemt",
        "støj"
]

async function initApp(event){
    const images = await getImages();

    getInfo();

    console.log(images);

    images.forEach((element) => renderImageCard(element))

}

async function getInfo(){
    //Get list of titles from the backend and set the list.
    const plays = await get("http://127.0.0.1:8080/api/plays");
    console.log(plays);
}

function renderImageCard(imageDto){
    console.log("Rendering image card:" + imageDto.url);
    
    const images = document.getElementById("images");

    const template = document.getElementById("cardTemplate");
    const clone = template.content.cloneNode(true);

    const card = addInfoToCard(imageDto, clone);

    

    images.appendChild(card);

}

function addInfoToCard(imageDto, card){
    card.getElementById("imgPreview").src = "http://127.0.0.1:8080/api" + imageDto.url;
    card.getElementById("imgTitle").innerHTML = imageDto.url;

    return card;
}

function addTheaterTitlesToDropDown(div){
    //Fetch the titles from the list

    listOfTitles.forEach(element => {
        const newOption = document.createElement("option");
        Option.name = element;
        Option.id = element;

        div.appendChild(newOption);
    });

}

async function getImages(){
        return await get("http://127.0.0.1:8080/api/images")
    }