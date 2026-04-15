import axiosInstance from "../axios/axios.js";

const earthquakeRepository = {

    fetchEarthquakes: async () => {
        return await axiosInstance.get("/fetch");
    },

    filterByMagnitude: async () => {
        return await axiosInstance.get("/filterByMagnitude");
    },

    filterByDate: async (date) => {
        return await axiosInstance.get("/filterByDate", {
            params: { date }
        });
    },

    deleteEarthquake: async (id) => {
        return await axiosInstance.delete(`/delete/${id}`);
    }
};

export default earthquakeRepository;