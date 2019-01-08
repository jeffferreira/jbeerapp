/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { HopUpdateComponent } from 'app/entities/hop/hop-update.component';
import { HopService } from 'app/entities/hop/hop.service';
import { Hop } from 'app/shared/model/hop.model';

describe('Component Tests', () => {
    describe('Hop Management Update Component', () => {
        let comp: HopUpdateComponent;
        let fixture: ComponentFixture<HopUpdateComponent>;
        let service: HopService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [HopUpdateComponent]
            })
                .overrideTemplate(HopUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HopUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HopService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Hop(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.hop = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Hop();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.hop = entity;
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
