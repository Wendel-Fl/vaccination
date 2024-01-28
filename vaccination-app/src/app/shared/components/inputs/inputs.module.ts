import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacFormFieldComponent } from './vac-form-field/vac-form-field.component';
import { VacFormFieldErrorComponent } from './vac-form-field/vac-form-field-error/vac-form-field-error.component';
import { VacFormFieldLabelComponent } from './vac-form-field/vac-form-field-label/vac-form-field-label.component';



@NgModule({
  declarations: [
    VacFormFieldComponent,
    VacFormFieldErrorComponent,
    VacFormFieldLabelComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    VacFormFieldComponent,
    VacFormFieldErrorComponent,
    VacFormFieldLabelComponent
  ]
})
export class InputsModule { }
