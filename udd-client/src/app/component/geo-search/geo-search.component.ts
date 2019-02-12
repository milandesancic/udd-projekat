import { Component, OnInit } from '@angular/core';
import { Observable, observable } from 'rxjs';
import { SearchService } from 'src/app/service/search.service';


@Component({
  selector: 'app-geo-search',
  templateUrl: './geo-search.component.html',
  styleUrls: ['./geo-search.component.css']
})

export class GeoSearchComponent implements OnInit {


  recenzencit;
  constructor(private searchService:SearchService) { }

  ngOnInit() {
  }


   submit(){
    let data={};
    data['longitude'] =44.7722;
    data['latitude'] = 17.1910;
    console.log(data);
    this.searchService.geo(data).subscribe(data=>{
      console.log(data);
      this.recenzencit = data;
    })

  }
}
