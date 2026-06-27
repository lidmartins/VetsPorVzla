# Veterinarios por Venezuela — Angular Architecture

## Tech Stack
- **Angular 17+** (standalone components, no NgModules)
- **Angular Router** with lazy-loaded feature routes
- **Angular Signals** for reactive local state
- **SCSS** with CSS custom properties (same design tokens)
- **HttpClient** for REST calls to Spring Boot backend

---

## Folder Structure

```
src/
├── app/
│   ├── core/
│   │   ├── auth/
│   │   │   ├── auth.service.ts
│   │   │   ├── auth.guard.ts
│   │   │   └── auth.interceptor.ts
│   │   └── services/
│   │       ├── animal.service.ts
│   │       ├── solicitud.service.ts
│   │       ├── refugio.service.ts
│   │       └── estado.service.ts
│   │
│   ├── shared/
│   │   ├── components/
│   │   │   ├── header/
│   │   │   ├── footer/
│   │   │   ├── alert-banner/
│   │   │   ├── pagination/
│   │   │   ├── status-pill/
│   │   │   ├── pet-card/
│   │   │   ├── shelter-card/
│   │   │   └── modal/
│   │   ├── pipes/
│   │   │   ├── animal-type.pipe.ts
│   │   │   ├── vet-status.pipe.ts
│   │   │   └── time-ago.pipe.ts
│   │   └── models/
│   │       ├── animal.model.ts
│   │       ├── solicitud.model.ts
│   │       ├── refugio.model.ts
│   │       ├── contacto.model.ts
│   │       ├── ubicacion.model.ts
│   │       └── user.model.ts
│   │
│   ├── features/
│   │   ├── public/
│   │   │   ├── public.routes.ts
│   │   │   ├── home/
│   │   │   ├── lost/
│   │   │   ├── found/
│   │   │   ├── shelters/
│   │   │   ├── adoptions/
│   │   │   ├── about/
│   │   │   └── report-modal/
│   │   │
│   │   ├── auth/
│   │   │   ├── auth.routes.ts
│   │   │   └── login/
│   │   │
│   │   └── vet/
│   │       ├── vet.routes.ts
│   │       ├── vet-layout/
│   │       ├── dashboard/
│   │       ├── solicitudes/
│   │       │   └── solicitud-detail/
│   │       ├── animales/
│   │       │   └── animal-detail/
│   │       └── refugios/
│   │           └── refugio-detail/
│   │
│   ├── app.component.ts
│   ├── app.component.html
│   ├── app.component.scss
│   └── app.routes.ts
│
├── assets/
│   ├── background.png
│   └── logo.svg
│
└── styles/
    ├── _variables.scss
    ├── _typography.scss
    ├── _layout.scss
    ├── _buttons.scss
    ├── _cards.scss
    ├── _modal.scss
    ├── _table.scss
    ├── _pagination.scss
    ├── _badges.scss
    ├── _forms.scss
    └── styles.scss
```

---

## Routing (`app.routes.ts`)

```typescript
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
```

---

## Models (`shared/models/`)

```typescript
// animal.model.ts
export interface Animal {
  an_cd_animal:             number;
  an_re_cd_refugio?:        number;
  an_nm_animal?:            string;
  an_tp_animal:             'G' | 'P';
  an_de_breed?:             string;
  an_de_color:              string;
  an_tp_size:               'P' | 'M' | 'G';
  an_tp_sex:                'M' | 'H';
  an_nu_approx_age?:        number;
  an_de_animal:             string;
  an_in_require_vet_review: 'S' | 'N';
  an_st_vet_review:         'P' | 'A' | 'R';
  an_dt_created:            string;
  an_dt_updated:            string;
}

// solicitud.model.ts
export interface Solicitud {
  so_cd_solicitud:        number;
  so_an_cd_animal:        number;
  so_co_cd_contacto:      number;
  so_ur_cd_ubicacion:     number;
  so_tp_solicitud:        'P' | 'E';
  so_dt_evento:           string;
  so_st_solicitud:        'P' | 'R' | 'A';
  so_de_observacion_vet?: string;
  so_de_s3_folder_path:   string;
  so_de_main_photo_url:   string;
  so_dt_created:          string;
  so_dt_updated:          string;
  contacto?:              Contacto;
  animal?:                Animal;
  ubicacion?:             Ubicacion;
}

// refugio.model.ts
export interface Refugio {
  re_cd_refugio:              number;
  re_cd_contacto:             number;
  re_ur_cd_ubicacion:         number;
  re_nm_refugio:              string;
  re_st_refugio:              'P' | 'A' | 'X' | 'I' | 'R';
  re_nu_capacity_total:       number;
  re_nu_capacity_available:   number;
  re_tp_species_allowed:      'G' | 'P' | 'A';
  re_tp_animal_special_needs?: 'AH' | 'CA' | 'AM';
  re_in_has_pets:             'S' | 'N';
  re_tp_housing:              'CP' | 'CS' | 'AP';
  re_in_fence_housing:        'C' | 'P' | 'N';
  re_de_additional_note?:     string;
  re_de_observacion_vet?:     string;
  re_dt_created:              string;
  re_dt_updated:              string;
}

// user.model.ts
export interface User {
  us_cd_user:          number;
  us_ro_cd_role:       number;
  us_nm_first_name:    string;
  us_nm_last_name:     string;
  us_de_email:         string;
  us_de_phone:         string;
  us_in_veterinarian:  'S' | 'N';
  us_st_user:          'A' | 'I' | 'B';
}

// pagination.model.ts
export interface Page<T> {
  content:       T[];
  totalElements: number;
  totalPages:    number;
  number:        number;   // current page (0-based from Spring)
  size:          number;
}
```

