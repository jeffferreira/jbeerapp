/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { MashStepDetailComponent } from 'app/entities/mash-step/mash-step-detail.component';
import { MashStep } from 'app/shared/model/mash-step.model';

describe('Component Tests', () => {
    describe('MashStep Management Detail Component', () => {
        let comp: MashStepDetailComponent;
        let fixture: ComponentFixture<MashStepDetailComponent>;
        const route = ({ data: of({ mashStep: new MashStep(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashStepDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MashStepDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MashStepDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mashStep).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
