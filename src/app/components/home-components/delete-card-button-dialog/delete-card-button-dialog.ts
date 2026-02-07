import { Component, ChangeDetectionStrategy,inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-delete-card-button-dialog',

  templateUrl: './delete-card-button-dialog.html',
  styleUrl: './delete-card-button-dialog.scss',
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DeleteCardButtonDialog {
  readonly dialogRef = inject(MatDialogRef<DeleteCardButtonDialog>);
}