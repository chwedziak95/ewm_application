import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sortByDate'
})
export class SortInternalOrderByDatePipe implements PipeTransform {
  transform(internalOrders: any[], sortOrder: string): any[] {
    if (!internalOrders) {
      return [];
    }

    return internalOrders.slice().sort((a, b) => {
      const dateA = new Date(a.orderDate).getTime();
      const dateB = new Date(b.orderDate).getTime();

      if (sortOrder === 'asc') {
        return dateA - dateB;
      } else {
        return dateB - dateA;
      }
    });
  }
}
