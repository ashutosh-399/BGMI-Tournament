import { Routes } from '@angular/router';
import { Landing } from './landing/landing';
import { BgmiMatch } from './tournaments/bgmi-match/bgmi-match';
import { adminGuard } from './guards/admin-guard';

export const routes: Routes = [
  
  { path: '', component: Landing, pathMatch: 'full' },

  {
    path: 'auth',
    loadChildren: () =>
      import('./modules/auth/auth-module').then(m => m.AuthModule)
  },
  {
    path: 'login', redirectTo: 'auth/login', pathMatch: 'full'
  },
  {
    path: 'bgmiMatch', component: BgmiMatch, pathMatch: 'full'
  },
  {
    path: 'admin',
    loadComponent: () =>
      import('./admin/dashboard/dashboard').then(m => m.Dashboard),
    canActivate: [adminGuard]
  }

];
