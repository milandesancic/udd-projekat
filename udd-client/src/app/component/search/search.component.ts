import { Component, OnInit } from '@angular/core';
import { Field } from 'src/app/model/field';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SearchService } from 'src/app/service/search.service';
import { UploadService } from 'src/app/service/upload.service';
import { saveAs } from "file-saver";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  formFields: Array<Field>;
  searchMode = 'text';
  searchSelect;
  searchValue;

  oblasti = ['IT', 'Fizika', 'Matematika', 'Medicina', 'Sport', 'Programiranja'];
  magazines = ['IT Svet', 'Sport Klub', 'FTN', 'PMF'];


  searchForm : FormGroup;
  constructor(private searchService:SearchService,private uploadService:UploadService) { }

  ngOnInit() {
    this.formFields = this.populateFormField();
    this.searchForm = this.makeForm();
  }

  makeForm():FormGroup{
    let retValue= new FormGroup({
      field: new FormControl("text",Validators.required),
      value: new FormControl("",Validators.required)
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
    this.searchForm.value['value']="";
    if (value === 'category' || value === 'magazine') {
      if(value ==='category'){
        this.searchSelect = this.oblasti;
      }
      if(value==='magazine'){
        this.searchSelect = this.magazines;
      }
      this.searchMode = 'keyword';
    }else{
      this.searchMode = 'text';
    }
  }

  standardSearch(){
    console.log("sandard");

    console.log(this.searchForm.value);
    this.searchService.standardSearch(this.searchForm.value).subscribe((data)=>{
      console.log(data);
      this.searchValue = data;
    },
    (error)=>{
      console.log(error);
    });
  }

  pharaseSearch(){
    console.log("fraza");
    console.log(this.searchForm.value);
    this.searchService.phraseSearch(this.searchForm.value).subscribe((data)=>{
      console.log(data);
      this.searchValue = data;
    },
    (error)=>{
      console.log(error);
    });
  }


  download(path,title){
    console.log("ovdie sam");
    this.uploadService.download(path).subscribe(data=>{
      console.log(data);
      var blob = new Blob([data], { type: 'application/pdf' })
      saveAs(blob,title)
    })
  }
}
