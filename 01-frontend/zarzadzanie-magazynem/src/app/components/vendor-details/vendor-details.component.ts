import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Vendor } from 'src/app/common/vendor/vendor';
import { VendorService } from 'src/app/services/vendor.service';
import { CreateVendorPayload } from '../create-vendor/create-vendor.payload';

@Component({
  selector: 'app-vendor-details',
  templateUrl: './vendor-details.component.html',
  styleUrls: ['./vendor-details.component.css']
})
export class VendorDetailsComponent implements OnInit{

  vendor: Vendor;
  isEditMode: boolean = false;
  vendorPayload: CreateVendorPayload;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private vendorService: VendorService
  ){
    this.vendor = {} as Vendor;
    this.vendorPayload = {} as CreateVendorPayload;
  }

  ngOnInit() {
    this.getVendorDetails(this.route.snapshot.params['id']);
  }
  getVendorDetails(id: number) {
   this.vendorService.getVendor(id)
   .subscribe((vendor) => (this.vendor = vendor));
  }

  saveChanges() {
    this.isEditMode = false;
    Object.assign(this.vendor, this.vendorPayload);
    this.vendorService
      .updateVendor(this.vendor.vendorId, this.vendorPayload)
      .subscribe((response) => {
        if (response.status == 200 || response.status == 201) {
          this.toastr.success('Zapisano zmiany');
          this.updateData();
        } else {
          this.toastr.error('Wystąpił bład');
        }
      });
  }

  toggleEditMode(): void {
    this.isEditMode = true;
    this.vendorPayload = {
      vendorName: this.vendor.vendorName,
      vendorAddress: this.vendor.vendorAddress,
      vendorCity: this.vendor.vendorCity,
      vendorPostalCode: this.vendor.vendorPostalCode,
      vendorCountry: this.vendor.vendorCountry,
      vendorEmail: this.vendor.vendorEmail,
      vendorPhone: this.vendor.vendorPhone,
      vendorNip: this.vendor.vendorNip,
      vendorRegon: this.vendor.vendorRegon,
      vendorKrs: this.vendor.vendorKrs,
      vendorBankAccount: this.vendor.vendorBankAccount,
      vendorBankName: this.vendor.vendorBankName
    };
  }

  cancelChanges(): void {
    this.isEditMode = false;
  }
  updateData(){
    this.getVendorDetails(this.vendor.vendorId);
  }

}
