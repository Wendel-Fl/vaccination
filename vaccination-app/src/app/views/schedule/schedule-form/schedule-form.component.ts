import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
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

  public scheduleDone$: BehaviorSubject<boolean> = new BehaviorSubject(false);

  private readonly SCHEDULE_STATUS: string = 'status';

  constructor(
    private scheduleService: ScheduleService,
    private vaccineService: VaccineService,
    private userService: UserService,
    private enumService: EnumService,
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
    this.scheduleDone$.unsubscribe();
    this.newSchedule$.unsubscribe();
  }

  public onSave(): void {
    if(this.scheduleForm.valid) 
      this.saveSchedule();
    else
      this.toastr.info("Formulário inválido!");
    console.log(this.scheduleForm)
  }

  public onClickDelete(): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção da Agenda?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool),
    });
  }

  public onClickCancel(): void {
    if(this.scheduleForm.valid) {
      this.dialog.open(ConfirmationDialogComponent, {
        inputs: {
          text: 'Confirma cancelamento da Agenda?<br> Os dados não poderão ser alterados depois',
        },
        onClose: (bool: any) => this.handleCancelConfirmation(bool),
      });
    }
    else
      this.toastr.info("Formulário inválido")
  }

  public onClickCarryOut(): void {
    if(this.scheduleForm.valid) {
      this.dialog.open(ConfirmationDialogComponent, {
        inputs: {
          text: 'Confirma realização da Agenda?<br> Os dados não poderão ser alterados depois',
        },
        onClose: (bool: any) => this.handleCarryOutConfirmation(bool),
      });
    }
    else
      this.toastr.info("Formulário inválido")
  }

  public getStatusDescription(name: string): string {
    return this.status$.value?.find(status => status?.name === name)?.description ?? 'Não informado';
  }

  private get scheduleForm(): FormGroup {
    return this.scheduleForm$.value;
  }

  private handleDeletionConfirmation = (bool: any): void => {
    if (bool) 
      this.deleteSchedule();
  };

  private handleCancelConfirmation = (bool: any): void => {
    if (bool) 
      this.cancelSchedule();
  };

  private handleCarryOutConfirmation = (bool: any): void => {
    if (bool) 
      this.carryOutSchedule();
  };

  private saveSchedule(): void {
    this.loading.show();
    const id: number = this.scheduleForm.controls['id'].value;
    const schedule: Schedule = this.buildScheduleFromFormValue();
    if(id)
      this.updateSchedule(schedule);
    else
      this.createSchedule(schedule);
  }

  private createSchedule(schedule: Schedule): void {
    this.scheduleService.create(schedule)
      .subscribe({
        next: () => {
          this.toastr.success("Agendas cadastradas com sucesso!");
          this.loading.hide();
          this.router.navigate(['schedule']);
        },
        error: this.handleError
      })
  }

  private updateSchedule(schedule: Schedule): void {
    this.scheduleService.update(schedule)
    .subscribe({
      next: () => {
        this.toastr.success("Agenda atualizada com sucesso!");
        this.loading.hide();
      },
      error: this.handleError
    })
  }

  private deleteSchedule(): void {
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

  private cancelSchedule(): void {
    this.scheduleService.cancel(this.buildScheduleFromFormValue())
      .subscribe({
        next: (schedule: Schedule) => {
          this.toastr.success("Agenda cancelada");
          this.loading.hide();
          this.router.navigate[`/schedule/form/${schedule?.id}`];
        },
        error: this.handleError
      });
  }

  private carryOutSchedule(): void {
    this.scheduleService.carryOut(this.buildScheduleFromFormValue())
    .subscribe({
      next: (schedule: Schedule) => {
        this.toastr.success("Agenda realizada");
        this.loading.hide();
        this.router.navigate[`/schedule/form/${schedule?.id}`];
      },
      error: this.handleError
    });
  }

  private handleRetrievedScheduleId(id: string): void {
    if(Number.isNaN(id) || Number(id) === 0){
      this.createScheduleForm();
      this.loading.hide();
    }
    else {
      this.newSchedule$.next(false);
      this.findScheduleById(Number(id));
    }
  }

  private findScheduleById(id: number): void {
    this.loading.show();
    this.scheduleService.findById(id)
      .subscribe({
        next: (schedule: Schedule) => {
          this.scheduleDone$.next(schedule?.statusDate != null);
          this.createScheduleForm(schedule);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

  private createScheduleForm(schedule: Schedule = new Schedule()): void {
    this.scheduleForm$.next(this.buildScheduleForm(schedule));
  }

  private buildScheduleForm(schedule: Schedule = new Schedule()): FormGroup {
    const done: boolean = schedule?.statusDate != null;
    return this.fb.group({
      id: [schedule?.id],
      dateTime: [{value: schedule?.dateTime, disabled: done}, [Validators.required]],
      status: [{value: schedule?.status, disabled: true}],
      statusDate: [{value: schedule?.statusDate, disabled: true}],
      notes: [{value: schedule?.notes, disabled: done}, [Validators.required, Validators.maxLength(200)]],
      user: [{value: schedule?.user?.id, disabled: done}, [Validators.required]],
      vaccination: [{value: schedule?.vaccination?.id, disabled: done}, [Validators.required]]
    });
  }

  private buildScheduleFromFormValue(): Schedule {
    const formValues = this.scheduleForm.value;
    const schedule = new Schedule(
      formValues?.id,
      formValues?.dateTime,
      formValues?.status,
      formValues?.statusDate,
      formValues?.notes,
      this.users$.value.find(user => user?.id == formValues?.user),
      this.vaccines$.value.find(vaccine => vaccine?.id == formValues?.vaccination),
    );
    console.log(formValues, schedule)
    return schedule;
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