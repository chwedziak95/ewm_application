import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginRequestPayload } from './login-request.payload';
import { HttpResponse } from '@angular/common/http';

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
  resetPasswordForm: FormGroup = new FormGroup({});


  constructor(
    private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private modalService: NgbModal,
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

    this.authService.login(this.loginRequestPayload, this.rememberMe).subscribe(
      (status) => {
        console.log("status : " + status)
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
}
