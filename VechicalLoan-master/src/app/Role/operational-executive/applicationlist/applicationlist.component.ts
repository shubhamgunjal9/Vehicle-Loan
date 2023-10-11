import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/Model/customer';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';

@Component({
  selector: 'app-applicationlist',
  templateUrl: './applicationlist.component.html',
  styleUrls: ['./applicationlist.component.scss']
})
export class ApplicationlistComponent {

 public trigger:boolean=false


constructor(private cs:CommonServiceService,private router:Router){}

customerdatalist:Customer[]



getAppliedList(){
  this.trigger=true
  this.cs.getCustomer("Applied").subscribe((application:any)=>{
this.customerdatalist=application.responceData
  })
}

verifiedApplication(){
  this.cs.getCustomer("Verified").subscribe((application:any)=>{
    this.customerdatalist=application.responceData
  });
}

rejectedApplication(){
  this.cs.getCustomer("Rejected").subscribe((application:any)=>{
    this.customerdatalist=application.responceData
  });
}


showApplicationData(customerApplication:any){
alert("showApplicationData method")
    // now we convert the the object in the String before transfer by routing    
     
    //now we pass this object through the router url 
    //way 1 not working :
    // let customerdata = JSON.stringify(customerApplication);
    // this.router.navigate(['./dashboardlayout/OExecutive/showApplication/'+customerdata])

   // way 2 working ok  :
    this.router.navigate(['./dashboardlayout/OExecutive/showApplication'],{
      queryParams:{data:JSON.stringify(customerApplication)}
      })
}
  

}

