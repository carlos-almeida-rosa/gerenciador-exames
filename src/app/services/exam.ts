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

}
