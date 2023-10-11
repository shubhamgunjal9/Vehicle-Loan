import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ApplicationlistComponent } from './applicationlist/applicationlist.component';
import { SingleappliactionComponent } from './singleappliaction/singleappliaction.component';



 export const oerouting: Routes = [
  {    path:'vieweApplication',component:ApplicationlistComponent },
  {    path:"showApplication",component:SingleappliactionComponent },
 // this is way 1 not working  {    path:"showApplication/:data",component:SingleappliactionComponent }// Data act as placeholder for the routing object in .ts of this commp
  
]

@NgModule({
  declarations: [
  ApplicationlistComponent,
  SingleappliactionComponent
  ],
  imports: [
    RouterModule.forChild(oerouting),
    CommonModule
  ],
  exports:[ 
    RouterModule,
    ApplicationlistComponent,
    SingleappliactionComponent]
})
export class OperationalExecutiveModule { }
