import { Component } from '@angular/core';

@Component({
  selector: 'vac-form-field-label',
  template: `
    <label class="vac-form-field-label">
      <ng-content></ng-content>
    </label>
  `,
  styleUrl: './vac-form-field-label.component.scss'
})
export class VacFormFieldLabelComponent {

}
