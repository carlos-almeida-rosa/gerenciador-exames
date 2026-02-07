
import { MatCardModule } from '@angular/material/card';
import { ChangeDetectionStrategy, Component, inject, model, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
} from '@angular/material/dialog';
import { OpenCardInfoButtonDialog } from '../open-card-info-button-dialog/open-card-info-button-dialog';
import { DeleteCardButtonDialog } from '../delete-card-button-dialog/delete-card-button-dialog';

@Component({
  selector: 'app-exam-card',
  templateUrl: './exam-card.html',
  styleUrl: './exam-card.scss',
  imports: [MatCardModule, MatButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class ExamCard {
  listaDeExames = [
    { id: 1, nomePaciente: 'Carlos ', status: 'Em andamento' },
    { id: 2, nomePaciente: 'Eduardo ', status: 'Em andamento' },
    { id: 3, nomePaciente: 'Roberta ', status: 'Concluído' },
  ]

  readonly dialog = inject(MatDialog);
  readonly animal = signal('');
  readonly name = model('');

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


