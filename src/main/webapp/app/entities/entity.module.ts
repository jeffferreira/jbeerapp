import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JbeerappRecipeModule } from './recipe/recipe.module';
import { JbeerappHopModule } from './hop/hop.module';
import { JbeerappFermentableModule } from './fermentable/fermentable.module';
import { JbeerappYeastModule } from './yeast/yeast.module';
import { JbeerappStyleModule } from './style/style.module';
import { JbeerappMashModule } from './mash/mash.module';
import { JbeerappMashStepModule } from './mash-step/mash-step.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JbeerappRecipeModule,
        JbeerappHopModule,
        JbeerappFermentableModule,
        JbeerappYeastModule,
        JbeerappStyleModule,
        JbeerappMashModule,
        JbeerappMashStepModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JbeerappEntityModule {}
