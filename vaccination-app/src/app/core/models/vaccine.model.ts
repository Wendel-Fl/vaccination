export class Vaccine {

    public id: number;
    public title: string;
    public description: string;
    public dosage: number;
    public frequency: number;
    public interval: number;

    constructor(
        id: number = null,
        title: string = null,
        description: string = null,
        dosage: number = null,
        frequency: number = null,
        interval: number = null
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dosage = dosage;
        this.frequency = frequency;
        this.interval = interval;
    }

}