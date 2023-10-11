import { Component } from '@angular/core';
import { Customer } from 'src/app/Model/customer';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';

@Component({
  selector: 'app-showlist',
  templateUrl: './showlist.component.html',
  styleUrls: ['./showlist.component.scss']
})
export class ShowlistComponent {

  customerdatalist:Customer;

constructor(private cs:CommonServiceService){

}


  customerAcceptedList(){
    this.cs.getCustomer("Customer_Accepted").subscribe((application:any)=>{
      this.customerdatalist=application.responceData
    });
  }     

      loanDisburseList(){
        this.cs.getCustomer("Loan_Disbursed").subscribe((application:any)=>{
          this.customerdatalist=application.responceData
        });
      }
      ledgerGenratedList(){
        this.cs.getCustomer("Ledger_Generated").subscribe((application:any)=>{
          this.customerdatalist=application.responceData
        });
      }
// no enum upto now
      defaulterList(){
        this.cs.getCustomer("Defualter").subscribe((application:any)=>{
          this.customerdatalist=application.responceData
        });
      }

      loandisbursement(customerId:string){
            this.cs.loandisbursement(customerId).subscribe((response:any)=>{
        });
      }

      ledgerGenration(customerId:string){
        this.cs.ledgerGenration(customerId).subscribe((response:any)=>{
    });
    }


}
