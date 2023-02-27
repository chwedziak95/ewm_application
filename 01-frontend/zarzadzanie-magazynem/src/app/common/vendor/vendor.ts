export class Vendor {
    vendorId: number;
    vendorName: string;
    vendorAddress: string;
    vendorCity: string;
    vendorPostalCode: string;
    vendorCountry: string;
    vendorEmail: string;
    vendorPhone: string;
    vendorNip: string;
    vendorRegon: string;
    vendorKrs: string;
    vendorBankAccount: string;
    vendorBankName: string;
    vendorCreated: Date;
  
    constructor(vendorId: number, vendorName: string, vendorAddress: string,
                vendorCity: string, vendorPostalCode: string, vendorCountry: string,
                vendorEmail: string, vendorPhone: string, vendorNip: string,
                vendorRegon: string, vendorKrs: string, vendorBankAccount: string,
                vendorBankName: string, vendorCreated: Date) {
      this.vendorId = vendorId;
      this.vendorName = vendorName;
      this.vendorAddress = vendorAddress;
      this.vendorCity = vendorCity;
      this.vendorPostalCode = vendorPostalCode;
      this.vendorCountry = vendorCountry;
      this.vendorEmail = vendorEmail;
      this.vendorPhone = vendorPhone;
      this.vendorNip = vendorNip;
      this.vendorRegon = vendorRegon;
      this.vendorKrs = vendorKrs;
      this.vendorBankAccount = vendorBankAccount;
      this.vendorBankName = vendorBankName;
      this.vendorCreated = vendorCreated;
    }
  }