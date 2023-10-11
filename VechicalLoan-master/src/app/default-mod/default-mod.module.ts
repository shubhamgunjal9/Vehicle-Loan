import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';     // must import for the pipe 

import { DefaultModRoutingModule } from './default-mod-routing.module';
import { FooterComponent } from './footer/footer.component';
// material all files 
// matstepper , matlist , maticon , matinput 
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { MatDialogModule} from '@angular/material/dialog';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
// for the forms input taken
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// Our componenet 
import { AppRoutingModule } from '../app-routing.module';
import { DefautcomponentComponent } from './defautcomponent/defautcomponent.component';
import { EmiCalculatorComponent } from '../Navigationbar/emi-calculator/emi-calculator.component';
import { AboutUsComponent } from '../Navigationbar/about-us/about-us.component';
import { LoanEnquiryComponent } from '../Navigationbar/loan-enquiry/loan-enquiry.component';
import { SignInComponent } from '../Navigationbar/sign-in/sign-in.component';
import { HomeComComponent } from '../Navigationbar/home-com/home-com.component';
 // module for chart   need to install -->  npm i ng-apexcharts
import { NgApexchartsModule } from "ng-apexcharts";  
// angu mat , bootstrap 
//signIn Imports
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';


@NgModule({
  declarations: [
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    DefautcomponentComponent,


    // child of the navbar 
    HomeComComponent,
    EmiCalculatorComponent,
    LoanEnquiryComponent,
    AboutUsComponent,
    SignInComponent
  ],
  imports: [
    AppRoutingModule,
    MatSidenavModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatTableModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    DefaultModRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    NgApexchartsModule,
    CommonModule,
    NgbModule,
    HttpClientModule

  
  ],
  exports:[
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    DefautcomponentComponent,
    HomeComComponent,
    EmiCalculatorComponent,
    LoanEnquiryComponent,
    AboutUsComponent,
    SignInComponent
  ]
})
export class DefaultModModule { }
