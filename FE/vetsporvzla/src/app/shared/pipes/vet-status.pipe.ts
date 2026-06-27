import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'vetStatus'
})
export class VetStatusPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
