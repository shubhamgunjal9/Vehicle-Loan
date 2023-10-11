import { Cibil } from "./cibil";

export class EnquiryDetails {
    
    enquiryId: string;
    customerFirstName:string;
    customerMiddleName:string;
    customerLastName:string;
    customerDateOfBirth:string;
    customerEmail:string;
    customerMobileNumber:number;
    pancardNumber:string;
    monthlyIncome:number;
    enquiryStatus:string;
    cibildata:Cibil;                               
    
    }
