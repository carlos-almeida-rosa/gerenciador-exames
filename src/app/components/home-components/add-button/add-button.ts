import { ChangeDetectionStrategy, Component, inject, model, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AddButtonDialog } from '../add-button-dialog/add-button-dialog';
import { ExamService } from '../../../services/exam';
import { Exam } from '../../../models/exam';

@Component({
  selector: 'app-add-button',
  templateUrl: './add-button.html',
  styleUrl: './add-button.scss',
  imports: [MatButtonModule, MatDividerModule, MatIconModule, MatFormFieldModule, MatInputModule, FormsModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class AddButton {
  readonly dialog = inject(MatDialog);
  private examService = inject(ExamService);

  openAddExamDialog(): void {
    const dialogRef = this.dialog.open(AddButtonDialog, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe((result: Exam | undefined) => {
      if (result) {
        this.examService.examList.update(listaAtual => [...listaAtual, result]);
      }
    });
  }
}
