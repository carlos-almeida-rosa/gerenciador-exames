import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { EmailInput } from '../email-input/email-input';
import { PasswordInput } from '../password-input/password-input';
import { Router, RouterLink } from "@angular/router";
import { Auth } from '../../../services/auth';
import { FormsModule } from "@angular/forms";
import { ToastrService } from 'ngx-toastr';
import { RestorePasswordDialog } from '../restore-password-dialog/restore-password-dialog';
import { MatDialog } from '@angular/material/dialog';
import { CdkPortalOutlet } from "@angular/cdk/portal";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
  imports: [MatCardModule, MatButtonModule, EmailInput, PasswordInput, RouterLink, FormsModule, CdkPortalOutlet],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginForm implements OnInit {

  loginEmail = signal('');
  loginPassword = signal('');
  private authService = inject(Auth);
  private router = inject(Router);
  toastr = inject(ToastrService)
  readonly dialog = inject(MatDialog)

  constructor() {
    if (localStorage.getItem('auth-token')) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
    const token = localStorage.getItem('auth-token');
    if (token) {
      this.router.navigate(['/home']);
    }
  }

  login() {
    const email = this.loginEmail();
    const password = this.loginPassword();
    this.authService.login(email, password).subscribe({
      next: (response) => {
        if (response && response.token) {
          localStorage.setItem('auth-token', response.token);
          localStorage.setItem('username', response.name);
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        if (err.status === 403 || err.status === 401) {
          this.toastr.error('Email ou senha incorretos!');
        } else {
          this.toastr.error('Erro no servidor: ', err.message);
        }
      }
    });
  }

  openRestorePasswordDialog(): void {
    const dialogRef = this.dialog.open(RestorePasswordDialog);
  }

  openCreateAccoutDialog(): void {
    // const dialogRef = this.dialog.open(RestorePasswordDialog);
    console.log("Implementar dialog de criar conta")
  }

}
