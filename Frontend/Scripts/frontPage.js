

document.addEventListener("DOMContentLoaded", initApp);

function initApp(){

    setupEventListeners();

    //Variables:
    var scrollPosition;
    var initScrollPosition;

    function setupEventListeners(){
        const scrollButton = document.getElementById("scrollDownButton");
        scrollButton.addEventListener("click", handleScrollButtonClick);

    
    }

    function handleScrollButtonClick(){
            initScrollPosition = window.pageYOffset;
            scrollPosition = window.pageYOffset;
            scrollToPosition(900);
        }

    function scrollToPosition(goalPosition){
        window.scrollTo(0, scrollPosition);
        scrollPosition = scrollPosition+getScrollAmount(goalPosition);
        console.log(scrollPosition);
        
        if(scrollPosition < goalPosition){
            requestAnimationFrame(() => scrollToPosition(goalPosition))
        }
    }

    function getScrollAmount(goalPosition){
        
        const middle = (goalPosition-initScrollPosition)/2+initScrollPosition;

        const range = goalPosition - middle;

        var difference;

        if (scrollPosition<middle){
            difference = range - scrollPosition;
        }
        else{
            difference = scrollPosition - range;
        }

        var scrollAmount = (range - difference)/20;
        console.log(scrollAmount);

        if(scrollAmount<.1){
            scrollAmount = .5;
        }

        return scrollAmount;
    }

}