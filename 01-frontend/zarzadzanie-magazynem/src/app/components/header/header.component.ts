import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/auth/shared/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  faUser = faUser;
  isLoggedIn: boolean;
  username: string;
  userFirstName: string;
  userLastName: string;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private cartService: CartService, 
    private router: Router) { }

  ngOnInit() {
    this.authService.authStatus$.subscribe((data: boolean) => {
      this.isLoggedIn = data;
      if (data) {
        this.username = this.authService.getUserName();
        this.userService.getUser(this.username).subscribe((data) => {
          this.userFirstName = data.firstName;
          this.userLastName = data.lastName;
        });
      } else {
        this.username = null;
        this.userFirstName = null;
        this.userLastName = null;
      }
    });
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.cartService.resetCart();
    this.router.navigateByUrl('/logout');
  }
}
