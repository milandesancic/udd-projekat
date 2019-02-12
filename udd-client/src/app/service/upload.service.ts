import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http:HttpClient) { }

  uploadFile(data){
    const url = "http://localhost:8080/index/add";
    
    return this.http.post(url,data);

  }

  download(path){
    const url = "http://localhost:8080/get/?path="+path;
    return this.http.get(url, { responseType: 'blob' });
    
  }
}
