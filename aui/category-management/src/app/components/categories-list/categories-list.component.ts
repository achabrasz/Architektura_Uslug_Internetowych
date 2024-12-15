import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { CategoryService } from '../../services/category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categories-list',
  standalone: true, // Ensure the component is standalone
  imports: [CommonModule], // Import CommonModule to use *ngFor and *ngIf
  templateUrl: './categories-list.component.html',
})
export class CategoriesListComponent implements OnInit {
  categories: any[] = [];

  constructor(private categoryService: CategoryService, private router: Router) {}

  ngOnInit(): void {
    this.fetchCategories();
  }

  // Fetch all categories
  fetchCategories(): void {
    this.categoryService.getAllCategories().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (err) => {
        console.error('Failed to fetch categories:', err);
      },
    });
  }

  // Navigate to the "Add Category" form
  addCategory(): void {
    this.router.navigate(['/categories/new']);
  }

  // Navigate to the "Edit Category" form
  editCategory(id: string): void {
    this.router.navigate([`/categories/edit/${id}`]);
  }

  // Delete a specific category
  deleteCategory(id: string): void {
    if (confirm('Are you sure you want to delete this category?')) {
      this.categoryService.deleteCategory(id).subscribe({
        next: () => {
          console.log('Category deleted successfully');
          this.fetchCategories(); // Refresh the list
        },
        error: (err) => {
          console.error('Error deleting category:', err);
        },
      });
    }
  }

  // View category details
  viewDetails(id: string): void {
    this.router.navigate([`/categories/${id}`]);
  }
}
