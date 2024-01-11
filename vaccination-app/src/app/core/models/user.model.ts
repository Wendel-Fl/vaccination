export class User {

    public id: number;
    public name: string;
    public dateOfBirth: Date;
    public gender: string;
    public publicPlace: string;
    public number: number;
    public district: string;
    public city: string;
    public fu: string;

    constructor(
        id: number = null,
        name: string = null,
        dateOfBirth: Date = null,
        gender: string = null,
        publicPlace: string = null,
        number: number = null,
        city: string,
        fu: string
    ) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.publicPlace = publicPlace;
        this.number = number;
        this.city = city;
        this.fu = fu;
    }

}