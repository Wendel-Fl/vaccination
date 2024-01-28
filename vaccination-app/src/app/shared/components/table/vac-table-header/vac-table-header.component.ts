import { Component } from '@angular/core';

@Component({
  selector: 'vac-table-header',
  template: `
    <thead class="vac-table-header">
      <ng-content select="vac-table-header-cell"></ng-content>
    </thead>
  `,
  styleUrl: './vac-table-header.component.scss'
})
export class VacTableHeaderComponent {

}
