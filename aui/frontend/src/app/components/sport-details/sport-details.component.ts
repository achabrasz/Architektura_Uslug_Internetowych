import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import { SportService } from '../../services/sport.service';
import { SportsmanService } from '../../services/sportsman.service';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sport-details.component.html',
})
export class SportDetailsComponent implements OnInit {
  category: any;
  elements: any[] = [];
  categoryId: string = '';

  constructor(
    private categoryService: SportService,
    private elementService: SportsmanService,
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
