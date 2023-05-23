import axios from "axios";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: "http://172.20.10.2:9018",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

// house deal API axios instance
// function houseInstance() {
//   const instance = axios.create({
//     baseURL: process.env.VUE_APP_HOUSE_DEAL_URL,
//     headers: {
//       "Content-Type": "application/json;charset=utf-8",
//     },
//   });
//   return instance;
// }

// export { apiInstance, houseInstance };
export { apiInstance };
