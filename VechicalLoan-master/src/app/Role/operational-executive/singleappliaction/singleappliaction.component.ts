import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/Model/customer';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-singleappliaction',
  templateUrl: './singleappliaction.component.html',
  styleUrls: ['./singleappliaction.component.scss']
})
export class SingleappliactionComponent implements OnInit {

  constructor(private fb: FormBuilder, public activatedRouter: ActivatedRoute, public route: Router, private cs:CommonServiceService) { }

  statusform: FormGroup;

  public customerdetails: any

  ngOnInit(): void {

    // parma method used to get Data from URL
    //now Data will be came in String formate we need to convert it into Object with help of JSON.parse method

    // way 1:
    // this.activatedRouter.paramMap.subscribe((param)=>{
    //   this.customerdetails= JSON.parse(param.get('data'));
    // })



    // working ok way 2: 
    this.activatedRouter.queryParams.subscribe((params) => {
      console.log(params)
      this.customerdetails = JSON.parse(params['data']);
    })
  }

  applicationVerify(customerId: string) {
      //verifiaction alert for Success
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Verify it!'
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          'Verified!',
          'Customer has been successfully Verified.',
          'success'
        )
      }
    })
    //if yes verify it is hit then status will change to verified
this.cs.withstatusUpdate(customerId,"Verified").subscribe(()=>{
});

  }
  applicationReject(customerId: string) {

    ////verifiaction alert for Reject
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Reject it!'
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          'Rejected!',
          'Customer has been successfully Rejected.',
          'success'
        )
      }
    })

    this.cs.withstatusUpdate(customerId,"Rejected").subscribe(()=>{
    })

  }


}
