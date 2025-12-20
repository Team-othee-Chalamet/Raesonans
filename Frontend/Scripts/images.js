import {get,post,put,del} from "../Scripts/fetchUtil.js";
document.addEventListener("DOMContentLoaded", (event) => initApp(event));

var listOfTitles = [];
var images;
var page = 0;

const url = "http://127.0.0.1:8080/api/"

async function initApp(event){
    await getInfo();
    console.log(images);
    reloadAndRenderImages();
    
    addEventListeners();
    

}

function addEventListeners(){
    document.getElementById("prevButton").addEventListener("click", () => handlePageClick("prev"));
    document.getElementById("nextButton").addEventListener("click", () => handlePageClick("next"));
    /* document.getElementById("images").addEventListener("submit", (event) => handleImageClick(event)); */
    document.getElementById("images").addEventListener("click", (event) => handleImageClick(event));
    
    
}

function handleImageClick(event){
    console.log("Clicked: " + event.target);

    if(event.target.classList.contains("saveEdit")){
        handleSaveEditClick(event);
    }
    if(event.target.classList.contains("deleteBtn")){
        handleDeleteClick(event);
    }
}

async function handleDeleteClick(event){
    
    const target = event.target.parentElement;
    console.log(target);
    const id = target.querySelector("#imageID").value;
    console.log(id);

    await del("http://127.0.0.1:8080/api/images/"+id);

    await reloadAndRenderImages();


}

async function handleSaveEditClick(event){

    event.preventDefault();
    const targetForm = event.target.parentElement.parentElement;
    console.log(targetForm);

    const formData = new FormData(targetForm);

    console.log(formData);
    
    const imageDto = {
        "id": formData.get("imageID"),
        "galleryVis": formData.get("galInputBox") == "on",
        "setSplash": formData.get("splashInputBox") == "on",
        "playTitle": formData.get("playDropDown")
    }

    console.log(imageDto);

    await put("http://127.0.0.1:8080/api/images", imageDto);

    await reloadAndRenderImages();
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

async function refreshImages(){
    images = await get(url+"images");
}

async function reloadAndRenderImages(){
    await refreshImages();

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
    playDropDown.value = imageDto.playTitle;

    
    card.getElementById("imageID").value = imageDto.id;

    
        card.getElementById("galInputBox").checked = imageDto.galleryVis;
    

        card.getElementById("splashInputBox").checked = imageDto.isSplash;
    

    return card;
}

function addPlaysToDropDown(playDropDown){
    const noneOption = document.createElement("option");
    noneOption.value = null;
    noneOption.text = "ingen";

    listOfTitles.forEach((element) => {
        const option = new Option(element, element);
        playDropDown.appendChild(option);
    })
}

async function getImages(){
        return await get("http://127.0.0.1:8080/api/images")
    }