import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {CategoriesListComponent} from './components/categories-list/categories-list.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CategoriesListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'category-management';
}
