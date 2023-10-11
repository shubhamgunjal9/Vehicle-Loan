import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardcompComponent } from '../dashboardcomp/dashboardcomp.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatDividerModule } from '@angular/material/divider';
import { RouterModule } from '@angular/router';



@NgModule({

  declarations: [
    DashboardcompComponent],
  imports: [
    CommonModule,
    MatSidenavModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatDividerModule,
    RouterModule
    
    



  ],
  exports:[
    DashboardcompComponent
  ]
})
export class DashboardModule { }
