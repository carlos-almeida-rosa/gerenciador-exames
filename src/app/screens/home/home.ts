import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from '../../components/home-components/header/header';
import { ExamCard } from '../../components/home-components/exam-card/exam-card';
import { AddButton } from '../../components/home-components/add-button/add-button';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrl: './home.scss',
  imports: [RouterOutlet, Header, ExamCard, AddButton],
})
export class Home {

}
