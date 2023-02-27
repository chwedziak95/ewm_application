import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterBy'
})
export class FilterByPipe implements PipeTransform {

  transform(items: any[], filters: any): any[] {
    if (!items || !filters) {
      return items;
    }

    // apply filters to the items array
    return items.filter(item => {
      // filter by property
      for (const key in filters) {
        if (filters.hasOwnProperty(key)) {
          const filterValue = filters[key];
          const itemValue = item[key];
          if (filterValue && itemValue && typeof filterValue === 'string' && typeof itemValue === 'string') {
            if (itemValue.toLowerCase().indexOf(filterValue.toLowerCase()) === -1) {
              return false;
            }
          } else if (filterValue && itemValue && typeof filterValue === 'object' && typeof itemValue === 'object') {
            if (!this.isMatch(filterValue, itemValue)) {
              return false;
            }
          }
        }
      }

      // all filters passed, include the item in the filtered array
      return true;
    });
  }

  isMatch(filterObj: any, itemObj: any): boolean {
    for (const key in filterObj) {
      if (filterObj.hasOwnProperty(key)) {
        const filterValue = filterObj[key];
        const itemValue = itemObj[key];
        if (filterValue && itemValue && typeof filterValue === 'string' && typeof itemValue === 'string') {
          if (itemValue.toLowerCase().indexOf(filterValue.toLowerCase()) === -1) {
            return false;
          }
        } else if (filterValue && itemValue && typeof filterValue === 'object' && typeof itemValue === 'object') {
          if (!this.isMatch(filterValue, itemValue)) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
