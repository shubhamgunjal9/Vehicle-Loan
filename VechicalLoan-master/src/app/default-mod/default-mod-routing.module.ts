import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DefautcomponentComponent } from './defautcomponent/defautcomponent.component';
import { AboutUsComponent } from '../Navigationbar/about-us/about-us.component';
import { EmiCalculatorComponent } from '../Navigationbar/emi-calculator/emi-calculator.component';
import { FAQComponent } from '../Navigationbar/faq/faq.component';
import { HomeComComponent } from '../Navigationbar/home-com/home-com.component';
import { InterestRateComponent } from '../Navigationbar/interest-rate/interest-rate.component';
import { LoanEnquiryComponent } from '../Navigationbar/loan-enquiry/loan-enquiry.component';
import { SignInComponent } from '../Navigationbar/sign-in/sign-in.component';

const routes: Routes = [
  {
   path:'',component:DefautcomponentComponent,
   children:[
    {
      path:'',component:HomeComComponent     
    },      
      {path:'emiCalculator',component:EmiCalculatorComponent
    },
    {path:'aboutUs',component:AboutUsComponent
    },
      {path:'loanEnquiry',component:LoanEnquiryComponent
    },
      {path:'signIN',component:SignInComponent
    }, 
    {path:'faq', component:FAQComponent
    },
    {
      path:'interestRate', component:InterestRateComponent
    },
    {
      path:'home',component:HomeComComponent     
    },  
  ]
  
  
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DefaultModRoutingModule { }
