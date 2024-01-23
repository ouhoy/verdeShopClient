import * as process from "process";

export const SERVER_URL = process.env.VITE_PRODUCTION ? 'verdeshopserver-production-7ff5.up.railway.app' : `http://localhost:8080`;