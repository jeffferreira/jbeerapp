/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { FermentableUpdateComponent } from 'app/entities/fermentable/fermentable-update.component';
import { FermentableService } from 'app/entities/fermentable/fermentable.service';
import { Fermentable } from 'app/shared/model/fermentable.model';

describe('Component Tests', () => {
    describe('Fermentable Management Update Component', () => {
        let comp: FermentableUpdateComponent;
        let fixture: ComponentFixture<FermentableUpdateComponent>;
        let service: FermentableService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [FermentableUpdateComponent]
            })
                .overrideTemplate(FermentableUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FermentableUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FermentableService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Fermentable(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fermentable = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Fermentable();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.fermentable = entity;
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
