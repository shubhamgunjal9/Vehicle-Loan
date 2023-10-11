import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//custome module
import { DefaultModModule } from './default-mod/default-mod.module';
import { DefaultModRoutingModule } from './default-mod/default-mod-routing.module';
import { DashboardModule } from './Dashboard/dashboard/dashboard.module';
import { RelationalExecutiveModule } from './Role/relational-executive/relational-executive.module';
import { UserModule } from './Role/user/user.module';
import { OperationalExecutiveModule } from './Role/operational-executive/operational-executive.module';
import { TearmsAndConditionComponent } from './tearms-and-condition/tearms-and-condition.component';
import { ShowlistComponent } from './Role/account-manager/showlist/showlist.component';
import { LedgerdataComponent } from './Role/account-manager/ledgerdata/ledgerdata.component';
import { InstallmentDataComponent } from './Role/account-manager/installment-data/installment-data.component';

@NgModule({
  declarations: [AppComponent, TearmsAndConditionComponent],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    DefaultModModule,
    DefaultModRoutingModule,
    
    DashboardModule,
    RelationalExecutiveModule,
    OperationalExecutiveModule,
    UserModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
