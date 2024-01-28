import { Component } from '@angular/core';

@Component({
  selector: 'vac-table-row-cell',
  template: `
    <td class="vac-table-row-cell">
      <ng-content></ng-content>
    </td>
  `,
  styleUrl: './vac-table-row-cell.component.scss'
})
export class VacTableRowCellComponent {

}
