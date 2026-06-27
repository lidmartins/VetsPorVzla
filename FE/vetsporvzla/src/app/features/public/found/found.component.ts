import { Component, signal, computed } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-found',
  imports: [FormsModule],
  templateUrl: './found.component.html',
  styleUrl: './found.component.scss'
})
export class FoundComponent {
  filter = signal<'all' | 'dog' | 'cat' | 'other'>('all');
  search = signal('');

  animals = [
    { name: 'Desconocido', species: 'cat', breed: 'Mestizo', state: 'La Guaira', zone: 'Maiquetía', time: 'Hace 5h', color: '#F0FDF4', icon: '🐈', status: 'En refugio' },
    { name: 'Desconocido', species: 'dog', breed: 'Mestizo', state: 'Vargas', zone: 'La Guaira', time: 'Hace 9h', color: '#F0FDF4', icon: '🐕', status: 'En refugio' },
    { name: 'Desconocido', species: 'dog', breed: 'Pequeño', state: 'Miranda', zone: 'Guarenas', time: 'Hace 1d', color: '#F0FDF4', icon: '🐕', status: 'En cuidado' },
  ];

  filtered = computed(() => {
    const f = this.filter();
    const s = this.search().toLowerCase();
    return this.animals.filter(a =>
      (f === 'all' || a.species === f) &&
      (a.name.toLowerCase().includes(s) || a.zone.toLowerCase().includes(s) || a.state.toLowerCase().includes(s))
    );
  });

  setFilter(v: 'all' | 'dog' | 'cat' | 'other') { this.filter.set(v); }
  onSearch(e: Event) { this.search.set((e.target as HTMLInputElement).value); }
}
