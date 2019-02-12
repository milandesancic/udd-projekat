import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { from } from 'rxjs';

@Component({
  selector: 'app-mlt-search',
  templateUrl: './mlt-search.component.html',
  styleUrls: ['./mlt-search.component.css']
})
export class MltSearchComponent implements OnInit {


  searchForm:FormGroup;
  recenzencit;
  constructor(private searchService:SearchService) { }

  ngOnInit() {
    this.searchForm = this.makeForm();
  }


  makeForm(){
    let form = new FormGroup({
      text:new FormControl("",Validators.required)
    })
    return form;
  }

  submit(){
    console.log(this.searchForm.value);
    this.searchService.mlt(this.searchForm.value).subscribe(data=>{
      console.log(data);
      this.recenzencit = data;
    })
  }
}
