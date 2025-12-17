import { handleLogout } from "../Scripts/logout.js";

document.addEventListener("DOMContentLoaded", initApp);

function initApp(){
const logoutButton = document.getElementById("logout");
logoutButton.addEventListener("click", handleLogout);
}
