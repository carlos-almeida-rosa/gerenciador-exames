import { Component, inject, model, signal } from '@angular/core';
import { EditCredentials } from '../../../types/EditCredentials';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule, Validators, FormControl } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from "@angular/material/icon";

@Component({
  selector: 'app-restore-password-dialog',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogContent,
    MatDialogActions,
    ReactiveFormsModule,
    MatIcon
  ],
  templateUrl: './restore-password-dialog.html',
  styleUrl: './restore-password-dialog.scss',
})
export class RestorePasswordDialog {

  readonly dialogRestorePassword = inject(MatDialogRef<RestorePasswordDialog>);
  readonly emailControl = new FormControl('', [Validators.required, Validators.email]);
  typedEmail = signal('');
  typedPassword = signal('');
  typedConfirmPassword = signal('');
  errorMessage = signal('')
  hidePassword = signal(true);
  hideConfirmPassword = signal(true);

  editedCredentials: EditCredentials = {
    email: '',
    password: '',
    confirmPassword: '',
  }

  onCloseClick(): void {
    this.dialogRestorePassword.close();
  }

  onChangePasswordClick(): void {
    this.editedCredentials.email = this.typedEmail();
    this.editedCredentials.password = this.typedPassword();
    this.editedCredentials.confirmPassword = this.typedConfirmPassword();

    console.log(this.editedCredentials.email);
    console.log(this.editedCredentials.password);
    console.log(this.editedCredentials.confirmPassword);
  }

  updateErrorMessage() {
    if (this.emailControl.hasError('required')) {
      this.errorMessage.set("Digite o email");
    } else if (this.emailControl.hasError('email')) {
      this.errorMessage.set("Email inválido");
    } else {
      this.errorMessage.set('');
    }
  }

  clickToHidePassword(event: MouseEvent) {
    this.hidePassword.set(!this.hidePassword());
    event.stopPropagation();
  }

  clickToHideConfirmPassword(event: MouseEvent) {
    this.hideConfirmPassword.set(!this.hideConfirmPassword());
    event.stopPropagation();
  }
}
