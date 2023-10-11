import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/Model/customer';
import { SanctionLetter } from 'src/app/Model/sanction-letter';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';

@Component({
  selector: 'app-show-verified-list',
  templateUrl: './show-verified-list.component.html',
  styleUrls: ['./show-verified-list.component.scss']
})
export class ShowVerifiedListComponent {

  showme=false;

  constructor(private router:Router,private cs :CommonServiceService){     
  }


  public customerdatalist:Customer[]

// all below method for list getting 
  verifiedApplication(){
    this.cs.getCustomer("Verified").subscribe((application:any)=>{
      this.customerdatalist=application.responceData
    });
    this.showme=true;
  }

  customerAccepted(){
    this.cs.getCustomer("Customer_Accepted").subscribe((application:any)=>{
      this.customerdatalist=application.responceData
    });
    this.showme=false;
  }
  customerRejected(){
    this.cs.getCustomer("Customer_Rejected").subscribe((application:any)=>{
      this.customerdatalist=application.responceData
    });
    this.showme=false;
  }

  sanctionGenerated(){
    this.cs.getCustomer("Sanction_Genetrated").subscribe((application:any)=>{
      this.customerdatalist=application.responceData
    });
    this.showme=false;
  }

  LoanDisbursed(){
    this.cs.getCustomer("Loan_Disbursed").subscribe((application:any)=>{
      this.customerdatalist=application.responceData
    });
    this.showme=false;
  }

    // santion generation reouting done here 
    generateSanction(customerdata:Customer){
      this.router.navigate(['./dashboardlayout/Cmanager/sanctionGeneration'],{
       queryParams:{ data:JSON.stringify(customerdata)}
      });
      this.showme=false;
    }

  }
