import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MaterialListComponent } from './components/material-list/material-list.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MaterialService } from './services/material.service';
import { RouterModule, Routes } from '@angular/router';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';
import { FilterByPipe } from './pipes/filter-by.pipe';
import { VendorListComponent } from './components/vendor-list/vendor-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { OrderItemsListComponent } from './components/order-items-list/order-items-list.component';
import { UserListComponent } from './components/user-list/user-list.component';

const routes: Routes = [
  {path: 'orders', component: OrderListComponent},
  {path: 'vendor', component: VendorListComponent},
  {path: 'material', component: MaterialListComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent}
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
    OrderItemsListComponent,
    UserListComponent
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
    FormsModule
  ],
  exports: [RouterModule],
  providers: [MaterialService],
  bootstrap: [AppComponent]
})
export class AppModule { }
