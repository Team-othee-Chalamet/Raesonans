import {fetchPlayById} from "../API/playApi.js";


// læser sendt id fra url
const urlParams = new URLSearchParams(window.location.search);
const playId = urlParams.get("id");

async function loadPlay() { //metoden kan godt loade et play, playId'et er dog hardcodet, det skal lige ændres


const play = await fetchPlayById(playId);
console.log(play);

const titleEl = document.getElementById("playTitleMiddle");
        if (titleEl) titleEl.textContent = play.title;

const descEl = document.getElementById("playDescription");
        if (descEl) descEl.textContent = play.description || "Ingen beskrivelse";;   

//      Check if reviews exist and display them
        const reviews = play.reviewDtos
    if (reviews.length > 0) {
        displayReviews(reviews);
    } else {
        const container = document.getElementById("reviewsContainer");
        container.innerHTML = "<p>Ingen anmeldelser tilgængelige.</p>";
    }
// Check for credits and display them
    const credits = play.credits || play.creditDtos || []; 
    displayCredits(credits);
}
/**
 * Displays the credits (Role: Name) in the left column.
 * @param {Array} credits - List of credit objects
 */
function displayCredits(credits) {
    const container = document.getElementById("creditsList");
    if (!container) return; // Safety check

    container.innerHTML = ''; // Clear existing content

    if (credits.length === 0) {
        container.innerHTML = '<p style="font-style:italic; color:#777;">Ingen kreditering info.</p>';
        return;
    }

    // Create a generic list
    const ul = document.createElement('ul');
    ul.classList.add('credit-list-style'); // We will add this class in CSS

    credits.forEach(credit => {
        const li = document.createElement('li');
        
        // Structure: Role: Name
        // Using spans allows us to bold the role separately
        const roleSpan = document.createElement('span');
        roleSpan.className = 'credit-role';
        roleSpan.textContent = credit.role + ': ';

        const nameSpan = document.createElement('span');
        nameSpan.className = 'credit-name';
        nameSpan.textContent = credit.name;

        li.appendChild(roleSpan);
        li.appendChild(nameSpan);
        ul.appendChild(li);
    });

    container.appendChild(ul);
}





/**
 * Creates and displays the review boxes in the right column.
 * @param {Array<Object>} reviews - Array of review objects from the backend.
 */
function displayReviews(reviews) {
    const container = document.getElementById("reviewsContainer");
    container.innerHTML = ''; // Clear any existing content

    reviews.forEach(review => {
        // 1. Create the outer box div (uses the existing CSS styling: #reviewsContainer > div)
        const reviewBox = document.createElement('div');

        // 2. Add the Title
        const title = document.createElement('h4');
        title.textContent = review.title || 'Anmeldelse';
        reviewBox.appendChild(title);
        
        // 3. Add the Star Rating
        const starRatingEl = document.createElement('div');
        starRatingEl.classList.add('star-rating'); // Add class for styling
        starRatingEl.innerHTML = generateStarRating(review.actualScore, review.maxScore);
        reviewBox.appendChild(starRatingEl);

        // 4. Add the Review Text
        const text = document.createElement('p');
        text.textContent = review.reviewText;
        reviewBox.appendChild(text);

        // 5. Add the Source Link
        if (review.sourceLink) {
            const link = document.createElement('a');
            link.href = review.sourceLink;
            link.textContent = 'Læs hele anmeldelsen';
            link.target = '_blank'; // Open link in new tab
            reviewBox.appendChild(link);
        }

        // Add the whole box to the container
        container.appendChild(reviewBox);
    });
}

/**
 * Generates HTML for the star rating, based on the specific maxScore of the review.
 * @param {number} actualScore - The score given (e.g., 5)
 * @param {number} maxScore - The maximum possible score (e.g., 6)
 * @returns {string} - HTML string of star icons.
 */
function generateStarRating(actualScore, maxScore) {
    // 1. Use the maxScore from the review as the total number of stars
    const totalStars = maxScore; 
    
    let starHtml = '';
    
    const fullStar = '★';
    const emptyStar = '☆';
    
    for (let i = 1; i <= totalStars; i++) {
        // 2. Compare directly: if current star index is less/equal to actual score, fill it.
        if (i <= actualScore) {
            starHtml += `<span class="full-star">${fullStar}</span>`;
        } else {
            starHtml += `<span class="empty-star">${emptyStar}</span>`;
        }
    }
    
    // Optional: Keep the text number as a backup
    starHtml += ` <span style="font-size: 0.8em; color: #666;">(${actualScore}/${maxScore})</span>`;
    
    return starHtml;
}











loadPlay();


