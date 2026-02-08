
import { MatCardModule } from '@angular/material/card';
import { ChangeDetectionStrategy, Component, inject, model, OnInit, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
} from '@angular/material/dialog';
import { OpenCardInfoButtonDialog } from '../open-card-info-button-dialog/open-card-info-button-dialog';
import { DeleteCardButtonDialog } from '../delete-card-button-dialog/delete-card-button-dialog';
import { ExamService } from '../../../services/exam';
import { Exam } from '../../../models/exam';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-exam-card',
  templateUrl: './exam-card.html',
  styleUrl: './exam-card.scss',
  imports: [CommonModule, MatCardModule, MatButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class ExamCard implements OnInit {

  readonly dialog = inject(MatDialog);
  readonly animal = signal('');
  readonly name = model('');
  listaDeExames = signal<Exam[]>([]);

  constructor(private examService: ExamService) { }
  
  ngOnInit(): void {
    this.examService.getExams().subscribe(
      (res) => {
        this.listaDeExames.set(res);
        console.log(res);
      },
      (error) => {
        console.log('Erro ao buscar exames: ', error);
      }
    );
  }

  openDeleteDialog(enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(DeleteCardButtonDialog, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
    });
  }

  openInfoExamDialog(): void {
    const dialogRef = this.dialog.open(OpenCardInfoButtonDialog, {
      data: { name: this.name(), animal: this.animal() },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if (result !== undefined) {
        this.animal.set(result);
      }
    });
  }
}


