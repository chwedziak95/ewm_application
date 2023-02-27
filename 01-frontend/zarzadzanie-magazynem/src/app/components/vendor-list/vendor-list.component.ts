import { Component, OnInit } from '@angular/core';
import { Vendor } from 'src/app/common/vendor/vendor';
import { FilterByPipe } from 'src/app/pipes/filter-by.pipe';
import { VendorService } from 'src/app/services/vendor.service';

@Component({
  selector: 'app-vendor-list',
  templateUrl: './vendor-list.component.html',
  styleUrls: ['./vendor-list.component.css'],
  providers: [FilterByPipe]
})
export class VendorListComponent implements OnInit{

  vendorNameFilter: string = '';
  vendorEmailFilter: string = ''; 
  vendorPhoneFilter: string = ''; 
  vendorNipFilter: string = ''; 

  vendor$: Array<Vendor> = [];
  constructor(private vendorService: VendorService) { 
    this.vendorService.getAll().subscribe( vendor =>{
      this.vendor$ = vendor;
    })
  }

  ngOnInit() : void{
  }

}
