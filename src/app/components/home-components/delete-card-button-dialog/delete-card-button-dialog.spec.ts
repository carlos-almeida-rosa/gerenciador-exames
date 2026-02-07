import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteCardButtonDialog } from './delete-card-button-dialog';

describe('DeleteCardButtonDialog', () => {
  let component: DeleteCardButtonDialog;
  let fixture: ComponentFixture<DeleteCardButtonDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteCardButtonDialog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteCardButtonDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
