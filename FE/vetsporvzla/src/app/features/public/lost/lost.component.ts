import { Component, signal, computed } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-lost',
  imports: [FormsModule],
  templateUrl: './lost.component.html',
  styleUrl: './lost.component.scss'
})
export class LostComponent {
  filter = signal<'all' | 'dog' | 'cat' | 'other'>('all');
  search = signal('');

  animals = [
    { name: 'Luna', species: 'dog', breed: 'Golden Retriever', state: 'Distrito Capital', zone: 'Caracas', time: 'Hace 3h', color: '#FEF2F2', icon: '🐕', status: 'Buscando' },
    { name: 'Max',  species: 'dog', breed: 'Labrador', state: 'Miranda', zone: 'Los Teques', time: 'Hace 7h', color: '#FEF2F2', icon: '🐕', status: 'Buscando' },
    { name: 'Misi', species: 'cat', breed: 'Mestizo', state: 'La Guaira', zone: 'Maiquetía', time: 'Hace 1d', color: '#FFF8F0', icon: '🐈', status: 'Buscando' },
    { name: 'Coco', species: 'dog', breed: 'Beagle', state: 'Carabobo', zone: 'Valencia', time: 'Hace 2d', color: '#FEF2F2', icon: '🐕', status: 'Buscando' },
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
