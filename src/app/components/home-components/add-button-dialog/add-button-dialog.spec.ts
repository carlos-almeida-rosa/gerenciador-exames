import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddButtonDialog } from './add-button-dialog';

describe('AddButtonDialog', () => {
  let component: AddButtonDialog;
  let fixture: ComponentFixture<AddButtonDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddButtonDialog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddButtonDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
