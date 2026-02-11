import { ChangeDetectionStrategy, Component, model, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { merge } from 'rxjs';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-email-input',
  templateUrl: './email-input.html',
  styleUrl: './email-input.scss',
  imports: [MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule, MatIconModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmailInput {
  readonly emailControl = new FormControl('', [Validators.required, Validators.email]);
  errorMessage = signal('');
  emailValue=model<string>('');

  constructor() {
    merge(this.emailControl.statusChanges, this.emailControl.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  updateErrorMessage() {
    if (this.emailControl.hasError('required')) {
      this.errorMessage.set('You must enter a value');
    } else if (this.emailControl.hasError('email')) {
      this.errorMessage.set('Not a valid email');
    } else {
      this.errorMessage.set('');
    }
  }
}
