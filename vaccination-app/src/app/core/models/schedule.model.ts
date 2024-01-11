export class Schedule {

    public id: number;
    public date: Date;
    public hour: string;
    public status: string;
    public statusDate: string;
    public note: string;

    constructor(
        id: number = null,
        date: Date = null,
        hour: string = null,
        status: string = null,
        statusDate: string = null,
        note: string
    ) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.status = status;
        this.statusDate = statusDate;
        this.note = note;
    }

}