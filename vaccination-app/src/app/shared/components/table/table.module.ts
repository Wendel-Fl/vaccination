import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacTableComponent } from './vac-table.component';
import { VacTableHeaderComponent } from './vac-table-header/vac-table-header.component';
import { VacTableHeaderCellComponent } from './vac-table-header-cell/vac-table-header-cell.component';
import { VacTableRowComponent } from './vac-table-row/vac-table-row.component';
import { VacTableRowCellComponent } from './vac-table-row-cell/vac-table-row-cell.component';
import { VacEmptyTableComponent } from './vac-empty-table/vac-empty-table.component';



@NgModule({
  declarations: [
    VacTableComponent,
    VacTableHeaderComponent,
    VacTableHeaderCellComponent,
    VacTableRowComponent,
    VacTableRowCellComponent,
    VacEmptyTableComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    VacTableComponent,
    VacTableHeaderComponent,
    VacTableHeaderCellComponent,
    VacTableRowComponent,
    VacTableRowCellComponent,
    VacEmptyTableComponent
  ]
})
export class TableModule { }
