import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { LoginRequestPayload } from './login-request.payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});
  loginRequestPayload: LoginRequestPayload;
  registerSuccessMessage: string = '';
  isError: boolean = false;
  rememberMe: boolean = false;


  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute,
    private router: Router, private toastr: ToastrService) {
    this.loginRequestPayload = {
      email: '',
      password: '',
    };
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
    });

    this.activatedRoute.queryParams
      .subscribe(params => {
        if (params['registered'] !== undefined && params['registered'] === 'true') {
          this.toastr.success('Zarejestrowano pomyślnie');
          this.registerSuccessMessage = 'Proszę sprawdzić skrzynkę mailową '
            + 'konto musi zostać aktywowane przed pierwszym logowaniem!';
        }
      });
  }

  login() {
    if (this.loginForm.invalid) {
      this.toastr.error('Błędne dane logowania');
      return;
    }
    this.loginRequestPayload.email = this.loginForm.get('email')?.value;
    this.loginRequestPayload.password = this.loginForm.get('password')?.value;

    this.authService.login(this.loginRequestPayload, this.rememberMe).subscribe(
      (status) => {
        console.log("status : " + status)
        if (status) {
          this.isError = false;
          this.router.navigateByUrl('');
          this.toastr.success('Zalogowano');
        }else{
          this.toastr.error('Błędne dane logowania');
        }
      },
      (error) => {
        console.error('Błąd logowania:', error);
        if (error) {
          this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
        }
      }
    );
  }
  
  
  
  onRememberMeChange(event: any) {
    this.rememberMe = event.target.checked;
  }  
}
