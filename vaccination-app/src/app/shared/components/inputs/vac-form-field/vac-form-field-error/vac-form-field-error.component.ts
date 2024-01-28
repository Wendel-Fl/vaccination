import { Component } from '@angular/core';

@Component({
  selector: 'vac-form-field-error',
  template: `
    <span class="vac-form-field-error">
      <ng-content></ng-content>
    </span>
  `,
  styleUrl: './vac-form-field-error.component.scss'
})
export class VacFormFieldErrorComponent {

}
