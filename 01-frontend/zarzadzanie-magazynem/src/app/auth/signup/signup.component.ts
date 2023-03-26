import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { SignupRequestPayload } from './signup-request.payload';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload: SignupRequestPayload;

  signupForm: FormGroup = new FormGroup({});

  isError: boolean = false;

  constructor(private authService: AuthService, private router: Router,
    private toastr: ToastrService){
    this.signupRequestPayload = {
      email: '',
      firstName: '',
      lastName: '',
      password: ''
    };
   }

  ngOnInit() {
    this.signupForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', [Validators.required]),
      password: new FormControl('', Validators.required),
    });
  }

  signup(){
    this.signupRequestPayload.email =  this.signupForm.get('email')?.value;
    this.signupRequestPayload.firstName =  this.signupForm.get('firstName')?.value;
    this.signupRequestPayload.lastName =  this.signupForm.get('lastName')?.value;
    this.signupRequestPayload.password =  this.signupForm.get('password')?.value;

    this.authService.signup(this.signupRequestPayload)
      .subscribe(data => {
        this.router.navigate(['/login'],
          { queryParams: { registered: 'true' } });
      }, error => {
        console.log(error);
        this.toastr.error('Rejestracja nie powiodła się, proszę spróbować ponownie.');
      });
  }
  
}
