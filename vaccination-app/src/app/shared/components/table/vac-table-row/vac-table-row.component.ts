import { Component } from '@angular/core';

@Component({
  selector: 'vac-table-row',
  template: `
    <tr class="vac-table-row">
      <ng-content select="vac-table-row-cell"></ng-content>
    </tr>
  `,
  styleUrl: './vac-table-row.component.scss'
})
export class VacTableRowComponent {

}
