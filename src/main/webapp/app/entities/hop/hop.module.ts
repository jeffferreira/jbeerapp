import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JbeerappSharedModule } from 'app/shared';
import {
    HopComponent,
    HopDetailComponent,
    HopUpdateComponent,
    HopDeletePopupComponent,
    HopDeleteDialogComponent,
    hopRoute,
    hopPopupRoute
} from './';

const ENTITY_STATES = [...hopRoute, ...hopPopupRoute];

@NgModule({
    imports: [JbeerappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [HopComponent, HopDetailComponent, HopUpdateComponent, HopDeleteDialogComponent, HopDeletePopupComponent],
    entryComponents: [HopComponent, HopUpdateComponent, HopDeleteDialogComponent, HopDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JbeerappHopModule {}
