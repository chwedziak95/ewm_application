export class User{
    userId: number;
    password: string;
    email: string;
    firstName: string;
    lastName: string;
    created: Date;
    enabled: boolean;
  
    constructor() {}

    static fromResponse(response: any): User {
        const user = new User();
        user.userId = response.userId;
        user.email = response.email;
        user.firstName = response.firstName;
        user.lastName = response.lastName;
        user.created = new Date(response.created);
        user.enabled = response.enabled;
        return user;
      }
}
