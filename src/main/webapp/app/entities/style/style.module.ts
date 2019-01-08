import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JbeerappSharedModule } from 'app/shared';
import {
    StyleComponent,
    StyleDetailComponent,
    StyleUpdateComponent,
    StyleDeletePopupComponent,
    StyleDeleteDialogComponent,
    styleRoute,
    stylePopupRoute
} from './';

const ENTITY_STATES = [...styleRoute, ...stylePopupRoute];

@NgModule({
    imports: [JbeerappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [StyleComponent, StyleDetailComponent, StyleUpdateComponent, StyleDeleteDialogComponent, StyleDeletePopupComponent],
    entryComponents: [StyleComponent, StyleUpdateComponent, StyleDeleteDialogComponent, StyleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JbeerappStyleModule {}
