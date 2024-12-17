import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportsmanDetailsComponent } from './sportsman-details.component';

describe('ElementDetailsComponent', () => {
  let component: SportsmanDetailsComponent;
  let fixture: ComponentFixture<SportsmanDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SportsmanDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SportsmanDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
