import {get,post,put,del} from "../Scripts/fetchUtil.js";
document.addEventListener("DOMContentLoaded", (event) => initApp(event));

var listOfTitles = [];
var images;
var page = 0;

async function initApp(event){
    images = await getImages();
    await getInfo();
    console.log(images);
    reloadAndRenderImages();
    
    addEventListeners();
    

}

function addEventListeners(){
    document.getElementById("prevButton").addEventListener("click", () => handlePageClick("prev"));
    document.getElementById("nextButton").addEventListener("click", () => handlePageClick("next"));
    
    
}

function handlePageClick(direction){
    if(direction == "next"){
        page++;
    }else{
        page--;
    }

    if(page < 0){
        page = 0;
    }

    if(page*12 > images.length){
        page--
    }

    reloadAndRenderImages();
}

function reloadAndRenderImages(){
    document.getElementById("images").innerHTML = "";

    console.log("Loading images")
    const lowNumber = page*12;
    var highNumber = page*12+12;

    

    if(highNumber>images.length){
        highNumber = images.length;
    }

document.getElementById("page").innerHTML = (lowNumber+1) + "-" + (highNumber) + " ud af " + (images.length);

    for(var i = lowNumber; i<highNumber; i++){
        console.log(highNumber);
        console.log(lowNumber);
        console.log("Rendering: " + images[i]);
        if(images[i]){
            renderImageCard(images[i]);
        }
    }
}

async function getInfo(){
    //Get list of titles from the backend and set the list.
    const plays = await get("http://127.0.0.1:8080/api/plays");
    console.log(plays);

    listOfTitles = [];

    plays.forEach((element) => {listOfTitles.push(element.title)});
    console.log(listOfTitles);
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
    const playDropDown = card.getElementById("playDropDown");
    addPlaysToDropDown(playDropDown);

    return card;
}

function addPlaysToDropDown(playDropDown){
    listOfTitles.forEach((element) => {
        console.log("Adding " + element + " To menu")
        const option = document.createElement("option");
        option.value = element;
        option.text = element;
        playDropDown.appendChild(option);
    })
}

async function getImages(){
        return await get("http://127.0.0.1:8080/api/images")
    }