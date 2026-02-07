import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenCardInfoButtonDialog } from './open-card-info-button-dialog';

describe('OpenCardInfoButtonDialog', () => {
  let component: OpenCardInfoButtonDialog;
  let fixture: ComponentFixture<OpenCardInfoButtonDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OpenCardInfoButtonDialog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OpenCardInfoButtonDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
