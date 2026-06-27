import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  stats = [
    { ic: '📋', bg: '#FEF2F2', n: 14, l: 'Solicitudes pendientes', delta: '+3 hoy', deltaColor: '#DC4A4A' },
    { ic: '🐾', bg: '#F0FDF4', n: 38, l: 'Animales en refugio',    delta: '+5 hoy', deltaColor: '#2F9E63' },
    { ic: '✅', bg: '#E9F2F0', n: 12, l: 'Reencuentros este mes',  delta: '+2 hoy', deltaColor: '#0F766E' },
    { ic: '🏠', bg: '#F5F3FF', n: 3,  l: 'Refugios activos',       delta: 'Sin cambios', deltaColor: '#9aa49f' },
  ];

  recentRequests = [
    { initials: 'ML', bg: '#e7f5ef', name: 'María López',   email: 'maria@email.com', animal: '🐕 Luna (Golden)',    date: 'Hace 2h',  status: 'Pendiente',  statusBg: '#fdeee9', statusColor: '#F26B4E' },
    { initials: 'CG', bg: '#fdeee9', name: 'Carlos García',  email: 'carlos@email.com', animal: '🐈 Misi (Mestizo)', date: 'Hace 4h',  status: 'En revisión', statusBg: '#FEF3CC', statusColor: '#B45309' },
    { initials: 'AR', bg: '#E9F2F0', name: 'Ana Rodríguez', email: 'ana@email.com',    animal: '🐕 Max (Labrador)', date: 'Hace 8h',  status: 'Aprobado',   statusBg: '#e7f5ef', statusColor: '#2F9E63' },
  ];

  recentAnimals = [
    { ic: '🐕', bg: '#FEF2F2', name: 'Luna',    breed: 'Golden', stBg: '#fdeee9', stColor: '#F26B4E', st: 'Perdida'    },
    { ic: '🐈', bg: '#F5F3FF', name: 'Misi',    breed: 'Mestizo', stBg: '#FEF3CC', stColor: '#B45309', st: 'En refugio' },
    { ic: '🐕', bg: '#F0FDF4', name: 'Coco',    breed: 'Beagle',  stBg: '#e7f5ef', stColor: '#2F9E63', st: 'Encontrado' },
    { ic: '🐕', bg: '#FEF2F2', name: 'Max',     breed: 'Labrador', stBg: '#fdeee9', stColor: '#F26B4E', st: 'Perdido'   },
  ];
}
