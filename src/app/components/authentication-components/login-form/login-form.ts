import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { EmailInput } from '../email-input/email-input';
import { PasswordInput } from '../password-input/password-input';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
  imports: [MatCardModule, MatButtonModule, EmailInput, PasswordInput],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginForm {

}
