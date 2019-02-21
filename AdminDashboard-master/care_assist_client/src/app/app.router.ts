import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
// import { ServicesComponent } from './services/services.component';

export const router: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full'},
    { path: 'home', component: HomeComponent },
    // { path: 'services', component: ServicesComponent }
];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);
