import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Hop } from 'app/shared/model/hop.model';
import { HopService } from './hop.service';
import { HopComponent } from './hop.component';
import { HopDetailComponent } from './hop-detail.component';
import { HopUpdateComponent } from './hop-update.component';
import { HopDeletePopupComponent } from './hop-delete-dialog.component';
import { IHop } from 'app/shared/model/hop.model';

@Injectable({ providedIn: 'root' })
export class HopResolve implements Resolve<IHop> {
    constructor(private service: HopService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Hop> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Hop>) => response.ok),
                map((hop: HttpResponse<Hop>) => hop.body)
            );
        }
        return of(new Hop());
    }
}

export const hopRoute: Routes = [
    {
        path: 'hop',
        component: HopComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hops'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hop/:id/view',
        component: HopDetailComponent,
        resolve: {
            hop: HopResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hops'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hop/new',
        component: HopUpdateComponent,
        resolve: {
            hop: HopResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hops'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hop/:id/edit',
        component: HopUpdateComponent,
        resolve: {
            hop: HopResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hops'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hopPopupRoute: Routes = [
    {
        path: 'hop/:id/delete',
        component: HopDeletePopupComponent,
        resolve: {
            hop: HopResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Hops'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
