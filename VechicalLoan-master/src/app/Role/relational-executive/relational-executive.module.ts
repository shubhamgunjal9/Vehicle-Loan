import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { ShowEnquiriesComponent } from './show-enquiries/show-enquiries.component';


export const rerouting: Routes = [
  {    path:'viewenquiries',component:ShowEnquiriesComponent }
]

@NgModule({
  declarations: [
    ShowEnquiriesComponent
  ],
  imports: [
    RouterModule.forChild(rerouting),   
    CommonModule
  ],
  exports:[
    ShowEnquiriesComponent
  ]
})
export class RelationalExecutiveModule { }
