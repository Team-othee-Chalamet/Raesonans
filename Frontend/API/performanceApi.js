import { get, post, put, del } from "../Scripts/fetchUtil.js";

const BASE_URL = "http://localhost:8080/api";
const PERFORMANCE_URL = `${BASE_URL}/performances`;

export async function getPerformances() {
    return await get(PERFORMANCE_URL);
}