import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JbeerappSharedModule } from 'app/shared';
import {
    MashComponent,
    MashDetailComponent,
    MashUpdateComponent,
    MashDeletePopupComponent,
    MashDeleteDialogComponent,
    mashRoute,
    mashPopupRoute
} from './';

const ENTITY_STATES = [...mashRoute, ...mashPopupRoute];

@NgModule({
    imports: [JbeerappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [MashComponent, MashDetailComponent, MashUpdateComponent, MashDeleteDialogComponent, MashDeletePopupComponent],
    entryComponents: [MashComponent, MashUpdateComponent, MashDeleteDialogComponent, MashDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JbeerappMashModule {}
