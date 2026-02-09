import { Component, inject, model } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ExamService } from '../../../services/exam';
import { Exam } from '../../../models/exam';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-add-button-dialog',
  templateUrl: './add-button-dialog.html',
  styleUrl: './add-button-dialog.scss',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  ],
})
export class AddButtonDialog {

  constructor(private examService: ExamService) { }

  readonly dialogRef = inject(MatDialogRef<AddButtonDialog>);

  newExam: Exam = {
    name: '',
    cpf: '',
    data: '',
    type: '' as any,
    status: '' as any,
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

  onCreateClick(): void {
    this.examService.createExam(this.newExam).subscribe({
      next: (res) => {
        console.log('Backend respondeu:', res); // Debug 1
        this.dialogRef.close(res);
      },
      error: (err) => console.error('Erro:', err)
    });
  }
}
