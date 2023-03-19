import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/shared/auth.service';
import { Subscription } from 'rxjs';
import { CartService } from './services/cart.service';
import { Router } from '@angular/router';
import { InternalCartService } from './services/internal-cart.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title: "Zarządzanie Magazynem";

  private logoutTimer: any;


  constructor(private authService: AuthService,
    private cartService: CartService,
    private internalCartService: InternalCartService,
    private router: Router) {}

  ngOnInit() {
    document.addEventListener('mousemove', this.resetTimer.bind(this));
    document.addEventListener('keydown', this.resetTimer.bind(this));
    document.addEventListener('mousedown', this.resetTimer.bind(this));
  
    this.authService.authStatus$.subscribe((isLoggedIn) => {
      if (isLoggedIn) {
        this.startTimer();
      } else {
        clearTimeout(this.logoutTimer);
      }
    });
  }
  

  startTimer() {
    if (this.logoutTimer) {
      clearTimeout(this.logoutTimer);
    }

    this.logoutTimer = setTimeout(() => {
      this.authService.logout();
      this.cartService.resetCart();
      this.router.navigateByUrl('/logout');
    }, 900000);
  }

  resetTimer() {
    this.startTimer();
  }
}
