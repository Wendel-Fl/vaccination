import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { InputsModule } from './inputs/inputs.module';
import { TableModule } from './table/table.module';
import { RouterModule } from '@angular/router';
import { ContainersModule } from './containers/containers.module';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent
  ],
  imports: [
    CommonModule,
    InputsModule,
    TableModule,
    ContainersModule,
    RouterModule
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    InputsModule,
    TableModule,
    ContainersModule
  ]
})
export class ComponentsModule { }
