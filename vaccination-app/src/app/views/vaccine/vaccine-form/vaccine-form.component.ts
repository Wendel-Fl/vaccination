import { Component, Injector } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-vaccine-form',
  standalone: true,
  imports: [],
  templateUrl: './vaccine-form.component.html',
  styleUrl: './vaccine-form.component.scss'
})
export class VaccineFormComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}
