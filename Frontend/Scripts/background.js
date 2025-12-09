document.addEventListener("DOMContentLoaded", initApp);

function initApp(){
    console.log("App started");

    setupEventListeners();

    function setupEventListeners(){
    }


    var col1 = {
        "h": 0,
        "s": 80,
        "l": 60
    }
    var col2 = {
        "h": 180,
        "s": 100,
        "l": 10
    }

    const changeSpeed = .1;
    changeBGcolor();
    

    function changeBGcolor(){
        col1.h += changeSpeed;
        col2.h += changeSpeed;

        if (col1.h>360){
            col1.h=0;
        }

        if (col2.h>360){
            col2.h=0;
        }

        const bgBackDiv = document.getElementById("bgBack");
        bgBackDiv.style.background = `hsl(${col1.h}, ${col1.s}%,${col1.l}%)`;
        const bgFrontDiv = document.getElementById("bgFront");
        bgFrontDiv.style.background = `hsl(${col2.h}, ${col2.s}%,${col2.l}%)`;
        requestAnimationFrame(changeBGcolor);
    }
}