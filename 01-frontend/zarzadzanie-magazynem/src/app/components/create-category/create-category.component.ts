import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CategoryService } from 'src/app/services/category.service';
import { CreateCategoryPayload } from './create-category.payload';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})
export class CreateCategoryComponent implements OnInit{

  categoryPayload: CreateCategoryPayload;
  createCategoryForm: FormGroup;
  private ST_CHAR_PATTERN = /^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ0-9 ]*$/;

  constructor(
    private categoryService: CategoryService,
    private toastr: ToastrService
  ){
    this.categoryPayload ={
      name: null
    }
  }

  ngOnInit() {
    this.createCategoryForm = new FormGroup({
      name: new FormControl('',[
        Validators.required,
        Validators.pattern(this.ST_CHAR_PATTERN)
      ])
    });
  }

  createCategorySubmit(){
    this.categoryPayload.name = this.createCategoryForm.get('name').value;

    if(this.createCategoryForm.invalid){
      this.createCategoryForm.markAllAsTouched();
      return;
    }

    this.categoryService.createCategory(this.categoryPayload).subscribe({
      next: (response) => {
        this.toastr.success(`Utworzono kategorię`);
      },
      error: (err) => {
        this.toastr.error(`Wystąpił błąd: ${err.message}`);
      },
    });
  }

  get name(){
    return this.createCategoryForm.get('name');
  }

}
