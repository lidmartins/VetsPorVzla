import { Routes } from '@angular/router';
import { authGuard } from './core/auth/auth.guard';

export const routes: Routes = [
  // PUBLIC
  { path: '',
    loadComponent: () => import('./features/public/home/home.component').then(m => m.HomeComponent) },
  { path: 'perdidas',
    loadComponent: () => import('./features/public/lost/lost.component').then(m => m.LostComponent) },
  { path: 'encontradas',
    loadComponent: () => import('./features/public/found/found.component').then(m => m.FoundComponent) },
  { path: 'refugios',
    loadComponent: () => import('./features/public/shelters/shelters.component').then(m => m.SheltersComponent) },
  { path: 'adopciones',
    loadComponent: () => import('./features/public/adoptions/adoptions.component').then(m => m.AdoptionsComponent) },
  { path: 'quienes-somos',
    loadComponent: () => import('./features/public/about/about.component').then(m => m.AboutComponent) },

  // AUTH
  { path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent) },

  // VET (protected by authGuard)
  {
    path: 'vet',
    canActivate: [authGuard],
    loadComponent: () => import('./features/vet/vet-layout/vet-layout.component').then(m => m.VetLayoutComponent),
    children: [
      { path: '',            redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard',
        loadComponent: () => import('./features/vet/dashboard/dashboard.component').then(m => m.DashboardComponent) },
      { path: 'solicitudes',
        loadComponent: () => import('./features/vet/solicitudes/solicitudes.component').then(m => m.SolicitudesComponent) },
      { path: 'animales',
        loadComponent: () => import('./features/vet/animales/animales.component').then(m => m.AnimalesComponent) },
      { path: 'refugios',
        loadComponent: () => import('./features/vet/refugios/refugios-vet.component').then(m => m.RefugiosVetComponent) },
    ]
  },

  { path: '**', redirectTo: '' }
];
