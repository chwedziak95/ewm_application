import { Component, OnInit } from '@angular/core';
import { Material } from 'src/app/common/material/material';
import { MaterialService } from 'src/app/services/material.service';
import { FilterByPipe } from 'src/app/pipes/filter-by.pipe';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/common/cart-item';

@Component({
  selector: 'app-material-list',
  templateUrl: './material-list.component.html',
  styleUrls: ['./material-list.component.css'],
  providers: [FilterByPipe]
})
export class MaterialListComponent implements OnInit{

  materialNumberFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po numerze materiału
  materialNameFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po nazwie materiału
  materialCategoryFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po kategorii materiału
  materialVendorFilter: string = ''; // Zmienna do przechowywania wartości filtrowania po dostawcy materiału

  material$: Array<Material> = [];
  constructor(private materialService: MaterialService,
              private cartService: CartService) { 
    this.materialService.getAll().subscribe( material =>{
      this.material$ = material;
    })
  }

  ngOnInit() : void{
  }

  addToCart(material: Material){
    console.log(`Dodano do koszyka: ${material.materialId}`);    

    const cartItem = new CartItem(material);

    this.cartService.addToCart(cartItem);
  }

}
