import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { ScheduleService } from '../../../core/services/schedule.service';
import { ActivatedRoute, ParamMap, Router, RouterModule } from '@angular/router';
import { Schedule } from '../../../core/models/schedule.model';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';
import { User } from '../../../core/models/user.model';
import { Vaccine } from '../../../core/models/vaccine.model';
import { VaccineService } from '../../../core/services/vaccine.service';
import { UserService } from '../../../core/services/user.service';
import { Enum } from '../../../core/types/enum.type';
import { EnumService } from '../../../core/services/enum.service';

@Component({
  selector: 'app-schedule-form',
  standalone: true,
  imports: [SharedModule, FormsModule, ReactiveFormsModule, RouterModule],
  templateUrl: './schedule-form.component.html',
  styleUrl: './schedule-form.component.scss'
})
export class ScheduleFormComponent extends UtilComponent implements OnInit, OnDestroy {

  public scheduleForm$: BehaviorSubject<FormGroup> = new BehaviorSubject(null);

  public users$: BehaviorSubject<User[]> = new BehaviorSubject(null);

  public vaccines$: BehaviorSubject<Vaccine[]> = new BehaviorSubject(null);

  public status$: BehaviorSubject<Enum[]> = new BehaviorSubject(null);

  public newSchedule$: BehaviorSubject<boolean> = new BehaviorSubject(true);

  public canEdit$: BehaviorSubject<boolean> = new BehaviorSubject(false);

  private readonly SCHEDULE_STATUS: string = 'status';

  constructor(
    private scheduleService: ScheduleService,
    private vaccineService: VaccineService,
    private userService: UserService,
    private enumService: EnumService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
    this.loadData();  
    this.getRouteData();
  }

  public ngOnDestroy(): void {
    this.scheduleForm$.unsubscribe();
    this.users$.unsubscribe();
    this.vaccines$.unsubscribe();
    this.status$.unsubscribe();
    this.canEdit$.unsubscribe();
    this.newSchedule$.unsubscribe();
  }

  public onSave(): void {
    if(this.scheduleForm.valid) 
      this.saveVaccine();
    else
      this.toastr.info("Formulário inválido!");
  }

  public onClickDelete(): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção da Agenda?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool),
    });
  }

  private get scheduleForm(): FormGroup {
    return this.scheduleForm$.value;
  }

  private handleDeletionConfirmation = (bool: any): void => {
    if (bool) 
      this.deleteVaccine();
  };

  private saveVaccine(): void {
    this.loading.show();
    const id: number = this.scheduleForm.controls['id'].value;
    if(id)
      this.updateVaccine();
    else
      this.createVaccine();
  }

  private createVaccine(): void {
    this.scheduleService.create(this.scheduleForm.value)
      .subscribe({
        next: (schedule: Schedule) => {
          this.toastr.success("Agenda cadastrada com sucesso!");
          this.loading.hide();
          this.router.navigate([`/schedule/form/${schedule?.id}`]);
        },
        error: this.handleError
      })
  }

  private updateVaccine(): void {
    this.scheduleService.update(this.scheduleForm.value)
    .subscribe({
      next: () => {
        this.toastr.success("Agenda atualizada com sucesso!");
        this.loading.hide();
      },
      error: this.handleError
    })
  }

  private deleteVaccine(): void {
    this.loading.show();
    this.scheduleService.deleteById(this.scheduleForm?.controls['id']?.value)
      .subscribe({
        next: () => {
          this.toastr.success("Agenda deletada com sucesso!");
          this.router.navigate(['/schedule']);
        },
        error: this.handleError
      })
  }

  private handleRetrievedScheduleId(id: string): void {
    if(Number.isNaN(id) || Number(id) === 0){
      this.createScheduleForm();
      this.loading.hide();
    }
    else {
      this.newSchedule$.next(false);
      this.findVaccineById(Number(id));
    }
  }

  private findVaccineById(id: number): void {
    this.loading.show();
    this.scheduleService.findById(id)
      .subscribe({
        next: (schedule: Schedule) => {
          this.createScheduleForm(schedule);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

  private createScheduleForm(schedule: Schedule = new Schedule()): void {
    this.scheduleForm$.next(
      this.fb.group({
        
      })
    );
  }

  private loadData(): void {
    this.loadUsers();
    this.loadVaccines();
    this.loadScheduleStatus();
  }

  private loadUsers(): void {
    this.userService.findAll()
      .subscribe({
        next: (users: User[]) => this.users$.next(users),
        error: this.handleError
      });
  }

  private loadVaccines(): void {
    this.vaccineService.findAll()
      .subscribe({
        next: (vaccine: Vaccine[]) => this.vaccines$.next(vaccine),
        error: this.handleError
      });
  }

  private loadScheduleStatus(): void {
    this.enumService.getEnum(this.SCHEDULE_STATUS)
      .subscribe({
        next: (enums: Enum[]) => this.status$.next(enums),
        error: this.handleError
      });
  }

  private getRouteData(): void {
    this.loading.show();
    this.route.paramMap.subscribe({
      next: (map: ParamMap) => {
        this.handleRetrievedScheduleId(map.get('id'));
      },
      error: () => {
        this.toastr.error("Erro ao resgatar dados da rota");
      }
    });
  }

}