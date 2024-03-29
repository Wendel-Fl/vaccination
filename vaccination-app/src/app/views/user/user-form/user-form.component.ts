import { ChangeDetectionStrategy, Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { AbstractControl, FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import { ActivatedRoute, ParamMap, Router, RouterModule } from '@angular/router';
import { User } from '../../../core/models/user.model';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';
import { AllergyService } from '../../../core/services/allergy.service';
import { Allergy } from '../../../core/models/allergy.model';
import { BehaviorSubject } from 'rxjs';
import { SharedModule } from '../../../shared/shared.module';
import { Enum } from '../../../core/types/enum.type';
import { EnumService } from '../../../core/services/enum.service';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [SharedModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserFormComponent extends UtilComponent implements OnInit, OnDestroy {

  public userForm$: BehaviorSubject<FormGroup> = new BehaviorSubject(null);

  public newUser$: BehaviorSubject<boolean> = new BehaviorSubject(true);

  public allergies$: BehaviorSubject<Allergy[]> = new BehaviorSubject(null);

  public ufs$: BehaviorSubject<Enum[]> = new BehaviorSubject(null);

  constructor(
    private userService: UserService,
    private allergyService: AllergyService,
    private enumService: EnumService,
    private route: ActivatedRoute,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
    this.findAllergies();
    this.findUfs();
  }

  public ngOnDestroy(): void {
    this.userForm$.unsubscribe();
    this.newUser$.unsubscribe();
    this.allergies$.unsubscribe();
    this.ufs$.unsubscribe();
  }

  public onSave(): void {
    if(this.userForm.valid)
      this.saveUser();
    else
      this.toastr.info("Formulário inválido!");
  }

  public onClickDelete(): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção do Usuário?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool),
    });
  }

  private get userForm(): FormGroup {
    return this.userForm$.value;
  }

  private handleDeletionConfirmation = (bool: any): void => {
    if (bool) 
      this.deleteUser();
  };

  private saveUser(): void {
    this.loading.show();
    const id: number = this.userForm.controls['id'].value;
    const user: User = this.buildUserFromFormValue();
    if(id)
      this.updateUser(user);
    else
      this.createUser(user);
  }

  private createUser(user: User): void {
    this.userService.create(user)
      .subscribe({
        next: (user: User) => {
          this.toastr.success("Usuário cadastrado com sucesso!");
          this.loading.hide();
          this.router.navigate([`/user/form/${user?.id}`]);
        },
        error: this.handleError
      });
  }

  private updateUser(user: User): void {
    this.userService.update(user)
      .subscribe({
        next: () => {
          this.toastr.success("Usuário atualizado com sucesso!");
          this.loading.hide();
        },
        error: this.handleError
      });
  }

  private deleteUser(): void {
    this.loading.show();
    this.userService.deleteById(this.userForm.controls['id'].value)
      .subscribe({
        next: () => {
          this.toastr.success("Usuário deletado com sucesso!");
          this.router.navigate(['/user']);
        },
        error: this.handleError
      });
  }

  private handleRetrievedUserId(id: string): void {
    if(Number.isNaN(id) || Number(id) === 0){
      this.createUserForm();
      this.loading.hide();
    }
    else {
      this.newUser$.next(false);
      this.findUserById(Number(id));
    }
  }

  private findUserById(id: number): void {
    this.loading.show();
    this.userService.findById(id)
      .subscribe({
        next: (user: User) => {
          this.createUserForm(user);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

  private createUserForm(user: User = new User()): void {
    const userForm: FormGroup = this.buildUserForm(user);
    const allergies: Allergy[] = this.allergies$.value;
    if(allergies) {
      userForm.addControl(
        'allergies', this.fb.array(
          allergies?.map(
            allergy => user?.allergies?.find(userAllergy => userAllergy?.id === allergy?.id) != undefined
          )
        )
      );
    }
    this.userForm$.next(userForm);
  }

  private buildUserFromFormValue(): User {
    const formValue = this.userForm.value;
    return new User(
      formValue?.id,
      formValue?.name,
      formValue?.dateOfBirth,
      formValue?.gender,
      formValue?.publicPlace,
      formValue?.number,
      formValue?.city,
      formValue?.state,
      formValue?.district,
      this.allergies$.value
        ?.map((allergy: Allergy, index: number) => formValue?.allergies[index] ? allergy : null)
        .filter(allergy => allergy != null)
    );
  }

  private findAllergies(): void {
    this.allergyService.findAll()
      .subscribe({
        next: (allergies: Allergy[]) => {
          if(allergies.length > 0)
            this.allergies$.next(allergies);
          this.getRouteData();
        },
        error: this.handleError
      });
  }

  private findUfs(): void {
    this.enumService.getEnum('state')
      .subscribe({
        next: (ufs: Enum[]) => {
          this.ufs$.next(ufs);
        },
        error: this.handleError
      })
  }

  private getRouteData(): void {
    this.loading.show();
    this.route.paramMap.subscribe({
      next: (map: ParamMap) => {
        this.handleRetrievedUserId(map.get('id'));
      },
      error: () => {
        this.toastr.error("Erro ao resgatar dados da rota");
      }
    });
  }

}