import { Component, Injector } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-allergy-form',
  standalone: true,
  imports: [],
  templateUrl: './allergy-form.component.html',
  styleUrl: './allergy-form.component.scss'
})
export class AllergyFormComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}
