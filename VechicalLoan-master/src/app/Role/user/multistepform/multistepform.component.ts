import { Component, OnInit,  } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { CommonServiceService } from 'src/app/sharedService/common-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-multistepform',
  templateUrl: './multistepform.component.html',
  styleUrls: ['./multistepform.component.scss'],
})
export class MultistepformComponent implements OnInit {

  constructor(private fb: FormBuilder, private cs: CommonServiceService) {}

  public isLinear = true;

  customerbasic = false;
  customerAddress = false;
  customerFinancialData = false;
  dealerData = false;
  guarantorDetails = false;
  customerDocuments = false;
    step = 1;

    





  ngOnInit(): void {}

  CustomerRegister = this.fb.group({
    customerbasic: this.fb.group({
      customerId:'', // field goes null
      enquiryId: this.fb.control('', Validators.required),
      customerFirstName: this.fb.control('', Validators.required),
      customerMiddleName: this.fb.control('', Validators.required),
      customerLastName: this.fb.control('', Validators.required),
      customerDateOfBirth: this.fb.control('', Validators.required),
      customerGender: this.fb.control('', Validators.required),
      customerMobileNumber: this.fb.control(0, Validators.required),
      customerAdditionalMobileNumber: this.fb.control(0, Validators.required),
      customerEmail: this.fb.control('', Validators.required),
      customerVerificationStatus: this.fb.control(''),// field goes null
    }),

    //form 2
    customerAddress: this.fb.group({
      addressId: 0,
      localHouseNumber: this.fb.control(0, Validators.required),
      localAreaName: this.fb.control('', Validators.required),
      localStreetName: this.fb.control('', Validators.required),
      localCityName: this.fb.control('', Validators.required),
      localDistrict: this.fb.control('', Validators.required),
      localState: this.fb.control('', Validators.required),
      localPincode: this.fb.control(0, Validators.required),
      

      permanentHouseNumber: this.fb.control(0, Validators.required),
      permanentAreaName: this.fb.control('', Validators.required),
      permanentStreetName: this.fb.control('', Validators.required),
      permanentCityName: this.fb.control('', Validators.required),
      permanentDistrict: this.fb.control('', Validators.required),
      permanentState: this.fb.control('', Validators.required),
      permanentPincode: this.fb.control(0, Validators.required),
      
    }),
    //form 3
    customerFinancialData: this.fb.group({
      customerFinancialId: 0,
      customerTotalLoanRequired: this.fb.control(0, Validators.required),
      customerAccountNumber: this.fb.control(0, Validators.required),
      bankName: this.fb.control('', Validators.required),
      accountHolderName: this.fb.control('', Validators.required),
      ifscCode: this.fb.control('', Validators.required),
      ocupationType: this.fb.control('', Validators.required),
      designation: this.fb.control('', Validators.required),
      monthlyIncome: this.fb.control(0, Validators.required),
      employerName: this.fb.control('', Validators.required),
    }),

    //form 4
    dealerData: this.fb.group({
      dealerId:'',
      dealerName: this.fb.control('', Validators.required),
      dealerLocation: this.fb.control('', Validators.required),
      dealerEmail: this.fb.control('', Validators.required),
      accountNumber: this.fb.control(0, Validators.required),
      accountIFSCCode: this.fb.control('', Validators.required),
      vehicleCompanyName: this.fb.control('', Validators.required),
      vehicleModel: this.fb.control('', Validators.required),
      vehiclePrice: this.fb.control(0, Validators.required),
    }),

    // form 5
    guarantorDetails: this.fb.group({
      guarantorId:0,
      guarantorName: this.fb.control('', Validators.required),
      guarantorRelation: this.fb.control('', Validators.required),
      guarantorMobileNumber: this.fb.control(0, Validators.required),
      guarantorAdharcardNumber: this.fb.control(0, Validators.required),
      guarantorAddress: this.fb.control('', Validators.required),
    }),
    // form 6
    customerDocuments: this.fb.group({
      documentId: 0,
      pancard: this.fb.control('', Validators.required),
      adharcard: this.fb.control('', Validators.required),
      photo: this.fb.control('', Validators.required),
      signature: this.fb.control('', Validators.required),
      incomeStatement: this.fb.control('', Validators.required),
    }),
  });

  get customerbasicform() {
    return this.CustomerRegister.get("customerbasic") as FormGroup;
  }
  get customerAddressform() {
    return this.CustomerRegister.get("customerAddress") as FormGroup;
  }
  get customerFinancialDataForm() {
    return this.CustomerRegister.get("customerFinancialData") as FormGroup;
  }
  get dealerDetailsform() {
    return this.CustomerRegister.get("dealerData") as FormGroup;
  }
  get guarantorDetailsform() {
    return this.CustomerRegister.get("guarantorDetails") as FormGroup;
  }
  get alldocumentsform() {
    return this.CustomerRegister.get("customerDocuments") as FormGroup;
  }

