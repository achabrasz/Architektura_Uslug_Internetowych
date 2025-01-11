import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ElementService } from '../../services/element.service';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-element-details',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './element-details.component.html',
})
export class ElementDetailsComponent implements OnInit {
  element: any;
  categoryId: string = '';
  elementId: string = '';

  constructor(
    private elementService: ElementService,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id')!;
    this.elementId = this.route.snapshot.paramMap.get('elementId')!;
    this.loadElement();
  }

  loadElement(): void {
    this.elementService.getElementById(this.categoryId, this.elementId).subscribe((data) => {
      this.element = data;
    });
  }
}
