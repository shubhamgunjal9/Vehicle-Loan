import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SancationGenerationComponent } from '../credit-managar/sancation-generation/sancation-generation.component';
import { InstallmentDataComponent } from './installment-data/installment-data.component';
import { LedgerdataComponent } from './ledgerdata/ledgerdata.component';
import { ShowlistComponent } from './showlist/showlist.component';

export const acRouting: Routes = [
  {    path:'showSanctionist',component: ShowlistComponent},
  {    path:'showLedgerData',component: LedgerdataComponent},
  {    path:'installmentData',component: InstallmentDataComponent},
]

@NgModule({
  declarations: [
    ShowlistComponent, 
    LedgerdataComponent, 
    InstallmentDataComponent],
  imports: [
    RouterModule.forChild(acRouting),
    CommonModule
  ],exports:[
    ShowlistComponent, 
    LedgerdataComponent, 
    InstallmentDataComponent
  ]
})


export class AccountManagerModule { }
