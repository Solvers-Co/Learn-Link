import axios from "axios";


const api = axios.create({
  baseURL: '/learnlink', // Configura para usar o proxy
});

// const api = axios.create({
//     baseURL: "http://localhost:8080" 
// });

api.interceptors.request.use(
    (config) => {
      const token = sessionStorage.getItem('token');
  
      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
      }
      
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
);
export default api;