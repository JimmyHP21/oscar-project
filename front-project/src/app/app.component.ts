import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { formatDate } from '@angular/common';
import { ShoeEditNewModel } from './shoe/model/shoe-edit-new.model';
import { ShoeEditNewService } from './shoe/service/shoe-edit-new.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ShoeService } from './shoe/shoe-list/service/shoe.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'front-project';
  pageType = 'list';
  editShoe: any;
  shoe: ShoeEditNewModel;
  shoeConstructor: FormGroup;
  formShoe: FormGroup;

  isLoading = true;

  shoeList;

  /**
   * Constructor
   *
   */
   constructor(private _formBuilder: FormBuilder, @Inject(LOCALE_ID) private locale: string,
              private _shoeService: ShoeService,
              private _newEditService: ShoeEditNewService,  private _matSnackBar: MatSnackBar) {}

  ngOnInit(){
    this.listShoe();

    this.formShoe = this._formBuilder.group({
      size: [0],
      category: [''],
      color: [''],
      price: [0.0],
      bride: [''],
      description: [''],
      dateRegister: ['']
    });
  }

  onSubmit() {
    let data = this.formShoe.value;
    data['dateRegister'] = formatDate(data['dateRegister'],'dd/MM/yyyy',this.locale);
  }

  listShoe() {
    this.isLoading = true;
    this._shoeService.getListShoe().subscribe((data) => {
      this.shoeList = data;
      this.isLoading = false;
    }, (error) => {
      console.log(error);
      this.isLoading = false;
    });
  }

  clickNewEdit(ret) {
    if (ret) {
      this.editShoe = ret.obj;
      this.pageType = ret.page;
    } else {
      this.editShoe = null;
      this.pageType = 'new';
    }


    if (this.editShoe) {
      this.shoe = new ShoeEditNewModel(this.editShoe);
    } else {
      this.shoe = new ShoeEditNewModel();
    }

    this.shoeConstructor = this.createForm();
  }

  // -----------------------------------------------------------------------------------------------------
  // @ Public methods
  // -----------------------------------------------------------------------------------------------------

  /**
   * Create form
   *
   * @returns {FormGroup}
   */
   createForm(): FormGroup {
    return this._formBuilder.group({
      id: [{value: this.shoe.id, disabled: true}],
      size: [this.shoe.size],
      category: [this.shoe.category],
      color: [this.shoe.color],
      price: [this.shoe.price],
      brand: [this.shoe.brand],
      quantity: [this.shoe.quantity],
      description: [this.shoe.description],
      dateRegister: [{value: this.shoe.dateRegister, disabled: true}]
    });
  }

  cancelar() {
    this.pageType = 'list';
  }

  addShoe() {
    const data = this.shoeConstructor.getRawValue();
    this._newEditService.addShoe(data)
      .then(() => {

        // Show the success message
        this._matSnackBar.open('Calçado criado', 'OK', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 2000
        });
      });

    this.listShoe();
    this.pageType = 'list';
  }

  saveShoe() {

    const data = this.shoeConstructor.getRawValue();

    this._newEditService.saveShoe(data)
      .then(() => {
        // Show the success message
        this._matSnackBar.open('Calçado atualizado', 'OK', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 2000
        });
      });

    this.listShoe();
    this.pageType = 'list';
  }

  deleteShoe(id) {
    this._shoeService.deleteShoe(id)
      .then(() => {
        // Show the success message
        this._matSnackBar.open('Tenis deletado', 'OK', {
          horizontalPosition: 'right',
          verticalPosition: 'top',
          duration: 2000
        });

        this.listShoe();
    });
  }

  get f(): any {
    return this.formShoe.controls;
  }
}
