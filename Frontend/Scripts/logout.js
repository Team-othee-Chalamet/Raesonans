import { post } from "../Scripts/fetchUtil.js";

export async function handleLogout(event) {
    console.log("Button clicked")
    event.preventDefault();
    const response = await post("http://localhost:8080/api/auth/logout", null);
    window.location.href = "login.html"; 
    return response;
}
