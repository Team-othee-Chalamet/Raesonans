console.log("login.js loaded");

//Wait for DOM to load
document.addEventListener("DOMContentLoaded", initApp);

document.addEventListener("mousemove", changeBackgroundColor);

//Import post function
import { post } from "../Scripts/fetchUtil.js"

function initApp(){
    initLogin();
    changeBackgroundColor();
}

//Add eventlistener to the form
function initLogin() {
    //Remove token if one exists (navigating to login page always logs you out)
    localStorage.removeItem("token");
    const form = document.getElementById("loginForm")
    form.addEventListener("submit", handleLogin);
}

//Handle login
async function handleLogin(event) {
    event.preventDefault();
    const body = {
        username: event.target.username.value,
        password: event.target.password.value
    };
    
    try {
        const response = await post("http://localhost:8080/api/auth/login", body);
        console.log("Login successful:", response);
        //Store the token in localStorage
        localStorage.setItem("token", response.token);
        //Redirect to landing page
        window.location.href = "createPlay.html";
    } catch (error) {
        event.target.password.value = "";
        console.error("Login failed:", error.message);
        alert("Forkert adgangskode eller brugernavn.");
    }
}

function changeBackgroundColor(event) {
        const background = document.getElementsByClassName("main")[0];
        const mouseY = event.clientY;
        const mouseX = event.clientX;

        const width = window.innerWidth;
        const height = window.innerHeight

        console.log("x:" + mouseX);
        console.log("y:" + mouseY);

        const red = Math.abs(Math.sin(mouseX)) * 150;
        const green = Math.abs(Math.cos(mouseX)) * 150;
        const blue = Math.abs(Math.cos(mouseY)) * 100;

        background.style.backgroundColor = `rgb(${red}, 100, 100)`;
    }