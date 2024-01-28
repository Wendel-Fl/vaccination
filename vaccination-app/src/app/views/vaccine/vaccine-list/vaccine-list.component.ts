import { Component, Injector } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-vaccine-list',
  standalone: true,
  imports: [],
  templateUrl: './vaccine-list.component.html',
  styleUrl: './vaccine-list.component.scss'
})
export class VaccineListComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}