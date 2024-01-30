import { Injectable, Injector } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";
import { ToastrService } from "ngx-toastr";
import { DialogsService } from "../../shared/components/dialogs/dialogs.service";
import { AbstractControl, FormControl, FormGroup } from "@angular/forms";

@Injectable()
export class UtilComponent {

    protected toastr: ToastrService;
    protected loading: NgxSpinnerService;
    protected dialog: DialogsService;

    constructor(injector: Injector) {
        this.toastr = injector.get(ToastrService);
        this.loading = injector.get(NgxSpinnerService);
        this.dialog = injector.get(DialogsService);
    }

    public getFormControl(control: AbstractControl): FormControl {
        return control as FormControl;
    }

    protected handleError = (error: any): void => {
        this.loading.hide();
        this.toastr.error(error?.error?.message);
    }

}