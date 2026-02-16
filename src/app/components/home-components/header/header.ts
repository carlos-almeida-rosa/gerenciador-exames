import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { Component, inject, model, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
} from '@angular/material/dialog';
import { ChangePasswordDialog } from '../change-password-dialog/change-password-dialog';
import { RouterLink } from "@angular/router";
import { Auth } from '../../../services/auth';

@Component({
  selector: 'app-header',
  templateUrl: './header.html',
  styleUrl: './header.scss',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatMenuModule, RouterLink],
})
export class Header {
  readonly dialog = inject(MatDialog);
  readonly password = signal('');
  readonly confirmPassword = model('');
  private authService = inject(Auth)

  openChangePasswordDialog(): void {
    const dialogRef = this.dialog.open(ChangePasswordDialog, {
      data: { password: this.password(), confirmPassword: this.confirmPassword() },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // if (result !== undefined) {
      //   this.password.set(result);
      // }
    });
  }

  logout(): void {
    this.authService.logout();
  }
}