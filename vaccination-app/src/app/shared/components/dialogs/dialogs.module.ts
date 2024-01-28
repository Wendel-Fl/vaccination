import { NgModule } from '@angular/core';
import { CommonModule, NgComponentOutlet } from '@angular/common';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { DialogComponent } from './dialog.component';
import { ContainersModule } from '../containers/containers.module';
import { DirectivesModule } from '../../directives/directives.module';



@NgModule({
  declarations: [
    ConfirmationDialogComponent,
    DialogComponent,
  ],
  imports: [
    CommonModule,
    NgComponentOutlet,
    ContainersModule,
    DirectivesModule
  ],
  exports: [
    DialogComponent
  ]
})
export class DialogsModule { }
