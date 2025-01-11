import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './category-form.component.html',
})
export class CategoryFormComponent implements OnInit {
  form: FormGroup;
  isEditMode: boolean = false;
  categoryId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    public router: Router
  ) {
    this.form = this.fb.group({
      name: [''],
    });
  }

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id');
    if (this.categoryId) {
      this.isEditMode = true;
      this.loadCategory();
    }
  }

  loadCategory(): void {
    this.categoryService.getCategoryById(this.categoryId!).subscribe((data) => {
      this.form.patchValue(data);
    });
  }

  saveCategory(): void {
    if (this.isEditMode) {
      this.categoryService
        .updateCategory(this.categoryId!, this.form.value)
        .subscribe(() => this.router.navigate(['/']));
    } else {
      this.categoryService.createCategory(this.form.value).subscribe(() => this.router.navigate(['/']));
    }
  }
}
