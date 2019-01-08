import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Style } from 'app/shared/model/style.model';
import { StyleService } from './style.service';
import { StyleComponent } from './style.component';
import { StyleDetailComponent } from './style-detail.component';
import { StyleUpdateComponent } from './style-update.component';
import { StyleDeletePopupComponent } from './style-delete-dialog.component';
import { IStyle } from 'app/shared/model/style.model';

@Injectable({ providedIn: 'root' })
export class StyleResolve implements Resolve<IStyle> {
    constructor(private service: StyleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Style> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Style>) => response.ok),
                map((style: HttpResponse<Style>) => style.body)
            );
        }
        return of(new Style());
    }
}

export const styleRoute: Routes = [
    {
        path: 'style',
        component: StyleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Styles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'style/:id/view',
        component: StyleDetailComponent,
        resolve: {
            style: StyleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Styles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'style/new',
        component: StyleUpdateComponent,
        resolve: {
            style: StyleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Styles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'style/:id/edit',
        component: StyleUpdateComponent,
        resolve: {
            style: StyleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Styles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stylePopupRoute: Routes = [
    {
        path: 'style/:id/delete',
        component: StyleDeletePopupComponent,
        resolve: {
            style: StyleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Styles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
