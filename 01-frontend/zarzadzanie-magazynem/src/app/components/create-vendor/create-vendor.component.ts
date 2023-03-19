import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { VendorService } from 'src/app/services/vendor.service';
import { CreateVendorPayload } from './create-vendor.payload';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-vendor',
  templateUrl: './create-vendor.component.html',
  styleUrls: ['./create-vendor.component.css']
})
export class CreateVendorComponent implements OnInit{

  vendorPayload: CreateVendorPayload;
  createVendorForm: FormGroup;
  private ST_CHAR_PATTERN = /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ0-9.+,/ -]*$/;


  constructor(
    private vendorService: VendorService,
    private router: Router,
    private toastr: ToastrService
  ){
    this.vendorPayload = {
    vendorName: null, 
    vendorAddress: null, 
    vendorCity: null, 
    vendorPostalCode: null, 
    vendorCountry: null, 
    vendorEmail: null, 
    vendorPhone: null, 
    vendorNip: null, 
    vendorRegon: null, 
    vendorKrs: null, 
    vendorBankAccount: null, 
    vendorBankName: null,
    }
  }

  ngOnInit(){
    this.createVendorForm = new FormGroup({
      vendorName: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorAddress: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorCity: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorPostalCode: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorCountry: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorEmail: new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      vendorPhone: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorNip: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorRegon: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorKrs: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorBankAccount: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ]),
      vendorBankName: new FormControl('', [
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ])
    });
  }

  createVendorSubmit(){
    this.vendorPayload.vendorName = this.createVendorForm.get('vendorName').value;
    this.vendorPayload.vendorAddress = this.createVendorForm.get('vendorAddress').value;
    this.vendorPayload.vendorCity = this.createVendorForm.get('vendorCity').value;
    this.vendorPayload.vendorPostalCode = this.createVendorForm.get('vendorPostalCode').value;
    this.vendorPayload.vendorCountry = this.createVendorForm.get('vendorCountry').value;
    this.vendorPayload.vendorEmail = this.createVendorForm.get('vendorEmail').value;
    this.vendorPayload.vendorPhone = this.createVendorForm.get('vendorPhone').value;
    this.vendorPayload.vendorNip = this.createVendorForm.get('vendorNip').value;
    this.vendorPayload.vendorRegon = this.createVendorForm.get('vendorRegon').value;
    this.vendorPayload.vendorKrs = this.createVendorForm.get('vendorKrs').value;
    this.vendorPayload.vendorBankAccount = this.createVendorForm.get('vendorBankAccount').value;
    this.vendorPayload.vendorBankName = this.createVendorForm.get('vendorBankName').value;
    
    if (this.createVendorForm.invalid){
      this.createVendorForm.markAllAsTouched();
      console.log(`Wystąpił błąd z invalid`);
      Object.keys(this.createVendorForm.controls).forEach((key) =>{
        const control = this.createVendorForm.controls[key];
        if (control.invalid){
          console.log(key, control.errors);
        }
      });
      return;
    }

    this.vendorService.createVendor(this.vendorPayload).subscribe({
      next: (response) => {
        this.toastr.success('Utworzono dostawcę');
      },
      error: (err) => {
        this.toastr.error(`Wystąpił błąd: ${err.message}`);
      }
    });  
  }

  get vendorName(){
    return this.createVendorForm.get('vendorName');
  }
  get vendorAddress(){
    return this.createVendorForm.get('vendorAddress');
  }
  get vendorCity(){
    return this.createVendorForm.get('vendorCity');
  }
  get vendorPostalCode(){
    return this.createVendorForm.get('vendorPostalCode');
  }
  get vendorCountry(){
    return this.createVendorForm.get('vendorCountry');
  }
  get vendorEmail(){
    return this.createVendorForm.get('vendorEmail');
  }
  get vendorPhone(){
    return this.createVendorForm.get('vendorPhone');
  }
  get vendorNip(){
    return this.createVendorForm.get('vendorNip');
  }
  get vendorRegon(){
    return this.createVendorForm.get('vendorRegon');
  }
  get vendorKrs(){
    return this.createVendorForm.get('vendorKrs');
  }
  get vendorBankAccount(){
    return this.createVendorForm.get('vendorBankAccount');
  }
  get vendorBankName(){
    return this.createVendorForm.get('vendorBankName');
  }
}
