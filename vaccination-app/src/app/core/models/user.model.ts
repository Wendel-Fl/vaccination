import { Allergy } from "./allergy.model";

export class User {

    public id: number;
    public name: string;
    public dateOfBirth: Date;
    public gender: string;
    public publicPlace: string;
    public number: number;
    public district: string;
    public city: string;
    public state: string;
    public allergies: Allergy[];

    constructor(
        id: number = null,
        name: string = null,
        dateOfBirth: Date = null,
        gender: string = null,
        publicPlace: string = null,
        number: number = null,
        city: string = null,
        state: string = null,
        district: string = null,
        allergies: Allergy[] = []
    ) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.publicPlace = publicPlace;
        this.number = number;
        this.city = city;
        this.state = state;
        this.district = district;
        this.allergies = allergies;
    }

}