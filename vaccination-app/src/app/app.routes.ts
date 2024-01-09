import { Routes } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { AllergyFormComponent } from './views/allergy/allergy-form/allergy-form.component';
import { AllergyListComponent } from './views/allergy/allergy-list/allergy-list.component';
import { ScheduleListComponent } from './views/schedule/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './views/schedule/schedule-form/schedule-form.component';
import { UserListComponent } from './views/user/user-list/user-list.component';
import { UserFormComponent } from './views/user/user-form/user-form.component';
import { VaccineListComponent } from './views/vaccine/vaccine-list/vaccine-list.component';
import { VaccineFormComponent } from './views/vaccine/vaccine-form/vaccine-form.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'allergy',
        children: [
            {
                path: '',
                component: AllergyListComponent
            },
            {
                path: 'form/:id',
                component: AllergyFormComponent
            }
        ]
    },
    {
        path: 'schedule',
        children: [
            {
                path: '',
                component: ScheduleListComponent
            },
            {
                path: 'form/:id',
                component: ScheduleFormComponent
            }
        ]
    },
    {
        path: 'user',
        children: [
            {
                path: '',
                component: UserListComponent
            },
            {
                path: 'form/:id',
                component: UserFormComponent
            }
        ]
    },
    {
        path: 'vaccine',
        children: [
            {
                path: '',
                component: VaccineListComponent
            },
            {
                path: 'form/:id',
                component: VaccineFormComponent
            }
        ]
    }
];
