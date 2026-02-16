import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { EmailInput } from '../email-input/email-input';
import { PasswordInput } from '../password-input/password-input';
import { Router, RouterLink } from "@angular/router";
import { HttpClient } from '@angular/common/http';
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

    console.log("🚀 1. Tentando logar com:", email);

    this.authService.login(email, password).subscribe({
      next: (response) => {
        // SUCESSO DO SERVIDOR
        console.log("📦 2. Resposta COMPLETA do Backend:", response);

        if (response && response.token) {
          console.log("🔑 3. Token encontrado:", response.token);

          // Salvando manualmente para garantir
          localStorage.setItem('auth-token', response.token);
          localStorage.setItem('username', response.name);

          console.log("💾 4. Salvo no LocalStorage. Navegando...");
          this.router.navigate(['/home']);
        } else {
          console.error("⚠️ O Backend respondeu, mas NÃO veio token!", response);
        }
      },
      error: (err) => {
        // ERRO DO SERVIDOR
        console.error("❌ Erro na requisição:", err);
        if (err.status === 403 || err.status === 401) {
          alert("Email ou senha incorretos!");
        } else {
          alert("Erro no servidor: " + err.message);
        }
      }
    });
  }
}
