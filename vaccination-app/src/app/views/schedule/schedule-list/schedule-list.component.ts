import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { SharedModule } from '../../../shared/shared.module';
import { Router, RouterModule } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Schedule } from '../../../core/models/schedule.model';
import { ScheduleService } from '../../../core/services/schedule.service';
import { EnumService } from '../../../core/services/enum.service';
import { Enum } from '../../../core/types/enum.type';

@Component({
  selector: 'app-schedule-list',
  standalone: true,
  imports: [SharedModule, RouterModule],
  templateUrl: './schedule-list.component.html',
  styleUrl: './schedule-list.component.scss'
})
export class ScheduleListComponent extends UtilComponent implements OnInit, OnDestroy {

  public schedules$: BehaviorSubject<Schedule[]> = new BehaviorSubject(null);

  public schedulesStatus$: BehaviorSubject<Enum[]> = new BehaviorSubject(null);

  private readonly SCHEDULE_STATUS: string = 'status';

  constructor(
    private scheduleService: ScheduleService,
    private enumService: EnumService,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
      this.loadScheduleStatus();
      this.loadSchedules();
  }

  public ngOnDestroy(): void {
      this.schedules$.unsubscribe();
      this.schedulesStatus$.unsubscribe();
  }

  public onClickEdit(id: number): void {
    this.router.navigate([`/schedule/form/${id}`]);
  }

  public getStatusDescription(name: string): string {
    return this.schedulesStatus$.value?.find(status => status?.name === name)?.description ?? 'Não informado';
  }

  private loadScheduleStatus(): void {
    this.enumService.getEnum(this.SCHEDULE_STATUS)
      .subscribe({
        next: (status: Enum[]) => this.schedulesStatus$.next(status),
        error: this.handleError
      });
  }

  private loadSchedules(): void {
    this.loading.show();
    this.scheduleService.findAll(null)
      .subscribe({
        next: (schedules: Schedule[]) => {
          if(schedules?.length > 0)
            this.schedules$.next(schedules);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

}