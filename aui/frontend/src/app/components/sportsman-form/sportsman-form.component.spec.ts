import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportsmanFormComponent } from './sportsman-form.component';

describe('ElementFormComponent', () => {
  let component: SportsmanFormComponent;
  let fixture: ComponentFixture<SportsmanFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SportsmanFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SportsmanFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
