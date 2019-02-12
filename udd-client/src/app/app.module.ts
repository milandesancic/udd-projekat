import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UploadDocumentComponent } from './component/upload-document/upload-document.component';
import { SearchComponent } from './component/search/search.component';
import { GeoSearchComponent } from './component/geo-search/geo-search.component';
import { MltSearchComponent } from './component/mlt-search/mlt-search.component';
import { BoolSearchComponent } from './component/bool-search/bool-search.component';

@NgModule({
  declarations: [
    AppComponent,
    UploadDocumentComponent,
    SearchComponent,
    GeoSearchComponent,
    MltSearchComponent,
    BoolSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
