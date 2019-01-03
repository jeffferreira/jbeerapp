import { NgModule } from '@angular/core';

import { JbeerappSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JbeerappSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JbeerappSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JbeerappSharedCommonModule {}
