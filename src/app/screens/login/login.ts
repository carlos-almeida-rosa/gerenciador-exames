import { Component } from '@angular/core';
import { LoginForm } from '../../components/authentication-components/login-form/login-form';

@Component({
  selector: 'app-login',
  templateUrl: './login.html',
  styleUrl: './login.scss',
  imports: [LoginForm],
})
export class Login {

}
