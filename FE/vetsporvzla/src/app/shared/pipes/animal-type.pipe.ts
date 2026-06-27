import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'animalType'
})
export class AnimalTypePipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
