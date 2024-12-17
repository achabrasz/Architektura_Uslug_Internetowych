import { Routes } from '@angular/router';
import { SportListComponent } from './components/sport-list/sport-list.component';
import { SportFormComponent } from './components/sport-form/sport-form.component';
import { SportDetailsComponent } from './components/sport-details/sport-details.component';
import { SportsmanFormComponent } from './components/sportsman-form/sportsman-form.component';
import { SportsmanDetailsComponent } from './components/sportsman-details/sportsman-details.component';

export const routes: Routes = [
  { path: '', component: SportListComponent },
  { path: 'categories/new', component: SportFormComponent },
  { path: 'categories/edit/:id', component: SportFormComponent },
  { path: 'categories/:id', component: SportDetailsComponent },
  { path: 'categories/:id/elements/new', component: SportsmanFormComponent },
  { path: 'categories/:id/elements/edit/:elementId', component: SportsmanFormComponent },
  { path: 'categories/:id/elements/:elementId', component: SportsmanDetailsComponent },
];
