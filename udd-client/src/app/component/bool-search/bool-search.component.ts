import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Field } from 'src/app/model/field';
import { SearchService } from 'src/app/service/search.service';
import { UploadService } from 'src/app/service/upload.service';
import { saveAs } from "file-saver";

@Component({
  selector: 'app-bool-search',
  templateUrl: './bool-search.component.html',
  styleUrls: ['./bool-search.component.css']
})
export class BoolSearchComponent implements OnInit {


  searchMode = 'text';
  searchMode2 = 'text';
  searchForm: FormGroup;
  oblasti = ['IT', 'Fizika', 'Matematika', 'Medicina', 'Sport', 'Programiranja'];
  magazines = ['IT Svet', 'Sport Klub', 'FTN', 'PMF'];
  formFields: Array<Field>;
  searchSelect;
  searchSelect2;

  searchValue;


  constructor(private searchService: SearchService, private uploadService: UploadService) { }

  ngOnInit() {
    this.searchForm = this.makeForm();
    this.formFields = this.populateFormField();
  }

  makeForm(): FormGroup {
    let retValue = new FormGroup({
      fieldOne: new FormControl("text", Validators.required),
      valueOne: new FormControl("", Validators.required),
      operation: new FormControl("AND", Validators.required),
      fieldSecond: new FormControl("text", Validators.required),
      valueSecond: new FormControl("", Validators.required),

    });
    return retValue;
  }

  private populateFormField(): Array<Field> {
    let retVal = new Array<Field>();
    retVal.push(new Field("Sadrzaj", "text"));
    retVal.push(new Field("Naslov rada", "title"));
    retVal.push(new Field("Kljucne reci", "keywords"));
    retVal.push(new Field("Naucna oblast", "category"));
    retVal.push(new Field("Apstrakt", "document_abstract"));
    retVal.push(new Field("Caspopis", "magazine"));
    retVal.push(new Field("Autor", "autor"));

    return retVal;

  }



  changeSearchField(value) {
    this.searchForm.value['value'] = "";
    if (value === 'category' || value === 'magazine') {
      if (value === 'category') {
        this.searchSelect = this.oblasti;
      }
      if (value === 'magazine') {
        this.searchSelect = this.magazines;
      }
      this.searchMode = 'keyword';
    } else {
      this.searchMode = 'text';
    }
  }

  changeSearchField2(value) {
    this.searchForm.value['value'] = "";
    if (value === 'category' || value === 'magazine') {
      if (value === 'category') {
        this.searchSelect2 = this.oblasti;
      }
      if (value === 'magazine') {
        this.searchSelect2 = this.magazines;
      }
      this.searchMode2 = 'keyword';
    } else {
      this.searchMode2 = 'text';
    }
  }

  search() {
    console.log(this.searchForm.value);
    this.searchService.bool(this.searchForm.value).subscribe((data) => {
      console.log(data);
      this.searchValue = data;
    })
  }

  download(path, title) {
    this.uploadService.download(path).subscribe(data => {
      console.log(data);
      var blob = new Blob([data], { type: 'application/pdf' })
      saveAs(blob, title)
    })
  }

}
