import { Component, OnInit } from '@angular/core';
import { Material } from 'src/app/common/material/material';
import { MaterialService } from 'src/app/services/material.service';
import { FilterByPipe } from 'src/app/pipes/filter-by.pipe';

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
  constructor(private materialService: MaterialService) { 
    this.materialService.getAll().subscribe( material =>{
      this.material$ = material;
    })
  }

  ngOnInit() : void{
  }
    
  }


