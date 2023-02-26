import { Component } from '@angular/core';
import { Material } from 'src/app/common/material';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-material-list',
  templateUrl: './material-list.component.html',
  styleUrls: ['./material-list.component.css']
})
export class MaterialListComponent {

  material$: Array<Material> = [];
  constructor(private materialService: MaterialService) { 
    this.materialService.getAll().subscribe( material =>{
      this.material$ = material;
    })
  }

  ngOnInit() : void{
  }
    
  }


