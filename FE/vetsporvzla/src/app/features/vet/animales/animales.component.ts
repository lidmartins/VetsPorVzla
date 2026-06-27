import { Component, signal, computed } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-animales',
  imports: [FormsModule],
  templateUrl: './animales.component.html',
  styleUrl: './animales.component.scss'
})
export class AnimalesComponent {
  search = signal('');
  statusFilter = signal('all');

  animals = [
    { ic: '🐕', bg: '#FEF2F2', name: 'Luna',    breed: 'Golden Retriever', shelter: 'Refugio La Guaira', status: 'Perdida',    stBg: '#fdeee9', stColor: '#F26B4E', date: '2024-01-15' },
    { ic: '🐈', bg: '#F5F3FF', name: 'Misi',    breed: 'Mestizo',          shelter: 'Refugio La Guaira', status: 'En refugio', stBg: '#FEF3CC', stColor: '#B45309', date: '2024-01-14' },
    { ic: '🐕', bg: '#F0FDF4', name: 'Coco',    breed: 'Beagle',           shelter: 'Centro Miranda',    status: 'Encontrado', stBg: '#e7f5ef', stColor: '#2F9E63', date: '2024-01-14' },
    { ic: '🐕', bg: '#FEF2F2', name: 'Max',     breed: 'Labrador',         shelter: 'Refugio Caracas',   status: 'Perdido',    stBg: '#fdeee9', stColor: '#F26B4E', date: '2024-01-13' },
    { ic: '🐈', bg: '#F0FDF4', name: 'Pelusa',  breed: 'Persa',            shelter: 'Refugio La Guaira', status: 'Adoptado',   stBg: '#e7f5ef', stColor: '#2F9E63', date: '2024-01-12' },
  ];

  filtered = computed(() => {
    const s = this.search().toLowerCase();
    const st = this.statusFilter();
    return this.animals.filter(a =>
      (st === 'all' || a.status.toLowerCase() === st) &&
      (a.name.toLowerCase().includes(s) || a.breed.toLowerCase().includes(s) || a.shelter.toLowerCase().includes(s))
    );
  });

  onSearch(e: Event) { this.search.set((e.target as HTMLInputElement).value); }
}
