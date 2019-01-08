import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MashStep } from 'app/shared/model/mash-step.model';
import { MashStepService } from './mash-step.service';
import { MashStepComponent } from './mash-step.component';
import { MashStepDetailComponent } from './mash-step-detail.component';
import { MashStepUpdateComponent } from './mash-step-update.component';
import { MashStepDeletePopupComponent } from './mash-step-delete-dialog.component';
import { IMashStep } from 'app/shared/model/mash-step.model';

@Injectable({ providedIn: 'root' })
export class MashStepResolve implements Resolve<IMashStep> {
    constructor(private service: MashStepService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MashStep> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MashStep>) => response.ok),
                map((mashStep: HttpResponse<MashStep>) => mashStep.body)
            );
        }
        return of(new MashStep());
    }
}

export const mashStepRoute: Routes = [
    {
        path: 'mash-step',
        component: MashStepComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MashSteps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mash-step/:id/view',
        component: MashStepDetailComponent,
        resolve: {
            mashStep: MashStepResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MashSteps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mash-step/new',
        component: MashStepUpdateComponent,
        resolve: {
            mashStep: MashStepResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MashSteps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mash-step/:id/edit',
        component: MashStepUpdateComponent,
        resolve: {
            mashStep: MashStepResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MashSteps'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mashStepPopupRoute: Routes = [
    {
        path: 'mash-step/:id/delete',
        component: MashStepDeletePopupComponent,
        resolve: {
            mashStep: MashStepResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MashSteps'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
