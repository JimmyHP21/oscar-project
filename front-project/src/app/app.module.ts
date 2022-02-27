import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import 'hammerjs';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { AppComponent } from './app.component';
import { AngularSharedModule } from './shared/modules/angular.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ShoeModule } from './shoe/shoe.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    AngularSharedModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BrowserModule,

    HttpClientModule,

    ShoeModule
  ],
  bootstrap: [AppComponent],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'en-GB' }
  ]
})
export class AppModule { }
