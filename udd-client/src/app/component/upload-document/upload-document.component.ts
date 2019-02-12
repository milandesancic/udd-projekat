import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UploadService } from 'src/app/service/upload.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload-document',
  templateUrl: './upload-document.component.html',
  styleUrls: ['./upload-document.component.css']
})
export class UploadDocumentComponent implements OnInit {


  uploadDocument: FormGroup;
  mode = 'addAutor';
  oblasti = ['IT', 'Fizika', 'Matematika', 'Medicina', 'Sport', 'Programiranja'];
  magazines = ['IT Svet', 'Sport Klub', 'FTN', 'PMF'];

  formData = new FormData();
  autorForm: FormGroup;
  autors = new Array();
  constructor(private uploadService: UploadService,private router:Router) { }

  ngOnInit() {
    this.uploadDocument = this.makeUploadForm();
    this.autorForm = this.makeAutorForm();
    console.log(this.oblasti);
  }

  makeAutorForm() {
    let form = new FormGroup({
      firstname: new FormControl("", Validators.required),
      lastname: new FormControl("", Validators.required),
      email :new FormControl("",Validators.required),
      state: new FormControl("", Validators.required),
      city: new FormControl("", Validators.required),
    });
    return form;
  }

  makeUploadForm() {
    let form = new FormGroup({
      title: new FormControl("", Validators.required),
      keywords: new FormControl("", Validators.required),
      apstract: new FormControl("", Validators.required),
      category: new FormControl("", Validators.required),
      magazine: new FormControl("", Validators.required)
    });
    return form;
  }

  onFileChange(event: any) {

    console.log(event)
    let fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      this.formData = new FormData();
      this.formData.append('file', file, file.name);
    }
  }

  submitForm() {
    this.formData.append('title', this.uploadDocument.value['title']);
    this.formData.append('magazine', this.uploadDocument.value['magazine']);
    this.formData.append('keywords', this.uploadDocument.value['keywords']);
    this.formData.append('apstract', this.uploadDocument.value['apstract']);
    this.formData.append('category', this.uploadDocument.value['category']);
    this.formData.append('jsonAutors',JSON.stringify(this.autors));
    console.log(this.autorForm.value);
    this.uploadService.uploadFile(this.formData).subscribe((data) => {
      console.log(data);
      alert("Uspesno dodan rad");
      this.router.navigate(["/"]);
    },
      (error) => {
        console.log(error);
      }
    )
  }

  addAutor(){
    this.autors.push(this.autorForm.value);
    this.autorForm = this.makeAutorForm();
  }

  nextForm(){
    this.autors.push(this.autorForm.value);
    console.log(this.autors);
    this.mode ="addDocument";
  }


}
