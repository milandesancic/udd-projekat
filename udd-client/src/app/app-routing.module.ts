import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UploadDocumentComponent } from './component/upload-document/upload-document.component';
import { SearchComponent } from './component/search/search.component';

const routes: Routes = [
  { path: 'add', component: UploadDocumentComponent },
  { path: '', pathMatch: 'full', redirectTo: 'add' },
  { path: 'search', component: SearchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
