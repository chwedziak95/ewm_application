import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MaterialListComponent } from './components/material-list/material-list.component';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RouterModule, Routes } from '@angular/router';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';
import { FilterByPipe } from './pipes/filter-by.pipe';
import { VendorListComponent } from './components/vendor-list/vendor-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { MaterialDetailsComponent } from './components/material-details/material-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { CreateMaterialComponent } from './components/create-material/create-material.component';
import { HeaderComponent } from './components/header/header.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthGuard } from './auth/auth.guard';
import { TokenInterceptor } from './auth/token/token-interceptor';
import { CreateVendorComponent } from './components/create-vendor/create-vendor.component';
import { CreateCategoryComponent } from './components/create-category/create-category.component';
import { CustomDatePipe } from './pipes/custom.datepipe';
import { VendorDetailsComponent } from './components/vendor-details/vendor-details.component';
import { InternalOrderListComponent } from './components/internal-order-list/internal-order-list.component';
import { InternalOrderCartComponent } from './components/internal-order-cart/internal-order-cart.component';
import { LogoutScreenComponent } from './components/logout-screen/logout-screen.component';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SortInternalOrderByDatePipe } from './pipes/sort-internal-order-by-date.pipe';
import { CompleteRegistrationComponent } from './auth/complete-registration/complete-registration.component';
import { RecaptchaV3Module, RECAPTCHA_V3_SITE_KEY } from 'ng-recaptcha';
import { environment } from 'src/environments/environment';
import { FooterComponent } from './components/footer/footer.component';

const routes: Routes = [
  {path: 'create-category', component: CreateCategoryComponent, canActivate: [AuthGuard]},
  {path: 'create-vendor', component: CreateVendorComponent, canActivate: [AuthGuard]},
  {path: 'create-material', component: CreateMaterialComponent, canActivate: [AuthGuard]},
  {path: 'cart-details', component: CartDetailsComponent, canActivate: [AuthGuard]},
  {path: 'internal-order-cart', component: InternalOrderCartComponent, canActivate: [AuthGuard]},
  {path: 'user-profile/:name', component: UserProfileComponent, canActivate: [AuthGuard] },
  {path: 'orders', component: OrderListComponent, canActivate: [AuthGuard]},
  {path: 'orders/:id', component: OrderDetailsComponent, canActivate: [AuthGuard]},
  {path: 'internal-orders', component: InternalOrderListComponent, canActivate: [AuthGuard]},
  {path: 'vendor', component: VendorListComponent, canActivate: [AuthGuard]},
  {path: 'vendor/:id', component: VendorDetailsComponent, canActivate: [AuthGuard]},
  {path: 'material', component: MaterialListComponent, canActivate: [AuthGuard]},
  {path: 'material/:id', component: MaterialDetailsComponent, canActivate: [AuthGuard]},
  {path: 'signup', component: SignupComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'complete-registration', component: CompleteRegistrationComponent},
  {path: 'logout', component: LogoutScreenComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    MaterialListComponent,
    SidebarMenuComponent,
    FilterByPipe,
    VendorListComponent,
    OrderListComponent,
    MaterialDetailsComponent,
    OrderDetailsComponent,
    CartDetailsComponent,
    CreateMaterialComponent,
    HeaderComponent,
    UserProfileComponent,
    CreateVendorComponent,
    CreateCategoryComponent,
    CustomDatePipe,
    SortInternalOrderByDatePipe,
    VendorDetailsComponent,
    InternalOrderListComponent,
    InternalOrderCartComponent,
    LogoutScreenComponent,
    CompleteRegistrationComponent,
    FooterComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FontAwesomeModule,
    FormsModule,
    CommonModule,
    NgbModule,
    RecaptchaV3Module
  ],
  exports: [RouterModule],
  providers: [
    {
      provide: RECAPTCHA_V3_SITE_KEY,
      useValue: environment.recaptcha.siteKey,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
