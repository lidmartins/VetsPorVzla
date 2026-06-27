// pagination.component.ts
import { Component, Input, Output, EventEmitter, computed, input } from '@angular/core';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [NgIf],
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
