/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { HopDetailComponent } from 'app/entities/hop/hop-detail.component';
import { Hop } from 'app/shared/model/hop.model';

describe('Component Tests', () => {
    describe('Hop Management Detail Component', () => {
        let comp: HopDetailComponent;
        let fixture: ComponentFixture<HopDetailComponent>;
        const route = ({ data: of({ hop: new Hop(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [HopDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HopDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HopDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.hop).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
