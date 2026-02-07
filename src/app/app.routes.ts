import { Routes } from '@angular/router';
import { Home } from './screens/home/home';
import { Login } from './screens/login/login';

export const routes: Routes = [
    {
        path: 'login',
        component: Login
    },
    {
        path: '',
        component: Home
    },
];
