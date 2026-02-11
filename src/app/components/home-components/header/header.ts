import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { Component, inject, model, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
} from '@angular/material/dialog';
import { ChangePasswordDialog } from '../change-password-dialog/change-password-dialog';
import { Router, RouterLink } from "@angular/router";
import { HttpClient } from '@angular/common/http';
import { finalize } from 'rxjs';
import { Auth } from '../../../services/auth';

@Component({
  selector: 'app-header',
  templateUrl: './header.html',
  styleUrl: './header.scss',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatMenuModule, RouterLink],
})
export class Header {
  readonly dialog = inject(MatDialog);
  readonly animal = signal('');
  readonly name = model('');
  private http = inject(HttpClient);
  private auth = inject(Auth)
  private router = inject(Router);

  openChangePasswordDialog(): void {
    const dialogRef = this.dialog.open(ChangePasswordDialog, {
      data: { name: this.name(), animal: this.animal() },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result !== undefined) {
        this.animal.set(result);
      }
    });
  }

  logout(): void {
    this.http.post('logout', {}).pipe(
      finalize(()=>{
        this.auth.authenticated = false;
        this.router.navigateByUrl('/login');
      })
    ).subscribe();
  }
}