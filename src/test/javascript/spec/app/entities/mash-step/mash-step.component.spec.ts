/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JbeerappTestModule } from '../../../test.module';
import { MashStepComponent } from 'app/entities/mash-step/mash-step.component';
import { MashStepService } from 'app/entities/mash-step/mash-step.service';
import { MashStep } from 'app/shared/model/mash-step.model';

describe('Component Tests', () => {
    describe('MashStep Management Component', () => {
        let comp: MashStepComponent;
        let fixture: ComponentFixture<MashStepComponent>;
        let service: MashStepService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashStepComponent],
                providers: []
            })
                .overrideTemplate(MashStepComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MashStepComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MashStepService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MashStep(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.mashSteps[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
