import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UploadDocumentComponent } from './component/upload-document/upload-document.component';
import { SearchComponent } from './component/search/search.component';
import { GeoSearchComponent } from './component/geo-search/geo-search.component';
import { MltSearchComponent } from './component/mlt-search/mlt-search.component';
import { BoolSearchComponent } from './component/bool-search/bool-search.component';

const routes: Routes = [
  { path: 'add', component: UploadDocumentComponent },
  { path: '', pathMatch: 'full', redirectTo: 'add' },
  { path: 'search', component: SearchComponent },
  { path: 'geo/search', component: GeoSearchComponent },
  { path: 'mlt/search', component: MltSearchComponent },
  { path: 'search/bool', component: BoolSearchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
