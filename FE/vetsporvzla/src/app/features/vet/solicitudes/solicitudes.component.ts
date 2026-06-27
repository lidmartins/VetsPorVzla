import { Component, computed, inject, signal } from '@angular/core';
import { SlicePipe } from '@angular/common';
import { SolicitudService } from '../../../core/services/solicitud.service';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { switchMap } from 'rxjs';
import { PaginationComponent } from '../../../shared/components/pagination/pagination.component';
import { SolicitudDetailComponent } from './solicitud-detail/solicitud-detail.component';

@Component({
  selector: 'app-solicitudes',
  imports: [PaginationComponent, SolicitudDetailComponent, SlicePipe],
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.scss'
})
export class SolicitudesComponent {
  private svc = inject(SolicitudService);

  filterTp = signal<'' | 'P' | 'E'>('');
  filterSt = signal<'' | 'P' | 'R' | 'A'>('');
  page     = signal(1);
  perPage  = 5;

  result = toSignal(
    toObservable(computed(() => ({
      tp:   this.filterTp(),
      st:   this.filterSt(),
      page: this.page() - 1,
      size: this.perPage,
    }))).pipe(switchMap(f => this.svc.getAll(f))),
    { initialValue: null }
  );

  selectedId = signal<number | null>(null);

  openDetail(id: number)  { this.selectedId.set(id); }
  closeDetail()           { this.selectedId.set(null); }
  onPageChange(p: number) { this.page.set(p); }

  typeLabel(tp: 'P' | 'E')        { return tp === 'P' ? '🔴 Perdida' : '🟢 Encontrada'; }
  typeColor(tp: 'P' | 'E')        { return tp === 'P' ? '#DC4A4A' : '#2F9E63'; }
  typeBg(tp: 'P' | 'E')           { return tp === 'P' ? '#FEF2F2' : '#F0FDF4'; }
  statusLabel(st: 'P' | 'R' | 'A') {
    return st === 'P' ? 'Pendiente' : st === 'A' ? 'Activa' : 'Rechazada';
  }
  statusColor(st: 'P' | 'R' | 'A') {
    return st === 'P' ? '#B45309' : st === 'A' ? '#2F9E63' : '#DC4A4A';
  }
  statusBg(st: 'P' | 'R' | 'A') {
    return st === 'P' ? '#FEF3CC' : st === 'A' ? '#e7f5ef' : '#fdecec';
  }
}
