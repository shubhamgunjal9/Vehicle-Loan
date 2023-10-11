 import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { zip } from 'rxjs';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-loan-enquiry',
  templateUrl: './loan-enquiry.component.html',
  styleUrls: ['./loan-enquiry.component.scss']
})
export class LoanEnquiryComponent 
{
  ConfirmButton:false;

  constructor(private fb:FormBuilder,private cs:CommonServiceService){}

 public enquiryForm:FormGroup
  
  ngOnInit(){
    this.enquiryForm=this.fb.group({
      enquiryId:[''],
      customerFirstName:['',[Validators.required]],
      customerMiddleName:['',[Validators.required]],
      customerLastName:['',[Validators.required]],
      customerDateOfBirth:['',[Validators.required]],
      customerMobileNumber:['',[Validators.required, Validators.minLength(10)],Validators.maxLength(10)],
      pancardNumber:['',[Validators.required,]],
      monthlyIncome:[0,[Validators.required,]],
      customerEmail:['',[Validators.required, Validators.email]],
      //below data is null
      enquiryStatus:[''],
      cibilData:[]
       }) 
    }

  customerEnquiry(){
    
      this.cs.enquiryDetails=this.enquiryForm.value
    this.cs.customerEnquiry(this.cs.enquiryDetails).subscribe(); 
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Enquiry Form Has Been Saved Successfully',
      showConfirmButton: true,
      timer: 1500
    })
      
    }

    get customerFirstName(){
      return this.enquiryForm.get('customerFirstName');
    }
    get customerMiddleName(){
      return this.enquiryForm.get('customerMiddleName');
    }
    get customerLastName(){
      return this.enquiryForm.get('customerLastName');
    }
    get customerDateOfBirth(){
      return this.enquiryForm.get('customerDateOfBirth');
    }
    get customerEmail(){
      return this.enquiryForm.get('customerEmail');
    }
    get customerMobileNumber(){
      return this.enquiryForm.get('customerMobileNumber');
    }
    get pancardNumber(){
      return this.enquiryForm.get('pancardNumber')
    }
    get monthlyIncome(){
      return this.enquiryForm.get('monthlyIncome')
    }
}
