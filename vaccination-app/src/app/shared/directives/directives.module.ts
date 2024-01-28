import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacButtonDirective } from './vac-button.directive';



@NgModule({
  declarations: [
    VacButtonDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    VacButtonDirective
  ]
})
export class DirectivesModule { }
