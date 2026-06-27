import { Component, Input, Output, EventEmitter, OnInit, inject } from '@angular/core';
import { SlicePipe } from '@angular/common';
import { SolicitudService } from '../../../../core/services/solicitud.service';
import { Solicitud } from '../../../../shared/models/solicitud.model';

@Component({
  selector: 'app-solicitud-detail',
  imports: [SlicePipe],
  templateUrl: './solicitud-detail.component.html',
  styleUrl: './solicitud-detail.component.scss'
})
export class SolicitudDetailComponent implements OnInit {
  @Input({ required: true }) id!: number;
  @Output() close = new EventEmitter<void>();

  private svc = inject(SolicitudService);

  solicitud: Solicitud | null = null;
  loading = true;
  error = '';

  ngOnInit() {
    this.svc.getById(this.id).subscribe({
      next:  s  => { this.solicitud = s; this.loading = false; },
      error: () => { this.error = 'No se pudo cargar la solicitud.'; this.loading = false; },
    });
  }

  typeLabel(tp: 'P' | 'E')         { return tp === 'P' ? '🔴 Perdida' : '🟢 Encontrada'; }
  statusLabel(st: 'P' | 'R' | 'A') { return st === 'P' ? 'Pendiente' : st === 'A' ? 'Activa' : 'Rechazada'; }
}
