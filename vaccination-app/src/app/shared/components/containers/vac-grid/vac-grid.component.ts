import { Component } from '@angular/core';

@Component({
  selector: 'vac-grid',
  template: `
    <div class="vac-grid-container">
      <ng-content></ng-content>
    </div>
  `,
  styleUrl: './vac-grid.component.scss'
})
export class VacGridComponent {

}
