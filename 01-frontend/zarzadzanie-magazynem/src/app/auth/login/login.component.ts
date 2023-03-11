import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { catchError, throwError } from 'rxjs';
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

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute,
    private router: Router, private toastr: ToastrService){
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
    this.loginRequestPayload.email = this.loginForm.get('email').value;
    this.loginRequestPayload.password = this.loginForm.get('password').value;
  
    this.authService.login(this.loginRequestPayload)
      .pipe(
        catchError((error) => {
          this.isError = true;
          console.log(error);
          return throwError(() => new Error(error));
        })
      )
      .subscribe((data) => {
        this.isError = false;
        this.router.navigateByUrl('');
        this.toastr.success('Zalogowano');
      });
  }
}
