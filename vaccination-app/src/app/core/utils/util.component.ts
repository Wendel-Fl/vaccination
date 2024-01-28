import { Injectable, Injector } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";
import { ToastrService } from "ngx-toastr";
import { DialogsService } from "../../shared/components/dialogs/dialogs.service";

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

    protected handleError = (error: any): void => {
        this.loading.hide();
        this.toastr.error(error?.error?.message);
    }

}