  pancardurl: any;
  adharcardurl: any;
  photourl: any;
  signatureurl: any;
  incomeStatementurl: any;

  pancard: any;
  adharcard: any;
  photo: any;
  signature: any;
  incomeStatement: any;

  goBack(stepper: MatStepper) {
    stepper.previous();
  }

  goForward(stepper: MatStepper) {
    stepper.next();
  }


  // onselectfile1(event) {
  //   this.pancard = event.target.files[0]
  //   var reader= new FileReader()        // this for preview the file 
  //   reader.readAsDataURL(event.target.files[0]);
  //   reader.onload=(event:any)=>{
  //     this.pancardurl=event.target.result;
  // }}

  onselectfile1(event) {
    this.pancard = event.target.files[0]
    var reader= new FileReader()        // this for preview the file 
    reader.readAsDataURL(event.target.files[0]);
    reader.onload=(event:any)=>{
      this.pancardurl=event.target.result;
  }}
  
  onselectfile2(event) {
    this.adharcard = event.target.files[0];
    var reader= new FileReader()        // this for preview the file 
    reader.readAsDataURL(event.target.files[0]);
    reader.onload=(event:any)=>{
      this.adharcardurl=event.target.result;
  }}
  onselectfile3(event) {
    this.photo = event.target.files[0];
    var reader= new FileReader()        // this for preview the file 
    reader.readAsDataURL(event.target.files[0]);
    reader.onload=(event:any)=>{
      this.photourl=event.target.result;
  }}
  onselectfile4(event) {
    this.signature = event.target.files[0];
    var reader= new FileReader()        // this for preview the file 
    reader.readAsDataURL(event.target.files[0]);
    reader.onload=(event:any)=>{
      this.signatureurl=event.target.result;
  }}
  onselectfile5(event) {
    this.incomeStatement = event.target.files[0];
    var reader= new FileReader()        // this for preview the file 
    reader.readAsDataURL(event.target.files[0]);
    reader.onload=(event:any)=>{
      this.incomeStatementurl=event.target.result;
  }}


  saveCustomer() {
    this.cs.customer.enquiryId =
      this.CustomerRegister.get('customerbasic').get('enquiryId').value;
    this.cs.customer.customerFirstName =
      this.CustomerRegister.get('customerbasic').get('customerFirstName').value;
    this.cs.customer.customerMiddleName =
      this.CustomerRegister.get('customerbasic').get('customerMiddleName').value;
    this.cs.customer.customerLastName =
      this.CustomerRegister.get('customerbasic').get('customerLastName').value;
    this.cs.customer.customerDateOfBirth = this.CustomerRegister.get(
      'customerbasic'
    ).get('customerDateOfBirth').value;
    this.cs.customer.customerGender =
      this.CustomerRegister.get('customerbasic').get('customerGender').value;
    this.cs.customer.customerMobileNumber = this.CustomerRegister.get(
      'customerbasic'
    ).get('customerMobileNumber').value;
    this.cs.customer.customerAdditionalMobileNumber = this.CustomerRegister.get(
      'customerbasic'
    ).get('customerAdditionalMobileNumber').value;
    this.cs.customer.customerDateOfBirth = this.CustomerRegister.get(
      'customerbasic'
    ).get('customerDateOfBirth').value;
    this.cs.customer.customerEmail =
      this.CustomerRegister.get('customerbasic').get('customerEmail').value;

    this.cs.customer.customerAddress =
      this.CustomerRegister.get('customerAddress').value;

    this.cs.customer.customerFinancialData = this.CustomerRegister.get(
      'customerFinancialData'
    ).value;

    this.cs.customer.dealerData = this.CustomerRegister.get('dealerData').value;

    this.cs.customer.guarantorDetails = this.CustomerRegister.get('guarantorDetails').value;

    let data = new FormData();

    let allData = JSON.stringify(this.cs.customer); //customerid from Form to Json

    data.append('pancard', this.pancard); //binds media file to data with form data
    data.append('adharcard', this.adharcard);
    data.append('photo', this.photo);
    data.append('signature', this.signature);
    data.append('incomeStatement', this.incomeStatement);
    data.append('customerData', allData);

    this.cs.saveCustomer(data,this.cs.customer.enquiryId).subscribe();
    alert('saved');

    //alert when form is submitted
    
    Swal.fire({
      title: 'Congratulations!',
      text: 'You are one step Closer for Getting Your Loan please Come Back Later to check the status of Your Application',
      
      imageUrl: 'https://unsplash.it/400/200',
      imageWidth: 400,
      imageHeight: 200,
      imageAlt: 'Custom image',
    })

  }
}
