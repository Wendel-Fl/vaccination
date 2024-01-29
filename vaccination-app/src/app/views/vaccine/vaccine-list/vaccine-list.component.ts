import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { SharedModule } from '../../../shared/shared.module';
import { Router, RouterModule } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Vaccine } from '../../../core/models/vaccine.model';
import { VaccineService } from '../../../core/services/vaccine.service';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-vaccine-list',
  standalone: true,
  imports: [SharedModule, RouterModule],
  templateUrl: './vaccine-list.component.html',
  styleUrl: './vaccine-list.component.scss'
})
export class VaccineListComponent extends UtilComponent implements OnInit, OnDestroy {

  public vaccines$: BehaviorSubject<Vaccine[]> = new BehaviorSubject(null);

  constructor(
    private vaccineService: VaccineService,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
      this.loadVaccines();
  }

  public ngOnDestroy(): void {
      this.vaccines$.unsubscribe();
  }

  public onClickEdit(id: number): void {
    this.router.navigate([`/vaccine/form/${id}`]);
  }

  public onClickDelete(id: number): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção da Vacina?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool, id),
    });
  }

  private handleDeletionConfirmation = (bool: any, id: number): void => {
    if (bool) 
      this.deleteVaccine(id);
  };

  private deleteVaccine(id: number): void {
    this.loading.show();
    this.vaccineService.deleteById(id)
      .subscribe({
        next: () => {
          this.toastr.success("Vacina deletada com sucesso!");
          this.loadVaccines();
        },
        error: this.handleError
      })
  }

  private loadVaccines(): void {
    this.loading.show();
    this.vaccineService.findAll()
      .subscribe({
        next: (vaccines: Vaccine[]) => {
          if(vaccines?.length > 0)
            this.vaccines$.next(vaccines);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

}