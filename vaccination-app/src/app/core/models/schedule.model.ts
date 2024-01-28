import { User } from "./user.model";
import { Vaccine } from "./vaccine.model";

export class Schedule {

    public id: number;
    public date: Date;
    public hour: string;
    public status: string;
    public statusDate: string;
    public note: string;
    public user: User;
    public vaccination: Vaccine;

    constructor(
        id: number = null,
        date: Date = null,
        hour: string = null,
        status: string = null,
        statusDate: string = null,
        note: string = null,
        user: User = null,
        vaccination: Vaccine = null
    ) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.status = status;
        this.statusDate = statusDate;
        this.note = note;
        this.user = user;
        this.vaccination = vaccination;
    }

}