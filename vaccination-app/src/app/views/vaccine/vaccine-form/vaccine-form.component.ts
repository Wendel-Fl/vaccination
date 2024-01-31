import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { VaccineService } from '../../../core/services/vaccine.service';
import { ActivatedRoute, ParamMap, Router, RouterModule } from '@angular/router';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';
import { Vaccine } from '../../../core/models/vaccine.model';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-vaccine-form',
  standalone: true,
  imports: [SharedModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './vaccine-form.component.html',
  styleUrl: './vaccine-form.component.scss'
})
export class VaccineFormComponent extends UtilComponent implements OnInit, OnDestroy {

  public vaccineForm$: BehaviorSubject<FormGroup> = new BehaviorSubject(null);

  public newVaccine$: BehaviorSubject<boolean> = new BehaviorSubject(true);

  constructor(
    private vaccineService: VaccineService,
    private route: ActivatedRoute,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
      this.getRouteData();
  }

  public ngOnDestroy(): void {
    this.vaccineForm$.unsubscribe();
    this.newVaccine$.unsubscribe();
  }

  public onSave(): void {
    if(this.vaccineForm.valid) 
      this.saveVaccine();
    else
      this.toastr.info("Formulário inválido!");
  }

  public onClickDelete(): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção da Vacina?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool),
    });
  }

  private get vaccineForm(): FormGroup {
    return this.vaccineForm$.value;
  }

  private handleDeletionConfirmation = (bool: any): void => {
    if (bool) 
      this.deleteVaccine();
  };

  private saveVaccine(): void {
    this.loading.show();
    const id: number = this.vaccineForm.controls['id'].value;
    if(id)
      this.updateVaccine();
    else
      this.createVaccine();
  }

  private createVaccine(): void {
    this.vaccineService.create(this.vaccineForm.value)
      .subscribe({
        next: (vaccine: Vaccine) => {
          this.toastr.success("Vacina cadastrada com sucesso!");
          this.loading.hide();
          this.router.navigate([`/vaccine/form/${vaccine?.id}`]);
        },
        error: this.handleError
      })
  }

  private updateVaccine(): void {
    this.vaccineService.update(this.vaccineForm.value)
    .subscribe({
      next: () => {
        this.toastr.success("Vacina atualizada com sucesso!");
        this.loading.hide();
      },
      error: this.handleError
    })
  }

  private deleteVaccine(): void {
    this.loading.show();
    this.vaccineService.deleteById(this.vaccineForm?.controls['id']?.value)
      .subscribe({
        next: () => {
          this.toastr.success("Vacina deletada com sucesso!");
          this.router.navigate(['/vaccine']);
        },
        error: this.handleError
      })
  }

  private handleRetrievedVaccineId(id: string): void {
    if(Number.isNaN(id) || Number(id) === 0){
      this.createVaccineForm();
      this.loading.hide();
    }
    else {
      this.newVaccine$.next(false);
      this.findVaccineById(Number(id));
    }
  }

  private findVaccineById(id: number): void {
    this.loading.show();
    this.vaccineService.findById(id)
      .subscribe({
        next: (vaccine: Vaccine) => {
          this.createVaccineForm(vaccine);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

  private createVaccineForm(vaccine: Vaccine = new Vaccine()): void {
    this.vaccineForm$.next(
      this.buildVaccineForm(vaccine)
    );
  }

  private getRouteData(): void {
    this.loading.show();
    this.route.paramMap.subscribe({
      next: (map: ParamMap) => {
        this.handleRetrievedVaccineId(map.get('id'));
      },
      error: () => {
        this.toastr.error("Erro ao resgatar dados da rota");
      }
    });
  }

}
