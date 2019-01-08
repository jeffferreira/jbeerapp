import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Fermentable } from 'app/shared/model/fermentable.model';
import { FermentableService } from './fermentable.service';
import { FermentableComponent } from './fermentable.component';
import { FermentableDetailComponent } from './fermentable-detail.component';
import { FermentableUpdateComponent } from './fermentable-update.component';
import { FermentableDeletePopupComponent } from './fermentable-delete-dialog.component';
import { IFermentable } from 'app/shared/model/fermentable.model';

@Injectable({ providedIn: 'root' })
export class FermentableResolve implements Resolve<IFermentable> {
    constructor(private service: FermentableService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Fermentable> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Fermentable>) => response.ok),
                map((fermentable: HttpResponse<Fermentable>) => fermentable.body)
            );
        }
        return of(new Fermentable());
    }
}

export const fermentableRoute: Routes = [
    {
        path: 'fermentable',
        component: FermentableComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fermentables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fermentable/:id/view',
        component: FermentableDetailComponent,
        resolve: {
            fermentable: FermentableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fermentables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fermentable/new',
        component: FermentableUpdateComponent,
        resolve: {
            fermentable: FermentableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fermentables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fermentable/:id/edit',
        component: FermentableUpdateComponent,
        resolve: {
            fermentable: FermentableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fermentables'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fermentablePopupRoute: Routes = [
    {
        path: 'fermentable/:id/delete',
        component: FermentableDeletePopupComponent,
        resolve: {
            fermentable: FermentableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Fermentables'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
