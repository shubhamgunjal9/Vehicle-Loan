import { Cibil } from "./cibil";
import { CustomerAddress } from "./customer-address";
import { CustomerFinancialData } from "./customer-financial-data";
import { Dealer } from "./dealer";
import { Documents } from "./documents";
import { GuarantorDetails } from "./guarantor-details";
import { Ledger } from "./ledger";
import { LoanDisbursement } from "./loan-disbursement";
import { SanctionLetter } from "./sanction-letter";

export class Customer {   

customerId:string;	
enquiryId:string;
customerFirstName:string;
customerMiddleName:string;
customerLastName:string;
customerDateOfBirth:string;
customerGender:string;
customerMobileNumber:number;
customerAdditionalMobileNumber:number;
customerEmail:string;

customerVerificationStatus:string;
	
// Secondary referance	
	
customerAddress:CustomerAddress;	

customerFinancialData:CustomerFinancialData;

dealerData:Dealer;

guarantorDetails:GuarantorDetails;	

customerDocuments:Documents;

// null input paased

loanDisbursement:LoanDisbursement;	
	
ledger:Ledger;		

sanctionLetter:SanctionLetter;	


customerCibilScore:Cibil;		


}
