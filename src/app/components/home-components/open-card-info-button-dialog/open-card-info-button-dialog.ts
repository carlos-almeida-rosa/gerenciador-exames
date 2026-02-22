import { Component, inject, model } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Exam } from '../../../models/exam';
import { ExamService } from '../../../services/exam';

@Component({
  selector: 'app-open-card-info-button-dialog',
  templateUrl: './open-card-info-button-dialog.html',
  styleUrl: './open-card-info-button-dialog.scss',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogContent,
    MatDialogActions,
  ],
})
export class OpenCardInfoButtonDialog {

  readonly dialogRef = inject(MatDialogRef<OpenCardInfoButtonDialog>);
  readonly data = inject<Exam>(MAT_DIALOG_DATA);

  editedExam: Exam = {
    cpf: this.data.cpf,
    data: this.data.data,
    name: this.data.name,
    status: this.data.status,
    type: this.data.type,
    id: this.data.id
  }

  constructor(private examService: ExamService) { }

  onCloseClick(): void {
    this.dialogRef.close();
  }

  onUpdateClick(): void {
    this.examService.updateExam(this.editedExam).subscribe({
      next: (res) => {
        this.dialogRef.close(res || this.editedExam);
      },
      error: (err) => console.error(err)
    });
  }
}
