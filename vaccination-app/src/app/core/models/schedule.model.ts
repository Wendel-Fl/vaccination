import { User } from "./user.model";
import { Vaccine } from "./vaccine.model";

export class Schedule {

    public id: number;
    public dateTime: Date;
    public status: string;
    public statusDate: string;
    public notes: string;
    public user: User;
    public vaccination: Vaccine;

    constructor(
        id: number = null,
        dateTime: Date = null,
        status: string = null,
        statusDate: string = null,
        notes: string = null,
        user: User = null,
        vaccination: Vaccine = null
    ) {
        this.id = id;
        this.dateTime = dateTime;
        this.status = status;
        this.statusDate = statusDate;
        this.notes = notes;
        this.user = user;
        this.vaccination = vaccination;
    }

}