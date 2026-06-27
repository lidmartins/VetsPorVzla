import { Component, signal, computed } from '@angular/core';

@Component({
  selector: 'app-refugios',
  imports: [],
  templateUrl: './refugios.component.html',
  styleUrl: './refugios.component.scss'
})
export class RefugiosComponent {
  search = signal('');

  refugios = [
    { name: 'Refugio Veterinario La Guaira', state: 'La Guaira', contact: '0412-1234567', dogs: 12, maxDogs: 20, cats: 8, maxCats: 15, status: 'Abierto', stBg: '#e7f5ef', stColor: '#1b7a55' },
    { name: 'Centro Animal Miranda',         state: 'Miranda',   contact: '0424-7654321', dogs: 20, maxDogs: 20, cats: 15, maxCats: 15, status: 'Lleno',   stBg: '#fdecec', stColor: '#DC4A4A' },
    { name: 'Refugio Caracas Norte',         state: 'D. Capital', contact: '0416-9876543', dogs: 7,  maxDogs: 25, cats: 3,  maxCats: 20, status: 'Abierto', stBg: '#e7f5ef', stColor: '#1b7a55' },
  ];

  filtered = computed(() => {
    const s = this.search().toLowerCase();
    return this.refugios.filter(r =>
      r.name.toLowerCase().includes(s) || r.state.toLowerCase().includes(s)
    );
  });

  onSearch(e: Event) { this.search.set((e.target as HTMLInputElement).value); }
}
