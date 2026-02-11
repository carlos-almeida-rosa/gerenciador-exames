import {ChangeDetectionStrategy, Component, inject, signal} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
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
  private http = inject(HttpClient);
  private router = inject(Router);

  constructor(){
    this.auth.authenticate(undefined, undefined);
  }

  onSubmit(){
    const email = this.loginEmail();
    const password = this.loginPassword();
    console.log('Email:', email);
    console.log('Senha:', password);
  }

}
