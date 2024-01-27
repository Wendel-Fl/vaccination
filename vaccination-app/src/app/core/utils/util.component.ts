import { Injectable, Injector } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";
import { ToastrService } from "ngx-toastr";

@Injectable()
export class UtilComponent {

    protected toastr: ToastrService;
    protected loading: NgxSpinnerService;

    constructor(injector: Injector) {
        this.toastr = injector.get(ToastrService);
        this.loading = injector.get(NgxSpinnerService);
    }

}