import { Component } from '@angular/core';

@Component({
  selector: 'vac-page',
  template: `
    <div class="vac-page-container">
      <div>
        <ng-content></ng-content>
      </div>
    </div>
  `,
  styleUrl: './vac-page.component.scss'
})
export class VacPageComponent {

}
