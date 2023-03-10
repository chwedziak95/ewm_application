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
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MaterialService } from './services/material.service';
import { RouterModule, Routes } from '@angular/router';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';
import { FilterByPipe } from './pipes/filter-by.pipe';
import { VendorListComponent } from './components/vendor-list/vendor-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { OrderItemsListComponent } from './components/order-items-list/order-items-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { MaterialDetailsComponent } from './components/material-details/material-details.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CategoryService } from './services/category.service';
import { UserService } from './services/user.service';
import { VendorService } from './services/vendor.service';
import { OrderService } from './services/order.service';
import { OrderItemsService } from './services/order-items.service';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { CreateMaterialComponent } from './components/create-material/create-material.component';
import { HeaderComponent } from './components/header/header.component';

const routes: Routes = [
  {path: 'create-material', component: CreateMaterialComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'order-details', component: OrderDetailsComponent},
  {path: 'orders', component: OrderListComponent},
  {path: 'vendor', component: VendorListComponent},
  {path: 'material/:materialId', component: MaterialDetailsComponent},
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
    UserListComponent,
    CartStatusComponent,
    MaterialDetailsComponent,
    OrderDetailsComponent,
    CartDetailsComponent,
    CreateMaterialComponent,
    HeaderComponent
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
  providers: [
    MaterialService, 
    CategoryService, 
    UserService, 
    VendorService,
    OrderService,
    OrderItemsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
