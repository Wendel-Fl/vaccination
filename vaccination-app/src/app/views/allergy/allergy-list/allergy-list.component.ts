import { Component, Injector } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-allergy-list',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './allergy-list.component.html',
  styleUrl: './allergy-list.component.scss'
})
export class AllergyListComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}