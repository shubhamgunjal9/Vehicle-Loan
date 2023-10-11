import { Component } from '@angular/core';
import { EnquiryDetails } from 'src/app/Model/EnquiryDetails';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-show-enquiries',
  templateUrl: './show-enquiries.component.html',
  styleUrls: ['./show-enquiries.component.scss']
})
export class ShowEnquiriesComponent {

  enquiry:EnquiryDetails;


constructor(private cs:CommonServiceService){}

public enquirylist:any[]
showme=false;


 pendingEnquiries(){  
  this.cs.customerEnquiries("Enquired").subscribe((response:any)=>{
    this.enquirylist=response.responceData;
  })
  this.showme=false;
 }

 validcibilEnquiries(){  
  this.cs.customerEnquiries("Cibilok").subscribe((response:any)=>{
    this.enquirylist=response.responceData;
    this.showme=true;
  })
 }

 rejectcibilEnquiries(){
  this.cs.customerEnquiries("Cibilreject").subscribe((response:any)=>{
    this.enquirylist=response.responceData;
    this.showme=true;
  })
 }

  
 cibilScoreCheck(enquiryDetails: EnquiryDetails){
this.cs.cibilScoreCheck(enquiryDetails).subscribe((response:any)=>{
  this.enquiry=response.responceData
  Swal.fire({
    position: 'center',
    icon: 'success',
    title: 'Cibil status for '+this.enquiry.enquiryId+'  is '+this.enquiry.enquiryStatus,
    showConfirmButton: true,
    timer: 5000,
  })
});

//window.location.reload();
 }

 sendMail(enquiryDetails: EnquiryDetails){
  this.cs.sendMail(enquiryDetails).subscribe((response:any)=>{
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Mail Sent To '+this.enquiry.customerFirstName+' on '+this.enquiry.customerEmail+' Regarding application',
      showConfirmButton: true,
      timer: 5000,
    })
  });
  

 
 // window.location.reload();
 }



}
