import { NgxSpinnerModule } from 'ngx-spinner';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentsModule } from './components/components.module';
import { DirectivesModule } from './directives/directives.module';
import { PipesModule } from './pipes/pipes.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ComponentsModule,
    DirectivesModule,
    NgxSpinnerModule,
    PipesModule
  ],
  exports: [
    CommonModule,
    ComponentsModule,
    DirectivesModule,
    NgxSpinnerModule,
    PipesModule
  ]
})
export class SharedModule { }
