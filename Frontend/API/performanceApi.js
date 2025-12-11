import { get, post, put, del } from "../Scripts/fetchUtil.js";

const BASE_URL = "http://localhost:8080/api";
const PERFORMANCE_URL = `${BASE_URL}/performances`;

export async function getPerformances() {
    return await get(PERFORMANCE_URL);
}

export async function createPerformance(performance) {
    return await post(PERFORMANCE_URL, performance);
}

export async function updatePerformance(performanceId, performance) {
    return await put(`${PERFORMANCE_URL}/${performanceId}`, performance);
}

export async function deletePerformance(performanceId) {
    return await del(`${PERFORMANCE_URL}/${performanceId}`);
}