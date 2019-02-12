import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  standardSearch(data) {
    const url = "http://localhost:8080/search/standard";
    return this.http.post(url, data);
  }

  phraseSearch(data) {
    const url = "http://localhost:8080/search/phrase";
    return this.http.post(url, data);
  }

  geo(data) {
    const url = "http://localhost:8080/search/geo";
    return this.http.post(url, data);
  }

  mlt(data) {
    const url = "http://localhost:8080/search/more_like_this";
    return this.http.post(url, data);
  }

  bool(data){
    const url = "http://localhost:8080/search/bool";
    return this.http.post(url, data);
  }
}
