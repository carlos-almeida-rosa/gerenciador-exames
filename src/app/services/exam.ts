import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Exam } from '../models/exam';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ExamService {

  private apiUrl = 'http://localhost:8080/api/exams';

  constructor(private http:HttpClient){}

  examList = signal<Exam[]>([]);
  
  loadExams(): void{
    this.http.get<Exam[]>(this.apiUrl).subscribe({
      next: (res) => {
        this.examList.set(res);
      },
      error: (err) => console.error('Erro ao buscar: ', err)
    });
  }

  updateExam(exam: Exam): Observable<Exam> {
    const url = `${this.apiUrl}?id=${exam.id}`;
    return this.http.put<Exam>(url, exam);
  }

  deleteExam(id: number): Observable<void>{
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  createExam(exam: Exam): Observable<Exam>{
    return this.http.post<Exam>(this.apiUrl, exam);
  }

  updateSignal(updatedExam: Exam) {
    this.examList.update(currentList => 
      currentList.map(item => 
        item.id === updatedExam.id ? updatedExam : item
      )
    );
  }

  deleteFromSignal(id: number) {
  this.examList.update(currentList => 
    currentList.filter(item => item.id !== id)
  );
}

}
