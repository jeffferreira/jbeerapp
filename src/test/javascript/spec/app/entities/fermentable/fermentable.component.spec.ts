/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JbeerappTestModule } from '../../../test.module';
import { FermentableComponent } from 'app/entities/fermentable/fermentable.component';
import { FermentableService } from 'app/entities/fermentable/fermentable.service';
import { Fermentable } from 'app/shared/model/fermentable.model';

describe('Component Tests', () => {
    describe('Fermentable Management Component', () => {
        let comp: FermentableComponent;
        let fixture: ComponentFixture<FermentableComponent>;
        let service: FermentableService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [FermentableComponent],
                providers: []
            })
                .overrideTemplate(FermentableComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FermentableComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FermentableService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Fermentable(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.fermentables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
