import {get} from "../Scripts/fetchUtil.js";
document.addEventListener("DOMContentLoaded", () => initApp());

var images;
const url = "http://127.0.0.1:8080/api"
var page = 0;


function initApp(){
    setupEventlisteners();


    reloadAndRenderImages();

}


function setupEventlisteners(){
    document.getElementById("prevButton").addEventListener("click", () => handlePageClick("prev"));
    document.getElementById("nextButton").addEventListener("click", () => handlePageClick("next"));
}

async function reloadAndRenderImages(){
    await refreshImages();

    document.getElementById("gallery").innerHTML = "";

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
            renderImage(images[i]);
        }
    }
}

async function refreshImages(){
    images = await get(url+"/images");
}

function renderImage(imageDto){
    if(imageDto.galleryVis){
        console.log("Rendering image:" + imageDto.url);
    
    const images = document.getElementById("gallery");

    const image = document.createElement("img");
    image.src = url + imageDto.url;

    images.appendChild(image);

    }
    
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