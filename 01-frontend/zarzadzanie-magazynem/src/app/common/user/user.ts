export class User{
    userId: number;
    password: string;
    email: string;
    firstName: string;
    lastName: string;
    created: Date;
    enabled: boolean;
  
    constructor(
      userId: number,
      password: string,
      email: string,
      firstName: string,
      lastName: string,
      created: Date,
      enabled: boolean,
    ) {
      this.userId = userId;
      this.password = password;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.created = created;
      this.enabled = enabled;
    }
}
