import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ElementService } from '../../services/element.service';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-element-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './element-form.component.html',
})
export class ElementFormComponent implements OnInit {
  form: FormGroup;
  isEditMode: boolean = false;
  categoryId: string = '';
  elementId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private elementService: ElementService,
    private route: ActivatedRoute,
    public router: Router
  ) {
    this.form = this.fb.group({
      name: [''],
      rating: [''],
    });
  }

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id')!;
    this.elementId = this.route.snapshot.paramMap.get('elementId');

    if (this.elementId) {
      this.isEditMode = true;
      this.loadElement();
    }
  }

  loadElement(): void {
    this.elementService.getElementById(this.categoryId, this.elementId!).subscribe((data) => {
      this.form.patchValue(data);
    });
  }

  saveElement(): void {
    if (this.isEditMode) {
      this.elementService
        .updateElement(this.categoryId, this.elementId!, this.form.value)
        .subscribe(() => this.router.navigate(['/categories', this.categoryId]));
    } else {
      this.elementService.createElement(this.categoryId, this.form.value).subscribe(() => {
        this.router.navigate(['/categories', this.categoryId]);
      });
    }
  }
}
