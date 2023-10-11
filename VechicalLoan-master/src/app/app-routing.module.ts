import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardcompComponent } from './Dashboard/dashboardcomp/dashboardcomp.component';
import { AccountManagerModule } from './Role/account-manager/account-manager.module';
import { CreditManagarModule } from './Role/credit-managar/credit-managar.module';
import { OperationalExecutiveModule } from './Role/operational-executive/operational-executive.module';
import { RelationalExecutiveModule } from './Role/relational-executive/relational-executive.module';

import { UserModule } from './Role/user/user.module';
import { TearmsAndConditionComponent } from './tearms-and-condition/tearms-and-condition.component';

const routes: Routes = [

  { path: 'dashboardlayout',component:DashboardcompComponent,
  children: [
    {
      path: 'RExecutive', loadChildren: () => RelationalExecutiveModule
    },
    {
      path: 'OExecutive', loadChildren: () => OperationalExecutiveModule
    },            
    {
      path: 'User', loadChildren: () => UserModule
    },
    {
      path: 'Cmanager', loadChildren: () => CreditManagarModule
    },   
     {
      path: 'acManager', loadChildren: () => AccountManagerModule
    }
  ]
},
  { path: 'termsAndCondition',component:TearmsAndConditionComponent
} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
