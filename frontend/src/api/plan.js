import { apiInstance } from "./index.js";

const api = apiInstance();



async function sendImg(mainImage, success, fail) {
    const formData = new FormData();
    if (mainImage.files.length > 0) {
        for (let i = 0; i < mainImage.files.length; i++) {
            const file = mainImage.files[i];
            formData.append("mainImage", file);
        }
    }
    
    const config = {
        headers: {
        'Content-Type': 'multipart/form-data',
        'access-token': sessionStorage.getItem('access-token'),
        },
    };

    await api.post(`/plan/write/image`, formData, config).then(success).catch(fail);
}

async function addPlan(plan, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.post(`/plan/write/article`, plan).then(success).catch(fail);
}

export { sendImg , addPlan}