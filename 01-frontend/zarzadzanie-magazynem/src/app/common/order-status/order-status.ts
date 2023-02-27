export class OrderStatus {
    public statusId: number;
    public name: string;
  
    constructor(statusId: number, name: string) {
      this.statusId = statusId;
      this.name = name;
    }
  }