---

## Auth Service (`core/auth/auth.service.ts`)

```typescript
import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly TOKEN_KEY = 'vpv_token';
  private _isLoggedIn = signal(!!localStorage.getItem(this.TOKEN_KEY));
  readonly isLoggedIn = this._isLoggedIn.asReadonly();

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<void> {
    return this.http
      .post<{ token: string }>(`${environment.apiUrl}/auth/login`, { email, password })
      .pipe(
        tap(res => {
          localStorage.setItem(this.TOKEN_KEY, res.token);
          this._isLoggedIn.set(true);
        }),
        map(() => void 0)
      );
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this._isLoggedIn.set(false);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }
}
```

---

## Auth Guard (`core/auth/auth.guard.ts`)

```typescript
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = () => {
  const auth   = inject(AuthService);
  const router = inject(Router);
  return auth.isLoggedIn() ? true : router.createUrlTree(['/login']);
};
```

---

## Auth Interceptor (`core/auth/auth.interceptor.ts`)

```typescript
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = inject(AuthService).getToken();
  if (!token) return next(req);
  return next(req.clone({
    setHeaders: { Authorization: `Bearer ${token}` }
  }));
};
```

---

## Example Service (`core/services/solicitud.service.ts`)

```typescript
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitud } from '../../shared/models/solicitud.model';
import { Page } from '../../shared/models/pagination.model';
import { environment } from '../../../environments/environment';

export interface SolicitudFilter {
  tp?: 'P' | 'E' | '';
  st?: 'P' | 'R' | 'A' | '';
  page?: number;
  size?: number;
}

@Injectable({ providedIn: 'root' })
export class SolicitudService {
  private base = `${environment.apiUrl}/solicitudes`;

  constructor(private http: HttpClient) {}

  getAll(filter: SolicitudFilter = {}): Observable<Page<Solicitud>> {
    let params = new HttpParams()
      .set('page',  (filter.page ?? 0).toString())
      .set('size',  (filter.size ?? 5).toString());
    if (filter.tp) params = params.set('tp', filter.tp);
    if (filter.st) params = params.set('st', filter.st);
    return this.http.get<Page<Solicitud>>(this.base, { params });
  }

  getById(id: number): Observable<Solicitud> {
    return this.http.get<Solicitud>(`${this.base}/${id}`);
  }

  updateStatus(id: number, st: string, obs: string): Observable<Solicitud> {
    return this.http.patch<Solicitud>(`${this.base}/${id}/status`, { so_st_solicitud: st, so_de_observacion_vet: obs });
  }
}
```

---

## Pagination Component (`shared/components/pagination/`)

