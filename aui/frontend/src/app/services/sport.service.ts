import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SportService {
  private apiUrl = 'http://localhost:8083/sports'; // Gateway endpoint for categories

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<any> {
    console.log(this.apiUrl);
    return this.http.get(`${this.apiUrl}`);
  }

  getCategoryById(id: string): Observable<any> {
    console.log(id);
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  createCategory(category: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, category);
  }

  updateCategory(id: string, category: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, category);
  }

  deleteCategory(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
