import { Injectable } from '@angular/core';
import { InternalCartItem } from '../common/internal-cart-item/internal-cart-item';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InternalCartService {

  internalCartItems: InternalCartItem[] = [];
  internalCartItems$ = new BehaviorSubject<InternalCartItem[]>([]);

  storage: Storage = localStorage;

  constructor() {
    let data = JSON.parse(this.storage.getItem('internalCartItems'));

    if (data != null) {
      this.internalCartItems = data;
      this.internalCartItems$.next(this.internalCartItems);
    }
  }

  addToInternalCart(internalCartItem: InternalCartItem) {
    let alreadyExistsInCart = false;
    let existingCartItem: InternalCartItem | undefined;
  
    if (this.internalCartItems.length > 0) {
      existingCartItem = this.internalCartItems.find(tempInternalCartItem => tempInternalCartItem.id === internalCartItem.id);
  
      alreadyExistsInCart = (existingCartItem !== undefined);
    }
  
    if (alreadyExistsInCart && existingCartItem) {
      if (existingCartItem.quantity < existingCartItem.availableQuantity) {
        existingCartItem.quantity++;
      } else {
        alert('Nie można dodać więcej przedmiotów niż dostępna ilość w magazynie');
      }
    } else {
      this.internalCartItems.push(internalCartItem);
    }
    this.persistCartItems();
    this.internalCartItems$.next(this.internalCartItems);
  }
  
  persistCartItems() {
    this.storage.setItem('internalCartItems', JSON.stringify(this.internalCartItems));
  }

  decrementQuantity(internalCartItem: InternalCartItem) {
    internalCartItem.quantity--;

    if (internalCartItem.quantity === 0) {
      this.remove(internalCartItem);
    } else {
      this.persistCartItems();
      this.internalCartItems$.next(this.internalCartItems);
    }
  }

  remove(internalCartItem: InternalCartItem) {
    const itemIndex = this.internalCartItems.findIndex(tempInternalCartItem => tempInternalCartItem.id === internalCartItem.id);

    if (itemIndex > -1) {
      this.internalCartItems.splice(itemIndex, 1);
    }
    this.persistCartItems();
    this.internalCartItems$.next(this.internalCartItems);
  }

  resetCart() {
    this.internalCartItems = [];
    localStorage.removeItem('internalCartItems');
    this.internalCartItems$.next(this.internalCartItems);
  }
}
