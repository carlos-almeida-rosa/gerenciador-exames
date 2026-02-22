import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestorePasswordDialog } from './restore-password-dialog';

describe('RestorePasswordDialog', () => {
  let component: RestorePasswordDialog;
  let fixture: ComponentFixture<RestorePasswordDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestorePasswordDialog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestorePasswordDialog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
