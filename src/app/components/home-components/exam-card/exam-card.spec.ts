import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamCard } from './exam-card';

describe('ExamCard', () => {
  let component: ExamCard;
  let fixture: ComponentFixture<ExamCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExamCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
