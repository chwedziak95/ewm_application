// complete-registration.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';

@Component({
  selector: 'app-complete-registration',
  templateUrl: './complete-registration.component.html',
  styleUrls: ['./complete-registration.component.css'],
})
export class CompleteRegistrationComponent implements OnInit {
  completeRegistrationForm: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.completeRegistrationForm = new FormGroup({
      password: new FormControl('', Validators.required),
      confirmPassword: new FormControl('', Validators.required),
    });
  }

  onSubmit() {
    const token = this.activatedRoute.snapshot.queryParamMap.get('token');
    const password = this.completeRegistrationForm.get('password')?.value;
    const confirmPassword =
      this.completeRegistrationForm.get('confirmPassword')?.value;
  
    if (password !== confirmPassword) {
      this.toastr.error('Hasła nie są takie same');
      return;
    }
  
    this.authService
      .completeRegistration(token, password, confirmPassword)
      .subscribe(
        (response) => {
          console.log("odpowiedź:", response);
          this.toastr.success(response.message);
          this.router.navigate(['/login']);
        },
        (error) => {
          this.toastr.error('Wystąpił problem przy rejestracji, proszę spróbować później');
        }
      );
  }
}
