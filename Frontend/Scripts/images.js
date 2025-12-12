import {get,post,put,del} from "../Scripts/fetchUtil.js";
document.addEventListener("DOMContentLoaded", (event) => initApp(event));


async function initApp(event){
    const images = await getImages();

    console.log(images);

    const newImage = document.createElement("img");

    newImage.src = "http://127.0.0.1:8080" + images[0].url;

    document.body.appendChild(newImage);

    
}

async function getImages(){
        return await get("http://127.0.0.1:8080/api/images")
    }