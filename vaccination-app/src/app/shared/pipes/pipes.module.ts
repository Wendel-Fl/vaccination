import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NamecasePipe } from './namecase.pipe';
import { DateformatPipe } from './dateformat.pipe';



@NgModule({
  declarations: [
    NamecasePipe,
    DateformatPipe
  ],
  imports: [
    CommonModule
  ]
})
export class PipesModule { }
