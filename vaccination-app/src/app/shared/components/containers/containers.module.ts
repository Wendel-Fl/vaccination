import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacGridComponent } from './vac-grid/vac-grid.component';
import { VacPageComponent } from './vac-page/vac-page.component';
import { VacFlexComponent } from './vac-flex/vac-flex.component';



@NgModule({
  declarations: [
    VacGridComponent,
    VacPageComponent,
    VacFlexComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    VacGridComponent,
    VacPageComponent,
    VacFlexComponent
  ]
})
export class ContainersModule { }
