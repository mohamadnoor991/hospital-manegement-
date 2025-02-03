import { SrvRecord } from "dns";

export interface Patient{
    id: number;
    f_Name: string;
    l_Name: string;
    email: string;
    phone: string;
    doctorResponsible: string;
    imageUrl: string;
    patientCode: string;


}