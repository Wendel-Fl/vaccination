import { Component, Injector } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss'
})
export class UserListComponent extends UtilComponent {

  constructor(
    injector: Injector
  ) {
    super(injector);
  }

}
