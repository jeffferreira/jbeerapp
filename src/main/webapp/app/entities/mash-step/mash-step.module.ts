import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JbeerappSharedModule } from 'app/shared';
import {
    MashStepComponent,
    MashStepDetailComponent,
    MashStepUpdateComponent,
    MashStepDeletePopupComponent,
    MashStepDeleteDialogComponent,
    mashStepRoute,
    mashStepPopupRoute
} from './';

const ENTITY_STATES = [...mashStepRoute, ...mashStepPopupRoute];

@NgModule({
    imports: [JbeerappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MashStepComponent,
        MashStepDetailComponent,
        MashStepUpdateComponent,
        MashStepDeleteDialogComponent,
        MashStepDeletePopupComponent
    ],
    entryComponents: [MashStepComponent, MashStepUpdateComponent, MashStepDeleteDialogComponent, MashStepDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JbeerappMashStepModule {}
