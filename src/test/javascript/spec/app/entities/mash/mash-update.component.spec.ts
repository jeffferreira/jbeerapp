/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { MashUpdateComponent } from 'app/entities/mash/mash-update.component';
import { MashService } from 'app/entities/mash/mash.service';
import { Mash } from 'app/shared/model/mash.model';

describe('Component Tests', () => {
    describe('Mash Management Update Component', () => {
        let comp: MashUpdateComponent;
        let fixture: ComponentFixture<MashUpdateComponent>;
        let service: MashService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashUpdateComponent]
            })
                .overrideTemplate(MashUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MashUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MashService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Mash(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.mash = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Mash();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.mash = entity;
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
