import { Component, Injector } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-schedule-form',
  standalone: true,
  imports: [],
  templateUrl: './schedule-form.component.html',
  styleUrl: './schedule-form.component.scss'
})
export class ScheduleFormComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}