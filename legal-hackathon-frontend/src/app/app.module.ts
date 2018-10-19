import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatToolbarModule,
  MatTooltipModule
} from '@angular/material';
import {ProfileComponent} from './profile/profile.component';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {RowComponent} from './profile/row/row.component';
import {FormsModule} from '@angular/forms';
import {BrowserComponent} from './browser/browser.component';
import {HttpClientModule} from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'browser', component: BrowserComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    HomeComponent,
    RowComponent,
    BrowserComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes, {useHash: true}
    ),
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatToolbarModule,
    MatTooltipModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