```typescript
// pagination.component.ts
import { Component, Input, Output, EventEmitter, computed, input } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [NgFor, NgIf],
  template: `
    <div class="pgn" *ngIf="pages() > 1">
      <button class="pgn-btn" [disabled]="page <= 1" (click)="go(page - 1)">‹</button>
      @for (p of pageNumbers(); track p) {
        @if (p === -1) { <span class="pgn-info">…</span> }
        @else {
          <button class="pgn-btn" [class.pgn-active]="p === page" (click)="go(p)">{{ p }}</button>
        }
      }
      <button class="pgn-btn" [disabled]="page >= pages()" (click)="go(page + 1)">›</button>
      <span class="pgn-info">{{ from() }}–{{ to() }} de {{ total }}</span>
    </div>
  `
})
export class PaginationComponent {
  @Input() page    = 1;
  @Input() total   = 0;
  @Input() perPage = 5;
  @Output() pageChange = new EventEmitter<number>();

  pages      = computed(() => Math.ceil(this.total / this.perPage) || 1);
  from       = computed(() => (this.page - 1) * this.perPage + 1);
  to         = computed(() => Math.min(this.page * this.perPage, this.total));
  pageNumbers = computed(() => {
    const total = this.pages();
    const cur   = this.page;
    const nums: number[] = [];
    for (let p = 1; p <= total; p++) {
      if (total <= 7 || p <= 2 || p >= total - 1 || Math.abs(p - cur) <= 1) {
        nums.push(p);
      } else if (nums[nums.length - 1] !== -1) {
        nums.push(-1); // ellipsis
      }
    }
    return nums;
  });

  go(p: number) { this.pageChange.emit(p); }
}
```

---

## VetLayout Shell (`features/vet/vet-layout/`)

```typescript
// vet-layout.component.ts
@Component({
  selector: 'app-vet-layout',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './vet-layout.component.html',
  styleUrl: './vet-layout.component.scss'
})
export class VetLayoutComponent {
  private auth = inject(AuthService);
  logout() { this.auth.logout(); }
}
```

```html
<!-- vet-layout.component.html -->
<div class="vetlayout wrap">
  <aside class="sidebar">
    <div class="sb-user">
      <div class="av">🩺</div>
      <div><b>Dra. Patricia Salas</b><small>MV · Aragua</small></div>
    </div>
    <nav class="sb-menu">
      <a class="sb-item" routerLink="dashboard"    routerLinkActive="active">📊 Panel</a>
      <a class="sb-item" routerLink="solicitudes"  routerLinkActive="active">📥 Solicitudes</a>
      <a class="sb-item" routerLink="animales"     routerLinkActive="active">🐾 Animales</a>
      <a class="sb-item" routerLink="refugios"     routerLinkActive="active">🏠 Refugios</a>
    </nav>
    <button class="sb-exit" (click)="logout()">← Salir del panel</button>
  </aside>
  <main><router-outlet /></main>
</div>
```

---

## Example Vet Solicitudes Component

```typescript
// solicitudes.component.ts
@Component({ selector: 'app-solicitudes', standalone: true, ... })
export class SolicitudesComponent {
  private svc = inject(SolicitudService);

  filterTp = signal<string>('');
  filterSt = signal<string>('');
  page     = signal(1);
  perPage  = 5;

  // Reacts to any filter/page change
  result = toSignal(
    toObservable(computed(() => ({ tp: this.filterTp(), st: this.filterSt(), page: this.page() - 1, size: this.perPage })))
      .pipe(switchMap(f => this.svc.getAll(f))),
    { initialValue: null }
  );

  selectedId = signal<number | null>(null);

  openDetail(id: number) { this.selectedId.set(id); }
  closeDetail()          { this.selectedId.set(null); }
  onPageChange(p: number){ this.page.set(p); }
}
```

```html
<!-- solicitudes.component.html -->
<div class="vmain-head">
  <div><h1>Solicitudes</h1><p>Revisión de reportes enviados por el público</p></div>
  <div class="filters">
    <select (change)="filterTp.set($any($event.target).value); page.set(1)">
      <option value="">Todos los tipos</option>
      <option value="P">🔴 Perdidos</option>
      <option value="E">🟢 Encontrados</option>
    </select>
    <select (change)="filterSt.set($any($event.target).value); page.set(1)">
      <option value="">Todos los estados</option>
      <option value="P">⏳ Pendientes</option>
      <option value="A">✅ Activas</option>
      <option value="R">❌ Rechazadas</option>
    </select>
  </div>
</div>

@if (result(); as data) {
  <div class="panel">
    <div class="panel-head">
      <h3>Listado</h3>
      <span class="pill-pending">{{ data.totalElements }} registros</span>
    </div>
    <div class="table-scroll">
      <table class="vtable">
        <thead><tr class="vthead">...</tr></thead>
        <tbody>
          @for (sol of data.content; track sol.so_cd_solicitud) {
            <tr class="vtrow" (click)="openDetail(sol.so_cd_solicitud)">...</tr>
          }
        </tbody>
      </table>
    </div>
    <app-pagination
      [page]="page()"
      [total]="data.totalElements"
      [perPage]="perPage"
      (pageChange)="onPageChange($event)" />
  </div>
}

@if (selectedId()) {
  <app-solicitud-detail [id]="selectedId()!" (close)="closeDetail()" />
}
```

