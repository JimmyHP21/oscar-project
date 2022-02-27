import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { AngularSharedModule } from "../shared/modules/angular.module";
import { ShoeListComponent } from "./shoe-list/shoe-list.component";

@NgModule({
  declarations: [ ShoeListComponent ],
  imports: [ AngularSharedModule, ReactiveFormsModule ],
  exports: [ ShoeListComponent ],
})
export class ShoeModule { }
