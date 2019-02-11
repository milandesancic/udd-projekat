import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UploadService } from 'src/app/service/upload.service';

@Component({
  selector: 'app-upload-document',
  templateUrl: './upload-document.component.html',
  styleUrls: ['./upload-document.component.css']
})
export class UploadDocumentComponent implements OnInit {


  uploadDocument: FormGroup;

  oblasti = ['IT', 'Fizika', 'Matematika', 'Medicina', 'Sport', 'Programiranja'];

  formData = new FormData();
  constructor(private uploadService: UploadService) { }

  ngOnInit() {
    this.uploadDocument = this.makeUploadForm();
    console.log(this.oblasti);
  }


  makeUploadForm() {
    let form = new FormGroup({
      title: new FormControl("", Validators.required),
      keywords: new FormControl("", Validators.required),
      apstract: new FormControl("", Validators.required),
      category: new FormControl("", Validators.required)
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
    this.formData.append('title',this.uploadDocument.value['title']);
    this.formData.append('keywords',this.uploadDocument.value['keywords']);
    this.formData.append('apstract',this.uploadDocument.value['apstract']);
    this.formData.append('category',this.uploadDocument.value['category']);
    console.log(this.formData)
    this.uploadService.uploadFile(this.formData).subscribe((data) => {
      console.log(data);
    },
      (error) => {
        console.log(error);
      }
    )
  }
}
