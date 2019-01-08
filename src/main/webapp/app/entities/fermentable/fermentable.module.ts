import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JbeerappSharedModule } from 'app/shared';
import {
    FermentableComponent,
    FermentableDetailComponent,
    FermentableUpdateComponent,
    FermentableDeletePopupComponent,
    FermentableDeleteDialogComponent,
    fermentableRoute,
    fermentablePopupRoute
} from './';

const ENTITY_STATES = [...fermentableRoute, ...fermentablePopupRoute];

@NgModule({
    imports: [JbeerappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FermentableComponent,
        FermentableDetailComponent,
        FermentableUpdateComponent,
        FermentableDeleteDialogComponent,
        FermentableDeletePopupComponent
    ],
    entryComponents: [FermentableComponent, FermentableUpdateComponent, FermentableDeleteDialogComponent, FermentableDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JbeerappFermentableModule {}
