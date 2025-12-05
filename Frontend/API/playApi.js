import { get, post, put, del } from "../Scripts/fetchUtil.js";

const BASE_URL = "http://localhost:8080/api";
const PLAY_URL = `${BASE_URL}/plays`;

// Fetch all full PlayDto objects
export function fetchAllPlays() {
    return get(PLAY_URL);
}

// Fetch preview list (PlayPreviewDto)
export function fetchPlayPreviews() {
    return get(`${PLAY_URL}/preview`);
}

// Fetch single play by ID (PlayDto)
export function fetchPlayById(id) {
    return get(`${PLAY_URL}/${id}`);
}

// Create new Play
export function createPlay(playDto) {
    return post(PLAY_URL, playDto);
}

// Update play
export function updatePlay(id, playDto) {
    return put(`${PLAY_URL}/${id}`, playDto);
}

// Delete play
export function deletePlay(id) {
    return del(`${PLAY_URL}/${id}`);
}

