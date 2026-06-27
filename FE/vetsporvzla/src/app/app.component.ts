import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { AnimalService } from './core/services/animal.service';
import { CreateAnimalRequest } from './shared/models/animal.model';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  private fb         = inject(FormBuilder);
  private animalSvc  = inject(AnimalService);

  showModal     = false;
  submitting    = signal(false);
  submitSuccess = signal(false);
  submitError   = signal('');

  reportForm = this.fb.group({
    tipo:         ['P', Validators.required],
    an_tp_animal: ['P', Validators.required],
    an_nm_animal: [''],
    an_de_color:  ['', Validators.required],
    an_tp_size:   ['M', Validators.required],
    an_tp_sex:    ['M', Validators.required],
    an_de_animal: ['', Validators.required],
    ubicacion:    [''],
    telefono:     [''],
  });

  openModal() {
    this.showModal = true;
    this.submitSuccess.set(false);
    this.submitError.set('');
    this.reportForm.reset({ tipo: 'P', an_tp_animal: 'P', an_tp_size: 'M', an_tp_sex: 'M' });
  }

  closeModal() {
    this.showModal = false;
  }

  submit() {
    if (this.reportForm.invalid) {
      this.reportForm.markAllAsTouched();
      return;
    }
    this.submitting.set(true);
    this.submitError.set('');

    const v = this.reportForm.getRawValue();
    const payload: CreateAnimalRequest = {
      an_tp_animal:             v.an_tp_animal as 'G' | 'P',
      an_nm_animal:             v.an_nm_animal || undefined,
      an_de_color:              v.an_de_color!,
      an_tp_size:               v.an_tp_size as 'P' | 'M' | 'G',
      an_tp_sex:                v.an_tp_sex as 'M' | 'H',
      an_de_animal:             v.an_de_animal!,
      an_in_require_vet_review: 'S',
    };

    this.animalSvc.createAnimal(payload).subscribe({
      next: () => {
        this.submitting.set(false);
        this.submitSuccess.set(true);
      },
      error: (err: Error) => {
        this.submitting.set(false);
        this.submitError.set(err.message);
      },
    });
  }
}
