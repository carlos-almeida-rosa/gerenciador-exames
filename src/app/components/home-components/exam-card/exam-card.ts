
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

  constructor(public examService: ExamService) { }

  ngOnInit(): void {
    this.examService.loadExams();
  }

  openDeleteDialog(exam: Exam): void {
    const dialogRef = this.dialog.open(DeleteCardButtonDialog, {
      width: '250px',
      enterAnimationDuration: '0ms',
      exitAnimationDuration: '0ms',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {

        this.examService.deleteExam(exam.id!).subscribe({
          next: () => {
            this.examService.deleteFromSignal(exam.id!);
          },
          error: (err) => console.error('Erro ao deletar:', err)
        });
      }
    });
  }

  openInfoExamDialog(exam: Exam): void {
    const dialogRef = this.dialog.open(OpenCardInfoButtonDialog, {
      data: { ...exam },
    });

    dialogRef.afterClosed().subscribe((result: Exam | undefined) => {
      if (result) {
        this.examService.updateSignal(result);
      }
    });
  }
}