import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginRequestPayload } from './login-request.payload';
import { ReCaptchaV3Service } from 'ng-recaptcha';

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
  closeResult = '';
  emailFocused = false;
passwordFocused = false;
  resetPasswordForm: FormGroup = new FormGroup({});


  constructor(
    private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private modalService: NgbModal,
    private recaptchaV3Service: ReCaptchaV3Service
  ) {
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

    // Initialize reset password form
    this.resetPasswordForm = new FormGroup({
      resetEmail: new FormControl('', [Validators.required, Validators.email]),
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

    this.recaptchaV3Service.execute('importantAction')
      .subscribe((token: string) => {
        console.debug(`Token [${token}] generated`);
      });

    this.recaptchaV3Service.execute('login').subscribe(
      (captchaResponse: string | null) => {
        if (captchaResponse) {
          this.authService.login(this.loginRequestPayload, this.rememberMe).subscribe(
            (status) => {
              if (status) {
                this.isError = false;
                this.router.navigateByUrl('');
                this.toastr.success('Zalogowano');
              } else {
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
        } else {
          this.toastr.error('Weryfikacja reCAPTCHA nie powiodła się. Spróbuj ponownie.');
        }
      },
      (error) => {
        this.toastr.error('Wystąpił błąd podczas weryfikacji reCAPTCHA. Spróbuj ponownie.');
      }
    );
  }


  onRememberMeChange(event: any) {
    this.rememberMe = event.target.checked;
  }

  open(content) {

    const modalRef = this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
    modalRef.result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      },
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  reset(modal) {
    const resetEmail = this.resetPasswordForm.get('resetEmail')?.value;
    this.authService.resetPassword(resetEmail).subscribe(
      (response) => {
        if (response.status === 200 || response.status === 204 || response.status === 201) {
          this.toastr.success('Wysłano email z linkiem resetującym hasło');
          modal.close();
        } else {
          this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
        }
      },
      (error) => {
        this.toastr.error('Wystąpił nieoczekiwany błąd. Spróbuj ponownie później.');
      }
    );
  }

  onEmailFocus() {
    this.emailFocused = true;
  }
  
  onEmailBlur() {
    this.emailFocused = false;
  }
  
  onPasswordFocus() {
    this.passwordFocused = true;
  }
  
  onPasswordBlur() {
    this.passwordFocused = false;
  }
}
