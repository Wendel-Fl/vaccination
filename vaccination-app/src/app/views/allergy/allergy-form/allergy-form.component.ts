import { ChangeDetectionStrategy, Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router, RouterModule } from '@angular/router';
import { AllergyService } from '../../../core/services/allergy.service';
import { Allergy } from '../../../core/models/allergy.model';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-allergy-form',
  standalone: true,
  imports: [SharedModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './allergy-form.component.html',
  styleUrl: './allergy-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AllergyFormComponent extends UtilComponent implements OnInit, OnDestroy {

  public allergyForm$: BehaviorSubject<FormGroup> = new BehaviorSubject(null);

  public newAllergy$: BehaviorSubject<boolean> = new BehaviorSubject(true);

  constructor(
    private allergyService: AllergyService,
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
    this.allergyForm$.unsubscribe();
    this.newAllergy$.unsubscribe();
  }

  public onSave(): void {
    if(this.allergyForm.valid) 
      this.saveAllergy();
    else
      this.toastr.info("Formulário inválido!");
  }

  public onClickDelete(): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção da Alergia?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool),
    });
  }

  private get allergyForm(): FormGroup {
    return this.allergyForm$.value;
  }

  private handleDeletionConfirmation = (bool: any): void => {
    if (bool) 
      this.deleteAllergy();
  };

  private saveAllergy(): void {
    this.loading.show();
    const id: number = this.allergyForm.controls['id'].value;
    if(id)
      this.updateAllergy();
    else
      this.createAllergy();
  }

  private createAllergy(): void {
    this.allergyService.create(this.allergyForm.value)
      .subscribe({
        next: (allergy: Allergy) => {
          this.toastr.success("Alergia cadastrada com sucesso!");
          this.loading.hide();
          this.router.navigate([`/allergy/form/${allergy?.id}`]);
        },
        error: this.handleError
      })
  }

  private updateAllergy(): void {
    this.allergyService.update(this.allergyForm.value)
    .subscribe({
      next: () => {
        this.toastr.success("Alergia atualizada com sucesso!");
        this.loading.hide();
      },
      error: this.handleError
    })
  }

  private deleteAllergy(): void {
    this.loading.show();
    this.allergyService.deleteById(this.allergyForm?.controls['id']?.value)
      .subscribe({
        next: () => {
          this.toastr.success("Alergia deletada com sucesso!");
          this.router.navigate(['/allergy']);
        },
        error: this.handleError
      })
  }

  private handleRetrievedAllergyId(id: string): void {
    if(Number.isNaN(id) || Number(id) === 0){
      this.createAllergyForm();
      this.loading.hide();
    }
    else {
      this.newAllergy$.next(false);
      this.findAllergyById(Number(id));
    }
  }

  private findAllergyById(id: number): void {
    this.loading.show();
    this.allergyService.findById(id)
      .subscribe({
        next: (allergy: Allergy) => {
          this.createAllergyForm(allergy);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

  private createAllergyForm(allergy: Allergy = new Allergy()): void {
    this.allergyForm$.next(
      this.buildAllergyForm(allergy)
    );
  }

  private getRouteData(): void {
    this.loading.show();
    this.route.paramMap.subscribe({
      next: (map: ParamMap) => {
        this.handleRetrievedAllergyId(map.get('id'));
      },
      error: () => {
        this.toastr.error("Erro ao resgatar dados da rota");
      }
    });
  }

}
