import { Component } from '@angular/core';
import { ContainersModule } from '../../shared/components/containers/containers.module';

@Component({
  selector: 'vac-home',
  standalone: true,
  imports: [ContainersModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
