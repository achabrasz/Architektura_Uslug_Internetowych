import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesListComponent } from './components/categories-list/categories-list.component';
import { CategoryFormComponent } from './components/category-form/category-form.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';
import { ElementFormComponent } from './components/element-form/element-form.component';
import { ElementDetailsComponent } from './components/element-details/element-details.component';

const routes: Routes = [
  { path: '', component: CategoriesListComponent },
  { path: 'categories/new', component: CategoryFormComponent },
  { path: 'categories/edit/:id', component: CategoryFormComponent },
  { path: 'categories/:id', component: CategoryDetailsComponent },
  { path: 'categories/:id/elements/new', component: ElementFormComponent },
  { path: 'categories/:id/elements/edit/:elementId', component: ElementFormComponent },
  { path: 'categories/:id/elements/:elementId', component: ElementDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

