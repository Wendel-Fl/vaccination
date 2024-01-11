import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacGridComponent } from './vac-grid/vac-grid.component';
import { VacPageComponent } from './vac-page/vac-page.component';



@NgModule({
  declarations: [
    VacGridComponent,
    VacPageComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    VacGridComponent,
    VacPageComponent
  ]
})
export class ContainersModule { }
