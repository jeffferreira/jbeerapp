/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { MashStepUpdateComponent } from 'app/entities/mash-step/mash-step-update.component';
import { MashStepService } from 'app/entities/mash-step/mash-step.service';
import { MashStep } from 'app/shared/model/mash-step.model';

describe('Component Tests', () => {
    describe('MashStep Management Update Component', () => {
        let comp: MashStepUpdateComponent;
        let fixture: ComponentFixture<MashStepUpdateComponent>;
        let service: MashStepService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashStepUpdateComponent]
            })
                .overrideTemplate(MashStepUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MashStepUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MashStepService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new MashStep(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.mashStep = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new MashStep();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.mashStep = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
