import axios from 'axios';

let API_URL = 'http://localhost:8080/api/vehiculos';



const getVehiclePagination = async (pageNum, size, sort) => {
   const page = {
      page: pageNum,
      size, sort
   };
   try {
      const response = await axios.get(API_URL, { page });

      return response.data;
   } catch (error) {
      throw error;
   }
};

const putVehicle = async (pageNum, size, sort) => {
    const page = {
       page: pageNum,
       size, sort
    };
    try {
       const response = await axios.put(API_URL, { page });
 
       return response.data;
    } catch (error) {
       throw error;
    }
 };

export default {
   getVehiclePagination
};