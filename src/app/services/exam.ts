import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Exam } from '../models/exam';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ExamService {

  private apiUrl = 'http://localhost:8080/api/exams';

  constructor(private http:HttpClient){}
  
  getExams(): Observable<Exam[]>{
    return this.http.get<Exam[]>(this.apiUrl);
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

}
