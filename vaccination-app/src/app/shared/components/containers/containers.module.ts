import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacGridComponent } from './vac-grid/vac-grid.component';



@NgModule({
  declarations: [
    VacGridComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    VacGridComponent
  ]
})
export class ContainersModule { }
