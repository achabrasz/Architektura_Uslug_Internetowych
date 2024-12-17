import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {SportListComponent} from './components/sport-list/sport-list.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SportListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'category-management';
}
