import { Injectable, Injector } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";
import { ToastrService } from "ngx-toastr";
import { DialogsService } from "../../shared/components/dialogs/dialogs.service";
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { User } from "../models/user.model";
import { Allergy } from "../models/allergy.model";
import { Vaccine } from "../models/vaccine.model";

@Injectable()
export class UtilComponent {

    protected fb: FormBuilder;
    protected toastr: ToastrService;
    protected loading: NgxSpinnerService;
    protected dialog: DialogsService;

    constructor(injector: Injector) {
        this.fb = injector.get(FormBuilder);
        this.toastr = injector.get(ToastrService);
        this.loading = injector.get(NgxSpinnerService);
        this.dialog = injector.get(DialogsService);
    }

    public getFormControl(control: AbstractControl): FormControl {
        return control as FormControl;
    }

    protected buildAllergyForm(allergy: Allergy = new Allergy()): FormGroup {
        return this.fb.group({
            id: [allergy?.id],
            name: [allergy?.name, [Validators.required, Validators.maxLength(40)]]
        });
    }

    protected buildUserForm(user: User = new User()): FormGroup {
        return this.fb.group({
            id: [user?.id],
            name: [user?.name, [Validators.required, Validators.maxLength(40)]],
            dateOfBirth: [user?.dateOfBirth, [Validators.required]],
            gender: [user?.gender, [Validators.required]],
            publicPlace: [user?.publicPlace, [Validators.required, Validators.maxLength(60)]],
            number: [user?.number, [Validators.required]],
            district: [user?.district, [Validators.required, Validators.maxLength(40)]],
            city: [user?.city, [Validators.required, Validators.maxLength(40)]],
            state: [user?.state, [Validators.required]]
        });
    }

    protected buildVaccineForm(vaccine: Vaccine = new Vaccine): FormGroup {
        return this.fb.group({
            id: [vaccine?.id],
            title: [vaccine?.title, [Validators.required, Validators.maxLength(60)]],
            description: [vaccine?.description, [Validators.required, Validators.maxLength(200)]],
            dosage: [vaccine?.dosage, [Validators.required]],
            frequency: [vaccine?.frequency, [Validators.required]],
            interval: [vaccine?.interval, [Validators.required]]
        });
    }

    protected handleError = (error: any): void => {
        this.loading.hide();
        this.toastr.error(error?.error?.message);
    }

}