import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Mash } from 'app/shared/model/mash.model';
import { MashService } from './mash.service';
import { MashComponent } from './mash.component';
import { MashDetailComponent } from './mash-detail.component';
import { MashUpdateComponent } from './mash-update.component';
import { MashDeletePopupComponent } from './mash-delete-dialog.component';
import { IMash } from 'app/shared/model/mash.model';

@Injectable({ providedIn: 'root' })
export class MashResolve implements Resolve<IMash> {
    constructor(private service: MashService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Mash> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Mash>) => response.ok),
                map((mash: HttpResponse<Mash>) => mash.body)
            );
        }
        return of(new Mash());
    }
}

export const mashRoute: Routes = [
    {
        path: 'mash',
        component: MashComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mashes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mash/:id/view',
        component: MashDetailComponent,
        resolve: {
            mash: MashResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mashes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mash/new',
        component: MashUpdateComponent,
        resolve: {
            mash: MashResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mashes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mash/:id/edit',
        component: MashUpdateComponent,
        resolve: {
            mash: MashResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mashes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mashPopupRoute: Routes = [
    {
        path: 'mash/:id/delete',
        component: MashDeletePopupComponent,
        resolve: {
            mash: MashResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mashes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
