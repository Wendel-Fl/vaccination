import { Component } from '@angular/core';

@Component({
  selector: 'vac-form-field',
  template: `
    <div class="vac-form-field">
      <ng-content select="vac-form-field-label"></ng-content>
      <ng-content select="input"></ng-content>
      <ng-content select="select"></ng-content>
      <ng-content select="vac-form-field-error"></ng-content>
    </div>
  `,
  styleUrl: './vac-form-field.component.scss',
})
export class VacFormFieldComponent {}
