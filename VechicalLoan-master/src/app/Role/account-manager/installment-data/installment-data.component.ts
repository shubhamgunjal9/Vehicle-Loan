import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/Model/customer';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';

@Component({
  selector: 'app-installment-data',
  templateUrl: './installment-data.component.html',
  styleUrls: ['./installment-data.component.scss']
})
export class InstallmentDataComponent {

  customerdetails:Customer;

  constructor(private fb: FormBuilder, public activatedRouter: ActivatedRoute, public route: Router, private cs:CommonServiceService) { }

  ngOnInit(){
    this.activatedRouter.queryParams.subscribe((params) => {
      console.log(params)
      this.customerdetails = JSON.parse(params['data']);
    })
  }

}
