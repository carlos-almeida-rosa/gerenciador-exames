import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
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
export class LoginForm {

  loginEmail = signal('');
  loginPassword = signal('');
  private auth = inject(Auth);
  private router = inject(Router);
  error = false;
  credentials = {
    email: '',
    password: ''
  }

  constructor() {
    this.auth.authenticate(undefined, undefined);
  }

  login(): boolean {
    this.credentials.email = this.loginEmail();
    this.credentials.password = this.loginPassword();
    // console.log('Email:', this.credentials.email);
    // console.log('Senha:', this.credentials.password);
    // if (this.credentials.email === 'my-email@gmail.com' && this.credentials.password === '123456') {
    //   this.router.navigate(['/home']);
    // }
    this.auth.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/home');
    });
    return false;
  }

}
