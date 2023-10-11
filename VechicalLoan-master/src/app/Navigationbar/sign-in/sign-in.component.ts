import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup , Validator, Validators} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent {
  @ViewChild('container')
  container: ElementRef;

  constructor(private fb: FormBuilder, private router: Router) {}

  //login data is here
  loginf: FormGroup;
  //registration data is here
  registerf: FormGroup;

  ngOnInit() {
    this.loginf = this.fb.group({
      customerEmail: ['',[Validators.required, Validators.email]],
      customerPassword: ['', Validators.required],
    });

    this.registerf = this.fb.group({
      customerEmail: [],
      customerPassword: [],
      customerName: [],
    });
  }

  signIn() {
    this.container.nativeElement.classList.remove('right-panel-active');
    if (
      this.loginf.get('customerEmail').value === 'user' &&
      this.loginf.get('customerPassword').value === 'user123'
    ) {
      alert('welcome User');
      localStorage.setItem('role', 'User'); //role is the key and OExecutive is the data  this value is get in the dashboard.ts file now
      this.router.navigateByUrl('dashboardlayout/User'); // dashborad it is differnet module where we rout now
    } else if (
      this.loginf.get('customerEmail').value === 'oe' &&
      this.loginf.get('customerPassword').value === 'oe123'
    ) {
      alert('welcome OExecutive');
      localStorage.setItem('role', 'OExecutive');
      this.router.navigateByUrl('dashboardlayout/OExecutive');
    } else if (
      this.loginf.get('customerEmail').value === 're' &&
      this.loginf.get('customerPassword').value === 're123'
    ) {
      alert('welcome RExecutive');
      localStorage.setItem('role', 'RExecutive');
      this.router.navigateByUrl('dashboardlayout/RExecutive');
    } else if (
      this.loginf.get('customerEmail').value === 'cm' &&
      this.loginf.get('customerPassword').value === 'cm123'
    ) {
      alert('welcome Credit Manager');
      localStorage.setItem('role', 'Cmanager');
      this.router.navigateByUrl('dashboardlayout/Cmanager');
  } else if (
    this.loginf.get('customerEmail').value === 'ac' &&
    this.loginf.get('customerPassword').value === 'ac123'
  ) {
    alert('welcome Account Manager');
    localStorage.setItem('role', 'acManager');
    this.router.navigateByUrl('dashboardlayout/acManager');
} else {
      alert('invalid credetials ...!!!!');
    }
  }

  signUp() {
    this.container.nativeElement.classList.add('right-panel-active');
  }

  get customerEmail(){
    return this.loginf.get('customerEmail');
  }

  get customerPassword(){
    return this.loginf.get('customerPassword');
  }
}
