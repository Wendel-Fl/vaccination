import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { UtilComponent } from '../../../core/utils/util.component';
import { AllergyService } from '../../../core/services/allergy.service';
import { BehaviorSubject } from 'rxjs';
import { Allergy } from '../../../core/models/allergy.model';
import { Router, RouterModule } from '@angular/router';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-allergy-list',
  standalone: true,
  imports: [SharedModule, RouterModule],
  templateUrl: './allergy-list.component.html',
  styleUrl: './allergy-list.component.scss'
})
export class AllergyListComponent extends UtilComponent implements OnInit, OnDestroy {

  public allergies$: BehaviorSubject<Allergy[]> = new BehaviorSubject(null);

  constructor(
    private allergyService: AllergyService,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
      this.loadAllergies();
  }

  public ngOnDestroy(): void {
      this.allergies$.unsubscribe();
  }

  public onClickEdit(id: number): void {
    this.router.navigate([`/allergy/form/${id}`]);
  }

  public onClickDelete(id: number): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção da Alergia?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool, id),
    });
  }

  private handleDeletionConfirmation = (bool: any, id: number): void => {
    if (bool) 
      this.deleteAllergy(id);
  };

  private deleteAllergy(id: number): void {
    this.loading.show();
    this.allergyService.deleteById(id)
      .subscribe({
        next: () => {
          this.toastr.success("Alergia deletada com sucesso!");
          this.loadAllergies();
        },
        error: this.handleError
      })
  }

  private loadAllergies(): void {
    this.loading.show();
    this.allergyService.findAll()
      .subscribe({
        next: allergies => {
          this.allergies$.next(allergies?.length > 0 ? allergies : null);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

}