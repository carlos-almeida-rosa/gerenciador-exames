import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { EmailInput } from '../email-input/email-input';
import { PasswordInput } from '../password-input/password-input';
import { Router, RouterLink } from "@angular/router";
import { Auth } from '../../../services/auth';
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
  imports: [MatCardModule, MatButtonModule, EmailInput, PasswordInput, RouterLink, FormsModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginForm implements OnInit {

  loginEmail = signal('');
  loginPassword = signal('');
  private authService = inject(Auth);
  private router = inject(Router);

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
          alert("Email ou senha incorretos!");
        } else {
          alert("Erro no servidor: " + err.message);
        }
      }
    });
  }
}
