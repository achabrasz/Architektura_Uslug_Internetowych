import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ElementService {
  private apiUrl = 'http://localhost:8083/sportsmen'; // Gateway endpoint for elements

  constructor(private http: HttpClient) {}

  getElementsByCategory(categoryId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${categoryId}`);
  }

  getElementById(categoryId: string, elementId: string): Observable<any> {
    console.log(`${this.apiUrl}/${categoryId}/${elementId}`);
    return this.http.get(`${this.apiUrl}/${categoryId}/${elementId}`);
  }

  createElement(categoryId: string, element: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/${categoryId}`, element);
  }

  updateElement(categoryId: string, elementId: string, element: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${categoryId}/${elementId}`, element);
  }

  deleteElement(categoryId: string, elementId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${categoryId}/${elementId}`);
  }
}
