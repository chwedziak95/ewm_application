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
    private toastr: ToastrService) {
    this.signupRequestPayload = {
      email: '',
      firstName: '',
      lastName: ''
    };
  }

  ngOnInit() {
    this.signupForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', [Validators.required])
    });
  }


  signup() {
    this.signupRequestPayload.email = this.signupForm.get('email')?.value;
    this.signupRequestPayload.firstName = this.signupForm.get('firstName')?.value;
    this.signupRequestPayload.lastName = this.signupForm.get('lastName')?.value;

    this.authService.signup(this.signupRequestPayload).subscribe(
      (response) => {
        this.toastr.success('Wysłanoemail aktywacyjny na skrzynkę pracownika ' + this.signupRequestPayload.email);
        this.router.navigate(['/']);
      },
      (error) => {
        console.log(error);
        this.toastr.error('Wystąpił problem podczas rejestarcji');
      }
    );
  }


}
