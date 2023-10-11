import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { BreakpointObserver } from '@angular/cdk/layout';
import { Menu } from 'src/app/Model/menu';

@Component({
  selector: 'app-dashboardcomp',
  templateUrl: './dashboardcomp.component.html',
  styleUrls: ['./dashboardcomp.component.scss']
})
export class DashboardcompComponent {
  
  
  @ViewChild(MatSidenav)  
  public sidenav: MatSidenav;

  constructor(private router: Router, private observer: BreakpointObserver) {}

  public userRole: any;  

  public menuItems: any[];

  ngOnInit() {
    this.menuItems = Menu.menus;
    console.log('menuitems array :' + this.menuItems);
    this.userRole = localStorage.getItem('role'); //   direclty getting data here
    console.log('UserRole is ' + this.userRole);
  }

  ngAfterViewInit() {
    this.observer.observe(['(max-width: 800px)']).subscribe((res) => {
      if (res.matches) {
        this.sidenav.mode = 'over';
        this.sidenav.close();
      } else {
        this.sidenav.mode = 'side';
        this.sidenav.open();
      }
    });
  }

  logout(){
    localStorage.setItem('role', '');
    this.router.navigateByUrl('home'); 

  }

}
