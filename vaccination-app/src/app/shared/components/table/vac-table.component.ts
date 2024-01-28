import { Component } from '@angular/core';

@Component({
  selector: 'vac-table',
  template: `
    <table class="vac-table">
      <ng-content select="vac-table-header"></ng-content>
      <tbody>
        <ng-content select="vac-table-row"></ng-content>
      </tbody>
    </table>
  `,
  styleUrl: './vac-table.component.scss'
})
export class VacTableComponent {

}
