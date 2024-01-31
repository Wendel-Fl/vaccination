import { Component } from '@angular/core';
import { ContainersModule } from '../../shared/components/containers/containers.module';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'vac-home',
  standalone: true,
  imports: [ContainersModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
