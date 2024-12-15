import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { ElementService } from '../../services/element.service';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-details.component.html',
})
export class CategoryDetailsComponent implements OnInit {
  category: any;
  elements: any[] = [];
  categoryId: string = '';

  constructor(
    private categoryService: CategoryService,
    private elementService: ElementService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id')!;
    this.loadCategory();
    this.loadElements();
  }

  loadCategory(): void {
    this.categoryService.getCategoryById(this.categoryId).subscribe((data) => {
      this.category = data;
    });
  }

  loadElements(): void {
    this.elementService.getElementsByCategory(this.categoryId).subscribe((data) => {
      this.elements = data;
    });
  }

  deleteElement(elementId: string): void {
    this.elementService.deleteElement(this.categoryId, elementId).subscribe(() => {
      this.loadElements();
    });
  }
}
