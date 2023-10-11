import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatStepperModule } from '@angular/material/stepper';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ShowVerifiedListComponent } from './show-verified-list/show-verified-list.component';
import { SancationGenerationComponent } from './sancation-generation/sancation-generation.component';
import { Router, RouterModule, Routes } from '@angular/router';
import { Customer } from 'src/app/Model/customer';

export const cmrouting: Routes = [
  {    path:'showVerifiedList',component: ShowVerifiedListComponent},
  {    path:'sanctionGeneration',component: SancationGenerationComponent},
]

@NgModule({
  declarations:[SancationGenerationComponent,
    ShowVerifiedListComponent],
  imports: [
    RouterModule.forChild(cmrouting),
    CommonModule,
    MatInputModule,
    MatStepperModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
    MatListModule,
    MatCardModule,
    MatCheckboxModule,
  ],
  exports: [SancationGenerationComponent,RouterModule,
    ShowVerifiedListComponent],
})
export class CreditManagarModule {

 
 }
