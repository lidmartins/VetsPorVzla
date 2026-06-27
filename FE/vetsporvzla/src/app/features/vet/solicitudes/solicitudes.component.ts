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