---

## SCSS Global Styles (`styles/styles.scss`)

```scss
@use 'variables';
@use 'typography';
@use 'layout';
@use 'buttons';
@use 'cards';
@use 'modal';
@use 'table';
@use 'pagination';
@use 'badges';
@use 'forms';

// Google Fonts
@import url('https://fonts.googleapis.com/css2?family=Sora:wght@500;600;700;800&family=Source+Sans+3:wght@400;500;600;700&display=swap');

* { box-sizing: border-box; margin: 0; padding: 0; }

html { scroll-behavior: smooth; height: 100%; }

body {
  font-family: 'Source Sans 3', system-ui, sans-serif;
  background: var(--bg);
  color: var(--ink);
  line-height: 1.5;
  -webkit-font-smoothing: antialiased;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

h1, h2, h3, h4 { font-family: 'Sora', sans-serif; letter-spacing: -0.01em; }
button { font-family: inherit; cursor: pointer; border: none; background: none; }
input, select, textarea { font-family: inherit; }
```

---

## Angular CLI Scaffold Commands

```bash
# Create project
ng new vpv --standalone --style=scss --routing

# Shared components
ng g c shared/components/header        --standalone --skip-tests
ng g c shared/components/footer        --standalone --skip-tests
ng g c shared/components/alert-banner  --standalone --skip-tests
ng g c shared/components/pagination    --standalone --skip-tests
ng g c shared/components/status-pill   --standalone --skip-tests
ng g c shared/components/pet-card      --standalone --skip-tests
ng g c shared/components/shelter-card  --standalone --skip-tests
ng g c shared/components/modal         --standalone --skip-tests

# Pipes
ng g pipe shared/pipes/animal-type  --standalone --skip-tests
ng g pipe shared/pipes/vet-status   --standalone --skip-tests
ng g pipe shared/pipes/time-ago     --standalone --skip-tests

# Services + Auth
ng g s core/services/animal       --skip-tests
ng g s core/services/solicitud    --skip-tests
ng g s core/services/refugio      --skip-tests
ng g s core/services/estado       --skip-tests
ng g s core/auth/auth             --skip-tests
ng g guard core/auth/auth         --skip-tests
ng g interceptor core/auth/auth   --skip-tests

# Public pages
ng g c features/public/home          --standalone --skip-tests
ng g c features/public/lost          --standalone --skip-tests
ng g c features/public/found         --standalone --skip-tests
ng g c features/public/shelters      --standalone --skip-tests
ng g c features/public/adoptions     --standalone --skip-tests
ng g c features/public/about         --standalone --skip-tests
ng g c features/public/report-modal  --standalone --skip-tests

# Auth page
ng g c features/auth/login --standalone --skip-tests

# Vet pages
ng g c features/vet/vet-layout                   --standalone --skip-tests
ng g c features/vet/dashboard                    --standalone --skip-tests
ng g c features/vet/solicitudes                  --standalone --skip-tests
ng g c features/vet/solicitudes/solicitud-detail --standalone --skip-tests
ng g c features/vet/animales                     --standalone --skip-tests
ng g c features/vet/animales/animal-detail       --standalone --skip-tests
ng g c features/vet/refugios                     --standalone --skip-tests
ng g c features/vet/refugios/refugio-detail      --standalone --skip-tests
```

---

## Key Design Decisions

| Decision | Why |
|---|---|
| **Standalone components** | Angular 17+ best practice — no NgModules boilerplate |
| **Lazy loading every route** | Public users never download the vet bundle |
| **Signals** | Replace `var page=1` / `renderLost()` with reactive, declarative state |
| **`Page<T>` from Spring** | Spring Pageable returns `content`, `totalElements`, `number`, `size` — matches directly |
| **`authGuard` on `/vet/**`** | All vet routes protected; matches current `isLoggedIn` logic |
| **SCSS partials** | 600-line `<style>` split into logical files; each component only imports what it needs |
| **Pipes for code→label** | `'P' \| vetStatus` → `'Pendiente'` instead of inline ternaries everywhere |
| **CSS custom props kept** | Same `--teal`, `--coral`, etc. — zero visual change for the end user |
| **HTTP interceptor** | Auto-attaches `Authorization: Bearer` to every API call |
| **`environment.ts`** | `apiUrl: 'http://localhost:8080/api'` dev vs AWS URL prod |
