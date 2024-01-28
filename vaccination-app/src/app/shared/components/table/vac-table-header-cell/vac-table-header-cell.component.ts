import { Component } from '@angular/core';

@Component({
  selector: 'vac-table-header-cell',
  template: `
    <th class="vac-table-header-cell">
      <ng-content></ng-content>
    </th>
  `,
  styleUrl: './vac-table-header-cell.component.scss'
})
export class VacTableHeaderCellComponent {

}
