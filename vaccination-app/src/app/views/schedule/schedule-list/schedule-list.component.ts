import { Component, Injector } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-schedule-list',
  standalone: true,
  imports: [],
  templateUrl: './schedule-list.component.html',
  styleUrl: './schedule-list.component.scss'
})
export class ScheduleListComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}