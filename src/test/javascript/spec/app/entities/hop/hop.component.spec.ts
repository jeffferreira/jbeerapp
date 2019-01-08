/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JbeerappTestModule } from '../../../test.module';
import { HopComponent } from 'app/entities/hop/hop.component';
import { HopService } from 'app/entities/hop/hop.service';
import { Hop } from 'app/shared/model/hop.model';

describe('Component Tests', () => {
    describe('Hop Management Component', () => {
        let comp: HopComponent;
        let fixture: ComponentFixture<HopComponent>;
        let service: HopService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [HopComponent],
                providers: []
            })
                .overrideTemplate(HopComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HopComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HopService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Hop(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.hops[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
