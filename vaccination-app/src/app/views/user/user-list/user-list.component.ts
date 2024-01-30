import { ChangeDetectionStrategy, Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { UtilComponent } from '../../../core/utils/util.component';
import { User } from '../../../core/models/user.model';
import { UserService } from '../../../core/services/user.service';
import { Router, RouterModule } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { ConfirmationDialogComponent } from '../../../shared/components/dialogs/confirmation-dialog/confirmation-dialog.component';
import { SharedModule } from '../../../shared/shared.module';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [SharedModule, RouterModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserListComponent extends UtilComponent implements OnInit, OnDestroy {

  public users$: BehaviorSubject<User[]> = new BehaviorSubject(null);

  constructor(
    private userService: UserService,
    private router: Router,
    injector: Injector
  ) {
    super(injector);
  }

  public ngOnInit(): void {
      this.loadUsers();
  }

  public ngOnDestroy(): void {
      this.users$.unsubscribe();
  }

  public onClickEdit(id: number): void {
    this.router.navigate([`/user/form/${id}`]);
  }

  public onClickDelete(id: number): void {
    this.dialog.open(ConfirmationDialogComponent, {
      inputs: {
        text: 'Confirma deleção do Usuário?',
      },
      onClose: (bool: any) => this.handleDeletionConfirmation(bool, id),
    });
  }

  private handleDeletionConfirmation = (bool: any, id: number): void => {
    if (bool) 
      this.deleteUser(id);
  };

  private deleteUser(id: number): void {
    this.loading.show();
    this.userService.deleteById(id)
      .subscribe({
        next: () => {
          this.toastr.success("Usuário deletado com sucesso!");
          this.loadUsers();
        },
        error: this.handleError
      })
  }

  private loadUsers(): void {
    this.loading.show();
    this.userService.findAll()
      .subscribe({
        next: (users: User[]) => {
          if(users?.length > 0)
            this.users$.next(users);
          this.loading.hide();
        },
        error: this.handleError
      })
  }

}
